import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Reply  implements Runnable{

	 String ip,sender,name;
	  Thread t;
	  int k,l;
	  JTextArea area = new JTextArea();
	Reply(String X,String Y,String Z){
		name =X;
		ip = Y;
		sender =Z;
		System.out.println(sender);
		
		t = new Thread(this,name );
		
		t.start(); // Start the thread
		
		
		
	}
	public void run()
	{
	         JFrame f;
	         f=new JFrame();
	         JLabel Text=new JLabel("Enter the Text");
	         JButton button = new JButton("Send");
	         Text.setBounds(10, 0, 100, 100);
	         area=new JTextArea(200,200);
	         area.setBounds(10,60,300,150);
	         button.setBounds(15, 240, 100, 30);
	 	    button.addActionListener(new ActionListener()
	 	{
	 	    	public void actionPerformed(ActionEvent e)
	 			  {
	 	    		
	 	    		String x;
	 			    x=area.getText();
	 			    area.setText(" ");
	 			   if(x!=null&&sender!=null){
	 			    	
	 			    	 try {

	 				          // Send the request
	 				          URL url = new URL(ip);
	 				          java.net.URLConnection conn = url.openConnection();
	 				          conn.setDoOutput(true);
	 				          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

	 				          //write parameters
	 				          writer.write(sender+"\n");
	 				          writer.write(x+"\n");
	 				          writer.write("\u0004\n");
	 				         // writer.write("**\n");

	 				          writer.flush();
	 				          writer.close();
	 				          // Get the response
	 				          StringBuffer answer = new StringBuffer();
	 				          BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 				          String line;
	 				          while ((line = reader.readLine()) != null) {
	 				              answer.append(line);
	 				          }
	 				         
	 				          reader.close();

	 				          //Output the response
	 				          System.out.println(answer.toString());
	 				          JOptionPane.showMessageDialog (null, "Message Sent");


	 				      } catch (MalformedURLException ex) {
	 				          ex.printStackTrace();
	 				      } catch (IOException ex) {
	 				          ex.printStackTrace();
	 				      }

	 			   }	
	 	}
	 	});
	 	
	 	   area.setBackground(Color.white);  
		    area.setForeground(Color.black); 
		    f.add(area); 
		    f.add(Text);
		    f.add(button);
		    f.setSize(400,400); 
		    f.setLayout(null);  
		    f.setVisible(true); 
	}
	
}
