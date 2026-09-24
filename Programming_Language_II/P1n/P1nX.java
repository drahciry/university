// Importacoes uteis ao programa
import java.util.Scanner;

/**
 * Classe do programa principal do projeto.
 */
public class P1nX {
    /**
     * Metodo main, entry point do programa, onde possui toda logica.
     * 
     * @param args ({@code String[]}): Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        // Se o tamanho da entrada for diferente de 6 e 9,
        // significa que a entrada e invalida. Exibe help
        // de como iniciar o programa e encerra.
        if (args.length != 6 && args.length != 9) {
            System.out.println("Quantidade de argumentos invalida. Veja como iniciar o programa corretamente:\n");
            displayHelp();
            return;
        }
        // Utiliza o metodo trataExcecaoCLI() para tentar criar
        // uma pessoa ou, em caso de erro, trata-las e exibir
        // uma mensagem indicando o erro junto com o help.
        Pessoa pessoaCriada = trataExcecaoCLI(args);
        // Se a referencia do objeto for nula, significa que
        // houve uma excecao lancada. Para a primeira pessoa
        // criada (pela CLI), o programa sera abortado.
        if (pessoaCriada == null) {
            System.out.println("\nPrograma encerrado devido a erro nos argumentos.");
            return;
        }
        // Se a referencia nao for nula, exibe mensagem
        // de sucesso e exibe as informacoes da pessoa.
        System.out.println("Pessoa cadastrada com sucesso!\n");
        System.out.println(pessoaCriada);
        // Instancia da classe Scanner para realizar leitura do teclado
        Scanner scanner = new Scanner(System.in);
        // Utiliza o metodo quantidadePessoas() para capturar
        // quantas pessoas deseja cadastrar. Tratamento de 
        // excecao ocorre dentro do metodo.
        int quantidade = quantidadePessoas(scanner);
        // Se a quantidade for 0, o programa sera abortado.
        // Uma mensagem sera exibida informando que o programa se encerrou.
        if (quantidade == 0) {
            System.out.println("Nenhuma pessoa mais sera criada.\n");
            System.out.println("\nPrograma encerrado!");
            return;
        }
        // Cria um array para armazenar objetos da classe Pessoa
        // com a quantidade passada pelo usuario.
        Pessoa[] pessoas = new Pessoa[quantidade + 1];
	// Adiciona pessoa criada via CLI ao array.
	pessoas[0] = pessoaCriada;
        // Utiliza o metodo capturaEntrada() para capturar informacoes
        // da pessoa, criar a pessoa e armazenar esta pessoa no array.
        capturaEntrada(quantidade, pessoas, scanner);
        // Apos realizar a captura das entradas e instanciar as pessoas, exibe as
        // informacoes de todas elas e a quantidade de homens e mulheres cadastradas.
	System.out.println("\n---------- PESSOAS CADASTRADAS ----------");
        exibeInfos(pessoas);
        // Exibe mensagem de programa encerrado.
        System.out.println("\nPrograma encerrado!");
        // Fecha o scanner para nao ocorrer problema de buffer.
        scanner.close();
        // Programa encerrado.
    }

    /**
     * Metodo que recebe um array de {@code Pessoa} como parametro e
     * exibe as informacoes e quantos homens e mulheres foram criadas.
     * 
     * @param pessoas ({@code Pessoa[]}): Array de {@code Pessoa}.
     */
    private static void exibeInfos(Pessoa[] pessoas) {
        // Usa o getter estatico da classe Pessoa para retornar
        // quantos objetos da classe Pessoa foram instanciados.
        int numPessoasCriadas = Pessoa.getNumPessoas();
        // Variaveis para armazenar a quantidade homens e mulheres criadas.
        int homensCriados = 0;
        int mulheresCriadas = 0;
        // Itera sobre o array, exibindo as informacoes de todos.
        for (int i = 0; i < numPessoasCriadas; i++) {
            // Exibe informacoes
            System.out.println("\n" + pessoas[i]);
            // Verifica se o objeto eh uma instancia de Homem ou Mulher
            if (pessoas[i] instanceof Homem)
                // Incrementa 1.
                homensCriados++;
            else if (pessoas[i] instanceof Mulher)
                // Incrementa 1.
                mulheresCriadas++;
        }
        // Exibe a quantidade de homens e mulheres criadas.
        System.out.println("\nQuantidade de homens cadastrados: " + homensCriados);
        System.out.println("Quantidade de mulheres cadastradas: " + mulheresCriadas);
    }

    /**
     * Metodo que captura a quantidade de pessoas que o
     * usuario deseja cadastrar.
     * 
     * @param scanner ({@code Scanner}): Objeto scanner para
     * realizar leitura de dados do teclado.
     * 
     * @return {@code int}: Retorna um int que eh a quantidade a ser criada.
     */
    private static int quantidadePessoas(Scanner scanner) {
        // Declara variavel que armazenara a quantidade
        int quantidade;
        // while (true) para que a entrada seja valida.
        // Enquanto for invalido, continua pedindo entrada.
        while (true) {
            // Se a entrada for 0, o programa sera encerrado.
            System.out.print("\nDeseja criar mais quantas pessoas? (Digite 0 para encerrar) R: ");
            // Tratamento de excecao para caso o usuario digite
            // algo que nao seja um numero inteiro.
            try {
                // Armazena a entrada em uma String e realiza o parse logo em seguida.
                String input = scanner.nextLine();
                quantidade = Integer.parseInt(input);
                // Se a quantidade for maior ou igual a 0, a entrada eh valida.
                // Logo, o loop quebrara.
                if (quantidade >= 0)
                    break;
                // Exibe mensagem de erro. Se a entrada for valida, mensagem nao sera mostrada.
                System.out.println("Erro: numero nao pode ser negativo. Tente novamente");
            // Caso a entrada seja invalida, captura excecao e exibe mensagem de erro.
            } catch (NumberFormatException e) {
                System.out.println("Erro: entrada invalida. Por favor, digite um numero inteiro e positivo.");
            }
        }
        // Retorna a quantidade
        return quantidade;
    }
    
    /**
     * Metodo que captura a entrada do usuario de cada informacao.
     * 
     * @param quantidade ({@code int}): Quantidade de pessoas a serem criadas.
     * @param pessoas ({@code Pessoa[]}): Array onde serao armazenadas as pessoas.
     * @param scanner ({@code Scanner}): Instancia de Scanner para capturar entrada.
     */
    private static void capturaEntrada(int quantidade, Pessoa[] pessoas, Scanner scanner) {
        // Utiliza mainLoop para que o loop principal seja quebrado quando o usuario quiser.
        mainLoop:
        for (int i = 1; i <= quantidade; i++) {
            // Exibe mensagem informativa para auxiliar usuario.
            System.out.println("\nCadastrando pessoa " + (i) + " de " + quantidade + ".");
            System.out.println("(Deixe o 'genero' em branco e pressione ENTER para parar)");
            // Utiliza while (true) para que so pare de capturar entrada
            // quando todas as entradas forem validas.
            while (true) {
                // Cria array para armazenar parametros de criacao da Pessoa.
                String[] parameters;
                // Inicia captura dos argumentos.
                System.out.print("\nInsira o genero: ");
                String genero = scanner.nextLine();
                // Se o genero for uma entrada vazia, o loop sera encerrado.
                if (genero.isEmpty()) {
                    System.out.println("Entrada de dados interrompida pelo usuario.\n");
                    // Quebra loop principal.
                    break mainLoop;
                }
                // Senao, continua a captura das entradas.
                System.out.print("Insira o nome: ");
                String nome = scanner.nextLine();
                System.out.print("Insira o sobrenome: ");
                String sobreNome = scanner.nextLine();
                System.out.print("Insira o dia de nascimento: ");
                String diaNasc = scanner.nextLine();
                System.out.print("Insira o mes de nascimento (ex: 5 ou 'Maio'): ");
                String mesNasc = scanner.nextLine();
                System.out.print("Insira o ano de nascimento: ");
                String anoNasc = scanner.nextLine();
                // Se a entrada do CPF for vazia, CPF, peso e altura nao serao utilizados.
                System.out.print("Insira o CPF (opcional, pressione ENTER para pular): ");
                String numCPF = scanner.nextLine();
                // Se CPF nao for uma entrada vazia, cria um array de 9 posicoes para
                // armazenar os argumentos para o construtor mais especializado.
                if (!numCPF.isEmpty()) {
                    // Continua captura das entradas.
                    parameters = new String[9];
                    System.out.print("Insira o peso: ");
                    String peso = scanner.nextLine();
                    System.out.print("Insira a altura: ");
                    String altura = scanner.nextLine();
                    // Armazena todas as entradas no array de argumentos.
                    parameters[0] = genero;
                    parameters[1] = nome;
                    parameters[2] = sobreNome;
                    parameters[3] = diaNasc;
                    parameters[4] = mesNasc;
                    parameters[5] = anoNasc;
                    parameters[6] = numCPF;
                    parameters[7] = peso;
                    parameters[8] = altura;
                // Senao, apenas os argumentos do construtor base serao utilizdos.
                } else {
                    // Armazena todas as entradas no array de argumentos.
                    parameters = new String[6];
                    parameters[0] = genero;
                    parameters[1] = nome;
                    parameters[2] = sobreNome;
                    parameters[3] = diaNasc;
                    parameters[4] = mesNasc;
                    parameters[5] = anoNasc;
                }
                // Utiliza metodo trataExcecaoLoop que criara e retornara a referencia 
                // do objeto criado. Tratamento de excecoes ocorre no metodo;
                Pessoa pessoaCriada = trataExcecaoLoop(parameters);
                // Se a referencia for nula, houve algum erro e a captura
                // precisara ocorrer novamente, de forma correta.
                if (pessoaCriada == null) {
                    System.out.println("Por favor, insira os dados novamente.");
                    // Pula para a proxima iteracao.
                    continue;
                }
                // Senao, exibe mensagem de sucesso e armazena no array.
                System.out.println("\n" + pessoaCriada.getNome() + " cadastrado(a) com sucesso!");
                pessoas[i] = pessoaCriada;
                // Quebra loop secundario.
                break;
            }
        }
    }

    /**
     * Metodo que retorna uma pessoa criada ou trata as excecoes. Seas excecoes forem disparadas,
     * todas serao tratadas, porem a pessoa nao sera criada, entao sera retornado null.
     * Este metodo sera usado na criacao de uma pessoa com passagem de parametros no CLI.
     * 
     * @param parameters ({@code String[]}): Array de String contendo todos os parametros de criacao de {@code Pessoa}.
     * 
     * @return {@code Pessoa}: Retorna objeto da classe {@code Pessoa} instanciado.
     */
    private static Pessoa trataExcecaoCLI(String[] parameters) {
        // Cria uma instancia com referencia para nulo.
        Pessoa pessoaCriada = null;
        // Tenta criar objeto.
        try {
            // Utiliza o metodo createPessoa(), que recebe os parametros e cria o objeto.
            pessoaCriada = createPessoa(parameters);
        // Se o nome ou o sobrenome for invalido, trata excecao e exibe mensagem.
        } catch (InvalidNameException | InvalidSurnameException e1) {
            System.out.println("\nErro de validacao: Nome ou Sobrenome invalido.");
            System.out.println("Detalhe: " + e1.getMessage());
            // Exibe ajuda.
            displayHelp();
        // Se a data for invalida, trata excecao e exibe mensagem.
        } catch (InvalidDataException e2) {
            System.out.println("\nErro de validacao: Data de Nascimento invalida.");
            System.out.println("Detalhe: " + e2.getMessage());
            // Exibe ajuda.
            displayHelp();
        // Se o CPF for invalido, trata excecao e exibe mensagem.
        } catch (InvalidCpfException e3) {
            System.out.println("\nErro de validacao: CPF invalido.");
            System.out.println("Detalhe: " + e3.getMessage());
            // Exibe ajuda.
            displayHelp();
        // Se o peso for invalido, trata excecao e exibe mensagem.
        } catch (InvalidWeightException e4) {
            System.out.println("\nErro de validacao: Peso invalido.");
            System.out.println("Detalhe: " + e4.getMessage());
            // Exibe ajuda.
            displayHelp();
        // Se a altura for invalida, trata excecao e exibe mensagem.
        } catch (InvalidHeightException e5) {
            System.out.println("\nErro de validacao: Altura invalida.");
            System.out.println("Detalhe: " + e5.getMessage());
            // Exibe ajuda.
            displayHelp();
        // Se o peso ou a altura nao forem valores numericos, trata excecao e exibe mensagem.
        } catch (NumberFormatException e) {
            System.out.println("\nErro de formato: Peso ou Altura nao sao numeros validos.");
            System.out.println("Detalhe: \"" + e.getMessage() + "\" nao pode ser convertido.");
            // Exibe ajuda.
            displayHelp();
        // Se o genero for invalido, trata excecao e exibe mensagem.
        } catch (IllegalArgumentException e6) {
            System.out.println("\nErro de validacao: Genero invalido.");
            // Exibe ajuda para genero.
            displayHelpGender(parameters[0]);
        // Para qualquer outra excecao inesperada, trata e exibe qual o erro.
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro inesperado:");
            e.printStackTrace();
            // Exibe ajuda.
            displayHelp();
        }
        // Retorna a referencia da instancia de Pessoa.
        return pessoaCriada;
    }

    /**
     * Metodo que retorna uma pessoa criada ou trata as excecoes. Seas excecoes forem disparadas,
     * todas serao tratadas, porem a pessoa nao sera criada, entao sera retornado null.
     * Este metodo sera usado no loop onde mais pessoas serao criadas.
     * 
     * @param parameters ({@code String[]}): Array de String contendo todos os parametros de criacao de {@code Pessoa}.
     * 
     * @return {@code Pessoa}: Retorna objeto da classe {@code Pessoa} instanciado.
     */
    private static Pessoa trataExcecaoLoop(String[] parameters) {
        // Cria uma instancia com referencia para nulo.
        Pessoa pessoaCriada = null;
        // Tenta criar objeto.
        try {
            // Utiliza o metodo createPessoa(), que recebe os parametros e cria o objeto.
            pessoaCriada = createPessoa(parameters);
        // Se o nome ou o sobrenome for invalido, trata excecao e exibe mensagem.
        } catch (InvalidNameException | InvalidSurnameException e1) {
            System.out.println("\nErro de validacao: Nome ou Sobrenome invalido.");
            System.out.println("Detalhe: " + e1.getMessage());
        // Se a data for invalida, trata excecao e exibe mensagem.
        } catch (InvalidDataException e2) {
            System.out.println("\nErro de validacao: Data de Nascimento invalida.");
            System.out.println("Detalhe: " + e2.getMessage());
        // Se o CPF for invalido, trata excecao e exibe mensagem.
        } catch (InvalidCpfException e3) {
            System.out.println("\nErro de validacao: CPF invalido.");
            System.out.println("Detalhe: " + e3.getMessage());
        // Se o peso for invalido, trata excecao e exibe mensagem.
        } catch (InvalidWeightException e4) {
            System.out.println("\nErro de validacao: Peso invalido.");
            System.out.println("Detalhe: " + e4.getMessage());
        // Se a altura for invalida, trata excecao e exibe mensagem.
        } catch (InvalidHeightException e5) {
            System.out.println("\nErro de validacao: Altura invalida.");
            System.out.println("Detalhe: " + e5.getMessage());
        // Se o peso ou a altura nao forem valores numericos, trata excecao e exibe mensagem.
        } catch (NumberFormatException e) {
            System.out.println("\nErro de formato: Peso ou Altura nao sao numeros validos.");
            System.out.println("Detalhe: \"" + e.getMessage() + "\" nao pode ser convertido.");
        // Se o genero for invalido, trata excecao e exibe mensagem.
        } catch (IllegalArgumentException e6) {
            System.out.println("\nErro de validacao: Genero invalido.");
            displayHelpGender(parameters[0]);
        // Para qualquer outra excecao inesperada, trata e exibe qual o erro.
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro inesperado:");
            e.printStackTrace();
        }
        // Retorna a referencia da instancia de Pessoa.
        return pessoaCriada;
    }

    /**
     * Metodo que cria uma pessoa baseado no sexo inserido.
     * 
     * @param parameters ({@code String[]}): Array de String com argumentos para criar {@code Pessoa}.
     * 
     * @return {@code Pessoa}: Retorna objeto instanciado da classe {@code Pessoa}.
     * 
     * @throws InvalidNameException Lanca excecao se o nome possuir caracteres invalidos ou estiver vazio.
     * @throws InvalidSurnameException Lanca excecao se o sobrenome possuir caracteres invalidos ou estiver vazio.
     * @throws InvalidDataException Lanca excecao se a data for invalida, como um dia negativo, um mes que nao existe
     * ou um ano que nao esta no intervalo determinado (120 anos).
     * @throws InvalidCpfException Lanca excecao se o CPF for invalido, podendo ser o formato ou os digitos verificadores.
     * @throws InvalidWeightException Lanca excecao se o peso for negativo.
     * @throws InvalidHeightException Lanca excecao se a altura for menor que 0.40m.
     * @throws NumberFormatException Lanca excecao se o peso ou a altura nao for um float.
     * @throws IllegalArgumentException Lanca excecao se o genero for invalido (help sera exibido para ajudar).
     */
    private static Pessoa createPessoa(String[] parameters)
        throws InvalidNameException, InvalidSurnameException, InvalidDataException, 
               InvalidCpfException, InvalidWeightException, InvalidHeightException,
               NumberFormatException, IllegalArgumentException {
        // Armazena os argumentos em instancias de String.
        String genero = parameters[0].toLowerCase(); // Utiliza lower case para normalizar.
        String nome = parameters[1];
        String sobreNome = parameters[2];
        String diaNasc = parameters[3];
        String mesNasc = parameters[4];
        String anoNasc = parameters[5];
        // Se o tamanho da entrada for 9, armazena mais 3 argumentos
        // e utiliza o construtor especializado da classe Pessoa.
        if (parameters.length == 9) {
            String numCPF = parameters[6];
            String pesoStr = parameters[7];
            String alturaStr = parameters[8];
            // Converte de String para float usando parseFloat.
            float peso = Float.parseFloat(pesoStr);
            float altura = Float.parseFloat(alturaStr);
            // Se for homem, retorna uma instancia de Homem.
            if (genero.equals("masculino") || genero.equals("m"))
                return (new Homem(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura));
            // Se for mulher, retorna uma instancia de Mulher.
            else if (genero.equals("feminino") || genero.equals("f"))
                return (new Mulher(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura));
            // Se for um genero invalido, lanca uma excecao.
            else
                throw new IllegalArgumentException("Genero \"" + parameters[0] + "\" invalido.");
        // Senão, utiliza o construtor base.
        } else {
            // Se for homem, retorna uma instancia de Homem.
            if (genero.equals("masculino") || genero.equals("m"))
                return (new Homem(nome, sobreNome, diaNasc, mesNasc, anoNasc));
            // Se for mulher, retorna uma instancia de Mulher.
            else if (genero.equals("feminino") || genero.equals("f"))
                return (new Mulher(nome, sobreNome, diaNasc, mesNasc, anoNasc));
            // Se for um genero invalido, lanca uma excecao.
            else
                throw new IllegalArgumentException("Genero \"" + parameters[0] + "\" invalido.");
        }
    }

    /**
     * Metodo que exibe mensagem de ajuda apos iniciar com argumentos incorretos.
     */
    private static void displayHelp() {
        // Exibe mensagem de help.
        System.out.println(
            "\njava P1nX <genero> <nome> <sobrenome> <dia> <mes> <ano> <CPF> <peso> <altura>\n" +
            "\nVeja os exemplos abaixo:\n" +
            "\nExemplo 1:\njava P1nX Masculino Richard Albino 4 1 2005\n" +
            "\nExemplo 2:\njava P1nX masculino Richard Albino 04 01 2005 000.000.000-00 95 1.73\n" +
            "\nExemplo 3:\njava P1nX M Richard Albino 4 janeiro 2005 000.000.000/00 95.0 1.73\n" +
            "\nExemplo 4:\njava P1nX m Richard Albino 4 1 2005 00000000000 95 1.73\n" +
            "\nPerceba que os argumentos CPF, peso e altura nao sao obrigatorios.\n" +
            "Para preencher o genero, voce pode usar:\n" +
            "\nPara homens: Masculino, M." +
            "\nPara mulheres: Feminino, F.\n" +
            "\nO programa sera encerrado."
        );
    }

    /**
     * Metodo estatico que exibe mensagem de ajuda para preencher genero.
     * 
     * @param genero ({@code String}): Genero da pessoa criada.
     */
    private static void displayHelpGender(String genero) {
        // Exibe mensagem de help para genero.
        System.out.println(
            "O genero " + genero + " esta incorreto.\n" +
            "Para preencher o genero, voce pode usar:\n" +
            "\nPara homens: Masculino, M." +
            "\nPara mulheres: Feminino, F.\n" +
            "\nO programa sera encerrado."
        );
    }
}
