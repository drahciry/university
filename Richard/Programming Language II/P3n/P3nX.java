// Importacoes uteis ao programa
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Properties;
import java.io.FileInputStream;

// Importa todas as classes do pacote da biblioteca
import lp2g21.bib.*;

/**
 * Classe principal do Sistema de Biblioteca.
 * Responsavel pela interacao com o usuario via menu e execucao das operacoes.
 */
public class P3nX {

    // Instancia estatica da Biblioteca para ser acessada por todos os metodos.
    private static Biblioteca library;
    // Scanner global para leitura de dados.
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Ponto de entrada do programa.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        System.out.println("#########################################");
        System.out.println("#      SISTEMA DE BIBLIOTECA v1.0       #");
        System.out.println("#########################################");

        // Fase de Inicializacao: Decide se comeca vazio ou carrega arquivos.
        inicializarSistema();

        boolean rodando = true;
        
        // Loop principal do menu
        while (rodando) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1":
                        menuCadastro(); // Modulo de Cadastro
                        break;
                    case "2":
                        menuMovimentacao(); // Modulo de Emprestimo/Devolucao
                        break;
                    case "3":
                        menuRelatorios(); // Modulo de Relatorios
                        break;
                    case "4":
                        menuManutencao(); // Modulo de Persistencia
                        break;
                    case "0":
                        System.out.println("\nEncerrando o sistema... Ate logo!");
                        rodando = false;
                        break;
                    default:
                        System.out.println("\n[AVISO] Opcao invalida. Tente novamente.");
                }
            } catch (Exception e) {
                // Captura generica para garantir que o programa nao feche abruptamente ("crash").
                System.out.println("\n[ERRO CRITICO] Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /*************************************************************
     *                 METODOS DE INICIALIZACAO                  *
    *************************************************************/

    /**
     * Pergunta ao usuario como ele deseja iniciar a biblioteca.
     */
    private static void inicializarSistema() {
        // Exibe menu de inicializacao.
        System.out.println("\nComo deseja iniciar a biblioteca?");
        System.out.println("1. Iniciar do zero (Banco de dados vazio)");
        System.out.println("2. Carregar de arquivos existentes");
        // Enquanto a entrada for invalida, realiza captura.
        while (true) {
            System.out.print("Escolha: ");
            // Captura entrada.
            String escolha = scanner.nextLine();
            // Verifica a escolha.
            if (escolha.equals("2")) {
                // Se a escolha for 2, captura os arquivos.
                System.out.print("Nome do arquivo de USUARIOS (ex: u.dat): ");
                // Captura o arquivo com usuarios.
                String uFile = scanner.nextLine();
                System.out.print("Nome do arquivo de LIVROS (ex: l.dat): ");
                // Captura o arquivo com livros.
                String lFile = scanner.nextLine();
                // Tenta instanciar o objeto com os arquivos utilizando o construtor.
                try {
                    // Tenta instanciar carregando os arquivos.
                    library = new Biblioteca(uFile, lFile);
                } catch (Exception e) {
                    // O construtor da Biblioteca lanca excecoes se falhar na leitura.
                    System.out.println("\n[FALHA] Nao foi possivel carregar os arquivos: " + e.getMessage());
                    System.out.println("Iniciando biblioteca vazia por seguranca.");
                    // Inicia a biblioteca vazia por seguranca.
                    library = new Biblioteca();
                }
            } else if (escolha.equals("1")) {
                // Se a escolha for 1, inicia vazia por padrao.
                library = new Biblioteca();
                System.out.println("\n[INFO] Biblioteca iniciada vazia.");
            } else {
                // A escolha eh invalida.
                System.out.println("[INFO] Esta opcao nao existe. Insira uma opcao valida.");
                continue;
            }
            break;
        }
        carregarConfigs();
    }

    private static void carregarConfigs() {
        // Cria objeto de Properties.
        Properties prop = new Properties();
        // Le arquivo de configuracoes.
        try (FileInputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            // Le as strings do arquivo.
            String diasStr = prop.getProperty("dias.emprestimo", "7"); // 7 é o padrão se falhar
            String multaStr = prop.getProperty("multa.diaria", "0.0");
            // Converte.
            int dias = Integer.parseInt(diasStr);
            double multa = Double.parseDouble(multaStr);
            // Injeta na biblioteca.
            if (library != null) {
                library.setConfiguracao(dias, multa);
                System.out.println("[CONFIG] Regras carregadas: " + dias + " dias de prazo / Multa R$" + multa);
            }
        // Captura excecoes.
        } catch (Exception e) {
            System.out.println("[INFO] Usando configuracoes padrao (Sem arquivo config.properties)");
        }
    }

    /**
     * Metodo para exibir o menu principal.
     */
    private static void exibirMenuPrincipal() {
        System.out.println("\n--------- MENU PRINCIPAL ---------");
        System.out.println("1. Cadastro (Usuarios e Livros)");
        System.out.println("2. Movimentacao (Emprestar e Devolver)");
        System.out.println("3. Relatorios (Listar)");
        System.out.println("4. Manutencao (Salvar e Carregar)");
        System.out.println("0. Sair");
        System.out.print("Digite a opcao desejada: ");
    }

    /*************************************************************
     *                    MODULO DE CADASTRO                     *
    *************************************************************/

    /**
     * Metodo que exibe menu de cadastro.
     */
    private static void menuCadastro() {
        System.out.println("\n--- CADASTRO ---");
        System.out.println("1. Cadastrar Novo Usuario");
        System.out.println("2. Cadastrar Novo Livro");
        System.out.print("Opcao: ");
        String opt = scanner.nextLine();

        try {
            if (opt.equals("1")) {
                cadastrarUsuario();
            } else if (opt.equals("2")) {
                cadastrarLivro();
            } else {
                System.out.println("[AVISO] Opcao invalida de cadastro.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n[ERRO DE FORMATO] Voce digitou texto onde deveria ser numero.");
        } catch (Exception e) {
            System.out.println("\n[ERRO NO CADASTRO] " + e.getMessage());
        }
    }

    /**
     * Metodo para cadastrar usuario.
     * 
     * @throws Exception
     */
    private static void cadastrarUsuario() throws Exception {
        System.out.println("\nPreencha os dados do Usuario:");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Sobrenome: "); String sobre = scanner.nextLine();
        System.out.print("CPF (formato XXX.XXX.XXX-XX): "); String cpf = scanner.nextLine();
        System.out.print("Endereco: "); String end = scanner.nextLine();
        
        System.out.print("Dia Nasc (1-31): "); int d = Integer.parseInt(scanner.nextLine());
        System.out.print("Mes Nasc (1-12): "); int m = Integer.parseInt(scanner.nextLine());
        System.out.print("Ano Nasc (AAAA): "); int a = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Peso (ex: 70.5): "); float peso = Float.parseFloat(scanner.nextLine());
        System.out.print("Altura (ex: 1.75): "); float alt = Float.parseFloat(scanner.nextLine());

        // Cria o objeto Usuario. As validacoes (Regex, Datas) ocorrem no construtor.
        Usuario u = new Usuario(nome, sobre, d, m, a, cpf, peso, alt, end);
        
        // Tenta cadastrar na biblioteca (Verifica se CPF ja existe).
        library.cadastraUsuario(u);
    }

    /**
     * Metodo para cadastrar livro.
     * @throws Exception
     */
    private static void cadastrarLivro() throws Exception {
        System.out.println("\nPreencha os dados do Livro:");
        System.out.print("Codigo (Numero Inteiro): "); 
        int cod = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Titulo: "); 
        String tit = scanner.nextLine();
        
        System.out.println("Categorias validas: Aventura, Romance, Ficcao, Comedia, Suspense, Terror");
        System.out.print("Categoria: "); 
        String cat = scanner.nextLine();
        
        System.out.print("Quantidade de Copias: "); 
        int qtd = Integer.parseInt(scanner.nextLine());

        // Cria o objeto Livro. Validacoes ocorrem no construtor.
        Livro l = new Livro(cod, tit, cat, qtd);
        
        // Tenta cadastrar na biblioteca (Verifica se Codigo ja existe).
        library.cadastraLivro(l);
    }

    /*************************************************************
     *                  MODULO DE MOVIMENTACAO                   *
    *************************************************************/

    /**
     * Metodo que realiza as acoes da biblioteca.
     */
    private static void menuMovimentacao() {
        System.out.println("\n--- MOVIMENTACAO ---");
        System.out.println("1. Realizar Emprestimo");
        System.out.println("2. Realizar Devolucao");
        System.out.print("Opcao: ");
        String opt = scanner.nextLine();

        try {
            System.out.print("Informe o CPF do Usuario (somente numeros): ");
            // Remove caracteres especiais caso o usuario digite
            String cpfRaw = scanner.nextLine().replaceAll("\\D", "");
            long cpf = Long.parseLong(cpfRaw);
            
            System.out.print("Informe o Codigo do Livro: ");
            int cod = Integer.parseInt(scanner.nextLine());

            // Tenta recuperar os objetos. Lanca excecao se nao existirem.
            Usuario user = library.getUsuario(cpf);
            Livro book = library.getLivro(cod);

            if (opt.equals("1")) {
                // Realiza Emprestimo
                library.emprestaLivro(user, book);
                
                // Exibe Recibo
                System.out.println("\n=== RECIBO DE EMPRESTIMO ===");
                System.out.println("Status: SUCESSO");
                System.out.println("Usuario: " + user.getNome() + " " + user.getSobreNome());
                System.out.println("Livro: " + book.getTitulo());
                System.out.println("Data: " + LocalDate.now());
                System.out.println("============================");

            } else if (opt.equals("2")) {
                // Realiza Devolucao
                library.devolveLivro(user, book);
                // Mensagem de sucesso ja eh exibida dentro do metodo devolveLivro
            } else {
                System.out.println("Opcao invalida.");
            }
        // Captura as excecoes.
        } catch (UsuarioNaoCadastradoEx | LivroNaoCadastradoEx e) {
            System.out.println("\n[ERRO DE BUSCA] " + e.getMessage());
        } catch (CopiaNaoDisponivelEx e) {
            System.out.println("\n[ERRO DE ESTOQUE] Nao ha copias disponiveis deste livro.");
        } catch (NenhumaCopiaEmprestadaEx e) {
            System.out.println("\n[ERRO LOGICO] Nao ha copias emprestadas para serem devolvidas.");
        } catch (UserNotFoundException | BookNotFoundException e) {
             System.out.println("\n[ERRO DE HISTORICO] Inconsistencia encontrada: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n[ERRO DE ENTRADA] CPF ou Codigo devem ser numericos.");
        } catch (Exception e) {
            System.out.println("\n[ERRO GERAL] " + e.getMessage());
        }
    }

    /*************************************************************
     *                   MODULO DE RELATORIOS                    *
    *************************************************************/

    /**
     * Metodo para exibir relatorios com metodos da {@code Biblioteca}.
     */
    private static void menuRelatorios() {
        System.out.println("\n--- RELATORIOS ---");
        System.out.println("1. Listar Todos os Usuarios (Ordenado por CPF)");
        System.out.println("2. Listar Todos os Livros (Ordenado por Codigo)");
        System.out.print("Opcao: ");
        String opt = scanner.nextLine();

        if (opt.equals("1")) {
            System.out.println("\nListagem de Usuarios:");
            System.out.println(library.imprimeUsuarios());
        } else if (opt.equals("2")) {
            System.out.println("\nListagem de Livros:");
            System.out.println(library.imprimeLivros());
        } else {
            System.out.println("Opcao invalida.");
        }
    }

    /*************************************************************
     *                   MODULO DE MANUTENCAO                    *
    *************************************************************/

    /**
     * Metodo que exibe menu de manutencao.
     */
    private static void menuManutencao() {
        System.out.println("\n--- MANUTENCAO DE DADOS ---");
        System.out.println("1. Salvar Dados em Arquivo");
        System.out.println("2. Recarregar Dados de Arquivo");
        System.out.print("Opcao: ");
        String opt = scanner.nextLine();

        // Solicita nomes de arquivos
        System.out.print("Nome do arquivo de USUARIOS (ex: u.dat): ");
        String uFile = scanner.nextLine();
        System.out.print("Nome do arquivo de LIVROS (ex: l.dat): ");
        String lFile = scanner.nextLine();

        try {
            if (opt.equals("1")) {
                // SALVAR:
                // Recupera os HashMaps da biblioteca e manda salvar.
                // Requer que a classe Biblioteca tenha os metodos getAllUsers() e getAllBooks()
                library.salvaArquivo(library.getAllUsers(), uFile);
                library.salvaArquivo(library.getAllBooks(), lFile);

            } else if (opt.equals("2")) {
                // RECARREGAR:
                // Chama o metodo leArquivo passando o tipo ("users" ou "books")
                library.leArquivo(uFile, "users");
                library.leArquivo(lFile, "books");
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (Exception e) {
            System.out.println("\n[ERRO DE ARQUIVO] " + e.getMessage());
        }
    }
}
