package com.platz.util;

import com.platz.model.CategoriaModel;

/**
 *
 * @author Anderson
 */
public class Teste {
    public static void main(String[] args) throws InterruptedException {
        CategoriaModel c = new CategoriaModel();
        Thread.sleep(10000);
        System.out.println("ax"+c.getId());
        
    }
}
