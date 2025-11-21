/**
 * Classe de excecao customizada para quando nao houver copias emprestadas.
 * 
 * Esta classe eh uma "Checked Exception", ou seja, voce deve possui a obrigacao de trata-la.
 */
public class NenhumaCopiaEmprestadaEx extends Exception {
    // Construtor
    public NenhumaCopiaEmprestadaEx(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public NenhumaCopiaEmprestadaEx(String message, Throwable cause) {
        super(message, cause);
    }
}