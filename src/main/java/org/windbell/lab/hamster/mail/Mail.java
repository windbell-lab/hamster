package org.windbell.lab.hamster.mail;

import java.awt.Toolkit;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Mail {
	private static final String FROM_MAIL = "XXXXXX@163.com";
	private static final String TOM_MAIL = "XXXXXX@qq.com";
	private static final String MAIL_SENDER = "mailSender";
	private static final String ENCODING = "utf-8";
	public ApplicationContext ctx = null;

	public Mail() {
		ctx = new ClassPathXmlApplicationContext("application-mail.xml");
	}

	/**
	 * 发送简单邮件
	 */
	public void sendSimpleMail() {
		JavaMailSender sender = (JavaMailSender) ctx.getBean(MAIL_SENDER);
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setTo(TOM_MAIL);// 接受者
			mail.setFrom(FROM_MAIL);// 发送者,这里还可以另起Email别名，不用和xml里的username一致
			mail.setSubject("spring mail test!");// 主题
			mail.setText("springMail的简单发送测试");// 邮件内容
			sender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送HTML格式邮件
	 * 
	 * @throws MessagingException
	 */
	public void sendHtmlMail() throws MessagingException {
		// 获取JavaMailSender bean
		JavaMailSender sender = (JavaMailSender) ctx.getBean(MAIL_SENDER);
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, ENCODING);
		try {
			messageHelper.setTo(TOM_MAIL);// 接受者
			messageHelper.setFrom(FROM_MAIL);// 发送者
			messageHelper.setSubject("测试邮件");// 主题
			// 邮件内容，注意加参数true，表示启用html格式
			messageHelper.setText("<html><head></head><body><h1>"
					+ "hello!!chao.wang" + "</h1></body></html>", true);
			sender.send(mailMessage);
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void sendMimeMail() throws MessagingException {
		// 获取JavaMailSender bean
		JavaMailSender sender = (JavaMailSender) ctx.getBean(MAIL_SENDER);
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, ENCODING);
		try {
			messageHelper.setTo(TOM_MAIL);// 接受者
			messageHelper.setFrom(FROM_MAIL);// 发送者
			messageHelper.setSubject("测试邮件");// 主题
			// 邮件内容，注意加参数true
			messageHelper.setText("<html><head></head><body><h1>"
					+ "hello!!chao.wang" + "</h1></body></html>", true);

			// 附件内容
//			messageHelper.addInline("a", new File("E:/办公网站地址.txt"));
//			messageHelper.addInline("b", new File(
//					"C:\\Users\\Administrator\\Pictures\\2016-04-19-0.bmp"));
			File file = new File("E:/xml校验设计文档.docx");
			// 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题
			messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()),
					file);
			sender.send(mailMessage);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws MessagingException {
		Mail mail = new Mail();
		mail.sendSimpleMail();
		mail.sendHtmlMail();
		mail.sendMimeMail();
	}
}