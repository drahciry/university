// Importacoes uteis a classe
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
public class Livro {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    private int codigo;
    private String titulo;
    private Categoria categoria;
    private int disponiveis;
    private int emprestados;
    private ArrayList<EmprestadoPara> hist;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor basico.
     * Composto de codigo do livro, titulo do livro, categoria do livro e
     * quantidade disponivel de livro. A quantidade de livros emprestados
     * se inicia em 0 (zero).
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
     * @param dataEmprest ({@code LocalDate}): Data de Emprestimo do livro.
     * @param dataDevol ({@code LocalDate}): Data de Devolucao do livro emprestado.
     * @param numCPF ({@code long}): Numero de CPF do usuario do emprestimo.
     */
    public void addUsuarioList(LocalDate dataEmprest, LocalDate dataDevol, long numCPF) {
        // Instancia um objeto da classe EmprestadoPara que armazena as informacoes do usuario.
        EmprestadoPara emprestadoPara = new EmprestadoPara(dataEmprest, dataDevol, numCPF);
        // Adiciona as informacoes em um historico do livro.
        hist.add(emprestadoPara);
    }
}
