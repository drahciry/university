def merge_sort(arr: list) -> list:
    if len(arr) <= 1:
        return arr

    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    sorted_left = merge_sort(left_half)
    sorted_right = merge_sort(right_half)
    
    sorted_arr = []
    i = 0 
    j = 0 

    while i < len(sorted_left) and j < len(sorted_right):
        if sorted_left[i] > sorted_right[j]:
            sorted_arr.append(sorted_left[i])
            i += 1
        else:
            sorted_arr.append(sorted_right[j])
            j += 1

    while i < len(sorted_left):
        sorted_arr.append(sorted_left[i])
        i += 1
    while j < len(sorted_right):
        sorted_arr.append(sorted_right[j])
        j += 1
    

    return sorted_arr

def solve():
    M = int(input())
    Pi = list(map(int, input().split()))

    sorted_Pi = merge_sort(Pi)

    count = 0
    for i in range(M):
        if Pi[i] == sorted_Pi[i]:
            count += 1
    
    print(count)

N = int(input())
for _ in range(N):
    solve()