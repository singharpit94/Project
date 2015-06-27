import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ca.odell.glazedlists.*;
import ca.odell.glazedlists.swing.*;

public class Send implements Runnable {
	String ip, name;
	Thread t;
	String[] Cont;
	int i,j,k,flag=0;
	String numb="";
	JTextArea area = new JTextArea();

	Send(String X, String Y, String[] A) {
		name = X;
		ip = Y;
		Cont = A;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start();
	}

	public void run()
	{
		
		JComboBox a=new JComboBox(Cont);
		JLabel Text=new JLabel("Enter the Text");
		JLabel Number=new JLabel("Enter the Phone number");
		JButton button = new JButton("Send");
		Icon warnIcon = new ImageIcon("cro.gif");
		JButton close=new JButton();
		close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cro.gif"))); 
		button.setFocusable(false);
		close.setFocusable(false);
		a.setEditable(true);

		SwingUtilities.invokeLater(new Runnable(){
		 
             public void run(){
		
		AutoCompleteSupport support = AutoCompleteSupport.install(
		        a, GlazedLists.eventListOf(Cont));
	    JFrame f;  
	     
	    f=new JFrame(); 
	    f.setUndecorated(true);
	    Text.setBounds(15,70,100,100);
	    Number.setBounds(15,0, 300, 100);
	    area=new JTextArea(200,200);
	    
	    area.setBounds(10,135,300,100);
	    a.setBounds(10, 70, 250, 30);
	    button.setBounds(15, 240, 100, 30);
	    button.addActionListener(new ActionListener()
	{
		  public void actionPerformed(ActionEvent e)
		  {  flag=0;
		    String x,y,z;
		    x=area.getText();
		    area.setText(" ");
		    y=(String) a.getSelectedItem();
		    z=y.substring(y.lastIndexOf("-") + 1);
		    System.out.println(z);
		    System.out.println(z.length());
		    k=z.length();
		    j=0;
		    i=0;
		    numb="";
		    if(z.charAt(0)=='+')
		    i=i+3;
		    else if(z.charAt(0)=='0')
		    	i++;
		    
		    for(;i<k;i++)
		    {    
		    
		    	if(z.charAt(i)>=48&&z.charAt(i)<=57)
		    	{
		    		numb+=z.charAt(i);
		    		j++;
		    	}
		    	else if (z.charAt(i)==' ')
		    		continue;
		    	else
		    	{
		    		flag =1;
		    		break;
		    		
		    	}
		    				
		    	
		    }
		    if(flag==1)
		    {
		    	int mc = JOptionPane.ERROR_MESSAGE;
		    	
		    	JOptionPane.showMessageDialog (null, "Invalid Number", "ERROR!", mc);


		    }
		    else
		    {
		    
		    numb+="\n";
		    System.out.println(numb);
		    System.out.println(numb.length());
		    
		   
		    if(x!=null&&numb!=null){
		    	
		    	 try {

			          // Send the request
			          URL url = new URL(ip);
			          java.net.URLConnection conn = url.openConnection();
			          conn.setDoOutput(true);
			          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			          //write parameters
			          writer.write(numb);
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
		  }
		});
	    close.setBounds(350, 10, 18, 18);
	 
	    close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	f.setState(Frame.ICONIFIED);
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
	    f.add(close);
	      
	    f.setSize(400,400);  
	    f.setLayout(null);  
	    f.setVisible(true); 
             }
		});

}

	
}
