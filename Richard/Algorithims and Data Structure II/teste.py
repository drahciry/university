eight = [1, 3, None, 8, 2, 5, 4, 7, 6]
SOLUCAO = [1, 2, 3, 4, 5, 6, 7, 8, None]
posicoes_visitadas = set()
passos = 0

def gera_configs(config: list[int]):
    posicao_vazio = config.index(None)
    novas_configs = []
    


def resolve_eight(eight: list[int]) -> bool:
    if eight == SOLUCAO:
        print(f"Solução feita em {passos} passos.")
        return True
    
    if eight in posicoes_visitadas:
        return False
    
    posicoes_visitadas.add(eight)
    
