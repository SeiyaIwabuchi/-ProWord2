import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.image.*;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

public class Sample6 extends Application {
	private Label lb = new Label("いらっしゃいませ。");
	private Button bt[] = new Button[5];
	private ToolBar tb = new ToolBar();
	private Image im = new Image(getClass().getResourceAsStream("car.jpg"));

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new Button();
			bt[i].setGraphic(new ImageView(im));
		}

		for (int i = 0; i < bt.length; i++) {
			bt[i].setTooltip(new Tooltip((i + 1) + "号車"));
		}

		int sep = 2;
		int add = 0;
		for (int i = 0; i < bt.length + add; i++) {
			Node nd = bt[i - add];
			if (i == sep) {
				nd = new Separator();
				add++;
			}
			tb.getItems().add(nd);
		}

		BorderPane bp = new BorderPane();

		bp.setTop(tb);
		bp.setCenter(lb);

		Queue<Integer> oldData = new ArrayDeque<>();
		for (int i = 0; i < 3; i++)
			oldData.add(-1);
		for (Button tbt : bt) {
			tbt.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					int num;
					for (num = 0; num < bt.length; num++) {
						if (event.getSource() == bt[num]) {
							break;
						}
					}
					if (!oldData.contains(num)) {
						oldData.poll();
						oldData.add(num);
					}
					ArrayList<Integer> al = new ArrayList<>();
					al.add(oldData.poll());
					al.add(oldData.poll());
					al.add(oldData.poll());
					oldData.add(al.get(0));
					oldData.add(al.get(1));
					oldData.add(al.get(2));
					al.sort(new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							// TODO Auto-generated method stub
							if (o1 > o2)
								return 1;
							else if (o1 < o2)
								return -1;
							else
								return 0;
						}
					});
					lb.setText((al.get(0) != -1 ? al.get(0) + "と" : "") + (al.get(1) != -1 ? al.get(1) + "と" : "")
							+ (al.get(2) != -1 ? al.get(2) : "") + "号車ですね。");
					al.clear();
				}
			});
		}

		Scene sc = new Scene(bp, 300, 200);
		stage.setScene(sc);
		stage.setTitle("サンプル");
		stage.show();
	}
}
