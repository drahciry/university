#include <math.h>
#include <stdio.h>
#include <stdbool.h>

bool bissection(double (*f)(double), double a, double b, double tol, int max_iter, double* xk, int* iter) {
    double f_a = f(a);
    double f_b = f(b);

    if ((f_a > 0.0) == (f_b > 0.0) && f_a != 0.0 && f_b != 0.0)
        return false;

    double error = fabs(b - a) / 2.0;
    double f_xk;
    *iter = 0;

    while ((error > tol) && (*iter < max_iter)) {
        (*iter)++;

        *xk = (a + b) / 2.0;
        f_xk = f(*xk);

        printf("Iteration: %2d | Error: %f | a: %f | b: %f | xk: %f\n", *iter, error, a, b, *xk);

        if (f_xk == 0) break;

        if ((f_a > 0.0) != (f_xk > 0.0)) {
            b = *xk;
        } else {
            a = *xk;
            f_a = f_xk;
        }

        error = fabs(b - a) / 2.0;
    }

    *xk = (a + b) / 2.0;

    return true;
}

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