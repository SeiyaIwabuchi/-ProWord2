import java.sql.*;

public class Sample3 {
	public static void main(String args[]) {
		String qry1 = "";
		if (args.length != 4) {
			System.out.println("パラメータの数が違います。");
			System.exit(1);

		}
		
		try {

			// 接続の準備
			String url = "jdbc:derby:cardb;create=true";
			String usr = "";
			String pw = "";

			// データベースへの接続
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
			
			// 問い合わせの準備
			Statement st1 = cn.createStatement();
			qry1 = "INSERT INTO 車表 VALUES (" + args[0] + ", '" + args[1] + "'," + args[2] + ", '" + args[3] + "')";
			String qry2 = "SELECT * FROM 車表";

			// 問い合わせ
			st1.executeUpdate(qry1);
			ResultSet rs = st1.executeQuery(qry2);

			// データの取得
			ResultSetMetaData rm = rs.getMetaData();
			int cnum = rm.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= cnum; i++) {
					System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + "  ");

				}
				System.out.println("");

			}

			// 接続のクローズ
			rs.close();
			st1.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(qry1);
			e.printStackTrace();

		}

	}

}
