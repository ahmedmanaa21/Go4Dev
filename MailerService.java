package Servicess;

public class MailerService {
    public void replyMail(String mail ,String Username , String Description) {
        String from = "yourmail";
        String pass = "yourpass";
        String[] to = {"" + mail}; // list of recipient email addresses
        String subject = "AIDE REPLY";
        String body = "Greetings Mr  "+Username.toUpperCase()+ " We would like to inform you that your aide has been answered as this :  \n "+Description+" . \n For more Please CONTACT our CLIENT SUPPORT AT AIDES.COM/SUPPORT \n Best Regards \n";
        MailService serv = new MailService();
        serv.sendFromGMail(from,pass,to,subject,body);
    }
}
