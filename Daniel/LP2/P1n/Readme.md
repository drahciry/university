# CLASSE: Pessoa

DESCRIÇÃO:
Modelo de dados em Java para armazenar informações de uma pessoa (Nome, Data de Nascimento, CPF, Peso e Altura). A classe utiliza as classes de validação externas (ValidaData, ValidaCPF) e internamente garante que todos os dados sejam válidos antes de serem armazenados.

## ATRIBUTOS PRINCIPAIS (Dados da Pessoa)

- nome, sobreNome: Nomes da pessoa.
- dataNasc: Data de nascimento (objeto GregorianCalendar).
- numCPF: CPF (armazenado como long).
- peso, altura: Dados biométricos (float).
- numPessoasCriadas (static): Contador do total de objetos Pessoa criados.

## MÉTODOS CHAVE (Funcionalidades)

### CONSTRUTORES (Quatro Sobrecargas)
- public Pessoa(...):
    - **Função:** Cria um objeto Pessoa.
    - **Ação:** Valida nomes, data (usando ValidaData) e, nas versões completas, valida o CPF, Peso e Altura.
    - **Efeito Colateral:** Incrementa `numPessoasCriadas`.

### VALIDAÇÃO INTERNA
- private static boolean isNomeValido(String texto):
    - **Função:** Checa se o nome/sobrenome contém apenas letras e espaços.

### GETTERS (Obter Dados)
- public String getNome(), getSobreNome(), getPeso(), etc.:
    - **Função:** Retorna o valor do respectivo atributo.
- public int getIdade():
    - **Função:** Calcula e retorna a idade atual da pessoa com base na data de nascimento.

### SETTERS (Alterar Dados)
- public void setNome(), setNumCPF(), setPeso(), etc.:
    - **Função:** Altera o valor do atributo, aplicando a validação de integridade correspondente antes da atribuição (ex: verifica se o CPF é válido ou se o peso é positivo).

### APRESENTAÇÃO
- public String toString():
    - **Função:** Retorna uma string formatada e detalhada de todas as informações da pessoa (Nome Completo, CPF formatado, Idade, etc.) para fácil impressão.

# CLASSE: ValidaCPF

DESCRIÇÃO:
Classe utilitária em Java, dedicada a verificar a validade e formatar o Cadastro de Pessoa Física (CPF). Não armazena dados de instância, pois todos os seus métodos são estáticos.

## MÉTODOS CHAVE (Funcionalidades)

### 1. normalizaCPF (Método Privado)
- private static String normalizaCPF(String cpf):
    - **Função:** Limpeza e Checagem de Formato Básico.
    - **Ação:** Usa Regex (Expressão Regular) para aceitar CPFs nos formatos:
        1.  Apenas 11 dígitos (`12345678901`).
        2.  Formatado com pontos e traço/barra (`123.456.789-01` ou `/`).
    - **Retorno:** Se o formato for inválido, retorna `null`. Se válido, retorna apenas os 11 dígitos numéricos limpos.

### 2. isCPF (Validação Principal)
- public static boolean isCPF(String cpf):
    - **Função:** Verifica a validade estrutural e matemática do CPF.
    - **Ação:**
        1.  Chama `normalizaCPF` para obter os 11 dígitos.
        2.  Verifica se não é uma sequência de dígitos iguais (ex: `111.111.111-11`).
        3.  Aplica o algoritmo matemático de cálculo dos dois dígitos verificadores (dv1 e dv2).
        4.  Compara os dígitos calculados com os dígitos originais.
    - **Retorno:** `True` se o CPF for matemática e formalmente válido, caso contrário, `False`.

### 3. imprimeCPF (Formatação)
- public static String imprimeCPF(String cpf):
    - **Função:** Formata o CPF no padrão brasileiro (XXX.XXX.XXX-XX).
    - **Ação:** Chama `normalizaCPF` e aplica as substrings e pontuações.
    - **Exceção:** Lança exceção se o CPF de entrada não estiver em um dos formatos válidos aceitos por `normalizaCPF`.
    - **Retorno:** A string do CPF formatada.

### 4. toLong (Conversão)
- public static long toLong(String cpf):
    - **Função:** Converte o CPF (String) para o tipo primitivo `long`.
    - **Ação:**
        1.  Primeiro, usa `isCPF` para garantir que o número é válido.
        2.  Usa `normalizaCPF` para obter apenas os 11 dígitos.
        3.  Converte a string numérica limpa para `long`.
    - **Exceção:** Lança exceção se o CPF for considerado inválido por `isCPF`.
    - **Retorno:** O CPF como um valor numérico `long`.

# CLASSE: ValidaData

DESCRIÇÃO:
Classe utilitária em Java para validar a integridade de datas (dia, mês e ano). Garante que a data seja uma data real (ex: checa ano bissexto para Fevereiro, dias máximos por mês) e que não esteja em um futuro distante ou muito passado. Suporta entradas em formato numérico e nomes de meses por extenso.

## ENUM INTERNA: Meses

- Enum Meses: Define constantes para cada mês (JANEIRO=1 a DEZEMBRO=12).
- buscaPorNomeInterna(String): Tenta encontrar o mês na enumeração a partir de um nome (string) fornecido.
- isNomeValido(String): Retorna True se a string fornecida corresponder a um nome de mês válido.
- toInt(String): Converte o nome de um mês (ex: "JANEIRO") para seu número correspondente (ex: 1). Retorna -1 se não encontrar.

## MÉTODOS CHAVE (Funcionalidades)

### 1. toInt
- public static int toInt(String valor):
    - **Função:** Conversão Segura.
    - **Ação:** Tenta converter qualquer string numérica (Dia, Mês ou Ano) para um inteiro.
    - **Retorno:** O valor inteiro ou `-1` se a string não for numérica.

### 2. isAnoBissexto (Método Privado)
- private static boolean isAnoBissexto(int ano):
    - **Função:** Lógica de Bissexto.
    - **Ação:** Aplica a regra matemática para determinar se o ano é bissexto.

### 3. isDia / isMes / isAno (Checagens Individuais)
- public static boolean isDia(int/String): Verifica se o dia está entre 1 e 31.
- public static boolean isMes(int/String): Verifica se o mês está entre 1 e 12 (aceita string numérica ou por extenso).
- public static boolean isAno(int/String):
    - **Função:** Verifica se o ano está dentro de um intervalo razoável (aproximadamente 120 anos antes do ano atual e não no futuro).

### 4. isDataValida (Validação Completa)
- public static boolean isDataValida(int/String dia, int/String mes, int/String ano):
    - **Função:** Valida se a combinação de Dia, Mês e Ano forma uma data real e permitida.
    - **Ação:**
        1.  Verifica Dia, Mês e Ano individualmente.
        2.  Impede datas no futuro (não permite ano, mês ou dia maior que a data atual).
        3.  Aplica a regra de dias vs. mês (ex: não permite dia 31 em Abril).
        4.  Verifica se Fevereiro é 29 ou 28 dias dependendo de `isAnoBissexto`.
    - **Retorno:** `True` se a data for válida em todos os aspectos, caso contrário, `False`.

# CLASSES: Homem e Mulher

DESCRIÇÃO:
'Homem' e 'Mulher' são classes filhas (subclasses) que HERDAM todas as propriedades (atributos) e funcionalidades (métodos) da classe base 'Pessoa'. O propósito principal dessas classes é especializar a entidade por gênero e fornecer uma apresentação específica no método 'toString()'.

## MÉTODOS CHAVE (Funcionalidades)

### CONSTRUTORES (Quatro Sobrecargas em Ambas)
- public Homem(...) / public Mulher(...):
    - **Função:** Cria um objeto do gênero específico.
    - **Ação:** Utiliza a palavra-chave `super(...)` para chamar o construtor correspondente na classe mãe (`Pessoa`). Isso garante que todas as validações de Nome, Data, CPF, Peso e Altura sejam realizadas pela classe base.

### HERANÇA DE FUNCIONALIDADES
- Getters, Setters, getIdade(), etc.:
    - **Função:** Todas as funcionalidades básicas são **herdadas** de `Pessoa` e funcionam de forma idêntica (por exemplo, `getIdade()` é o mesmo para ambos).

### APRESENTAÇÃO (Método toString() Sobrescrito)
- public String toString():
    - **Função:** Apresentação formatada dos dados com a identificação de gênero.
    - **Ação:**
        1. Chama `super.toString()` para obter todas as informações básicas da classe `Pessoa`.
        2. **Adiciona duas linhas** específicas: a identificação do Gênero (Homem ou Mulher) e a Idade calculada.
    - **Retorno:** A string completa com as informações pessoais, Gênero e Idade.

# CLASSE: P1nX

DESCRIÇÃO:
Classe principal (main) do programa. Sua função é gerenciar a interface de entrada/saída (I/O) do usuário, criando objetos Homem ou Mulher com base em dados fornecidos por linha de comando OU por entrada interativa, armazená-los em um array e, no final, fazer uma contagem dos objetos criados.

## MÉTODOS CHAVE (Funcionalidades)

### 1. main
- public static void main(String[] args):
    - **Função:** Ponto de entrada do programa.
    - **Ação:**
        1. Valida o número de argumentos da linha de comando (6 ou 9).
        2. Tenta criar uma `Pessoa` a partir dos argumentos da linha de comando.
        3. Inicia a seção de leitura **interativa** (Scanner).
        4. Pergunta o tamanho do array e chama `lerEArmazenarDados` em um loop.
        5. Exibe os detalhes de todas as pessoas criadas e finaliza com a contagem total.

### 2. criarPessoa
- private static Pessoa criarPessoa(String[] dados):
    - **Função:** Fábrica de Objetos.
    - **Ação:** Recebe um array de strings e tenta criar um objeto `Homem` ou `Mulher`.
    - **Validação:** Checa o gênero ('H' ou 'M') e usa os construtores das subclasses. É o ponto onde as exceções de validação (`ValidaData`, `ValidaCPF`, etc.) são capturadas e exibidas.
    - **Retorno:** O objeto `Pessoa` criado, ou `null` em caso de erro de validação/formato.

### 3. lerLinhaOuParar
- private static String lerLinhaOuParar(Scanner scanner, String prompt):
    - **Função:** Gerenciamento de I/O Interativa.
    - **Ação:** Pede a entrada do usuário.
    - **Controle de Parada:** Lança a exceção de controle `StopReadingException` se o usuário pressionar **ENTER** em uma linha vazia, interrompendo o loop de leitura.
    - **Retorno:** A string digitada.

### 4. lerEArmazenarDados
- private static void lerEArmazenarDados(Scanner scanner, Pessoa[] arrayPessoas, int tamanhoMaximo):
    - **Função:** Loop de Leitura.
    - **Ação:** Itera até o `tamanhoMaximo` do array. Dentro do loop, usa `lerLinhaOuParar` para coletar dados interativamente (Nome, CPF, etc.).
    - **Tratamento de Erros:** Contém um loop `while(true)` e blocos `try-catch` que permitem ao usuário **corrigir a entrada** de dados inválidos sem perder o progresso.
    - **Parada:** Retorna imediatamente se uma `StopReadingException` for capturada.

### 5. contarObjetosPorGenero
- private static void contarObjetosPorGenero(Pessoa[] arrayPessoas):
    - **Função:** Contagem e Relatório.
    - **Ação:** Percorre o array de Pessoas e usa o operador `instanceof` para contar quantos objetos são instâncias de `Homem` e quantos são instâncias de `Mulher`.