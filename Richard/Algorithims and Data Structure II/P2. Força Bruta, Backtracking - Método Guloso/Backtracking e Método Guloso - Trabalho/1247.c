#include <stdio.h>
#include <math.h>

char eh_possivel(int VF, int VG, int D) {
    double tempo_guarda = 0.0;
    double limite_tempo = (12.0 / VF);
    double distG = sqrt((12.0 * 12.0) + (D * D));

    while (distG > 0) {
        if (tempo_guarda > limite_tempo)
            return 'N';
        distG -= VG;
        tempo_guarda++;
    }

    return (tempo_guarda <= limite_tempo) ? 'S' : 'N';
}

int main() {
    int D, VF, VG;

    while (scanf("%d", &D) != EOF) {
        scanf("%d %d", &VF, &VG);
        printf("%c\n", eh_possivel(VF, VG, D));
    }
    return 0;
}