#include <stdio.h>

int cont_solucao = 0;

int mdc(int a, int b) {
    int resto;

    while (b != 0) {
        resto = a % b;
        a = b;
        b = resto;
    }

    return a;
}

int abs(int a) {
    return (a > 0) ? (a) : (a * (-1));
}

int verifica(int xi, int yi, int xf, int yf) {
    int dl = abs(xi - xf);
    int dc = abs(yi - yf);

    return mdc(dl, dc) == 1;
}

void backtracking(int n, int p, int x, int y, int np) {
    if (np == p) {
        cont_solucao++;
        return;
    }

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (verifica(x, y, i, j))
                backtracking(n, p, i, j, np + 1);
}

void solucao(int n, int p) {
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            backtracking(n, p, i, j, 1);
}

int main() {
    int n, p;

    while (1) {
        scanf("%d %d", &n, &p);
        if (!n && !p)
            break;
        solucao(n, p);
        printf("%d\n", cont_solucao % 1300031);
        cont_solucao = 0;
    }

    return 0;
}