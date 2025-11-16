import java.util.Scanner;
import java.util.Arrays;

public class P1nX {

    // Excecao de controle para sinalizar a interrupcao do usuario
    private static class StopReadingException extends RuntimeException {
        public StopReadingException(String message) {
            super(message);
        }
    }

    // Sintaxe esperada para o help
    private static final String HELP = 
        "\nSintaxe correta:\n" +
        "java P1nX <genero> <nome> <sobre> <dia> <mes> <ano> <CPF> <peso> <altura>\n" +
        "OU (Minimo): [Genero, Nome, Sobrenome, Dia, Mes, Ano]\n" +
        "O genero deve ser especificado como 'H' (Homem) ou 'M' (Mulher).";

    // Main
    public static void main(String[] args) {
        // Validacao de argumentos na linha de comando

        if (args.length != 6 && args.length != 9) { // 6 args (1 genero + 5 min) 9 args (1 genero e 8 completo)
            System.err.println("ERRO: Numero incorreto de parametros de linha de comando.");
            System.out.println(HELP); // help
            return; // Encerra o programa
        }
        
        Pessoa pessoaLinhaComando = criarPessoa(args);

        if (pessoaLinhaComando == null) {
            System.out.println(HELP);
            return; // Encerra o programa
        }
        
        System.out.println("\n--- PESSOA CRIADA POR LINHA DE COMANDO ---");
        System.out.println(pessoaLinhaComando.toString()); 

        // Leitura interativa e criacao do array

        Pessoa[] arrayPessoas = null;
        Scanner scanner = new Scanner(System.in);
        int tamanhoArray = 0;

        System.out.println("\n--- ETAPA DE ENTRADA INTERATIVA ---");
        System.out.print("Quantas pessoas adicionais voce quer criar? ");
        
        try {
            tamanhoArray = scanner.nextInt();
            scanner.nextLine(); // remove o \n
            // Array negativo
            if (tamanhoArray < 0) {
                 System.err.println("ERRO: O tamanho do array nao pode ser negativo. Finalizando...");
                 return;  // Encerra o programa
            }
            // Erro de tipo
        } catch (java.util.InputMismatchException e) {
            System.err.println("ERRO: A entrada para o tamanho do array deve ser um numero inteiro. Finalizando...");
            return; // Encerra o programa
        }
        // Criacao do array
        if (tamanhoArray > 0) {
            arrayPessoas = new Pessoa[tamanhoArray];
            lerEArmazenarDados(scanner, arrayPessoas, tamanhoArray);
        }
        
        // Exibicao das Pessoas no array
        
        if (arrayPessoas != null && Pessoa.numPessoas() > 1) { 
            int elementosAdicionados = Pessoa.numPessoas() - 1; 

            System.out.println("\n--- PESSOAS DO ARRAY ARMAZENADAS (" + elementosAdicionados + " de " + tamanhoArray + ") ---");
            
            for (int i = 0; i < arrayPessoas.length; i++) {
                if (arrayPessoas[i] != null) {
                    System.out.println("\n--- Posicao " + i + " ---");
                    System.out.println(arrayPessoas[i].toString());
                }
            }
        } 


        // Contagem de Generos
        System.out.println("\n--- CONTAGEM FINAL DE PESSOAS ---");
        
        contarObjetosPorGenero(arrayPessoas);
        
        System.out.println("Total Geral de Pessoas Criadas (Incluindo Linha de Comando): " + Pessoa.numPessoas());
        
        System.out.println("\nPrograma finalizado.");
        scanner.close();
    }
    
    // Metodo auxiliar para criar Pessoa a partir de um array de Strings (usado por CLI e Interativo)
    private static Pessoa criarPessoa(String[] dados) {
        // Assume que o length ja foi checado para 6 ou 9
        String genero = dados[0];
        String nome = dados[1];
        String sobreNome = dados[2];
        String dia = dados[3];
        String mes = dados[4];
        String ano = dados[5];

        Pessoa novaPessoa = null;

        try {
            if (dados.length == 9) {
                // MODO COMPLETO (9 args)
                String cpf = dados[6];
                String peso = dados[7];
                String altura = dados[8];
                
                float pesoFloat = Float.parseFloat(peso);
                float alturaFloat = Float.parseFloat(altura); 
                
                // Criacao do objeto usando o construtor completo com String/float
                if (genero.equalsIgnoreCase("M")) {
                    novaPessoa = new Mulher(nome, sobreNome, dia, mes, ano, cpf, pesoFloat, alturaFloat);
                } else if (genero.equalsIgnoreCase("H")) {
                    novaPessoa = new Homem(nome, sobreNome, dia, mes, ano, cpf, pesoFloat, alturaFloat);
                } else {
                    throw new IllegalArgumentException("Genero invalido. Use 'H' ou 'M'.");
                }
                
            } else if (dados.length == 6) {
                // MODO MINIMO (6 args)
                if (genero.equalsIgnoreCase("M")) {
                    novaPessoa = new Mulher(nome, sobreNome, dia, mes, ano);
                } else if (genero.equalsIgnoreCase("H")) {
                    novaPessoa = new Homem(nome, sobreNome, dia, mes, ano);
                } else {
                    throw new IllegalArgumentException("Genero invalido. Use 'H' ou 'M'.");
                }
            }
            
        } catch (NumberFormatException e) {
            System.err.println("ERRO de entrada: Parametro numerico invalido (Peso/Altura ou Data). (Detalhe: " + e.getMessage() + ")");
            return null; 
        } catch (IllegalArgumentException e) {
            // Captura erros lançados pelos validadores (ValidaCPF, ValidaData, isNomeValido, etc.)
            System.err.println("ERRO de validacao: " + e.getMessage());
            return null;
        }

        return novaPessoa; // Retorna pessoa criada
    }
    
    // Ler a linha e checar a parada por ENTER
    private static String lerLinhaOuParar(Scanner scanner, String prompt) throws StopReadingException {
        System.out.print(prompt); // Exibe a mensagem
        String input = scanner.nextLine().trim();
        
        // Se a linha estiver vazia, lança a excecao para interromper o loop
        if (input.isEmpty()) {
            throw new StopReadingException("Interrupcao do usuario via ENTER.");
        }
        return input; // retorna a esntrada caso nao seja um ENTER
    }

    // Loop de Leitura (Para com ENTER)
    private static void lerEArmazenarDados(Scanner scanner, Pessoa[] arrayPessoas, int tamanhoMaximo) {
        System.out.println("\n--- INICIANDO LEITURA INTERATIVA --- (Pressione ENTER em qualquer prompt para parar)");

        for (int i = 0; i < tamanhoMaximo; i++) {
            System.out.println("\n-> Objeto " + (i + 1) + " de " + tamanhoMaximo + ":");
            String[] dados = new String[9];
            
            while (true) { // Loop para correcao de entrada
                try {
                    // o metodo auxiliar que verifica condicao de parada
                    dados[0] = lerLinhaOuParar(scanner, "Genero (H/M): "); 
                    dados[1] = lerLinhaOuParar(scanner, "Nome: ");
                    dados[2] = lerLinhaOuParar(scanner, "Sobrenome: ");
                    dados[3] = lerLinhaOuParar(scanner, "Dia de nascimento: ");
                    dados[4] = lerLinhaOuParar(scanner, "Mes de nascimento (Ex: 01 ou janeiro): ");
                    dados[5] = lerLinhaOuParar(scanner, "Ano de nascimento: ");
                    
                    // Escolha do construtor (6 ou 9 argumentos) ---
                    
                    System.out.print("Deseja inserir CPF, Peso e Altura (S/N)? ");
                    String respostaCompleta = scanner.nextLine().trim();
                    
                    if (respostaCompleta.equalsIgnoreCase("N")) {
                         // MODO MÍNIMO (6 argumentos)
                         Pessoa novaPessoa = criarPessoa(Arrays.copyOfRange(dados, 0, 6)); // Copia apenas os 6 primeiros
                         
                         if (novaPessoa != null) {
                            arrayPessoas[i] = novaPessoa;
                            System.out.println("Pessoa criada e armazenada com sucesso (MINIMO).");
                            break; 
                         }
                    } else if (respostaCompleta.equalsIgnoreCase("S")) {
                        // MODO COMPLETO (9 argumentos)
                        // Leitura dos 3 campos adicionais (tambem com checagem de parada)
                        dados[6] = lerLinhaOuParar(scanner, "CPF (Ex: 123.456.789-00): ");
                        dados[7] = lerLinhaOuParar(scanner, "Peso (Ex: 70.0): ");
                        dados[8] = lerLinhaOuParar(scanner, "Altura (Ex: 1.65): ");
                        
                        Pessoa novaPessoa = criarPessoa(dados); // Chamada com 9 argumentos
                        
                        if (novaPessoa != null) {
                            arrayPessoas[i] = novaPessoa;
                            System.out.println("Pessoa criada e armazenada com sucesso (COMPLETO).");
                            break; 
                        }
                    } else {
                         System.err.println("Resposta invalida. Por favor, responda S ou N.");
                         continue;
                    }
                } catch (StopReadingException e) {
                     System.out.println("Entrada de dados interrompida pelo usuario.");
                     return; // Sai do metodo 'lerEArmazenarDados' para finalizar.
                } catch (IllegalArgumentException e) {
                     System.err.println("ERRO detectado: " + e.getMessage());
                     System.err.println("Por favor, corrija os dados e tente novamente.");
                } catch (Exception e) {
                     System.err.println("Erro de entrada inesperado: " + e.getMessage());
                     return;
                }
            }
        }
    }
    
    private static void contarObjetosPorGenero(Pessoa[] arrayPessoas) {
        if (arrayPessoas == null) return;
        
        int contagemHomens = 0;
        int contagemMulheres = 0;
        
        for (Pessoa p : arrayPessoas) {
            if (p != null) {
                if (p instanceof Homem) {
                    contagemHomens++;
                } else if (p instanceof Mulher) {
                    contagemMulheres++;
                }
            }
        }
        
        System.out.println("Total de Homens no array: " + contagemHomens);
        System.out.println("Total de Mulheres no array: " + contagemMulheres);
    }
}