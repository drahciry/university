// Pacote das classes
package lp2g21.bib;

/**
 * Enumerate para categoria de livro.
 */
public enum Categoria {
    /*************************************************************
     *                     INSTANCIAS DO ENUM                    *
    *************************************************************/

    AVENTURA("Aventura"),
    ROMANCE("Romance"),
    FICCAO("Ficcao"),
    COMEDIA("Comedia"),
    SUSPENSE("Suspense"),
    TERROR("Terror");

    /*************************************************************
     *                         ATRIBUTOS                         *
    *************************************************************/

    /**
     * Constante que armazenara a categoria do livro apos ser instanciado.
     */
    private final String categoria;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor PRIVADO. Nao pode ser instanciado com new.
     * 
     * @param categoria (String): String que sera a categoria. 
     */
    private Categoria(String categoria) {
        this.categoria = categoria;
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Metodo getter para obter a categoria do livro.
     * 
     * @return {@code String}: Retorna a categoria.
     */
    public String getCategoria() {
        return categoria;
    }
}
