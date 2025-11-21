// Importacoes uteis a classe
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;

/**
 * Classe que representa uma pessoa.
 * Possue atributos como:
 * Nome,
 * Sobrenome,
 * Data de Nascimento,
 * Numero de CPF,
 * Peso e
 * Altura.
 */
public class Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/
    
    /**
     * Regex para validar nome e sobrenome.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final Pattern NOME_VALIDO = Pattern.compile("^[\\p{L}\\s]+$");
    private static int numPessoas = 0;
    private String nome;
    private String sobreNome;
    private LocalDate dataNasc;
    private long numCPF = 0;
    private float peso = 0;
    private float altura = 0;

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Pessoa}.
     */
    public Pessoa(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc) {
        setNome(nome);
        setSobreNome(sobreNome);
        setDataNasc(diaNasc, mesNasc, anoNasc);
        numPessoas++;
    }

    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Pessoa}.
     */
    public Pessoa(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc) {
        setNome(nome);
        setSobreNome(sobreNome);
        setDataNasc(diaNasc, mesNasc, anoNasc);
        numPessoas++;
    }

    /**
     * Construtor especializado.
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
    public Pessoa(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String numCPF, float peso, float altura) {
        setNome(nome);
        setSobreNome(sobreNome);
        setDataNasc(diaNasc, mesNasc, anoNasc);
        setNumCPF(numCPF);
        setPeso(peso);
        setAltura(altura);
        numPessoas++;
    }

    /**
     * Construtor especializado.
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
    public Pessoa(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String numCPF, float peso, float altura) {
        setNome(nome);
        setSobreNome(sobreNome);
        setDataNasc(diaNasc, mesNasc, anoNasc);
        setNumCPF(numCPF);
        setPeso(peso);
        setAltura(altura);
        numPessoas++;
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Setter para nome da {@code Pessoa}.
     * 
     * @param nome ({@code String}): Nome da pessoa.
     * 
     * @throws InvalidNameException Caso nome da pessoa possua caracteres invalidos,
     * uma Runtime Exception sera lancada.
     */
    public void setNome(String nome) throws InvalidNameException {
        // Verifica se o nome informado nao eh uma string nula, vazia ou se nao bate com regex.
        if (nome == null || nome.trim().isEmpty() || !NOME_VALIDO.matcher(nome).matches())
            // Lanca excecao de nome invalido.
            throw new InvalidNameException(
                "O nome \"" + nome + "\" nao pode ser utilizado (deve conter apenas letras e espaco)."
            );
        // Atribui valor ao atributo, caso nao tenha lancado erro.
        this.nome = nome;
    }

    /**
     * Getter para nome da {@code Pessoa}.
     * 
     * @return {@code String}: Retorna uma String carregada com o nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter para sobrenome da {@code Pessoa}.
     * 
     * @param sobreNome ({@code String}): Sobrenome da pessoa.
     * 
     * @throws InvalidSurnameException Caso sobrenome da pessoa possua caracteres invalidos,
     * uma Runtime Exception sera lancada.
     */
    public void setSobreNome(String sobreNome) throws InvalidSurnameException {
        // Verifica se o sobrenome informado nao eh uma string nula, vazia ou se nao bate com regex.
        if (sobreNome == null || sobreNome.trim().isEmpty() || !NOME_VALIDO.matcher(sobreNome).matches())
            // Lanca excecao de nome invalido.
            throw new InvalidSurnameException(
                "O sobrenome \"" + sobreNome + "\" nao pode ser utilizado (deve conter apenas letras e espacos)."
            );
        // Atribui valor ao atributo, caso nao tenha lancado erro.
        this.sobreNome = sobreNome;
    }

    /**
     * Getter para sobrenome da {@code Pessoa}.
     * 
     * @return {@code String}: Retorna uma String carregada com o sobrenome da pessoa.
     */
    public String getSobreNome() {
        return sobreNome;
    }

    /**
     * Setter para data de nascimento da {@code Pessoa}.
     * 
     * @param dia ({@code int}): Dia do nascimento da {@code Pessoa}.
     * @param mes ({@code int}): Mes do nascimento da {@code Pessoa}.
     * @param ano ({@code int}): Ano do nascimento da {@code Pessoa}.
     * 
     * @throws InvalidDataException Caso a data nao seja uma data valida,
     * como dias inexistentes em certos meses ou datas negativas,
     * sera lancada uma Runtime Exception.
     */
    public void setDataNasc(int dia, int mes, int ano) throws InvalidDataException {
        // Verifica se a data eh valida com metodo estatico da classe ValidaData.
        if (!ValidaData.isData(dia, mes, ano))
            // Lanca excecao de data invalida.
            throw new InvalidDataException(dia + "/" + mes + "/" + ano + " nao eh uma data valida.");
        // Atribui valor ao atributo, caso nao tenha lancado erro.
        dataNasc = LocalDate.of(ano, mes, dia);
    }

    /**
     * Setter para data de nascimento da {@code Pessoa}.
     * 
     * @param dia ({@code String}): Dia do nascimento da {@code Pessoa}.
     * @param mes ({@code String}): Mes do nascimento da {@code Pessoa}.
     * @param ano ({@code String}): Ano do nascimento da {@code Pessoa}.
     * 
     * @throws InvalidDataException Caso a data nao seja uma data valida,
     * como dias inexistentes em certos meses, datas negativas ou
     * mes escrito por extenso errado, sera lancada uma Runtime Exception.
     */
    public void setDataNasc(String dia, String mes, String ano) throws InvalidDataException {
        // Verifica se a data eh valida com metodo estatico da classe ValidaData.
        // O metodo pode lancar uma excecao, entao todas excecoes sao tradas
        // para lancar uma nova excecao mais geral, informando a origem com Throwable.
        try {
            // Aqui, se a data for invalida, lanca excecao.
            if (!ValidaData.isData(dia, mes, ano))
                throw new InvalidDataException(dia + "/" + mes + "/" + ano + " nao eh uma data valida.");
        // Aqui, caso o dia seja invalido, lanca excecao.
        } catch (InvalidDayException e1) {
            throw new InvalidDataException(dia + " nao eh um dia valido.", e1);
        // Aqui, caso o mes seja invalido, lanca excecao.
        } catch (InvalidMonthException e2) {
            throw new InvalidDataException(mes + " nao eh um mes valido.", e2);
        // Aqui, caso o ano seja invalido, lanca excecao.
        } catch (InvalidYearException e3) {
            throw new InvalidDataException(ano + " nao eh um ano valido.", e3);
        }
        // Converte valores de String para int.
        int diaInteiro = Integer.parseInt(dia);
        int anoInteiro = Integer.parseInt(ano);
        int mesInteiro;
        try {
            // Primeiro, tenta parsear a String.
            mesInteiro = Integer.parseInt(mes);
        } catch (NumberFormatException e) {
            // Se não der certo, tenta converter um possivel mes escrito por extenso.
            // Se nao for um mes valido, uma excecao sera lancada, mas aqui a propagacao sera respeitada.
            mesInteiro = ValidaData.converteMes(mes);
        }
        // Atribui valor ao atributo, caso nao tenha lancado erro.
        dataNasc = LocalDate.of(anoInteiro, mesInteiro, diaInteiro);
    }

    /**
     * Getter para data de nascimento da {@code Pessoa}.
     * 
     * @return {@code LocalDate}: Retorna um objeto LocalDate com a data de nascimento.
     */
    public LocalDate getDataNasc() {
        return dataNasc;
    }

    /**
     * Setter para numero de CPF da {@code Pessoa}.
     * 
     * @param numCPF ({@code long}): Numero de CPF da pessoa.
     * 
     * @exception InvalidCpfException Caso o CPF nao seja valido,
     * lanca uma Runtime Exception.
     */
    public void setNumCPF(String numCPF) {
        // Valida o CPF informado com metodo estatico da classe ValidaCPF.
        if (!ValidaCPF.isCPF(numCPF))
            // Lanca excecao de CPF invalido.
            throw new InvalidCpfException(numCPF + " nao eh um CPF valido.");
        // Atribui valor ao atributo, caso nao tenha lancado erro.
        this.numCPF = ValidaCPF.toLong(numCPF);
    }

    /**
     * Getter para numero de CPF da {@code Pessoa}.
     * 
     * @return {@code long}: Retorna um long com o numero de CPF da pessoa.
     */
    public long getNumCPF() {
        return numCPF;
    }

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
     * Getter para idade da {@code Pessoa}.
     * 
     * @return {@code int}: Retorna um int com a idade da pessoa.
     */
    public int getIdade() {
        // Retorna realizando um calculo entre o periodo atual e o periodo informado.
        return Period.between(this.dataNasc, LocalDate.now()).getYears();
    }

    /**
     * Getter para numero de objetos da classe {@code Pessoa} instanciados.
     * 
     * @return {@code int}: Retorna um int com o numero de pessoas instanciadas.
     */
    public static int getNumPessoas() {
        return numPessoas;
    }

    // Método toString(), padrão para exibição de informação.
    public String toString() {
        // Se o CPF for 0, o construtor especializado nao foi utilizado.
        if (numCPF == 0)
            return (
                "Nome: " + nome + " " + sobreNome +
                "\nIdade: " + getIdade() +
                "\nData de Nascimento: " + dataNasc.toString() +
                "\nCPF: Nao informado" +
                "\nPeso: Nao informado" +
                "\nAltura: Nao informado"
            );
        // O construtor especializado foi utilizado.
        return (
            "Nome: " + nome + " " + sobreNome +
            "\nIdade: " + getIdade() +
            "\nData de Nascimento: " + dataNasc.toString() +
            // Utiliza o metodo estatico format() da classe String para que o CPF tenha sempre 11 digitos.
            "\nCPF: " + ValidaCPF.toString(String.format("%011d", numCPF)) +
            "\nPeso: " + peso +
            "\nAltura: " + altura 
        );
    }
}
