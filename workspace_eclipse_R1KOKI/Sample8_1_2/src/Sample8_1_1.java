import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Sample8_1_1
{
   public static void main(String args[])
   {
	   if (args.length == 0) {
		   System.out.println("引数を与えてください。");
		   return;
	   }
      try{
         //接続の準備
         String url = "jdbc:derby:cardb;create=true";
         String usr = "";
         String pw = "";

         //データベースへの接続
         Connection cn = DriverManager.getConnection(url, usr, pw);

         //問い合わせの準備
         DatabaseMetaData dm = cn.getMetaData();
         ResultSet tb = dm.getTables(null, null, "車表", null);

         Statement st = cn.createStatement();
         String qry2;
         if(args[0].equals("u")) {
        	 qry2 =  String.format("UPDATE 車表 SET 番号=%s,名前='%s',速度=%s,メーカ='%s' WHERE 番号=%s",args[1],args[2],args[3] ,args[4],args[1]); 
         }else if(args[0].equals("i")){
        	 qry2 =  String.format("INSERT INTO 車表 VALUES (%s, '%s', %s, '%s')",args[1],args[2],args[3] ,args[4]); 
         }else {
        	 System.out.println(String.format("無効な引数:%s",args[0]));
        	 return;
         }
        String qry1 = "CREATE TABLE 車表(番号 int, 名前 varchar(50),速度 double, メーカ varchar(10))";
		String qry3 = "SELECT * FROM 車表";
		
		try {
			System.out.println("SQLクエリ:" + qry2);
			st.executeUpdate(qry2);
		}catch (java.sql.SQLSyntaxErrorException e) {
			//テーブルが存在しない場合
			e.printStackTrace();
			st.executeUpdate(qry1);
			st.executeUpdate(qry2);
		}finally {
			System.out.println("result:done.");
		}

         //問い合わせ
         ResultSet rs = st.executeQuery(qry3); 

         //データの取得
         ResultSetMetaData rm = rs.getMetaData();
         int cnum = rm.getColumnCount();
         while(rs.next()){
            for(int i=1; i<=cnum; i++){
                System.out.print(rm.getColumnName(i) +  ":"+ rs.getObject(i) + "  ");
            }
            System.out.println("");
         }

         //接続のクローズ
         rs.close();
         st.close();
         cn.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}