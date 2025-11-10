import java.util.Scanner;

public class P2nX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinhaListaOrdenavel pessoas = new MinhaListaOrdenavel();

        Homem homem1 = new Homem(
            "Giorgian",
            "de Arrascaeta",
            "01",
            "Junho",
            "1994",
            73.52f,
            1.73f
        );
        Homem homem2 = new Homem(
            "Bruno",
            "Henrique",
            "30",
            "Dezembro",
            "1990",
            77.89f,
            1.84f
        );
        Homem homem3 = new Homem(
            "Erick",
            "Pulgar",
            "15",
            "Janeiro",
            "1994",
            73.19f,
            1.87f
        );
        Homem homem4 = new Homem(
            "Agustin",
            "Rossi",
            "21",
            "Agosto",
            "1995",
            92.33f,
            1.95f
        );
        Homem homem5 = new Homem(
            "Jorge",
            "Carrascal",
            "25",
            "Maio",
            "1998",
            73.4f,
            1.80f
        );

        Mulher mulher1 = new Mulher(
            "Jennifer",
            "Lawrence",
            "15",
            "Agosto",
            "1990",
            67.37f,
            1.75f
        );
        Mulher mulher2 = new Mulher(
            "Scarlett",
            "Johanson",
            "22",
            "Novembro",
            "1984",
            53.7f,
            1.6f
        );
        Mulher mulher3 = new Mulher(
            "Emma",
            "Stone",
            "06",
            "Novembro",
            "1988",
            56.1f,
            1.68f
        );
        Mulher mulher4 = new Mulher(
            "Emma",
            "Watson",
            "15",
            "Abril",
            "1990",
            55.41f,
            1.65f
        );
        Mulher mulher5 = new Mulher(
            "Angelina",
            "Jolie",
            "04",
            "Junho",
            "1975",
            61.04f,
            1.69f
        );

        pessoas.add(homem1);
        pessoas.add(homem2);
        pessoas.add(homem3);
        pessoas.add(homem4);
        pessoas.add(homem5);

        pessoas.add(mulher1);
        pessoas.add(mulher2);
        pessoas.add(mulher3);
        pessoas.add(mulher4);
        pessoas.add(mulher5);

        int opcao;
        MinhaListaOrdenavel.CriterioOrdenacao criterio;

        while (true) {
            System.out.print(
                "1. Imprimir lista\n" +
                "2. Sair\n\n" +
                "Digite sua opcao: "
            );
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Erro: insira somente numeros inteiros.\n");
                continue;
            }

            if (opcao < 1 || opcao > 2)  {
                System.out.println("Entrada invalida: insira somente valores nas opcoes listadas.\n");
                continue;
            }

            if (opcao == 2)
                break;

            while (true) {
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
                
                try {
                    opcao = Integer.parseInt(scanner.nextLine()); 
                    System.out.println();
                    break;   
                } catch (NumberFormatException e) {
                    System.out.println("Erro: insira somente numeros inteiros.\n");
                }
            }
            criterio = MinhaListaOrdenavel.CriterioOrdenacao.fromValor(opcao);

            for (PessoaIMC p : pessoas.ordena(criterio)) {
                System.out.println(p + "\n");
            }
        }

        System.out.println("Programa encerrado!");

        scanner.close();
    }
}
