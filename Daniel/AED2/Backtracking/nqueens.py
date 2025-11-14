# Dado um tabuleiro de xadrez de tamanho N x N, posicione N rainhas no tabuleiro 
# de forma que nenhuma rainha ataque ou possa ser capturada por qualquer outra rainha.

def solucao(n: int):
    listaSolucao = []
    solucoesEncontradas = []

    def guardaPosRainha(x: int, y: int):
       listaSolucao.append((x,y))

    def removePosRainha():
        if listaSolucao:
            listaSolucao.pop()

    def podeColocarRainha(x: int, y: int) -> bool:
        for q in listaSolucao:
            if ((x == q[0]) or (y == q[1])):
                return False
            if (abs(x - q[0]) == abs(y - q[1])):
                return False
            
        return True

    def backtrack(linhaAtual: int):
        if linhaAtual == n:
            solucoesEncontradas.append(list(listaSolucao))
            return

        for j in range(n):
            if podeColocarRainha(linhaAtual, j):
                guardaPosRainha(linhaAtual, j)
                backtrack(linhaAtual + 1)
                removePosRainha()

    backtrack(0)

    if not solucoesEncontradas:
        print("Sem solução")
    else:
        print(len(solucoesEncontradas), " Soluções Encontradas:\n")
        for solucao in solucoesEncontradas:
            print(solucao)

solucao(4)