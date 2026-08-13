#include <math.h>
#include <stdio.h>
#include <stdbool.h>

bool linear_iteration(double (*phi)(double), double x0, double tol, int max_iter, double* zero, int* iter) {
    double x_current = x0;
    double x_next;

    *zero = 0;
    *iter = 0;

    while (*iter < max_iter) {
        (*iter)++;

        x_next = phi(x_current);

        double error = fabs(x_next - x_current);

        printf("Iteration: %2d | Error: %f | xk: %f | xk+1: %f\n", *iter, error, x_current, x_next);

        if (error < tol) {
            *zero = x_next;
            return true;
        }

        x_current = x_next;
    }

    *zero = x_current;
    return false;
}

int main(int argc, char* argv[]) {
    return 0;
}