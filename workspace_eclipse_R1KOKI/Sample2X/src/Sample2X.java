import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample2X extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //フォームデータの取得
         String tmp = request.getParameter("paras");
         String carname = new String(tmp.getBytes("8859_1"), "JISAutoDetect");

         //コンテンツタイプの設定
         response.setContentType("text/html; charset=Shift_JIS");

         //HTML文書の書き出し
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>\n" + carname + "</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>\n" +  carname + "</h2>\n" +
                    carname + "のお買い上げありがとうございました。<br/>\n" +
                    "</center></body>\n" +
                    "</html>\n");
       }
       catch(Exception e){
          e.printStackTrace();
       }
   } 
}
