listSolucao = []
def verifica_ciclo(p: list):
    cont = 0
    chave = -1
    while chave != 0 and cont <= len(p):
        if chave == -1:
            chave = 0
        chave = p[chave] - 1
        cont += 1
    return chave == 0 and cont == len(p)

def solucao(n: int):

    for i in range(1, n+1):
        if i in listSolucao:
            continue

        tam = len(listSolucao)

        poda = False
            
        if (tam >= 2):
            prev = listSolucao[-2]
            current = listSolucao[-1]
            next = i
            if (prev > current and current > next):
                poda = True
            if (prev < current and current < next):
                poda = True
            
        if poda:
            continue

        listSolucao.append(i)

        if len(listSolucao) == n and verifica_ciclo(listSolucao):
            print(listSolucao)
            return True
        else:
            if solucao(n):
                return True
        listSolucao.pop()
    return False

solucao(int(input()))