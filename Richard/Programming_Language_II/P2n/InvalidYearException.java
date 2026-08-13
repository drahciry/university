/**
 * Classe de excecao customizada para anos que nao sejam validos ou nao existam.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidYearException extends RuntimeException {
    // Construtor
    public InvalidYearException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidYearException(String message, Throwable cause) {
        super(message, cause);
    }
}
