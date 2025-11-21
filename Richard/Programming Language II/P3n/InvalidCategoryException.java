/**
 * Classe de excecao customizada para categorias que nao existam ou nao sejam validos.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidCategoryException extends RuntimeException {
    // Construtor
    public InvalidCategoryException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}