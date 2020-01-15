package sample3;

import java.net.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Sample3 extends Application {
    private Label lb1, lb2, lb3,lb4;
    private TextField tf1, tf2, tf3, tf4;
    private Button bt;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        try {
            InetAddress ia = InetAddress.getLocalHost();

            // コントロールの作成
            lb1 = new Label("入力してください。");
            lb2 = new Label("ホスト名");
            lb3 = new Label("IPアドレス");
            lb4 = new Label("クラス");
            tf1 = new TextField();
            tf2 = new TextField();
            tf3 = new TextField();
            tf4 = new TextField();
            bt = new Button("検索");

            // ペインの作成
            BorderPane bp = new BorderPane();
            VBox vb = new VBox();

            // ペインへの追加
            vb.getChildren().add(lb1);
            vb.getChildren().add(tf1);
            vb.getChildren().add(lb2);
            vb.getChildren().add(tf2);
            vb.getChildren().add(lb3);
            vb.getChildren().add(tf3);
            vb.getChildren().add(lb4);
            vb.getChildren().add(tf4);

            bp.setCenter(vb);
            bp.setBottom(bt);

            // イベントハンドラの登録
            bt.setOnAction(new SampleEventHandler());

            // シーンの作成
            Scene sc = new Scene(bp, 300, 200);

            // ステージへの追加
            stage.setScene(sc);

            // ステージの表示
            stage.setTitle("サンプル");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // イベントハンドラクラス
    class SampleEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                InetAddress ia = InetAddress.getByName(tf1.getText());
                tf2.setText(ia.getHostName());
                tf3.setText(ia.getHostAddress());
                String classStr = "";
                byte[] address = ia.getAddress();
                //グローバルかプライベートか
                if(ia.isSiteLocalAddress()){
                    //プライベートなら
                    /*
                    10.0.0.0-10.255.255.255
                    172.16.0.0-172.31.255.255
                    192.168.0.0-192.168.255.255
                    */
                    if(10 == Byte.toUnsignedInt(address[0])){
                        classStr = "クラスA";
                    }
                    else if(172 == Byte.toUnsignedInt(address[0])){
                        classStr = "クラスB";
                    }
                    else if(192 == Byte.toUnsignedInt(address[0])){
                        classStr = "クラスC";
                    }
                }else{
                    //グローバルなら
                    if(1 <= Byte.toUnsignedInt(address[0]) && Byte.toUnsignedInt(address[0]) <= 126){
                        classStr = "クラスA";
                    }else if(128 <= Byte.toUnsignedInt(address[0]) && Byte.toUnsignedInt(address[0]) <= 191){
                        classStr = "クラスB";
                    }else if(192 <= Byte.toUnsignedInt(address[0]) && Byte.toUnsignedInt(address[0]) <= 223){
                        classStr = "クラスC";
                    }
                }
                if(ia.isLoopbackAddress()){
                    classStr = "";
                }
                tf4.setText(classStr);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}