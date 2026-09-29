#include <stdio.h>
#include <stdlib.h>

double* newton_divided_differences(double* x, double* y, size_t n) {
    double** table = (double**)malloc(n * sizeof(double*));
    if (!table) return NULL;

    for (size_t i = 0; i < n; i++) {
        table[i] = (double*)calloc(n, sizeof(double));
        if (!table[i]) {
            for (size_t k = 0; k < i; k++) free(table[k]);
            free(table);
            return NULL;
        }
        table[i][0] = y[i];
    }

    for (size_t j = 1; j < n; j++) {
        for (size_t i = 0; i < n - j; i++) {
            double numerator = table[i + 1][j - 1] - table[i][j - 1];
            double denominator = x[i + j] - x[i];

            table[i][j] = numerator / denominator;
        }
    }

    double* coefs = (double*)malloc(n * sizeof(double));
    if (coefs) {
        for (size_t j = 0; j < n; j++)
            coefs[j] = table[0][j];
    }

    for (size_t i = 0; i < n; i++)
        free(table[i]);
    free(table);

    return coefs;
}

double evalute_newton_polynomial(double* x_nodes, double* coefs, size_t n, double target_x) {
    double final_y = 0.0;

    for (size_t i = 0; i < n; i++) {
        double term = coefs[i];
        for (size_t j = 0; j < i; j++)
            term *= (target_x - x_nodes[j]);
        final_y += term;
    }

    return final_y;
}

int main() {
    size_t n;
    
    printf("--- Newton's Polynomial Interpolation ---\n\n");

    printf("How much points will be inserted?\nR: ");
    scanf("%zu", &n);
    while (getchar() != '\n');
    puts("");

    double* x_nodes = (double*)malloc(n * sizeof(double));
    double* y_nodes = (double*)malloc(n * sizeof(double));
    if (!x_nodes || !y_nodes) {
        fprintf(stderr, "[!] Error: memory allocation failed.\n");
        if (x_nodes) free(x_nodes);
        if (y_nodes) free(y_nodes);
        return 1;
    }

    printf("Insert points:\n\n");
    for (size_t i = 0; i < n; i++) {
        printf("x_%zu = ", i);
        scanf("%lf", &x_nodes[i]);
        while(getchar() != '\n');

        printf("y_%zu = ", i);
        scanf("%lf", &y_nodes[i]);
        while(getchar() != '\n');

        puts("");
    }

    double* coefs = newton_divided_differences(x_nodes, y_nodes, n);
    if (!coefs) {
        fprintf(stderr, "[!] Error: memory allocation failed.\n");
        free(x_nodes);
        free(y_nodes);
        return 1;
    }

    printf("[*] Coefficients Extracted:\n");
    for (size_t i = 0; i < n; i++)
        printf("d_%zu = %lf\n", i, coefs[i]);

    double x_target;
    printf("\nInsert x target: ");
    scanf("%lf", &x_target);
    while (getchar() != '\n');
    
    double predicted_y = evalute_newton_polynomial(x_nodes, coefs, n, x_target);

    printf("\n[*] Evaluation:\n");
    printf("For x = %.2lf, the calculated y is: %lf\n", x_target, predicted_y);

    free(x_nodes);
    free(y_nodes);
    free(coefs);
    return 0;
}