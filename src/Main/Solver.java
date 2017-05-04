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
    static List<Node> all_childs = new ArrayList<Node>();
    static List<Node> solve_track = new ArrayList<Node>();

    static int level = 1;

    static Node get_min() {
        int min = 999999999;
        Node mn = new Node();
        for (Node e : all_childs) {
            if (e.cost < min) {

                min = e.cost;
                mn = e;
            }
            System.out.print("current cost node " + e.cost + "  min " + min + " \n");
        }
        return mn;

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

    static boolean is_goal(Node n) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (n.n[i][j] != goal.n[i][j]) {
                    return false;
                }

            }
        }
        return true;
    }

    public static void get_child(Node n) {
//        List<Node> child= new ArrayList<Node>();
        // Node m = n;
        int a[][] = n.n;

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

        if (zi + 1 <= 2) {
            replace(a, zi + 1, zj, zi, zj);

        }
        a = n.n;
        if (zi - 1 >= 0) {
            replace(a, zi - 1, zj, zi, zj);
        }
        a = n.n;
        if (zj + 1 <= 2) {
            replace(a, zi, zj + 1, zi, zj);

        }
        a = n.n;
        if (zj - 1 >= 0) {
            replace(a, zi, zj - 1, zi, zj);
        }

        for (int s = 0; s < 3; s++) {
            for (int q = 0; q < 3; q++) {
                System.out.print(n.n[s][q] + " ");

            }
            System.out.println("");

        }
        System.out.println("");
        level++;
//        return child;
    }

    static void replace(int[][] n, int i, int j, int zi, int zj) {
        int[][] m = new int[3][3];
        for (int s = 0; s < 3; s++) {
            for (int q = 0; q < 3; q++) {
                m[s][q] = n[s][q];

            }

        }
        int temp = m[i][j];
        m[i][j] = m[zi][zj];
        m[zi][zj] = temp;
//        for (int s = 0; s < 3; s++) {
//            for (int q = 0; q < 3; q++) {
//                System.out.print(m[s][q] + " ");
//
//            }
//            System.out.println("");
//
//        }
        int cost = set_cost(m);
        Node c = new Node();
        c.n = m;
        c.cost = cost;
        c.level = level;
        if (!is_exist(c)) {
            System.out.println("not exist");
            System.out.println(c.level);
            all_childs.add(c);
        } else {
            System.out.println("exist");
        }

        System.out.println("");

    }

    static int set_cost(int[][] n) {
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

        System.out.println("sum = " + sum);
        return sum;
    }
 static void set_goal() {
        for (int i = 0, a = 1; i < 3; i++, a++) {
            for (int j = 0; j < 3; j++) {
                goal.n[i][j] = a;

            }

        }
        goal.n[2][2] = 0;
    }

    static void set_random() {
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
    }
    public static void main(String[] args) {

        set_random();
        set_goal();
        int cost = set_cost(start.n);
        start.cost = cost;
        for (int s = 0; s < 3; s++) {
            for (int q = 0; q < 3; q++) {
                System.out.print(start.n[s][q] + " ");

            }
            System.out.println("");
        }

        System.out.println("");
        all_childs.add(start);
        get_child(start);

        // start.cost=set_cost(start.n);
        System.out.println(all_childs.size() + "  " + get_min().cost);

    }

   
}
