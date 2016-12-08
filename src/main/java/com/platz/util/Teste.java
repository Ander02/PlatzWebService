package com.platz.util;

import com.platz.controller.AvaliacaoController;
import com.platz.controller.CurtidaController;
import com.platz.controller.EventoController;
import com.platz.controller.PresencaController;
import com.platz.dao.EventoDao;
import com.platz.http.leitura.EventoLeitura;
import com.platz.model.EventoModel;
import com.platz.model.TipoPresenca;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Teste {

    // Main method to invoke the above methods
    public static void main(String[] args) {
//        System.out.println(new TokenUtil().isValid("824a87871903439b8be46f797c59ad5bd472f865b3a7eff3e323caf9119b485685e8046f92566dea7a952f0cc1a70351"));        
        //new EventoController().TopNEventos(3);        
        /*
        int a = 3;
        int b = a - (a % 3);        
        System.out.println(b);*/

        //desenvolvimento de buscar eventos por curtidos, avaliação e participação
        /*
        int max = 3;
        int terco = max / 3;
        int resto = max % 3;
        List<EventoModel> eventos = new EventoDao().buscarNaoCanceladosENaoCensurados();
        
        List<EventoModel> eventosCurtidos = new ArrayList<>();
        int[] curtidas = new int[eventos.size()];
        
        List<EventoModel> eventosParticipacoes = new ArrayList<>();
        int[] participacoes = new int[eventos.size()];
        
        List<EventoModel> eventosNotas = new ArrayList<>();
        double[] notas = new double[eventos.size()];
        
        for (int i = 0; i < eventos.size(); i++) {
            EventoModel evento = eventos.get(i);
            System.out.println(evento.getNome());
            
            curtidas[i] = new CurtidaController().buscarPeloEvento(evento).size();
            System.out.println("curtidas " + curtidas[i]);
            
            participacoes[i] = new PresencaController().buscartipoPresenca(TipoPresenca.SIM, evento).size();
            System.out.println("participação " + participacoes[i]);
            
            notas[i] = new AvaliacaoController().mediaPorEvento(evento);
            
            System.out.println("media " + notas[i]);
            System.out.println("");
        }
        
        for (int i = 0; i < curtidas.length; i++) {
            if (i < terco + resto) {
                eventosCurtidos.add(i, eventos.get(i));
            } else {
                for (int j = 0; j < terco + resto; j++) {
                    
                    if (curtidas[j] == curtidas[i]) {                        
                        
                    }
                    
                }
            }
            
        }
         */
 /*
        HashMap<Integer, Integer> teste = new HashMap<>();
        teste.put(1, 404);
        
        ArrayList<String> strings = new ArrayList<>();
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String t, String t1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
         */
    }
}
