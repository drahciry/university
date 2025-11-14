n = int(input())
S = []

def gera_sub(t: int):
    for i in range(t, n + 1):
        S.append(i)
        print(S)
        if i < n:
            gera_sub(i + 1)
        S.pop()

gera_sub(1)