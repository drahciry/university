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

int main(int argc, int* argv[]) {
    return 0;
}