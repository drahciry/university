// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para caso nao seja possivel inicializar biblioteca.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class StartLibraryException extends RuntimeException {
    // Construtor
    public StartLibraryException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public StartLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}

