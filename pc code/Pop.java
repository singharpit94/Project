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
import javax.swing.WindowConstants;


public class Pop implements Runnable {
	  String name,message,sender;
	  Thread t;
	Pop(String X,String Y,String Z){
		name = X;
		message =Z;
		sender=Y;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start(); // Start the thread
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void run(){
		
		String message1;
		if(message.equals("\u0004"))
		{ message1 = "You got a new CAll From   \n"+sender;}
		else
		{
			 message1="You got a new message From  \n"+sender;
		}
				String header = "This is header of notification message";
				JFrame frame = new JFrame();
				JButton cloesButton = new JButton("View");
				JButton close=new JButton();
				close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cro.gif"))); 
				cloesButton.setBounds(110, 200, 75, 25);
				close.setBounds(270, 10, 20, 20);
				cloesButton.setMargin(new Insets(5, 4, 1, 4));
				cloesButton.setFocusable(false);
				cloesButton.addActionListener(new ActionListener()
				{
				  public void actionPerformed(ActionEvent e)
				  {
				    JFrame frame2=new JFrame();
				    frame2.setSize(500,300);
				    
				    JLabel messageLabel = new JLabel(message);
				    frame2.add(messageLabel);
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


