package sample6;
import java.io.*;
import java.util.Observable;
import java.util.regex.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.event.*;

import javafx.collections.*;

import java.awt.Color;

public class Sample6 extends Application {
    private Label lb;
    private TextArea ta;
    private TextField tf;
    private Button bt1, bt2;
    private Matcher matcher;
    private TextFlow textFlow;
    private Text text;
    BorderPane bp = new BorderPane();
    HBox hb = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        // コントロールの作成
        lb = new Label("入力してください。");
        ta = new TextArea();
        tf = new TextField();
        bt1 = new Button("検索");
        bt2 = new Button("解除");
        // ペインの作成
        
        // ペインへの追加
        hb.getChildren().add(tf);
        hb.getChildren().add(bt1);
        hb.getChildren().add(bt2);
        bp.setTop(lb);
        bp.setCenter(ta);
        bp.setBottom(hb);
        

        // イベントハンドラの登録
        bt1.setOnAction(new SampleEventHandler());
        bt2.setOnAction(new SampleEventHandler());
        // シーンの作成
        Scene sc = new Scene(bp, 300, 200);

        // ステージへの追加
        stage.setScene(sc);
        // ステージの表示
        stage.setTitle("サンプル");
        stage.show();
    }

    // イベントハンドラクラス
    class SampleEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                if (e.getSource() == bt1) {
                    Pattern pn = Pattern.compile(tf.getText());
                    matcher = pn.matcher(ta.getText());
                    int start,end,pos = 0;
                    String sourceText = ta.getText();
                    textFlow = new TextFlow();
                    while (matcher.find()) {
                        start = matcher.start();
                        end = matcher.end();
                        if (start == 0) {
                            //頭に対象文字列があったとき
                            text = new Text(sourceText.substring(start, end));
                            text.setStyle("-fx-font-size: 20px;-fx-fill: crimson;");
                            textFlow.getChildren().add(text);
                            //コピーところまでカーソル移動（的な意味合い）
                            pos = end;
                        }else{
                            if (pos != start) {
                                //カーソルがスタートの位置ではないとき（今のカーソル位置から対象文字列が前にある時）
                                text = new Text(sourceText.substring(pos, start));
                                text.setStyle("-fx-font-size: 16px;");
                                textFlow.getChildren().add(text);
                                //ここまでで対象外のコピー
                            }
                            //ここから対象のコピー
                            text = new Text(sourceText.substring(start, end));
                            text.setStyle("-fx-font-size: 20px;-fx-fill: crimson;");
                            textFlow.getChildren().add(text);
                            pos = end;
                        }
                    }
                    //残ったテキストをコピー
                    if (pos < sourceText.length()) {
                        text = new Text(sourceText.substring(pos, sourceText.length()));
                        text.setStyle("-fx-font-size: 16px;");
                        textFlow.getChildren().add(text);
                    }
                    bp.setCenter(textFlow);
                } else if (e.getSource() == bt2) {
                    try{
                        /*
                        これだと編集した後に解除を押すと編集前に戻ってしまう。
                        String tmpstr = "";
                        for(Node t:textFlow.getChildren()){
                            Text t2 = (Text)t;
                            tmpstr += t2.getText();
                        }*/
                        ta.setText(ta.getText());
                    }catch(NullPointerException npe){
                        ta.setText("");
                    }
                    bp.setCenter(ta);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}