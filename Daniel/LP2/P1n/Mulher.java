// Classe Mulher herda de Pessoa
public class Mulher extends Pessoa {
    // Construtor com menos informacao usando int
    public Mulher(String nome, String sobreNome, int dia, int mes, int ano) {
        // Usa 'super' para chamar o construtor de Pessoa
        super(nome, sobreNome, dia, mes, ano);
    }

    // Construtor com menos informacao usando String
    public Mulher(String nome, String sobreNome, String dia, String mes, String ano) {
        // Usa 'super' para chamar o construtor de Pessoa
        super(nome, sobreNome, dia, mes, ano);
    }

    // Construtor completo usando int
    public Mulher(String nome, String sobreNome, int dia, int mes, int ano, String cpf, float peso, float altura) {
        // Usa 'super' para chamar o construtor completo de Pessoa
        super(nome, sobreNome, dia, mes, ano, cpf, peso, altura);
    }

    // Construtor completo usando String
    public Mulher(String nome, String sobreNome, String dia, String mes, String ano, String cpf, float peso, float altura) {
        // Usa 'super' para chamar o construtor completo de Pessoa
        super(nome, sobreNome, dia, mes, ano, cpf, peso, altura);
    }
    // Substitui o toString da classe Pessoa
    public String toString() {
        // Informacoes basicas da classe Pessoa
        String infoPessoa = super.toString(); 
        
        // Adiciona o genero e a idade calculada
        return infoPessoa + 
               "\nGênero: Mulher" + 
               "\nIdade: " + this.getIdade() + " anos"; // Usa getIdade() de Pessoa.
    }
}