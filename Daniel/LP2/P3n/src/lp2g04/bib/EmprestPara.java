package lp2g04.bib;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.io.Serializable; // Boa Pratica

public class EmprestPara implements Serializable {

    private GregorianCalendar dataEmprestimo;
    private GregorianCalendar dataDevolucao;
    private long cpfUsuario; // Armazena o CPF de quem pegou o livro 

    // --- CONSTRUTOR ---
    public EmprestPara(GregorianCalendar dataEmprestimo, GregorianCalendar dataDevolucao, long cpfUsuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao; // Geralmente null na criacao de um novo emprestimo
        this.cpfUsuario = cpfUsuario;
    }

    // --- METODOS DE CONTROLE ---
    public void setDataDevol(GregorianCalendar dataDevolucao) {
        if (dataDevolucao != null && dataDevolucao.before(dataEmprestimo)) {
            throw new IllegalArgumentException("Erro: Data de devolucao anterior a data de emprestimo.");
        }
        this.dataDevolucao = dataDevolucao;
    }

    // --- GETTERS ---

    public GregorianCalendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    public GregorianCalendar getDataDevolucao() {
        return dataDevolucao;
    }

    public long getCpfUsuario() {
        return cpfUsuario;
    }

    // --- METODO toString() ---
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String dataEmpStr = (dataEmprestimo != null) ? sdf.format(dataEmprestimo.getTime()) : "N/A";
        
        String dataDevolStr;
        if (dataDevolucao == null) {
            dataDevolStr = "Pendente";
        } else {
            dataDevolStr = sdf.format(dataDevolucao.getTime());
        }

        // Caso o CPF informado tivesse inicio com 0s, na passagem do long eles foram perdidos
        // Para resolver isso, verifica-se o tamanho do cpf e adiciona quantos 0 forem ate chegar a 11
        String cpfFormatado;
        if (this.cpfUsuario != 0) {
            // Converte e preenche com zeros a esquerda ateh completar 11 digitos
            String cpfPreenchido = String.format("%011d", this.cpfUsuario);

            // Chama o ValidaCPF.ImprimeCPF para deixar formatado
            cpfFormatado = ValidaCPF.imprimeCPF(cpfPreenchido);
        } else {
            cpfFormatado = "Nao informado";
        }
        
        return String.format(" | Usuário (CPF: %s) | Retirado: %s | Devolvido: %s", 
                             cpfFormatado, dataEmpStr, dataDevolStr);
    }
}