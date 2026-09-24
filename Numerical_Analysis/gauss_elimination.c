#include <stdio.h>
#include <stdlib.h>

double** allocate_system(int n) {
    double** temp = (double**)malloc(n * sizeof(double*));
    if (temp == NULL) return NULL;

    for (int i = 0; i < n; i++) {
        temp[i] = (double*)malloc((n + 1) * sizeof(double));
        if (temp[i] == NULL) {
            for (int j = 0; j < i; j++) free(temp[j]);
            free(temp);
            return NULL;
        }
    }

    return temp;
}

double* gauss_elimination(double** system, int n) {
    for (int k = 0; k < n; k++) {
        for (int i = k + 1; i < n; i++) {
            double m = system[i][k] / system[k][k];

            for (int j = k; j < n + 1; j++)
                system[i][j] = system[i][j] - (m * system[k][j]);
        }
    }

    double* xn = (double*)malloc(n * sizeof(double));
    if (xn == NULL) return NULL;

    for (int i = n - 1; i >= 0; i--) {
        xn[i] = system[i][n];

        for (int j = i + 1; j < n; j++)
            xn[i] = xn[i] - (system[i][j] * xn[j]);

        xn[i] = xn[i] / system[i][i];
    }

    return xn;
}

void free_system(double** system, int n) {
    if (system == NULL) return;
    
    for (int i = 0; i < n; i++)
        free(system[i]);

    free(system);
}

int main(int argc, char* argv[]) {
    int n = 0;
    printf("What is the system length?\nR: ");
    scanf("%d", &n);
    while (getchar() != '\n');

    double** system = allocate_system(n);
    if (system == NULL) return 1;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("Equation %d: x[%d] = ", i + 1, j + 1);
            scanf("%lf", &system[i][j]);
            while (getchar() != '\n');
        }
        printf("Equation %d: b = ", i + 1);
        scanf("%lf", &system[i][n]);
        while (getchar() != '\n');

        puts("");
    }

    double* solution = gauss_elimination(system, n);

    for (int i = 0; i < n; i++)
        printf("x[%d] = %lf\n", i + 1, solution[i]);

    free(solution);
    free_system(system, n);

    return 0;
}