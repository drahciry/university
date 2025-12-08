"""
Este problema é resolvido com uma estratégia Gulosa porque o algoritmo faz a "melhor escolha local" 
imediata para atingir a solução global, sem reconsiderar decisões:

1. Escolha do Caminho: O algoritmo "fominha" escolhe imediatamente a menor distância possível 
   (a linha reta/hipotenusa) até o ponto de fuga. Ele não gasta tempo calculando curvas ou 
   outras rotas.
   
2. Ponto de Interceptação: Ele escolhe o ponto limite (12 milhas) como o alvo único. Se a 
   Guarda Costeira consegue chegar nesse ponto limite a tempo, assume-se que ela consegue 
   interceptar.

A simplicidade de ir direto ao ponto crítico pelo caminho mais curto caracteriza o comportamento guloso.
"""

while True:
    try:
        data = input().split()
        if not data:
            break
            
        D, VF, VG = map(int, data)
        
        dist_guard = (D**2 + 12**2) ** 0.5
        
        time_guard = dist_guard / VG
        time_thief = 12 / VF
        
        if time_guard <= time_thief:
            print('S')
        else:
            print('N')
            
    except EOFError:
        break