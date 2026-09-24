n, s = map(int, input().split())
S = []

soma = 0

def gera_sub_soma(t: int):
    global soma
    for i in range(t, n + 1):
        S.append(i)
        soma += i
        if soma == s:
            print(S)
        if i < n:
            gera_sub_soma(i + 1)
        S.pop()
        soma -= i

gera_sub_soma(1)
        