// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para chaves de hash tables já utilizadas.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidKeyException extends RuntimeException {
    // Construtor
    public InvalidKeyException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
