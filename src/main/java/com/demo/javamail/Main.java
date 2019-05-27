/**
 * Copyright (C), 2015-2019
 * FileName: Main
 * Author:   huhu
 * Date:     2019/5/27 20:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.demo.javamail;

import javax.mail.*;
import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author huhu
 * @create 2019/5/27
 * @since 1.0.0
 */
public class Main {


    private static final String sendUserAccount="";
    private static final String sendUserPassword="Abc123456";
    private static final String receiveEmail="248@qq.com";

    public static void main(String[] args) throws Exception {
        sendEmail();
        //sendMail("测试发邮件");
    }

    private static void sendEmail() throws Exception {
        Properties props = new Properties();
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.163.com");
        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session);
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();

        try {
            //设置发件人的账户名和密码
            transport.connect(sendUserAccount, sendUserPassword);
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg,msg.getAllRecipients());
        } finally {
            //5、关闭邮件连接
            transport.close();
        }
    }

    /**
     * 获得创建一封邮件的实例对象
     * @param session
     * @return
     * @throws MessagingException
     */
    public static MimeMessage getMimeMessage(Session session) throws Exception{
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(sendUserAccount));
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveEmail));
        //设置邮件主题
        msg.setSubject("主题","UTF-8");
        //设置邮件正文
        msg.setContent("发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件", "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());

        return msg;
    }


}