n = int(input())
P = [None] * n
S = [False] * n

def permuta_v2(np: int, n: int):
    for i in range(1, n + 1):
        if not S[i - 1]:
            P[np - 1] = i
            S[i - 1] = True
            if np == n:
                print(P)
            else:
                permuta_v2(np + 1, n)
            S[i - 1] = False
            
permuta_v2(1, n)