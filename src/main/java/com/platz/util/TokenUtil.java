package com.platz.util;

import java.util.Date;

/**
 *
 * @author Anderson
 */
public class TokenUtil {

    public String criarToken(String id) {

        try {
            return new EncriptAES().byteParaString(new EncriptAES().encrypt(id + ":" + new Date().toString(), EncriptAES.getChaveEncriptacao()));

        } catch (Exception e) {
            System.out.println("Erro ao produzir o token: " + e.getMessage());
            return null;
        }
    }

}
