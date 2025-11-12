n, q = map(int, input().split())
S = [False] * n
P = [None] * q

def arranjo(np: int, n: int, q: int):
    for i in range(1, n + 1):
        if not S[i - 1]:
            P[np - 1] = i
            S[i - 1] = True
            if np == q:
                print(P)
            else:
                arranjo(np + 1, n, q)
            S[i - 1] = False
            
arranjo(1, n, q)