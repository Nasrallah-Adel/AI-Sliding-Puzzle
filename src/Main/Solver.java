/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nasrallah
 */
public class Solver {

    static Node start = new Node();
    static Node goal = new Node();
    static Node parent = new Node();
    static List<Node> all_childs = new ArrayList<Node>();
    static List<Node> solve_track = new ArrayList<Node>();
    static int g = 1;
    static int[] dr = new int[]{0, -1, 0, 1}; // E,N,W,S
    static int[] dc = new int[]{1, 0, -1, 0}; // R,U,L,D

    static Node get_min() {
        int min = 999999999;
        Node mn = new Node();
        for (Node e : all_childs) {
            //System.out.println("min loop : " + e + " cost " + e.cost);

            if (e.cost < min) {
                min = e.cost;
                mn = e;
            }
        }
        //System.out.println("small cost is : " + mn.cost);
        return mn;

    }

    static boolean is_goal(Node n) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (n.n[i][j] != goal.n[i][j]) {
                    return false;
                }

            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(n.n[i][j] + " ");

            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("congratulation");
        return true;
    }

    static boolean is_exist(Node n) {
        int flag = 0;
        for (Node e : all_childs) {
            flag = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (e.n[i][j] == n.n[i][j]) {
                        flag++;
                    }

                }

            }
            if (flag == 9) {
                return true;
            }
        }
        return false;
    }

    public static boolean opend(Node n) {

        return n.opend;
    }

    static void swap(Node n, int i, int j, int zi, int zj) {
        int[][] m = new int[3][3];
        for (int s = 0; s < 3; s++) {
            for (int q = 0; q < 3; q++) {
                m[s][q] = n.n[s][q];

            }

        }
        int temp = m[i][j];
        m[i][j] = m[zi][zj];
        m[zi][zj] = temp;
        Node child = new Node();
        child.n = m;
        child.cost = set_h(m) + g;
        child.parent = n;
        if (!is_exist(child)) {
            all_childs.add(child);
        }

    }

    public static void get_child(Node n) {
        if (n.opend) {
            all_childs.remove(n);
            return;
        }
        n.opend = true;
//        List<Node> child= new ArrayList<Node>();
        // Node m = n;

        parent = n;
        int zi = -1, zj = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (n.n[i][j] == 0) {
                    zi = i;
                    zj = j;
                    break;
                }

            }
        }

        for (int i = 0; i < 4; i++) {
            int new_i = zi + dr[i];
            int new_j = zj + dc[i];
            if (valid(new_i, new_j)) {
                swap(n, new_i, new_j, zi, zj);
            }
        }

        g++;
        // again(get_min());
//        return child;
    }

    static boolean valid(int r, int c) {
        return 0 <= r && r < 3 && 0 <= c && c < 3;
    }

    static int set_h(int[][] n) {
        int sum = 0;
        for (int s = 0; s < 3; s++) {
            for (int q = 0; q < 3; q++) {

                for (int i = 0, a = 1; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (a == n[s][q]) {
                            //  System.out.println(n[s][q] + "[" + s + " . " + q + "]  : " + Math.abs((s - i) + (q - j))+" a= "+a+ "[" + i + " . " + j + "]  : " );
                            sum += Math.abs((s - i)) + Math.abs((q - j));

                        }
                        a++;
                    }

                }
            }

        }

        return (sum + g);
    }

    static void set_goal() {
        for (int i = 0, a = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goal.n[i][j] = a++;

            }

        }
        goal.n[2][2] = 0;
    }

    public static void main(String[] args) {
        set_goal();
        get_child(start);
        Node k = null;
        while (!is_goal(get_min())) {

            get_child(get_min());

        }
//        for (Node c : all_childs) {
//            System.out.println("node : " + c);
//
//            System.out.println("cost " + c.cost);
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    System.out.print(c.n[i][j] + " ");
//
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//            System.out.println("");
//        }
    }

}
