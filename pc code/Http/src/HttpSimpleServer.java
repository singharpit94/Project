import java.awt.Toolkit;
import java.io.BufferedReader;

import javax.swing.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.UrlValidator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import sun.net.www.URLConnection;
 
//Http server class
public class HttpSimpleServer {
	 public static  String murl;
   public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(4444), 0);
    server.createContext("/test", new MyHandler());
    server.setExecutor(null); // creates a default executor
    Pop noti=new Pop("New","Arpit","123456789123456789123456789123456789123456789123456789");
    SoundUtils.tone(1000,100);
    
     
    server.start();
    
    
    
  }
   String getm()
   {
	   return murl;
   }

  static class MyHandler implements HttpHandler {
   
	  public void handle(HttpExchange t) throws IOException {
      String response = "hhhhhhhhhhh";
     String response1 = "hhhhhhhhhh";
  
     String first;
     String j="Th";
     String[] where=new String[150];
     int i=2;
     List<String> contacts = new ArrayList<String>();
     
    // System.out.println(response);
     
     // os.close();
     
      
      InputStream in = t.getRequestBody();
     BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder result = new StringBuilder();
      String line;
      String x=null;
      try {
  	     //Getting the contact list
  	    final InputStream yourInputStream = t.getRequestBody(); // InputStream from where to receive the map, in case of network you get it from the Socket instance.
  	    final ObjectInputStream mapInputStream = new ObjectInputStream(yourInputStream);
  	    final Map<String, String> yourMap = (Map) mapInputStream.readObject();
  	    System.out.println("Got list\n");
  	  List<String> keys = new ArrayList<String>(yourMap.keySet());
  	
  	
  	int k=0;
  	
  	for (String key: keys) {
  	    System.out.println(key + ": " + yourMap.get(key));
  	    where[k]=(key+"-"+yourMap.get(key));
  	    k++;
  	}
       
  	} catch(Exception e) {
  		
  		System.out.println("contacts\n");
  	}
     first=reader.readLine().toString();
      
     while((line = reader.readLine()) != null) {
          result.append(line);
         
      }
      j=result.toString();
     
    
      
      System.out.println(first);
      System.out.println(j);
      //checking for valid url
      UrlValidator defaultValidator = new UrlValidator(); // default schemes
	  if (defaultValidator.isValid(first)) {
	      System.out.println("valid");
	        murl=first;
	        //opening send dialog box
	      Send h=new Send("thrid",first,where);
	      
	  }
     
	  else
	  {   //opening popup
		  Pop noti=new Pop("New",first,j);
	  }
     
      t.sendResponseHeaders(200, (response+response1).length());
      OutputStream os = t.getResponseBody();
      
      
      {
    	  os.write(response.getBytes()); 
    	  os.write(response1.getBytes());
      }
      
    	in.close();
    	os.close();
    	//outPutStream.close();
    	//OutputStream os = t.getResponseBody();
        
        
        
      
    }
      
    }
  
  }

