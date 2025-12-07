def maior_soma(dias, lucros):
    max_lucro = 0

    for i in range(dias):
        max_lucro_local = 0
        for j in range(i, dias):
            max_lucro_local += lucros[j]
            if max_lucro_local > max_lucro:
                max_lucro = max_lucro_local
    
    return max_lucro

def ler_input():
    while True:
        try:
            linha = input()
            for token in linha.split():
                yield token
        except EOFError:
            break

def main():
    tokens = ler_input()
    
    while True:
        try:
            dias_str = next(tokens)
            dias = int(dias_str)
            
            custo_str = next(tokens)
            custo = int(custo_str)
            
            lucros = []
            for _ in range(dias):
                val_str = next(tokens)
                val = int(val_str)
                lucros.append(val - custo)
            
            resultado = maior_soma(dias, lucros)
            print(resultado)
            
        except StopIteration:
            break

if __name__ == "__main__":
    main()