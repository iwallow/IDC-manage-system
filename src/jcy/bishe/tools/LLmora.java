package jcy.bishe.tools;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;



public class LLmora {
	 private Snmp snmp = null;
     private int version ;
     String[][] macad=new String[2][12];
     private int j=0;
     public static String averliuliang="null";
     public static String indatapacsum="null";
     public static String outdatapacsum="null";
     public LLmora(int version) {
         try {
             this.version = version;
             TransportMapping transport = new DefaultUdpTransportMapping();
             snmp = new Snmp(transport);
             if (version == SnmpConstants.version3) {
                 // 设置安全模式
                 USM usm = new USM(SecurityProtocols.getInstance(),new OctetString(MPv3.createLocalEngineID()), 0);
                 SecurityModels.getInstance().addSecurityModel(usm);
             }
             // 开始监听消息
             transport.listen();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     public void sendMessage(Boolean syn, final Boolean bro, String addr, String num)
             throws IOException {
    	 //System.out.println(num);
    	 for( ;j<2;j++){
    		 // 生成目标地址对象
         Address targetAddress = GenericAddress.parse(addr);
//         System.out.println(targetAddress+);
//         System.out.println(addr);
         Target target = null;
         if (version == SnmpConstants.version3) {
             // 添加用户
             snmp.getUSM().addUser(new OctetString("MD5DES"),new UsmUser(new OctetString("MD5DES"), AuthMD5.ID,new OctetString("MD5DESUserAuthPassword"),PrivDES.ID, new OctetString("MD5DESUserPrivPassword")));
             target = new UserTarget();
             // 设置安全级别
             ((UserTarget) target).setSecurityLevel(SecurityLevel.AUTH_PRIV);
             ((UserTarget) target).setSecurityName(new OctetString("MD5DES"));
             target.setVersion(SnmpConstants.version3);
         } else {
             target = new CommunityTarget();
             if (version == SnmpConstants.version1) {
                 target.setVersion(SnmpConstants.version1);
                 ((CommunityTarget) target).setCommunity(new OctetString("public"));
            } else {
                 target.setVersion(SnmpConstants.version2c);
                 ((CommunityTarget) target).setCommunity(new OctetString("public"));
             }
 
         }
         // 目标对象相关设置
         // 构造报文
    	 PDU pdu = new PDU();
    	 //PDU pdu = new ScopedPDU();
    	 // 设置要获取的对象ID，这个OID代表远程计算机的名称
    	 OID oids =new OID(".1.3.6.1.2.1.1.3.0");
    	 OID oids1 = new OID(".1.3.6.1.2.1.2.2.1.5."+num);
    	 OID oids2 = new OID(".1.3.6.1.2.1.2.2.1.10."+num);
    	 OID oids3 = new OID(".1.3.6.1.2.1.2.2.1.11."+num);
    	 OID oids4 = new OID(".1.3.6.1.2.1.2.2.1.12."+num);
    	 OID oids5 = new OID(".1.3.6.1.2.1.2.2.1.13."+num);
    	 OID oids6 = new OID(".1.3.6.1.2.1.2.2.1.14."+num);
    	 OID oids7 = new OID(".1.3.6.1.2.1.2.2.1.16."+num);
    	 OID oids8 = new OID(".1.3.6.1.2.1.2.2.1.17."+num);
    	 OID oids9 = new OID(".1.3.6.1.2.1.2.2.1.18."+num);
    	 OID oids10 = new OID(".1.3.6.1.2.1.2.2.1.19."+num);
    	 OID oids11 = new OID(".1.3.6.1.2.1.2.2.1.20."+num);
    	 pdu.add(new VariableBinding(oids));
    	 pdu.add(new VariableBinding(oids1));
    	 pdu.add(new VariableBinding(oids2));
    	 pdu.add(new VariableBinding(oids3));
    	 pdu.add(new VariableBinding(oids4));
    	 pdu.add(new VariableBinding(oids5));
    	 pdu.add(new VariableBinding(oids6));
    	 pdu.add(new VariableBinding(oids7));
    	 pdu.add(new VariableBinding(oids8));
    	 pdu.add(new VariableBinding(oids9));
    	 pdu.add(new VariableBinding(oids10));
    	 pdu.add(new VariableBinding(oids11));
    	 // 设置报文类型
    	 pdu.setType(PDU.GET);
         target.setAddress(targetAddress);
         target.setRetries(5);
         target.setTimeout(1000);
 
         if (!syn) {
             // 发送报文 并且接受响应
             ResponseEvent response = snmp.send(pdu, target);
             // 处理响应
//             System.out.println("Synchronize(同步) message(消息) from(来自) "
//                     + response.getPeerAddress() + "\r\n"+"request(发送的请求):"
//                     + response.getRequest() + "\r\n"+"response(返回的响应):"
//                     + response.getResponse());
             
             
//             System.out.println(response.getResponse().getVariableBindings());
             
             
             if (response != null && response.getResponse() != null) {
                    Vector<VariableBinding> recVBs = (Vector<VariableBinding>) response.getResponse().getVariableBindings();
                    for (int i = 0; i < recVBs.size(); i++) {
                           VariableBinding recVB = recVBs.elementAt(i);
                           macad[j][i] =recVB.getVariable().toString();
                           //System.out.println(macad[j][i]);
                           }
                    }
             }
         if(j==0){
        	 MyThread thread =new MyThread();
             thread.start();
             try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         if(j==1){
        	long[] a=new long[12];
        	long[][] b=new long[2][12];
//        	System.out.println(macad[0][0]);
//        	System.out.println(macad[1][0]);
        	for(int t=1; t<12;t++){
        		
        		b[0][t]=Long.parseLong(macad[0][t]);
        		b[1][t]=Long.parseLong(macad[1][t]);
        		a[t]=b[1][t]-b[0][t];
        		//System.out.println(a[t]);
        		
        	}
        	Double liuliang=((double)(a[2]+a[7]))/10.0;
        	//System.out.println(liuliang);
        	if((liuliang)<1000){
        		
        		//System.out.println(liuliang+"B/s");
        		averliuliang=liuliang.toString()+"B/s";
        	}
        	else if((liuliang/1000)<1000){
        		//System.out.println((liuliang/1000)+"KB/s");
        		liuliang=(liuliang/1000);
        		averliuliang=liuliang.toString()+"KB/s";
        	}
        	else if((liuliang/1000000)<1000){
        		//System.out.println((liuliang/1000000)+"MB/s");
        		liuliang=(liuliang/1000000);
        		averliuliang=liuliang.toString()+"MB/s";
        	}
        	Long ruzongshu=a[3]+a[4];
        	indatapacsum=ruzongshu.toString();
        	//System.out.println(ruzongshu);
        	Long chuzongshu=a[8]+a[9];
        	outdatapacsum=chuzongshu.toString();
        	//System.out.println(chuzongshu);
         }
         
    }
         
}
     
     /*public static void main(String[] args) {
    		// TODO Auto-generated method stub
    		//Snmp的三个版本号
    		  		String ip = "49.140.171.80";
    		        //int ver3 = SnmpConstants.version3;
    		         int ver2c = SnmpConstants.version2c;
    		         //int ver1 = SnmpConstants.version1;
    		         LLmora manager = new LLmora(ver2c); 
    		         LLwomorau L1 =new LLwomorau();
    		         int num =L1.num;
    		         
    		        	 
    		        	 //((ScopedPDU) pdu).setContextName(new OctetString("priv"));
    		        	 try {
    		        		 // 发送消息 其中最后一个是想要发送的目标地址
    		        		 //manager.sendMessage(false, true, pdu, "udp:192.168.1.229/161");//192.168.1.229 Linux服务器
    		        		 manager.sendMessage(false, true, ip,num);//192.168.1.233 WinServer2008服务器
    		        	 } catch (IOException e) {
    		        		 e.printStackTrace();
    		        	 }
    		         
    	}*/
     
}
