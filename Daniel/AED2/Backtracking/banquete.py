# Como dispor em uma mesa circular 8 convidados de tal forma que inimigos nao sentem juntos

def solucao(convidados: list):
    mesa = []

    def backtrack():
        for convidado in convidados:
            if convidado in mesa:
                continue
            if mesa[-1] == "Putin" and convidado == "Zelensky":
                continue
            elif mesa[-1] == "Valdermort" and convidado == "Harry Potter":
                continue
            elif mesa [-1] == "Sherlock" and convidado == "Moriaty":
                continue
            elif mesa[-1] == "Jobs" and convidado == "Bill":
                continue
            elif convidado == "Putin" and mesa[-1] == "Zelensky":
                continue
            elif convidado == "Valdermort" and mesa[-1] == "Harry Potter":
                continue
            elif convidado == "Sherlock" and mesa[-1] == "Moriaty":
                continue
            elif convidado == "Jobs" and mesa[-1] == "Bill":
                continue
            
            mesa.append(convidado)

            if len(mesa) == 8:
                print(mesa)
                break

            backtrack()
            mesa.remove(convidado)

    mesa.append(convidados[0])
    backtrack()

lista_convidados = [
    "Putin", "Voldermort", "Moriaty", "Jobs", 
    "Zelensky", "Sherlock", "Harry Potter", "Bill"
    ]

solucao(lista_convidados)