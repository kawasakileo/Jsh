package br.unifil.dc.sisop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;

/**
 * A classe Jsh é a classe principal do projeto e contém os métodos que
 * compõem a interface do terminal e controlam sua execução.
 *
 * @author Leonardo Kawasaki e Leonardo Lima
 * @version 200913
 */
public final class Jsh {

    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) throws IOException {
        promptTerminal();
    }

    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {}

    /**
    * Funcao principal do Jsh.
    */
    public static void promptTerminal() throws IOException {
        boolean aux = true;
        while (aux) {
    		exibirPrompt();
    		ComandoPrompt comandoEntrado = lerComando();
            aux = executarComando(comandoEntrado);
    	}
    }

    public static void exibirPrompt() throws IOException {
        usuario_nome = System.getProperty("user.name");
        usuario_diretorio = System.getProperty("user.dir");
        usuario_UID = gerarUUID();
        System.err.print(usuario_nome + "#" + usuario_UID + ":" + usuario_diretorio + "%" );
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
        return new ComandoPrompt(line);
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
    public static boolean executarComando(ComandoPrompt comando) throws IOException {
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
                ComandosInternos.criarNovoDiretorio(comando.getArgumentos());
                return true;
            case "ad":
                ComandosInternos.apagarDiretorio(comando.getArgumentos());
                return true;
            case "mdt":
                ComandosInternos.mudarDiretorioTrabalho(comando.getArgumentos().get(0));
                return true;
            default:
                executarPrograma(comando);
                System.out.println(
                        "\n" + "Comando ou programa ao identificado! Segue abaixo a lista de comandos existentes" + "\n" +
                        "'relogio' : Informa a data e a hora do sistema" + "\n" +
                        "'la' : Informa a lista de arquivos e pastas existentes no diretorio atual" + "\n" +
                        "'cd' : Cria um novo diretorio com o argumento informado" + "\n" +
                        "'ad' : Apaga o diretorio com o argumento informado" + "\n" +
                        "'mdt' : Muda o diretorio atual para o diretorio do argumento informado" + "\n");
                return true;
        }
    }

    public static void executarPrograma(ComandoPrompt comando) throws IOException {
        File userdir = new File(Paths.get("").toAbsolutePath().toString());
        File[] arquivos = userdir.listFiles();

        List<String> finalList = new ArrayList<>();

        assert arquivos != null;
        for(File arquivo : arquivos) {
            String convertString = arquivo.toString();
            String trocandoBarras = convertString.replaceAll("\\\\", "/");
            String[] stringFinal = trocandoBarras.split("/");
            finalList.add(stringFinal[stringFinal.length-1]);
        }

//        if(finalList.contains(comando)) {
//            String executar = userdir.toString() + "\\" + comando;
//            Process proc = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL cmd /c \""+executar+"\"");
//        } else {
//        }
    }

    /**
     * Método que obtém o UID do usuario
     * @return retorna um int com o valor obtido.
     */
    public static int gerarUUID() throws IOException {
        int uid;

        String comando = "id -u " + username;
        Process comandoUID = Runtime.getRuntime().exec(comando);
        InputStream saidaProcesso = comandoUID.getInputStream();

        byte[] arrSaida = saidaProcesso.readAllBytes();
        uid = arrSaida[0];

        return uid;
    }

    public static String usuario_nome;
    public static String usuario_diretorio;
    public static int usuario_UID;
    private static final String username = System.getProperty("user.name");
}
