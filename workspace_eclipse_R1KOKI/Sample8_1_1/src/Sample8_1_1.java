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
		   System.out.println("������^���Ă��������B");
		   return;
	   }
      try{
         //�ڑ��̏���
         String url = "jdbc:derby:cardb;create=true";
         String usr = "";
         String pw = "";

         //�f�[�^�x�[�X�ւ̐ڑ�
         Connection cn = DriverManager.getConnection(url, usr, pw);

         //�₢���킹�̏���
         DatabaseMetaData dm = cn.getMetaData();
         ResultSet tb = dm.getTables(null, null, "�ԕ\", null);

         Statement st = cn.createStatement();
         
        String qry1 = "CREATE TABLE �ԕ\(�ԍ� int, ���O varchar(50),���x double, ���[�J varchar(10))";
		String qry2 =  String.format("INSERT INTO �ԕ\ VALUES (%s, '%s', %s, '%s')",args[0],args[1],args[2] ,args[3]); 
		String qry3 = "SELECT * FROM �ԕ\";
		
		try {
			st.executeUpdate(qry2);
		}catch (java.sql.SQLSyntaxErrorException e) {
			st.executeUpdate(qry1);
			st.executeUpdate(qry2);
		}finally {
			System.out.println("�X�V�ɐ������܂����B");
		}

         //�₢���킹
         ResultSet rs = st.executeQuery(qry3); 

         //�f�[�^�̎擾
         ResultSetMetaData rm = rs.getMetaData();
         int cnum = rm.getColumnCount();
         while(rs.next()){
            for(int i=1; i<=cnum; i++){
                System.out.print(rm.getColumnName(i) +  ":"+ rs.getObject(i) + "  ");
            }
            System.out.println("");
         }

         //�ڑ��̃N���[�Y
         rs.close();
         st.close();
         cn.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}