// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para quando houver uma tentativa de
 * acessar um usuario nao cadastrado na biblioteca.
 * 
 * Esta classe eh uma "Checked Exception", ou seja, voce deve possui a obrigacao de trata-la.
 */
public class UsuarioNaoCadastradoEx extends Exception {
    // Construtor
    public UsuarioNaoCadastradoEx(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public UsuarioNaoCadastradoEx(String message, Throwable cause) {
        super(message, cause);
    }
}