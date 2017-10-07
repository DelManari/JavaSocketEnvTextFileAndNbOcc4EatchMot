

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class FileServer extends Thread {
	
	private ServerSocket ss,sss;
	
	public FileServer(int port) {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				Socket clientSock = ss.accept();
                                        PrintWriter out = new PrintWriter(clientSock.getOutputStream(),true);
        out.println("Connection reussie !");
				saveFile(clientSock);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile(Socket clientSock) throws IOException {
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream("testfile.txt");
		byte[] buffer = new byte[4096];
		
		int filesize = 15123; // Send file size in separate msg
		int read = 0;
		int totalRead = 0;
		int remaining = filesize;
		while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}
      //read
       String c="" ;

              try {
            FileReader reader = new FileReader("testfile.txt");
            int character;
            while ((character = reader.read()) != -1) {
               c += (char) character;
            }
            		fos.close();
		dis.close();
            System.out.println(c);
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        //chercher l'occurence
        
        HashMap<String, Integer> map = new HashMap<>();
        String sentence = c.toLowerCase();
        for(String word : sentence.split("\\W")) {
          if(word.isEmpty()) {
               continue;
            }
    if(map.containsKey(word)) {
        map.put(word, map.get(word)+1);
    }
    else {
        map.put(word, 1);
    }
}


                            clientSock.close();
        sss = new ServerSocket(1987);                   
        Socket Sock = sss.accept();

        //send to client
            ObjectOutputStream mapOutputStream = new ObjectOutputStream(Sock.getOutputStream());
              mapOutputStream.writeObject(map);

	}
	
	public static void main(String[] args) {
		FileServer fs = new FileServer(1988);
		fs.start();
	}

}