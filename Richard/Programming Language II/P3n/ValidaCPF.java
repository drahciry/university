// Importacoes uteis a classe
import java.util.regex.Pattern;

/**
 * Classe com metodos estaticos voltados para a validacao de CPF, com metodos como:
 * Validar o formato do CPF, validar se os digitos verifadores sao validos, retornar o CPF
 * como um valor numerico (sem caracteres especiais) e formatacao de CPF usando mascara.
 */
public final class ValidaCPF {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * Regex para digitos repetidos de um CPF.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final Pattern REGEX_DIGITO_REPETIDO = Pattern.compile("(\\d)\\1{10}");
    /**
     * Regex para formato do CPF, utilizando ou nao simbolos especiais.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final Pattern REGEX_CPF = Pattern.compile("(^\\d{11}$)|(^\\d{9}[-/]\\d{2}$)|(^\\d{3}\\.\\d{3}\\.\\d{3}[-/]\\d{2}$)");

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor PRIVADO. Nao pode ser instanciado com new.
     */
    private ValidaCPF() {
        // Apenas para que nao seja possivel instanciar um objeto de uma classe de ajuda.
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Metodo estatico que recebe a String CPF e remove todos os simbolos que nao sejam digitos.
     * 
     * @param cpf (String): String carregada com o CPF que sera usado para remover os simbolos especiais.
     * 
     * @return String: Retorna uma nova String somente com digitos.
     */
    private static String cpfSanitizado(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    /**
     * Metodo estatico que verifica se o formato do CPF eh valido. 
     *
     * @param cpf (String): String carregada com o CPF que sera validado.
     *
     * @return boolean: Retorna um valor booleano de acordo com a validacao realizada.
     */
    private static boolean validaFormato(String cpf) {
        // Primeira verificacao: digitos repetidos.
        // Para facilitar a verificacao de digitos repetidos, uma instancia
        // auxiliar eh criada, sem a presenca de ".", "-" ou "/".
        String cpfAuxiliar = cpfSanitizado(cpf);
        // Este regex ira vericar se a entrada possui 11 digitos iguais,
        // poupando tempo de processamento para calcular o digito verificador,
        // pois CPFs que tenham digitos repetidos sao "reservados" por serem obvios.
        // Retorna falso caso o CPF numerico de match com o regex.
        if (REGEX_DIGITO_REPETIDO.matcher(cpfAuxiliar).matches()) return false;

        // Segunda verificacao: formato do CPF e tamanho da entrada.
        // Se o formato do CPF nao estiver nos seguintes casos:
        // DDDDDDDDDDD | DDD.DDD.DDD-DD | DDD.DDD.DDD/DD | DDDDDDDDD-DD | DDDDDDDDD/DD
        // para X pertencente ao intervalo inteiro [0, 9], o formato do CPF esta invalido.
        // Perceba que em todos os casos, o tamanho está correto.
        // Retorna verdadeiro caso o CPF esteja no padrao dos formatos validos. Caso contrario, retorna falso.
        return REGEX_CPF.matcher(cpf).matches();
    }

    /**
     * Metodo que calcula o digito verificador de acordo com o tamanho da entrada.
     * 
     * @param base (String): Uma substring de CPF de tamanho 9 ou 10.
     * 
     * @return char: Retorna um char correspondente ao digito verificador.
     */
    private static char calcularDigitoVerificador(String base) {
        // soma armazenara a soma de (digitos * pesos).
        int soma = 0;
        // peso armazenara o peso utilizado para o calculo da soma.
        // O peso comeca com o tamanho do parametro base + 1.
        // Se o tamanho de base for 9, peso sera 10.
        // Se o tamanho de base for 10, peso sera 11.
        // Desta forma, o peso sera decrementado ate chegar a 2.
        int peso = base.length() + 1;
        // Laco para recuperar algarismos do CPF um a um ate o nono digito.
        for (int i = 0; i < base.length(); i++) {
            // Armazena o valor do digito realizando a conversao de char para int
            // utilizando a classe Character e seu metodo de classe getNumericValue().
            int num = Character.getNumericValue(base.charAt(i));
            // Incrementa a soma com a operacao num * peso.
            soma += (num * peso);
            // Decrementa o peso em 1.
            peso--;
        }
        
        // resto armazenara a operacao do digito verificador (11 - resto mod soma).
        int resto = 11 - (soma % 11);
        // Realiza conversao int para char utilizando a classe Character e seu metodo de classe forDigit().
        // Se o resto for 10 ou 11, o digito verificador sera 0.
        if ((resto == 10) || (resto == 11)) return Character.forDigit(0, 10);
        // Senao, sera o proprio resto.
        return Character.forDigit(resto, 10);
    }

    /**
     * Metodo estatico que verifica se o formato e a composicao do CPF sao validos. 
     * 
     * @param cpf (String): String carregada com o CPF que sera validado.
     *
     * @return boolean: Retorna um valor booleano de acordo com a validacao realizada.
     */
    public static boolean isCPF(String cpf) {
        // Primeiro valida o formato do CPF. Se o formato for invalido, retorna falso imediatamente.
        if (!validaFormato(cpf)) return false;

        // Instancia auxiliar sem a presenca de ".", "-" ou "/" para utilizar no calculo dos digitos verificadores.
        String cpfAuxiliar = cpfSanitizado(cpf);
        // Utiliza o CPF sanitizado (sem simbolos especiais) para calcular os digitos verificadores.
        char dig10 = calcularDigitoVerificador(cpfAuxiliar.substring(0, 9));
        char dig11 = calcularDigitoVerificador(cpfAuxiliar.substring(0, 10));
        
        // Se os digitos verificadores calculados forem iguais aos digitos verificadores do CPF informado, entao o CPF eh valido.
        return ((dig10 == cpfAuxiliar.charAt(9)) && (dig11 == cpfAuxiliar.charAt(10)));
    }

    /**
     * Metodo estatico que recebe um CPF como String e retorna como long.
     * 
     * @param cpf (String): String carregada com o CPF que sera convertido para long.
     * 
     * @return long: String cpf convertida para long.
     * 
     * @exception InvalidCpfException Caso o cpf (String) nao possua o formato valido,
     * sera lancado uma excecao informado que o formato eh invalido.
     */
    public static long toLong(String cpf) throws InvalidCpfException {
        // Realiza validacao do CPF. Caso o CPF seja invalido, uma excecao customizada sera lancada.
        if (!isCPF(cpf)) throw new InvalidCpfException("O CPF inserido nao eh valido!");
        // Instancia auxiliar sem a presenca de ".", "-" ou "/" para conversao correta para long.
        String cpfAuxiliar = cpfSanitizado(cpf);
        // Retorna um long usando o metodo parseLong da classe Long, correspondente ao tipo long.
        return Long.parseLong(cpfAuxiliar);
    }

    /**
     * Metodo estatico que recebe um CPF e retorna o CPF formatado.
     * O CPF precisa já estar validado.
     * 
     * @param cpf (String): String carregada com o CPF, que sera validade e formatado.
     * 
     * @return String: String cpf convertida para long.
     */
    public static String toString(String cpf) {
        // Instancia auxiliar sem a presenca de ".", "-" ou "/" para fatiamento correto e preciso da String do CPF.
        String cpfAuxiliar = cpfSanitizado(cpf);
        return (cpfAuxiliar.substring(0, 3) + "." + 
                cpfAuxiliar.substring(3, 6) + "." +
                cpfAuxiliar.substring(6, 9) + "-" + 
                cpfAuxiliar.substring(9, 11));
    }
}