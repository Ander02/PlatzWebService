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

        String html = "<html>"
                + "<head>"
                + "	<style type='text/css'>"
                + ""
                + "	*{"
                + "		margin: 0;"
                + "		padding: 0;"
                + "	}"
                + "	body{"
                + "		font-family: Arial;"
                + "		width: 75%;"
                + "		margin: 0 auto;"
                + "	}"
                + "	h1{"
                + "		font-size: 50px;"
                + "		font-style: italic;"
                + "		text-align: center;"
                + "		margin-bottom: 10px;"
                + "	}"
                + "	.mensagem{"
                + "		background-color: rgba(255,255, 0, .5);"
                + "		padding: 3px;"
                + "		font-size: 25px;"
                + "	}"
                + "	.resposta{"
                + "		font-size: 25px;"
                + "		background-color: rgba(0,255, 0, .5);"
                + "		padding: 3px;"
                + ""
                + "	}"
                + "	div h2{"
                + "		font-size: 30px;"
                + "		font-weight: bold;"
                + "	}"
                + "	</style>"
                + "</head>"
                + "<body>"
                + "	<h1>Platz</h1>"
                + "	<div class='mensagem' style='background-color: rgba(255,255, 0, .5);padding: 3px;font-size: 25px;'>\n"
                + "		<h2>Mensagem:</h2>"
                + "		<p>" + mensagem + "</p>"
                + "	</div>"
                + "	<div class='resposta' style='background-color: rgba(0,255, 0, .5);font-size: 25px;padding: 3px;'>\n"
                + "		<h2>Resposta:</h2>"
                + "		<p>" + resposta + "</p>"
                + "	</div>"
                + "</body>"
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
