import java.util.ArrayList;
import java.util.Scanner;

public class P2nX {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Cria a instancia de MinhaListaOrdenavel
        MinhaListaOrdenavel listaOrdenavel = new MinhaListaOrdenavel();
        
        // Cria 10 objetos PessoaIMC (5 Homem e 5 Mulher) manualmente e insere na lista.
        criarObjetosManualmente(listaOrdenavel);
        
        // Loop do Menu Principal
        int opcao = 0;
        
        do {
            exibirMenuPrincipal();
            
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome o \n

                switch (opcao) {
                    case 1:
                        imprimirListaOrdenada(scanner, listaOrdenavel); // Opcao Listar
                        break;
                    case 2:
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.err.println("Opcao invalida! Escolha 1 ou 2.");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Opcao invalida! Digite um numero.");
                scanner.nextLine(); // Limpa o buffer
                opcao = 0;
            }
        } while (opcao != 2);
        
        scanner.close();
    }

    // MÉTODOS AUXILIARES
    // Cria e insere 5 Mulheres e 5 Homens na lista.
    private static void criarObjetosManualmente(MinhaListaOrdenavel lista) {
        // NOTA: CPF's são fictícios e devem ser válidos para sua classe ValidaCPF.

        // --- 5 Mulheres (M) ---
        lista.add(new Mulher("Alice", "Silva", "10", "maio", "1995", "01234567890", 55.0f, 1.60f)); 
        lista.add(new Mulher("Bruna", "Santos", 22, 1, 1980, "196.495.487/85", 65.5f, 1.75f)); 
        lista.add(new Mulher("Carla", "Almeida", 1, 11, 2005, "125.134.706-03", 48.0f, 1.55f)); 
        lista.add(new Mulher("Diana", "Rocha", "5", "8", "1970", "33917454800", 72.0f, 1.68f)); 
        lista.add(new Mulher("Erika", "Ferreira", 15, 6, 1999, "00000000191", 58.0f, 1.70f)); 

        // --- 5 Homens (H) ---
        lista.add(new Homem("Fábio", "Moura", 3, 12, 1990, "98765432191", 85.0f, 1.80f)); 
        lista.add(new Homem("Gustavo", "Ramos", "28", "FEVEREIRO", "1985", "529.177.118-02", 70.0f, 1.75f));
        lista.add(new Homem("Henrique", "Lins", "12", "9", "2001", "70295155609", 95.0f, 1.90f));
        lista.add(new Homem("Igor", "Dias", 7, 7, 1975, "001.002.003/88", 78.5f, 1.72f)); 
        lista.add(new Homem("João", "Viana", 19, 4, 1965, "44455588891", 62.0f, 1.65f)); 
        
        System.out.println("10 objetos criados manualmente e inseridos na MinhaListaOrdenavel.");
    }
    
    // Exibe o menu principal.
    private static void exibirMenuPrincipal() {
        System.out.println("\n-------------------------------------");
        System.out.println("1. Imprimir Lista (Escolher Criterio)");
        System.out.println("2. Sair");
        System.out.print("Digite sua opcao: ");
    }
    
    // Exibe o menu de ordenacao, ordena e imprime a lista.
    private static void imprimirListaOrdenada(Scanner scanner, MinhaListaOrdenavel lista) {
        System.out.println("\nEscolha seu modo de ordenacao:");
        
        // Opcoes de ordenacao detalhadas
        System.out.println("-------------------------------------");
        System.out.println(" 1. Nome (A-Z)       |  2. Nome (Z-A)"); 
        System.out.println(" 3. Peso (Menor)     |  4. Peso (Maior)"); 
        System.out.println(" 5. IMC (Menor)      |  6. IMC (Maior)"); 
        System.out.println(" 7. Gênero (Mulher/H)|  8. Gênero (Homem/M)");
        System.out.println(" 9. Idade (Jovem/V)  | 10. Idade (Velho/J)");
        System.out.println("11. Data Nasc. (V/J) | 12. Data Nasc. (J/V)");
        System.out.println("13. CPF (Crescente)  | 14. CPF (Decrescente)");
        System.out.print("Digite sua opcao (1-14): ");

        try {
            int criterioInt = scanner.nextInt();
            scanner.nextLine();

            // Opcao do usuario para os valores do ENUM (1-14)
            int criterioMapeado;
            switch (criterioInt) {
                // Nome
                case 1: criterioMapeado = MinhaListaOrdenavel.Criterio.NOME_AZ.getValor(); break;
                case 2: criterioMapeado = MinhaListaOrdenavel.Criterio.NOME_ZA.getValor(); break;
                // Peso
                case 3: criterioMapeado = MinhaListaOrdenavel.Criterio.PESO_CRESCENTE.getValor(); break;
                case 4: criterioMapeado = MinhaListaOrdenavel.Criterio.PESO_DECRESCENTE.getValor(); break;
                // IMC
                case 5: criterioMapeado = MinhaListaOrdenavel.Criterio.IMC_CRESCENTE.getValor(); break;
                case 6: criterioMapeado = MinhaListaOrdenavel.Criterio.IMC_DECRESCENTE.getValor(); break;
                // Genero
                case 7: criterioMapeado = MinhaListaOrdenavel.Criterio.GENERO_M_H.getValor(); break;
                case 8: criterioMapeado = MinhaListaOrdenavel.Criterio.GENERO_H_M.getValor(); break;
                // Idade
                case 9: criterioMapeado = MinhaListaOrdenavel.Criterio.IDADE_CRESCENTE.getValor(); break;
                case 10: criterioMapeado = MinhaListaOrdenavel.Criterio.IDADE_DECRESCENTE.getValor(); break;
                // Data de Nascimento
                case 11: criterioMapeado = MinhaListaOrdenavel.Criterio.DATA_NASCIMENTO_AZ.getValor(); break;
                case 12: criterioMapeado = MinhaListaOrdenavel.Criterio.DATA_NASCIMENTO_ZA.getValor(); break;
                // CPF
                case 13: criterioMapeado = MinhaListaOrdenavel.Criterio.CPF_CRESCENTE.getValor(); break;
                case 14: criterioMapeado = MinhaListaOrdenavel.Criterio.CPF_DECRESCENTE.getValor(); break;
                default:
                    throw new IllegalArgumentException("Opcao de criterio invalida.");
            }

            // O metodo ordena retorna a lista ordenada.
            ArrayList<PessoaIMC> listaOrdenada = lista.ordena(criterioMapeado); 

            System.out.println("\n*** LISTA ORDENADA (Criterio " + criterioInt + ") ***");
            System.out.println("-------------------------------------");
            for (PessoaIMC p : listaOrdenada) {
                // Exibe as informacoes completas (toString).
                System.out.println(p.toString());
            }

        } catch (java.util.InputMismatchException e) {
            System.err.println("Entrada invalida. Por favor, digite um numero.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de criterio: " + e.getMessage());
        }
    }
}