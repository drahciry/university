def solucao(xi: int, yi: int, n: int):
    
    tabuleiro = [[0 for _ in range(n)] for _ in range(n)]
    
    movimentos = [
        (2, 1), (2, -1), (-2, 1), (-2, -1),
        (1, 2), (1, -2), (-1, 2), (-1, -2)
    ]
    
    def verifica(x: int, y: int):
        return (0 <= x < n) and (0 <= y < n)

    def backtrack(x: int, y: int, passoAtual: int):
        
        tabuleiro[x][y] = passoAtual
        
        if passoAtual == n**2:
            return True
        
        for dx, dy in movimentos:
            prox_x, prox_y = x + dx, y + dy
            
            if verifica(prox_x, prox_y) and tabuleiro[prox_x][prox_y] == 0:
                
                if backtrack(prox_x, prox_y, passoAtual + 1):
                    return True
        
        tabuleiro[x][y] = 0
        return False

    if backtrack(xi, yi, 1):
        for linha in tabuleiro:
            largura = len(str(n**2)) + 1
            print(" ".join(f"{str(celula):>{largura}}" for celula in linha))
    else:
        print("Nenhuma solução encontrada")
        
solucao(0, 0, 5)