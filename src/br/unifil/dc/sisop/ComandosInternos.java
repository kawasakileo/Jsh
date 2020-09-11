package br.unifil.dc.sisop;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
    /** Essa classe não deve ser instanciada. */
    private ComandosInternos() {}

    public static void exibirRelogio() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

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

    public static void criarNovoDiretorio(List<String> args) {
        String caminhoCompleto = gerarCaminhoAbsoluto(Optional.of(args.get(0)));
        new File(caminhoCompleto).mkdir();
    }

    public static void apagarDiretorio(List<String> args) {
        String caminhoCompleto = gerarCaminhoAbsoluto(Optional.of(args.get(0)));
        File dirASerApagado = new File(caminhoCompleto);
        if ((dirASerApagado.exists()) && (dirASerApagado.isDirectory())) {
            if (dirASerApagado.delete()) {
                System.out.println("Deletado com sucesso");
            } else {
                List<String> listaArquivos =
                        Collections.singletonList(dirASerApagado.listFiles().toString());
                apagarDiretorio(listaArquivos);
            }
        } else if (!dirASerApagado.exists()) {
            System.out.println("Esse diretorio não existe </3");
        } else if (!dirASerApagado.isDirectory()) {
            System.out.println("Isso não é um diretorio");
        }
    }

    public static String gerarCaminhoAbsoluto(Optional<String> dir) {
        String caminhoRetorno;
        String barraDoSistema = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        caminhoRetorno = dir.map(s -> userDir + barraDoSistema + s).orElse(userDir);
        return caminhoRetorno;
    }

    public static void mudarDiretorioTrabalho(String nomeDir) {
        File directory;
        directory = new File(nomeDir).getAbsoluteFile();
        if (directory.exists()) {
            System.setProperty("user.dir", directory.getAbsolutePath());
        } else {
            System.out.println("Diretorio nao encontrado!");
            return;
        }
        System.out.println("Diretorio alterado com sucesso!");
    }
}
