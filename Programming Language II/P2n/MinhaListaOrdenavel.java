import java.util.ArrayList;

public class MinhaListaOrdenavel {
    
    ArrayList<PessoaIMC> pessoas = new ArrayList<PessoaIMC>();

    public void add(PessoaIMC p) {
        pessoas.add(p);
    }

    public PessoaIMC get(int index) {
        if (index < 0 || index >= pessoas.size()) {

        }

        return pessoas.get(index);
    }

}
