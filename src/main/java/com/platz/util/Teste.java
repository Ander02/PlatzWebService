package com.platz.util;

import com.platz.controller.ContaController;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {

         
        try {
            System.out.println("ok   " + new EncriptAES().decrypt(new EncriptAES().stringParaByte(new ContaController().buscarPorId("57dace0658cf2005a8e03723").getToken()), EncriptAES.getChaveEncriptacao()));
            
        } catch (Exception e) {
            System.out.println("jksgfuyjhsdb f");
            e.printStackTrace();
        }
        
         
    }
}
