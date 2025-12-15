package lp2g04.bib;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.io.Serializable; // Boa Pratica

public class Livro implements Serializable {

    private int codLivro;       // Codigo do Livro (int, de 1 a 999)
    private String titulo;      // Titulo do Livro
    private String categoria;   // Categoria (String ou Enum)
    private int quantidade;     // Copias disponiveis (total no acervo)
    private int emprestados;    // Copias deste titulo emprestadas
    
    // Historico de usuarios que pegaram este livro
    private ArrayList<EmprestPara> hist; 

    // --- CONSTRUTOR ---
    public Livro(int codLivro, String titulo, String categoria, int quantidade) {
        // Validacao basica
        if (codLivro < 1 || codLivro > 999) {
            throw new IllegalArgumentException("Codigo do Livro deve estar entre 1 e 999.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de copias deve ser positiva.");
        }

        this.codLivro = codLivro;
        this.titulo = titulo;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.emprestados = 0; // Inicialmente zero
        this.hist = new ArrayList<>();
    }

    // --- METODOS DE CONTROLE ---
    public void empresta() throws CopiaNaoDisponivelEx {
        if (this.emprestados >= this.quantidade) {
            throw new CopiaNaoDisponivelEx("Todas as copias do livro '" + this.titulo + "' estao emprestadas.");
        }
        this.emprestados++;
    }

    public void devolve() throws NenhumaCopiaEmprestadaEx {
        if (this.emprestados <= 0) {
            throw new NenhumaCopiaEmprestadaEx("Nenhuma copia do livro '" + this.titulo + "' estava emprestada.");
        }
        this.emprestados--;
    }
    
    public void addUsuarioHist(GregorianCalendar dataLocacao, GregorianCalendar dataDevolucao, long cpfUsuario) {
        // Cria um objeto EmprestPara
        EmprestPara registro = new EmprestPara(dataLocacao, dataDevolucao, cpfUsuario); 
        this.hist.add(registro); //
    }
    
    // --- GETTERS e SETTERS (Para todos os campos) ---

    public int getCodLivro() { return codLivro; }
    public String getTitulo() { return titulo; }
    public String getCategoria() { return categoria; }
    public int getQuantidade() { return quantidade; }
    public int getEmprestados() { return emprestados; }
    public ArrayList<EmprestPara> getHist() { return hist; }

    // SETTERS

    public void setCodLivro(int codLivro) {
        if (codLivro < 1 || codLivro > 999) {
            throw new IllegalArgumentException("Codigo do Livro deve estar entre 1 e 999.");
        }
        this.codLivro = codLivro;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O titulo do livro nao pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria nao pode ser vazia.");
        }
        this.categoria = categoria;
    }

    public void setQuantidade(int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A quantidade total deve ser maior que zero.");
        }
        if (novaQuantidade < this.emprestados) {
            throw new IllegalArgumentException("Nao eh possível reduzir o acervo para " + novaQuantidade + 
                " pois ja existem " + this.emprestados + " livros emprestados.");
        }
        this.quantidade = novaQuantidade;
    }

    public void setEmprestados(int emprestados) {
        if (emprestados < 0) {
            throw new IllegalArgumentException("A quantidade de emprestados nao pode ser negativa.");
        }
        if (emprestados > this.quantidade) {
            throw new IllegalArgumentException("Impossivel ter " + emprestados + 
                " emprestados se o acervo total eh apenas " + this.quantidade);
        }
        this.emprestados = emprestados;
    }

    public void setHist(ArrayList<EmprestPara> hist) {
        // Apenas previne que a lista seja nula
        if (hist == null) {
            this.hist = new ArrayList<>();
        } else {
            this.hist = hist;
        }
    }

    // --- METODO toString() ---
    @Override
    public String toString() {
        String dataAtualizacao = new SimpleDateFormat("dd/MM/yyyy").format(new GregorianCalendar().getTime());

        String output = "\n--- DADOS DO LIVRO (" + dataAtualizacao + ") ---\n";
        output += "Codigo: " + this.codLivro + "\n";
        output += "Titulo: " + this.titulo + " (" + this.categoria + ")\n";
        output += "Acervo Total: " + this.quantidade + "\n";
        output += "Emprestados: " + this.emprestados + "\n";
        output += "Disponiveis: " + (this.quantidade - this.emprestados) + "\n";
        output += "--- Historico de Usuarios ---\n";

        if (hist.isEmpty()) {
            output += "Nenhum historico de emprestimo registrado para este livro.\n";
        } else {
            for (EmprestPara registro : hist) {
                output += " " + registro.toString() + "\n";
            }
        }
        
        return output;
    }
}