// Pacote das classes
package lp2g21.bib;

// Importacoes uteis a classe
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa o emprestimo do livro.
 * Possue atributos como:
 * Codigo do Livro emprestado,
 * Data de Emprestimo do livro,
 * Data de Devolucao do livro.
 */
public class Emprest implements Serializable {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * Versao da serializacao da classe para persistencia.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final long serialVersionUID = 1L;
    private LocalDate dataEmprest;
    private LocalDate dataDevol;
    int codigo;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor basico.
     * Composto de codigo do livro e data de emprestimo do livro.
     * 
     * @param codigo ({@code int}): Codigo do livro emprestado.
     * @param dataEmprest ({@code LocalDate}): Data de emprestimo do livro.
     * @param dataDevol ({@code LocalDate}): Data de devolucao do livro.
    */
    public Emprest(int codigo, LocalDate dataEmprest, LocalDate dataDevol) {
        setCodigo(codigo);
        setDataEmprest(dataEmprest);
        setDataDevol(dataDevol);
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Setter para data de emprestimo do livro.
     * 
     * @param data ({@code LocalDate}): Data de emprestimo do livro.
     */
    private void setDataEmprest(LocalDate data) {
        this.dataEmprest = data;
    }

    /**
     * Setter para data de devolucao do livro.
     * 
     * @param data ({@code LocalDate}): Data de devolucao do livro.
     */
    public void setDataDevol(LocalDate data) {
        this.dataDevol = data;
    }

    /**
     * Setter para codigo do livro emprestado.
     * 
     * @param codigo ({@code int}): Codigo do livro emprestado.
     * @throws InvalidCodeException Caso o codigo seja menor que 1 ou maior que 999,
     * sera lancada uma excecao de codigo invalido.
     */
    private void setCodigo(int codigo) throws InvalidCodeException {
        // Verifica se o codigo eh negativo ou zero.
        if (codigo < 1 || codigo > 999) {
            // Lanca a excecao de codigo invalido.
            throw new InvalidCodeException("Codigo de livro inserido eh invalido.");
        }
        // Atribui o codigo ao emprestimo.
        this.codigo = codigo;
    }

    /**
     * Getter para codigo do livro emprestado.
     * 
     * @return {@code int}: Retorna o Codigo do livro emprestado.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Getter para data de emprestimo do livro.
     * 
     * @return {@code LocalDate}: Retorna a Data do Emprestimo do livro.
     */
    public LocalDate getDataEmprest() {
        return dataEmprest;
    }

    /**
     * Getter para data de devolucao do livro.
     * 
     * @return {@code LocalDate}: Retorna a Data de Devolucao do livro.
     */
    public LocalDate getDataDevol() {
        return dataDevol;
    }

    // Método toString(), padrão para exibição de informação.
    public String toString() {
        // Se a data de devolucao for null, entao a devolucao do livro esta pendente.
        if (dataDevol == null) {
            return (
                "Codigo do Livro: " + codigo +
                "\nData de Emprestimo: " + dataEmprest +
                "\nData de Devolucao: Pendente"
            );    
        }
        // Senao, o livro ja foi devolvido e ha uma data de devolucao.
        return (
            "Codigo do Livro: " + codigo +
            "\nData de Emprestimo: " + dataEmprest +
            "\nData de Devolucao: " + dataDevol
        );
    }
}
