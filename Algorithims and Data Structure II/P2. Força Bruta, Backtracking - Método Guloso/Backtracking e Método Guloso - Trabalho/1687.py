def solucao(n: int, p: int) -> int:

    def verifica(xi: int, yi: int, xf: int, yf: int) -> bool:
        if (xi == xf):
            if ((yi == yf - 1) or (yi == yf + 1)):
                return True
            return False
        if (yi == yf):
            if ((xi == xf - 1) or (xi == xf + 1)):
                return True
            return False
        if ((xi - yi) == (xf - yf)):
            if ((xi - xf) == (yi - yf)):
                return True
            return False
        if ((xi + yi) == (xf + yf)):
            if ((xi + xf) == (yi + yf)):
                return True
            return False
        return True

    def backtrack(x: int, y: int, np: int) -> int:
        M[x][y] = np
        global cont_solucao

        if (np == p):
            cont_solucao += 1
            return
        
        for i in range(n):
            for j in range(n):
                if verifica(x, y, i, j):
                    if (M[i][j] != np - 1):
                        backtrack(i, j, np + 1)
                        M[i][j] = None

    for i in range(n):
        for j in range(n):
            M = [[None for _ in range(n)] for _ in range(n)]
            backtrack(i, j, 1)
    
    return cont_solucao

while True:
    n, p = map(int, input().split())
    if (n == 0) and (p == 0):
        break
    cont_solucao = 0
    print(solucao(n, p) % 1300031)