#include <stdio.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int maior_soma(int dias, int lucros[]) {
    int memo[dias + 1];
    int max_value;

    memo[0] = lucros[0];
    max_value = memo[0];

    for (int i = 1; i <= dias; i++) {
        memo[i] = max(lucros[i], lucros[i] + memo[i - 1]);

        if (memo[i] > max_value)
            max_value = memo[i];
    }

    return max_value;
}

int main() {
    int dias, custo;

    while (scanf("%d", &dias) != EOF) {
        scanf("%d", &custo);

        int lucros[dias + 1];

        lucros[0] = 0;
        for (int i = 1; i <= dias; i++) {
            scanf("%d", &lucros[i]);
            lucros[i] -= custo;
        }

        printf("%d\n", maior_soma(dias, lucros));
    }

    return 0;
}