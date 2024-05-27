//package utilities;
//
//
//import jakarta.activation.DataHandler;
//import jakarta.activation.DataSource;
//import jakarta.activation.FileDataSource;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeBodyPart;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.mail.internet.MimeMultipart;
//import org.testng.annotations.Test;
//import java.util.Properties;
//
//
//
//public class emailUtils {
//
//    @Test
//    public static void sendEmail(String dirPathForOutput, String outputFileName, String lineOfCenter, String timeStamp) {
//
//        // change accordingly
//        final String username = "senderMail.com";
//
//        // change accordingly
//        final String password = "nm46A_7rK!d6&FF]p9pRBH=jh";
//
//        // Get system properties
//        Properties props = System.getProperties();
//
//        // Assuming you are sending email from localhost
//        String host = "smtp.office365.com";
//        String port="587";
//
//        // Set system properties
//        Properties properties = System.getProperties();
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.debug", "true");
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.ssl.enable", "false");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        //Add below property to debug the smtp failure like auth or host property
////        properties.put("mail.debug", "true");
//
//        // Creating Session instance referenced to Authenticator object to pass in Session.getInstance argument
//        Session session = Session.getInstance(props,
//                new Authenticator() {
//
//                    //override the getPasswordAuthentication method
//                    protected PasswordAuthentication
//                    getPasswordAuthentication() {
//                        return new PasswordAuthentication(username,
//                                password);
//                    }
//                });
//
//        System.out.println("~~~~~~  Preparing to send out an email with the results for the run. ~~~~~~");
//        try {
//
//            // compose the message
//            Message message = new MimeMessage(session);
//
//            // Add from field data
//            message.setFrom(new InternetAddress(username));
//
//            // EMAIL RECIPIENTS
//            String recipientEmails = "testMail@test.com";
//            String CCRecipients = "";
//
//            // Setting the recipients here.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(recipientEmails));
//
//            // Setting the CC recipients here.
//            message.setRecipients(Message.RecipientType.CC,
//                    InternetAddress.parse(CCRecipients));
//
//            //Preparing Body Text part
//            Multipart multipart = new MimeMultipart("mixed");
//            MimeBodyPart bodyTextPart = new MimeBodyPart();
//
//            if(lineOfCenter.equals("PC"))
//                message.setSubject("Automated Test Results for Policy Transactions(BOD vs PC) Comparison: " + timeStamp);
//            else
//                message.setSubject("Automated Test Results for Commissions(BOD vs BC) Comparison: " + timeStamp);
//
//            bodyTextPart.setText("Hi All,\n" +
//                    "\n" +
//                    "Ivans Test results from " + timeStamp + ". We have attached the Output file in here. \n" +
//                    "\n" +
//                    "Let us know if you have any questions. \n" +
//                    "\n" +
//                    "\n" +
//                    "Thank You, \n" +
//                    "\n" +
//                    "Automation Team\n");
//
//            //Preparing Attachment part
//            BodyPart attachmentPart = new MimeBodyPart();
////            String dirPathForOutput = "C:\\Users\\Ivans_Output\\Output_TestingProdRun_20211013";
////            String outputFileName = "Output_TestingProdRun_20211013";
//
//            outputFileName = outputFileName + ".xlsx";
//            String file = dirPathForOutput + ".xlsx";
//            DataSource source = new FileDataSource(file);
//            attachmentPart.setDataHandler(new DataHandler(source));
//            attachmentPart.setFileName(outputFileName);
//
//            // Set text message part
//            multipart.addBodyPart(bodyTextPart);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~    Body Text Added      ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//            // Set Attachment part
//            multipart.addBodyPart(attachmentPart);
//            System.out.println("~~~~~~~~~~~~~~~    Attachment Attached Successfully    ~~~~~~~~~~~~~~~~~~~~");
//
//            //Set Body content
//            message.setContent(multipart);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~    Sending Email        ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//            //Send Message
//            Transport.send(message);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~    Sent Successfully    ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//        } catch (Exception e) {
//            System.err.println("~~~~~~~~~~~~~~~~~~~~~~~    Error Sending mail:  ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//}
//
