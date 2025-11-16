import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MinhaListaOrdenavel {

    // ENUM CRITERIOS
    public enum Criterio {
        // Criado para suportar as possibilidades de ordenacao
        NOME_AZ(1), 
        NOME_ZA(2), 
        PESO_CRESCENTE(3), 
        PESO_DECRESCENTE(4),
        IMC_CRESCENTE(5), 
        IMC_DECRESCENTE(6), 
        GENERO_M_H(7), GENERO_H_M(8), // Mulher -> Homem e o inverso
        IDADE_CRESCENTE(9), 
        IDADE_DECRESCENTE(10),
        DATA_NASCIMENTO_AZ(11), 
        DATA_NASCIMENTO_ZA(12), // Mais Velho -> Mais Novo
        CPF_CRESCENTE(13), 
        CPF_DECRESCENTE(14);
        
        private final int valor;

        Criterio(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
        
        public static Criterio fromInt(int valor) {
            for (Criterio c : Criterio.values()) {
                if (c.valor == valor) {
                    return c;
                }
            }
            return null; // Retorna null se o valor nao for valido
        }
    }
    
    // Objeto ArrayList interno que coleciona objetos PessoaIMC.
    private ArrayList<PessoaIMC> listaPessoas;

    public MinhaListaOrdenavel() {
        this.listaPessoas = new ArrayList<>();
    }

    // METODOS BASICOS

    public void add(PessoaIMC p) {
        this.listaPessoas.add(p);
    }

    public PessoaIMC get(int index) {
        // Incluida validacao de indice por boa pratica.
        if (index < 0 || index >= this.listaPessoas.size()) {
            throw new IndexOutOfBoundsException("Indice invalido: " + index + ". O array tem " + this.listaPessoas.size() + " elementos.");
        }
        return this.listaPessoas.get(index);
    }

    // COMPARADORES PRIVADOS
    // Comparador por Nome (A-Z)
    private Comparator<PessoaIMC> nomeC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            // Compara Nome, se for igual, usa Sobrenome
            int resultado = p1.getNome().compareTo(p2.getNome());
            if (resultado != 0) {
                return resultado;
            }
            return p1.getSobreNome().compareTo(p2.getSobreNome());
        }
    };

    // Comparador por Peso (Crescente)
    private Comparator<PessoaIMC> pesoC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Float.compare(p1.getPeso(), p2.getPeso());
        }
    };

    // Comparador por IMC (Crescente)
    private Comparator<PessoaIMC> imcC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Float.compare(p1.calculaIMC(), p2.calculaIMC());
        }
    };

    // Comparador por Genero (Mulher -> Homem)
    private Comparator<PessoaIMC> generoC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            // Mulher (0) < Homem (1)
            int genero1 = (p1 instanceof Mulher) ? 0 : 1; 
            int genero2 = (p2 instanceof Mulher) ? 0 : 1;
            return Integer.compare(genero1, genero2);
        }
    };

    // Comparador por Idade (Crescente - Mais Jovem primeiro)
    private Comparator<PessoaIMC> idadeC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Integer.compare(p1.getIdade(), p2.getIdade());
        }
    };

    // Comparador por Data de Nascimento (Crescente - Mais Velho primeiro)
    private Comparator<PessoaIMC> dataNascimentoC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            // GregorianCalendar possui o metodo compareTo.
            return p1.getDataNasc().compareTo(p2.getDataNasc());
        }
    };

    // Comparador por CPF (Crescente)
    private Comparator<PessoaIMC> cpfC = new Comparator<PessoaIMC>() {
        @Override
        public int compare(PessoaIMC p1, PessoaIMC p2) {
            return Long.compare(p1.getNumCPF(), p2.getNumCPF());
        }
    };

    // METODO DE ORDENACAO
    //Ordena a lista de PessoaIMC baseado no criterio fornecido (int).
    public ArrayList<PessoaIMC> ordena(int criterioInt) {
        
        Criterio criterio = Criterio.fromInt(criterioInt);

        if (criterio == null) {
            throw new IllegalArgumentException("Criterio de ordenacao invalido: " + criterioInt);
        }

        // Cria uma copia da lista interna para ordenar.
        ArrayList<PessoaIMC> listaOrdenada = new ArrayList<>(this.listaPessoas);
        Comparator<PessoaIMC> comparador = null;

        switch (criterio) {
            // NOME
            case NOME_AZ:
                comparador = nomeC;
                break;
            case NOME_ZA:
                comparador = nomeC.reversed();
                break;

            // PESO
            case PESO_CRESCENTE:
                comparador = pesoC;
                break;
            case PESO_DECRESCENTE:
                comparador = pesoC.reversed();
                break;

            // IMC
            case IMC_CRESCENTE:
                comparador = imcC;
                break;
            case IMC_DECRESCENTE:
                comparador = imcC.reversed();
                break;

            // GENERO
            case GENERO_M_H:
                comparador = generoC;
                break;
            case GENERO_H_M:
                comparador = generoC.reversed();
                break;

            // IDADE
            case IDADE_CRESCENTE:
                comparador = idadeC;
                break;
            case IDADE_DECRESCENTE:
                comparador = idadeC.reversed();
                break;

            // DATA DE NASCIMENTO 
            case DATA_NASCIMENTO_AZ:
                comparador = dataNascimentoC;
                break;
            case DATA_NASCIMENTO_ZA:
                comparador = dataNascimentoC.reversed();
                break;

            // CPF
            case CPF_CRESCENTE:
                comparador = cpfC;
                break;
            case CPF_DECRESCENTE:
                comparador = cpfC.reversed();
                break;
        }
        
        // Aplica a ordenacao usando a classe Collections.
        Collections.sort(listaOrdenada, comparador);

        return listaOrdenada;
    }
}