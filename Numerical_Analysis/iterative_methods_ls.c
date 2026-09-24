#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>

double** allocate_A(int n) {
    double** temp = (double**)malloc(n * sizeof(double*));
    if (temp == NULL) return NULL;

    for (int i = 0; i < n; i++) {
        temp[i] = (double*)malloc(n * sizeof(double));
        if (temp[i] == NULL) {
            for (int j = 0; j < i; j++) free(temp[j]);
            free(temp);
            return NULL;
        }
    }

    return temp;
}

double* allocate_b(int n) {
    double* temp = (double*)malloc(n * sizeof(double));
    return temp;
}

void free_system(double** A, double* b, int n) {
    if (A == NULL && b == NULL) return;
    if (A == NULL) {
        free(b);
        return;
    }

    for (int i = 0; i < n; i++)
        free(A[i]);

    free(A);
    free(b);
}

bool check_row_criterion(double** A, size_t n) {
    for (size_t i = 0; i < n; i++) {
        double sum = 0.0;

        for (size_t j = 0; j < n; j++)
            if (j != i) 
                sum += fabs(A[i][j]);

        if (sum >= fabs(A[i][i]))
            return false;
    }

    return true;
}

bool check_sassenfeld(double** A, size_t n) {
    double* beta = (double*)malloc(n * sizeof(double));
    if (beta == NULL) return false;

    double max_beta = 0.0;

    for (size_t i = 0; i < n; i++) {
        double current_sum = 0.0;

        for (size_t j = 0; j < n; j++) {
            if (j != i) {
                if (j < i)
                    current_sum += fabs(A[i][j]) * beta[j];
                else
                    current_sum += fabs(A[i][j]);
            }
        }

        beta[i] = current_sum / fabs(A[i][i]);

        if (beta[i] > max_beta)
            max_beta = beta[i];
    }

    free(beta);

    return (max_beta < 1.0);
}

double relative_error(double* x_old, double* x_new, size_t n) {
    double max_diff = 0.0;
    double max_val = 0.0;

    for (size_t i = 0; i < n; i++) {
        double diff = fabs(x_new[i] - x_old[i]);
        if (diff > max_diff)
            max_diff = diff;

        double val = fabs(x_new[i]);
        if (val > max_val)
            max_val = val;
    }

    if (max_val == 0.0) return 0.0;

    return max_diff / max_val;
}

double* gauss_jacobi(double** A, double* b, size_t n, double tol, int max_iter, double* err, int* iter) {
    double* x_old = (double*)calloc(n, sizeof(double));
    double* x_new = (double*)calloc(n, sizeof(double));

    if (!x_old || !x_new) {
        if (x_old) free(x_old);
        if (x_new) free(x_new);
        return NULL;
    }

    *iter = 0;
    *err = 1.0;

    while ((*err > tol) && (*iter < max_iter)) {
        for (size_t i = 0; i < n; i++) {
            double sum = 0.0;

            for (size_t j = 0; j < n; j++)
                if (j != i)
                    sum += A[i][j] * x_old[j];

            x_new[i] = (b[i] - sum) / A[i][i];
        }

        *err = relative_error(x_old, x_new, n);
        (*iter)++;

        double* temp = x_old;
        x_old = x_new;
        x_new = temp;
    }

    free(x_new);
    return x_old;
}

double* gauss_seidel(double** A, double* b, size_t n, double tol, int max_iter, double* err, int* iter) {
    double* x = (double*)calloc(n, sizeof(double));
    double* x_prev = (double*)malloc(n * sizeof(double));

    if (!x || !x_prev) {
        if (x) free(x);
        if (x_prev) free(x_prev);
        return NULL;
    }

    *iter = 0;
    *err = 1.0;

    while ((*err > tol) && (*iter < max_iter)) {
        for (size_t i = 0; i < n; i++)
            x_prev[i] = x[i];

        for (size_t i = 0; i < n; i++) {
            double sum = 0.0;

            for (size_t j = 0; j < n; j++)
                if (j != i)
                    sum += A[i][j] * x[j];

            x[i] = (b[i] - sum) / A[i][i];
        }

        *err = relative_error(x, x_prev, n);
        (*iter)++;
    }

    free(x_prev);
    return x;
}

int main(int argc, char* argv[]) {
    size_t n = 0;
    printf("\nWhat is the system length?\nR: ");
    scanf("%zu", &n);
    while (getchar() != '\n');
    puts("");

    double** A = allocate_A(n);
    double* b = allocate_b(n);
    if (!A || !b) {
        if (A) free(A);
        if (b) free(b);
        return 1;
    }

    for (size_t i = 0; i < n; i++) {
        for (size_t j = 0; j < n; j++) {
            printf("Equation %zu: x[%zu] = ", i + 1, j + 1);
            scanf("%lf", &A[i][j]);
            while (getchar() != '\n');
        }
        printf("Equation %d: b = ", i + 1);
        scanf("%lf", &b[i]);
        while (getchar() != '\n');

        puts("");
    }
    
    double err;
    int iter;
    double* solution;

    if (!check_row_criterion(A, n))
        fprintf(stderr, "Error: row criterion not satisfied. Gauss-Jacobi skiped.\n");

    else { 
        solution = gauss_jacobi(A, b, n, 1e-8, 100, &err, &iter);
        if (!solution) return 1;
        
        printf(
            "=================================================\n"
            "====          GAUSS-JACOBI SOLUTION          ====\n"
            "=================================================\n"
        );
        for (size_t i = 0; i < n; i++)
            printf("x[%zu] = %lf\t", i + 1, solution[i]);
        printf("\nError = %lf | Total iterations = %d\n", err, iter);
        free(solution);
    }

    if (!check_sassenfeld(A, n)) {
        fprintf(stderr, "Error: Sassenfeld criterion not satisfied.\n");
        return 1;
    }

    else {
        solution = gauss_seidel(A, b, n, 1e-8, 100, &err, &iter);
        if (!solution) return 1;
        
        printf(
            "\n=================================================\n"
            "====          GAUSS-SEIDEL SOLUTION          ====\n"
            "=================================================\n"
        );
        for (size_t i = 0; i < n; i++)
            printf("x[%zu] = %lf\t", i + 1, solution[i]);
        printf("\nError = %lf | Total iterations = %d\n", err, iter);
        free(solution);
    }

    printf("\nProgram finished!\n\n");
    free_system(A, b, n);

    return 0;
}