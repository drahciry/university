n = int(input())

P = [None] * n
S = [False] * n
P[n - 1] = n

def permuta_circular(np: int, n: int):
    for i in range(1, n):
        if not S[i - 1]:
            P[np - 1] = i
            S[i - 1] = True
            if np == n - 1:
                print(P)
            else:
                permuta_circular(np + 1, n)
            S[i - 1] = False
            
permuta_circular(1, n)