import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.beans.value.*; 
import javafx.beans.property.*;
public class sample3 extends Application{
	private Label lb;
	private TableView<RowData> tv;
	private Button dlBt;
	ObservableList<RowData> ol;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception{
		lb = new Label("いらっしゃいませ。");
		tv = new TableView<RowData>();
		dlBt = new Button();
		dlBt.setText("選択行を削除");
		dlBt.setOnAction(new dlBtHandler());
		
		TableColumn<RowData, String> tc1 = new TableColumn<RowData, String>("車名"); 
		TableColumn<RowData, String> tc2 = new TableColumn<RowData, String>("価格"); 
		TableColumn<RowData, String> tc3 = new TableColumn<RowData, String>("月日"); 
		TableColumn<RowData, String> tc4 = new TableColumn<RowData, String>("運転手");
		
		tc1.setCellValueFactory(new PropertyValueFactory<RowData, String>("name"));
		tc2.setCellValueFactory(new PropertyValueFactory<RowData, String>("price"));
		tc3.setCellValueFactory(new PropertyValueFactory<RowData, String>("date"));
		tc4.setCellValueFactory(new PropertyValueFactory<RowData, String>("driver"));
		
		ol = FXCollections.observableArrayList();
		ol.add(new RowData("乗用車",		1200,"10-01","川名"));
		ol.add(new RowData("トラック",		2400,"10-05","青木"));
		ol.add(new RowData("オープンカー",	1200,"10-06","阿部"));
		ol.add(new RowData("タクシー",		1200,"10-10","高橋"));
		ol.add(new RowData("スポーツカー",	1200,"10-11","千葉"));
		ol.add(new RowData("ミニカー",		1200,"10-12","佐々木"));
		ol.add(new RowData("自転車",		1200,"10-15","石井"));
		ol.add(new RowData("三輪車",		1200,"10-18","中谷"));
		ol.add(new RowData("飛行機",		1200,"10-19","種市"));
		ol.add(new RowData("乗用車",		1200,"10-01","藤田"));
		ol.add(new RowData("ヘリコプター",	1200,"10-21","富樫"));
		
		tv.getColumns().add(tc1); 
		tv.getColumns().add(tc2); 
		tv.getColumns().add(tc3); 
		tv.getColumns().add(tc4); 
		
		tv.setItems(ol);
		
		tv.getSelectionModel().selectedItemProperty().addListener(new lbUpdate());
		
		//tv.addEventHandler(MouseEvent.MOUSE_CLICKED, new lbUpdate()); 
		/*tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				event.getTarget()
			}
		});*/
		
		BorderPane bp = new BorderPane();
		
		 bp.setTop(lb); 
		 bp.setCenter(tv);
		 bp.setBottom(dlBt);
		 
		 bp.setAlignment(dlBt, Pos.CENTER_RIGHT);
		 
		 Scene sc = new Scene(bp, 300, 200);
		 
		 stage.setScene(sc);
		 
		 stage.setTitle("サンプル");
		 stage.show();
		 
	}
	
	public class lbUpdate implements ChangeListener<RowData>{
		@Override
		public void changed(ObservableValue<? extends RowData> observable, RowData oldValue, RowData newValue) {
			// TODO Auto-generated method stub
			String lbStr = "選択中：";
			try {
				lbStr += newValue.name.get() + ", ";
				lbStr += newValue.price.get() + ", ";
				lbStr += newValue.date.get() + ", ";
				lbStr += newValue.driver.get();
			}catch(NullPointerException nulp) {
				lbStr = "データがありません。";
			}finally {
				lb.setText(lbStr);
			}
		}
	}
	public class RowData{
		 private final SimpleStringProperty name;
		 private final SimpleIntegerProperty price;
		 private final SimpleStringProperty date;
		 private final SimpleStringProperty driver;
		 
		 public RowData(String n,int p, String d, String r) {
			 this.name = new SimpleStringProperty(n);
			 this.price = new SimpleIntegerProperty(p);
			 this.date = new SimpleStringProperty(d);
			 this.driver = new SimpleStringProperty(r);
		 }
		 
		 public StringProperty nameProperty() {return name;}
		 public IntegerProperty priceProperty() {return price;}
		 public StringProperty dateProperty() {return date;}
		 public StringProperty driverProperty() {return driver;}
	 }
	public class dlBtHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			ol.remove(tv.getSelectionModel().getSelectedItem());
			if(ol.size() < 1) {
				dlBt.setDisable(true);
			}
		}
		
	}
}