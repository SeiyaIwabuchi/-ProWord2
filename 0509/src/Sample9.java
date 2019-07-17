import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

class Share{
	static Canvas cv = new Canvas(300,200);
	static GraphicsContext gc = cv.getGraphicsContext2D();
	static CheckBox cb = new CheckBox("é©ìÆï`âÊ");
}

public class Sample9 extends Application{

	public static void main(String[] args) {
		launch();
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Th au = new Th();
		au.start();
		
		Button bt = new Button("çƒï`âÊ");
		HBox hb = new HBox();
		
		Common.rePaint();
		Share.cb.setSelected(false);
		
		bt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Share.gc.clearRect(0, 0, 300, 200);
				Common.rePaint();
			}
		});
		
		BorderPane bp = new BorderPane();
		
		hb.getChildren().addAll(Share.cb,bt);
		
		
		bp.setTop(Share.cv);
		bp.setBottom(hb);
		
		Scene sc = new Scene(bp,300,230);
		
		primaryStage.setScene(sc);
		
		primaryStage.setTitle("ÉTÉìÉvÉã");
		
		primaryStage.show();
		
	}
}

class Th extends Thread{
	public void run() {
		try {
			while(Share.cb.isSelected()) {
				Common.rePaint();
				Thread.sleep(new Long(3000));
			}
		}catch(Exception e) {
			System.out.println("Except");
		}
	}
}

class Common{
	static void rePaint() {
		for(int i=0;i<100;i++) {
			int r = (int)(Math.random() * 256);
			int g = (int)(Math.random() * 256);
			
			int b = (int)(Math.random() * 256);
			
			double x = Math.random() * 300;
			double y = Math.random() * 200;
			
			Share.gc.setFill(Color.rgb(r, g, b, 1.0));
			Share.gc.fillOval(x, y, 10, 10);
		}
	}
}