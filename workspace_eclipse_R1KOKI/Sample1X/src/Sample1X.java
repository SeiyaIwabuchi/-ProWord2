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
         //�R���e���c�^�C�v�̐ݒ�
         response.setContentType("text/html; charset=Shift_JIS");

         //�����̎擾
         Date dt = new Date(); 
         
         //HTML�����̏����o��
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>�T���v��</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>�悤����</h2>" +
                    "<hr/>\n" +
                    "��" + dt + "�ł��B<br/>\n" +
                    "���I�т��������B<br/>\n" +
                    "<br/>\n" +
                    "<a href=\"../car1_2.html\">��p��</a><br/>\n" +
                    "<a href=\"../car2_2.html\">�g���b�N</a><br/>\n" +
                    "<a href=\"../car3_2.html\">�I�[�v���J�[</a><br/>\n" +
                    "</center></body>\n" +
                    "</html>\n");
       }
       catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
