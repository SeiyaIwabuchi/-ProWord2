import javax.servlet.*;
import javax.servlet.http.*;

public class Sample5X extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //�t�H�[���f�[�^�̎擾
         String carname = request.getParameter("apples");

         //�T�[�u���b�g�R���e�L�X�g�̎擾
         ServletContext sc = getServletContext();

         //���N�G�X�g�̓]��
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
