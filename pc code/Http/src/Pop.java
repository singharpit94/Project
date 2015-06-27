import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;



public class Pop implements Runnable {
	  String name,message,sender,j1="";
	  String S="";
	  Thread t;
	  int k,l,i,ctr=0;
	Pop(String X,String Y,String Z){
		name = X;
		message =Z;
		sender=Y;
		k=message.length();
		l=k-10;
		
		System.out.println(k);
		for(i=0;i<k;i++)
	      { 
	    	  j1+=message.charAt(i);
	    	  ctr++;
	    	  if(ctr>42)
	    	  {
	    		  j1+="\n";
	    		  ctr=0;
	    	  }
	    		  
	    	  
	      }
		
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start(); // Start the thread
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void run(){
		
		String message1="MEssage";
		if(message.equals("\u0004"))
		{ message1 = "You got a new CAll From   \n"+sender;}
		else
		{
			 message1="You got a new message From  \n"+sender;
		}
		
		      
		     for(int i=l;i<k;i++)
		    	S+=message.charAt(i);
				String header = "This is header of notification message";
				JFrame frame = new JFrame();
				frame.getContentPane().setBackground( Color.WHITE);
				JButton cloesButton = new JButton("View");
				
				JButton close=new JButton();
				close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cro.gif"))); 
				cloesButton.setBounds(110, 200, 75, 25);
				close.setBounds(270, 10, 18, 18);
				cloesButton.setMargin(new Insets(5, 4, 1, 4));
				cloesButton.setFocusable(false);
				cloesButton.addActionListener(new ActionListener()
				{
				  public void actionPerformed(ActionEvent e)
				  {
				    JFrame frame2=new JFrame();
				    frame2.setSize(400,400);
				    HttpSimpleServer g=new HttpSimpleServer();
					   String ur=g.getm();
					   System.out.println(ur);
				    JButton reply=new JButton("Reply");
				    reply.setFocusable(false);
				    
				    reply.setBounds(10, 300, 80, 30);
				    reply.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent f) {
							frame2.dispose();
							Reply r=new Reply("R",ur,S);
							
						}
					});
				    frame2.add(reply);
				   JTextArea ar=new JTextArea(200,200);
				   ar.setBounds(0, 0, 400, 200);
				   ar.setText(j1);
				   ar.setEditable(false);
				   frame2.setLayout(null);
				   
				    frame2.add(ar);
				    frame.dispose();
				    frame2.setVisible(true);
				    
				  }
				});
				 close.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	
			            	frame.dispose();
			            }
			        });
				frame.add(cloesButton);
				frame.add(close);
				
				frame.setSize(300,250);
				frame.setUndecorated(true);
				
				JLabel headingLabel = new JLabel(header);
				
				JLabel messageLabel = new JLabel(message1);
				messageLabel.setAlignmentX(50);
				frame.add(messageLabel);
				Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
				Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());// height of the task bar
				frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight());
				frame.setVisible(true);
				
				 try {
					                   t.sleep(15000); // time after which pop up will be disappeared.
					                   frame.dispose();
					                   t.stop();
					            } catch (InterruptedException e) {
					                   e.printStackTrace();
					            }
	}
	
	}


