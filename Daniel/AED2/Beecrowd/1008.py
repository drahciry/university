def merge_sort_and_count(arr: list) -> tuple[list, int]:
    if len(arr) <= 1:
        return arr, 0

    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    sorted_left, left_inversions = merge_sort_and_count(left_half)
    sorted_right, right_inversions = merge_sort_and_count(right_half)

    inversions = left_inversions + right_inversions
    
    merged_arr = []
    i, j = 0, 0

    while i < len(sorted_left) and j < len(sorted_right):
        if sorted_left[i] <= sorted_right[j]:
            merged_arr.append(sorted_left[i])
            i += 1
        else:
            merged_arr.append(sorted_right[j])
            inversions += len(sorted_left) - i
            j += 1

    merged_arr.extend(sorted_left[i:])
    merged_arr.extend(sorted_right[j:])
    
    return merged_arr, inversions


while True:
    try:
        line = input().split()
        if not line:
            continue
        
        list_size = int(line[0])
        if list_size == 0:
            break
            
        numbers = list(map(int, line[1:]))

        _, total_inversions = merge_sort_and_count(numbers)

        if total_inversions % 2 == 1:
            print("Marcelo")
        else:
            print("Carlos")

    except (EOFError, IndexError):
        break