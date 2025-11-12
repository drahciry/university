n, q = map(int, input().split())

P = [None] * q

def combinacao_v2(np: int, t: int, n: int, q: int):
    for i in range(t, n + 1):
        P[np - 1] = i
        if np == q:
            print(P)
        else:
            combinacao_v2(np + 1, i + 1, n, q)
            
combinacao_v2(1, 1, n, q)