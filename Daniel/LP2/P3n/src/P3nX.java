import lp2g04.bib.*; // Importa todas as classes
import java.util.*;

public class P3nX {

    // Instância única da biblioteca para todo o programa
    private static Biblioteca bib;
    private static Scanner scanner = new Scanner(System.in);
    
    // Nomes padrao dos arquivos
    private static String arqUsuarios = "u.dat";
    private static String arqLivros = "l.dat";

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTAO DE BIBLIOTECA ===");
        
        // Inicializacao (Carregar ou Zerar)
        iniciarSistema();

        // Loop do Menu Principal
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    moduloManutencao();
                    break;
                case 2:
                    moduloCadastro();
                    break;
                case 3:
                    moduloEmprestimo();
                    break;
                case 4:
                    moduloRelatorio();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Encerrando o sistema...");
                    break;
                case 99: // Opção extra para gerar os dados de teste
                    gerarDadosDeTeste();
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
        scanner.close();
    }

    // --- METODOS DE INICIALIZACAO ---

    private static void iniciarSistema() {
        System.out.println("\nComo deseja iniciar?");
        System.out.println("1 - Iniciar limpo (Nova Biblioteca)");
        System.out.println("2 - Carregar dados do disco");
        System.out.println("3 - Carregar dados padrao (u.dat / l.dat)");
        
        int op = lerInteiro("Opcao: ");

        if (op == 2 || op == 3) {
            if (op == 2) {
                arqUsuarios = lerString("Nome do arquivo de Usuarios: ");
                arqLivros = lerString("Nome do arquivo de Livros: ");
            }
            // Tenta carregar
            bib = new Biblioteca(arqUsuarios, arqLivros);
        } else {
            // Inicia zerada
            bib = new Biblioteca();
            System.out.println("Biblioteca iniciada vazia.");
        }
    }

    // --- MENUS ---

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Manutencao (Arquivos)");
        System.out.println("2. Cadastro (Usuarios/Livros)");
        System.out.println("3. Emprestimos e Devolucoes");
        System.out.println("4. Relatorios");
        System.out.println("0. Sair");
        System.out.println("----------------------");
    }

    // --- MODULO 1: MANUTENCAO ---
    private static void moduloManutencao() {
        System.out.println("\n--- MANUTENCAO DE ARQUIVOS ---");
        System.out.println("1. Salvar estado atual");
        System.out.println("2. Alterar nomes dos arquivos padrao");
        System.out.println("0. Voltar");

        int op = lerInteiro("Opcao: ");
        switch (op) {
            case 1:
                salvarDados();
                break;
            case 2:
                arqUsuarios = lerString("Novo nome para Usuarios (ex: users_bkp.dat): ");
                arqLivros = lerString("Novo nome para Livros (ex: books_bkp.dat): ");
                System.out.println("Nomes alterados. Lembre-se de salvar para efetivar nestes arquivos.");
                break;
            case 0:
                return;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void salvarDados() {
        try { 
             // Chamada:
             bib.salvaArquivo(bib.getCadastroUsuarios(), arqUsuarios);
             bib.salvaArquivo(bib.getAcervoLivros(), arqLivros);
            
            System.out.println("Dados salvos com sucesso em " + arqUsuarios + " e " + arqLivros);
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // --- MÓDULO 2: CADASTRO ---
    private static void moduloCadastro() {
        System.out.println("\n--- CADASTRO ---");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Livro");
        System.out.println("0. Voltar");

        int op = lerInteiro("Opção: ");
        switch (op) {
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                cadastrarLivro();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
        
        // Pergunta se quer salvar logo (requisito do PDF)
        System.out.println("Deseja salvar as alterações agora? (S/N)");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("S")) {
            salvarDados();
        }
    }

    private static void cadastrarUsuario() {
        try {
            System.out.println("\nNovo Usuário:");
            
            // CORREÇÃO: Pede Nome e Sobrenome separados
            String nome = lerString("Primeiro Nome: ");
            String sobreNome = lerString("Sobrenome: ");
            
            String cpfStr = lerString("CPF (apenas números): ");
            // Valida CPF antes de continuar
            if (!ValidaCPF.isCPF(cpfStr)) {
                System.out.println("CPF Inválido! Cadastro cancelado.");
                return;
            }
            // OBS: Não converte para Long aqui, pois o construtor pede String
            
            String end = lerString("Endereço: ");
            String dataNascStr = lerString("Data Nascimento (dd/mm/aaaa): ");
            
            // CORREÇÃO: Divide a String da data para passar dia, mes e ano separados
            String[] partes = dataNascStr.split("/");
            if(partes.length != 3) {
                 System.out.println("Formato de data inválido!");
                 return;
            }

            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);

            // CORREÇÃO: Usa o construtor correto da classe Usuario
            // Usuario(nome, sobrenome, dia, mes, ano, cpfString, endereco)
            Usuario u = new Usuario(nome, sobreNome, dia, mes, ano, cpfStr, end);
            
            bib.cadastraUsuario(u);
            System.out.println("Usuário cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private static void cadastrarLivro() {
        try {
            System.out.println("\nNovo Livro:");
            int cod = lerInteiro("Código (1-999): ");
            String tit = lerString("Título: ");
            String cat = lerString("Categoria: ");
            int qtd = lerInteiro("Quantidade total: ");

            Livro l = new Livro(cod, tit, cat, qtd);
            bib.cadastraLivro(l);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    // --- MÓDULO 3: EMPRÉSTIMOS ---
    private static void moduloEmprestimo() {
        System.out.println("\n--- CIRCULAÇÃO ---");
        System.out.println("1. Emprestar Livro");
        System.out.println("2. Devolver Livro");
        System.out.println("0. Voltar");

        int op = lerInteiro("Opção: ");
        try {
            if (op == 0) return;

            long cpf = Long.parseLong(lerString("CPF do Usuário: "));
            Usuario u = bib.getUsuario(cpf); // Pode lançar UsuarioNaoCadastradoEx
            
            System.out.println("Usuário selecionado: " + u.getNome());

            int cod = lerInteiro("Código do Livro: ");
            Livro l = bib.getLivro(cod); // Pode lançar LivroNaoCadastradoEx
            System.out.println("Livro selecionado: " + l.getTitulo());

            if (op == 1) {
                bib.emprestaLivro(u, l); // Pode lançar CopiaNaoDisponivelEx
            } else if (op == 2) {
                bib.devolveLivro(u, l); // Pode lançar NenhumaCopiaEmprestadaEx
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite números válidos para CPF/Código.");
        } catch (Exception e) {
            // Captura as exceções personalizadas (UsuarioNaoCadastradoEx, etc)
            System.out.println("Não foi possível completar a operação: " + e.getMessage());
        }
    }

    // --- MÓDULO 4: RELATÓRIOS ---
    private static void moduloRelatorio() {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Listar Livros (por código)");
        System.out.println("2. Listar Usuários (por CPF)");
        System.out.println("0. Voltar");

        int op = lerInteiro("Opção: ");
        switch (op) {
            case 1:
                System.out.println(bib.imprimeLivros());
                break;
            case 2:
                System.out.println(bib.imprimeUsuarios());
                break;
        }
    }

    // --- UTILITÁRIOS E DEBUG ---

    private static String lerString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static int lerInteiro(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. " + msg);
            scanner.next();
        }
        int v = scanner.nextInt();
        scanner.nextLine(); // Limpa buffer
        return v;
    }

    // Gera os dados para atender o item VIII do PDF
    private static void gerarDadosDeTeste() {
        System.out.println("Gerando dados de exemplo para avaliação...");
        try {
            // CORREÇÃO: Atualizado para o novo construtor (Nome, Sobrenome, Dia, Mes, Ano, CPF String, Endereco)
            Usuario u1 = new Usuario("Ana", "Silva", 1, 1, 1990, "11111111111", "Rua A");
            Usuario u2 = new Usuario("Beto", "Souza", 15, 5, 1995, "22222222222", "Rua B");
            Usuario u3 = new Usuario("Carlos", "Lima", 25, 12, 1980, "33333333333", "Rua C");
            Usuario u4 = new Usuario("Daniela", "Paz", 10, 3, 2000, "44444444444", "Rua D");
            Usuario u5 = new Usuario("Eduardo", "Luz", 7, 7, 1985, "55555555555", "Rua E");

            bib.cadastraUsuario(u1); bib.cadastraUsuario(u2); bib.cadastraUsuario(u3);
            bib.cadastraUsuario(u4); bib.cadastraUsuario(u5);

            // 5 Livros
            Livro l1 = new Livro(101, "Java Completo", "Tecnologia", 5);
            Livro l2 = new Livro(102, "Dom Casmurro", "Literatura", 2);
            Livro l3 = new Livro(103, "O Senhor dos Anéis", "Fantasia", 3);
            Livro l4 = new Livro(104, "Clean Code", "Tecnologia", 1);
            Livro l5 = new Livro(105, "Harry Potter", "Fantasia", 4);

            bib.cadastraLivro(l1); bib.cadastraLivro(l2); bib.cadastraLivro(l3);
            bib.cadastraLivro(l4); bib.cadastraLivro(l5);

            // 1 Empréstimo em Vigor (Eduardo pegou Harry Potter)
            bib.emprestaLivro(u5, l5);

            // 1 Devolução Concluída (Ana pegou Java e devolveu)
            bib.emprestaLivro(u1, l1);
            bib.devolveLivro(u1, l1);

            System.out.println("Dados gerados! Salvando em " + arqUsuarios + " e " + arqLivros);
            salvarDados();

        } catch (Exception e) {
            System.out.println("Erro ao gerar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}