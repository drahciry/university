// Classe Mulher herda de PessoaIMC
public class Mulher extends PessoaIMC {
    // Construtor com menos informacao usando int
    public Mulher(String nome, String sobreNome, int dia, int mes, int ano, float peso, float altura) {
        // Usa 'super' para chamar o construtor de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, peso, altura);
    }

    // Construtor com menos informacao usando String
    public Mulher(String nome, String sobreNome, String dia, String mes, String ano, float peso, float altura) {
        // Usa 'super' para chamar o construtor de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, peso, altura);
    }

    // Construtor completo usando int
    public Mulher(String nome, String sobreNome, int dia, int mes, int ano, String cpf, float peso, float altura) {
        // Usa 'super' para chamar o construtor completo de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, cpf, peso, altura);
    }

    // Construtor completo usando String
    public Mulher(String nome, String sobreNome, String dia, String mes, String ano, String cpf, float peso, float altura) {
        // Usa 'super' para chamar o construtor completo de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, cpf, peso, altura);
    }

    // Calcula o IMC
    @Override
    public String resultIMC() {
        float imc = calculaIMC(); // Usa o metodo da classe PessoaIMC

        // Classificacao para Mulher
        if (imc < 19) {
            return "Abaixo do peso ideal";
        } else if (imc <= 25.8) { // 19 <= IMC <= 25.8
            return "Peso ideal";
        } else { // IMC > 25.8
            return "Acima do peso ideal";
        }
    }

    // Substitui o toString para adicionar Genero/Idade/IMC/Avaliacao
    @Override
    public String toString() {
        String infoBase = super.toString(); // Usa o toString de PessoaIMC
        
        return infoBase + 
               "Gênero: Mulher" + 
               "\nIdade: " + this.getIdade() + " anos" +
               "\nIMC: " + String.format("%.1f", calculaIMC()) + 
               "\nAvaliação: " + resultIMC() + "\n"; //
    }
}