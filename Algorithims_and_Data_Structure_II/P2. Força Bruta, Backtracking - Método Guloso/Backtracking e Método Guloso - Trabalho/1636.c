#include <stdio.h>
#include <string.h>

int eh_ciclica(int P[], int n) {
    int j = 0;
    int count = 0;

    int visitados[n];
    memset(visitados, 0, n * sizeof(int));

    do {
        if (visitados[j] == 1) return 0;
        visitados[j] = 1;

        j = P[j] - 1;
        count++;
    } while ((j != 0) && (count <= n));

    return ((j == 0) && (count == n));
}

int permutacao(int n, int np, int P[], int S[]) {
    for (int i = 1; i <= n; i++) {
        if (S[i - 1] == 1)
            continue;

        P[np - 1] = i;
        S[i - 1] = 1;

        int poda = 0;
        if (np > 2) {
            int p_prev = P[np - 3];
            int p_curr = P[np - 2];
            int p_next = P[np - 1];
            
            if ((p_prev < p_curr) && (p_curr < p_next))
                poda = 1;
            if ((p_prev > p_curr) && (p_curr > p_next))
                poda = 1;
        }
        
        if (poda == 1) {
            S[i - 1] = 0;
            continue;
        }

        if (np == n) {
            if (eh_ciclica(P, n)) {
                for (int k = 0; k < n; k++)
                    printf("%d ", P[k]);
                printf("\n");
                return 1;
            }
        } else {
            if (permutacao(n, np + 1, P, S))
                return 1;
        }

        S[i - 1] = 0;
    }

    return 0;
}

int main() {
    int n;

    while (1) {
        scanf("%d", &n);

        if (n == 0)
            break;

        int S[n], P[n];
        memset(S, 0, n * sizeof(int));

        permutacao(n, 1, P, S);
    }

    return 0;
}