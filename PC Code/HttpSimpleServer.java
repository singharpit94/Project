import java.io.BufferedReader;

import javax.swing.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import java.util.List;

import org.apache.commons.validator.UrlValidator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import sun.net.www.URLConnection;
 class SimpleHttpPut  { 
	String name; // name of thread
	
	
	SimpleHttpPut(String threadname) {
		name = threadname;
		//t = new Thread(this, name);
		//System.out.println("New thread: " + t);
		//t.start(); // Start the thread
		}
	  public void fun(){
		  
		  try {

	          // Send the request
	          URL url = new URL("http://10.141.129.178:8888");
	          java.net.URLConnection conn = url.openConnection();
	          conn.setDoOutput(true);
	          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

	          //write parameters
	          writer.write("Arpit\n");

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

public class HttpSimpleServer {
   
   public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(4444), 0);
    server.createContext("/test", new MyHandler());
    server.setExecutor(null); // creates a default executor
    
     
    server.start();
    
    
    
  }

  static class MyHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      String response = "hhhhhhhhhhh";
     String response1 = "hhhhhhhhhhhh";
     String first;
     String j="Th";
     int i=2;
     
    // System.out.println(response);
     
     // os.close();
     
      
      InputStream in = t.getRequestBody();
     BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder result = new StringBuilder();
      String line;
      String x=null;
     first=reader.readLine().toString();
      
     while((line = reader.readLine()) != null) {
          result.append(line);
      }
      j=result.toString();
      System.out.println(first);
      System.out.println(result.toString());
      UrlValidator defaultValidator = new UrlValidator(); // default schemes
	  if (defaultValidator.isValid(first)) {
	      System.out.println("valid");
	      Send h=new Send("thrid",first);
	  }
     
	  else
	  {
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

