n, q = map(int, input().split())

P = [None] * (q + 1)
P[0] = 0

def combinacao(np: int, n: int, q: int):
    for i in range(1, n + 1):
        if i > P[np - 1]:
            P[np] = i
            if np == q:
                print(P[1:])
            else:
                combinacao(np + 1, n, q)
                
combinacao(1, n, q)