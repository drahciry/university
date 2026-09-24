def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] >= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[high] = arr[high], arr[i + 1]

    return i + 1

def quick_sort(arr, low, high):
    if low < high:
        index = partition(arr, low, high)
        quick_sort(arr, low, index - 1)
        quick_sort(arr, index + 1, high)
        
def solve():
    M = int(input())
    Pi = list(map(int, input().split()))
    
    sorted_Pi = Pi[:]
    quick_sort(sorted_Pi, 0, M - 1)
    
    count = 0
    for i in range(M):
        if Pi[i] == sorted_Pi[i]:
            count += 1
    
    print(count)

N = int(input())
for _ in range(N):
    solve()