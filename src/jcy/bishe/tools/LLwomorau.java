package jcy.bishe.tools;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
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
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;






public class LLwomorau {


	public static final int DEFAULT_VERSION = SnmpConstants.version2c;  
    public static final String DEFAULT_PROTOCOL = "udp";  
    public static final int DEFAULT_PORT = 161;  
    public static final long DEFAULT_TIMEOUT = 3 * 1000L;  
    public static final int DEFAULT_RETRY = 3;  
    public static String num;
    public static boolean asss=true;
    /** 
     * 创建对象communityTarget 
     * 
     * @param targetAddress 
     * @param community 
     * @param version 
     * @param timeOut 
     * @param retry 
     * @return CommunityTarget 
     */  
    public static CommunityTarget createDefault(String ip, String community) {  
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip  
                + "/" + DEFAULT_PORT);  
        CommunityTarget target = new CommunityTarget();  
        target.setCommunity(new OctetString(community));  
        target.setAddress(address);  
        target.setVersion(DEFAULT_VERSION);  
        target.setTimeout(DEFAULT_TIMEOUT); // milliseconds  
        target.setRetries(DEFAULT_RETRY);  
        return target;  
    }  
  
    /** 
     * @param ip 
     * @param community 
     * @param oid 
     */  
    public static void snmpWalk(String ip, String community, String targetOid, String mac) {  
  
        CommunityTarget target = createDefault(ip, community);  
        TransportMapping transport = null;  
        Snmp snmp = null;  
        try {  
            transport = new DefaultUdpTransportMapping();  
            snmp = new Snmp(transport);  
            transport.listen();  
  
            PDU pdu = new PDU();  
            OID targetOID = new OID(targetOid);  
            pdu.add(new VariableBinding(targetOID));  
  
            boolean finished = false;  
            //System.out.println("----> demo start <----");  
            while (!finished) {  
                VariableBinding vb = null;  
                ResponseEvent respEvent = snmp.getNext(pdu, target);  
  
                PDU response = respEvent.getResponse();  
  
                if (null == response) {  
                    System.out.println("responsePDU == null");  
                    finished = true;  
                    asss=false;
                    break;  
                } else {  
                    vb = response.get(0);  
                }  
                // check finish  
                finished = checkWalkFinished(targetOID, pdu, vb);  
                if (!finished) {  
//                    System.out.println("==== walk each vlaue :");  
//                    System.out.println(vb.getVariable().toString() );  
                    if(vb.getVariable().toString().equals(mac)){
                    	
                    	//System.out.println(vb.getOid());
                    	String oid =vb.getOid().toString();
                    	oid =oid.substring(20);
                    	num=oid;
//                    	System.out.println(num);
                    	break;
                    }
                    // Set up the variable binding for the next entry.  
                    pdu.setRequestID(new Integer32(0));  
                    pdu.set(0, vb);  
                } else {  
                    System.out.println("SNMP walk OID has finished.");  
                    snmp.close();  
                }  
            }  
            //System.out.println("----> demo end <----");  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("SNMP walk Exception: " + e);  
        } finally {  
            if (snmp != null) {  
                try {  
                    snmp.close();  
                } catch (IOException ex1) {  
                    snmp = null;  
                }  
            }  
        }  
  
    }  
  
    /** 
     * 1)responsePDU == null<br> 
     * 2)responsePDU.getErrorStatus() != 0<br> 
     * 3)responsePDU.get(0).getOid() == null<br> 
     * 4)responsePDU.get(0).getOid().size() < targetOID.size()<br> 
     * 5)targetOID.leftMostCompare(targetOID.size(),responsePDU.get(0).getOid()) 
     * !=0<br> 
     * 6)Null.isExceptionSyntax(responsePDU.get(0).getVariable().getSyntax())<br> 
     * 7)responsePDU.get(0).getOid().compareTo(targetOID) <= 0<br> 
     * 
     * @param resquestPDU 
     * @param targetOID 
     * @param responsePDU 
     * @param vb 
     * @return 
     */  
    private static boolean checkWalkFinished(OID targetOID, PDU pdu,  
            VariableBinding vb) {  
        boolean finished = false;  
        if (pdu.getErrorStatus() != 0) {  
            System.out.println("[true] responsePDU.getErrorStatus() != 0 ");  
            System.out.println(pdu.getErrorStatusText());  
            finished = true;  
        } else if (vb.getOid() == null) {  
            System.out.println("[true] vb.getOid() == null");  
            finished = true;  
        } else if (vb.getOid().size() < targetOID.size()) {  
            System.out.println("[true] vb.getOid().size() < targetOID.size()");  
            finished = true;  
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {  
            System.out.println("[true] targetOID.leftMostCompare() != 0");  
            finished = true;  
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {  
            System.out  
                    .println("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");  
            finished = true;  
        } else if (vb.getOid().compareTo(targetOID) <= 0) {  
            System.out.println("[true] Variable received is not "  
                    + "lexicographic successor of requested " + "one:");  
            System.out.println(vb.toString() + " <= " + targetOID);  
            finished = true;  
        }  
        return finished;  
  
    }  
    
    
    
    
    
    
	public static void getresult(String ips,String macs) {
		// TODO Auto-generated method stub
		asss=true;
		Iterator map1it=Macwomorau.MACMap.entrySet().iterator();
        while(map1it.hasNext())
        {
         Map.Entry entry=(Map.Entry) map1it.next();
         System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
        }

        
		String ip = ips;
		String mac=macs;
        String community = "public";  
        // 1.3.6.1.2.1.2.2.1.2  
        String targetOid = ".1.3.6.1.2.1.2.2.1.6";
        LLwomorau.snmpWalk(ip, community, targetOid, mac);
        // TODO Auto-generated method stub
		//Snmp的三个版本号
		//int ver3 = SnmpConstants.version3;
        if(asss==true){
        	int ver2c = SnmpConstants.version2c;
    		//int ver1 = SnmpConstants.version1;
    		LLmora manager = new LLmora(ver2c); 
    		LLwomorau L1 =new LLwomorau();
    		String num =L1.num;
    		String ipcode ="udp:"+ip+"/161";
    		//((ScopedPDU) pdu).setContextName(new OctetString("priv"));
    		try {
    		    // 发送消息 其中最后一个是想要发送的目标地址
    		    //manager.sendMessage(false, true, pdu, "udp:192.168.1.229/161");//192.168.1.229 Linux服务器
    		    manager.sendMessage(false, true, ipcode, num);//192.168.1.233 WinServer2008服务器
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }else{
        	System.out.println("该电脑没有配置snmp服务");
        }
        
		
		
	}

}
