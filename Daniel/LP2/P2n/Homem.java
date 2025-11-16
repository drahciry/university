// Classe Homem herda de PessoaIMC
public class Homem extends PessoaIMC { // Agora herda de PessoaIMC
    // Construtor com menos informacao usando int
    public Homem(String nome, String sobreNome, int dia, int mes, int ano, float peso, float altura) {
        // Usa 'super' para chamar o construtor de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, peso, altura);
    }

    // Construtor com menos informacao usando String
    public Homem(String nome, String sobreNome, String dia, String mes, String ano, float peso, float altura) {
        // Usa 'super' para chamar o construtor de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, peso, altura);
    }

    // Construtor completo usando int
    public Homem(String nome, String sobreNome, int dia, int mes, int ano, String cpf, float peso, float altura) {
        // Usa 'super' para chamar o construtor completo de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, cpf, peso, altura);
    }

    // Construtor completo usando String
    public Homem(String nome, String sobreNome, String dia, String mes, String ano, String cpf, float peso, float altura) {
        // Usa 'super' para chamar o construtor completo de PessoaIMC
        super(nome, sobreNome, dia, mes, ano, cpf, peso, altura);
    }

    // Calcula o IMC
    @Override
    public String resultIMC() {
        float imc = calculaIMC(); // Usa o metodo da classe PessoaIMC
        
        // Classificacao para Homem
        if (imc < 20.7) {
            return "Abaixo do peso ideal";
        } else if (imc <= 26.4) { // 20.7 <= IMC <= 26.4
            return "Peso ideal";
        } else { // IMC > 26.4
            return "Acima do peso ideal";
        }
    }

    // Substitui o toString para adicionar Genero/Idade/IMC/Avaliacao
    @Override
    public String toString() {
        String infoBase = super.toString(); // Usa o toString de PessoaIMC
        
        return infoBase + 
               "Gênero: Homem" + 
               "\nIdade: " + this.getIdade() + " anos" +
               "\nIMC: " + String.format("%.1f", calculaIMC()) + 
               "\nAvaliação: " + resultIMC() + "\n"; //
    }
}