package lp2g04.bib;

// bibliotecas utilizadas
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.io.Serializable; // Boa pratica

public class Emprest implements Serializable {
    // ATRIBUTOS
    private GregorianCalendar dataEmprestimo; // Data da locacao
    private GregorianCalendar dataDevolucao; // (null se pendente)
    private int codLivro; // Codigo do livro

    // CONSTRUTOR
    public Emprest(GregorianCalendar dataEmprestimo, int codLivro) {
        // Atribuicoes
        this.dataEmprestimo = dataEmprestimo;
        this.codLivro = codLivro;
        this.dataDevolucao = null; // Pendente
    }

    // METODOS GETTERS
    public GregorianCalendar getDataEmprestimo() { return dataEmprestimo; }
    public GregorianCalendar getDataDevolucao() { return dataDevolucao; }
    public int getCodLivro() { return codLivro; }

    // METODOS DE CONTROLE
    // Metodo para registrar a data de devolucao
    public void setDataDevol(GregorianCalendar dataDevolucao) {
        // Validacao basica
        if (dataDevolucao != null && dataDevolucao.before(dataEmprestimo)) {
            // lanca a excecao
            throw new IllegalArgumentException("A data de devolucao nao pode ser anterior a data de emprestimo.");
        }
        this.dataDevolucao = dataDevolucao;
    }

    // Verifica se o livro esta pendente
    public boolean isPendente() { return this.dataDevolucao == null; }

    // TO STRING
    // Sobrescreve o toString() para mostrar todas as informacoes
    @Override
    public String toString() {
        // Formata a data para DD/MM/AAAA
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Formata a data de emprestimo
        String dataEmpStr = sdf.format(dataEmprestimo.getTime());
        
        String dataDevolStr;
        
        // Verifica se a dataDevolucao eh null (pendente)
        if (dataDevolucao == null) dataDevolStr = "Pendente";
        else dataDevolStr = sdf.format(dataDevolucao.getTime());

        // Concatenacao
        String output = " [CodLivro: " + codLivro + "]";
        output += " Emprestimo: " + dataEmpStr;
        output += " | Devolucao: " + dataDevolStr;
        
        return output;
    }
}