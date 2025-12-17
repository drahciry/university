import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

// Importa as classes do pacote do backend
import lp2g04.bib.*;

public class P4nX extends JFrame {

    private Biblioteca bib;
    private final String ARQ_USUARIOS = "u.dat";
    private final String ARQ_LIVROS = "l.dat";

    // Componentes da Interface
    private JTable tabelaUsuarios;
    private JTable tabelaLivros;
    private DefaultTableModel modeloUsuarios;
    private DefaultTableModel modeloLivros;
    
    // Campos do Painel de Emprestimo
    private JTextField txtEmpLivroID;
    private JTextField txtEmpUsuarioCPF;
    private JRadioButton rbEmprestar;
    private JRadioButton rbDevolver;

    public P4nX() {
        super("Sistema de Biblioteca - P4n");
        
        // Carrega dados existentes
        bib = new Biblioteca(ARQ_USUARIOS, ARQ_LIVROS);

        // Config da Janela Principal
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza
        setLayout(new BorderLayout());

        //Monta os Paineis
        JPanel painelSuperior = criarPainelSuperior();
        JTabbedPane painelAbas = criarPainelAbas();

        add(painelSuperior, BorderLayout.NORTH);
        add(painelAbas, BorderLayout.CENTER);

        //Carrega os dados iniciais nas tabelas
        atualizarTabelas();

        //Listener para salvar ao fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarDados();
            }
        });
    }

    // --- CRIAÇAO DOS PAINEIS ---

    private JPanel criarPainelSuperior() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // SUB-PAINEL 1: EMPRESTIMO (Esquerda)
        JPanel pEmp = new JPanel(new GridLayout(5, 1));
        pEmp.setBorder(BorderFactory.createTitledBorder("Emprestimo / Devolucao"));
        
        JPanel pLine1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pLine1.add(new JLabel("Cód. Livro:"));
        txtEmpLivroID = new JTextField(5);
        pLine1.add(txtEmpLivroID);
        
        JPanel pLine2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pLine2.add(new JLabel("CPF Usuario:"));
        txtEmpUsuarioCPF = new JTextField(11);
        pLine2.add(txtEmpUsuarioCPF);

        // Radio Buttons
        rbEmprestar = new JRadioButton("Emprestar", true);
        rbDevolver = new JRadioButton("Devolver");
        ButtonGroup group = new ButtonGroup();
        group.add(rbEmprestar);
        group.add(rbDevolver);
        
        JPanel pRadios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pRadios.add(rbEmprestar);
        pRadios.add(rbDevolver);

        JButton btnEfetuar = new JButton("Efetuar");
        btnEfetuar.addActionListener(e -> acaoEmprestimoDevolucao());

        pEmp.add(pLine1);
        pEmp.add(pLine2);
        pEmp.add(pRadios);
        pEmp.add(btnEfetuar);

        // SUB-PAINEL 2: CADASTRO (Centro)
        JPanel pCad = new JPanel(new GridLayout(2, 1, 5, 5));
        pCad.setBorder(BorderFactory.createTitledBorder("Cadastro"));
        
        JButton btnCadLivro = new JButton("Cadastrar Livro");
        btnCadLivro.addActionListener(e -> abrirDialogoCadastroLivro());
        
        JButton btnCadUser = new JButton("Cadastrar Usuário");
        btnCadUser.addActionListener(e -> abrirDialogoCadastroUsuario());

        pCad.add(btnCadLivro);
        pCad.add(btnCadUser);

        // SUB-PAINEL 3: RELATORIO/MANUTENCAO (Direita)
        JPanel pRel = new JPanel(new GridLayout(3, 1, 5, 5));
        pRel.setBorder(BorderFactory.createTitledBorder("Relatorios"));

        JButton btnAtualizar = new JButton("Atualizar Tabelas");
        btnAtualizar.addActionListener(e -> atualizarTabelas());

        JButton btnHistUser = new JButton("Historico de Usuario");
        btnHistUser.addActionListener(e -> mostrarHistoricoUsuario());

        JButton btnHistLivro = new JButton("Historico de Livro");
        btnHistLivro.addActionListener(e -> mostrarHistoricoLivro());

        pRel.add(btnAtualizar);
        pRel.add(btnHistUser);
        pRel.add(btnHistLivro);

        // Adiciona sub-paineis ao painel superior
        panel.add(pEmp);
        panel.add(pCad);
        panel.add(pRel);

        return panel;
    }

    private JTabbedPane criarPainelAbas() {
        JTabbedPane tabs = new JTabbedPane();

        // --- ABA USUARIOS ---
        // Colunas baseadas no toString() e atributos de Pessoa/Usuario
        String[] colunasUser = {"CPF", "Nome", "Sobrenome", "Nascimento", "Endereço", "Livros Emp."};
        modeloUsuarios = new DefaultTableModel(colunasUser, 0) {
            @Override // Impede edicao das celulas
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaUsuarios = new JTable(modeloUsuarios);
        // Ativa ordenacao
        tabelaUsuarios.setRowSorter(new TableRowSorter<>(modeloUsuarios));
        tabs.addTab("Usuarios", new JScrollPane(tabelaUsuarios));

        // --- ABA LIVROS ---
        String[] colunasLivro = {"Codigo", "Titulo", "Categoria", "Total", "Emprestados", "Disponiveis"};
        modeloLivros = new DefaultTableModel(colunasLivro, 0) {
            @Override 
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaLivros = new JTable(modeloLivros);
        // Ativa ordenacao
        tabelaLivros.setRowSorter(new TableRowSorter<>(modeloLivros));
        tabs.addTab("Livros", new JScrollPane(tabelaLivros));

        return tabs;
    }

    // --- LOGICA E ACOES ---

    private void atualizarTabelas() {
        // Limpa as tabelas atuais
        modeloUsuarios.setRowCount(0);
        modeloLivros.setRowCount(0);

        // Reusa o acesso aos HashMaps da Biblioteca
        HashMap<Long, Usuario> mapUsuarios = bib.getCadastroUsuarios();
        HashMap<Integer, Livro> mapLivros = bib.getAcervoLivros();

        // Preenche Usuarios
        for (Usuario u : mapUsuarios.values()) {
            // Formata data
            String dataNasc = String.format("%02d/%02d/%d", 
                u.getDataNasc().get(java.util.Calendar.DAY_OF_MONTH),
                u.getDataNasc().get(java.util.Calendar.MONTH) + 1,
                u.getDataNasc().get(java.util.Calendar.YEAR));

            Object[] row = {
                ValidaCPF.imprimeCPF(String.format("%011d", u.getNumCPF())),
                u.getNome(),
                u.getSobreNome(),
                dataNasc,
                u.getEndereco(),
                u.getLivrosEmprestados()
            };
            modeloUsuarios.addRow(row);
        }

        // Preenche Livros
        for (Livro l : mapLivros.values()) {
            Object[] row = {
                l.getCodLivro(),
                l.getTitulo(),
                l.getCategoria(),
                l.getQuantidade(),
                l.getEmprestados(),
                (l.getQuantidade() - l.getEmprestados())
            };
            modeloLivros.addRow(row);
        }
    }

    private void acaoEmprestimoDevolucao() {
        try {
            String cpfStr = txtEmpUsuarioCPF.getText().trim();
            String codStr = txtEmpLivroID.getText().trim();

            if (!ValidaCPF.isCPF(cpfStr)) {
                JOptionPane.showMessageDialog(this, "CPF Invalido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            long cpf = ValidaCPF.toLong(cpfStr);
            int cod = Integer.parseInt(codStr);

            Usuario u = bib.getUsuario(cpf);
            Livro l = bib.getLivro(cod);

            if (rbEmprestar.isSelected()) {
                bib.emprestaLivro(u, l);
                JOptionPane.showMessageDialog(this, "Emprestimo realizado com sucesso!");
            } else {
                bib.devolveLivro(u, l);
                JOptionPane.showMessageDialog(this, "Devolucao realizada com sucesso!");
            }
            atualizarTabelas();
            // Salvar automaticamente apos transacao
            salvarDados();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Codigo do livro deve ser numerico.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirDialogoCadastroLivro() {
        JTextField txtCod = new JTextField();
        JTextField txtTit = new JTextField();
        JTextField txtCat = new JTextField();
        JTextField txtQtd = new JTextField();

        Object[] message = {
            "Código (1-999):", txtCod,
            "Título:", txtTit,
            "Categoria:", txtCat,
            "Quantidade Total:", txtQtd
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Novo Livro", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int cod = Integer.parseInt(txtCod.getText());
                String tit = txtTit.getText();
                String cat = txtCat.getText();
                int qtd = Integer.parseInt(txtQtd.getText());

                Livro l = new Livro(cod, tit, cat, qtd);
                bib.cadastraLivro(l);
                
                JOptionPane.showMessageDialog(this, "Livro cadastrado!");
                atualizarTabelas();
                salvarDados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirDialogoCadastroUsuario() {
        JTextField txtNome = new JTextField();
        JTextField txtSobre = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtNasc = new JTextField(); // dd/mm/aaaa
        JTextField txtEnd = new JTextField();

        Object[] message = {
            "Nome:", txtNome,
            "Sobrenome:", txtSobre,
            "CPF (somente números):", txtCpf,
            "Nascimento (dd/mm/aaaa):", txtNasc,
            "Endereço:", txtEnd
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Novo Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String[] dataParts = txtNasc.getText().split("/");
                if (dataParts.length != 3) throw new IllegalArgumentException("Data deve ser dd/mm/aaaa");
                
                int dia = Integer.parseInt(dataParts[0]);
                int mes = Integer.parseInt(dataParts[1]);
                int ano = Integer.parseInt(dataParts[2]);

                Usuario u = new Usuario(
                    txtNome.getText(), 
                    txtSobre.getText(), 
                    dia, mes, ano, 
                    txtCpf.getText(), 
                    txtEnd.getText()
                );

                bib.cadastraUsuario(u);
                JOptionPane.showMessageDialog(this, "Usuario cadastrado!");
                atualizarTabelas();
                salvarDados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarHistoricoUsuario() {
        String cpfStr = JOptionPane.showInputDialog(this, "Digite o CPF do usuario:");
        if (cpfStr != null && ValidaCPF.isCPF(cpfStr)) {
            try {
                Usuario u = bib.getUsuario(ValidaCPF.toLong(cpfStr));
                
                StringBuilder sb = new StringBuilder();
                sb.append("Historico de ").append(u.getNome()).append(":\n\n");
                if (u.getHist().isEmpty()) sb.append("Nenhum registro.");
                else {
                    for(Emprest emp : u.getHist()) {
                        sb.append(emp.toString()).append("\n");
                    }
                }
                
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Historico do Usuario", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (cpfStr != null) {
            JOptionPane.showMessageDialog(this, "CPF Invalido.");
        }
    }
    
    private void mostrarHistoricoLivro() {
        String codStr = JOptionPane.showInputDialog(this, "Digite o Codigo do Livro:");
        if (codStr != null) {
            try {
                int cod = Integer.parseInt(codStr);
                Livro l = bib.getLivro(cod);
                
                StringBuilder sb = new StringBuilder();
                sb.append("Historico de '").append(l.getTitulo()).append("':\n\n");
                if (l.getHist().isEmpty()) sb.append("Nenhum registro.");
                else {
                    for(EmprestPara emp : l.getHist()) {
                        sb.append(emp.toString()).append("\n");
                    }
                }
                
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Historico do Livro", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(this, "Codigo deve ser um numero.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void salvarDados() {
        try {
            bib.salvaArquivo(bib.getCadastroUsuarios(), ARQ_USUARIOS);
            bib.salvaArquivo(bib.getAcervoLivros(), ARQ_LIVROS);
            System.out.println("Dados salvos automaticamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
    
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new P4nX().setVisible(true);
        });
    }
}