package br.unifil.dc.sisop;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
 * Classe que contém os métodos que controlam os método recebidos pelo
 * prompt.
 *
 * @author Leonardo Kawasaki
 * @author Leonardo Lima
 * @version 20200913
 */
public class ComandoPrompt {

    /**
     * Classe que lê o comando digitado pelo usuário no prompt.
     *
     * @param comando String que contém o comando passado pelo usuário no prompt.
     */
    public ComandoPrompt(String comando) {
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
