/**
 * Enumerate para mes. Cada mes possui um valor inteiro associado,
 * que vai de 1 a 12, na mesma ordem em que os meses decorrem.
 */
public enum Mes {
    /*************************************************************
     *                     INSTANCIAS DO ENUM                    *
    *************************************************************/

    JANEIRO(1),
    FEVEREIRO(2),
    MARCO(3),
    ABRIL(4),
    MAIO(5),
    JUNHO(6),
    JULHO(7),
    AGOSTO(8),
    SETEMBRO(9),
    OUTUBRO(10),
    NOVEMBRO(11),
    DEZEMBRO(12);

    /*************************************************************
     *                         ATRIBUTOS                         *
    *************************************************************/

    /**
     * Constante que armazenara o valor inteiro relativo ao mes apos ser instanciado.
     * Armazena os valores de 1 a 12.
     */
    private final int mesInteiro;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor PRIVADO. Nao pode ser instanciado com new.
     * 
     * @param mes (int): Valor inteiro do mes informado. 
     */
    private Mes (int mes) {
        this.mesInteiro = mes;
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/
    /**
     * Metodo getter para obter qual o valor inteiro relativo ao mes.
     * 
     * @return int: Retorna um valor inteiro no intervalo de 1 a 12, relativo ao mes.
     */
    public int getMesInteiro() {
        return mesInteiro;
    }
}