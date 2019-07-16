import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*; 
import javafx.collections.*; 
import javafx.beans.value.*; 
public class Sample2 extends Application {    
	private Label lb;    
	private ListView<String> lv1,lv2;    
	private String price = null;    
	private String item = null;
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage stage)throws Exception{
		//�R���g���[���̍쐬       
		lb = new Label("��������Ⴂ�܂��B");       
		lv1 = new ListView<String>();       
		lv2 = new ListView<String>();              
		//�R���g���[���̐ݒ�      
		ObservableList<String> ol1 = FXCollections.observableArrayList(" 10000�~�`  30000�~"," 31000�~�`100000�~","110000�~�`300000�~");
		ObservableList<String> ol2 = FXCollections.observableArrayList("�g�����y�b�g", "�z����", "�h����");       
		lv1.setItems(ol1);       
		lv2.setItems(ol2); 

		//�y�C���̍쐬       
		FlowPane fp = new FlowPane(); 

		//�y�C���ւ̒ǉ�       
		fp.getChildren().add(lb);        
		fp.getChildren().add(lv1);        
		fp.getChildren().add(lv2); 

		//�C�x���g�n���h���̓o�^      
		lv1.getSelectionModel().selectedItemProperty().addListener(new SampleChangeListener());
		lv2.getSelectionModel().selectedItemProperty().addListener(new SampleChangeListener2());              
		//�V�[���̍쐬      
		Scene sc = new Scene(fp, 800, 420);              
		//�X�e�[�W�ւ̒ǉ�       
		stage.setScene(sc); 

		//�X�e�[�W�̕\��       
		stage.setTitle("�T���v��");       
		stage.show();

	}
		//�C�x���g�n���h���N���X    
		class SampleChangeListener implements ChangeListener<String>{
			public void changed(ObservableValue ob, String bs, String as){
				System.out.println(bs);
				price = as;
				if(item == null){
					lb.setText("���i��I�����Ă��������B");
				}else {
					lb.setText(price + "��" + item + "�ł��ˁB");
				}
			}
		}    //�C�x���g�n���h���N���X    
		class SampleChangeListener2 implements ChangeListener<String>{
			public void changed(ObservableValue ob, String bs, String as){
				System.out.println(bs);
				item = as;
				if(price == null){
					lb.setText(item + "�ł��ˁB");
				}else { 
					lb.setText(price + "��" + item + "�ł��ˁB");
				}
			}
		}
}