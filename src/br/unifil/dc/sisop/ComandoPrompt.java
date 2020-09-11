package br.unifil.dc.sisop;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
 * Write a description of class ComandoPrompt here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public class ComandoPrompt {

    public ComandoPrompt(String comando) {
        // ESCREVA AQUI SEU CODIGO PARA ESTRUTURAR O COMANDO RECEBIDO DO PROMPT.
        String[] arr;
        if(comando.indexOf(" ") > 0) {
            arr = comando.split(" ");
            nome = arr[0];
            argumentos = new String[arr.length -1];
            for(int i = 1; i < arr.length; i++) {
                argumentos[i - 1] = arr[1];
            }
        } else {
            nome = comando;
            argumentos = new String[1];
        }
    }

    /**
     * Método acessor get para o nome do comando.
     *
     * @return o nome do comando, exatamente como foi entrado.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método acessor get para os argumentos que seguram ao nome do comando.
     *
     * @return Lista de argumentos do comando, protegida contra modificações externas.
     */
    public List<String> getArgumentos() {
        return Collections.unmodifiableList(Arrays.asList(argumentos));
    }

    private final String nome;
    private final String[] argumentos;
}
