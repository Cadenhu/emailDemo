package com.demo.javamail;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExchangeEmail
 * @Description
 * @Author 74716
 * @Date 2019/5/29 18:01
 * @Version 1.0
 **/
public class ExchangeEmail {
    public static boolean sendEmail() {

        Boolean flag = false;
        try {
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP1);
            ExchangeCredentials credentials = new WebCredentials("21@xx.com", "xx");
            service.setCredentials(credentials);
            service.setUrl(new URI("https://baidu.com/EWS/Exchange.asmx"));
            EmailMessage msg = new EmailMessage(service);
            msg.setSubject("测试邮件");
            msg.setBody(MessageBody.getMessageBodyFromText("测试邮件内容"));
            msg.getToRecipients().add("xx@xx.com");
//            List<String> CCUser=new ArrayList<>(3);
//            CCUser.add("xx@xx.com");
//            msg.getCcRecipients().addSmtpAddressRange(CCUser.iterator());
            //msg.getAttachments().addFileAttachment("123.xls",fileInputStream);
            msg.send(); //发送
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    public static void main(String[] args) {
        sendEmail();
    }
}
