package com.sam.pdfbox;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class GmailInbox {
	/*
	 * public static void main(String[] args) {
	 * 
	 * GmailInbox gmail = new GmailInbox(); gmail.read();
	 * 
	 * }
	 * 
	 * public void read() {
	 * 
	 * Properties props = new Properties();
	 * 
	 * try { props.load(new FileInputStream( new File(
	 * "D:\\EclipseWorkspace\\pdfbox\\src\\main\\java\\com\\sam\\pdfbox\\smtp.properties"
	 * ))); Session session = Session.getDefaultInstance(props, null);
	 * 
	 * Store store = session.getStore("imaps"); store.connect("smtp.gmail.com",
	 * "akulasam202@gmail.com", "samba@8271");
	 * 
	 * Folder inbox = store.getFolder("inbox"); inbox.open(Folder.READ_ONLY); int
	 * messageCount = inbox.getMessageCount();
	 * 
	 * System.out.println("Total Messages:- " + messageCount);
	 * 
	 * Message[] messages = inbox.getMessages();
	 * System.out.println("------------------------------");
	 * 
	 * for (int i = 0; i < 10; i++) { System.out.println("Mail Subject:- " +
	 * messages[i].getSubject()); }
	 * 
	 * inbox.close(true); store.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	public static void check(String host, String storeType, String user, String password) {
		try {

			// create properties field
			Properties properties = new Properties();

			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "993");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());

				/*
				 * Object text = message.getContent();
				 * 
				 * text.toString()
				 */

			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String host = "smtp.gmail.com";// change accordingly
		String mailStoreType = "pop3";
		String username = "akulasam202@gmail.com";// change accordingly
		String password = "samba@8271";// change accordingly

		check(host, mailStoreType, username, password);

	}

}
