n = int(input())

tabuleiro = [[0 for _ in range(n)] for _ in range(n)]
    
def cavalo(np: int, l: int, c: int):
    tabuleiro[l][c] = np
    
    if np == n * n:
        for linha in tabuleiro:
            print(linha)
        return
    
    if (0 <= l + 1 < n) and (0 <= c + 2 < n):
        if tabuleiro[l + 1][c + 2] == 0:
            cavalo(np + 1, l + 1, c + 2)
            
    if (0 <= l + 1 < n) and (0 <= c - 2 < n):
        if tabuleiro[l + 1][c - 2] == 0:
            cavalo(np + 1, l + 1, c - 2)
            
    if (0 <= l - 1 < n) and (0 <= c + 2 < n):
        if tabuleiro[l - 1][c + 2] == 0:
            cavalo(np + 1, l - 1, c + 2)
            
    if (0 <= l - 1 < n) and (0 <= c - 2 < n):
        if tabuleiro[l - 1][c - 2] == 0:
            cavalo(np + 1, l - 1, c - 2)
            
    if (0 <= l + 2 < n) and (0 <= c + 1 < n):
        if tabuleiro[l + 2][c + 1] == 0:
            cavalo(np + 1, l + 2, c + 1)
            
    if (0 <= l + 2 < n) and (0 <= c - 1 < n):
        if tabuleiro[l + 2][c - 1] == 0:
            cavalo(np + 1, l + 2, c - 1)
    
    if (0 <= l - 2 < n) and (0 <= c + 1 < n):
        if tabuleiro[l - 2][c + 1] == 0:
            cavalo(np + 1, l - 2, c + 1)
            
    if (0 <= l - 2 < n) and (0 <= c - 1 < n):
        if tabuleiro[l - 2][c - 1] == 0:
            cavalo(np + 1, l - 2, c - 1)
            
    tabuleiro[l][c] = 0
    
cavalo(1, 0, 0)