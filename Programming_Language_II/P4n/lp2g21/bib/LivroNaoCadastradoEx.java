// Pacote das classes
package lp2g21.bib;

/**
 * Classe de excecao customizada para quando houver uma tentativa de
 * acessar um livro nao cadastrado na biblioteca.
 * 
 * Esta classe eh uma "Checked Exception", ou seja, voce deve possui a obrigacao de trata-la.
 */
public class LivroNaoCadastradoEx extends Exception {
    // Construtor
    public LivroNaoCadastradoEx(String message) {
        super(message);
    }
    // Construtor com anexacao de excecoes
    public LivroNaoCadastradoEx(String message, Throwable cause) {
        super(message, cause);
    }
}