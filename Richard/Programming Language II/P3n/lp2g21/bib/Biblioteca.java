// Pacote das classes
package lp2g21.bib;

// Importacoes uteis a classe.
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa uma biblioteca.
 * Possui atributos como:
 * Registro de Usuarios Cadastrados e
 * Registro de Livros Cadastrados.
 */
public class Biblioteca {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    private HashMap<Long, Usuario> usuariosCadastrados;
    private HashMap<Integer, Livro> livrosCadastrados;
    private int diasPermitidos = 7;
    private double valorMultaDiaria = 0.0;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor basico, sem parametros.
     * Iniciara a biblioteca sem livros e sem usuarios.
     */
    public Biblioteca() {
        this.usuariosCadastrados = new HashMap<Long, Usuario>();
        this.livrosCadastrados = new HashMap<Integer, Livro>();
    }

    /**
     * Construtor especializado.
     * Iniciara a biblioteca com livros e usuarios ja armazenados
     * em um arquivo que contem todos os objetos utilizados.
     * 
     * @param userFile ({@code String}): Arquivo que contem os dados de usuarios.
     * @param bookFile ({@code String}): Arquivo que contem os dados de livros.
     */
    public Biblioteca(String userFile, String bookFile) {
        // Primeiro, cria os registros utilizando o construtor basico.
        this();
        // Apos, tenta ler os arquivos para inicializar os registros.
        leArquivo(userFile, "users");
        leArquivo(bookFile, "books");
    }

    /*************************************************************
     *                         METODOS                           *
    *************************************************************/

    /**
     * Getter para registro de livros.
     * 
     * @return {@code HashMap<Integer, Livro>}: Retorna um HashMap com os livros, onde o codigo do livro eh a chave.
     */
    public HashMap<Integer, Livro> getAllBooks() {
        return this.livrosCadastrados;
    }

    /**
     * Getter para registro de usuarios.
     * 
     * @return {@code HashMap<Long, Usuario>}: Retorna um HashMap com os usuarios, onde o CPF do usuario eh a chave.
     */
    public HashMap<Long, Usuario> getAllUsers() {
        return this.usuariosCadastrados;
    }

    /**
     * Getter para livro da biblioteca.
     * 
     * @param cod ({@code int}): Codigo do livro a ser retornado.
     * @return {@code Livro}: Retorna o livro da biblioteca com o codigo inserido.
     * 
     * @throws LivroNaoCadastradoEx Caso o codigo nao exista no sistema, lanca excecao.
     */
    public Livro getLivro(int cod) throws LivroNaoCadastradoEx {
        // Verifica se o codigo existe no sistema.
        if (!livrosCadastrados.containsKey(cod))
            // Se nao existir, lanca excecao.
            throw new LivroNaoCadastradoEx("O codigo inserido nao esta cadastrado na biblioteca.");
        // Retorna o livro utilizando a chave.
        return livrosCadastrados.get(cod);
    }

    /**
     * Getter para usuario da biblioteca.
     * 
     * @param numCPF ({@code long}): CPF do usuario a ser retornado.
     * @return {@code Livro}: Retorna o usuario cadastrado na biblioteca com o CPF inserido.
     * 
     * @throws LivroNaoCadastradoEx Caso o CPF nao exista no sistema, lanca excecao.
     */
    public Usuario getUsuario(long numCPF) throws UsuarioNaoCadastradoEx {
        // Verifica se o CFP existe no sistema.
        if (!usuariosCadastrados.containsKey(numCPF))
            // Se nao existir, lanca excecao.
            throw new UsuarioNaoCadastradoEx("O CPF inserido nao esta cadastrado na biblioteca.");
        // Retorna o usuario utilizando a chave.
        return usuariosCadastrados.get(numCPF);
    }

    /**
     * Metodo que configura quantos dias eh permitido ficar com
     * o livro e qual o valor da multa diaria caso passe o permitido.
     * 
     * @param dias ({@code int}): Quantidade de dias permitidos.
     * @param multa ({@code double}): Valor da multa diaria.
     */
    public void setConfiguracao(int dias, double multa) {
        this.diasPermitidos = dias;
        this.valorMultaDiaria = multa;
    }

    /**
     * Metodo que recebe um usuario e cadastra no sistema.
     * 
     * @param usuario ({@code Usuario}): Usuario a ser cadastrado.
     * 
     * @throws InvalidKeyException Caso o CPF do usuario ja esteja cadastrado, sera lancada uma excecao.
     */
    public void cadastraUsuario(Usuario usuario) throws InvalidKeyException {
        // Resgata o CPF do usuario.
        long cpf = usuario.getNumCPF();
        // Verfica se a chave ja existe no sistema.
        if (usuariosCadastrados.containsKey(cpf))
            // Caso esteja, lanca excecao.
            throw new InvalidKeyException("Usuario com cpf " + cpf + " ja existe!");
        // Cadastra o usuario no sistema utilizando CPF como chave.
        usuariosCadastrados.put(cpf, usuario);
        // Exibe mensagem de sucesso.
        System.out.println("Usuario cadastrado com sucesso.");
    }

    /**
     * Metodo que recebe um livro e cadastra no sistema.
     * 
     * @param livro ({@code Livro}): Livro a ser cadastrado.
     * 
     * @throws InvalidKeyException Caso o codigo do livro ja esteja cadastrado, sera lancada uma excecao.
     */
    public void cadastraLivro(Livro livro) throws InvalidKeyException {
        // Resgata o codigo do livro.
        int codigo = livro.getCodigo();
        // Verifica se a chave ja existe no sistema.
        if (livrosCadastrados.containsKey(codigo))
            // Caso exista, lanca excecao.
            throw new InvalidKeyException("Codigo de livro " + codigo + " ja existe!");
        // Cadastra o livrio no sistema utilizando o codigo do livro como chave.
        livrosCadastrados.put(codigo, livro);
        // Exibe mensagem de sucesso.
        System.out.println("Livro cadastrado com sucesso.");
    }

    /**
     * Metodo que salva os registros em um arquivo.
     * 
     * @param map ({@code HashMap<?, ?>}): Registro a ser salvo em arquivo.
     * @param filename ({@code String}): Nome do arquivo a ser salvo.
     */
    public void salvaArquivo(HashMap<?, ?> map, String filename) {
        // Realiza a abertura do arquivo como saida (caso nao exista, sera criado).
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Salva o objeto construido dentro do arquivo.
            oos.writeObject(map);
            // Exibe mensagem de sucesso.
            System.out.println("Salvo com sucesso como " + filename);
        // Captura excecoes de abertura e fechamento de arquivos.
        } catch (IOException e) {
            // Apenas exibe mensagem de erro.
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    /**
     * Metodo que le um arquivo com o registro e inicializa a biblioteca.
     * 
     * @param filename ({@code String}): Nome do arquivo a ser lido.
     * @param type ({@code String}): Qual o tipo de registro sera lido.
     * 
     * @throws StartLibraryException Caso nao seja possivel realizar o processo,
     * uma excecao sera lancada dizendo que nao ha como iniciar a biblioteca.
     */
    @SuppressWarnings("unchecked") // O tipo foi checado com o parametro type.
    public void leArquivo(String filename, String type) throws StartLibraryException {
        // Realiza a abertura do arquivo como leitura.
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Transforma os dados lidos em objeto.
            Object obj = ois.readObject();
            // Verifica qual o tipo de registro lido.
            if (type.equals("users") && obj instanceof HashMap)
                // Se for usuario, salva no registro de usuarios.
                this.usuariosCadastrados = (HashMap<Long, Usuario>) obj;
            else if (type.equals("books") && obj instanceof HashMap)
                // Se for livro, salva no registro de livros.
                this.livrosCadastrados = (HashMap<Integer, Livro>) obj;
            // Exibe mensagem de sucesso.
            System.out.println("Carregado os dados de " + type + " de " + filename);
        // Captura excecoes de abertura e fechamento de arquivos ou transformacao de dados em objetos.
        } catch (IOException | ClassNotFoundException e) {
            // Lanca a excecao propagando a stack de excecoes.
            throw new StartLibraryException("Ocorreu um erro ao iniciar a biblioteca: " + e.getMessage(), e);
        }
    }

    /**
     * Metodo para emprestar livros.
     * 
     * @param usuario ({@code Usuario}): Usuario que realiza emprestimo.
     * @param livro ({@code Livro}): Livro que sera emprestado.
     * 
     * @throws CopiaNaoDisponivelEx Caso nao exista copias no sistema, lanca excecao.
     */
    public void emprestaLivro(Usuario usuario, Livro livro) throws CopiaNaoDisponivelEx {
        // Realiza o emprestimo no sistema.
        livro.empresta(); 
        // Usa a data atual.
        LocalDate now = LocalDate.now();
        // Adiciona nos historicos do usuario e do livro.
        livro.addUsuarioHist(usuario.getNumCPF(), now, null);
        usuario.addLivroHist(livro.getCodigo(), now, null);
    }

    /**
     * Metodo para devolver livro.
     * 
     * @param usuario ({@code Usuario}): Usuario que devolve o livro.
     * @param livro ({@code Livro}): Livro a ser devolvido.
     * 
     * @throws NenhumaCopiaEmprestadaEx Caso o livro nao esteja emprestado, lanca excecao.
     */
    public void devolveLivro(Usuario usuario, Livro livro) throws NenhumaCopiaEmprestadaEx, UserNotFoundException, BookNotFoundException {
        // Primeiro verifica se ha atraso.
        verificarAtraso(usuario, livro);
        // Devolve o livro no sistema.
        livro.devolve();
        // Usa a data atual para atualizar o historico.
        LocalDate now = LocalDate.now();
        // Atualiza o historico do USUARIO: Encontra o emprestimo aberto desse livro e fecha.
        usuario.finalizarEmprestimo(livro.getCodigo(), now);
        // Atualiza o historico do LIVRO: Encontra o usuario que estava com o livro e fecha.
        livro.finalizarEmprestimo(usuario.getNumCPF(), now);
        // Exibe mensagem de sucesso.
        System.out.println("Livro devolvido com sucesso!");
    }

    /**
     * Metodo para verificar se o emprestimo esta em atraso.
     * 
     * @param usuario ({@code Usuario}): Usuario que realizou emprestimo.
     * @param livro ({@code Livro}): Livro que foi emprestado.
     */
    private void verificarAtraso(Usuario usuario, Livro livro) {
        // Captura a data do emprestimo do livro.
        LocalDate dataEmprestimo = usuario.getDataEmprestimoAtual(livro.getCodigo());
        // Se a data for nula, nao houve nenhum emprestimo para este usuario com este livro.
        if (dataEmprestimo == null) return;
        // Calcula a data prevista.
        LocalDate dataPrevista = dataEmprestimo.plusDays(diasPermitidos);
        // Captura a data de hoje.
        LocalDate hoje = LocalDate.now();
        // Se hoje for depois da data prevista.
        if (hoje.isAfter(dataPrevista)) {
            // Calcula quantos dias se passaram do limite e a data de hoje.
            long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, hoje);
            // Calcula valor da multa.
            double multaTotal = diasAtraso * valorMultaDiaria;
            // Exibe informacoes do atraso.
            System.out.println("\n*********************************");
            System.out.println("[ALERTA] DEVOLUCAO EM ATRASO!");
            System.out.println("Dias de atraso: " + diasAtraso);
            System.out.printf("Multa a pagar: R$ %.2f\n", multaTotal);
            System.out.println("*********************************\n");
        }
    }
    
    /**
     * Metodo para imprimir todos os livros do sistema.
     * 
     * @return {@code String}: Retorna uma String com todos os livros do sistema.
     */
    public String imprimeLivros() {
        // Cria um array list so de chaves.
        ArrayList<Integer> keys = new ArrayList<Integer>(livrosCadastrados.keySet());
        // Ordena o array list.
        Collections.sort(keys);
        // Cria um StringBuilder para facilitar construcao da String.
        StringBuilder sb = new StringBuilder();
        // Realiza um for each para capturar cada livro do sistema.
        for (Integer k : keys)
            // Adiciona os dados dos livros na String utilizando a chave.
            sb.append("\n").append(livrosCadastrados.get(k)).append("\n");
        // Retorna a String construida.
        return sb.toString();
    }
    
    /**
     * Metodo para imprimir todos os usuarios do sistema.
     * 
     * @return {@code String}: Retorna uma String com todos os usuarios do sistema.
     */
    public String imprimeUsuarios() {
        // Cria um array list so de chaves.
        ArrayList<Long> keys = new ArrayList<Long>(usuariosCadastrados.keySet());
        // Ordena o array list.
        Collections.sort(keys);
        // Cria um StringBuilder para facilitar construcao da String.
        StringBuilder sb = new StringBuilder();
        // Realiza um for each para capturar cada usuario do sistema.
        for (Long k : keys)
            // Adiciona os dados dos usuarios na String utilizando a chave.
            sb.append("\n").append(usuariosCadastrados.get(k)).append("\n");
        // Retorna a String construida.
        return sb.toString();
    }
}