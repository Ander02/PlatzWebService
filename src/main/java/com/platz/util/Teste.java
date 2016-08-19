package com.platz.util;

import com.platz.http.cadastro.ContaCadastro;
import com.platz.http.cadastro.UsuarioCadastro;
import com.platz.service.UsuarioService;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {
        ContaCadastro c = new ContaCadastro("chrisx@hotmail.com", "4152635214");
        c.setPerfil(2);
        UsuarioCadastro cadastro = new UsuarioCadastro(c, "nome do usuario", new Date(), "11912345678", "415.450.548-35", "");
        
        System.out.println(new UsuarioService().cadastrar(cadastro));  
    }
}
