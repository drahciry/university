// Importa Scanner para capturar opcoes do usuario
import java.util.Scanner;

/**
 * Classe do programa principal do projeto.
 */
public class P2nX {
    /**
     * Metodo main, entry point do programa, onde possui toda logica.
     * 
     * @param args ({@code String[]}): Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        // Criação do scanner e da lista ordenavel.
        Scanner scanner = new Scanner(System.in);
        MinhaListaOrdenavel pessoas = new MinhaListaOrdenavel();

        // Adiciona 5 homens a lista.
        pessoas.add(new Homem("Giorgian", "de Arrascaeta", "01", "Junho", "1994", 73.52f, 1.73f));
        pessoas.add(new Homem("Bruno", "Henrique", "30", "Dezembro", "1990", 77.89f, 1.84f));
        pessoas.add(new Homem( "Erick", "Pulgar", "15", "Janeiro", "1994", 73.19f, 1.87f));
        pessoas.add(new Homem( "Agustin", "Rossi", "21", "Agosto", "1995", 92.33f, 1.95f));
        pessoas.add(new Homem( "Jorge", "Carrascal", "25", "Maio", "1998", 73.4f, 1.80f));
        // Adiciona 5 mulheres a lista.
        pessoas.add(new Mulher( "Jennifer", "Lawrence", "15", "Agosto", "1990", 67.37f, 1.75f));
        pessoas.add(new Mulher( "Scarlett", "Johanson", "22", "Novembro", "1984", 53.7f, 1.6f));
        pessoas.add(new Mulher( "Emma", "Stone", "06", "Novembro", "1988", 56.1f, 1.68f));
        pessoas.add(new Mulher( "Emma", "Watson", "15", "Abril", "1990", 55.41f, 1.65f));
        pessoas.add(new Mulher( "Angelina", "Jolie", "04", "Junho", "1975", 61.04f, 1.69f));

        // Variavel inteira para armazenar pessoa e Enum interno de MinhaListaOrdenavel..
        int opcao;
        MinhaListaOrdenavel.CriterioOrdenacao criterio;

        // Loop com while true que o usuario escolha ordenar mais de uma vez.
        while (true) {
            // Exibe as opcoes.
            System.out.print(
                "1. Imprimir lista\n" +
                "2. Sair\n\n" +
                "Digite sua opcao: "
            );
            // Realiza um tratamento para que seja aceito apenas numeros.
            try {
                // Realiza o parse para transformar a String em um int.
                opcao = Integer.parseInt(scanner.nextLine());
                System.out.println();
            } catch (NumberFormatException e) {
                // Exibe mensagem informativa.
                System.out.println("Entrada invalida: insira somente numeros inteiros.\n");
                continue;
            }
            // Realiza um tratamento para que seja digitado apenas opcoes validas.
            if (opcao < 1 || opcao > 2)  {
                System.out.println("Entrada invalida: insira somente valores nas opcoes listadas.\n");
                continue;
            }
            // Se a opcao for 2, o usuario decidiu encerrar o programa.
            if (opcao == 2)
                break;
            // Novo while true para que seja selecionado o modo de ordenacao da lista.
            // Aqui o while sera quebrado apenas se o usuario digitar o modo de ordenacao
            // corretamente, garantindo consistencia ao programa.
            while (true) {
                // Exibe opcoes.
                System.out.print(
                    "Escolha seu modo de ordenacao:\n" +
                    "1. Alfabetica (A-Z)\n" +
                    "2. Alfabetica (Z-A)\n" +
                    "3. Menor Peso\n" +
                    "4. Maior Peso\n" +
                    "5. Menor Altura\n" +
                    "6. Maior Altura\n" +
                    "7. Menor IMC\n" +
                    "8. Maior IMC\n" +
                    "9. Genero (Feminino-Masculino)\n" +
                    "10. Genero (Masculino-Feminino)\n" +
                    "11. Menor Idade\n" +
                    "12. Maior Idade\n" +
                    "13. Data de Nascimento (Mais Novo-Mais Velho)\n" +
                    "14. Data de Nascimento (Mais Velho-Mais Novo)\n" +
                    "15. Menor CPF\n" +
                    "16. Maior CPF\n\n" +
                    "Digite sua opcao: "
                );
                // Realiza um tratamento para que seja aceito apenas numeros.
                try {
                    // Realiza o parse para transformar a String em um int.
                    opcao = Integer.parseInt(scanner.nextLine()); 
                    System.out.println();
                    break;   
                } catch (NumberFormatException e) {
                    // Exibe mensagem informativa.
                    System.out.println("Entrada invalida: insira somente numeros inteiros.\n");
                }
            }
            // Apos capturar a opcao do usuario, retorna uma instancia do Enum
            // com a opcao correspondente ao modo inserido.
            criterio = MinhaListaOrdenavel.CriterioOrdenacao.fromValor(opcao);
            // Por fim, a lista sera ordenada por meio do metodo ordena() e,
            // logo em seguida, sera exibida ja ordenada, utilizando um for each.
            for (PessoaIMC p : pessoas.ordena(criterio)) {
                // Exibe informacoes da pessoa na lista presente.
                System.out.println(p + "\n");
            }
        }
        // Loop principal encerrado, programa principal encerrado.
        System.out.println("Programa encerrado!");
        // Fecha o scanner para que nao aja erro de buffer.
        scanner.close();
    }
}
