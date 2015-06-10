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
import javax.swing.JTextArea;


public class Send implements Runnable {
	  String ip,name;
	  Thread t;
	  
	Send(String X,String Y)
	{
		name=X;
		ip=Y;
		t=new Thread(this,name);
		System.out.println("New thread: " + t);
		t.start();
	}
	public void run()
	{
		JTextArea area,a; 
		JLabel Text=new JLabel("Enter the Text");
		JLabel Number=new JLabel("Enter the Phone number");
		JButton button = new JButton("Send");
		button.setFocusable(false);
	    JFrame f;  
	     
	    f=new JFrame();  
	    Text.setBounds(15,0,100,100);
	    Number.setBounds(15,135, 300, 100);
	    area=new JTextArea(200,100);
	    a=new JTextArea(200,100);
	    area.setBounds(10,70,300,100);
	    a.setBounds(10, 200, 150, 30);
	    button.setBounds(15, 240, 100, 30);
	    button.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {
		    String x,y;
		    x=area.getText();
		    area.setText(" ");
		    y=a.getText();
		    a.setText(" ");
		    if(x!=null&&y!=null){
		    	
		    	 try {

			          // Send the request
			          URL url = new URL(ip);
			          java.net.URLConnection conn = url.openConnection();
			          conn.setDoOutput(true);
			          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			          //write parameters
			          writer.write(y+"\n");
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
	    a.setBackground(Color.white);
	    a.setForeground(Color.black);
	          
	    f.add(area); 
	    f.add(Text);
	    f.add(a);
	    f.add(Number);
	    f.add(button);
	      
	    f.setSize(400,400);  
	    f.setLayout(null);  
	    f.setVisible(true); 
		
	

}
}
