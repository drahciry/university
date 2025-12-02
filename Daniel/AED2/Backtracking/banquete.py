# Como dispor em uma mesa circular 8 convidados de tal forma que inimigos nao sentem juntos

def solucao(convidados: list):
    mesa = []

    def da_briga(a, b):
        if (a  == "Putin" and b == "Zelensky") or (b  == "Putin" and a == "Zelensky"):
            return True
        if (a == "Valdermort" and b == "Harry Potter") or (b == "Valdermort" and a == "Harry Potter"):
            return True
        if (a == "Sherlock" and b == "Moriaty") or (b == "Sherlock" and a == "Moriaty"):
            return True
        if (a == "Jobs" and b == "Bill") or (b == "Jobs" and a == "Bill"):
            return True
        
        return False

    def backtrack():
        for convidado in convidados:
            if convidado in mesa:
                continue

            if da_briga(mesa[-1], convidado):
                continue
            
            if len(mesa) == 7:
                if da_briga(mesa[0], convidado):
                    continue

            mesa.append(convidado)

            if len(mesa) == 8:
                print(mesa)

            backtrack()
            mesa.remove(convidado)

    mesa.append(convidados[0])
    backtrack()

lista_convidados = [
    "Putin", "Voldermort", "Moriaty", "Jobs", 
    "Zelensky", "Sherlock", "Harry Potter", "Bill"
    ]

solucao(lista_convidados)