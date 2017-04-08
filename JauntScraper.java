package JauntyLittleScraper;

import com.jaunt.Element;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class JauntScraper {
  public static void main(String[] args) throws InterruptedException{
    
    //infinite loop to continue running scraper
    while(true){
     scraper();  
    }
  }
  
  public static void scraper()throws InterruptedException{
    //add your keyword here. Rudimentary, make this a regex for better searches
    String keyword = "class";
        
        //Scrape
        try{
          UserAgent userAgent = new UserAgent(); 
          userAgent.visit("http://www.example.com");
          
          //Can print the HTML doc here to scrape for more information
          System.out.println(userAgent.doc.innerHTML()); 
          
          //Finds any matches for the keyword
          String searching = userAgent.doc.findEach(keyword).getText();
          System.out.print(searching);
          
          //Used to print links for visual queues if you actively running the scraper
          Element anchor = userAgent.doc.findEvery("<a href>");
          Element search = userAgent.doc.findEvery(keyword);
          System.out.println("anchor's href attribute: " + anchor.getAt("href")+ "\n");
          System.out.println("anchor's parent Element: " + anchor.getParent());
          

          //Comment block these lines if you are actively watching the output
          if(searching.equals(keyword)){
            sendMail();
          }
          
         //Stop the program from running for an hour.
         //Edit this number if you want to do more scraping
	 //Milliseconds
         Thread.sleep(3600000);
        }
        catch(JauntException e){      
          System.err.println(e);
          Thread.sleep(3600000);
        }
  }
  
  public static void sendMail(){  
      // Add the email address to send this to
      String to = "abcd@gmail.com";

      // Add the email address which this is from
      String from = "web@gmail.com";

      // Sending from localhost? or other?
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Set mail server info up here
      properties.setProperty("mail.smtp.host", host);

      // Create session
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Sets From
         message.setFrom(new InternetAddress(from));

         // Sets To
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject
         message.setSubject("This is the Subject Line!");

         // Set message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }
      catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}


