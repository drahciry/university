import java.text.DecimalFormat;

public abstract class PessoaIMC extends Pessoa {
    // ATRIBUTOS (protegidos)
    protected float peso;
    protected float altura;

    // CONSTRUTORES
    // Construtor minino usando int
    public PessoaIMC(String nome, String sobreNome, int dia, int mes, int ano, float peso, float altura) {
        // Chama o construtor minimo de Pessoa
        super(nome, sobreNome, dia, mes, ano);
        
        // Validacao de Peso e Altura
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e Altura devem ser valores positivos.");
        }
        
        // Atribuicao
        this.peso = peso;
        this.altura = altura;
    }

    //Construtor minimo usando string
    public PessoaIMC(String nome, String sobreNome, String dia, String mes, String ano, float peso, float altura) {
        // Chama o construtor minimo de Pessoa
        super(nome, sobreNome, dia, mes, ano);
        
        // Validacao de Peso e Altura
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e Altura devem ser valores positivos.");
        }
        
        // Atribuicao
        this.peso = peso;
        this.altura = altura;
    }

    // Construtor completo usando int
    public PessoaIMC(String nome, String sobreNome, int dia, int mes, int ano, String cpf, float peso, float altura) {
        // Chama o construtor completo de Pessoa
        super(nome, sobreNome, dia, mes, ano, cpf); 
        
        // Validacao de Peso e Altura
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e Altura devem ser valores positivos.");
        }
        
        // Atribuicao
        this.peso = peso;
        this.altura = altura;
    }
    
    // Construtor completo usando string
    public PessoaIMC(String nome, String sobreNome, String dia, String mes, String ano, String cpf, float peso, float altura) {
        // Chama o construtor completo de Pessoa
        super(nome, sobreNome, dia, mes, ano, cpf); 
        
        // Validacao de Peso e Altura
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e Altura devem ser valores positivos.");
        }
        
        // Atribuicao
        this.peso = peso;
        this.altura = altura;
    }

    // Metodos
    // GET
    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    // SET
    public void setPeso(float peso) {
    // peso deve ser positivo
    if (peso <= 0) {
        throw new IllegalArgumentException("O peso deve ser um valor positivo.");
    }

    this.peso = peso;
    }

    public void setAltura(float altura) {
    // altura deve ser positiva
    if (altura <= 0) {
        throw new IllegalArgumentException("A altura deve ser um valor positivo.");
    }
    this.altura = altura;
    }

    // Metodo para calcular o IMC
    public float calculaIMC() {
        if (altura <= 0) return 0.0f; // Altura = 0 da ERRO de divisao por zero
        return peso / (altura * altura);
    }
    // Metodo abstrato que retorna a classificacao do IMC (abaixo, ideal, acima)
    public abstract String resultIMC();

    // toString
    @Override
    public String toString() {
        // Reuso do codigo de Pessoa
        String infoPessoa = super.toString(); 
        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        // Peso e Altura
        return infoPessoa + 
               "Peso: " + df.format(this.peso) + " kg\n" +
               "Altura: " + df.format(this.altura) + " m\n";
    }
}