package jcy.bishe.controllers;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcy.bishe.mappers.MyMapper;

import jcy.bishe.models.userInf;
import jcy.bishe.tools.LLmora;
import jcy.bishe.tools.LLwomorau;
import jcy.bishe.tools.Macwomorau;
import jcy.bishe.tools.listen;
import jcy.bishe.tools.uploadAddress;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;








@Controller
public class LoadController {

	
	@Autowired
	private MyMapper mymapper;
	
	
	
	
	@RequestMapping("/getresult.do")
	public void getsres(HttpServletRequest request,HttpServletResponse response){
		String p = request.getParameter("param123");
		//System.out.println(p);
		String [] aks=p.split("-");
		LLwomorau.getresult(aks[0], aks[1]);
		if(LLwomorau.asss){
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				String ask="<tr><td>"+"IP��ַ"+"</td>"+"<td>" +"Mac��ַ"+"</td>"+"<td>"+"10s��ƽ������"+"</td>"+
		         "<td>"+"10s�ڽ������ݰ�"+"</td>"+
		         "<td>"+"10s�ڷ������ݰ�"+"</td></tr>";
				out.print(ask+"<tr><td>"+aks[0]+"</td>"+"<td>" +aks[1]+"</td>"+"<td>"+LLmora.averliuliang+"</td>"+
				         "<td>"+LLmora.indatapacsum+"��</td>"+
				         "<td>"+LLmora.outdatapacsum+"��</td></tr>");
		     	out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				String ask="<tr><td>"+"IP��ַ"+"</td>"+"<td>" +"Mac��ַ"+"</td>"+"<td>"+"10s��ƽ������"+"</td>"+
		         "<td>"+"10s�ڽ������ݰ�"+"</td>"+
		         "<td>"+"10s�ڷ������ݰ�"+"</td></tr>";
				out.print(ask+"<tr><td>"+aks[0]+"</td>"+"<td>" +aks[1]+"</td>"+"<td>"+"δ����snmp"+"</td>"+
				         "<td>"+"δ����snmp"+"</td>"+
				         "<td>"+"δ����snmp"+"</td></tr>");
		     	out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	@RequestMapping("/queryallnum.do")
	public void queryallnum(HttpServletRequest request,HttpServletResponse response){
		
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();

			out.print(uploadAddress.al.size());
	     	out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@RequestMapping("/que.do")
	public void que(HttpServletRequest request,HttpServletResponse response){
		int start =0;
		int end=uploadAddress.al.size();
		String p = request.getParameter("param123");
		if(Integer.parseInt(p)<10){
			start=0;
		}else{
			start=Integer.parseInt(p);
		}
		if(end-start>10){
			end=start+10;
		}else{
			
		}
		
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String [] aa=new String[uploadAddress.al.size()];
		      int j=0;
				for(int i=start;i<end;i++){
					
					String ff=Macwomorau.gainAllIp(uploadAddress.al.get(i));
					aa[j]="<td>"+uploadAddress.al.get(i)+"</td>"+
			         "<td>"+ff+"</td>"+
			         "<td>"+"255.255.255.0"+"</td>"+
			         "<td>"+uploadAddress.al.get(i)+"</td>"+
			         "<td>"+uploadAddress.al.get(i)+"</td>";
			
					j++;
				}
				String ssss="";
				for(int i=0;i<aa.length;i++){
					ssss+=aa[i]+"*";
				}
				
				out.print(ssss);
			
				out.flush();
				out.close();
			
		    
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
		
	
	@RequestMapping("/show.do")
	public void show(HttpServletRequest request,HttpServletResponse response){
		
		// Thread ssas= new Thread(new listen());
		 //ssas.start();
		uploadAddress aasd= new uploadAddress();
		aasd.getResult();
		for(int i=0;i<uploadAddress.al.size();i++){
			Integer cs =mymapper.selectIP(uploadAddress.al.get(i));
			//System.out.println(cs);
			if(cs==null||cs<=0){
				try {
					String mac =Macwomorau.gainAllIp(uploadAddress.al.get(i));
					mymapper.insertIP(uploadAddress.al.get(i),mac);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				
			}
			
		}
		
		
  
	}

	@RequestMapping("/sign.do")
	public ModelAndView sign(userInf usermodle,HttpServletRequest  request){
	String name =request.getParameter("ע��");
		
		String userN =request.getParameter("name").trim();
		String passW =request.getParameter("password").trim();
		
		if(userN!=null&&passW!=null&&!userN.equals("")&&!passW.equals("")){
			if(name==null){
				
				userInf ums =mymapper.selectUser(userN);
			     System.out.println(ums.getPassword());
				if(ums!=null&&ums.getPassword().trim().equals(passW)){
					ModelAndView mav =new ModelAndView();
					mav.setViewName("Untitled1.jsp");
					return mav;
				}else{
					
					ModelAndView mav =new ModelAndView();
				    mav.setViewName("default.jsp");
					return mav;
				}
				
				
			}else{
				
				ModelAndView mav =new ModelAndView();
				mymapper.signuser(usermodle);
				mav.setViewName("success.jsp");
				return mav;
			}
		}else{
			
			ModelAndView mav =new ModelAndView();
		    mav.setViewName("default.jsp");
			return mav;
		}
		
		
	}
	
	
	@RequestMapping("/query.do")
	public void query(HttpServletRequest request,HttpServletResponse response){
		
	/*	int start =0;
		int pageNums=8;
		try {
			String  p = request.getParameter("param123");
		
			
			start=Integer.parseInt(p);
			
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			ArrayList<MensClothingModel> mcm =mapper.queryAll(start,pageNums);
			String [] aa=new String[mcm.size()];
			for(int i=0;i<mcm.size();i++){
				
				 aa[i]="<tr><td>"+mcm.get(i).getId()+"</td>"+
		         "<td>"+mcm.get(i).getName()+"</td>"+
		         "<td>"+mcm.get(i).getColor()+"</td>"+
		         "<td>"+mcm.get(i).getManufacturer()+"</td>"+
		         "<td>"+mcm.get(i).getMaterialQuality()+"</td>"+
		         "<td>"+mcm.get(i).getPlaceOfOrigin()+"</td>"+
		         "<td>"+mcm.get(i).getPrice()+"</td>"+
		         "<td>"+mcm.get(i).getSize()+"</td>"+
		         "<td>"+mcm.get(i).getSuitCrowds()+"</td>"+
		         "<td>"+mcm.get(i).getImageName()+"</td>"+
		         "<td>"+mcm.get(i).getImageOneName()+"</td>"+
		         "<td>"+mcm.get(i).getType()+"</td>"+
		         "<td>"+mcm.get(i).getListingDate()+"</td></tr>";
		
				
			}
			String ssss="<tr><th>���</th>" +
					    "<th>��Ʒ��</th>"+
                        "<th>��ɫ </th>"+
                        "<th>���� </th>"+
                        "<th>ԭ���� </th>"+
                        "<th>���� </th>"+
                        "<th>�۸� </th>"+
                        "<th>�ߴ� </th>"+
                        "<th>�ʺ���Ⱥ </th>"+
                        "<th>ͼƬһ </th>"+
                        "<th>ͼƬ�� </th>"+
                        "<th>���� </th>"+
                        "<th>����ʱ�� </th></tr>";
			for(int i=0;i<aa.length;i++){
				ssss+=aa[i];
			}
			
			out.print(ssss);
		
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	
	
}
