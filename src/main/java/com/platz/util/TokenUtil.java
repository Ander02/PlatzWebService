package com.platz.util;

import com.platz.dao.ContaDao;
import com.platz.model.ContaModel;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class TokenUtil {

    public String criarToken(String id) {

        try {
            return new EncriptAES().byteParaString(new EncriptAES().encrypt(id + "," + new DataUtil().converterData(new DataUtil().adicionaDias(1, new Date())), EncriptAES.getChaveEncriptacao()));

        } catch (Exception e) {
            System.out.println("Erro ao produzir o token: " + e.getMessage());
            return null;
        }
    }

    public boolean isValid(String token) {

        try {
            String tokenDesc = new EncriptAES().decrypt(new EncriptAES().stringParaByte(token), EncriptAES.getChaveEncriptacao());

            return new DataUtil().converterData(tokenDesc.split(",")[1]).after(new Date());

        } catch (Exception e) {
            System.out.println("Erro ao validar token" + e.getMessage());
            return false;
        }

    }

}
