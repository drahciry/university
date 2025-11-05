/**
 * Classe que representa uma mulher, extensão de uma pessoa.
 * Possue atributos como:
 * Nome,
 * Sobrenome,
 * Data de Nascimento,
 * Numero de CPF,
 * Peso,
 * Altura e
 * Sexo.
 */
public class Mulher extends Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * Constante que armazena o sexo da pessoa.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final String SEXO = "Feminino";

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome da {@code Mulher}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Mulher}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Mulher}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Mulher}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Mulher}.
     */
    public Mulher(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome da {@code Mulher}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Mulher}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Mulher}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Mulher}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Mulher}.
     */
    public Mulher(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome da {@code Mulher}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Mulher}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Mulher}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Mulher}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Mulher}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Mulher}.
     * @param peso ({@code float}): Peso da {@code Mulher}.
     * @param altura ({@code float}): Altura da {@code Mulher}.
     */
    public Mulher(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String numCPF, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome da {@code Mulher}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Mulher}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Mulher}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Mulher}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Mulher}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Mulher}.
     * @param peso ({@code float}): Peso da {@code Mulher}.
     * @param altura ({@code float}): Altura da {@code Mulher}.
     */
    public Mulher(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String numCPF, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Getter que retorna o sexo da pessoa
     * @return {@Code}: Retorna uma String com o genero da {@code Pessoa}.
     */
    public String getSexo() {
        return SEXO;
    }

    // Método toString(), padrão para exibição de informação.
    public String toString() {
        return super.toString() + "\nGenero: " + SEXO;
    }
}
