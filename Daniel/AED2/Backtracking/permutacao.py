def solucao(str: list):
    totalPermutacoes = []
    permutacao = []

    def backtrack(str: list):
        if not str:
            totalPermutacoes.append(list(permutacao))
            return

        for i in range(len(str)):
            permutacao.append(str[i])
            resto = str[:i] + str[i+1:]
            backtrack(resto)
            permutacao.pop()

    backtrack(str) 
    
    for item in totalPermutacoes:
        print(item)

solucao("1234")