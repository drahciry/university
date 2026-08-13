#include <math.h>
#include <stdio.h>
#include <stdbool.h>

bool newton_raphson(double (*f)(double), double (*df)(double), double x0, double tol, int max_iter, double* zero, int* iter) {
    double x_current = x0;
    double x_next;

    const double EPSILON = 1e-12;

    *zero = 0.0;
    *iter = 0;

    while (*iter < max_iter) {
        (*iter)++;

        double fx = f(x_current);
        double dfx = df(x_current);

        if (fabs(dfx) < EPSILON) {
            printf("Error: Derivative is zero. Tangent is horizontal.\n");
            *zero = x_current;
            return false;
        }

        x_next = x_current - (fx / dfx);

        if (isinf(x_next) || isnan(x_next)) {
            printf("Error: Function diverged to Infinity or NaN.\n");
            *zero = x_current;
            return false;
        }

        double error = fabs(x_next - x_current);

        if (error < tol) {
            *zero = x_next;
            return true;
        }

        x_current = x_next;
    }

    printf("Warning: Iteration limit reached. The zero is unreliable.\n");
    *zero = x_current;
    return false;
}

int main(int argc, char* argv[]) {
    return 0;
}