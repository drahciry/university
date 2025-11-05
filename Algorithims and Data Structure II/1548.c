#include <stdio.h>
#include <stdlib.h>

void swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void _quick_sort_recursive(int* arr, int low, int high) {
    while (low < high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] >= pivot) {
                i++;
                swap(&arr[i], &arr[j]);
            }
        }
        swap(&arr[i + 1], &arr[high]);

        int partition_index = i + 1;

        if ((partition_index - low) < (high - partition_index)) {
            _quick_sort_recursive(arr, low, partition_index - 1);
            low = partition_index + 1;
        } else {
            _quick_sort_recursive(arr, partition_index + 1, high);
            high = partition_index - 1;
        }
    }
}

void quick_sort(int* arr, int len) {
    _quick_sort_recursive(arr, 0, len - 1);
}

int main() {
    int N, M, *P, *P_copy, count;

    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        count = 0;
        
        scanf("%d", &M);
        P = malloc(M * sizeof(int));
        P_copy = malloc(M * sizeof(int));
        
        for (int j = 0; j < M; j++) {
            scanf("%d", P + j);
            P_copy[j] = P[j];
        }

        quick_sort(P, M);
        
        for (int j = 0; j < M; j++) {
            if (P_copy[j] == P[j]) count++;
        }
        printf("%d\n", count);

        free(P); free(P_copy);
    }
    return 0;
}