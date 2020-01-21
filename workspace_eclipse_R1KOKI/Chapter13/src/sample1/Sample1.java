package sample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
-----------------------------------
対コンピュータ
人はボタンをクリック
コンピュータは
1,選択可能なマスの一覧を取得する。
    すべてのマスの中からButtonオブジェクトだけを抽出した配列を作る
2,乱数を発生させ適当に選択させる。
*/
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Sample1 extends Application {
    private int Rows=8,Columns=8; //行列の数
    private Control[][] controls = new Control[Rows][Columns]; //コントロールを格納しておく配列
    private Label blank = new Label("  "); //空白部分
    private boolean player = true; //先手か
    private Stage gameStage;
    private Label gameInfoLabel = new Label("〇の番です。");
    private cellClickedEventHandler comEventHandler = new cellClickedEventHandler();
    @Override
    public void start(Stage primaryStage) throws Exception {
        gameStage = primaryStage;
        showStartMenu();
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
        //BorderPaneの中にgridを入れる
        BorderPane bpane = new BorderPane();
        //gridPaneに要素を追加する。
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                if(controls[i][j] != null){
                    pane.add(controls[i][j], i, j);
                }
            }
        }
        bpane.setCenter(pane); //boederPaneにgridPaneを追加
        bpane.setBottom(gameInfoLabel);
        Scene scene = new Scene(bpane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sample");
    }
    public void showStartMenu(){
        //スタートメニューを表示する
        /*
        先手か後手かの選択（ラジオボタンで行う）
        スタートボタン()
        BorderPaneのCenterにGridPaneを配置する。bottomにスタートボタンを配置する。
        */
        Stage startMenuStage = new Stage();
        BorderPane startMenuBorderPane = new BorderPane();
        GridPane startMenuGridPane = new GridPane();
        ToggleGroup turnToggle = new ToggleGroup();
        RadioButton selectTrunRadioButtons[] = {new RadioButton("先手 "),new RadioButton("後手")};
        selectTrunRadioButtons[0].setSelected(true);
        selectTrunRadioButtons[0].setToggleGroup(turnToggle);
        selectTrunRadioButtons[1].setToggleGroup(turnToggle);
        Button startButton = new Button("スタート");
        Scene startMenuScene;
        startButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
                if(selectTrunRadioButtons[0].isSelected()){

                }else{
                    player = false;
                    doComputer();
                }
				gameStage.show();
			}
        });
        startMenuGridPane.add(new Label("手番 : "),0,0);
        startMenuGridPane.add(selectTrunRadioButtons[0],1,0);
        startMenuGridPane.add(selectTrunRadioButtons[1],2,0);
        startMenuBorderPane.setCenter(startMenuGridPane);
        BorderPane.setAlignment(startButton, Pos.CENTER);
        startMenuBorderPane.setBottom(startButton);
        startMenuScene = new Scene(startMenuBorderPane,300,250);
        startMenuStage.setScene(startMenuScene);
        startMenuStage.setTitle("スタートメニュー");
        startMenuStage.show();
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

    public void setNextButton(){
        for(int i_=0;i_<controls.length;i_++){ //列
            for(int j_=0;j_<controls.length;j_++){ //行
                if(j_ != Rows-1){ //最終行より上
                    if(controls[i_][j_+1] instanceof Label){ //一個下がLabelの時
                        Label tmplb = (Label)controls[i_][j_+1];
                        Label tmplb2 = (Label)controls[i_][j_];
                        if((tmplb.getText() == "   ●   " || tmplb.getText() == "   〇   ") && (tmplb2.getText() != "   ●   " && tmplb2.getText() != "   〇   ")){ //一個下のラベルの内容が〇または●のとき。
                            Button tmpbtn = new Button(Integer.toString(i_) + Integer.toString(j_));
                            tmpbtn.setOnAction(new cellClickedEventHandler());
                            controls[i_][j_] = tmpbtn;
                            
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
        BorderPane bpane = new BorderPane();
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                if(controls[i][j] != null){
                    pane.add(controls[i][j], i, j);
                }
            }
        }
        bpane.setCenter(pane);
        bpane.setBottom(gameInfoLabel);
        Scene scene = new Scene(bpane, 300, 250);
        gameStage.setScene(scene);
        gameStage.setTitle("Sample");
        gameStage.show();
    }
    int[] downDim(int[][] highDimArray,int row,int col,int direction){
        int lowDimArray[] = {-100,-100,-100,-100};
        for(int i=0;i<4;i++){
            if(direction == 0 && col+i < highDimArray[row].length){ //縦
                lowDimArray[i] = highDimArray[row][col+i];
            }else if(direction == 1 && row+i < highDimArray.length){ //横
                lowDimArray[i] = highDimArray[row+i][col];
            }else if(direction == 2 && row+i < highDimArray.length && col+i < highDimArray[row].length){ //右斜め
                lowDimArray[i] = highDimArray[row+i][col+i];
            }else if(direction == 3 && row+3 < highDimArray.length && col+i < highDimArray[row].length){ //左斜め
                lowDimArray[i] = highDimArray[row-i+3][col+i];
            }
            System.out.print(lowDimArray[i] + "\t");
        }
        System.out.println();
        return lowDimArray;
    }
    public String judge(){
        //いったん別の配列に置き換える。
        //-1,0,1だけの配列
        int[][] table = new int[controls[0].length][controls.length];
        int mark;
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                if(controls[i][j] instanceof Button){ //ボタンは未選択状態
                    table[j][i] = 0;
                }else if(controls[i][j] instanceof Label){
                    Label tmplb = (Label)controls[i][j];
                    if(tmplb.getText() == "   ●   "){
                        table[j][i] = 1;
                    }else if(tmplb.getText() == "   〇   "){
                        table[j][i] = -1;
                    }else{
                        table[j][i] = 0;
                    }
                }
                System.out.print(Integer.toString(table[i][j]) + "\t");
            }
            System.out.println();
        }
        if(player){
            mark = 1;
        }else{
            mark = -1;
        }
        for (int i = 0; i <controls.length ; i++) {
            for (int j = 0; j <controls[i].length ; j++) {
                /*
                 -3|@ - - @ - - @
                 -2|- @ - @ - @ -
                 -1|- - @ @ @ - -
                 0 |@ @ @ @ @ @ @
                 1 |- - @ @ @ - -
                 2 |- @ - @ - @ -
                 3 |@ - - @ - - @
                -----------------
                    -3-2-10 1 2 3
                */
                if(table[i][j] != mark){
                    continue;
                }else if(Math.abs(Arrays.stream(downDim(table, i, j, 0)).sum()) == 4){ //縦の判定
                    return judgeState.decided;
                }else if(Math.abs(Arrays.stream(downDim(table, i, j, 1)).sum()) == 4){ //横の判定
                    return judgeState.decided;
                }else if(Math.abs(Arrays.stream(downDim(table, i, j, 2)).sum()) == 4){ //右斜めの判定
                    return judgeState.decided;
                }else if(Math.abs(Arrays.stream(downDim(table, i, j, 3)).sum()) == 4){ //左斜めの判定
                    return judgeState.decided;
                }
            }
        }
        return judgeState.none;
    }
    void doComputer(){
        //選択可能なマスを選択する。
        List<Button> selectableButtons = new ArrayList<Button>();
        for(Control[] cont1 : controls){
            for(Control cont2: cont1){
                if(cont2 instanceof Button){
                    selectableButtons.add((Button)cont2);
                }
            }
        }
        Random random = new Random();
        comEventHandler.handle(selectableButtons.get(random.nextInt(selectableButtons.size())));
    }
    class cellClickedEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent arg) {
            Button clickedButton = (Button)arg.getSource();
            int[] contIndex = getButtonIndex(clickedButton);
            this.common(contIndex);
        }
        public void handle(Button bt){
            int[] contIndex = getButtonIndex(bt);
            this.common(contIndex);
        }
        public void common(int[] contIndex){
            System.out.println(Integer.toString(contIndex[0]) + Integer.toString(contIndex[1]));
            String mark;
            if(player){
                mark = "   ●   ";
            }else{
                mark = "   〇   ";
            }
            controls[contIndex[0]][contIndex[1]] = new Label(mark);
            controls[contIndex[0]][contIndex[1]].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            gameInfoLabel.setText((player?"〇":"●") + "の番です。");
            removeButton(); //一度全ボタン削除
            setNextButton(); //次に置ける場所にボタンを配置する。
            refresh(); //画面を再描画する。
            if(judge() == judgeState.decided){  //勝敗判定する。
                Alert dialog = new Alert(AlertType.INFORMATION);
                dialog.setHeaderText(null);
                dialog.setContentText(mark + "の勝ちです。");
                dialog.showAndWait();
                Platform.exit();
            }
            player = !player;
            if(!player){
                doComputer();
            }
        }
    }
    public static void main(String[] args){
        launch(args);
    }
}
class judgeState{
    public static String none = "none",decided="decided";
}