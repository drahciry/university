// Bibliotecas utilizadas
import java.util.GregorianCalendar;
import java.util.Calendar;

public class ValidaData { 
    // Enum para entradas com nomes dos meses
    public enum Meses { 
        JANEIRO(1),
        FEVEREIRO(2),
        MARCO(3),
        ABRIL(4),
        MAIO(5),
        JUNHO(6),
        JULHO(7),
        AGOSTO(8),
        SETEMBRO(9),
        OUTUBRO(10),
        NOVEMBRO(11),
        DEZEMBRO(12);
        
        private final int numero;
        
        Meses(int numero) {
            this.numero = numero;
        }
        
        public int getNumero() {
            return numero;
        }

        // Metodo que verifica se a entrada bate com algum do enum
        private static Meses buscaPorNomeInterna(String nomeMes) {
            if (nomeMes == null) return null;

            try {
                String nomeNormalizado = nomeMes.trim().toUpperCase();
        
                if (nomeNormalizado.isEmpty()) return null;
        
                return Meses.valueOf(nomeNormalizado); 
            } catch (IllegalArgumentException e) {
                return null; // Nao encontrou
            }
        }

        // Apenas verifica se a busca interna retornou uma constante (nao nula)
        public static boolean isNomeValido(String nomeMes) {
            return buscaPorNomeInterna(nomeMes) != null;
        }
        
        public static int toInt(String nomeMes) {
            Meses mesEncontrado = buscaPorNomeInterna(nomeMes);
            
            if (mesEncontrado != null) {
                return mesEncontrado.getNumero(); // Retorna o numero do mes
            } else {
                return -1; // Retorna o valor de erro/invalido
            }
        }
    }

    // Metodo que recebe String ou int e retorna int
    public static int toInt(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return -1;
        }
        try {
            // Garante que 0 a esquerda sao absorvidos.
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Função auxiliar para checar se o ano eh bissexto
    private static boolean isAnoBissexto(int ano) {
        // Regra de Ano Bissexto:
        // Divisivel por 400
        // OU Divisível por 4 E NAO divisivel por 100
        return (ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0);
    }

    // Metodo que verifica se o dia esta entre 1 e 31 inclusive
    public static boolean isDia(int dia){
        // A checagem mais rigorosa (dia vs. mes) eh feita em isDataValida.
        return dia >= 1 && dia <= 31;
    }
    
    // Sobrecarga isDia(String)
    public static boolean isDia(String dia) {
        return isDia(toInt(dia));
    }

    // Metodo que recebe o nome de um mes e o verifica
    public static boolean isMes(String nomeMes) {
        // isMes deve aceitar string numerica (1 a 12) E string por extenso.
        int mesNumero = toInt(nomeMes);
        
        if (isMes(mesNumero)) {
            return true;
        }
        
        // Verifica se eh nome por extenso
        return Meses.isNomeValido(nomeMes);
    }

    // Metedo que recebe o numero do mes e verifica
    public static boolean isMes(int mes){
        // Meses validos de 1 a 12.
        return mes >= 1 && mes <= 12;
    }

    // Metodo que verifica se o ano informado esta entre (ANO_MINIMO) ateh o (ANO_ATUAL)
    public static boolean isAno(int ano) {
        // Calcula o ano atual no momento da execução
        GregorianCalendar calendario = new GregorianCalendar();
        int anoCorrente = calendario.get(Calendar.YEAR); 

        // Calcula o ano mínimo dinamicamente
        int anoMinimo = anoCorrente - 120;

        return ano >= anoMinimo && ano <= anoCorrente;
    }
    
    // Sobrecarga isAno(String)
    public static boolean isAno(String ano) {
        return isAno(toInt(ano));
    }

    // Sobrecarga isDataValida(int, int, int)
    public static boolean isDataValida(int dia, int mes, int ano) {
        // Obtem data atual no momento da execucao
        GregorianCalendar hoje = new GregorianCalendar();
        int anoAtual = hoje.get(Calendar.YEAR);
        int mesAtual = hoje.get(Calendar.MONTH) + 1; // Os meses sao de 0 a 11
        int diaAtual = hoje.get(Calendar.DAY_OF_MONTH);

        // (Dia, Mes, e Ano individuais)
        if (!isDia(dia) || !isMes(mes) || !isAno(ano)) return false;

        // O isAno ja garante que o ano nao eh futuro (ano > ANO_ATUAL)
        // Caso em que o ano fornecido eh igual ao ANO_ATUAL
        if (ano == anoAtual) {
            // Mes futuro
            if (mes > mesAtual) {
                return false; // Ex: Mes 11 (Novembro) eh futuro em Outubro
            }
        
            // Mes atual e dia futuro
            if (mes == mesAtual && dia > diaAtual) {
            return false; // Ex: Dia 30 eh futuro no dia 29.
            }
        }

        // (Dias vs. Mes)
        // Meses com 30 dias: Abril (4), Junho (6), Setembro (9), Novembro (11)
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            // Se o dia for 31, eh invalido para estes meses.
            return dia <= 30;
        }
        
        // Fevereiro (2)
        if (mes == 2) {
            int maxDia = 28;
            
            // Verifica se o ano eh bissexto para permitir o dia 29.
            if (isAnoBissexto(ano)) {
                maxDia = 29;
            }
            
            // Se o dia for maior que o maximo permitido (28 ou 29), eh invalido.
            return dia <= maxDia;
        }
        
        // Meses com 31 dias (Jan, Mar, Mai, Jul, Ago, Out, Dez): 
        // A checagem inicial isDia(dia) >= 1 && <= 31 ja eh suficiente para estes.
        return true;
    }

    // Sobrecarga isDataValida(String, String, String)
    public static boolean isDataValida(String dia, String mes, String ano) {
        // Conversao de dia e ano para int
        int diaNumeral = toInt(dia);
        int anoNumeral = toInt(ano);

        // Se o dia ou o ano nao puderam ser convertidos para um numero valido, retorna falso.
        if (!isDia(diaNumeral) || !isAno(anoNumeral)) {
            return false;
        }

        // O mes pode ser string numerica ou por extenso
        int mesNumero;
        
        int mesNumeral = toInt(mes);
        
        if (isMes(mesNumeral)) { // Eh numerico valido
            mesNumero = mesNumeral;
        } else { // Tenta por nome (extenso)
            mesNumero = Meses.toInt(mes);
        }

        if (mesNumero == -1) return false; // Falhou na conversao numerica e nominal
        
        return isDataValida(diaNumeral, mesNumero, anoNumeral);
    }
}