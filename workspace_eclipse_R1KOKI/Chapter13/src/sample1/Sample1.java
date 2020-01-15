package sample1;

/*
四目並べ
グリッドレイアウトを使う
選択はボタンで行う。
ボタンは仕様より8x8の２次元配列で管理する。
クリックされた位置の取得はループ処理でボタン配列の要素一つ一つで取得しe.getSource()を使って比較。行番号列番号を取得する。
選択後はそのセルに〇または●のラベルを配置する。
先手後手それぞれで選択可能な要素を検索しそこにボタンを配置する。
手番が変わったら、前の番手で表示されたボタンはラベルに置き換える。
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
    private boolean player = false; //先手後手番どちらか
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        //コントロールの作成
        //一番下(Rows-1)以外はすべてblankを配置する。
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                //番号付きボタンの作成
                if (j == Rows-1){
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
    public int[] getButtonIndex(Button clickedButton){
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                //番号付きボタンの作成
                if (clickedButton == controls[i][j]){
                    int index[] = {i,j};
                    return index;
                }
            }
        }
        return new int[1];
    }

    public void setNextButton(int i,int j){
        for (int i_ = i-1; i_ <= i+1; i_++) {
            for (int j_ = j-1; j_ <=j+1 ; j_++) {
                if(i_!=i && j_!=j){
                    if(controls[i_][j_] instanceof Label){
                        Label tmplb2 = (Label)controls[i_][j_];
                        if(tmplb2.getText() != "   ●   " && tmplb2.getText() != "   〇   "){
                            controls[i_][j_] = new Button(Integer.toString(i) + Integer.toString(j));
                        }
                    }
                }
            }
        }
    }
    public void removeButton(){
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                if(j != Rows-1){
                    if(controls[i][j] instanceof Button){
                        controls[i][j] = new Label("   " + Integer.toString(i) + Integer.toString(j) + "   ");
                        controls[i][j].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    }
                }
            }
        }
    }
    public void refresh(){
        GridPane pane = new GridPane();
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                if(controls[i][j] != null){
                    pane.add(controls[i][j], i, j);
                }
            }
        }
        Scene scene = new Scene(pane, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Sample");
        stage.show();
    }
    class cellClickedEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent arg) {
            Button clickedButton = (Button)arg.getSource();
            int[] contIndex = getButtonIndex(clickedButton);
            System.out.println(Integer.toString(contIndex[0]) + Integer.toString(contIndex[1]));
            String mark;
            if(player){
                mark = "   ●   ";
            }else{
                mark = "   〇   ";
            }
            player = !player;
            controls[contIndex[0]][contIndex[1]] = new Label(mark);
            controls[contIndex[0]][contIndex[1]].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            removeButton();
            for (int i = 0; i <controls.length ; i++) {
                for (int j = 0; j <controls[i].length ; j++) {
                    if(controls[i][j] != null && controls[i][j] instanceof Label){
                        Label tmplb = (Label)controls[i][j];
                        try{
                            if(tmplb.getText() == "   ●   " && player){
                                setNextButton(i, j);
                            }else if(tmplb.getText() == "   〇   " && !player){
                                setNextButton(i, j);
                            }
                        }catch(ArrayIndexOutOfBoundsException e){
                            
                        }
                    }
                }
            }
            refresh();
        }
        
    }
    public static void main(String[] args){
        launch(args);
    }
}