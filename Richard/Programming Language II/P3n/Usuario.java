import java.util.ArrayList;
import java.time.LocalDate;

public class Usuario extends Pessoa {
    private String endereco;
    private ArrayList<Emprest> hist;
    private int quantidade = 0;

    public Usuario(String nome, String sobreNome, int diaNasc, int mesNasc, int anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    public Usuario(String nome, String sobreNome, String diaNasc, String mesNasc, String anoNasc) {
        super(nome, sobreNome, diaNasc, mesNasc, anoNasc);
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
