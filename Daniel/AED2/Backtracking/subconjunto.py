def solucao(conjunto: list):
    subconjuntos = []
    
    def backtrack(inicio: int, caminho_atual: list):
        subconjuntos.append(caminho_atual[:])

        for i in range(inicio, len(conjunto)):
            caminho_atual.append(conjunto[i])
            backtrack(i + 1, caminho_atual)
            caminho_atual.pop()

    backtrack(0, [])
    return subconjuntos

conjunto = [1, 2, 3, 4, 5]

print(solucao(conjunto))