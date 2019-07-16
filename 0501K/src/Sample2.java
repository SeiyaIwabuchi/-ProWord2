import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*; 
import javafx.collections.*; 
import javafx.beans.value.*; 
public class Sample2 extends Application {    
	private Label lb;    
	private ListView<String> lv1,lv2;    
	private String price = null;    
	private String item = null;
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage stage)throws Exception{
		//コントロールの作成       
		lb = new Label("いらっしゃいませ。");       
		lv1 = new ListView<String>();       
		lv2 = new ListView<String>();              
		//コントロールの設定      
		ObservableList<String> ol1 = FXCollections.observableArrayList(" 10000円～  30000円"," 31000円～100000円","110000円～300000円");
		ObservableList<String> ol2 = FXCollections.observableArrayList("トランペット", "ホルン", "ドラム");       
		lv1.setItems(ol1);       
		lv2.setItems(ol2); 

		//ペインの作成       
		FlowPane fp = new FlowPane(); 

		//ペインへの追加       
		fp.getChildren().add(lb);        
		fp.getChildren().add(lv1);        
		fp.getChildren().add(lv2); 

		//イベントハンドラの登録      
		lv1.getSelectionModel().selectedItemProperty().addListener(new SampleChangeListener());
		lv2.getSelectionModel().selectedItemProperty().addListener(new SampleChangeListener2());              
		//シーンの作成      
		Scene sc = new Scene(fp, 800, 420);              
		//ステージへの追加       
		stage.setScene(sc); 

		//ステージの表示       
		stage.setTitle("サンプル");       
		stage.show();

	}
		//イベントハンドラクラス    
		class SampleChangeListener implements ChangeListener<String>{
			public void changed(ObservableValue ob, String bs, String as){
				System.out.println(bs);
				price = as;
				if(item == null){
					lb.setText("商品を選択してください。");
				}else {
					lb.setText(price + "の" + item + "ですね。");
				}
			}
		}    //イベントハンドラクラス    
		class SampleChangeListener2 implements ChangeListener<String>{
			public void changed(ObservableValue ob, String bs, String as){
				System.out.println(bs);
				item = as;
				if(price == null){
					lb.setText(item + "ですね。");
				}else { 
					lb.setText(price + "の" + item + "ですね。");
				}
			}
		}
}