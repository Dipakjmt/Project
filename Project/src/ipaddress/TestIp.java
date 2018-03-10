package ipaddress;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

public class TestIp {
	public static String getSystemIP(){
        try{
            String sysIP="";
            String OSName=  System.getProperty("os.name");
	    if(OSName.contains("Windows")){
                sysIP   =InetAddress.getLocalHost().getHostAddress();
	    }
	    else{
	    	sysIP=getSystemIP4Linux("eth0");
	    	if(sysIP==null){
                    sysIP=getSystemIP4Linux("eth1");
		    if(sysIP==null){
		  	sysIP=getSystemIP4Linux("eth2");
                        if(sysIP==null){
                            sysIP=getSystemIP4Linux("usb0");
                        }
                    }
	   	}
	    }
	    return sysIP;
	}
	catch(Exception E){
            System.err.println("System IP Exp : "+E.getMessage());
            return null;
	}
    }
	private static String getSystemIP4Linux(String name){
        try{
            String ip="";
            NetworkInterface networkInterface = NetworkInterface.getByName(name);
            Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
            InetAddress currentAddress = inetAddress.nextElement();
            while(inetAddress.hasMoreElements()){
                currentAddress = inetAddress.nextElement();
                if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress()){
                    ip = currentAddress.toString();
                    break;
                }
            }
            if(ip.startsWith("/")){
                ip=ip.substring(1);
            }
            return ip;
        } 
        catch (Exception E) {
            System.err.println("System Linux IP Exp : "+E.getMessage());
            return null;
        }
    }
    
	 public static void main(String[] args) {
		 String ipAddress = getSystemIP();
	     System.out.println("System IP : "+ipAddress);
	     CheckInternet ch = new CheckInternet();
	     ch.start();
		
	 }
}
class CheckInternet extends Thread
{
	
	public void run() {
		for(;;)
		{
		 boolean connectivity;
		 try {
		 URL url = new URL("http://www.google.com");
		 URLConnection conn = url.openConnection();
		 
		 conn.connect();
		 connectivity = true;
		 System.out.println("Internet Is Connected");
		 sleep(5000);
		 } catch (Exception e) {
		 connectivity = false;
		 System.out.println("No Internet Connection");
		 		 }
		 }
		
		
	}
}