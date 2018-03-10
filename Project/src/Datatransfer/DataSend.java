package Datatransfer;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DataSend {

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the msg");
		String msg=sc.next();
		ServerSocket ss = new ServerSocket(444);
		Socket s=ss.accept();
		System.out.println("Connection established");
		OutputStream obj =s.getOutputStream();
		PrintStream ps = new PrintStream(obj);
		
		ps.print(msg);
		
		ps.close();
		ss.close();
		s.close();
		sc.close();
		

	}

}
