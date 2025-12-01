def letra_a(T: list) -> int:
    custo_acumulado = 0

    while len(T) > 1:
        T.sort()

        arq1 = T.pop(0)
        arq2 = T.pop(0)
        
        mesclagem = arq1 + arq2
        custo_acumulado += mesclagem
        T.append(mesclagem)

    return custo_acumulado

# Complexidade: N log N
