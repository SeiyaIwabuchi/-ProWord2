import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DataTypes{
    static String varchar(int n){
        return String.format("varchar(%d)", n);
    }
    static String Int(){
        return "int";
    }
    static String Float(){
        return "float";
    }
}

public class Sample4 {
    static String[] modeArgs = {};
    static String[] dataArgs = {};
    static String[] conditionArgs = {};
    
    public static void main(String[] args) {
        // ----クエリ----
        /*
         * 引数説明:[-m mode][-d data][-w conditions] -m : dでdelete,-iでinsert,-uでupdate 
         * -d :modeがdの時は(列名=データ)の繰り返し。区切りにスペースを使う。modeがiの時はデータの繰り返し。区切りにスペースを使う。modeがuの時は(列名=データ)の繰り返し。区切りにスペースを使う。 
         * -w : 条件が必要なmodeで指定する。必要のないときは無視される。
         */
        String[][] defineColumn = {
            {"クラス",DataTypes.varchar(4)},
            {"ＮＯ",DataTypes.Int()},
            {"学生名",DataTypes.varchar(20)},
            {"出身県",DataTypes.varchar(12)},
            {"テスト平均点",DataTypes.Float()}
        };
        String columnQrys = "";
        for (int idx=0;idx<defineColumn.length;idx++){
            columnQrys += defineColumn[idx][0] + " " + defineColumn[idx][1];
            if(idx != defineColumn.length-1) columnQrys += ",";
        }
        String createTBqry = String.format("CREATE TABLE 学生表(%s)",columnQrys);
        String insrtQry = "INSERT INTO %s VALUES(%s)";
        String updtQry = "UPDATE %s SET %s WHERE %s";
        String dltQry = "DELETE FROM %s WHERE %s";
        String execQry = "";
        // ----接続準備----
        String url = "jdbc:derby:cardb;create=true";
        String usr = "";
        String pw = "";
        // ----接続----
        try {
            Connection cn = DriverManager.getConnection(url, usr, pw);
            DatabaseMetaData dm = cn.getMetaData();
            ResultSet tb = dm.getTables(null, null, "学生表", null);
            Statement st = cn.createStatement();
            // ----テーブルが存在するか確認しない場合は作成し、存在するなら何もしない----
            if (/* 存在を判定する */tb.next()) {
                System.out.println("SELECT * FROM 学生表");
                ResultSet rs = st.executeQuery("SELECT * FROM 学生表");
                ResultSetMetaData rm = rs.getMetaData();
                int cnum = rm.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= cnum; i++) {
                        System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + "  ");
                    }
                    System.out.println("");
                }
                //現在のレコード数を表示する
                System.out.println("--------------------------------------------------");
                System.out.println("SELECT COUNT(*) \"レコード数\" FROM 学生表");
                rs = st.executeQuery("SELECT COUNT(*) \"レコード数\" FROM 学生表");
                rm = rs.getMetaData();
                cnum = rm.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= cnum; i++) {
                        System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + "  ");
                    }
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println(createTBqry);
                st.executeUpdate(createTBqry);
            }
            // ----クエリ実行準備----
            // ----実行するクエリを組み立てる----
            boolean argsAnalyzeResult = analyzeArgs(args);
            if (argsAnalyzeResult){
                System.out.println("mode:" + modeArgs[1]);
                switch(modeArgs[1]){
                    case "d":
                        String whereToken = "";
                        for (int idx=1;idx<conditionArgs.length;idx++){
                            whereToken += conditionArgs[idx];
                            if (idx != conditionArgs.length-1) whereToken += " AND ";
                        }
                        execQry = String.format(dltQry,"学生表",whereToken);
                        break;
                    case "i":
                        String insrtTok = "";
                        for (int idx=1;idx<dataArgs.length;idx++){
                            //int型、double型にパースできないなら文字列としてシングルで囲う
                            try{
                                Integer.parseInt(dataArgs[idx]);
                            }catch(NumberFormatException nfe){
                                try{
                                    Double.parseDouble(dataArgs[idx]);
                                }catch(NumberFormatException nfe2){
                                    dataArgs[idx] = "'" + dataArgs[idx] + "'";
                                }
                            }
                            insrtTok += dataArgs[idx];
                            if (idx != dataArgs.length-1) insrtTok += ",";
                        }
                        execQry = String.format(insrtQry,"学生表",insrtTok);
                        break;
                    case "u":
                        String dataTok = "";
                        String whereToken2 = "";
                        for (int idx=1;idx<conditionArgs.length;idx++){
                            whereToken2 = conditionArgs[idx];
                            if (idx != conditionArgs.length-1) whereToken2 += " AND ";
                        }
                        for (int idx=1;idx<dataArgs.length;idx++){
                            dataTok = dataArgs[idx];
                            if (idx != dataArgs.length-1) dataTok += ",";
                        }
                        execQry = String.format(updtQry,"学生表",dataTok,whereToken2);
                        break;
                    default:
                        System.out.println("error");
                }
            }
            // ----実行する----
            if(argsAnalyzeResult){
                //引数に問題がなければクエリを実行する
                System.out.println("実行したSQL:" + execQry);
                st.executeUpdate(execQry);
            }else{
                //引数に問題があれば実行せずに説明を出力する。
                System.out.println("引数説明:[-m mode][-d data][-w conditions] -m : dでdelete,-iでinsert,-uでupdate ");
                System.out.println("-d :modeがdの時は(列名=データ)の繰り返し。区切りにスペースを使う。modeがiの時はデータの繰り返し。区切りにスペースを使う。modeがuの時は(列名=データ)の繰り返し。区切りにスペースを使う。");
                System.out.println("-w : 条件が必要なmodeで指定する。必要のないときは無視される。");
                return;
            }
            // ----実行結果を確認するためにテーブル全体を出力する----
            System.out.println("SELECT * FROM 学生表");
            ResultSet rs = st.executeQuery("SELECT * FROM 学生表");
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= cnum; i++) {
                    System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + "  ");
                }
                System.out.println("");
            }
            System.out.println("--------------------------------------------------");
            
            //クローズ
            rs.close();
            st.close();
	        cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static boolean analyzeArgs(String[] args){
        String swichChar = "";
        boolean ret = true;
        for (String tok : args){
            //System.out.println("tok:" + tok);
            //System.out.println("swichChar:" + swichChar);
            switch(tok){
                case "-m":
                    swichChar = "m";
                    break;
                case "-d":
                    swichChar = "d";
                    break;
                case "-w":
                    swichChar = "c";
                    break;
                default:
                    if (tok.charAt(0) == '-'){
                        return false;
                    }
                    break;
            }
            switch(swichChar){
                case "m":
                    String[] t = modeArgs;
                    modeArgs = new String[modeArgs.length+1];
                    if(t.length > 0) System.arraycopy(t, 0, modeArgs, 0, t.length);
                    modeArgs[modeArgs.length-1] = tok;
                    break;
                case "d":
                    t = dataArgs;
                    dataArgs = new String[dataArgs.length+1];
                    if(t.length > 0) System.arraycopy(t, 0, dataArgs, 0, t.length);
                    dataArgs[dataArgs.length-1] = tok;
                    break;
                case "c":
                    t = conditionArgs;
                    conditionArgs = new String[conditionArgs.length+1];
                    if(t.length > 0) System.arraycopy(t, 0, conditionArgs, 0, t.length);
                    conditionArgs[conditionArgs.length-1] = tok;
                    break;
            }
        }
        if(modeArgs.length < 2) ret = false;
        return ret;
    }
}