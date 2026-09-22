#include <math.h>
#include <stdio.h>
#include <stdlib.h>
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

int* allocate_P(int n) {
    int* temp = (int*)malloc(n * sizeof(int));
    return temp;
}

double* allocate_b(int n) {
    double* temp = (double*)malloc(n * sizeof(double));
    return temp;
}

int lu_decomposition(double** A, int* P, int n) {
    for (int i = 0; i < n; i++)
        P[i] = i;

    for (int k = 0; k < n; k++) {
        int max_row = k;
        double max_val = fabs(A[k][k]);

        for (int i = k + 1; i < n; i++) {
            if (fabs(A[i][k]) > max_val) {
                max_val = fabs(A[i][k]);
                max_row = i;
            }
        }

        if (max_val < 1e-12) {
            printf("Critical Error: Singular matrix detected at column %d.\n", k);
            return false;
        }

        if (max_row != k) {
            double* temp_ptr = A[k];
            A[k] = A[max_row];
            A[max_row] = temp_ptr;

            int temp_p = P[k];
            P[k] = P[max_row];
            P[max_row] = temp_p;
        }

        for (int i = k + 1; i < n; i++) {
            double m = A[i][k] / A[k][k];
            A[i][k] = m;

            for (int j = k + 1; j < n; j++)
                A[i][j] = A[i][j] - (m * A[k][j]);
        }
    }

    return true;
}

double* lu_solve(double** LU, int* P, double* b, int n) {
    double* y = (double*)malloc(n * sizeof(double));
    double* x = (double*)malloc(n * sizeof(double));

    if (!y || !x) {
        if (y) free(y);
        if (x) free(x);
        return NULL;
    }

    for (int i = 0; i < n; i++)
        y[i] = b[P[i]];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < i; j++)
            y[i] = y[i] - (LU[i][j] * y[j]);

    for (int i = n - 1; i >= 0; i--) {
        x[i] = y[i];

        for (int j = i + 1; j < n; j++)
            x[i] = x[i] - (LU[i][j] * x[j]);

        x[i] = x[i] / LU[i][i];
    }

    free(y);
    return x;
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

int main(int argc, char* argv[]) {
    int n = 0;
    printf("What is the system length?\nR: ");
    scanf("%d", &n);
    while (getchar() != '\n');

    double** A = allocate_A(n);
    int* P = allocate_P(n);
    double* b = allocate_b(n);
    if (!A || !P || !b) {
        if (A) free(A);
        if (P) free(P);
        if (b) free(b);
        return 1;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("Equation %d: x[%d] = ", i + 1, j + 1);
            scanf("%lf", &A[i][j]);
            while (getchar() != '\n');
        }
        printf("Equation %d: b = ", i + 1);
        scanf("%lf", &b[i]);
        while (getchar() != '\n');

        puts("");
    }

    if (!lu_decomposition(A, P, n)) return 1;
    double* solution = lu_solve(A, P, b, n);

    if (!solution) {
        fprintf(stderr, "Error: the values are too small.\n");
        return 1;
    }
    
    for (int i = 0; i < n; i++)
        printf("x[%d] = %lf\n", i + 1, solution[i]);

    free(solution);
    free_system(A, b, n);

    return 0;
}