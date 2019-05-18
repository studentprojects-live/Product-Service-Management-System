

1.CREATE THE DSN 

	CONTROL PANEL==>OPEN ODBC DATASOURCES ==>CLICK ON USERDSN => SAY ADD
		
		select microsoft access driver(*.mdb) 

		ENTER THE DSN NAME AS 'WebMan'

		select the database file 'from the folder DATABASE as web.mdb'


2.  set path=%path%;c:\jdk1.3\bin

 set classpath=.;c:\jsdk2.1\servlet.jar;c:\webman\JMail\mail.jar;c:\webman\JMail\activation.jar		

4.run the batch file cc.bat  or cc1.bat

4.1 copy 'docS directory ' to 'c:\jsdk2.1\httproot\' 
	
for javawebserver
	copy docs directory to c:\javawebserver2.0\public_html


5. goto commandprompt 
		
	change directory to c:\jsdk2.1		

	run file say 'runner'


	for javawebserver
		
	c:\javawebserver2.0\bin\httpd


6. goto internet explorer

in address box enter :http://localhost:8080/docs/Index.htm

[in address box enter :http://127.0.0.1:8080/docs/Index.htm

in address box enter :http://ipaddress:8080/docs/Index.htm

in address box enter :http://systemname:8080/docs/Index.htm]






		
