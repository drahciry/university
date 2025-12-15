package lp2g04.bib;

public class ValidaCPF {
    // Metodo que verifica se o formato de entrada eh valido e, caso seja, retorna apenas os digitos do CPF
    private static String normalizaCPF(String cpf){
        if (cpf == null) return null;

        String regexValida = 
            "^\\d{11}$" + // apenas 11 digitos
            "|" +
            "^\\d{3}\\.\\d{3}\\.\\d{3}[-/]\\d{2}$"; // Formato: xxx.xxx.xxx-xx ou xxx.xxx.xxx/xx

        if (!cpf.matches(regexValida)) return null; // Recusa por formato invalido 

        // Remove tudo que nao for digito
        return cpf.replaceAll("\\D", "");
    }

    // Metodo que verifica se o CPF eh valido
    // Nao pode ter 11 digitos iguais
    // E possui uma logica matematica para os dois ultimos digitos
    public static boolean isCPF(String cpf) {
        String cpfNormalizado = normalizaCPF(cpf);

        if (cpfNormalizado == null) {
        return false;
        }

        // Nao aceita uma sequencia de digitos iguais
        if (cpfNormalizado.matches("(\\d)\\1{10}")) return false;

        // Conversao para facilitar na conta futuramente
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) digitos[i] = cpfNormalizado.charAt(i) - '0';

        // --------- Calculo dos digitos verificadores ---------
        // 1o digito (dv1)
        int soma1 = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * peso; peso--;
        }

        int resto1 = soma1 % 11;
        int dv1 = (resto1 < 2) ? 0 : (11 - resto1);

        // dv1 deve ser igual ao 10o digito do cpf
        if (dv1 != digitos[9]) return false;

        // 2o digito (dv2)
        int soma2 = 0;
        peso = 11; // inclui o dv1

        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * peso; peso--;
        }

        int resto2 = soma2 % 11;
        int dv2 = (resto2 < 2) ? 0 : (11 - resto2);

        // dv2 deve ser igual ao 11o digito do cpf
        if (dv2 != digitos[10]) return false;

        // Se passar por todas as verificacoes
        return true;
    }
    
    // Metodo que imprime o CPF no formato xxx.xxx.xxx-xx
    public static String imprimeCPF(String cpf) {
        String cpfNormalizado = normalizaCPF(cpf);

        if (cpfNormalizado == null) 
            throw new IllegalArgumentException("O CPF fornecido nao esta em um formato valido para formataçao: 12345678901, 123.456.789-01, ou 123.456.789/01.\"");
        
        return  cpfNormalizado.substring(0, 3) + "." +
                cpfNormalizado.substring(3, 6) + "." +
                cpfNormalizado.substring(6, 9) + "-" +
                cpfNormalizado.substring(9, 11);
    }

    // Metodo que transforma o CPF que esta em String para o primitivo long
    public static long toLong(String cpf) {
        if (isCPF(cpf) == false)
            throw new IllegalArgumentException("O CPF fornecido nao esta correto.\"");
        
        String cpfNormalizado = normalizaCPF(cpf);
        
        // Converte a string para o tipo long de forma segura
        // pois o metodo normalizaCPF ja garante 11 digitos
        return Long.parseLong(cpfNormalizado);
    }
}