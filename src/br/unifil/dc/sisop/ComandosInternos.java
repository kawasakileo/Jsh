package br.unifil.dc.sisop;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}

    public static int exibirRelogio() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return 0;
    }

    public static int escreverListaArquivos() {
        File userdir = new File(Paths.get("").toAbsolutePath().toString());
        File[] arquivos = userdir.listFiles();
        for(File arquivo : arquivos){
            System.out.println(arquivo.toString());
        }
        return 0;
    }

    public static int criarNovoDiretorio(String nomeDir) {
        File file = new File(nomeDir);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Diretorio "+"'"+nomeDir+"'"+" criado com sucesso!");
                return 0;
            } else {
                System.out.println("Falha na criacao do diretorio, verifique se o diretorio ja nao existe!");
                return 0;
            }
        }
        System.out.println("Diretorio ja existe!");
        return 0;
    }

    public static int apagarDiretorio(String nomeDir) {
        File file = new File(nomeDir);
        if (!file.exists()) {
            System.out.println("Diretorio nao encontrado!");
        } else {
            file.delete();
            System.out.println("Diretorio "+"'"+nomeDir+"'"+" apagado!");
        }
        return 0;
    }

    public static int mudarDiretorioTrabalho(String nomeDir){
        File directory;
        directory = new File(nomeDir).getAbsoluteFile();
        if (directory.exists()) {
            System.setProperty("user.dir", directory.getAbsolutePath());
        }else{
            System.out.println("Diretorio nao encontrado!");
            return 0;
        }
        System.out.println("Diretorio alterado com sucesso!");
        return 0;
    }
}
