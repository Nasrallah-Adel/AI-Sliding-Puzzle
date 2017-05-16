/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author windows
 */
public class cell extends StackPane{

    private Rectangle r;
    private Text text;

    public cell ( int h,int w){
        r = new Rectangle(h, w);
        r.setFill(Color.BISQUE);
        r.setStroke(Color.AZURE);
        getChildren().add(r);
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public Text getText() {
        return text;
    }

    public void setText(int t) {
        this.text = new Text();
        this.text.setText(t+"");
        getChildren().add(this.text);
    }

}
