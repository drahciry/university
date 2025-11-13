// Importacoes uteis a classe
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;

/**
 * Classe que encapsula um Array List de apenas {@code PessoaIMC}.
 * Possui tambem diferentes formas de ordenacao para as pessoas.
 */
public class MinhaListaOrdenavel {
    /*************************************************************
     *                        ATRIBUTOS                          *
    *************************************************************/

    /**
     * ArrayList para armazenar objetos {@PessoaIMC}.
     * Atributo privado e estatico, nao podendo ser acessado de fora da classe.
     */
    private ArrayList<PessoaIMC> pessoas = new ArrayList<PessoaIMC>();

    /**
     * Adiciona o elemento especifico ao final desta lista.
     *
     * @param p ({@code PessoaIMC}): Elemento a ser adicionado a esta lista.
     */
    public void add(PessoaIMC p) {
        // Utiliza metodo do proprio ArrayList para armazenar.
        pessoas.add(p);
    }

    /**
     * Retorna o elemento na posicao especificada nesta lista.
     *
     * @param  index ({@code int}): Index do elemento a retornar.
     * 
     * @return {@code PessoaIMC}: Retorna o elemento na posicao especificada nesta lista.
     *
     * @throws IndexOutOfBoundsException Caso o index inserido seja negativo ou maior
     *  que o tamanho da lista, uma excecao sera lancada.
     */
    public PessoaIMC get(int index) throws IndexOutOfBoundsException {
        // Realiza a verificacao do index inserido.
        if (index < 0 || index >= pessoas.size())
            // Caso seja invalido, lanca uma excecao com mensagem informativa.
            throw new IndexOutOfBoundsException("O indice inserido deve estar entre 0 e o tamanho da lista.");
        // Se a validacao funcionar, apenas retorna o elemento na posicao.
        return pessoas.get(index);
    }

    /**
     * Metodo que recebe um criterio de ordenacao e executa a ordenacao
     * baseada no criterio. (Nome, Peso, IMC, Sexo, Idade, Data de Nascimento e CPF).
     * Para a ordenacao da lista, serao utilizadas expressoes lambdas
     * (declaradas ao fim da classe) com implementacao da interface
     * Comparator<>, feitas para cada criterio.
     * 
     * @param criterio ({@code CriterioOrdenacao}): Enumerate com o criterio de ordenacao.
     * 
     * @return {@code ArrayList<PessoaIMC>}: Retorna um {@code ArrayList<>}
     * ordenado baseado no criterio inserido.
     */
    public ArrayList<PessoaIMC> ordena(CriterioOrdenacao criterio) {
        // Se o criterio selecionado for invalido, apenas nao ordena a lista.
        if (criterio == null)
            // Retorna lista do jeito que esta.
            return this.pessoas;
        // Caso o criterio nao seja invalido, sera necessario
        // validar qual criterio foi selecionado para ordenar corretamente.
        switch (criterio) {
            // Ordenacao por nome.
            case NOME:
                this.pessoas.sort(nomeC);
                break;
            // Ordenacao reversa por nome.
            case NOME_REVERSO:
                this.pessoas.sort(nomeC.reversed());
                break;
            // Ordenacao por peso.
            case PESO:
                this.pessoas.sort(pesoC);
                break;
            // Ordenacao reversa por peso.
            case PESO_REVERSO:
                this.pessoas.sort(pesoC.reversed());
                break;
            // Ordenacao por altura.
            case ALTURA:
                this.pessoas.sort(alturaC);
                break;
            // Ordenacao reversa por altura.
            case ALTURA_REVERSO:
                this.pessoas.sort(alturaC.reversed());
                break;
            // Ordenacao por IMC.
            case IMC:
                this.pessoas.sort(imcC);
                break;
            // Ordenacao reversa por IMC.
            case IMC_REVERSO:
                this.pessoas.sort(imcC.reversed());
                break;
            // Ordenacao por genero (sexo).
            case GENERO:
                this.pessoas.sort(sexoC);
                break;
            // Ordenacao reversa por genero (sexo).
            case GENERO_REVERSO:
                this.pessoas.sort(sexoC.reversed());
                break;
            // Ordenacao por idade.
            case IDADE:
                this.pessoas.sort(idadeC);
                break;
            // Ordenacao reversa por idade.
            case IDADE_REVERSO:
                this.pessoas.sort(idadeC.reversed());
                break;
            // Ordenacao por data de nascimento.
            case DATA_NASCIMENTO:
                this.pessoas.sort(dataNascC);
                break;
            // Ordenacao reversa por data de nascimento.
            case DATA_NASCIMENTO_REVERSO:
                this.pessoas.sort(dataNascC.reversed());
                break;
            // Ordenacao por CPF.
            case CPF:
                this.pessoas.sort(cpfC);
                break;
            // Ordenacao reversa por CPF.
            case CPF_REVERSO:
                this.pessoas.sort(cpfC.reversed());
                break;
        }
        // Retorna a lista agora ordenada.
        return this.pessoas;
    }

    // Metodo toString().
    @Override
    public String toString() {
        return pessoas.toString();
    }

    /*************************************************************
     *                        ENUMERATES                         *
    *************************************************************/

    /**
     * Enumerate para criterio. Cada criterio possui
     * um valor inteiro associado, que vai de 1 a 14.
     */
    public enum CriterioOrdenacao {
        /*************************************************************
         *                     INSTANCIAS DO ENUM                    *
        *************************************************************/

        NOME(1),
        NOME_REVERSO(2),
        PESO(3),
        PESO_REVERSO(4),
        ALTURA(5),
        ALTURA_REVERSO(6),
        IMC(7),
        IMC_REVERSO(8),
        GENERO(9),
        GENERO_REVERSO(10),
        IDADE(11),
        IDADE_REVERSO(12),
        DATA_NASCIMENTO(13),
        DATA_NASCIMENTO_REVERSO(14),
        CPF(15),
        CPF_REVERSO(16);

        /*************************************************************
         *                         ATRIBUTOS                         *
        *************************************************************/

        /**
         * Constante que armazenara o valor inteiro relativo ao criterio apos ser instanciado.
         * Armazena os valores de 1 a 14.
         */
        private final int valor;

        /*************************************************************
         *                        CONSTRUTOR                         *
        *************************************************************/

        /**
         * Construtor PRIVADO. Nao pode ser instanciado com new.
         * 
         * @param criterio (int): Valor inteiro do criterio informado. 
         */
        private CriterioOrdenacao (int criterio) {
            this.valor = criterio;
        }

        /*************************************************************
         *                          METODOS                          *
        *************************************************************/

        /**
         * Metodo getter para obter qual o valor inteiro relativo ao criterio.
         * 
         * @return int: Retorna um valor inteiro no intervalo de 1 a 14, relativo ao criterio.
         */
        public int getCriterio() {
            return valor;
        }

        /**
         * Metodo que retorna uma instancia de {@code CriterioOrdenacao}
         * de acordo com um valor inteiro.
         * @param valor ({@code int}): Valor inteiro que representa o criterio.
         * 
         * @return {@code CriterioOrdenacao}: Retorna uma instancia com o criterio correto.
         */
        public static CriterioOrdenacao fromValor(int valor) {
            // Realiza um for each nas instancias para compararar os valores inteiros.
            for (CriterioOrdenacao criterio : values())
                // Se o valor passado for compativel com algum criterio,
                // entao uma instancia desse criterio sera retornada.
                if (criterio.valor == valor)
                    return criterio;
            // Caso valor nao seja valido, ou seja, compativel com nenhum criterio,
            // sera retornadoo o valor "null", para que a lista nao seja ordenada.
            return null;
        }
    }

    /*************************************************************
     *                     EXPRESSOES LAMBDA                     *
    *************************************************************/

    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por Nome utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> nomeC = (p1, p2) -> {
        String nome1 = p1.getNome() + " " + p1.getSobreNome();
        String nome2 = p2.getNome() + " " + p2.getSobreNome();
        return nome1.compareTo(nome2);
    };

    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por Peso utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> pesoC = (p1, p2) -> {
        float peso1 = p1.getPeso();
        float peso2 = p2.getPeso();
        return Float.compare(peso1, peso2);
    };

    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por Altura utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> alturaC = (p1, p2) -> {
        float altura1 = p1.getAltura();
        float altura2 = p2.getAltura();
        return Float.compare(altura1, altura2);
    };
    
    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por IMC utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> imcC = (p1, p2) -> {
        float imc1 = p1.calculaIMC();
        float imc2 = p2.calculaIMC();
        return Float.compare(imc1, imc2);
    };
    
    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por Sexo utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> sexoC = (p1, p2) -> {
        int sexo1 = (p1 instanceof Mulher) ? 1 : 2;
        int sexo2 = (p2 instanceof Mulher) ? 1 : 2;
        return Integer.compare(sexo1, sexo2);
    };

    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por Idade utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> idadeC = (p1, p2) -> {
        int idade1 = p1.getIdade();
        int idade2 = p2.getIdade();
        return Integer.compare(idade1, idade2);
    };

    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por Data de Nascimento utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> dataNascC = (p1, p2) -> {
        LocalDate dataNasc1 = p1.getDataNasc();
        LocalDate dataNasc2 = p2.getDataNasc();
        return dataNasc1.compareTo(dataNasc2);
    };

    /**
     * Expressao lambda para ordenar {@code ArrayList<PessoaIMC>}
     * por CPF utilizando a interface {@code Comparator}.
     */
    private Comparator<PessoaIMC> cpfC = (p1, p2) -> {
        long numCPF1 = p1.getNumCPF();
        long numCPF2 = p2.getNumCPF();
        return Long.compare(numCPF1, numCPF2);
    };
}