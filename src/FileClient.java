

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class FileClient {
	
	private Socket s,sss;
	
	public FileClient(String host, int port, String file) {
		try {
			s = new Socket(host, port);
                        BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
                         System.out.println(in.readLine());

			sendFile(file);

                
		} catch (Exception e) {
			e.printStackTrace();
		}	
                
                    HashMap<String, Integer> map = new HashMap<>();
                    try{
                        sss = new Socket("localhost",1987);
                        ObjectInputStream mapInputStream = new ObjectInputStream(sss.getInputStream());
                        map = (HashMap)mapInputStream.readObject();

                            //affichage
                            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                                System.out.println(entry.getKey() + ": " + entry.getValue());
                            }
                    }catch(Exception e){
                        
                    }

	}
	
	public void sendFile(String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		fis.close();
		dos.close();	
	}
	
	public static void main(String[] args) {
		FileClient fc = new FileClient("localhost", 1988, "ca.txt");
                
	}

}