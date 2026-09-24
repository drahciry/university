with open('arrays_1.txt', 'r', encoding='UTF-8') as file:
    arrays = file.read().split('\n\n\n')
    
for array in arrays:
    array = list(map(int, array.split(', ')))   
    print(type(array))
