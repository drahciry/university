// Importaçoes uteis a classe
import java.time.LocalDate;

/**
 * Classe com metodos estaticos voltados para a validaçao de data, com metodos como:
 * Validar dia inteiro ou string, validar mes inteiro ou string, validar ano inteiro ou string,
 * validar uma data inteira (dia, mes e ano) com entradas inteiras ou strings.
 */
public final class ValidaData {
    /*************************************************************
     *                        CONSTRUTOR                         *
    *************************************************************/

    /**
     * Construtor PRIVADO. Nao pode ser instanciado com new.
     */
    private ValidaData() {
        // Apenas para que nao seja possivel instanciar um objeto de uma classe de ajuda.
    }

    /*************************************************************
     *                          METODOS                          *
    *************************************************************/

    /**
     * Metodo estatico que recebe um mes como String e
     * converte para o valor correspondente em inteiro.
     * 
     * @param mes (String): Mes escrito por extenso.
     * 
     * @return int: Retorna o valor inteiro correspondente ao mes.
     * 
     * @exception InvalidMonthException Caso o mes por extenso inserido
     * nao seja um mes valido, o metodo lancara uma excecao.
     */
    public static int converteMes(String mes) throws InvalidMonthException {
        // Normaliza a string do mes para que tente associar a instancia correta do enum.
        String mesNormalizado = mes.trim().toUpperCase();
        Mes mesEnum;
        try {
            // Tentativa de instanciar o enum com o mes por extenso passado.
            mesEnum = Mes.valueOf(mesNormalizado);
        } catch (IllegalArgumentException e) {
            // Caso nao seja possivel, uma RuntimException sera lancada pelo metodo. 
            throw new InvalidMonthException(mes + " nao eh valido/nao existe.", e);
        }
        // Retorna o valor inteiro associado ao mes dentro do enum.
        return mesEnum.getMesInteiro();
    }

    /**
     * Metodo estatico que recebe um ano como e verifica se eh ano bissexto.
     * 
     * @param ano (int): Ano que sera verificado se eh bissexto.
     * 
     * @return boolean: Retorna um valor booleano dizendo se eh ou nao bissexto.
     */
    private static boolean isBissexto(int ano) {
        // Primeiro verifica se eh um ano valido. Se nao for, retorna falso.
        if (!isAno(ano)) return false;
        // Aqui ocorre a verificacao de ano bissexto. Se for divisivel por 4,
        // eh bissexto. Mas se for divisivel por 100, nao eh bissexto, a menos
        // que o ano seja divisivel por 400, sendo, desta forma, bissexto.
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    /**
     * Metodo estatico que recebe um ano como e verifica se eh ano bissexto.
     * 
     * @param ano (String): Ano que sera verificado se eh bissexto.
     * 
     * @return boolean: Retorna um valor booleano dizendo se eh ou nao bissexto.
     */
    private static boolean isBissexto(String ano) {
        // Primeiro verifica se eh um ano valido. Se nao for, retorna falso.
        if (!isAno(ano)) return false;
        // Converte o ano de String para int utilizando parseInt() da classe padrao Integer.
        int anoInteiro = Integer.parseInt(ano);
        // Aqui ocorre a verificacao de ano bissexto. Se for divisivel por 4,
        // eh bissexto. Mas se for divisivel por 100, nao eh bissexto, a menos
        // que o ano seja divisivel por 400, sendo, desta forma, bissexto.
        return (anoInteiro % 4 == 0 && anoInteiro % 100 != 0) || (anoInteiro % 400 == 0);
    }

    /**
     * Metodo estatico que recebe um dia e verifica se eh um dia valido.
     * 
     * @param dia (int): Dia que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o dia validado.
     */
    private static boolean isDia(int dia) {
        // Nao ha como haver dias nao positivos e 
        // o maximo de dias que ha em um unico mes eh igual a 31.
        return (dia >= 1 && dia <= 31);
    }

    /**
     * Metodo estatico que recebe um mes e verifica se eh um mes valido.
     * 
     * @param mes (int): Mes que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o mes validado.
     */
    private static boolean isMes(int mes) {
        // Nao ha como haver mes como inteiro nao positivo ou maior que 12.
        return (mes >= 1 && mes <= 12);
    }

    /**
     * Metodo estatico que recebe um ano e verifica se eh um ano valido.
     * 
     * @param ano (int): Ano que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o ano validado.
     */
    private static boolean isAno(int ano) {
        // Utilizacao da classe LocalDate para obter o tempo atual e nao gerar obsolencia.
        LocalDate now = LocalDate.now();
        // Os anos somente serao validos se estiver em um intervalo de:
        // [ano atual - 120; ano atual].
        return (ano >= (now.getYear() - 120) && ano <= now.getYear());
    }

    /**
     * Metodo estatico que recebe um dia e verifica se eh um dia valido.
     * 
     * @param dia (String): Dia que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o dia validado.
     * 
     * @exception InvalidDayException Quando a entrada nao for um dia valido,
     * ou seja, nao for um valor inteiro entre 1 e 31, sera lancada uma Runtime Exception.
     */
    private static boolean isDia(String dia) throws InvalidDayException {
        // Se a entrada como string for maior que 2, nao ha como ser um dia valido, nem mesmo no formato.
        if (dia.length() > 2) return false;
        int diaInteiro;
        // Bloco de try-catch para verificar se a entrada eh um inteiro.
        try {
            // Sendo inteiro, armazena valor utilizando parseInt().
            diaInteiro = Integer.parseInt(dia);
        } catch (NumberFormatException e) {
            // Caso a conversao de errado, a entrada era invalida, entao lanca uma excecao.
            throw new InvalidDayException(
                dia + " nao eh um dia. A entrada dever um inteiro no formato DD.", e
            );
        }
        // Nao ha como haver dias nao positivos e 
        // o maximo de dias que ha em um unico mes eh igual a 31.
        return (diaInteiro >= 1 && diaInteiro <= 31);
    }

    /**
     * Metodo estatico que recebe um mes e verifica se eh um mes valido.
     * O mes pode ser no formato inteiro ou por extenso.
     * 
     * @param mes (String): Mes que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o mes validado.
     * 
     * @exception InvalidMonthException Quando a entrada nao for um mes valido,
     * ou seja, nao for um valor inteiro entre 1 e 12 ou um mes por extenso escrito
     * corretamente, sera lancada uma Runtime Exception.
     */
    private static boolean isMes(String mes) throws InvalidMonthException {
        int mesInteiro;
        // Bloco de try-catch para verificar se a entrada eh um inteiro.
        try {
            // Sendo inteiro, armazena valor utilizando parseInt().
            mesInteiro = Integer.parseInt(mes);
        } catch (NumberFormatException e1) {
            // Caso nao seja uma entrada inteira, tenta converter entrada,
            // pois entrada pode ser o mes escrito por extenso (ex: "janeiro").
            try {
                mesInteiro = converteMes(mes);
            } catch (InvalidMonthException e2) {
                // Caso a conversao de errado, a entrada era invalida, entao lanca uma excecao.
                throw new InvalidMonthException(
                    mes + " nao eh um mes valido. A entrada dever um inteiro no formato MM ou um mes por extenso.", e2
                );
            }
        }
        // Nao ha como haver mes como inteiro nao positivo ou maior que 12.
        return (mesInteiro >= 1 && mesInteiro <= 12);
    }

    /**
     * Metodo estatico que recebe um ano e verifica se eh um ano valido.
     * 
     * @param ano (String): Ano que sera validado.
     * 
     * @return boolean: Retorna um valor booleano para o ano validado.
     * 
     * @exception InvalidYearException Quando a entrada nao for um ano valido,
     * ou seja, nao for um valor inteiro de ate 120 atras, sera lancada uma Runtime Exception.
     */
    private static boolean isAno(String ano) throws InvalidYearException {
        // Se a entrada como string tiver tamanho diferente de tamanho 4, nao eh um ano valido.
        // Todos os anos validos terao tamanho 4. Se nao tiver, retorna falso.
        if (ano.length() != 4) return false;
        int anoInteiro;
        // Bloco de try-catch para verificar se a entrada eh um inteiro.
        try {
            // Sendo inteiro, armazena valor utilizando parseInt().
            anoInteiro = Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            // Caso a conversao de errado, a entrada era invalida, entao lanca uma excecao.
            throw new InvalidYearException(
                ano + " nao eh um ano valido. A entrada dever um inteiro no formato AAAA ate 120 anos atras.", e
            );
        }
        // Utilizacao da classe LocalDate para obter o tempo atual e nao gerar obsolencia.
        LocalDate now = LocalDate.now();
        // Os anos somente serao validos se estiver em um intervalo de:
        // [ano atual - 120; ano atual].
        return (anoInteiro >= (now.getYear() - 120) && anoInteiro <= now.getYear());
    }

    /**
     * Metodo estatico que recebe uma data completa (dia, mes e ano)
     * e verifica se eh uma data valida.
     * 
     * @param dia (int): Dia da data.
     * @param mes (int): Mes da data.
     * @param ano (int): Ano da data.
     * 
     * @return boolean: Retorna um valor booleano para a data validada.
     */
    public static boolean isData(int dia, int mes, int ano) {
        // O ponto de partida eh a validacao de dia, mes e ano separadamente
        // para que a verificacao nao ocorra de forma duplicada,
        // ja que existem metodos para realizar esta validacao.
        if (!isDia(dia)) return false;
        if (!isMes(mes)) return false;
        if (!isAno(ano)) return false;
        // Utiliza data atual para validar a data.
        LocalDate now = LocalDate.now();
        int anoAtual = now.getYear();
        // Se o ano inserido for igual o ano corrente, o mes nao pode ser maior que o mes atual.
        if (anoAtual == ano) {
            // Utiliza o mes atual para validar a data.
            int mesAtual = now.getMonthValue();
            // Se o mes corrente for menor que o mes inserido, retorna falso.
            if (mesAtual < mes) return false;
            // Se o mes corrente for igual ao mes inserido, o dia nao pode ser maior que o dia atual.
            if (mesAtual == mes) {
                // Utiliza o dia atual para validar a data.
                int diaAtual = now.getDayOfMonth();
                // Se o dia atual for menor que o dia inserido, retorna falso. 
                if (diaAtual < dia) return false;
            }
         }
        // Armazena o dia limite de cada mes.
        int[] diasNoMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // Se o ano for bissexto, troca de 28 dias para 29 dias.
        if (isBissexto(ano)) diasNoMes[2] = 29;
        // Retorna um valor booleano verificando se a data corresponde
        // ao limite do mes informado, de acordo com os valores do array.
        return (dia <= diasNoMes[mes]);
    }

    /**
     * Metodo estatico que recebe uma data completa (dia, mes e ano)
     * e verifica se eh uma data valida.
     * 
     * @param dia (String): Dia da data.
     * @param mes (String): Mes da data.
     * @param ano (String): Ano da data.
     * 
     * @return boolean: Retorna um valor booleano para a data validada.
     * 
     * @exception InvalidDayException Quando a entrada nao for um dia valido,
     * ou seja, nao for um valor inteiro entre 1 e 31, sera lancada uma Runtime Exception.
     * @exception InvalidMonthException Quando a entrada nao for um mes valido,
     * ou seja, nao for um valor inteiro entre 1 e 12 ou um mes por extenso escrito
     * corretamente, sera lancada uma Runtime Exception.
     * @exception InvalidYearException Quando a entrada nao for um ano valido,
     * ou seja, nao for um valor inteiro de ate 120 atras, sera lancada uma Runtime Exception.
     */
    public static boolean isData(String dia, String mes, String ano) {
        // O ponto de partida eh a validacao de dia, mes e ano separadamente
        // para que a verificacao nao ocorra de forma duplicada,
        // ja que existem metodos para realizar esta validacao.
        if (!isDia(dia)) return false;
        if (!isMes(mes)) return false;
        if (!isAno(ano)) return false;
        // Converte as strings de dia e mes para inteiro.
        int diaInteiro = Integer.parseInt(dia);
        int anoInteiro = Integer.parseInt(ano);
        int mesInteiro;
        try {
            mesInteiro = Integer.parseInt(mes);
        } catch (NumberFormatException e) {
            mesInteiro = converteMes(mes);
        }
        // Utiliza data atual para validar a data.
        LocalDate now = LocalDate.now();
        int anoAtual = now.getYear();
        // Se o ano inserido for igual o ano corrente, o mes nao pode ser maior que o mes atual.
        if (anoAtual == anoInteiro) {
            // Utiliza o mes atual para validar a data.
            int mesAtual = now.getMonthValue();
            // Se o mes corrente for menor que o mes inserido, retorna falso.
            if (mesAtual < mesInteiro) return false;
            // Se o mes corrente for igual ao mes inserido, o dia nao pode ser maior que o dia atual.
            if (mesAtual == mesInteiro) {
                // Utiliza o dia atual para validar a data.
                int diaAtual = now.getDayOfMonth();
                // Se o dia atual for menor que o dia inserido, retorna falso. 
                if (diaAtual < diaInteiro) return false;
            }
         }
        // Armazena o dia limite de cada mes.
        int[] diasNoMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // Se o ano for bissexto, troca de 28 dias para 29 dias.
        if (isBissexto(ano)) diasNoMes[2] = 29;
        // Retorna um valor booleano verificando se a data corresponde
        // ao limite do mes informado, de acordo com os valores do array.
        return (diaInteiro <= diasNoMes[mesInteiro]);
    }
}