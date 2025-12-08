@echo off
echo ==========================================
echo  Compilando e Executando Biblioteca (P3nX)
echo ==========================================

REM Compila todos os arquivos
javac -d . P3nX.java GeraBases.java lp2g21/bib/*.java

REM Verifica erro de compilacao
if %errorlevel% neq 0 (
    echo [ERRO] Falha na compilacao.
    pause
    exit /b %errorlevel%
)

REM Se nao existem os arquivos .dat, pergunta se quer gerar
if not exist u.dat (
    echo [AVISO] Arquivos de dados nao encontrados.
    echo Gerando u.dat e l.dat automaticamente...
    java GeraBases
)

REM Executa o programa principal
java P3nX
pause