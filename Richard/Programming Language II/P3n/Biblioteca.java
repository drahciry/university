// Importacoes uteis a classe
import java.io.*;
import java.util.HashMap;

public class Biblioteca implements Serializable {
    private HashMap<Long, Usuario> usuariosCadastrados;
    private HashMap<Integer, Livro> livrosCadastrados;

    public Biblioteca() {
        usuariosCadastrados = new HashMap<Long, Usuario>();
        livrosCadastrados = new HashMap<Integer, Livro>();
    }

    public Biblioteca(String filename) {
    }
}
