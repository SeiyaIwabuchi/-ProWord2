import java.sql.*;

public class Sample3 {
	public static void main(String args[]) {
		String qry1 = "";
		if (args.length != 4) {
			System.out.println("�p�����[�^�̐����Ⴂ�܂��B");
			System.exit(1);

		}
		
		try {

			// �ڑ��̏���
			String url = "jdbc:derby:cardb;create=true";
			String usr = "";
			String pw = "";

			// �f�[�^�x�[�X�ւ̐ڑ�
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
			
			// �₢���킹�̏���
			Statement st1 = cn.createStatement();
			qry1 = "INSERT INTO �ԕ\ VALUES (" + args[0] + ", '" + args[1] + "'," + args[2] + ", '" + args[3] + "')";
			String qry2 = "SELECT * FROM �ԕ\";

			// �₢���킹
			st1.executeUpdate(qry1);
			ResultSet rs = st1.executeQuery(qry2);

			// �f�[�^�̎擾
			ResultSetMetaData rm = rs.getMetaData();
			int cnum = rm.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= cnum; i++) {
					System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + "  ");

				}
				System.out.println("");

			}

			// �ڑ��̃N���[�Y
			rs.close();
			st1.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(qry1);
			e.printStackTrace();

		}

	}

}
