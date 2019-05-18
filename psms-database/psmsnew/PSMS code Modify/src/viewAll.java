import webman.*;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import java.util.*;

public class viewAll extends HttpServlet
{
    Connection con=null;
    HttpSession hs=null;
    String uid;
    int i;	
    webman.User U=new webman.User();
	webman.Dealer D=new webman.Dealer();
	webman.Part P=new webman.Part();
	webman.Suppliers S=new webman.Suppliers();
	webman.BillOfMaterials B=new webman.BillOfMaterials();
	webman.Product Pr=new webman.Product();
	webman.ProductStock Prs=new webman.ProductStock();

	Vector v=new Vector();
    Vector v1=new Vector();

	String tmp=null;
	String str=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
	try{
		res.setContentType("text/html");
		hs=req.getSession(true);
		PrintWriter pw=res.getWriter();
		String uid=hs.getValue("uid").toString();
		String cname=hs.getValue("cname").toString();
		String utype=hs.getValue("utype").toString();
	tmp=req.getParameter("Data");

	if (tmp.equals("user"))str="USERS";
	if (tmp.equals("dealer"))str="DEALERS";
	if (tmp.equals("splr"))str="SUPPLIERS";
	if (tmp.equals("prd"))str="PRODUCTS";
	if (tmp.equals("prds"))str="PRODUCT STOCK";
	if (tmp.equals("bom"))str="BILL OF MATERIALS";
	if (tmp.equals("part"))str="PARTS";


    pw.println("<html><head><TITLE>Web-Enabled Automated Manufacturing System</TITLE>");
	pw.println("</head><P align=center><FONT color=deepskyblue size=4><STRONG>DETAILS OF ALL   "+str+"    </STRONG></FONT></P> ");
	pw.println("<body><br><br>");
	pw.println("<center><TABLE border=1 cellPadding=1 cellSpacing=1 width='20%' style='HEIGHT: 50px; WIDTH: 400px'>");

   if (tmp.equals("user"))
    {
	 v1=U.allUsers();
	 pw.println("<TR><TD>CompanyName</TD><TD>Username</td><td>Password</td><td>LoginType</td><td>Email</td></tr>");
	 }
   else if(tmp.equals("dealer"))
     {
  	   if (utype.equals("Admin"))
    	  v1=D.allDealers();
   	   else if (utype.equals("Dealer"))
	      v1=D.allDealers();

	  pw.println("<TR><TD>DealerId</TD><TD>Dealername</td><td>Address</td><td>CreditLimit</td><td>Status</td></tr>");
	  }
    else if(tmp.equals("part"))
     {
	  v1=P.allParts();
	  pw.println("<TR><TD>PartId</TD><TD>Part Name</td><td>PartDescription</td><td>Status</td><td>Quantity</td><td>ReOrderLevel</td></tr>");
	  }
    else if(tmp.equals("splr"))
     {
	   if (utype.equals("Admin"))
    	  v1=S.allSuppliers();
   	   else if (utype.equals("Supplier"))
	      v1=S.allSuppliers();
	  pw.println("<TR><TD>SupplerrId</TD><TD>SupplierName</td><td>Address</td><td>CreditLimit</td><td>Status</td><td>EMail</td></tr>");
	  }
    else if(tmp.equals("bom"))
     {
	  v1=B.allBOFM();
	  pw.println("<TR><TD>ProductId</TD><td>PartId</td><td>No Of Parts Required</td></tr>");
	  }
     else if(tmp.equals("prd"))
     {
	  v1=Pr.allProducts();
	  pw.println("<TR><TD>ProductId</TD><TD>Product Name</td><td>Description</td><td>Price</td><td>Status</td></tr>");
	  }
     else if(tmp.equals("prds"))
     {
	  v1=Prs.allProductStock();
	  pw.println("<TR><TD>Dealer Id</td><td>Product id</td><td>Quantity</td><td>Reoder Level</td></tr>");
	  }


   	for(i=0;i<v1.size();i++)
	{
	if (tmp.equals("user"))
		v=U.ShowUser((String)v1.elementAt(i));
    else if(tmp.equals("dealer"))
	    v=D.getDealer((String)v1.elementAt(i));
    else if(tmp.equals("splr"))
   	     v=S.ShowSuppliers((String)v1.elementAt(i));
	else if(tmp.equals("bom"))
	    v=B.ShowBOFM((String)v1.elementAt(i));
	else if(tmp.equals("prd"))
		v=Pr.ShowProduct((String)v1.elementAt(i));
	else if(tmp.equals("prds") )
		{
		v=Prs.ShowProductStock((String)v1.elementAt(i),(String)v1.elementAt(++i));
//		System.out.println("ASDFDS " + (String)v1.elementAt(i)+ " dd " +(String)v1.elementAt(i+1) + " i=" + i);
		}
	else if(tmp.equals("part"))
	    v=P.ShowPart((String)v1.elementAt(i));
    System.out.println(v.size());
	pw.println("<TR><TD>"+(String)v.elementAt(0)+"</TD><td>"+(String)v.elementAt(1)+"</TD><TD>");
	 if(v.size()==3)
		pw.println((String)v.elementAt(2)+"</TD></Tr>");
     if(v.size()==4)
		pw.println((String)v.elementAt(2)+"</TD><td>"+(String)v.elementAt(3)+"</TD></Tr>");
     if(v.size()==5)
	 	pw.println((String)v.elementAt(2)+"</TD><td>"+(String)v.elementAt(3)+"</TD><td>"+(String)v.elementAt(4)+"</TD></tr>");
     if(v.size()==6)
	 	pw.println((String)v.elementAt(2)+"</TD><td>"+(String)v.elementAt(3)+"</TD><td>"+(String)v.elementAt(4)+"</TD><td>"+(String)v.elementAt(5)+"</TD></tr>");
     }

	          pw.println("</table></center></body></html>");
	//pw.println("<center><A HREF='./getUser'>continue</a></center></body></html>");
	
			pw.flush();
			pw.close();
			v=null;
			v1=null;
			}catch(Exception e){}
			}
			
            
}