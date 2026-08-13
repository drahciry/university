n, l, c = map(int, input().split())

P = [None] * n
S = [False] * n

def torres_pacificas(np: int, n: int, l: int, c: int):
    for i in range(1, n + 1):
        if not S[i - 1]:
            P[np - 1] = i
            S[i - 1] = True
            if np == n:
                if P[c - 1] == l:
                    print(P)
            else:
                torres_pacificas(np + 1, n, l, c)
            S[i - 1] = False
            
torres_pacificas(1, n, l, c)