/**
 * Classe de excecao customizada para CPFs que possuam um formato invalido
 * ou que tenha digitos verificadores incorretos.
 * 
 * Esta classe eh uma "Runtime Exception", ou seja, voce deve nao possui a obrigacao de
 * trata-la, mas a nao verificacao eh por conta em risco.
 */
public class InvalidCpfException extends RuntimeException {
    // Construtor
    public InvalidCpfException(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public InvalidCpfException(String message, Throwable cause) {
        super(message, cause);
    }
}