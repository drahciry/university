def mdc(a: int, b: int) -> int:
    while b !=0:
        resto = a % b
        a = b
        b = resto

    return a

def verifica(xi: int, yi: int, xf: int, yf: int) -> bool:
    dist_linhas = abs(xi - xf)
    dist_colunas = abs(yi - yf)
    
    return mdc(dist_linhas, dist_colunas) == 1

def backtrack(x: int, y: int, np: int, n: int, p: int) -> int:
    global cont_solucao

    if (np == p):
        cont_solucao += 1
        return
    
    for i in range(n):
        for j in range(n):
            if not verifica(x, y, i, j):
                continue
            backtrack(i, j, np + 1, n, p)

def solucao(n: int, p: int) -> int:
    for i in range(n):
        for j in range(n):
            backtrack(i, j, 1, n, p)
    
    return cont_solucao

while True:
    n, p = map(int, input().split())
    if (n == 0) and (p == 0):
        break
    cont_solucao = 0
    print(solucao(n, p) % 1300031)