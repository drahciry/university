public abstract class PessoaIMC extends Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    private float peso;
    private float altura;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor básico com peso e altura.
     * Composto pelo construtor base + Peso e Altura.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public PessoaIMC(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
        setPeso(peso);
        setAltura(altura);
    }

    /**
     * Construtor básico com peso e altura.
     * Composto pelo construtor base + Peso e Altura.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public PessoaIMC(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
        setPeso(peso);
        setAltura(altura);
    }

    /**
     * Construtor especializado com peso e altura.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Pessoa}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public PessoaIMC(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String numCPF, float peso, float altura) {
        super(sobreNome, sobreNome, numCPF, nome, sobreNome, numCPF);
        setPeso(peso);
        setAltura(altura);
    }

    /**
     * Construtor especializado com peso e altura.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Pessoa}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public PessoaIMC(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String numCPF, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF);
        setPeso(peso);
        setAltura(altura);
    }
    
    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Setter para peso da {@code Pessoa}.
     * 
     * @param peso ({@code float}): Peso da pessoa.
     * 
     * @throws InvalidWeightException Caso o peso seja um valor negativo,
     * retorna uma Runtime Exception.
     */
    public void setPeso(float peso) throws InvalidWeightException {
        // Verifica se o peso eh negativo.
        if (peso <= 0)
            // Lanca excecao de peso invalido.
            throw new InvalidWeightException(peso + " nao eh valido. Peso deve ser um valor positivo.");
        this.peso = peso;
    }

    /**
     * Getter para peso da {@code Pessoa}.
     * 
     * @return {@code float}: Retorna um float com o peso da pessoa.
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Setter para altura da {@code Pessoa}.
     * 
     * @param altura ({@code float}): Altura da pessoa.
     * 
     * @throws InvalidHeightException Caso a altura seja menor que 0.40m,
     * sera lancada uma Runtime Exception. Essa escolha foi arbitraria e pode ser alterada.
     */
    public void setAltura(float altura) throws InvalidHeightException {
        if (altura < 0.40)
            throw new InvalidHeightException(altura + " nao eh valida. Altura deve ser um valor acima de 0.40m.");
        this.altura = altura;
    }

    /**
     * Getter para altura da {@code Pessoa}.
     * 
     * @return {@code float}: Retorna um float com a altura da pessoa.
     */
    public float getAltura() {
        return altura;
    }

    /**
     * Metodo para calcular o IMC da pessoa.
     * 
     * @return {@code float}: Retorna um float correspondente ao IMC.
     */
    public float calculaIMC() {
        return peso / (altura * altura);
    }
    
    // Metodo toString(), padrao para exibicao de informacao.
    public String toString() {
        // Reutiliza o toString() da classe herdada.
        return super.toString() + "\nPeso: " + peso + "\nAltura: " + altura;
    }

    /*************************************************************
     *                     METODOS ABSTRATOS                     *
    *************************************************************/

    // Metodo abstrato que retornara a classificacao do IMC, de acordo com o sexo.
    public abstract String resultIMC();

}
