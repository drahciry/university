from random import randint

array_1 = [randint(1, 10000) for _ in range(100)]
array_2 = [randint(1, 10000) for _ in range(1000)]
array_3 = [randint(1, 10000) for _ in range(10000)]

with open('arrays_1.txt', 'w', encoding='UTF-8') as file:
    file.write(f"{', '.join(map(str, array_1))}\n\n\n{', '.join(map(str, array_2))}\n\n\n{', '.join(map(str, array_3))}")
    
# print(array_1)
# print(array_2)
# print(array_3)