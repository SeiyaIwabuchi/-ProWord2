import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class Sample1 extends Application{
	private Button[] bt = new Button[5];
	
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) throws Exception{
		bt[0] = new Button("TopRight");
		bt[1] = new Button("ButtomLeft");
		bt[2] = new Button("Center");
		bt[3] = new Button("LeftTop");
		bt[4] = new Button("BottomRight");
		
		BorderPane bp = new BorderPane();
		/*
		BorderPaneインスタンス.setTop,setCenter,setBottom,setLeft,setright
			コントロールをボーダーペインのどこの領域に設置するか設定する
		*/
		bp.setTop(bt[0]);
		bp.setRight(bt[1]);
		bp.setCenter(bt[2]);
		bp.setLeft(bt[3]);
		bp.setBottom(bt[4]);
		
		/*
		 * 更に設置する領域のどの部分に置くかを決める
		 */
		BorderPane.setAlignment(bt[0], Pos.CENTER_RIGHT);
		BorderPane.setAlignment(bt[1], Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(bt[2], Pos.CENTER);
		BorderPane.setAlignment(bt[3], Pos.TOP_CENTER);
		BorderPane.setAlignment(bt[4], Pos.CENTER_RIGHT);

		Scene sc = new Scene(bp, 300, 200);
		
		stage.setScene(sc);
		
		stage.setTitle("Sample1");
		stage.show();
		
	}
}