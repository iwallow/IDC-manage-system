package jcy.bishe.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Macwomorau {

	public static String store ="";
    public static String ip;  
    public static String gainAllFrame="GainAllFrame";  
    public static String area_IP ="";
    public static File file1;
    public static String macAddress = "";
    public static  Map<String, String> MACMap = new HashMap<String, String>();  
    
    
    
    
    public static String gainAllIp(String ip) throws IOException{  
        
        Macwomorau allMACFrame=new Macwomorau();  
        PingIpThread thread=null;  

        String hostAddress=ip;       //同样获得IP（以文本表现形式）  
       

            thread=allMACFrame.new PingIpThread(hostAddress);  
            // System.out.println("ip:"+ip);  
            thread.start(); 
            try {  
                thread.join();  
            } catch (InterruptedException e) {  
                // TODO 自动生成的 catch 块  
                e.printStackTrace();  
            }  
        
         // System.out.println(macAddress);
        return macAddress;  
          
    }  
    
    
    
    
    
    
    class PingIpThread extends Thread{  
        public String ip;  
        public  Map<String, String> map = new HashMap<String, String>();   
        PingIpThread (String ip){  
            this.ip=ip;  
        }  
        public Map<String, String> getMap(){  
            return map;  
        }  
          
        public void run() {  
                try {  
                      
                 
                    Process process=Runtime.getRuntime().exec("arp -a");  
                   
                    InputStream is=process.getInputStream();    //数据流  
                   
                    InputStreamReader isr=new InputStreamReader(is);    //数据流读取  
                   
                    BufferedReader in=new BufferedReader(isr);  //缓冲流  
                    
                    String line=in.readLine();   //缓存中读取         
                   
                    if(line.equals(null))System.out.println("null");  
                    while(line!=null){
                    	 
                   
                        if(line!=null&&!line.equals("")){  
                        	String [] s =line.split(" ");
                        	
                            if(s[2].equals(ip)  
                            		&&(line.length()>35)){
                              //判断是ping通过的IP地址
                            	String mac=line.substring(24, 41);
                            	//System.out.println(mac);
                                map.put(ip,mac);                     //向集合中添加IP地址  
                                mac=mac.replaceAll("-", ":");
                                macAddress=mac;
                            }  
                        }  
                        line=in.readLine();  
                      
                    }  
                    
                } catch (IOException e) {  
                    // TODO: handle exception  
                }  
            }  
  
          
    }  
    
}
