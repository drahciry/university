/**
 * Classe de excecao customizada para titulos que nao existam ou nao sejam validos.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidTitleException extends RuntimeException {
    // Construtor
    public InvalidTitleException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}