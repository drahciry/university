#include <stdio.h>
#include <string.h>

int cont_solucao = 0;

int verifica(int xi, int yi, int xf, int yf) {
    if (xi == xf) {
        if ((yi == yf - 1) || (yi == yf + 1))
            return 1;
        return 0;
    }
    if (yi == yf) {
        if ((xi == xf - 1) || (xi == xf + 1))
            return 1;
        return 0;
    }
    if ((xi - yi) == (xf - yf)) {
        if ((xi - xf) == (yi - yf))
            return 1;
        return 0;
    }
    if ((xi + yi) == (xf + yf)) {
        if ((xi + xf) == (yi + yf))
            return 1;
        return 0;
    }
    return 1;
}

void backtracking(int n, int p, int x, int y, int np, int M[][n]) {
    M[x][y] = np;

    if (np == p) {
        cont_solucao++;
        return;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (verifica(x, y, i, j)) {
                backtracking(n, p, i, j, np + 1, M);
                M[i][j] = -1;
            }
        }
    }
}

void solucao(int n, int p) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int M[n][n];

            for (int k = 0; k < n; k++)
                memset(M[k], -1, n * sizeof(int));

            backtracking(n, p, i, j, 1, M);
        }
    }
}

int main() {
    int n, p;

    while (1) {
        scanf("%d %d", &n, &p);
        if (!n && !p)
            break;
        solucao(n, p);
        printf("%d\n", cont_solucao);
        cont_solucao = 0;
    }

    return 0;
}