#include <stdio.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int maior_soma(int dias, int lucros[]) {
    int max_lucro_ate_aqui = 0;
    int max_lucro_total = 0;

    for (int i = 0; i < dias; i++) {
        max_lucro_ate_aqui += lucros[i];

        if (max_lucro_ate_aqui < 0)
            max_lucro_ate_aqui = 0;

        max_lucro_total = max(max_lucro_total, max_lucro_ate_aqui);
    }

    return max_lucro_total;
}

int main() {
    int dias, custo;

    while (scanf("%d", &dias) != EOF) {
        scanf("%d", &custo);

        int lucros[dias];
        for (int i = 0; i < dias; i++) {
            scanf("%d", &lucros[i]);
            lucros[i] -= custo;
        }

        printf("%d\n", maior_soma(dias, lucros));
    }

    return 0;
}