import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class sample03 extends Application{
	private Button[][] bt = new Button[4][3];
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		//コントロールの作成（番号付きボタンの作成）
		char a = 'A';
		for(int i=0;i<bt[0].length;i++) {
			for(int j=0;j<bt.length;j++) {
				bt[j][i] = new Button("" + Character.valueOf(a++));
			}
		}
		//GridPane作成
		GridPane gp = new GridPane();
		
		//gpにボタンを追加
		for(int i=0;i<bt.length;i++) {
			for(int j=0;j<bt[i].length;j++) {
				gp.add(bt[i][j], i*2, j*2);
			}
		}
		//シーン作成
		Scene sc = new Scene(gp,300,200);
		
		//ステージにシーンを設定
		stage.setScene(sc);
		
		//ステージの表示
		stage.setTitle("サンプル");
		stage.show();
	}
}
