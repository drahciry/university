// Pacote das classes
package lp2g21.bib;

// Importacoes uteis a classe
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Classe que representa um usuario, uma extensao de pessoa.
 * Possui novos atributos como:
 * Endereco e
 * Historico de Emprestimos.
 */
public class Usuario extends Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * Versao da serializacao da classe para persistencia.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final long serialVersionUID = 1L;
    private String endereco;
    private ArrayList<Emprest> hist = new ArrayList<Emprest>();

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor especializado.
     * Composto pelo construtor especializado + endereco.
     * Historico se inicia vazio.
     * 
     * @param nome ({@code String}): Nome do {@code Usuario}.
     * @param sobreNome ({@code String}): Sobrenome do {@code Usuario}.
     * @param diaNasc ({@code int}): Dia de nascimento do {@code Usuario}.
     * @param mesNasc ({@code int}): Mes de nascimento do {@code Usuario}.
     * @param anoNasc ({@code int}): Ano de nascimento do {@code Usuario}.
     * @param numCPF ({@code String}): Numero de CPF do {@code Usuario}.
     * @param peso ({@code float}): Peso do {@code Usuario}.
     * @param altura ({@code float}): Altura do {@code Usuario}.
     * @param endereco ({@code String}): Endereco do {@code Usuario}
     */
    public Usuario(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String numCPF, float peso, float altura, String endereco) {
        // Utiliza construtor da classe herdada.
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
        setEndereco(endereco);
    }
    
    /**
     * Construtor especializado.
     * Composto pelo construtor especializado + endereco.
     * Historico se inicia vazio.
     * 
     * @param nome ({@code String}): Nome da {@code Usuario}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Usuario}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Usuario}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Usuario}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Usuario}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Usuario}.
     * @param peso ({@code float}): Peso da {@code Usuario}.
     * @param altura ({@code float}): Altura da {@code Usuario}.
     */
    public Usuario(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String numCPF, float peso, float altura, String endereco) {
        // Utiliza construtor da classe herdada.
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
        setEndereco(endereco);
    }
    
    /*************************************************************
     *                         METODOS                           *
    *************************************************************/

    /**
     * Setter para endereco do {@code Usuario}.
     * 
     * @param endereco ({@code String}): Endereco do usuario.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Getter para endereco do {@code Usuario}.
     * 
     * @return {@code String}: Retorna o endereco do usuario.
     */
    public String getEndereco() {
        return this.endereco;
    }

    /**
     * Getter para numero de CPF do {@code Usuario}.
     * 
     * @return {@code long}: Retorna o numero de CPF do usuario como long.
     */
    public long getNumCPF() {
        // Utiliza o metodo da classe herdada.
        return super.getNumCPF();
    }

    /**
     * Retorna a data de emprestimo de um livro que ainda nao foi devolvido.
     * 
     * @return {@code LocalDate}: Retorna a data de emprestimo do livro.
     */
    public LocalDate getDataEmprestimoAtual(int codigoLivro) {
        // Procura o livro no historico.
        for (Emprest e : hist) {
            // Se for o livro certo E ainda não foi devolvido
            if (e.getCodigo() == codigoLivro && e.getDataDevol() == null) {
                return e.getDataEmprest();
            }
        }
        // Não achou emprestimo ativo desse livro
        return null;
    }

    public void addLivroHist(int codigoLivro, LocalDate dataEmprest, LocalDate dataDevol) {
        // Cria o emprestimo.
        Emprest emprestimo = new Emprest(codigoLivro, dataEmprest, dataDevol);
        // Adiciona o emprestimo ao historico do usuario.
        this.hist.add(emprestimo);
    }

    /**
     * Metodo que finaliza o emprestimo.
     * 
     * @param codigoLivro ({@code int}): Codigo do livro a ser devolvido.
     * @param dataDevolucao ({@code LocalDate}): Data de devolucao do livro.
     */
    public void finalizarEmprestimo(int codigoLivro, LocalDate dataDevolucao) throws BookNotFoundException {
        // Busca o livro no historico.
        for (Emprest e : hist) {
            // Verifica se eh o livro certo E se ainda nao foi devolvido (dataDevol eh null).
            if (e.getCodigo() == codigoLivro && e.getDataDevol() == null) {
                e.setDataDevol(dataDevolucao);
                return; // Encontrou e atualizou, pode parar de procurar.
            }
        }
        // Se nao retornou no loop, nao encontrou, entao lanca excecao.
        throw new BookNotFoundException("Livro nao encontrado no historico: o livro nao foi emprestado a este usuario.");
    }
    
    // Metodo toString(), padrao para exibicao de informacao.
    public String toString() {
        // StringBuilder foi utilizado para que seja possivel juntar
        // todas informacoes do usuario junto com o historico.
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nEndereco: ").append(endereco);
        // Se historico esta vazio, nao tenta exibir.
        if (hist.isEmpty())
            return sb.toString();
        sb.append("\n--- Historico ---\n");
        // Passa o historico todo para o StringBuilder utilizando o toString do historico.
        for (Emprest e : hist) {
            sb.append(e.toString()).append("\n");
        }
        // Remove o ultimo pulo de linha.
        sb.deleteCharAt(sb.lastIndexOf("\n"));
        // Apos juntar tudo, retorna as informacoes.
        return sb.toString();
    }

    public ArrayList<Emprest> getHistorico() {
        return this.hist;
    }
}
