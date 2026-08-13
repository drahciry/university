P = list()

def permuta(n: int):
    for i in range(1, n + 1):
        if i not in P:
            P.append(i)
            if len(P) == n:
                print(P)
            else:
                permuta(n)
            P.pop()
            
permuta(int(input()))