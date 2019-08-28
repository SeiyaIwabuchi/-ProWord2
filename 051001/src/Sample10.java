import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class Sample10 extends Application {
	private Canvas cv;
	private double[] xPoints;
	private double[] yPoints;
	private int nPoints = 9;

	double multi = 1.4; //�g��{��

	public static void main(String[] args)    {
		launch(args);

	}
	public void start(Stage stage)throws Exception    {

		xPoints = new double[nPoints];
		yPoints = new double[nPoints];

		//�R���g���[���̍쐬 
		cv = new Canvas(300*multi, 200*multi);

		//�R���g���[���̐ݒ� 
		GraphicsContext gc = cv.getGraphicsContext2D();


		xPoints[0] = 20;
		yPoints[0] = 20;
		xPoints[1] = 56.000000;
		yPoints[1] = 56.000000;
		xPoints[2] = 168.000000;
		yPoints[2] = 56.000000;
		xPoints[3] = 196.000000;
		yPoints[3] = 28.000000;
		xPoints[4] = 196.000000;
		yPoints[4] = 196.000000;
		xPoints[5] = 168.000000;
		yPoints[5] = 224.000000;
		xPoints[6] = 56.000000;
		yPoints[6] = 224.000000;
		xPoints[7] = 28.000000;
		yPoints[7] = 196.000000;
		xPoints[8] = 28.000000;
		yPoints[8] = 28.000000;
		
		/*for(int i=1;i<xPoints.length;i++) {
			xPoints[i] *= multi;
			yPoints[i] *= multi;
			System.out.println(String.format("xPoints[%d] = %f;", i,xPoints[i]));
			System.out.println(String.format("yPoints[%d] = %f;", i,yPoints[i]));
		}*/
		
		gc.setFill(Color.rgb(0, 0, 0, 1.0));
		gc.fillPolygon(xPoints, yPoints, nPoints);
		gc.setFill(Color.rgb(111, 111, 255, 1.0));

		gc.fillOval(56.000000,84.000000,28.000000,28.000000);
		//System.out.println(String.format("%f,%f,%f,%f",40*multi, 60*multi, 20*multi, 20*multi));
		gc.fillOval(140.000000,84.000000,28.000000,28.000000);
		//System.out.println(String.format("%f,%f,%f,%f",100*multi, 60*multi, 20*multi, 20*multi));
		//�y�C���̍쐬 
		gc.setFill(Color.rgb(196, 19, 255, 1.0));
		gc.fillRect(98.000000,184.000000,30,10); //��
		gc.setFill(Color.rgb(96, 169, 60, 1.0));
		gc.fillOval(98,150,30,10); //�@
		BorderPane bp = new BorderPane();



		//�y�C���ւ̒ǉ� 
		bp.setCenter(cv);

		//�V�[���̍쐬 
		Scene sc = new Scene(bp, 300, 200);

		//�X�e�[�W�ւ̒ǉ� 
		stage.setScene(sc);



		//�X�e�[�W�̕\�� 
		stage.setTitle("�T���v��");
		stage.show();

	}

}
