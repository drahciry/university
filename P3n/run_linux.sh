#!/bin/bash
echo "=========================================="
echo " Compilando e Executando Biblioteca (P3nX)"
echo "=========================================="

# Compila
javac -d . P3nX.java GeraBases.java lp2g21/bib/*.java

if [ $? -eq 0 ]; then
    # Verifica se precisa gerar bases
    if [ ! -f "u.dat" ]; then
        echo "[AVISO] Gerando bases de dados iniciais..."
        java GeraBases
    fi
    
    # Executa
    java P3nX
else
    echo "[ERRO] Falha na compilacao."
fi