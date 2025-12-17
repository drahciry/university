// Gostaria de pedir perdao pela falta de comentarios. Estive com o tempo muito apertado,
// ate mesmo para que a interface grafica ficasse a melhor e mais interativa possivel,
// e tambem porque tive bastante demanda no trabalho que acabou me exigindo um pouco mais.
// Espero que isso nao seja um fator determinante e que de para entender completamente
// o codigo que foi feito e a proposta.

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.io.FileInputStream;
import lp2g21.bib.*;

public class P4nX extends JFrame {

    private Biblioteca library;
    private JTabbedPane tabbedPane;

    private JTable tableUsersMaster;
    private DefaultTableModel modelUsersMaster;
    private DefaultTableModel modelUserHistory;
    private JLabel lblDetailNome, lblDetailCpf, lblDetailEnd;

    private JTable tableBooksMaster;
    private DefaultTableModel modelBooksMaster;
    private DefaultTableModel modelBookHistory;
    private JLabel lblDetailTitulo, lblDetailCod, lblDetailCat;

    private JTextField txtMovCpf, txtMovCodLivro;

    private JTextField txtConfigDias, txtConfigMulta;

    public P4nX() {
        library = new Biblioteca();
        carregarConfigsIniciais();

        setTitle("Sistema Bibliotecario");
        setSize(1024, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void carregarConfigsIniciais() {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            int dias = Integer.parseInt(prop.getProperty("dias.emprestimo", "7"));
            double multa = Double.parseDouble(prop.getProperty("multa.diaria", "0.0"));
            library.setConfiguracao(dias, multa);
        } catch (Exception e) {}
    }

    private void initUI() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Usuarios", createUsersPanel());
        tabbedPane.addTab("Livros", createBooksPanel());
        tabbedPane.addTab("Movimentacao", createMovimentacaoPanel());
        tabbedPane.addTab("Sistema", createSystemPanel());

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        modelUsersMaster = new DefaultTableModel(new Object[]{"CPF", "Nome", "Sobrenome"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Long.class;
                return String.class;
            }
        };
        
        tableUsersMaster = new JTable(modelUsersMaster);
        tableUsersMaster.setAutoCreateRowSorter(true);
        tableUsersMaster.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) atualizarDetalhesUsuario();
        });

        JScrollPane scrollMaster = new JScrollPane(tableUsersMaster);

        JPanel pnlDetail = new JPanel(new BorderLayout());
        pnlDetail.setBorder(BorderFactory.createTitledBorder("Detalhes do Usuario Selecionado"));

        JPanel pnlInfo = new JPanel(new GridLayout(3, 1));
        lblDetailNome = new JLabel("Nome: -");
        lblDetailCpf = new JLabel("CPF: -");
        lblDetailEnd = new JLabel("Endereco: -");
        pnlInfo.add(lblDetailNome); pnlInfo.add(lblDetailCpf); pnlInfo.add(lblDetailEnd);

        modelUserHistory = new DefaultTableModel(new Object[]{"Cod Livro", "Data Emprestimo", "Data Devolucao"}, 0);
        JTable tableHistory = new JTable(modelUserHistory);
        JScrollPane scrollHistory = new JScrollPane(tableHistory);
        scrollHistory.setBorder(BorderFactory.createTitledBorder("Historico de Emprestimos"));

        pnlDetail.add(pnlInfo, BorderLayout.NORTH);
        pnlDetail.add(scrollHistory, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollMaster, pnlDetail);
        splitPane.setDividerLocation(400);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Novo Usuario");
        JButton btnRefresh = new JButton("Recarregar Lista");
        
        btnAdd.addActionListener(e -> showAddUserDialog());
        btnRefresh.addActionListener(e -> refreshUserTable());
        
        btnPanel.add(btnAdd);
        btnPanel.add(btnRefresh);

        panel.add(splitPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void atualizarDetalhesUsuario() {
        int viewRow = tableUsersMaster.getSelectedRow();
        if (viewRow == -1) return;

        int modelRow = tableUsersMaster.convertRowIndexToModel(viewRow);
        long cpf = (long) modelUsersMaster.getValueAt(modelRow, 0);

        try {
            Usuario u = library.getUsuario(cpf);

            lblDetailNome.setText("Nome: " + u.getNome() + " " + u.getSobreNome());
            lblDetailCpf.setText("CPF: " + u.getNumCPF());
            lblDetailEnd.setText("Endereco: " + u.getEndereco());

            modelUserHistory.setRowCount(0);

            for (Emprest e : u.getHistorico()) {
                String devolucao = (e.getDataDevol() == null) ? "PENDENTE" : e.getDataDevol().toString();
                modelUserHistory.addRow(new Object[]{
                    e.getCodigo(),
                    e.getDataEmprest(),
                    devolucao
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        modelBooksMaster = new DefaultTableModel(new Object[]{"Codigo", "Titulo", "Disponivel"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 2) return Integer.class;
                return String.class;
            }
        };
        tableBooksMaster = new JTable(modelBooksMaster);
        tableBooksMaster.setAutoCreateRowSorter(true);
        
        tableBooksMaster.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) atualizarDetalhesLivro();
        });

        JScrollPane scrollMaster = new JScrollPane(tableBooksMaster);

        JPanel pnlDetail = new JPanel(new BorderLayout());
        pnlDetail.setBorder(BorderFactory.createTitledBorder("Detalhes do Livro Selecionado"));

        JPanel pnlInfo = new JPanel(new GridLayout(3, 1));
        lblDetailTitulo = new JLabel("Titulo: -");
        lblDetailCod = new JLabel("Codigo: -");
        lblDetailCat = new JLabel("Categoria: -");
        pnlInfo.add(lblDetailTitulo); pnlInfo.add(lblDetailCod); pnlInfo.add(lblDetailCat);

        modelBookHistory = new DefaultTableModel(new Object[]{"Usuario (CPF)", "Data Emprest", "Data Devol"}, 0);
        JTable tableHistory = new JTable(modelBookHistory);
        JScrollPane scrollHistory = new JScrollPane(tableHistory);
        scrollHistory.setBorder(BorderFactory.createTitledBorder("Quem pegou este livro?"));

        pnlDetail.add(pnlInfo, BorderLayout.NORTH);
        pnlDetail.add(scrollHistory, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollMaster, pnlDetail);
        splitPane.setDividerLocation(400);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Novo Livro");
        JButton btnRefresh = new JButton("Recarregar Lista");
        
        btnAdd.addActionListener(e -> showAddBookDialog());
        btnRefresh.addActionListener(e -> refreshBookTable());
        
        btnPanel.add(btnAdd);
        btnPanel.add(btnRefresh);

        panel.add(splitPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void atualizarDetalhesLivro() {
        int viewRow = tableBooksMaster.getSelectedRow();
        if (viewRow == -1) return;

        int modelRow = tableBooksMaster.convertRowIndexToModel(viewRow);
        int cod = (int) modelBooksMaster.getValueAt(modelRow, 0);

        try {
            Livro l = library.getLivro(cod);
            
            lblDetailTitulo.setText("Titulo: " + l.getTitulo());
            lblDetailCod.setText("Codigo: " + l.getCodigo());
            lblDetailCat.setText("Categoria: " + l.getCategoria());

            modelBookHistory.setRowCount(0);
            
            for (EmprestPara ep : l.getHistorico()) {
                String devolucao = (ep.getDataDevol() == null) ? "PENDENTE" : ep.getDataDevol().toString();
                modelBookHistory.addRow(new Object[]{
                    ep.getNumCPF(),
                    ep.getDataEmprest(),
                    devolucao
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JPanel createMovimentacaoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCpf = new JLabel("CPF do Usuario:");
        txtMovCpf = new JTextField(20);
        JLabel lblCod = new JLabel("Codigo do Livro:");
        txtMovCodLivro = new JTextField(20);
        
        JButton btnEmprestar = new JButton("EMPRESTAR");
        btnEmprestar.setBackground(new Color(200, 255, 200));
        JButton btnDevolver = new JButton("DEVOLVER");
        btnDevolver.setBackground(new Color(255, 200, 200));

        btnEmprestar.addActionListener(e -> realizarEmprestimo());
        btnDevolver.addActionListener(e -> realizarDevolucao());

        gbc.gridx=0; gbc.gridy=0; panel.add(lblCpf, gbc);
        gbc.gridx=1; gbc.gridy=0; panel.add(txtMovCpf, gbc);
        gbc.gridx=0; gbc.gridy=1; panel.add(lblCod, gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(txtMovCodLivro, gbc);
        gbc.gridx=0; gbc.gridy=2; panel.add(btnEmprestar, gbc);
        gbc.gridx=1; gbc.gridy=2; panel.add(btnDevolver, gbc);

        return panel;
    }

    private JPanel createSystemPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        JPanel pnlFiles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        pnlFiles.setBorder(BorderFactory.createTitledBorder("Persistencia"));
        JButton btnSave = new JButton("Salvar Tudo");
        JButton btnLoad = new JButton("Carregar Tudo");
        
        btnSave.addActionListener(e -> acaoSalvar());
        btnLoad.addActionListener(e -> acaoCarregar());
        
        pnlFiles.add(btnLoad); pnlFiles.add(btnSave);

        JPanel pnlConfig = new JPanel(new FlowLayout());
        pnlConfig.setBorder(BorderFactory.createTitledBorder("Configuracoes"));
        txtConfigDias = new JTextField("7", 5);
        txtConfigMulta = new JTextField("2.50", 5);
        JButton btnApply = new JButton("Aplicar");
        
        btnApply.addActionListener(e -> {
            try {
                int d = Integer.parseInt(txtConfigDias.getText());
                double m = Double.parseDouble(txtConfigMulta.getText());
                library.setConfiguracao(d, m);
                JOptionPane.showMessageDialog(this, "Configuracoes Aplicadas!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Erro numero"); }
        });
        
        pnlConfig.add(new JLabel("Dias:")); pnlConfig.add(txtConfigDias);
        pnlConfig.add(new JLabel("Multa R$:")); pnlConfig.add(txtConfigMulta);
        pnlConfig.add(btnApply);

        panel.add(pnlFiles); panel.add(pnlConfig);
        return panel;
    }

    private void refreshUserTable() {
        modelUsersMaster.setRowCount(0);
        modelUserHistory.setRowCount(0);
        lblDetailNome.setText("Nome: -");
        
        HashMap<Long, Usuario> map = library.getAllUsers();
        ArrayList<Long> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        for (Long k : keys) {
            Usuario u = map.get(k);
            modelUsersMaster.addRow(new Object[]{ u.getNumCPF(), u.getNome(), u.getSobreNome() });
        }
    }

    private void refreshBookTable() {
        modelBooksMaster.setRowCount(0);
        modelBookHistory.setRowCount(0);
        lblDetailTitulo.setText("Titulo: -");
        
        HashMap<Integer, Livro> map = library.getAllBooks();
        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        for (Integer k : keys) {
            Livro l = map.get(k);
            modelBooksMaster.addRow(new Object[]{ l.getCodigo(), l.getTitulo(), l.getDisponiveis() });
        }
    }
    
    private void realizarEmprestimo() {
        try {
            long cpf = Long.parseLong(txtMovCpf.getText());
            int cod = Integer.parseInt(txtMovCodLivro.getText());
            library.emprestaLivro(library.getUsuario(cpf), library.getLivro(cod));
            JOptionPane.showMessageDialog(this, "Sucesso!");
            refreshBookTable();
            refreshUserTable();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }
    
    private void realizarDevolucao() {
        try {
            long cpf = Long.parseLong(txtMovCpf.getText());
            int cod = Integer.parseInt(txtMovCodLivro.getText());
            library.devolveLivro(library.getUsuario(cpf), library.getLivro(cod));
            JOptionPane.showMessageDialog(this, "Devolvido! (Veja terminal para multas)");
            refreshBookTable();
            refreshUserTable();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    private void acaoSalvar() {
        String u = JOptionPane.showInputDialog(this, "Arquivo Usuarios:", "u.dat");
        if(u==null) return;
        String l = JOptionPane.showInputDialog(this, "Arquivo Livros:", "l.dat");
        if(l==null) return;
        library.salvaArquivo(library.getAllUsers(), u);
        library.salvaArquivo(library.getAllBooks(), l);
        JOptionPane.showMessageDialog(this, "Salvo!");
    }

    private void acaoCarregar() {
        try {
            String u = JOptionPane.showInputDialog(this, "Arquivo Usuarios:", "u.dat");
            if(u==null) return;
            String l = JOptionPane.showInputDialog(this, "Arquivo Livros:", "l.dat");
            if(l==null) return;
            library.leArquivo(u, "users");
            library.leArquivo(l, "books");
            refreshUserTable();
            refreshBookTable();
            JOptionPane.showMessageDialog(this, "Carregado!");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro load"); }
    }

    private void showAddUserDialog() {
        String nome = JOptionPane.showInputDialog("Nome:");
        if(nome == null) return;
        String cpf = JOptionPane.showInputDialog("CPF (Numeros):");
        try {
            Usuario u = new Usuario(nome, "Sobrenome", 1, 1, 2000, cpf, 70f, 1.70f, "Endereco Padrao");
            library.cadastraUsuario(u);
            refreshUserTable();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    private void showAddBookDialog() {
        String tit = JOptionPane.showInputDialog("Titulo:");
        if(tit == null) return;
        String cod = JOptionPane.showInputDialog("Codigo:");
        try {
            Livro l = new Livro(Integer.parseInt(cod), tit, "AVENTURA", 5);
            library.cadastraLivro(l);
            refreshBookTable();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage()); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { new P4nX().setVisible(true); });
    }
}