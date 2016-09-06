package com.platz.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Anderson
 */
public class ImagemUtil {

    public final String RAIZ = "C:/platzImg/";

    //Método de salvar arquivo que recebe o caminho e o arquivo como inputStream
    public boolean salvarArquivo(String diretorio, String nomeDoArquivo, InputStream inputStream) {

        try {

            //Cria a pasta do arquivo
            new File(diretorio).mkdirs();

            //Criação das variáveis que serão necessárias para o upload          
            OutputStream outputStream = new FileOutputStream(diretorio + nomeDoArquivo);
            int read = 0;
            byte[] bytes = new byte[2048];

            //Salvando o arquivo
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            System.out.println("Upload Concluído");

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao fazer upload do arquivo " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void deletarArquivo(String caminhoDoArquivo) {

        try {
            //Pega o arquivo
            File file = new File(caminhoDoArquivo);

            //Apaga o arquivo
            file.delete();

        } catch (Exception e) {
            System.out.println("Erro ao excluir arquivo: " + e.getMessage());

        }
    }

}
