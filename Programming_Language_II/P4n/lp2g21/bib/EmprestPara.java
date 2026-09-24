// Pacote das classes
package lp2g21.bib;

// Importacoes uteis a classe
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que armazena informacoes do usuario que realizou emprestimo de livro.
 * Possui atributos como:
 * Data de Emprestimo do Livro,
 * Data de Devolucao do Livro,
 * Numero de CPF do Usuario.
 */
public class EmprestPara implements Serializable {
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
    private long numCPF;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor basico.
     * Composto de data de emprestimo do livro, data de devolucao do livro
     * e numero de CPF do usuario.
     * 
     * @param numCPF ({@code long}): Numero de CPF do Usuario.
     * @param dataEmprest ({@code LocalDate}): Data de Emprestimo do Livro.
     * @param dataDevol ({@code LocalDate}): Data de Devolucao do Livro.
     */
    public EmprestPara(long numCPF, LocalDate dataEmprest, LocalDate dataDevol) {
        setNumCPF(numCPF);
        setDataEmprest(dataEmprest);
        setDataDevol(dataDevol);
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Setter para data de emprestimo do livro.
     * 
     * @param dataEmprest ({@code LocalDate}): Data de Emprestimo do Livro.
     */
    private void setDataEmprest(LocalDate dataEmprest) {
        this.dataEmprest = dataEmprest;
    }

    /**
     * Getter para data de emprestimo do livro.
     * 
     * @return {@code LocalDate}: Data de Emprestimo do Livro.
     */
    public LocalDate getDataEmprest() {
        return dataEmprest;
    }

    /**
     * Setter para data de devolucao do livro.
     * 
     * @param dataDevol ({@code LocalDate}): Data de Devolucao do Livro.
     */
    public void setDataDevol(LocalDate dataDevol) {
        this.dataDevol = dataDevol;
    }

    /**
     * Getter para data de devolucao do livro.
     * 
     * @return {@code LocalDate}: Data de Devolucao do Livro.
     */
    public LocalDate getDataDevol() {
        return dataDevol;
    }

    /**
     * Setter para numero de CPF do usuario.
     * 
     * @param numCPF ({@code long}): Numero de CPF do Usuario.
     */
    private void setNumCPF(long numCPF) {
        this.numCPF = numCPF;
    }

    /**
     * Getter para numero do CPF do usuario.
     * 
     * @return {@code long}: Numero de CPF do Usuario.
     */
    public long getNumCPF() {
        return numCPF;
    }

    // Método toString(), padrão para exibição de informação.
    public String toString() {
        // Se a data de devolucao for null, entao a devolucao do livro esta pendente.
        if (dataDevol == null) {
            return (
                "Usuario: " + numCPF +
                "\nData de Emprestimo: " + dataEmprest +
                "\nData de Devolucao: Pendente"
            );    
        }
        // Senao, o livro ja foi devolvido e ha uma data de devolucao.
        return (
            "\nUsuario: " + numCPF +
            "\nData de Emprestimo: " + dataEmprest +
            "\nData de Devolucao: " + dataDevol
        );
    }
}
