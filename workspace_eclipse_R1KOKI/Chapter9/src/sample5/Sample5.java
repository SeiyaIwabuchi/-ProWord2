package sample5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Sample5 extends Application {
    private Label lb1, lb2, lb3;
    private TextArea ta;
    private TextField tf1, tf2;
    private Button btReplace,btUndo; // ボタンの追加
    private List<String> historyList = new ArrayList<String>();

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage stage) throws Exception {
        // コントロールの作成
        lb1 = new Label("入力してください。");
        lb2 = new Label("置換前");
        lb3 = new Label("置換後");
        ta = new TextArea();
        btReplace = new Button("置換");
        btUndo = new Button("戻す");
        // ボタンの追加
        tf1 = new TextField();
        tf2 = new TextField();
        // ペインの作成
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        // ペインへの追加
        hb.getChildren().add(lb2);
        hb.getChildren().add(tf1);
        hb.getChildren().add(lb3);

        hb.getChildren().add(tf2);
        hb.getChildren().add(btReplace);
        hb.getChildren().add(btUndo);
        // ボタンの追加
        bp.setTop(lb1);
        bp.setCenter(ta);
        bp.setBottom(hb);
        // イベントハンドラの登録
        btReplace.setOnAction(new SampleEventHandler());
        btUndo.setOnAction(new UndoButtonEventHandler());
        // イベントハンドラの追加
        // シーンの作成
        Scene sc = new Scene(bp, 500, 200);
        // ステージへの追加
        stage.setScene(sc);
        // ステージの表示
        stage.setTitle("サンプル");
        stage.show();
    }

    // イベントハンドラクラス
    class SampleEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Pattern pn = Pattern.compile(tf1.getText());
            Matcher mt = pn.matcher(ta.getText());
            historyList.add(ta.getText());
            ta.setText(mt.replaceAll(tf2.getText()));
        }
    }
    // イベントハンドラの追加
    class UndoButtonEventHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            try{
                ta.setText(historyList.get(historyList.size()-1));
                historyList.remove(historyList.size()-1);
            }catch(ArrayIndexOutOfBoundsException excep){
                
            }
        }
    }
}