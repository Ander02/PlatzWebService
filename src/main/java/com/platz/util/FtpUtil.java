package com.platz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host, port);

        System.out.println("FTP URL:" + ftp.getDefaultPort());

        reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }

        ftp.login(usuario, senha);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    // Method to upload the File on the FTP Server
    public void uploadFTPFile(String caminhoLocalDoArquivo, String nomeDoArquivo, String diretorioDoUpload) throws Exception {
        try {
            InputStream arquivo = new FileInputStream(new File(caminhoLocalDoArquivo));

            this.ftp.storeFile(diretorioDoUpload + nomeDoArquivo, arquivo);
        } catch (Exception e) {
            System.out.println("Erro ao Fazer upload do Arquivo por FTP: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to upload the File on the FTP Server
    public void uploadFTPFile(InputStream arquivo, String nomeDoArquivo, String diretorioDoUpload) throws Exception {
        try {

            this.ftp.storeFile(diretorioDoUpload + nomeDoArquivo, arquivo);
        } catch (Exception e) {
            System.out.println("Erro ao Fazer upload do Arquivo por FTP: " + e.getMessage());
        }
    }

    // Download the FTP File from the FTP Server
    public void downloadFTPFile(String hostRemoto, String localDownload) {

        try (FileOutputStream fos = new FileOutputStream(localDownload)) {
            this.ftp.retrieveFile(hostRemoto, fos);
        } catch (IOException e) {
            System.out.println("Erro ao Fazer download do Arquivo por FTP: " + e.getMessage());
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
