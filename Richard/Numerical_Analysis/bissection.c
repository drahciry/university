#include <math.h>
#include <stdio.h>
#include <stdbool.h>

bool bissection(double (*f)(double), double a, double b, double tol, int max_iter, double* zero, int* iter) {
    double f_a = f(a);
    double f_b = f(b);

    if ((f_a > 0.0) == (f_b > 0.0) && f_a != 0.0 && f_b != 0.0)
        return false;

    double error = fabs(b - a) / 2.0;
    double m;
    double f_m;
    *iter = 0;

    while ((error > tol) && (*iter < max_iter)) {
        (*iter)++;

        m = (a + b) / 2.0;
        f_m = f(m);

        printf("Iteration: %2d | Error: %f | a: %f | b: %f | m: %f\n", *iter, error, a, b, m);

        if (f_m == 0) break;

        if ((f_a > 0.0) != (f_m > 0.0)) {
            b = m;
        } else {
            a = m;
            f_a = f_m;
        }

        error = fabs(b - a) / 2.0;
    }

    *zero = (a + b) / 2.0;

    if (*iter == max_iter) {
        printf("Warning: Iteration limit reached. The zero is unreliable.\n");
        return false;
    }

    return true;
}

int main(int argc, int* argv[]) {
    return 0;
}