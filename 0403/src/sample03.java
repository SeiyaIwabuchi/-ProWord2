import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class sample03 extends Application{
	private Button[][] bt = new Button[4][3];
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		//�R���g���[���̍쐬�i�ԍ��t���{�^���̍쐬�j
		char a = 'A';
		for(int i=0;i<bt[0].length;i++) {
			for(int j=0;j<bt.length;j++) {
				bt[j][i] = new Button("" + Character.valueOf(a++));
			}
		}
		//GridPane�쐬
		GridPane gp = new GridPane();
		
		//gp�Ƀ{�^����ǉ�
		for(int i=0;i<bt.length;i++) {
			for(int j=0;j<bt[i].length;j++) {
				gp.add(bt[i][j], i*2, j*2);
			}
		}
		//�V�[���쐬
		Scene sc = new Scene(gp,300,200);
		
		//�X�e�[�W�ɃV�[����ݒ�
		stage.setScene(sc);
		
		//�X�e�[�W�̕\��
		stage.setTitle("�T���v��");
		stage.show();
	}
}
