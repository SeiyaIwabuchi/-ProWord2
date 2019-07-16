import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.beans.value.*;

public class sample1 extends Application{
	private Label lb;
	private ComboBox<String> cb1,cb2;
	private ObservableList<String> ol;
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) throws Exception{
		//�R���g���[���̍쐬
		lb = new Label("");
		cb1 = new ComboBox<String>();
		cb2 = new ComboBox<String>();
		
		//�R���g���[���̐ݒ�
		ObservableList<String> ol = FXCollections.observableArrayList("","�g���b�N","�I�[�v���J�[","�^�N�V�[","�X�|�[�c�J�[","�~�j�J�[");
		cb1.setItems(ol);
		cb2.setItems(ol);
		
		//�y�C���̍쐬
		BorderPane bp = new BorderPane();
		
		//�y�C���ւ̒ǉ�
		bp.setTop(lb);
		bp.setLeft(cb1);
		bp.setRight(cb2);
		
		//�C�x���g�n���h���̓o�^
		cb1.setOnAction(new SampleEventHandler1());
		cb2.setOnAction(new SampleEventHandler2());
		
		//�V�[���̍쐬
		Scene sc = new Scene(bp,300,200);
		
		//�X�e�[�W�ւ̒ǉ�
		stage.setScene(sc);
		
		//�X�e�[�W�\��
		stage.setTitle("�T���v��");
		stage.show();
	}
	
	private String cb1Str = "",cb2Str = "";
	
	//�C�x���g�n���h���N���X�̍쐬
	class SampleEventHandler1 implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			ComboBox tmp = (ComboBox) e.getSource();
			String str = tmp.getValue().toString();
			cb1Str = str;
			setLabel();
		}
	}
	class SampleEventHandler2 implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			ComboBox tmp = (ComboBox) e.getSource();
			String str = tmp.getValue().toString();
			cb2Str = str;
			setLabel();
		}
	}
	
	private void setLabel() {
		if(cb1Str.equals("") && cb2Str.equals("")) {
			lb.setText("");
		}else if(cb1Str.equals("") && cb2Str.equals("")==false) {
			lb.setText(cb1Str + cb2Str + "�ł��ˁB");
		}else if(cb1Str.equals("")==false && cb2Str.equals("")==true) {
			lb.setText(cb1Str + cb2Str + "�ł��ˁB");
		}else if(cb1Str.equals("")==false && cb2Str.equals("")==false){
			lb.setText(cb1Str + "��" + cb2Str + "�ł��ˁB");
			//System.out.println(cb1Str);
			//System.out.println(cb2Str);
		}
	}
}
