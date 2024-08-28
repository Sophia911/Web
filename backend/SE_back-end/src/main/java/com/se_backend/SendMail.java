package com.se_backend;

import com.se_backend.DB.DB_object.OfficialMails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;


abstract public class SendMail {

    //发送邮件
    static boolean sentMail(String mailAdr, String subject, String content, OfficialMails officialMails ){
        if(officialMails == null) {
            System.out.println("官方邮箱获取失败");
            return false;
        }
        //配置发送邮件的环境
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host",officialMails.getServerType());
        properties.put("mail.smtp.port","465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.enable", true);
        //设置安全证书
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        //配置发件邮箱、密码
        properties.put("mail.user",officialMails.getOfficialMail());
        properties.put("mail.password",officialMails.getSecretKey());
        //构建授权信息，以便进行SMTP验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        //使用环境与授权信息构建会话
        Session mailSession = Session.getInstance(properties, authenticator);
        //创建邮件信息
        MimeMessage message = new MimeMessage(mailSession);
        //准备进行发件
        try {
            //设置发件人
            InternetAddress from = new InternetAddress(properties.getProperty("mail.user"));
            message.setFrom(from);
            //设置收件人
            InternetAddress to = new InternetAddress(mailAdr);
            message.setRecipients(Message.RecipientType.TO, String.valueOf(to));
            System.out.println(properties.getProperty("mail.user")+mailAdr);
            //设置标题
            message.setSubject(subject);
            //设置内容
            message.setContent(content,"text/html;charset=UTF-8");
            //发送邮件
            Transport.send(message);
            return true;
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean Sent(String mailAdr, String subject, String content, OfficialMails officialMails) throws IOException {
        return sentMail(mailAdr, subject, content, officialMails);
    }
}
