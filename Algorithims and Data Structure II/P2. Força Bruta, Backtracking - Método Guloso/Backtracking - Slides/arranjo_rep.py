P = list()

def arranjo(n: int, q: int):
    for i in range(1, n + 1):
        P.append(i)
        if len(P) == q:
            print(P)
        else:
            arranjo(n, q)
        P.pop()
        
n, q = map(int, input().split()) 
arranjo(n, q)