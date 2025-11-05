/**
 * Classe de excecao customizada para alturas que nao sejam validas ou nao existam.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidHeightException extends RuntimeException {
    // Construtor
    public InvalidHeightException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidHeightException(String message, Throwable cause) {
        super(message, cause);
    }
}
