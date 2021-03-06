import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Sample7 extends Application{
	private Label lb = new Label("いらっしゃいませ。");
	private Button bt = new Button("購入");
	private Boolean bl = true;
	public static void main(String[] args) {
		launch();
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane bp = new BorderPane();
		
		bp.setTop(lb);
		bp.setCenter(bt);
		
		Scene sc = new Scene(bp,300,200);
		
		bt.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(bl) {
					Alert al = new Alert(Alert.AlertType.INFORMATION);
					al.setTitle("購入");
					al.getDialogPane().setHeaderText("ご購入ありがとうございました。");
					al.show();
					bl = !bl;
				}else {
					Alert al = new Alert(Alert.AlertType.WARNING);
					al.setTitle("在庫なし");
					al.getDialogPane().setHeaderText("申し訳ありませんが品切れです。");
					al.show();
					bl = !bl;
				}
			}
		});
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}
}