import java.sql.*;

public class Sample2 {
	public static void main(String args[])    {
		try{

			//�ڑ��̏��� 
			String url = "jdbc:derby:cardb;create=true";
			String usr = "";
			String pw = "";



			//�f�[�^�x�[�X�ւ̐ڑ� 
			Connection cn = DriverManager.getConnection(url, usr, pw);

			//���O�Ƀf�[�^����Ƃ��Ȃ��ƃ_���Ȃ񂩂Ȃ�
			Statement st = cn.createStatement();
			//�e�[�u���̍쐬
			//st.executeUpdate("CREATE TABLE �ԕ\(�ԍ� int, ���O varchar(50),���x double, ���[�J varchar(10))");
			//�f�[�^�̑}��
			String [] instQry =   { "INSERT INTO �ԕ\ VALUES (12, '��p��', 123.4, '�g�~�^')","INSERT INTO �ԕ\ VALUES (13, '�I�[�v���J�[', 234.5, '�ڎY')","INSERT INTO �ԕ\ VALUES (14, '�g���b�N', 199.9, '�I���_')","INSERT INTO �ԕ\ VALUES (25, '�~�j�o��', 399.9, '�h�C�n�c')","INSERT INTO �ԕ\ VALUES (26, '�����{�b�N�X', 288.8, '�i�c�_')" };
			for (String qry :instQry ){
				//st.executeUpdate(qry);
			}
			//�}���m�F
			ResultSet rst;
			//�₢���킹
	         	rst = st.executeQuery("SELECT * FROM �ԕ\"); 
	         	//�f�[�^�̎擾
	         	ResultSetMetaData rm = rst.getMetaData();
	         	int cnum = rm.getColumnCount();
	         	System.out.println("SELECT * FROM �ԕ\");
	         while(rst.next()){
	            for(int i=1; i<=cnum; i++){
	                System.out.print(rm.getColumnName(i) +  ":"+ rst.getObject(i) + "  ");
	         }
            System.out.println("");
	         }
	         System.out.println("---------------------------------------------------------------------------");
			//�₢���킹�̏��� 
			Statement st1 = cn.createStatement();
			String qry1 = "SELECT * FROM �ԕ\ WHERE �ԍ�>=20 OR ���x<150";
			String qry2 = "SELECT * FROM �ԕ\ WHERE �ԍ� BETWEEN 10 AND 20";
			String qry3 = "SELECT * FROM �ԕ\ WHERE ���[�J<>'�g�~�^' AND ���x>200";
			String qry4 = "SELECT MAX(���x),���[�J FROM �ԕ\ GROUP BY ���[�J";
			String qry5 = "SELECT * FROM �ԕ\ WHERE ���x = (SELECT MAX(���x) FROM �ԕ\)";
			String[] qrys = {qry1,qry2,qry3,qry4,qry5};
			ResultSet rs = st1.executeQuery(qry1);
			for (String qry : qrys) {
				//�₢���킹1 
				rs = st1.executeQuery(qry);


				//�f�[�^�̎擾 
				ResultSetMetaData rm1 = rs.getMetaData();
				int cnum1 = rm1.getColumnCount();
				System.out.println("���s����SQL��:" + qry);
				while(rs.next()){
					for(int i=1;
							i<=cnum1;
							i++){
						System.out.print(rm1.getColumnName(i) +  ":"+ rs.getObject(i) + "\t");

					}
					System.out.println("");

				}
				System.out.println("");
			}
			//�ڑ��̃N���[�Y 
			rs.close();
			st1.close();
			cn.close();

		}
		catch(Exception e){
			e.printStackTrace();

		}

	}

}
