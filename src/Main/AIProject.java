
package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class  AIProject extends Application {
    cell[][] cells;

    @Override
    public void start(Stage primaryStage) throws Exception {
        cells = new cell[3][3];
        GridPane grid = new GridPane();
        

        for(int i=0 ,a=1; i<3 ; i++){
            for(int j =0 ;j<3 ; j++){
                cells[i][j] = new cell(100,100);
                grid.add(cells[i][j], j, i);
                if (i==2&&j==2) {
                    break;
                }
                cells[i][j].setText(a++);
                
            }
        }
        cells[2][2].setText(0);
        HBox hb =new HBox();
        Label l =new Label("moves:");
         l.setFont(Font.font("shimaa", FontWeight.BOLD, 18));
        hb.getChildren().add(l);
        TextField tf = new TextField();
        tf.setScaleX(1);
        tf.setScaleY(1);
       hb.getChildren().add(tf); 
        BorderPane bp = new BorderPane();
        bp.setTop(hb);
        bp.setCenter(grid);
        Scene scene = new Scene(bp, 300, 340);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}