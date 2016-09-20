package com.platz.util;

/**
 *
 * @author Anderson
 */
public class Teste {
    
    public static void main(String[] args) throws Exception {
        
        //new EmailUtil().enviarEmailSimples("chrisxchris2010@hotmail.com", "Teste de email Simples","Um Teste", "Uma resposta para o teste");
        new EmailUtil().enviarEmailComHtml("chrisxchris2010@hotmail.com", "Teste de email com HTML","Um Teste", "Uma resposta para o teste em html");
    }
}
