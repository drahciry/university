/**
 * Classe de excecao customizada para codigos de livros que sejam invalidos.
 * Para que um codigo seja invalido, o codigo deve ser menor que 1 ou maior que 999.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidCodeException extends RuntimeException {
    // Construtor
    public InvalidCodeException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
