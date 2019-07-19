import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Sample8 extends Application {

	private Label lb;
	private Button bt;

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage stage)throws Exception    {

		//コンポーネントの作成 
		lb = new Label();
		bt = new Button();

		//コンポーネントの設定 
		lb.setText("いらっしゃいませ。");
		bt.setText("購入");



		//ペインの作成 
		BorderPane bp = new BorderPane();



		//ペインへの追加 
		bp.setTop(lb);


		bp.setCenter(bt);

		//イベントハンドラの登録 
		bt.setOnAction(new SampleEventHandler());



		//シーンの作成 
		Scene sc = new Scene(bp, 300, 200);



		//ステージへの追加 
		stage.setScene(sc);



		//ステージの表示 
		stage.setTitle("サンプル");
		stage.show();

	}

	//イベントハンドラクラス 
	class SampleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e)       {
			Alert al1 = new Alert(Alert.AlertType.CONFIRMATION);

			al1.setTitle("確認");
			al1.getDialogPane().setHeaderText("本当に購入しますか？");
			Optional<ButtonType> res = al1.showAndWait();


			if(res.get() == ButtonType.OK){
				Alert al2 = new Alert(Alert.AlertType.CONFIRMATION);
				al2.setTitle("再確認");
				al2.getDialogPane().setHeaderText("本当に本当に購入しますか？");
				Optional<ButtonType> res2 = al2.showAndWait();
				if(res2.get() == ButtonType.OK){
					Alert al3 = new Alert(Alert.AlertType.INFORMATION);
					al3.setTitle("購入");
					al3.getDialogPane().setHeaderText("ご購入ありがとうございました。");
					al3.show();

				}

			}

		}

	}

}
