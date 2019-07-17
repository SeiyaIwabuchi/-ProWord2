import java.util.HashSet;
import java.util.Set;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.beans.value.*;

public class sample1 extends Application{
	private Label lb;
	private ComboBox<String> cb1,cb2;
	private ObservableList<String> ol;
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) throws Exception{
		//コントロールの作成
		lb = new Label("");
		cb1 = new ComboBox<String>();
		cb2 = new ComboBox<String>();
		
		//コントロールの設定
		Set<String> set = new HashSet<String>() {
			{
				add("");
				add("トラック");
				add("オープンカー");
				add("タクシー");
				add("スポーツカー");
				add("ミニカー");
			}
		};
		ObservableList<String> ol = FXCollections.observableArrayList(set);
		cb1.setItems(ol);
		cb2.setItems(ol);
		
		//ペインの作成
		BorderPane bp = new BorderPane();
		
		//ペインへの追加
		bp.setTop(lb);
		bp.setLeft(cb1);
		bp.setRight(cb2);
		
		//イベントハンドラの登録
		cb1.setOnAction(new SampleEventHandler1());
		cb2.setOnAction(new SampleEventHandler2());
		
		//シーンの作成
		Scene sc = new Scene(bp,300,200);
		
		//ステージへの追加
		stage.setScene(sc);
		
		//ステージ表示
		stage.setTitle("サンプル");
		stage.show();
	}
	
	private String cb1Str = "",cb2Str = "";
	
	//イベントハンドラクラスの作成
	class SampleEventHandler1 implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			ComboBox tmp = (ComboBox) e.getSource();
			String str = tmp.getValue().toString();
			cb1Str = str;
			setLabel();
		}
	}
	class SampleEventHandler2 implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			ComboBox tmp = (ComboBox) e.getSource();
			String str = tmp.getValue().toString();
			cb2Str = str;
			setLabel();
		}
	}
	
	private void setLabel() {
		if(cb1Str.equals("") && cb2Str.equals("")) {
			lb.setText("");
		}else if(cb1Str.equals("") && cb2Str.equals("")==false) {
			lb.setText(cb1Str + cb2Str + "ですね。");
		}else if(cb1Str.equals("")==false && cb2Str.equals("")==true) {
			lb.setText(cb1Str + cb2Str + "ですね。");
		}else if(cb1Str.equals("")==false && cb2Str.equals("")==false){
			lb.setText(cb1Str + "と" + cb2Str + "ですね。");
			//System.out.println(cb1Str);
			//System.out.println(cb2Str);
		}
	}
}
