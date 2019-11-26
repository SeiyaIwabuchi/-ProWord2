public class Sample4{
    public static void main(String[] args){
        for (String arg : args) System.out.println(arg);
        //----クエリ----
        String createTBqry = 
        "CREATE TABLE 学生表(" + 
        "クラス varchar(4)" + 
        "ＮＯ int" +
        "学生名 varchar(20)" + 
        "出身県 varchar(12)";
        //----接続準備----
        //----テーブルが存在するか確認しない場合は作成し、存在するなら何もしない----
        if(/* 存在を判定する */false){
            //ある場合はテーブル全体を出力する。
        }else{
            //ない場合は作成する。
        }
        //----クエリ実行準備----
        //----実行するクエリを組み立てる----
        //----実行する----
        //----実行結果を確認するためにテーブル全体を出力する----
    }
}