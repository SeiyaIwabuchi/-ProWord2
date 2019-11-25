import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sample8_1_Console
{
   public static void main(String args[])
   {
	   System.out.println("JDBCコンソール");
	   try{
         //接続の準備
         String url = "jdbc:derby:cardb;create=true";
         String usr = "";
         String pw = "";

         //データベースへの接続
         
         String str = "";
         while(true) {
        	 Connection cn = DriverManager.getConnection(url, usr, pw);

             //問い合わせの準備
             DatabaseMetaData dm = cn.getMetaData();
             ResultSet rs;

             Statement st = cn.createStatement();
        	try {
        		InputStreamReader isr = new InputStreamReader(System.in);
        		BufferedReader br = new BufferedReader(isr);
        		str = br.readLine();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	String[] splitedStr = str.split(" ");
			try {
	        	switch(splitedStr[0]) {
	        		case "SELECT":
	        			//問い合わせ
	       	         	rs = st.executeQuery(str); 
	       	         	//データの取得
	       	         	ResultSetMetaData rm = rs.getMetaData();
	       	         	int cnum = rm.getColumnCount();
		    	         while(rs.next()){
		    	            for(int i=1; i<=cnum; i++){
		    	                System.out.print(rm.getColumnName(i) +  ":"+ rs.getObject(i) + "  ");
		    	         }
	    	            System.out.println("");
		    	         }
		    	         rs.close();
	        			break;
	        		case "INSERT":
	        			st.executeUpdate(str);
	        			break;
	        		case "CREATE":
	        			st.executeUpdate(str);
	        			break;
	        		case "DELETE":
	        			st.executeUpdate(str);
	        			break;
	        		case "UPDATE":
	        			st.executeUpdate(str);
	        			break;
	        		case "DROP":
	        			st.execute(str);
	        			break;
	        		case "exit":
	        			System.out.println("Bye.");
	        			return;
	        		default:
	        			System.out.println("Undefined instruction" + splitedStr[0]);
	        			break;
	        	}
	        	System.out.println("done.");
			}catch (java.sql.SQLSyntaxErrorException e) {
				e.printStackTrace();
			}
	         st.close();
	         cn.close();
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}