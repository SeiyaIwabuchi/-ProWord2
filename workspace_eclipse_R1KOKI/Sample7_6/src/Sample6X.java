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
         //�t�H�[���f�[�^�̎擾
         String zipcode = request.getParameter("zipcode");
         
         //Bean�̍쐬
         ZipBean zb = new ZipBean();
         zb.setZipcode(zipcode);
         try {
        	 System.out.println(zb.getAddress());
         }catch(Exception e) {
        	 System.out.println("error");
         }
      
         //���N�G�X�g�ɐݒ�
         request.setAttribute("zb", zb);

         //�T�[�u���b�g�R���e�L�X�g�̎擾
         ServletContext sc = getServletContext();

         //���N�G�X�g�̓]��
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


