#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char **frase;
int size;

char *slice(char *string, int p, int q) {
    int length = q - p;
    char *newString = (char *)malloc((length + 1) * sizeof(char));
    if (!newString) 
        return NULL;

    for (int i = 0; i < (q - p); i++)
        newString[i] = string[p + i];
    newString[length] = '\0';

    return newString;
}

int strIn(char *string, char **array, int length) {
    for (int i = 0; i < length; i++)
        if (!strcmp(string, array[i])) 
            return 1;
    return 0;
}

void printFrase(char **frase) {
    for (int i = 0; i < size; i++) {
        if (i == size - 1)
            printf("%s\n", frase[i]);
        else
            printf("%s ", frase[i]);
    }
}

void frase_bt(char *cadeia, char **dicionario, int len) {
    int length = strlen(cadeia);
    for (int i = 0; i < length; i++) {
        for (int j = i + 1; j < length + 1; j++) {
            char *substring = slice(cadeia, i, j);
            if (strIn(substring, dicionario, len)) {
                size++;
                frase = (char **)realloc(frase, size * sizeof(char *));
                frase[size - 1] = substring;

                char *restoCadeia = slice(cadeia, j, length);
                frase_bt(restoCadeia, dicionario, len);
                free(restoCadeia);

                size--;
                free(frase[size]);
                frase = (char **)realloc(frase, size * sizeof(char *));
            } else {
                free(substring);
            }
        }
    }

    if (size > 0)
        printFrase(frase);
}

int main() {
    size = 0;
    frase = NULL;
    char *cadeia = "gatosecao";
    char *dicionario[] = {"gato", "gatos", "e", "cao"};

    frase_bt(cadeia, dicionario, 4);
    for (int i = 0; i < size; i++) free(frase[i]);
    free(frase);

    return 0;
}