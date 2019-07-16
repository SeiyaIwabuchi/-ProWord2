import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.beans.value.*;

public class Sample2 extends Application{

	private Label lb;
	private ListView<String> lv;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public void start(Stage stage) throws Exception{
		lb = new Label("いらっしゃいませ。");
		lv = new ListView<String>();
		
		ObservableList<String> ol =FXCollections.observableArrayList("乗用車","トラック","オープンカー","タクシー","スポーツカー","ミニカー","自転車","三輪車","バイク","飛行機","ヘリコプター","ロケット");
		
		lv.setItems(ol);
		
		BorderPane bp = new BorderPane();
		
		bp.setTop(lb);
		bp.setCenter(lv);
		
		lv.getSelectionModel().selectedItemProperty().addListener(new SampleChangeListener());
		
		Scene sc = new Scene(bp,300,200);
		
		stage.setScene(sc);
		
		stage.setTitle("サンプル");
		stage.show();
	}
	
	class SampleChangeListener implements ChangeListener<String>{
		public void changed(ObservableValue ob,String bs, String as) {
			lb.setText(as + "ですね。");
		}
	}
}
