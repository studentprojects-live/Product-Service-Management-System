import java.util.Enumeration;

import javax.servlet.ServletContext;

/**
 * 
 */

/**
 * @author activeproject
 *
 */
public class LoggedInUsers {
	ServletContext context;
	String name=new String();
	String username=new String();
	boolean found;

	 	//constructor for taking context and username from LoginServlet class
		LoggedInUsers(ServletContext ctxt,String username){
			context=ctxt;
			this.username=username;
		}

		//Finds whether user has already logged in	
		public boolean getExistingUser(){
			Enumeration enumeration=context.getAttributeNames();
			while(enumeration.hasMoreElements()){
			name=enumeration.nextElement().toString();
			String name2=context.getAttribute(name).toString();
			if(name2.equals(username)){
					found=true;
			}//if end
			}//while end
			//context.setAttribute(username,username);
			return found;
		}

}
