// Pacote das classes
package lp2g21.bib;

// Importacoes uteis a classe
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que representa o livro.
 * Possue atributos como:
 * Codigo do Livro,
 * Titulo do Livro,
 * Categoria do Livro,
 * Quantidade Disponivel,
 * Quantidade Emprestada.
 */
public class Livro implements Serializable {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * Versao da serializacao da classe para persistencia.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final long serialVersionUID = 1L;
    private int codigo;
    private String titulo;
    private Categoria categoria;
    private int disponiveis;
    private int emprestados;
    private ArrayList<EmprestPara> hist = new ArrayList<EmprestPara>();

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor basico.
     * Composto de codigo do livro, titulo do livro, categoria do livro e
     * quantidade disponivel de livro. A quantidade de livros emprestados
     * se inicia em 0 (zero). Historico se inicia vazio.
     * 
     * @param codigo ({@code int}): Codigo do livro.
     * @param titulo ({@code String}): Nome do livro.
     * @param categoria ({@code String}): Categoria do livro.
     * @param disponiveis ({@code int}): Quantidade de livros disponiveis.
     */
    public Livro(int codigo, String titulo, String categoria, int disponiveis) {
        setCodigo(codigo);
        setTitulo(titulo);
        setCategoria(categoria);
        setDisponiveis(disponiveis);
        setEmprestados(0);
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Setter para codigo do livro.
     * 
     * @param codigo ({@code int}): Codigo do livro.
     * 
     * @throws InvalidCodeException Caso o codigo seja menor que 1 ou maior que 999,
     * sera lancada uma excecao de codigo invalido.
     */
    private void setCodigo(int codigo) throws InvalidCodeException {
        // Verifica se o codigo eh negativo ou zero.
        if (codigo < 1 || codigo > 999) {
            // Lanca a excecao de codigo invalido.
            throw new InvalidCodeException("Codigo de livro inserido eh invalido.");
        }
        // Atribui o codigo do livro.
        this.codigo = codigo;
    }
    
    /**
     * Getter para codigo do livro emprestado.
     * 
     * @return {@code int}: Retorna o Codigo do Livro.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Setter para nome do livro.
     * 
     * @param titulo ({@code String}): Nome do Livro.
     * 
     * @exception InvalidTitleException Caso o titulo nao exista ou seja vazio,
     * lanca a excecao de titulo invalido.
     */
    public void setTitulo(String titulo) throws InvalidTitleException {
        // Verifica se o nome eh valido e nao eh vazio.
        if (titulo == null || titulo.trim().isEmpty()) {
            // Se for vazio, lanca uma excecao.
            throw new InvalidTitleException("O nome do livro inserido eh invalido.");
        }
        // Atribui o titulo do livro.
        this.titulo = titulo;
    }

    /**
     * Getter para codigo do livro emprestado.
     * 
     * @return {@code String}: Retorna o Titulo do Livro.
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Setter para categoria do livro.
     * 
     * @param categoria ({@code String}): Categoria do Livro.
     * 
     * @exception InvalidCategoryException Caso a categoria nao exista ou seja vazia,
     * lanca a excecao de categoria invalida.
     */
    public void setCategoria(String categoria) throws InvalidCategoryException {
        // Verifica se a categoria nao eh vazia.
        if (categoria == null || categoria.trim().isEmpty()) {
            // Se for vazia, lanca uma excecao.
            throw new InvalidCategoryException("A categoria do livro inserida eh invalida.");
        }
        // Normaliza a categoria para verificar se eh uma categoria existente.
        String categoriaNormalizada = categoria.toUpperCase();
        // Verifica se a categoria existe.
        Categoria categoryEnum;
        try {
            // Tenta criar uma categoria Enum com as categorias validas.
            categoryEnum = Categoria.valueOf(categoriaNormalizada);
        } catch (IllegalArgumentException e) {
            // Caso nao seja possivel, uma RuntimeException sera lancada pelo metodo. 
            throw new InvalidCategoryException(categoria + " nao eh valido/nao existe.", e);
        }
        // Atribui a categoria do livro.
        this.categoria = categoryEnum;
    }

    /**
     * Getter para codigo do livro emprestado.
     * 
     * @return {@code Categoria}: Retorna a Categoria do Livro.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Setter para quantidade disponivel do livro.
     * 
     * @param categoria ({@code int}): Quantidade disponivel do Livro.
     * 
     * @exception InvalidAvailableException Caso a quantidade de livros disponiveis seja
     * negativa, sera lancada a excecao de quantidade invalida.
     */
    private void setDisponiveis(int disponiveis) throws InvalidAvailableException {
        // Verifica se a quantidade informada eh negativa.
        if (disponiveis < 0) {
            // Se for, lanca uma RuntimeException.
            throw new InvalidAvailableException("Quantidade de livros disponiveis inserida invalida.");
        }
        // Atribui valor a quantidade disponivel.
        this.disponiveis = disponiveis;
    }

    /**
     * Getter para codigo do livro emprestado.
     * 
     * @return {@code int}: Retorna a Quantidade Disponivel do Livro.
     */
    public int getDisponiveis() {
        return disponiveis;
    }

    /**
     * Setter para quantidade emprestada do livro.
     * Este setter sera somente para iniciar a quantidade de emprestados em 0.
     * 
     * @param emprestados ({@code int}): Quantidade emprestada do Livro.
     */
    private void setEmprestados(int emprestados) {
        // Atribui valor a quantidade emprestada.
        this.emprestados = emprestados;
    }

    /**
     * Getter para codigo do livro emprestado.
     * 
     * @return {@code int}: Retorna a Quantidade Emprestada do Livro.
     */
    public int getEmprestados() {
        return emprestados;
    }

    /**
     * Metodo que empresta livros.
     * Atualiza a quantidade de livros disponiveis e quantidade de livros emprestados.
     * 
     * @throws CopiaNaoDisponivelEx Caso nao haja copias disponiveis para emprestimo,
     * sera lancada a excecao de copias nao disponiveis.
     */
    public void empresta() throws CopiaNaoDisponivelEx {
        // Verifica se ha copias para emprestar.
        if (disponiveis == 0) {
            // Se nao houver copias disponiveis, sera lancada uma Checked Exception.
            throw new CopiaNaoDisponivelEx("Nao ha nenhuma copia disponivel para emprestimo.");
        }
        // Se houver copias, decrementa a quantidade de livros disponiveis
        // e incrementa a quantidade de de livros emprestados.
        disponiveis--;
        emprestados++;
    }

    /**
     * Metodo que devolve livros.
     * Atualiza a quantidade de livros disponiveis e quantidade de livros emprestados.
     * 
     * @throws NenhumaCopiaEmprestadaEx Caso nao haja copias emprestadas,
     * sera lancada a excecao de nenhuma copia disponivel.
     */
    public void devolve() throws NenhumaCopiaEmprestadaEx {
        // Verifica se ha copias emprestadas.
        if (emprestados == 0) {
            // Se nao houver copias emprestadas, sera lancada uma Checked Exception.
            throw new NenhumaCopiaEmprestadaEx("Nao ha nenhuma copia emprestada.");
        }
        // Se houver copias emprestadas, incrementa a quantidade de livros disponiveis
        // e decrementa a quantidade de de livros emprestados.
        disponiveis++;
        emprestados--;
    }

    /**
     * Metodo que adiciona as informacoes do emprestimo em um historico.
     * 
     * @param numCPF ({@code long}): Numero de CPF do usuario do emprestimo.
     * @param dataEmprest ({@code LocalDate}): Data de Emprestimo do livro.
     * @param dataDevol ({@code LocalDate}): Data de Devolucao do livro emprestado.
     */
    public void addUsuarioHist(long numCPF, LocalDate dataEmprest, LocalDate dataDevol) {
        // Instancia um objeto da classe EmprestadoPara que armazena as informacoes do usuario.
        EmprestPara emprestadoPara = new EmprestPara(numCPF, dataEmprest, dataDevol);
        // Adiciona as informacoes em um historico do livro.
        this.hist.add(emprestadoPara);
    }

    /**
     * Metodo que finaliza o emprestimo.
     * 
     * @param numCPF ({@code long}): Numero de CPF de quem esta devolvendo.
     * @param dataDevolucao ({@code LocalDate}): Data de devolucao do livro.
     */
    public void finalizarEmprestimo(long numCPF, LocalDate dataDevolucao) throws UserNotFoundException {
        // Busca o usuario no historico.
        for (EmprestPara e : hist) {
            // Verifica se eh o usuario certo E se o livro ainda nao foi devolvido (dataDevol eh null).
            if (e.getNumCPF() == numCPF && e.getDataDevol() == null) {
                e.setDataDevol(dataDevolucao);
                return; // Encontrou e atualizou, pode parar de procurar.
            }
        }
        // Se nao retornou no loop, nao encontrou, entao lanca excecao.
        throw new UserNotFoundException("Usuario nao encontrado no historico: o usuario nao realizou emprestimo deste livro.");
    }

    // Metodo toString(), padrao para exibicao de informacao.
    public String toString() {
        // StringBuilder foi utilizado para que seja possivel juntar
        // todas informacoes do livro junto com o historico.
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo do Livro: ").append(codigo);
        sb.append("\nTitulo: ").append(titulo);
        sb.append("\nCategoria: ").append(categoria.getCategoria());
        sb.append("\nDisponiveis: ").append(disponiveis);
        sb.append("\nEmprestados: ").append(emprestados);
        // Se historico esta vazio, nao tenta exibir.
        if (hist.isEmpty())
            return sb.toString();
        sb.append("\n--- Historico ---");
        // Passa o historico todo para o StringBuilder utilizando o toString do historico.
        for (EmprestPara e : hist) {
            sb.append(e.toString()).append("\n");
        }
        // Remove o ultimo pulo de linha.
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        // Apos juntar tudo, retorna as informacoes.
        return sb.toString();
    }

    public ArrayList<EmprestPara> getHistorico() {
        return this.hist;
    }
}
