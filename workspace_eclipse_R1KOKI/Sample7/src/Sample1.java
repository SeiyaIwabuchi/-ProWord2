
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample1 extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //フォームデータの取得
         String carname = request.getParameter("cars");

         //サーブレットコンテキストの取得
         ServletContext sc = getServletContext();

         //リクエストの転送
         if(carname.length() != 0){
             sc.getRequestDispatcher("/Sample5.jsp")
               .forward(request, response);
         }
         else{
            sc.getRequestDispatcher("/error.html")
              .forward(request, response);
         }
      }
      catch(Exception e){    
         e.printStackTrace();
      }
   } 
}
