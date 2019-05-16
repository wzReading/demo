package com.qf.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmail {
	public static final String HOSTACCOUNT = "13755257350@163.com";
	public static final String EMAILSERVERADDRESS= "smtp.163.com";
	public static final String HOSTPASSWORD = "zzs123456";
	
	public static void send(String toEmail,String verificationCode,int typeCode) throws Exception{
		Properties prop = new Properties();
		prop.setProperty("mail.host", EMAILSERVERADDRESS);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(prop);
		session.setDebug(true);
		Transport transport = session.getTransport();
		transport.connect(EMAILSERVERADDRESS,HOSTACCOUNT,HOSTPASSWORD);
		Message message = createSipleMail(session,toEmail,verificationCode,typeCode);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	private static Message createSipleMail(Session session,String toEmail,String verificationCode,int typeCode) throws Exception {
		
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(HOSTACCOUNT));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject("邮箱验证");
		
		String content = "";
		if(typeCode == 1){
			content = "您的注册验证码为<a>"+verificationCode+"<a>,输入即可验证";
		}else if(typeCode == 0){
			content = "正在执行修改操作，<a href='http://localhost:8080/w/toUpdatePassword?email='"+toEmail+">点我修改密码</a>"
					+ "<br>如果被拦截，请复制一下网址在浏览器打开http://localhost:8080/w/toUpdatePassword?email="+toEmail;
		}
		message.setContent(content, "text/html;charset=UTF-8");
		
		message.setSentDate(new Date());
		
		message.saveChanges();
		return message;
	}
}
