import java.sql.*;

public class Sample8_1
{
   public static void main(String args[])
   {
	   System.out.println(args[0]);
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
			String[] qry2 = { "INSERT INTO �ԕ\ VALUES (12, '��p��', 123.4, '�g�~�^')",
					"INSERT INTO �ԕ\ VALUES (13, '�I�[�v���J�[', 234.5, '�ڎY')",
					"INSERT INTO �ԕ\ VALUES (14, '�g���b�N', 199.9, '�I���_')",
					"INSERT INTO �ԕ\ VALUES (25, '�~�j�o��', 399.9, '�h�C�n�c')",
					"INSERT INTO �ԕ\ VALUES (26, '�����{�b�N�X', 288.8, '�i�c�_')" }; 
         String qry3 = "SELECT * FROM �ԕ\";
         String qry4 = "DROP TABLE �ԕ\";

         if(!tb.next()){
        	 st.executeUpdate(qry1);
            for(int i=0; i<qry2.length; i++){
            	st.executeUpdate(qry2[i]);
            }
         }else {
        	 st.execute(qry4);
        	 st.executeUpdate(qry1);
        	 for(int i=0; i<qry2.length; i++){
             	st.executeUpdate(qry2[i]);
             }
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