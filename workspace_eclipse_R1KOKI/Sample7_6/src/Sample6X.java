import mybeans.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sample6X extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //フォームデータの取得
         String zipcode = request.getParameter("zipcode");
         
         //Beanの作成
         ZipBean zb = new ZipBean();
         zb.setZipcode(zipcode);
         try {
        	 System.out.println(zb.getAddress());
         }catch(Exception e) {
        	 System.out.println("error");
         }
      
         //リクエストに設定
         request.setAttribute("zb", zb);

         //サーブレットコンテキストの取得
         ServletContext sc = getServletContext();

         //リクエストの転送
         if(zipcode.length() != 0){
            sc.getRequestDispatcher("/Sample6X.jsp")
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


