def frase_bt(cadeia: str, dicionario: list[str], frase: list[str] = None):
    if not frase:
        frase = list()
    
    for i in range(len(cadeia)):
        for j in range(i + 1, len(cadeia) + 1):
            if cadeia[i:j] in dicionario:
                frase.append(cadeia[i:j])
                frase_bt(cadeia[j:], dicionario, frase)
                frase.pop()
        
    if frase:
        print(" ".join(map(str, frase)))
        
frase_bt("gatosecao", ["gato", "gatos", "e", "cao"])
            