package com.platz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author Anderson
 */
public class FtpUtil {

    // Creating FTP Client instance
    FTPClient ftp = null;

    // Constructor para a Conexão com o Servidor FTP
    public FtpUtil(String host, int port, String usuario, String senha) throws Exception {

        ftp = new FTPClient();
        //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host, port);

        //System.out.println("FTP URL:" + ftp.getDefaultPort());
        reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }

        ftp.login(usuario, senha);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    public FtpUtil() throws Exception {
        ftp = new FTPClient();
        //ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect("localhost", 21);

        //System.out.println("FTP URL:" + ftp.getDefaultPort());
        reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }

        ftp.login("admin", "");
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    public boolean criarDiretorios(String diretorio) throws Exception {

        String[] pathElements = diretorio.split("/");
        System.out.println(Arrays.toString(pathElements));
        if (pathElements != null && pathElements.length > 0) {
            for (String singleDir : pathElements) {
                boolean exist = this.ftp.changeWorkingDirectory(singleDir);
                if (!exist) {
                    boolean ok = this.ftp.makeDirectory(singleDir);
                    if (ok) {
                        System.out.println("Diretório: " + singleDir + " criado com sucesso");
                        this.ftp.changeWorkingDirectory(singleDir);
                    } else {
                        System.out.println("Não foi possível criar o diretório: " + singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Method to upload the File on the FTP Server
    public void uploadArquivoFTP(String caminhoLocalDoArquivo, String diretorioDoUpload, String nomeDoArquivo) throws Exception {
        try {

            System.out.println(diretorioDoUpload);

            this.criarDiretorios(diretorioDoUpload);

            InputStream arquivo = new FileInputStream(new File(caminhoLocalDoArquivo));

            this.ftp.storeFile(nomeDoArquivo, arquivo);
        } catch (Exception e) {
            System.out.println("Erro ao Fazer upload do Arquivo por FTP: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to upload the File on the FTP Server
    public void uploadArquivoFTP(InputStream arquivo, String nomeDoArquivo, String diretorioDoUpload) throws Exception {
        try {

            if (this.criarDiretorios(diretorioDoUpload)) {

                boolean ok = this.ftp.storeFile(nomeDoArquivo, arquivo);

                if (!ok) {
                    System.out.println("Não foi possivel subir o arquivo");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao Fazer upload do Arquivo por FTP: " + e.getMessage());
        }
    }

    // Download the FTP File from the FTP Server
    public void downloadArquivoFTP(String hostRemoto, String localDownload) {

        try {
            FileOutputStream fos = new FileOutputStream(localDownload);
            this.ftp.retrieveFile(hostRemoto, fos);
        } catch (IOException e) {
            System.out.println("Erro ao Fazer download do Arquivo por FTP: " + e.getMessage());
        }
    }

    public InputStream downloadArquivoFTP(String hostRemoto) {

        try {
            return ftp.retrieveFileStream(hostRemoto);
        } catch (IOException e) {
            System.out.println("Erro ao Fazer download do Arquivo por FTP: " + e.getMessage());
            return null;
        }
    }

    public boolean deletarArquivoFTP(String caminhoDoArquivo) {
        try {

            boolean status = this.ftp.deleteFile(caminhoDoArquivo);

            if (status) {
                System.out.println("Deletou o Arquivo");
                return status;
            }
            return !status;

        } catch (Exception e) {
            System.out.println("Erro ao deletar arquivo pelo FTP: " + e.getMessage());
            return false;
        }
    }

    // Disconnect the connection to FTP
    public void desconectar() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException e) {
                // do nothing as file is already saved to server
                e.printStackTrace();
            }
        }
    }
}
