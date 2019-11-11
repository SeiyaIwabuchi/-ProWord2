import javax.servlet.*;
import javax.servlet.http.*;

public class Sample5X extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //フォームデータの取得
         String carname = request.getParameter("apples");

         //サーブレットコンテキストの取得
         ServletContext sc = getServletContext();

         //リクエストの転送
         if(carname.length() != 0){
            sc.getRequestDispatcher("/thanksX.html")
              .forward(request, response);
         }
         else{
            sc.getRequestDispatcher("/errorX.html")
              .forward(request, response);
         }
      }
      catch(Exception e){    
         e.printStackTrace();
      }
   } 
}
