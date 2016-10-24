package com.platz.util;

import java.io.InputStream;

/**
 *
 * @author Anderson
 */
public class ImagemUtil {

    public final String RAIZ = "C:/platzImg/";
    public static final String URL_FTP = "ftp://localhost/";

    //Método de salvar arquivo que recebe o caminho e o arquivo como inputStream
    public boolean salvarArquivo(String diretorio, String nomeDoArquivo, InputStream inputStream) {

        try {

            FtpUtil ftp = new FtpUtil();
            
            //ftp.criarDiretorios(diretorio);
            
            //ftp.desconectar();
            
            //ftp = new FtpUtil();
            
            ftp.uploadArquivoFTP(inputStream, nomeDoArquivo, diretorio);
                        
            ftp.desconectar();

            /*
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
             */
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao fazer upload do arquivo " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public InputStream baixarImagem(String hostRemoto) {

        try {
            FtpUtil ftp = new FtpUtil();
            String caminhoReduzido = hostRemoto.replaceFirst(ImagemUtil.URL_FTP, "");

            return ftp.downloadArquivoFTP(caminhoReduzido);

        } catch (Exception e) {
            System.out.println("Erro ao Fazer Download: " + e.getMessage());
            return null;
        }

    }

    public boolean deletarArquivo(String caminhoDoArquivo) {

        try {
            FtpUtil ftp = new FtpUtil();

            String caminhoReduzido = caminhoDoArquivo.replaceFirst(ImagemUtil.URL_FTP, "");

            //System.out.println(caminhoDoArquivo);
            boolean ok = ftp.deletarArquivoFTP(caminhoReduzido);

            if (ok) {
                ftp.desconectar();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir arquivo: " + e.getMessage());
            return false;
        }
    }

}
