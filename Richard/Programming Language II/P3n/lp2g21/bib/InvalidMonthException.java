// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para meses que nao sejam validos ou nao existam.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidMonthException extends RuntimeException {
    // Construtor
    public InvalidMonthException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidMonthException(String message, Throwable cause) {
        super(message, cause);
    }
}
