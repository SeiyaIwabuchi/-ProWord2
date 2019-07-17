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
	
	final private int oldNum = 5;
	final private int buttonNum = 10;
	
	private Label lb = new Label("いらっしゃいませ。");
	private Button bt[] = new Button[buttonNum]; 
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

		int sep = 5;
		int add = 0;
		for (int i = 0; i < bt.length + add; i++) {
			Node nd = bt[i - add];
			if ((i - add) % sep == 0 && i != 0) {
				nd = new Separator();
				add++;
			}
			tb.getItems().add(nd);
		}

		BorderPane bp = new BorderPane();

		bp.setTop(tb);
		bp.setCenter(lb);

		Queue<Integer> oldData = new ArrayDeque<>();
		for (int i = 0; i < oldNum; i++)
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
					/*al.add(oldData.poll());
					al.add(oldData.poll());
					al.add(oldData.poll());*/
					for(int i=0;i<oldNum;i++) al.add(oldData.poll());
					for(int i=0;i<oldNum;i++) {
						oldData.add(al.get(i));
					}
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
					//lb.setText((al.get(0) != -1 ? al.get(0) + "と" : "") + (al.get(1) != -1 ? al.get(1) + "と" : "")
					//		+ (al.get(2) != -1 ? al.get(2) : "") + "号車ですね。");
					String lbText = "";
					int iyu = 0;
					for(iyu=0;iyu<oldNum-1;iyu++) {
						lbText += (al.get(iyu) != -1 ? al.get(iyu) + "と" : "");
					}
					lbText += (al.get(iyu) != -1 ? al.get(iyu) : "") + "号車ですね。";
					lb.setText(lbText);
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
