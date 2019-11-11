import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample1X extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //コンテンツタイプの設定
         response.setContentType("text/html; charset=Shift_JIS");

         //時刻の取得
         Date dt = new Date(); 
         
         //HTML文書の書き出し
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>サンプル</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>ようこそ</h2>" +
                    "<hr/>\n" +
                    "今" + dt + "です。<br/>\n" +
                    "お選びください。<br/>\n" +
                    "<br/>\n" +
                    "<a href=\"../car1_2.html\">乗用車</a><br/>\n" +
                    "<a href=\"../car2_2.html\">トラック</a><br/>\n" +
                    "<a href=\"../car3_2.html\">オープンカー</a><br/>\n" +
                    "</center></body>\n" +
                    "</html>\n");
       }
       catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
