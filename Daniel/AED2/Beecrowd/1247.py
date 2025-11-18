def solucao(D, VF, VG):
    dguarda = ((12 * 12) + (D * D)) ** 0.5
    dlarao = 12.0
    tladrao = dlarao / VF
    tguarda = dguarda / VG
    return 'S' if tguarda <= tladrao else 'N'


while True:
    D = int(input())
    VF, VG = map(int, input().split())
    print(solucao(D, VF, VG))

