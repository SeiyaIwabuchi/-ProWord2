import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;

import javafx.event.*;

public class sample5 extends Application {
	private Label lb;
	private MenuBar mb;
	private Menu[] mn = new Menu[7];
	private MenuItem[] mi = new MenuItem[15];

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception { // コントロールの作成 
		lb = new Label("いらっしゃいませ。");
		//MenuBarオブジェクトの生成
		mb = new MenuBar();
		// コントロールの設定
		//コンストラクタの引数に文字列を与えると、ラベルとなる。？？
		//メニューを作る
		mn[0] = new Menu("メイン1");
		mn[1] = new Menu("メイン2");
		mn[2] = new Menu("サブ1");
		mn[3] = new Menu("サブ2");
		mn[4] = new Menu("メイン3");
		mn[5] = new Menu("サブ1");
		mn[6] = new Menu("サブ2");
		//メニューアイテムを作る。これが末端に来る
		mi[0] = new MenuItem("乗用車");
		mi[1] = new MenuItem("トラック");
		mi[2] = new MenuItem("オープンカー");
		mi[3] = new MenuItem("タクシー");
		mi[4] = new MenuItem("スポーツカー");
		mi[5] = new MenuItem("ミニカー");
		
		mi[6] = new SeparatorMenuItem();
		mi[7] = new MenuItem("パトカー");
		mi[8] = new MenuItem("消防車");
		mi[9] = new MenuItem("自転車");
		
		mi[10] = new SeparatorMenuItem();
		mi[11] = new MenuItem("ベビーカー");
		mi[12] = new SeparatorMenuItem();
		mi[13] = new MenuItem("三輪車");
		mi[14] = new SeparatorMenuItem();
		
		//メニューン下にメニューを置くときは先にしたのメニューを作る必要がある。下を作ったら上を作る。
		//メイン１にぶら下がる項目の追加
		mn[0].getItems().addAll(mi[0], mi[1]);
		//メイン２のサブ１にぶら下がる項目の追加
		mn[2].getItems().addAll(mi[2], mi[3]);
		//メイン２のサブ２にぶら下がる項目の追加
		mn[3].getItems().addAll(mi[4], mi[5]);
		//メイン２にぶら下がる項目の追加
		mn[1].getItems().addAll(mn[2],mi[6]/*セパレータ*/, mn[3]);
		//メイン３のサブ１にぶら下がる項目を追加する
		mn[5].getItems().addAll(mi[7], mi[8]);
		//メイン３のサブ2にぶら下がる項目を追加する
		mn[6].getItems().addAll(mi[9], mi[10],mi[11], mi[12],mi[13]);
		//メイン３にぶら下がる項目を追加する
		mn[4].getItems().addAll(mn[5],mi[14], mn[6]);
		//メニューバーに追加する
		mb.getMenus().addAll(mn[0], mn[1], mn[4]);

		// ペインの作成 
		BorderPane bp = new BorderPane();

		// ペインへの追加
		bp.setTop(mb);
		bp.setCenter(lb);

		// イベントハンドラの登録
		for (int i = 0; i < mi.length; i++) {
			mi[i].setOnAction(new SampleEventHandler());
		}

		// シーンの作成
		Scene sc = new Scene(bp, 300, 200);

		// ステージへの追加
		stage.setScene(sc);

		// ステージの表示
		stage.setTitle("サンプル");
		stage.show();
	}

	// イベントハンドラクラス
	class SampleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			MenuItem tmp = (MenuItem) e.getSource();//e.getSource()でイベントが発生したときのオブジェクトが返ってくる
			String str = tmp.getText();
			lb.setText(str + "ですね。");
		}
	}
}
