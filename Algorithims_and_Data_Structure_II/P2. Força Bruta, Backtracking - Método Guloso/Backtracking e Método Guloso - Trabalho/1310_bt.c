#include <stdio.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int maior_soma(int dias, int lucros[]) {
    int max_lucro = 0;

    for (int i = 0; i < dias; i++) {
        int max_lucro_local = 0;
        for (int j = i; j < dias; j++) {
            max_lucro_local += lucros[j];
            max_lucro = max(max_lucro, max_lucro_local);
        }
    }

    return max_lucro;
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