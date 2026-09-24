// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para usuarios nao encontrados no historico.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class UserNotFoundException extends RuntimeException {
    // Construtor
    public UserNotFoundException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}