// Pxxxxxx_26.java
// xxxxxx ���� 
import java.  io  .*;
class Pxxxxxx_26 {
	public static void main(String[] args) throws IOException {
		String str, str2;
		int n[] = new int[4];
		int index1 = 0, index2 = 0;  

		System.out.print("IP address = ");
		BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
		str = kbd. readLine (); 

		for(int i=0; i < 4; i++) {
			index2 = str.indexOf (".", index1);  // �s���I�h�̈ʒu�𒲂ׂ�
			if(index2 == -1)
				index2 = str.length ();  // ������Ȃ��Ƃ��͍Ō�܂łƂ��� 
			n[i] = Integer. parseInt  (str.substring(index1, index2)); // �؂�o��10�i���ɕϊ�
			str2 = "00000000" + Integer.toBinaryString(n[i]);       // �擪�Ƀ[����t��
			System.out.println("��"+(i+1)+"�I�N�e�b�g : " +str2.substring(str2.length()-8, str2.length()));   // ��납��W�����؂�o��
			index1 = index2 + 1;  // ����10�i���̐擪�ʒu         } 
		}

		if(n[0] < 128)
			System.out.println("�N���X�`�ł�");     // ��P�I�N�e�b�g�̍��� 0
		else if(n[0] < 192)
			System.out.println("�N���X�a�ł�");     // ��P�I�N�e�b�g�̍��� 10
		else if(n[0] < 224)
			System.out.println("�N���X�b�ł�");     // ��P�I�N�e�b�g�̍���110
	}
} 