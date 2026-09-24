import lp2g21.bib.*;

/**
 * Classe utilitaria apenas para gerar os arquivos iniciais u.dat e l.dat.
 */
public class GeraBases {
    public static void main(String[] args) {
        System.out.println("Gerando bases de dados iniciais...");
        Biblioteca bib = new Biblioteca();

        try {
            // 1. Cadastrar 5 Usuarios
            Usuario u1 = new Usuario("Joao", "Silva", 1, 1, 1990, "957.736.920-07", 80f, 1.80f, "Rua A");
            Usuario u2 = new Usuario("Maria", "Souza", 2, 2, 1995, "516.776.340-14", 60f, 1.65f, "Rua B");
            Usuario u3 = new Usuario("Pedro", "Alves", 3, 3, 2000, "170.463.260-96", 75f, 1.75f, "Rua C");
            Usuario u4 = new Usuario("Ana", "Clara", 4, 4, 1998, "616.518.280-16", 55f, 1.60f, "Rua D");
            Usuario u5 = new Usuario("Lucas", "Moura", 5, 5, 1985, "786.823.530-67", 90f, 1.85f, "Rua E");

            bib.cadastraUsuario(u1);
            bib.cadastraUsuario(u2);
            bib.cadastraUsuario(u3);
            bib.cadastraUsuario(u4);
            bib.cadastraUsuario(u5);

            // Cadastrar 5 Livros
            Livro l1 = new Livro(101, "Dom Casmurro", "ROMANCE", 3);
            Livro l2 = new Livro(102, "Harry Potter", "AVENTURA", 5);
            Livro l3 = new Livro(103, "O Hobbit", "AVENTURA", 2);
            Livro l4 = new Livro(104, "It a Coisa", "TERROR", 1);
            Livro l5 = new Livro(105, "Sherlock Holmes", "SUSPENSE", 4);

            bib.cadastraLivro(l1);
            bib.cadastraLivro(l2);
            bib.cadastraLivro(l3);
            bib.cadastraLivro(l4);
            bib.cadastraLivro(l5);

            // Criar Emprestimos (Historico)
            
            // Cenario A: Usuario com livro Devolvido (u1 pegou l1 e devolveu)
            bib.emprestaLivro(u1, l1);
            bib.devolveLivro(u1, l1);
            System.out.println("Cenario 1: Emprestimo devolvido criado.");

            // Cenario B: Usuario com emprestimo EM VIGOR (u2 pegou l2)
            bib.emprestaLivro(u2, l2);
            System.out.println("Cenario 2: Emprestimo em vigor criado.");

            // Salvar Arquivos
            bib.salvaArquivo(bib.getAllUsers(), "u.dat");
            bib.salvaArquivo(bib.getAllBooks(), "l.dat");

            System.out.println("\nSUCESSO! Arquivos u.dat e l.dat criados na raiz.");

        } catch (Exception e) {
            System.out.println("Erro ao gerar bases: " + e.getMessage());
            e.printStackTrace();
        }
    }
}