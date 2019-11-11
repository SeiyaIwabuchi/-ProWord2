import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;

public class Sample4X extends HttpServlet
{
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
   throws ServletException
   {
      try{
         //�Z�b�V�����̎擾
         HttpSession hs = request.getSession(true);	
         Integer cn = (Integer) hs.getAttribute("count");	
         Date dt = (Date) hs.getAttribute("date");	

         String str1, str2,aisatu;

         //�񐔂̐ݒ� 
         if(cn == null){
            cn = new Integer(1);
            dt = new Date();
            str1 = "�͂��߂Ă̂������ł��ˁB";
            str2 = "";
         }
         else{
            cn = new Integer(cn.intValue() + 1);
            dt = new Date();
            str1 = cn + "��ڂ̂������ł��ˁB";
            str2 = "�i�O��F" + dt + ")";
         }
         dt = new Date();
         //SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
         //Date date = sdFormat.parse("2019/10/30 00:00:00");
         //dt = date;
         if (dt.getHours() < 12) {
        	 aisatu = "���͂悤�������܂��B";
         }else {
        	 aisatu = "����ɂ��́B";
         }
         
         //�Z�b�V�����̐ݒ�
         hs.setAttribute("count", cn);
         hs.setAttribute("date", dt);

         //�R���e���c�^�C�v�̐ݒ�
         response.setContentType("text/html; charset=Shift_JIS");

         //HTML�����̏����o��
         PrintWriter pw = response.getWriter();
         pw.println("<html>\n" +
                    "<head><title>�T���v��</title></head>\n" +
                    "<body><center>\n" +
                    "<h2>�悤����</h2>" +
                    "<hr />\n" +
                    aisatu + str1 + "<br/>\n" +
                    str2 + "<br/>\n" +
                    "���I�т��������B<br/>\n" +
                    "<br/>\n" +
                    "<a href=\"../car1.html\">��p��</a><br/>\n" +
                    "<a href=\"../car2.html\">�g���b�N</a><br/>\n" +
                    "<a href=\"../car3.html\">�I�[�v���J�[</a><br/>\n" +
                    "</center></body>\n" +
                    "</html>\n");
        }
        catch(Exception e){    
          e.printStackTrace();
       }
   } 
}
