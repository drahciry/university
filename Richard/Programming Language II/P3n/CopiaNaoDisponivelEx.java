/**
 * Classe de excecao customizada para quando nao houver copias disponiveis.
 * 
 * Esta classe eh uma "Checked Exception", ou seja, voce deve possui a obrigacao de trata-la.
 */
public class CopiaNaoDisponivelEx extends Exception {
    // Construtor
    public CopiaNaoDisponivelEx(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public CopiaNaoDisponivelEx(String message, Throwable cause) {
        super(message, cause);
    }
}