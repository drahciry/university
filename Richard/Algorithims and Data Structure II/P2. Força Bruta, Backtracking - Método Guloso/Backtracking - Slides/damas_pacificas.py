P = [None] * 8

def possivel(l: int, c: int):
    for i in range(1, c):
        if P[i - 1] == l:
            return False
        
        if P[i - 1] - i == l - c:
            return False
        if P[i - 1] + i == l + c:
            return False
        
    return True

def damas_pacificas(c: int):
    for l in range(1, 9):
        if possivel(l, c):
            P[c - 1] = l
            if c == 8:
                print(P)
            else:
                damas_pacificas(c + 1)
                
damas_pacificas(1)