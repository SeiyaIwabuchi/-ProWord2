import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class sample5 extends Application{
	private Label[] lb = new Label[3];
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception{
		
		for(int i=0;i<lb.length;i++) {
			lb[i] = new Label("ŽÔ" + Integer.toString(i) + "‚Í‚¢‚©‚ª‚Å‚·‚©?");
		}
		
		lb[0].setTextFill(Color.BLACK);
		lb[1].setTextFill(Color.BLUE);
		lb[2].setTextFill(Color.RED);
		
		BorderPane bp = new BorderPane();
		VBox bv = new VBox();
		
		bp.setTop(bv);
		for(int i=0;i<lb.length;i++) {
			bv.getChildren().add(lb[i]);
		}
		
		
		Scene sc = new Scene(bp,300,200);
		
		stage.setScene(sc);
		
		stage.setTitle("ƒTƒ“ƒvƒ‹");
		
		stage.show();
	}
}
