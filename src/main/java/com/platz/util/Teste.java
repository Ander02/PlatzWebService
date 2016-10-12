package com.platz.util;

/**
 *
 * @author Anderson
 */
public class Teste {

    // Main method to invoke the above methods
    public static void main(String[] args) {
        try {
            FtpUtil ftp = new FtpUtil("localhost", 21, "admin", "");

            //ftp.uploadFTPFile("C:\\Users\\Jose da Cruz\\Documents\\Anderson e Andre\\Anderson\\Apostila C#.pdf", "C#.pdf", "/");
            //ftp.downloadFTPFile("/C#.pdf", "C:\\Users\\Jose da Cruz\\Desktop\\teste.pdf");
            //System.out.println("FTP File downloaded successfully");
            ftp.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
