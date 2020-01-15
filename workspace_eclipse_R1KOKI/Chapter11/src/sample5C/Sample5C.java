package sample5C;

import java.io.*;
import java.net.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Sample5C extends Application implements Runnable

{
    public static final String HOST = "localhost";
    public static final int PORT = 10000;

    private TextField tf;
    private TextArea ta;
    private Button bt;

    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;
    private StringBuffer sb;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception

    {
        try {
            InetAddress ia = InetAddress.getLocalHost();

            // コンポーネントの作成
            tf = new TextField();
            ta = new TextArea();
            bt = new Button("送信");

            // ペインの作成
            BorderPane bp = new BorderPane();

            bp.setTop(tf);
            bp.setCenter(ta);
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
            Alert dialog = new Alert(AlertType.ERROR);
            dialog.setHeaderText(null);
            dialog.setContentText("ネットワーク接続を確認してください。");
            dialog.showAndWait();
            e.printStackTrace();
        }

        // 接続
        Thread th = new Thread(this);
        th.start();
    }

    public void run() {
        try {
            sc = new Socket(HOST, PORT);
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
            sb = new StringBuffer();

            while (true) {
                try {
                    String str = br.readLine();
                    sb.append(str + "\n");
                    ta.setText(sb.toString());
                } catch (Exception e) {
                    br.close();
                    pw.close();

                    sc.close();
                    break;
                }
            }
        } catch (Exception e) {
            Alert dialog = new Alert(AlertType.ERROR);
            dialog.setHeaderText(null);
            dialog.setContentText("サーバー通信に失敗しました。");
            dialog.showAndWait();
            e.printStackTrace();
        }
    }

    // イベントハンドラクラス
    class SampleEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                String str = tf.getText();
                sb.append(str + "\n");

                pw.println(str);
                pw.flush();
                tf.setText("");
            } catch (Exception ex) {
                Alert dialog = new Alert(AlertType.ERROR);
                dialog.setHeaderText(null);
                dialog.setContentText("送信中に問題が発生しました。");
                dialog.showAndWait();
                ex.printStackTrace();
            }
        }
    }
}