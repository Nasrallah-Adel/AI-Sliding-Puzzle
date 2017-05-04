/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.List;

/**
 *
 * @author nasrallah
 */
public class Solver {
public List<Node> solve(){
    List <Node> childs;
    //position of zero
    
}
    public static void main(String[] args) {
        Node start = new Node();
        Node goal = new Node();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start.n[i][j] = (int) (Math.random() * 9);
                for (int a = 0; a <= i; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (b == j && a == i) {
                            break;
                        }
                        if (start.n[i][j] == start.n[a][b]) {
                            j = b;
                            i = a;
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0,a=1; i < 3; i++,a++) {
            for (int j = 0; j < 3; j++) {
               goal.n[i][j]=a;

            }
            System.out.println("");

        }
  goal.n[2][2]=0;
    }
}
