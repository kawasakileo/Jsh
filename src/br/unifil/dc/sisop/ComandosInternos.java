package br.unifil.dc.sisop;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe que contém os métodos com os comandos fundamentais do prompt.
 *
 * @author Leonardo Kawasaki
 * @author Leonardo Lima
 * @version 20200913
 */
public final class ComandosInternos {

    /** Essa classe não deve ser instanciada. */
    private ComandosInternos() {}

    /**
     * Método sem retorno que exibe o relógio com a data e horário atual.
     */
    public static void exibirRelogio() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    /**
     * Método sem retorno que exibe a lista de arquivos que estão contidos
     * no diretório atual.
     */
    public static void escreverListaArquivos() {
        String diretorioAtual = System.getProperty("user.dir");
        File userdir = new File(Paths.get(diretorioAtual).toAbsolutePath().toString());
        System.out.println(userdir);
        File[] arquivos = userdir.listFiles();

        assert arquivos != null;
        for (File arquivo : arquivos) {
            String convertString = arquivo.toString();
            String trocandoBarras = convertString.replaceAll("\\\\", "/");
            String[] stringFinal = trocandoBarras.split("/");
            System.out.println(stringFinal[stringFinal.length - 1]);
        }
    }

    /**
     * Método sem retorno que cria um novo diretório na localização
     * atual do usuário.
     *
     * @param args lista de String que contém o caminho.
     */
    public static void criarNovoDiretorio(List<String> args) {
        String caminhoCompleto = gerarCaminhoAbsoluto(Optional.of(args.get(0)));
        new File(caminhoCompleto).mkdir();
    }

    /**
     * Método sem retorno que apaga determinado diretório contido na
     * localização atual do usuário.
     *
     * @param args lista de String que contém o caminho.
     */
    public static void apagarDiretorio(List<String> args) {
        String caminhoCompleto = gerarCaminhoAbsoluto(Optional.of(args.get(0)));
        File dirASerApagado = new File(caminhoCompleto);
        if ((dirASerApagado.exists()) && (dirASerApagado.isDirectory())) {
            if (dirASerApagado.delete()) {
                System.out.println("Deletado com sucesso.");
            } else {
                List<String> listaArquivos =
                        Collections.singletonList(dirASerApagado.listFiles().toString());
                apagarDiretorio(listaArquivos);
            }
        } else if (!dirASerApagado.exists()) {
            System.out.println("Diretório não encontrado.");
        } else if (!dirASerApagado.isDirectory()) {
            System.out.println("Isso não é um diretorio.");
        }
    }

    /**
     * Método auxiliar que gera o caminho absoluto.
     *
     * @param dir Optional, que contḿe o caminho, exista.
     * @return retorna uma String que contém valor do caminho absoluto.
     */
    public static String gerarCaminhoAbsoluto(Optional<String> dir) {
        String caminhoRetorno;
        String barraDoSistema = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        caminhoRetorno = dir.map(s -> userDir + barraDoSistema + s).orElse(userDir);
        return caminhoRetorno;
    }

    /**
     * Método sem retorno que faz a mudança do diretório, a partir da
     * localização atual do usuário.
     *
     * @param nomeDir String que contém o nome do diretório para qual o
     * usuário será direcionado.
     */
    public static void mudarDiretorioTrabalho(String nomeDir) {
        File directory;
        directory = new File(nomeDir).getAbsoluteFile();
        if (directory.exists()) {
            System.setProperty("user.dir", directory.getAbsolutePath());
        } else {
            System.out.println("Diretório nao encontrado.");
            return;
        }
        System.out.println("Diretório atual alterado.");
    }
}
