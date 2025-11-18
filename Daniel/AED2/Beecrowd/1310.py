def solucao(dias: int, cuto_diario: int, receias: list):
    if dias == 0:
        return 0
    
    lucros_diarios = [receias[i] - cuto_diario for i in range(dias)]
    lucro_total = 0
    lucro_atual = 0

    for i in lucros_diarios:
        lucro_atual += i

        if lucro_atual <= 0:
            lucro_atual = 0

        if lucro_total < lucro_atual:
            lucro_total = lucro_atual
    
    return lucro_total

print(solucao(6, 20, [18, 35, 6, 80, 15, 21]))