#include <iostream>
#include <vector>

long long merge(std::vector<int>& arr, int left, int mid, int right) {
    std::vector<int> left_half(arr.begin() + left, arr.begin() + mid + 1);
    std::vector<int> right_half(arr.begin() + mid + 1, arr.begin() + right + 1);

    long long split_inversion_count = 0;
    
    int i = 0, j = 0;
    int k = left;

    while (i < left_half.size() && j < right_half.size()) {
        if (left_half[i] <= right_half[j]) {
            arr[k] = left_half[i];
            i++;
        } else {
            arr[k] = right_half[j];
            j++;
            
            split_inversion_count += (left_half.size() - i);
        }
        k++;
    }

    while (i < left_half.size())
        arr[k++] = left_half[i++];

    while (j < right_half.size())
        arr[k++] = right_half[j++];
        
    return split_inversion_count;
}

long long countInversions(std::vector<int>& arr, int left, int right) {
    long long total_inversion_count = 0;
    
    if (left < right) {
        int mid = left + (right - left) / 2;

        total_inversion_count += countInversions(arr, left, mid);
        total_inversion_count += countInversions(arr, mid + 1, right);

        total_inversion_count += merge(arr, left, mid, right);
    }
        
    return total_inversion_count;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int N;
    while (std::cin >> N && N != 0) {
        std::vector<int> permutation(N);

        for (int i = 0; i < N; ++i)
            std::cin >> permutation[i];

        long long total_moves = countInversions(permutation, 0, N - 1);

        if (total_moves % 2 == 0)
            std::cout << "Carlos\n";
        else
            std::cout << "Marcelo\n";
    }

    return 0;
}