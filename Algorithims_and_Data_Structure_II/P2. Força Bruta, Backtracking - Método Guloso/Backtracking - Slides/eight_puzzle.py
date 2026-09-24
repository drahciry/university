from collections import deque

eight = [1, 3, None, 8, 2, 5, 4, 7, 6]
SOLUCAO = (1, 2, 3, 4, 5, 6, 7, 8, None)

def gera_configs(config_list: list[int]) -> list[tuple[int]]:
    config = list(config_list)
    vazio = config.index(None)
    novas_configs = []
    
    if (vazio % 3 == 0):
        nova_config = config[:]
        nova_config[vazio + 1], nova_config[vazio] = nova_config[vazio], nova_config[vazio + 1]
        novas_configs.append(tuple(nova_config))
    elif (vazio % 3 == 2):
        nova_config = config[:]
        nova_config[vazio - 1], nova_config[vazio] = nova_config[vazio], nova_config[vazio - 1]
        novas_configs.append(tuple(nova_config))
    elif (vazio % 3 == 1):
        nova_config = config[:]
        nova_config[vazio + 1], nova_config[vazio] = nova_config[vazio], nova_config[vazio + 1]
        novas_configs.append(tuple(nova_config))
        nova_config = config[:]
        nova_config[vazio - 1], nova_config[vazio] = nova_config[vazio], nova_config[vazio - 1]
        novas_configs.append(tuple(nova_config))

    if (0 <= vazio - 3):
        nova_config = config[:]
        nova_config[vazio - 3], nova_config[vazio] = nova_config[vazio], nova_config[vazio - 3]
        novas_configs.append(tuple(nova_config))
    if (vazio + 3 < len(config)):
        nova_config = config[:]
        nova_config[vazio + 3], nova_config[vazio] = nova_config[vazio], nova_config[vazio + 3]
        novas_configs.append(tuple(nova_config))

    return novas_configs

def reconstroi_caminho(predecessores: dict, fim: tuple) -> list[tuple]:
    caminho = []
    config_atual = fim

    while config_atual is not None:
        caminho.append(config_atual)
        config_atual = predecessores.get(config_atual)

    return caminho[::-1]

def resolve_eight(config_incial: list[int]):
    inicio = tuple(config_incial)

    fila = deque([inicio])
    visitados = {inicio}
    predecessores = {inicio: None}
    distancia = {inicio: 0}

    while fila:
        config_atual = fila.popleft()

        if config_atual == SOLUCAO:
            print(f"Solução encontrada em {distancia[config_atual]} passos.")

            caminho = reconstroi_caminho(predecessores, SOLUCAO)
            for i, config in enumerate(caminho):
                print(f"Passo {i}: {config}")
            return

        for vizinho in gera_configs(config_atual):
            if vizinho not in visitados:
                visitados.add(vizinho)
                predecessores[vizinho] = config_atual
                distancia[vizinho] = distancia[config_atual] + 1
                fila.append(vizinho)

    print("Nenhuma solução encontrada.")

resolve_eight(eight)