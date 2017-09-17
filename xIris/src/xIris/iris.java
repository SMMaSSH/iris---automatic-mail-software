package xIris;
//// /**
/////   * #lock#@wbp.parser.entryPoint#lock#
/// */

//#################################################### imports ##################################################

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.TextField;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JSeparator;
import java.awt.Label;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import javax.swing.border.Border;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.BorderLayout;
 


import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.management.timer.TimerNotification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JProgressBar;

//#################################################### BEGINN ##################################################


public class iris {

	
	static int rep = 0;
	private static JTextField tbSingleAd;
	private static JTextField tbMultiAd;
	private static JTextField tbListPath;
	private static JTextField tbSubject;
	private static JTextField tbReplyto;
	private static JTextField tbFakeSender;
	private static JLabel lblAddress = new JLabel("address example : xy@z.com");
    private static JCheckBox cbSingleAd = new JCheckBox("single Address");
    private static Label tbFakeName = new Label("fake sender\r\n");
    private static JTextArea textArea = new JTextArea();
    private static JCheckBox chckbxText = new JCheckBox("Text");
    private static JCheckBox chckbxHtml = new JCheckBox("html");
    private static JCheckBox cbList = new JCheckBox("List");
	private static JCheckBox cbmultiAd = new JCheckBox("multi Address");
	private static JTextField tbRepeat;
	private static JTextField tbWait;
	private static JPasswordField passwordField;
	private static File list;
	private static JProgressBar progressBar = new JProgressBar();

	public static void main(String[] args) throws IOException {
	
		if(UserDataExist() != true ){ // startet die Methode UserDataExist. Diese überprüft, ob ein Account schon gespeichert ist, wenn nicht wird Login() gestartet
			Login();
			}

	master(); // Das Hauptfenster
	
	}
	
// #################################################### Login Methode BEGINN ##################################################

public static void Login(){
	
	// login Fenster, erscheint nur einmal am Anfang und speichert dann die Userdaten im "userdata" File
	
	JFrame lFrame = new JFrame("iris login");  //  Das Fenster wird initialisiert 
	lFrame.setSize(444, 279 );
	lFrame.setLocationRelativeTo(null);
    lFrame.setAlwaysOnTop(true);
	lFrame.getContentPane().setLayout(null);

	//__________________________________________________________________
	
	TextField addressField = new TextField();                      //  Mail Adresse, unten alle Eigenschaften
	addressField.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
	addressField.setBounds(12, 41, 394, 33);
	lFrame.getContentPane().add(addressField);
	addressField.setColumns(10);


	//__________________________________________________________________
	
		
	JLabel lblGmailadresse = new JLabel("Gmail-Adresse"); 
	lblGmailadresse.setBounds(12, 23, 102, 16);
	lFrame.getContentPane().add(lblGmailadresse);

	//__________________________________________________________________
	
	JLabel lblGmailpasswort = new JLabel("Gmail-Passwort");
	lblGmailpasswort.setBounds(12, 80, 102, 16);
	lFrame.getContentPane().add(lblGmailpasswort);
	

	//__________________________________________________________________
	
	JTextPane leiderTxt = new JTextPane(); // Hinweis, dass nur Gmail unterstützt wird
	leiderTxt.setEditable(false);
	leiderTxt.setForeground(SystemColor.desktop);
	leiderTxt.setFont(new Font("Tahoma", Font.BOLD, 10));
	leiderTxt.setBackground(SystemColor.menu);
	leiderTxt.setText("Leider ist iris zur Zeit nur mit einem Gmail-Account kompatibel. Weitere Mail Dienste folgen. Bitte stellen sie sicher, dass sie den Zugriff f\u00FCr Applikationen von Drittentwicklern in ihren Gmail-Sicherheitseinstellungen aktiviert haben");
	leiderTxt.setBounds(12, 138, 400, 45);
	lFrame.getContentPane().add(leiderTxt);

	//__________________________________________________________________
	
	JButton anmeldenBtn = new JButton("Anmelden !");                    // Anmelde Button 
	anmeldenBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				FileWriter fwrite = new FileWriter("userdata");  // userdata wird initialisiert
				fwrite.write(addressField.getText()+"\n");       // Email wird geschrieben, indem das Email-Fest ausgelesen wird
				fwrite.write(passwordField.getPassword());       // Passwort Feld wird ausgelesen und gespeichert
				fwrite.close();
				lFrame.dispose();                                // Login Fenster wird geschlossen
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}
	});
    anmeldenBtn.setBounds(12, 186, 394, 33);

	//__________________________________________________________________
    passwordField = new JPasswordField();                          // Passwortfeld, unten alle Eigenschaften
	passwordField.setFont(new Font("Tahoma", Font.BOLD, 17));
	passwordField.setBounds(12, 96, 394, 33);
	lFrame.getContentPane().add(passwordField);
	lFrame.setVisible(true);

	lFrame.getContentPane().add(anmeldenBtn);
	
	}

// #################################################### Login Methode ENDE ##################################################

//#################################################### UserDataExist Methode BEGINN ##################################################

public static boolean UserDataExist() throws IOException{
	boolean exist;
	
	Reader file = new FileReader("userdata");                 // initialisiert file "userdata"
	BufferedReader reader = new BufferedReader(file);
	if(reader.read() != -1){                                  // überprüft, ob userdata leer ist       
		exist = true; 
	}else exist = false;
	
	
		return exist;                                         // diese Funktion ist nötig, da das Programm wissen muss, ob schon ein Profil des Users angelegt wurde oder nicht
															 // Wenn nicht: lässt die Methode Login ein Loginfenster erscheinen, wenn doch : dann nicht   
		
}

//#################################################### master Methode BEGINN ##################################################

/**
 * @throws IOException 
 * @wbp.parser.entryPoint
 */


public static void master() throws HeadlessException, IOException{

	Reader file = new FileReader("userdata");                
	BufferedReader reader = new BufferedReader(file);  // Zum Anzeigen der Adresse im Fenster als Lable
	
	//__________________________________________________________________
	
	// Hauptfenster wird erstellt und optimiert ...
	
	JFrame master = new JFrame("iris (c) -master- alpha version 1.3 ");  
	master.setSize(1000, 820);
	master.setLocationRelativeTo(null);
	master.setResizable(false);
	master.getContentPane().setLayout(null);
	
	JTextPane presentation = new JTextPane();
	presentation.setEditable(false);
	presentation.setFont(new Font("Consolas", Font.BOLD, 14));
	presentation.setBackground(SystemColor.menu);
	presentation.setForeground(SystemColor.desktop);
	presentation.setText("iris (c)- by SmSh \r\n");
	presentation.setBounds(832, 0, 150, 25);
	master.getContentPane().add(presentation);

	//__________________________________________________________________
	
	lblAddress.setForeground(SystemColor.activeCaptionBorder);
	lblAddress.setBounds(141, 58, 210, 16);
	master.getContentPane().add(lblAddress);
	
	tbSingleAd = new JTextField(); // 
	tbSingleAd.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
	tbSingleAd.setBounds(141, 73, 602, 31);
	master.getContentPane().add(tbSingleAd);
	tbSingleAd.setColumns(10);
	
	
	// CheckBox: An wieviele Adressanten die Mail gesendet werden soll. Außerdem entwählt die untere Methode alle andere CheckBox 
	cbSingleAd.setSelected(true);                                
	cbSingleAd.addActionListener(new ActionListener() {  // wenn (Checkbox) cbSingleAd ausgewält wird werden alle anderen Möglichkeiten entwählt
		public void actionPerformed(ActionEvent e) {
			if(cbSingleAd.isSelected()){
				cbmultiAd.setSelected(false);
				cbList.setSelected(false);
				
			}
		}
	});
	cbSingleAd.setBounds(20, 76, 113, 25);
	master.getContentPane().add(cbSingleAd);

	//__________________________________________________________________	
	
	JLabel lblMultiAddressExample = new JLabel("multi address example : xy@z.com ; yx@z.com ...");
	lblMultiAddressExample.setForeground(SystemColor.activeCaptionBorder);
	lblMultiAddressExample.setBounds(141, 108, 326, 16);
	master.getContentPane().add(lblMultiAddressExample);
	
	

	// CheckBox: An wieviele Adressanten die Mail gesendet werden soll. Außerdem entwählt die untere Methode alle anderen CheckBox
	tbMultiAd = new JTextField();
	tbMultiAd.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
	tbMultiAd.setColumns(10);
	tbMultiAd.setBounds(141, 123, 602, 31);
	master.getContentPane().add(tbMultiAd);
	cbmultiAd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(cbmultiAd.isSelected()){
				cbSingleAd.setSelected(false);
				cbList.setSelected(false);
				
			}
		}
	});
	cbmultiAd.setBounds(20, 126, 113, 25);
	master.getContentPane().add(cbmultiAd);

	//__________________________________________________________________
	
	JLabel lblPathOfList = new JLabel("path of list (txt) example : C:\\\\doc\\list.txt");
	lblPathOfList.setForeground(SystemColor.activeCaptionBorder);
	lblPathOfList.setBounds(140, 158, 297, 16);
	master.getContentPane().add(lblPathOfList);
	
	tbListPath = new JTextField();
	tbListPath.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
	tbListPath.setColumns(10);
	tbListPath.setBounds(141, 173, 602, 31);
	master.getContentPane().add(tbListPath);
	
	

	// CheckBox: An wieviele Adressanten die Mail gesendet werden soll. Außerdem entwählt die untere Methode alle anderen CheckBox
	cbList.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(cbList.isSelected()){
				cbmultiAd.setSelected(false);
				cbSingleAd.setSelected(false);
				
			}
		}
	});
	cbList.setBounds(20, 176, 113, 25);
	master.getContentPane().add(cbList);

	//__________________________________________________________________
	
	JLabel lblSendTo = DefaultComponentFactory.getInstance().createTitle("send to");
	lblSendTo.setBounds(20, 40, 104, 16);
	master.getContentPane().add(lblSendTo);
	
	JSeparator separator = new JSeparator();
	separator.setBounds(12, 38, 822, 2);
	master.getContentPane().add(separator);
	
	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(20, 229, 822, 2);
	master.getContentPane().add(separator_1);

	//__________________________________________________________________
	Label label = new Label("signed in as : " + reader.readLine());   // gibt den Username an oberster Stelle an 
	label.setBounds(10, 10, 437, 24);
	master.getContentPane().add(label);
	
	JButton loginagain = new JButton("change username");
	loginagain.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Login();										// Die Login Methode wird wider aufgerufen, damit userdata geändert werden kann
		}																	
	});
	loginagain.setBounds(467, 10, 178, 25);
	master.getContentPane().add(loginagain);
	
	
	JButton signout = new JButton("sign out !"); // löscht die vorhandenen Anmeldedaten 
	signout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
		}
	});
	signout.setBounds(664, 10, 113, 25);
	master.getContentPane().add(signout);
	

	//__________________________________________________________________
	
	// TextBoxen für Eingaben vom Nutzer
	
	tbSubject = new JTextField();
	tbSubject.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
	tbSubject.setBounds(141, 254, 461, 29);
	master.getContentPane().add(tbSubject);
	tbSubject.setColumns(10);
	
	Label label_1 = new Label("subject");
	label_1.setBounds(24, 259, 70, 24);
	master.getContentPane().add(label_1);
	
	Label label_2 = new Label("default <empty>");
	label_2.setBounds(619, 259, 124, 24);
	master.getContentPane().add(label_2);
	
	tbReplyto = new JTextField();
	tbReplyto.setFont(new Font("Yu Gothic", Font.ITALIC, 15));
	tbReplyto.setColumns(10);
	tbReplyto.setBounds(140, 297, 461, 29);
	master.getContentPane().add(tbReplyto);
	
	Label label_3 = new Label("reply to");
	label_3.setBounds(24, 304, 70, 24);
	master.getContentPane().add(label_3);
	
	Label label_4 = new Label("default <username>");
	label_4.setBounds(618, 302, 124, 24);
	master.getContentPane().add(label_4);
	
	tbFakeSender = new JTextField();
	tbFakeSender.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
	tbFakeSender.setColumns(10);
	tbFakeSender.setBounds(141, 338, 461, 29);
	master.getContentPane().add(tbFakeSender);
	
	
	tbFakeName.setBounds(25, 343, 81, 24);
	master.getContentPane().add(tbFakeName);
	
	Label label_6 = new Label("default <empty>");
	label_6.setBounds(619, 343, 124, 24);
	master.getContentPane().add(label_6);
	
	

	//__________________________________________________________________
	
	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(20, 390, 822, 2);
	master.getContentPane().add(separator_2);
	
	JScrollPane scrollPane = new JScrollPane();                                            // damit man im TextEditor scrollen kann
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);  // horizontales Scrollen soll verboten werden 
	scrollPane.setBounds(5, 421, 983, 322);
	master.getContentPane().add(scrollPane);
	
	
	

	
	Insets m = new Insets(0, 10, 0, 20);                                   // Das ist der Rand an den Seiten im TextEditor, damit es schöner aussieht
	textArea.setMargin(m);
    textArea.setLineWrap(true);
	textArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
	scrollPane.setViewportView(textArea);
	
	
	chckbxText.setBounds(57, 394, 70, 25);
	chckbxText.setSelected(true);
	master.getContentPane().add(chckbxText);
	                                            // Abfrage, ob man Html oder ein Text schicken will
	chckbxHtml.setBounds(126, 394, 113, 25);
	master.getContentPane().add(chckbxHtml);
	
	
	Label label_5 = new Label("format:");
	label_5.setBounds(10, 396, 44, 24);
	master.getContentPane().add(label_5);


	//__________________________________________________________________	
	JLabel lblRepeat = new JLabel("repeat:  ");
	lblRepeat.setBounds(20, 756, 56, 16);
	master.getContentPane().add(lblRepeat);

	
	tbRepeat = new JTextField();                // TextBox Abfrage, wie oft wiederholt weden soll
	tbRepeat.setText("1");
	tbRepeat.setBounds(67, 753, 56, 22);
	master.getContentPane().add(tbRepeat);
	tbRepeat.setColumns(10);
		
	
	JLabel lblWaitUntilRepeat = new JLabel("wait:");   
	lblWaitUntilRepeat.setBounds(141, 756, 44, 16);
	master.getContentPane().add(lblWaitUntilRepeat);
	
	
	tbWait = new JTextField();      // TextBox Intervall-Pause 
	tbWait.setBounds(173, 753, 61, 22);
	tbWait.setText("out of use");
	master.getContentPane().add(tbWait);
	tbWait.setColumns(10);
	
	
	
	JLabel lblInMs = new JLabel("ms");
	lblInMs.setBounds(236, 756, 56, 16);
	master.getContentPane().add(lblInMs);
	

	//__________________________________________________________________
	
	JButton btnClearTB = new JButton("clear text box"); // Dieser Button setzt den TextEditor zurück 
	btnClearTB.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			textArea.setText("");
		}
	});
	btnClearTB.setBounds(264, 752, 143, 25);
	master.getContentPane().add(btnClearTB);
	

	//__________________________________________________________________
	
	JButton btnStart = new JButton("start iris");         // Startet die send Methode, welche die Mail/s verschickt 
	btnStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			try {
				send();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}});
	
	btnStart.setBounds(796, 752, 186, 25);
	master.getContentPane().add(btnStart);
	
	JButton searchFilebtn = new JButton("search in system"); // 
	searchFilebtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser choose = new JFileChooser();  // Dieses Objekt choose vom Typen JFileChooser ist aus der Java Standart Bibliothek und erleichtert die Datei suche im System
			choose.showOpenDialog(master);
			list = choose.getSelectedFile();
			tbListPath.setText(list.getPath().replace('\\', '/'));   // Der Pfad der ausgesuchten Datei wird angegeben
		}
	});
	searchFilebtn.setBounds(763, 178, 143, 25);
	master.getContentPane().add(searchFilebtn);
	
	// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 
	progressBar.setBounds(429, 758, 348, 14);
	master.getContentPane().add(progressBar);
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	
	master.setVisible(true);
	}


//__________________________________________________________________
//#################################################### master Methode ENDE ##################################################


//#################################################### send Methode BEGINN ##################################################



public static void send() throws IOException{
	
	
	Reader file = new FileReader("userdata");  // Anmeldedaten, die zum Verschicken benötigt werden
	BufferedReader reader = new BufferedReader(file);
	
	String address[];  		// so darin können mehrere Adressen abgespeichert werden 
	
	
	// Hier: Überprüfung welche Empfänger 

	if(cbSingleAd.isSelected()){
		address = new String[1];
		address[0] = tbSingleAd.getText(); // Wenn cbSingle: dann address = TextBox
		
	}else if(cbmultiAd.isSelected()){
		address = tbMultiAd.getText().split(";"); // Wenn cbMulti: dann TextBox splitten und in address array speichern 
	
	}else if(cbList.isSelected()){
		String content = readFile(list.getPath(), StandardCharsets.UTF_8); // Wenn cbList: dann wird die Datei gelesen und in content gespeichert 
		address = content.split(";"); // dann wird content gesplittet und im array adress gespeichert
	
	}else address = new String[1];  // muss gemacht werden, sonst Fehlermeldung.
	

		final String username = reader.readLine(); 
		final String password = reader.readLine(); // unveränderbare Senderdaten
	
	
	System.out.println(">> iris (c) consonl monitor ");
	System.out.println("#> signd in as : " + username);
	System.out.println("#> smtp service : smtp.gmail.com");
	
	System.out.println("\n#>loading recipient list :");
	
	for(int i =0;i<address.length;i++){ // Empfänger wird/werden in der Console ausgegeben
	
		System.out.println("- ["+ (i+1)+"/"+address.length +"]   			     " +address[i]);
			
	}
	
	
	
	    Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
       
        
    	System.out.println("\n#> sending progress started : ");
       
        for(int t= 0; t<Integer.parseInt(tbRepeat.getText());t++){ // Schleife, wie oft wiederholt werden soll
        
        	
        for(int i=0; i<address.length;i++){           // Sendet an die ganze Liste... geht Array address durch
       
      System.out.print("\n#> preparing reciver : " + address[i] +"  		["+   (i+1)+"/"+address.length+"]\n");
       
        try {

            Message message = new MimeMessage(session);
        
            InternetAddress me = new InternetAddress();
            me.setPersonal(tbFakeSender.getText());
            message.setFrom(me);
            
            
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(address[rep]));
        message.setSubject(tbSubject.getText());
        if(chckbxHtml.isSelected()){
           message.setContent(textArea.getText(), "text/html; charset=utf-8");
        }else message.setText(textArea.getText());

    System.out.print("#> mail sending : " + address[i]+"\n");
        
            Transport.send(message);
        
    System.out.print("#> done with sending : " + address[i]+"\n");

          
        } catch (MessagingException e) {
        	System.out.print("#> error while sending : " + address[i]+"\n");

        }
	
        
        } // address Array for Schleife
	
        System.out.println("\n######## done with list ########");
        
        } //// Ende Repeat for Schleife
	//########################################################################	
	
 } // Ende Send Methode


 

// ####################################################unten: readFile: externe Methode : 
// #####  https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
// ich bin nicht selbst auf die Idee gekommen, wie ich eine Text Datei direkt in ein String umwandlen kann
// Daher habe ich diese Methode im Internet gefunden und übernommen  


static String readFile(String path, Charset encoding) 
		  throws IOException 
		{
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
		}
}
