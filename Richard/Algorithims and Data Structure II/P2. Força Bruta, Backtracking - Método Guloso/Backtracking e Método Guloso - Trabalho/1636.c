#include <stdio.h>

void permutacao(int n, int np, int P[], int S[]) {
    for (int i = 1; i <= n; i++) {
        if ((S[i - 1] == 0)) {
            P[np - 1] = i;
            S[i - 1] = 1;
            if (np == n) {
                int j = 0;
                int count = 0;
                int modo = 0;
                
                if (P[1] < P[0])
                    modo = 1;
                else if (P[1] > P[0])
                    modo = 2;

                do {
                    // if (modo == 1) {
                    //     if (P[j] < P[j + 1])
                    //         break;
                    //     modo = 2;
                    // } else {
                    //     if (P[j] > P[j + 1])
                    //        break;
                    //     modo = 1;
                    // }
                    j = P[j] - 1;
                    count++;
                } while (j != 0);

                if (count == n) {
                    for (int k = 0; k < n; k++)
                        printf("%d ", P[k]);
                    printf("\n");
                }
            } else {
                permutacao(n, np + 1, P, S);
            }
            S[i - 1] = 0;
        }
    }
}

int main() {
    int n;

    while (1) {
        scanf("%d", &n);

        if (n == 0)
            break;

        int S[n], P[n];

        for (int i = 0; i < n; i++)
            S[i] = 0;

        permutacao(n, 1, P, S);
    }

    return 0;
}