#include <stdio.h>
#include <math.h>

char eh_possivel(int D, int VF, int VG) {
    double dist_front = sqrt((12 * 12) + (D * D));
    double time_VF = (12.0 / VF);
    double time_VG = (dist_front / VG);

    return (time_VG <= time_VF) ? 'S' : 'N';
}

int main() {
    int D, VF, VG;

    while (scanf("%d", &D) != EOF) {
        scanf("%d %d", &VF, &VG);
        printf("%c\n", eh_possivel(D, VF, VG));
    }
    return 0;
}