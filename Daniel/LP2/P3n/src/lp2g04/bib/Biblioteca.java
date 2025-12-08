package lp2g04.bib;

import java.io.*;
import java.util.*;

public class Biblioteca {
    // Campos para cadastro usando HashMap
    private HashMap<Long, Usuario> cadastroUsuarios;
    private HashMap<Integer, Livro> acervoLivros;

    // --- CONSTRUTORES ---

    // Construtor Padrao (Inicia vazio)
    public Biblioteca() {
        this.cadastroUsuarios = new HashMap<>();
        this.acervoLivros = new HashMap<>();
    }

    // Construtor que carrega de arquivos
    public Biblioteca(String nomeArqUsuarios, String nomeArqLivros) {
        this(); // Chama o construtor padrao para garantir instancias caso a leitura falhe
        try {
            leArquivoUsuarios(nomeArqUsuarios);
            leArquivoLivros(nomeArqLivros);
            System.out.println("Base de dados carregada com sucesso.");
        } catch (Exception e) {
            System.err.println("Aviso: Nao foi possível carregar os arquivos (" + e.getMessage() + "). Iniciando base vazia.");
        }
    }

    // --- GETTERS (NECESSÁRIOS PARA O P3nX SALVAR ARQUIVOS) ---
    public HashMap<Long, Usuario> getCadastroUsuarios() {
        return cadastroUsuarios;
    }

    public HashMap<Integer, Livro> getAcervoLivros() {
        return acervoLivros;
    }

    // --- METODOS DE CADASTRO ---

    public void cadastraUsuario(Usuario usuario) {
        if (cadastroUsuarios.containsKey(usuario.getNumCPF())) {
            // Decisao de projeto: Nao permitir sobrescrever
            System.err.println("Erro: Ja existe um usuario com o CPF " + usuario.getNumCPF());
            return;
        }
        cadastroUsuarios.put(usuario.getNumCPF(), usuario);
    }

    public void cadastraLivro(Livro livro) {
        if (acervoLivros.containsKey(livro.getCodLivro())) {
            System.err.println("Erro: Ja existe um livro com o codigo " + livro.getCodLivro());
            return;
        }
        acervoLivros.put(livro.getCodLivro(), livro);
    }

    // --- METODOS DE ARQUIVO (PERSISTENCIA) ---

    // Salva qualquer HashMap (Generico, conforme enunciado permite reaproveitar)
    public void salvaArquivo(HashMap<?, ?> map, String nomeArquivo) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            output.writeObject(map);
        }
    }

    // Le o HashMap de Usuarios
    @SuppressWarnings("unchecked")
    public void leArquivoUsuarios(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            this.cadastroUsuarios = (HashMap<Long, Usuario>) input.readObject();
        }
    }

    // Le o HashMap de Livros
    @SuppressWarnings("unchecked")
    public void leArquivoLivros(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            this.acervoLivros = (HashMap<Integer, Livro>) input.readObject();
        }
    }

    // --- METODOS DE BUSCA ---

    public Livro getLivro(int cod) throws LivroNaoCadastradoEx {
        Livro l = acervoLivros.get(cod);
        if (l == null) {
            throw new LivroNaoCadastradoEx("Livro com codigo " + cod + " nao encontrado.");
        }
        return l;
    }

    public Usuario getUsuario(long cpf) throws UsuarioNaoCadastradoEx {
        Usuario u = cadastroUsuarios.get(cpf);
        if (u == null) {
            throw new UsuarioNaoCadastradoEx("Usuario com CPF " + cpf + " nao encontrado.");
        }
        return u;
    }

    // --- OPERAÇÕES DE EMPRESTIMO E DEVOLUCAO ---

    public void emprestaLivro(Usuario usuario, Livro livro) throws CopiaNaoDisponivelEx {
        // Tenta emprestar no Livro
        livro.empresta();

        // Data atual
        GregorianCalendar dataEmp = new GregorianCalendar();

        // Atualiza historico do Livro
        // null na data de devolucao indica que esta com o livro
        livro.addUsuarioHist(dataEmp, null, usuario.getNumCPF());

        // Atualiza historico do Usuario
        usuario.addLivroHist(dataEmp, livro.getCodLivro());
        
        System.out.println("Emprestimo realizado: " + livro.getTitulo() + " para " + usuario.getNome());
    }

    public void devolveLivro(Usuario usuario, Livro livro) throws NenhumaCopiaEmprestadaEx {
        // Tenta devolver no Livro
        livro.devolve();

        // Data de devolucao (Agora)
        GregorianCalendar dataDevol = new GregorianCalendar();

        // ATUALIZAR HISTORICO DO LIVRO
        // Precisamos achar o registro "aberto" (dataDevolucao == null) para este usuario
        boolean achouNoLivro = false;
        for (EmprestPara registro : livro.getHist()) {
            if (registro.getCpfUsuario() == usuario.getNumCPF() && registro.getDataDevolucao() == null) {
                registro.setDataDevol(dataDevol);
                achouNoLivro = true;
                break; // Encontrou e atualizou, para.
            }
        }

        // ATUALIZAR HISTORICO DO USUARIO
        // Precisamos achar o registro "aberto" para este livro
        boolean achouNoUsuario = false;
        for (Emprest registro : usuario.getHist()) {
            if (registro.getCodLivro() == livro.getCodLivro() && registro.getDataDevolucao() == null) {
                registro.setDataDevol(dataDevol);
                achouNoUsuario = true;
                break;
            }
        }

        if (!achouNoLivro || !achouNoUsuario) {
            System.err.println("Aviso de Inconsistencia: O livro foi devolvido ao estoque, mas nao foi encontrado o registro de emprestimo em aberto nos historicos.");
        } else {
            System.out.println("Devolucao confirmada: " + livro.getTitulo() + " devolvido por " + usuario.getNome());
        }
    }

    // --- RELATORIOS ---

    public String imprimeLivros() {
        // Converte valores do Map para Lista para poder ordenar
        ArrayList<Livro> lista = new ArrayList<>(acervoLivros.values());
        
        // Ordena por Codigo
        Collections.sort(lista, new Comparator<Livro>() {
            @Override
            public int compare(Livro l1, Livro l2) {
                return Integer.compare(l1.getCodLivro(), l2.getCodLivro());
            }
        });

        StringBuilder sb = new StringBuilder("--- RELATÓRIO DE LIVROS ---\n");
        for (Livro l : lista) {
            sb.append(l.toString()).append("\n-----------------\n");
        }
        return sb.toString();
    }

    public String imprimeUsuarios() {
        // Converte valores do Map para Lista
        ArrayList<Usuario> lista = new ArrayList<>(cadastroUsuarios.values());

        // Ordena por CPF
        Collections.sort(lista, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario u1, Usuario u2) {
                return Long.compare(u1.getNumCPF(), u2.getNumCPF());
            }
        });

        StringBuilder sb = new StringBuilder("--- RELATÓRIO DE USUÁRIOS ---\n");
        for (Usuario u : lista) {
            sb.append(u.toString()).append("\n-----------------\n");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Biblioteca: " + cadastroUsuarios.size() + " usuarios, " + acervoLivros.size() + " livros.";
    }
}