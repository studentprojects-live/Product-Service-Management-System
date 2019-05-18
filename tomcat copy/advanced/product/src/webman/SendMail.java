package webman;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;


public class SendMail
{

//eg:  private static final String SMTP_HOST_NAME = "myserver.smtphost.com";
	private static final String SMTP_HOST_NAME = "net21";
//eg: private static final String SMTP_AUTH_USER = "myusername";
//eg: private static final String SMTP_AUTH_PWD  = "mypwd";
	private static final String SMTP_AUTH_USER = "administrator";
	private static final String SMTP_AUTH_PWD  = "";
 

  private static  String emailMsgTxt      = "Online Order Confirmation Message.Send U R quotation.";
  private static  String emailSubjectTxt  = "Order Confirmation Subject";
  private static  String emailFromAddress = "nag@webman.com";
  private static  String[] emailList = {"phani_prasad@webman.com", "nag_bugs@webman.com"};

  public SendMail(){}
  public SendMail(String[] emailList,String emailSubjectTxt,String emailMsgTxt,String emailFromAddress)
  {
		this.emailList=emailList;
		this.emailSubjectTxt=emailSubjectTxt;
		this.emailMsgTxt=emailMsgTxt;
		this.emailFromAddress=emailFromAddress;
  }

  public static void main(String args[]) throws Exception
  {
    SendMail smtpMailSender = new SendMail();
    smtpMailSender.postMail( emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress);
    System.out.println("Sucessfully Sent mail to All Users");
  }

  public void postMail( String recipients[ ], String subject,
                            String message , String from) throws MessagingException
  {
    boolean debug = false;

     Properties props = new Properties();
     props.put("mail.smtp.host", SMTP_HOST_NAME);
     props.put("mail.smtp.auth", "true");

    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getDefaultInstance(props, auth);

    session.setDebug(debug);


    Message msg = new MimeMessage(session);


    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);

    InternetAddress[] addressTo = new InternetAddress[recipients.length];
    for (int i = 0; i < recipients.length; i++)
    {
        addressTo[i] = new InternetAddress(recipients[i]);
    }
    msg.setRecipients(Message.RecipientType.TO, addressTo);

    msg.setSubject(subject);
    msg.setContent(message, "text/plain");
    Transport.send(msg);
 }

private class SMTPAuthenticator extends Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        return new PasswordAuthentication(username, password);
    }
}

}


