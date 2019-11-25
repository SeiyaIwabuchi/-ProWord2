import java.sql.*;

public class Sample2 {
	public static void main(String args[])    {
		try{

			//接続の準備 
			String url = "jdbc:derby:cardb;create=true";
			String usr = "";
			String pw = "";



			//データベースへの接続 
			Connection cn = DriverManager.getConnection(url, usr, pw);

			//事前にデータ入れとかないとダメなんかなぁ
			Statement st = cn.createStatement();
			//テーブルの作成
			//st.executeUpdate("CREATE TABLE 車表(番号 int, 名前 varchar(50),速度 double, メーカ varchar(10))");
			//データの挿入
			String [] instQry =   { "INSERT INTO 車表 VALUES (12, '乗用車', 123.4, 'トミタ')","INSERT INTO 車表 VALUES (13, 'オープンカー', 234.5, '目産')","INSERT INTO 車表 VALUES (14, 'トラック', 199.9, 'オンダ')","INSERT INTO 車表 VALUES (25, 'ミニバン', 399.9, 'ドイハツ')","INSERT INTO 車表 VALUES (26, 'ワンボックス', 288.8, 'ナツダ')" };
			for (String qry :instQry ){
				//st.executeUpdate(qry);
			}
			//挿入確認
			ResultSet rst;
			//問い合わせ
	         	rst = st.executeQuery("SELECT * FROM 車表"); 
	         	//データの取得
	         	ResultSetMetaData rm = rst.getMetaData();
	         	int cnum = rm.getColumnCount();
	         	System.out.println("SELECT * FROM 車表");
	         while(rst.next()){
	            for(int i=1; i<=cnum; i++){
	                System.out.print(rm.getColumnName(i) +  ":"+ rst.getObject(i) + "  ");
	         }
            System.out.println("");
	         }
	         System.out.println("---------------------------------------------------------------------------");
			//問い合わせの準備 
			Statement st1 = cn.createStatement();
			String qry1 = "SELECT * FROM 車表 WHERE 番号>=20 OR 速度<150";
			String qry2 = "SELECT * FROM 車表 WHERE 番号 BETWEEN 10 AND 20";
			String qry3 = "SELECT * FROM 車表 WHERE メーカ<>'トミタ' AND 速度>200";
			String qry4 = "SELECT MAX(速度),メーカ FROM 車表 GROUP BY メーカ";
			String qry5 = "SELECT * FROM 車表 WHERE 速度 = (SELECT MAX(速度) FROM 車表)";
			String[] qrys = {qry1,qry2,qry3,qry4,qry5};
			ResultSet rs = st1.executeQuery(qry1);
			for (String qry : qrys) {
				//問い合わせ1 
				rs = st1.executeQuery(qry);


				//データの取得 
				ResultSetMetaData rm1 = rs.getMetaData();
				int cnum1 = rm1.getColumnCount();
				System.out.println("実行したSQL文:" + qry);
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
			//接続のクローズ 
			rs.close();
			st1.close();
			cn.close();

		}
		catch(Exception e){
			e.printStackTrace();

		}

	}

}
