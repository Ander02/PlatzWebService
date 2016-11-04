package com.platz.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Anderson
 */
public class EmailUtil {

    public void enviarEmailSimples(String destino, String assunto, String mensagem, String resposta) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setDebug(true); //Debug do email
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.setSmtpPort(587); //Porta
        email.setAuthentication("platz.dev@gmail.com", "8010897116122");
        email.addTo(destino); //destinatário
        email.setFrom("platz.dev@gmail.com", "Platz"); // remetente
        email.setSubject(assunto); // assunto do e-mail

        email.setMsg("A nossa resposta para sua mensagem:\n"
                + mensagem + " é: \n"
                + resposta); //conteudo do e-mail

        email.setSSLOnConnect(true); //Segurança
        email.send(); //envia o e-mail
        System.out.println("Email enviado!");

    }

    public void enviarEmailComHtml(String destino, String assunto, String mensagem, String resposta) throws EmailException {

        HtmlEmail email = new HtmlEmail();
        email.setDebug(true); //Debug do email
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.setSmtpPort(587); //Porta
        email.setAuthentication("platz.dev@gmail.com", "8010897116122");
        email.addTo(destino); //destinatário
        email.setFrom("platz.dev@gmail.com", "Platz"); // remetente
        email.setSubject(assunto); // assunto do e-mail
        email.setSSLOnConnect(true); //Segurança

        String html = "<html>\n"
                + "<head>\n"
                + " <style type='text/css'>\n"
                + "\n"
                + " *{\n"
                + "  margin: 0;\n"
                + "  padding: 0;\n"
                + "}\n"
                + "body{\n"
                + "  background: #ecf0f1;\n"
                + "  font-family: Arial;\n"
                + "  width: 75%;\n"
                + "  margin: 0 auto;\n"
                + "}\n"
                + ".card\n"
                + "{\n"
                + "  background: #fff;\n"
                + "  margin: 10%;\n"
                + "  padding: 100px;\n"
                + "  webkit-box-shadow: -2px 2px 20px -1px rgba(0,0,0,0.61);\n"
                + "  -moz-box-shadow: -2px 2px 20px -1px rgba(0,0,0,0.61);\n"
                + "  box-shadow: -2px 2px 20px -1px rgba(0,0,0,0.61);\n"
                + "}\n"
                + ".card h1{\n"
                + "  font-size: 50px;\n"
                + "  font-style: italic;\n"
                + "  text-align: center;\n"
                + "  margin-bottom: 10px;\n"
                + "}\n"
                + ".mensagem\n"
                + "{\n"
                + "  padding: 3px;\n"
                + "  margin: 50px;\n"
                + "  font-size: 25px;\n"
                + "}\n"
                + ".mensagem p\n"
                + "{\n"
                + "  margin: 20px;\n"
                + "  text-align: center;\n"
                + "}\n"
                + ".resposta{\n"
                + "  font-size: 25px;\n"
                + "  padding: 20px;\n"
                + "  margin: 50px;\n"
                + "  background: rgba(44, 62, 80,0.3);\n"
                + "  border-radius: 15px;\n"
                + "\n"
                + "}\n"
                + ".resposta p\n"
                + "{\n"
                + "  text-align: center;\n"
                + "  margin: 20px;\n"
                + "}\n"
                + "div h2{\n"
                + "  font-size: 30px;\n"
                + "  font-weight: bold;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <div class=\"card\">\n"
                + "   <h1>Platz - Suas Rotas, Seus Eventos.</h1>\n"
                + "   <div class=\"mensagem\" >\n"
                + "     <h2>Mensagem:</h2>\n"
                + "     <p> " + mensagem + "</p>\n"
                + "   </div>\n"
                + "   <div class=\"resposta\" >\n"
                + "    <h2>Resposta:</h2>\n"
                + "    <p>" + resposta + " </p>\n"
                + "  </div>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";

        email.setHtmlMsg(html);
        email.setTextMsg("Seu provedor de email não suporta emails em HTML...\n "
                + "Mas a nossa resposta para sua mensagem: \n "
                + mensagem + "é: \n"
                + resposta);

        email.send(); //envia o e-mail
        System.out.println("Email com HTML enviado");
    }

}
