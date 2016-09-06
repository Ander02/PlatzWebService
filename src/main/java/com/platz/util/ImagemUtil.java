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

    public final String RAIZ = "C:/";

    //Método de salvar arquivo que recebe o caminho e o arquivo como inputStream
    public void salvarArquivo(String caminhoDoArquivo, InputStream inputStream) {

        try {

            //Criação das variáveis que serão necessárias para o upload
            OutputStream outputStream = new FileOutputStream(new File(caminhoDoArquivo));
            int read = 0;
            byte[] bytes = new byte[1024];

            //Salvando o arquivo
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();

            System.out.println("Upload Concluído");

        } catch (Exception e) {
            System.out.println("Erro ao fazer upload do arquivo" + e.getMessage());
            e.printStackTrace();
        }
    }

}
