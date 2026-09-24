// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para quantidades disponiveis invalidas.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidAvailableException extends RuntimeException {
    // Construtor
    public InvalidAvailableException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}