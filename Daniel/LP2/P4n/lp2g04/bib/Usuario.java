package lp2g04.bib;

// Import utilizados
import java.util.ArrayList;
import java.util.GregorianCalendar;

// Usuario que herda de Pessoa
public class Usuario extends Pessoa{
    // ATRIBUTOS
    private String endereco;
    private ArrayList<Emprest> hist; // Armazena objetos da classe Emprest
    private int livrosEmprestados = 0; // Contagem de emprestimos

    // CONSTRUTORES
    // Construtor com a data como int
    public Usuario(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc, String cpf, String endereco) {
        // Usa o construtor pai
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, cpf);

        // Atribuicoes
        this.endereco = endereco;
        this.hist = new ArrayList<>();

        // Contador livrosEmprestados comeca em 0
    }

    // Construtor com a data como String (incluindo mes por extenso)
    public Usuario(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc, String cpf, String endereco) {
        // Mesma logica do construtor anterior
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc, cpf); 

        this.endereco = endereco;
        this.hist = new ArrayList<>();
    }

    // METODOS GETTERS/SETTER
    public String getEndereco() { return endereco; }
    public ArrayList<Emprest> getHist() { return hist; }
    public int getLivrosEmprestados() { return livrosEmprestados; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    // METODOS DE HISTORICO
    // Adiciona um registro de emprestimo ao hitorico (hist)
    public void addLivroHist(GregorianCalendar dataLocacao, int codLivro) {
        // Cria um objeto Emprest
        Emprest novoEmprestimo = new Emprest(dataLocacao, codLivro); 
        this.hist.add(novoEmprestimo);
        this.livrosEmprestados++; // Incrementa o contador
    }

    // Metodo que decrementa o contador quando o livro for devolvido
    public void decrementarLivrosEmprestados() {
        if (this.livrosEmprestados > 0) {
            this.livrosEmprestados--;
        }
    }

    // TO STRING
    // Sobrescreve toString para mostrar as informacoes adicionais
    @Override
    public String toString() {
        String output = super.toString(); // Informacoes compartilhadas com a classe Pai

        // Adiciona as novas informacoes
        output += "Endereço: " + this.endereco + "\n";
        output += "Livros Emprestados: " + this.livrosEmprestados + "\n";
        output += "\n--- HISTÓRICO DE EMPRÉSTIMOS ---\n";
        
        if (hist.isEmpty()) output += "Nenhum empréstimo registrado.\n"; // Checa se esta vazio
        else for (Emprest emprestimo : hist) output += emprestimo.toString(); // Adiciona cada registro
    
        return output; // String completa
    }
}