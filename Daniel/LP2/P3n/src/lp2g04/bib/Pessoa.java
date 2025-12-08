package lp2g04.bib;

import java.io.Serializable;
// Bibliotecas utilizadas
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class Pessoa implements Serializable {
    // (Atributos)
    private String nome;
    private String sobreNome;
    private GregorianCalendar dataNasc;
    private long numCPF;

    // (Static)
    private static int numPessoasCriadas = 0;

    // (Construtores)
    // Construtor com menos informacoes usando int
    public Pessoa(String nome, String sobreNome, int dia, int mes, int ano) {
        // Verificacao de Nome e Sobrenome valido
        if (!isNomeValido(nome)) {
            throw new IllegalArgumentException("O nome '" + nome + "' contem caracteres invalidos (numeros ou simbolos).");
        }
        if (!isNomeValido(sobreNome)) {
            throw new IllegalArgumentException("O sobrenome '" + sobreNome + "' contem caracteres invalidos (numeros ou simbolos).");
        } 
        // Verificacao de data valida
        if (!ValidaData.isDataValida(dia, mes, ano)) 
            throw new IllegalArgumentException("A data de nascimento " + dia + "/" + mes + "/" + ano + " eh invalida ou fora do intervalo permitido.");
        
        // Atribuicoes
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNasc = new GregorianCalendar(ano, mes -1, dia); // Os meses vao de 0 a 11

        // Inicializa os atributos nao fornecidos com zero
        this.numCPF = 0;

        // Incrementa o contador de instancias
        Pessoa.numPessoasCriadas++;
    }

    // Construtor com menos informacoes usando String
    public Pessoa(String nome, String sobreNome, String dia, String mes, String ano) {
        // Verificacao de Nome e Sobrenome valido
        if (!isNomeValido(nome)) {
            throw new IllegalArgumentException("O nome '" + nome + "' contem caracteres invalidos (numeros ou simbolos).");
        }
        if (!isNomeValido(sobreNome)) {
            throw new IllegalArgumentException("O sobrenome '" + sobreNome + "' contem caracteres invalidos (numeros ou simbolos).");
        } 

        // Validacao da Data usando a sobrecarga de String
        if (!ValidaData.isDataValida(dia, mes, ano))
            throw new IllegalArgumentException("A data de nascimento " + dia + "/" + mes + "/" + ano + " eh invalida ou fora do intervalo permitido.");
    
        // Converte os strings de data para int (sabemos que a conversao eh segura se a validacao passou)
        int diaInt = ValidaData.toInt(dia);
        int anoInt = ValidaData.toInt(ano);
        
        int mesInt;
        // Tenta primeiro como numero.
        int mesNumeral = ValidaData.toInt(mes);
    
        if (ValidaData.isMes(mesNumeral)) { // Verifica se eh numerico valido (1-12)
            mesInt = mesNumeral;
        } else { 
           // Se falhou como numero, tenta a conversao de nome por extenso.
            mesInt = ValidaData.Meses.toInt(mes);
        }

        // Atribuicoes
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNasc = new GregorianCalendar(anoInt, mesInt -1, diaInt); // Os meses vao de 0 a 11

        // Inicializa o atributo nao fornecidos com zero
        this.numCPF = 0;

        // Incrementa o contador de instancias
        Pessoa.numPessoasCriadas++;

    }
    // Construtor completo com int
    public Pessoa(String nome, String sobreNome, int dia, int mes, int ano, String cpf) {
        // Chama o construtor base
        this(nome, sobreNome, dia, mes, ano);

        // Verificacao de CPF certo
        if (!ValidaCPF.isCPF(cpf)) 
            throw new IllegalArgumentException("O CPF " + cpf + " eh invalido.");

        // Atribuiçao do atributo adicional
        // Convertendo o CPF para long
        this.numCPF = ValidaCPF.toLong(cpf);
    }

    // Construtor completo com string
    public Pessoa(String nome, String sobreNome, String dia, String mes, String ano, String cpf) {
        // Chama o construtor base
        this(nome, sobreNome, dia, mes, ano);

        // Verificacao de CPF certo
        if (!ValidaCPF.isCPF(cpf)) 
            throw new IllegalArgumentException("O CPF " + cpf + " eh invalido.");

        // Atribuiçao do atributo adicional
        // Convertendo o CPF para long
        this.numCPF = ValidaCPF.toLong(cpf);
    }

    // Metodo static
    public static int numPessoas() { return numPessoasCriadas; }

    private static boolean isNomeValido(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false; // Nao pode ser nulo ou vazio
        }
        // Regex que permite: 
        // Todas as letras (minusculas, maiusculas, e acentuadas comuns)
        // Zero ou mais espaços (permitindo nomes compostos)
        // Pelo menos um caractere
        return texto.trim().matches("^[a-zA-ZáàâãéèêíïóôõöúüçÇÁÀÂÃÉÈÊÍÏÓÔÕÖÚÜ\\s]+$");
    }

    // GETTERS e SETTERS

    public String getNome() { return nome; }
    public String getSobreNome() { return sobreNome; }
    public GregorianCalendar getDataNasc() { return dataNasc; }
    public long getNumCPF() { return numCPF; }
    public int getIdade() {
        GregorianCalendar hoje = new GregorianCalendar();
        
        int anoAtual = hoje.get(Calendar.YEAR);
        int mesAtual = hoje.get(Calendar.MONTH);
        int diaAtual = hoje.get(Calendar.DAY_OF_MONTH);
        
        int anoNasc = this.dataNasc.get(Calendar.YEAR);
        int mesNasc = this.dataNasc.get(Calendar.MONTH);
        int diaNasc = this.dataNasc.get(Calendar.DAY_OF_MONTH);
    
        int idade = anoAtual - anoNasc;
    
        // Ajusta a idade se a pessoa ainda nao fez aniversario este ano
        if (mesAtual < mesNasc || (mesAtual == mesNasc && diaAtual < diaNasc)) {
            idade--;
        }
        
        return Math.max(0, idade); // Outra camada de segurança para nao retornar numeros negativos
    }

    // Setter de nome com verificação
    public void setNome(String nome) {
        // Reutiliza a mesma logica de validacao do construtor.
        if (!isNomeValido(nome)) {
            throw new IllegalArgumentException("O nome '" + nome + "' contem caracteres invalidos (numeros ou simbolos).");
        }
        this.nome = nome;
    }

    // Setter de Sobrenome com verificação
    public void setSobreNome(String sobreNome) {
        // Reutiliza a mesma logica de validacao do construtor.
        if (!isNomeValido(sobreNome)) {
            throw new IllegalArgumentException("O sobrenome '" + sobreNome + "' contem caracteres invalidos (numeros ou simbolos).");
        }
        this.sobreNome = sobreNome;
    }
    // Setter de CPF com verificacao
    public void setNumCPF(String cpf) {
        if (!ValidaCPF.isCPF(cpf)) {
            throw new IllegalArgumentException("O CPF " + cpf + " eh invalido.");
        }
        this.numCPF = ValidaCPF.toLong(cpf);
    }

    public String toString() {
        // Monta a data no formato DD/MM/AAAA
        String dataFormatada = String.format("%02d/%02d/%d", 
            this.dataNasc.get(Calendar.DAY_OF_MONTH), 
            this.dataNasc.get(Calendar.MONTH) + 1, // Os meses vao de 0 a 11
            this.dataNasc.get(Calendar.YEAR));
        
        // Caso o CPF informado tivesse inicio com 0s, na passagem do long eles foram perdidos
        // Para resolver isso, verifica-se o tamanho do cpf e adiciona quantos 0 forem ate chegar a 11
        String cpfFormatado;
        if (this.numCPF != 0) {
            // Converte e preenche com zeros a esquerda ateh completar 11 digitos
            String cpfPreenchido = String.format("%011d", this.numCPF);

            // Chama o ValidaCPF.ImprimeCPF para deixar formatado
            cpfFormatado = ValidaCPF.imprimeCPF(cpfPreenchido);
        } else {
            cpfFormatado = "Nao informado";
        }
        
        return "INFORMAÇÕES PESSOAIS\n" +
               "--------------------------------\n" +
               "Nome Completo: " + this.nome + " " + this.sobreNome + "\n" +
               "Nascimento: " + dataFormatada + "\n" +
               "CPF: " + cpfFormatado + "\n";
    }
}