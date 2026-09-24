// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para livros nao encontrados no historico.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class BookNotFoundException extends RuntimeException {
    // Construtor
    public BookNotFoundException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}