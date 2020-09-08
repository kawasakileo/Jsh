package br.unifil.dc.sisop;

import java.util.Scanner;
import java.util.UUID;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {
    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) {

        promptTerminal();
    }


    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {}

    /**
    * Funcao principal do Jsh.
    */
    public static void promptTerminal() {
        while (true) {
    		exibirPrompt();
    		ComandoPrompt comandoEntrado = lerComando();
    		executarComando(comandoEntrado);
    	}
    }

    /**
    * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
    * terminal está pronto para receber o próximo comando como entrada.
    */
    public static void exibirPrompt() {
        System.out.print(username+"#"+uuid+"@"+userdir+" ");
    }

    /**
    * Preenche as strings comando e parametros com a entrada do usuario do terminal.
    * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
    * outras palavras subsequentes sao parametros para o comando. A palavras sao
    * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
    * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
    * EOL (End Of Line).
    *
    * @return
    */
    public static ComandoPrompt lerComando() {
        Scanner scanner =  new Scanner(System.in);
        String line = scanner.nextLine();
        ComandoPrompt read = new ComandoPrompt(line);
        return read;
    }

    /**
    * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
    * se for, o executa.
    *
    * Se nao for, verifica se é o nome de um programa terceiro localizado no atual
    * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
    * esse processo executa, o processo do uniterm deve permanecer em espera.
    *
    * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
    * programa desconhecido.
    */
    public static boolean executarComando(ComandoPrompt comando) {
        switch (comando.getNome()) {
            case "encerrar":
                return false;
            case "relogio":
                ComandosInternos.exibirRelogio();
                return true;
            case "la":
                ComandosInternos.escreverListaArquivos();
                return true;
            case "cd":
                ComandosInternos.criarNovoDiretorio(comando.getArgumentos().get(0));
                return true;
            case "ad":
                ComandosInternos.apagarDiretorio(comando.getArgumentos().get(0));
                return true;
            case "mdt":
                ComandosInternos.mudarDiretorioTrabalho(comando.getArgumentos().get(0));
                return true;
            default:
                System.out.println("\n" + "Comando ou programa ao identificado! Segue abaixo a lista de comandos existentes" + "\n" +
                        "'relogio' : Informa a data e a hora do sistema" + "\n" +
                        "'la' : Informa a lista de arquivos e pastas existentes no diretorio atual" + "\n" +
                        "'cd' : Cria um novo diretorio com o argumento informado" + "\n" +
                        "'ad' : Apaga o diretorio com o argumento informado" + "\n" +
                        "'mdt' : Muda o diretorio atual para o diretorio do argumento informado" + "\n");
                return true;
        }
    }

    public static int executarPrograma(ComandoPrompt comando) {
        throw new RuntimeException("Método ainda não implementado.");
    }

    public static long gerarUUID(){
        long tempo = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            UUID.randomUUID();
        }
        long uuid = System.currentTimeMillis() - tempo;
        return uuid;
    }

    private static long uuid = gerarUUID();
    private static String username = System.getProperty("user.name");
    private static String userdir = System.getProperty("user.dir");
}
