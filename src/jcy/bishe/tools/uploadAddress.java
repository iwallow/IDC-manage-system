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



public class uploadAddress {
	
	public static String store ="";
    public static String ip;  
    public static String gainAllFrame="GainAllFrame";  
    public static String area_IP ="";
    public static File file1;
    public static  Map<String, String> pingMap = new HashMap<String, String>();  
    public  static ArrayList<String> al = new ArrayList<String>();
    public  void getResult(){
    	 
    	try {
			area_IP=gainAllIp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println(gainAllFrame);  
    }
	 
	    
	    public static String gainAllIp() throws IOException{  
	        
	        uploadAddress allIPFrame=new uploadAddress();  
	        PingIpThread thread=null;  
	        allIPFrame.gainAllFrame="finished";  
	        InetAddress host=InetAddress.getLocalHost();    //获得本机的InetAddress对象 ，回送IP地址  
	        String hostAddress=host.getHostAddress();       //同样获得IP（以文本表现形式）  
	        int pos=hostAddress.lastIndexOf(".");           //获得IP地址中最后一个点的位置  
	        String wd=hostAddress.substring(0,pos+1);  
	        //System.out.println("\n"+"wd"+wd);//对本机的IP地址进行街区，get the network segment  
	        //System.out.println("11111");  
	        for (int i = 100; i < 254; i++) {  
	            String ip=wd+i;  
	            thread=allIPFrame.new PingIpThread(ip);  
	            // System.out.println("ip:"+ip);  
	            thread.start();  
	            try {  
	                thread.join();  
	            } catch (InterruptedException e) {  
	                // TODO 自动生成的 catch 块  
	                e.printStackTrace();  
	            }    
	            pingMap=thread.getMap();  
	              
	              
	            Set<String> set=pingMap.keySet();  
	            Iterator<String> it=set.iterator();       //获得迭代器对象  
	              
	            while(it.hasNext()){  
	                String key=it.next();  
	                String value=pingMap.get(key);  
	               // System.out.println("\n"+"4444"+value);  
	                if(value.equals("true")){  
	                    area_IP+=(key+"\n");  
	                    System.out.println("\n"+"success   "+key);  
	                    store+=key+"/n";
	                    al.add(key);
	                }  
	            }  
	        }     
	          
	        return area_IP;  
	          
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
	                      
	                    //System.out.println("\n"+"第一行");  
	                    //获得ping的IP进程,-w 280是等待每次回复的超时时间，-n 1是要发送的回显请求数  
	                    Process process=Runtime.getRuntime().exec("ping "+ip+" -w 1000 -n 1");  
	                    //System.out.println("\n"+"第二行");  
	                    InputStream is=process.getInputStream();    //数据流  
	                    //System.out.println("\n"+"is:"+is);  
	                    InputStreamReader isr=new InputStreamReader(is);    //数据流读取  
	                    //System.out.println("\n"+"isr:"+isr);  
	                    BufferedReader in=new BufferedReader(isr);  //缓冲流  
	                    //System.out.println("\n"+"in:"+in);  
	                    String line=in.readLine();   //缓存中读取         
	                    //System.out.print("line:"+line);  
	                    if(line.equals(null))System.out.println("null");  
	                    while(line!=null){  
	                        if(line!=null&&!line.equals("")){  
	                            if(line.substring(0, 2).equals("来自")  
	                                    ||(line.length()>10&&line.substring(0, 10).equals("Reply from"))){   //判断是ping通过的IP地址  
	                                map.put(ip,"true");                     //向集合中添加IP地址  
	                               // System.out.println("!null"+map);  
	                            }  
	                        }  
	                        line=in.readLine();  
	                        //System.out.println("!null"+line);  
	                    }  
	                    //System.out.println("!line"+line);  
	                } catch (IOException e) {  
	                    // TODO: handle exception  
	                }  
	            }  
	  
	          
	    }  
}
