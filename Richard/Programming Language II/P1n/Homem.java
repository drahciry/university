/**
 * Classe que representa um homem, extensão de uma pessoa.
 * Possue atributos como:
 * Nome,
 * Sobrenome,
 * Data de Nascimento,
 * Numero de CPF,
 * Peso,
 * Altura e
 * Sexo.
 */
public class Homem extends Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * Constante que armazena o sexo da pessoa.
     * Atributo privado, estatico e constante, nao podendo ser alterado e nem acessado de fora da classe.
     */
    private static final String SEXO = "Masculino";

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/
    
    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome do {@code Homem}.
     * @param sobreNome ({@code String}): Sobrenome do {@code Homem}.
     * @param diaNasc ({@code int}): Dia de nascimento do {@code Homem}.
     * @param mesNasc ({@code int}): Mes de nascimento do {@code Homem}.
     * @param anoNasc ({@code int}): Ano de nascimento do {@code Homem}.
     */
    public Homem(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }
    
    /**
     * Construtor básico.
     * Composto de Nome, Sobrenome e Data de Nascimento.
     * 
     * @param nome ({@code String}): Nome do {@code Homem}.
     * @param sobreNome ({@code String}): Sobrenome do {@code Homem}.
     * @param diaNasc ({@code String}): Dia de nascimento do {@code Homem}.
     * @param mesNasc ({@code String}): Mes de nascimento do {@code Homem}.
     * @param anoNasc ({@code String}): Ano de nascimento do {@code Homem}.
     */
    public Homem(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome do {@code Homem}.
     * @param sobreNome ({@code String}): Sobrenome do {@code Homem}.
     * @param diaNasc ({@code int}): Dia de nascimento do {@code Homem}.
     * @param mesNasc ({@code int}): Mes de nascimento do {@code Homem}.
     * @param anoNasc ({@code int}): Ano de nascimento do {@code Homem}.
     * @param numCPF ({@code String}): Numero de CPF do {@code Homem}.
     * @param peso ({@code float}): Peso do {@code Homem}.
     * @param altura ({@code float}): Altura do {@code Homem}.
     */
    public Homem(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String numCPF, float peso, float altura) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * @param nome ({@code String}): Nome do {@code Homem}.
     * @param sobreNome ({@code String}): Sobrenome do {@code Homem}.
     * @param diaNasc ({@code String}): Dia de nascimento do {@code Homem}.
     * @param mesNasc ({@code String}): Mes de nascimento do {@code Homem}.
     * @param anoNasc ({@code String}): Ano de nascimento do {@code Homem}.
     * @param numCPF ({@code String}): Numero de CPF do {@code Homem}.
     * @param peso ({@code float}): Peso do {@code Homem}.
     * @param altura ({@code float}): Altura do {@code Homem}.
     */
    public Homem(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String numCPF, float peso, float altura) {
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
