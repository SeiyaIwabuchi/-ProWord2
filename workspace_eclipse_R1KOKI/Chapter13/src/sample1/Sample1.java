package sample1;

/*
四目並べ
グリッドレイアウトを使う
選択はボタンで行う。
ボタンは仕様より8x8の２次元配列で管理する。
クリックされた位置の取得はループ処理でボタン配列の要素一つ一つで取得しe.getSource()を使って比較。行番号列番号を取得する。
選択後はそのセルに〇または●のラベルを配置する。
選択可能なセルにのみボタンは配置する。
*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Sample1 extends Application {
    private int Rows=8,Columns=8; //行列の数
    private Control[][] controls = new Control[Rows][Columns]; //コントロールを格納しておく配列
    private Label blank = new Label("  "); //空白部分
    private boolean player = false; //甲乙どちらの番なのか
    @Override
    public void start(Stage primaryStage) throws Exception {
        //コントロールの作成
        //一番下(Rows-1)以外はすべてblankを配置する。
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                //番号付きボタンの作成
                if (j == Rows-1 || true){
                    Button tmp = new Button(Integer.toString(i) + Integer.toString(j));
                    tmp.setOnAction(new cellClickedEventHandler());
                    controls[i][j] = tmp;
                }else{
                    controls[i][j] = new Label("   " + Integer.toString(i) + Integer.toString(j) + "   ");
                }
                //色を交互に変えて格子を表現する
                /*if((i+j) % 2 == 0 && j != Rows-1){
                    controls[i][j].setBackground(new Background(new BackgroundFill( Color.rgb(199, 199, 199) , CornerRadii.EMPTY , Insets.EMPTY )));
                }else{
                    controls[i][j].setBackground(new Background(new BackgroundFill( Color.rgb(129, 129, 129) , CornerRadii.EMPTY , Insets.EMPTY )));
                }*/
                //境界線を表示する。
                controls[i][j].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
        //GridPaneの作成
        GridPane pane = new GridPane();
 
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                if(controls[i][j] != null){
                    pane.add(controls[i][j], i, j);
                }
            }
        }
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sample");
        primaryStage.show();
    }
    public int[][] getIndex(){
        return new int[1][1];
    }
    class cellClickedEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent arg) {
            Button clickedButton = (Button)arg.getSource();
            for (int i = 0; i <controls.length ; i++) {
                for (int j = 0; j <controls[i].length ; j++) {
                    //番号付きボタンの作成
                    if (clickedButton == controls[i][j]){
                        System.out.println(Integer.toString(i) + Integer.toString(j));
                        break;
                    }
                }
            }
        }
        
    }
    public static void main(String[] args){
        launch(args);
    }
}