import java.util.ArrayList;
import java.time.LocalDate;

public class Usuario extends Pessoa {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    private String endereco;
    private int quantidade = 0;
    private ArrayList<Emprest> hist = new ArrayList<Emprest>();

    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * Historico se inicia vazio.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code int}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code int}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code int}): Ano de nascimento da {@code Pessoa}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public Usuario(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String numCPF, float peso, float altura) {
        // Utiliza construtor da classe herdada.
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }
    
    /**
     * Construtor especializado.
     * Composto pelo construtor base + Numero de CPF, Peso e Altura.
     * Historico se inicia vazio.
     * 
     * @param nome ({@code String}): Nome da {@code Pessoa}.
     * @param sobreNome ({@code String}): Sobrenome da {@code Pessoa}.
     * @param diaNasc ({@code String}): Dia de nascimento da {@code Pessoa}.
     * @param mesNasc ({@code String}): Mes de nascimento da {@code Pessoa}.
     * @param anoNasc ({@code String}): Ano de nascimento da {@code Pessoa}.
     * @param numCPF ({@code String}): Numero de CPF da {@code Pessoa}.
     * @param peso ({@code float}): Peso da {@code Pessoa}.
     * @param altura ({@code float}): Altura da {@code Pessoa}.
     */
    public Usuario(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String numCPF, float peso, float altura) {
        // Utiliza construtor da classe herdada.
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, numCPF, peso, altura);
    }
    
    /*************************************************************
     *                         METODOS                           *
    *************************************************************/

    /**
     * Setter para endereco do {@code Usuario}.
     * 
     * @param endereco ({@code String}): Endereco do usuario.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void addLivroHist(LocalDate dataEmprest, int codigoLivro) {
        // Cria o emprestimo.
        Emprest emprestimo = new Emprest(codigoLivro, dataEmprest);
        // Adiciona o emprestimo ao historico.
        this.hist.add(emprestimo);
    }
    
    // Metodo toString(), padrao para exibicao de informacao.
    public String toString() {
        // StringBuilder foi utilizado para que seja possivel juntar
        // todas informacoes do usuario junto com o historico.
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nEndereco: ").append(endereco);
        sb.append("\n--- Historico ---\n");
        // Passa o historico todo para o StringBuilder utilizando o toString do historico.
        for (Emprest e : hist) {
            sb.append(e.toString()).append("\n");
        }
        // Apos juntar tudo, retorna as informacoes.
        return sb.toString();
    }
}
