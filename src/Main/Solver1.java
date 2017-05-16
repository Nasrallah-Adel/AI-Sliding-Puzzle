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
public class Solver1 {

    static Node start = new Node();
    static Node goal = new Node();
    static Node parent = new Node();
    static Node current = new Node();
    static List<Node> openList = new ArrayList<Node>();
    static List<Node> closedlist = new ArrayList<Node>();
    static int g = 0;
    static int[] dr = new int[]{0, -1, 0, 1}; // E,N,W,S
    static int[] dc = new int[]{1, 0, -1, 0}; // R,U,L,D

    static String A_star() {
        start.f = set_h(start.n);
        openList.add(start);
        
        while (openList.size() != 0) {
            System.out.println(g);
            System.out.println("openList.size() = "+openList.size());
            System.out.println("closedlist.size() = "+closedlist.size());
            current = get_min();
            if (is_goal(current)) {
                print_path(current);
                return "congrat";
            }
//              for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    System.out.print(current.n[i][j] + " ");
//
//                }
//                System.out.println("");
//              }
            openList.remove(current);
            
            closedlist.add(current);
            get_child(current);
        }

        return "fail";
    }

    static Node get_min() {
        int min = 999999999;
        Node mn = new Node();
        for (Node e : openList) {

            if (e.f < min) {
                min = e.f;
                mn = e;
            }
        }

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
        for (Node e : openList) {
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
        flag=0;
        for (Node e : closedlist) {
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
        child.cost = set_h(m);
        child.parent = n;
        child.f = set_h(child.n);
        if (!is_exist(child)) {
            openList.add(child);
        }

    }

    public static void get_child(Node n) {

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

        return (sum +g);
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
        System.out.println(A_star());
    }

    private static void print_path(Node current) {
        while (current.parent != null) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(current.n[i][j] + " ");

                }
                System.out.println("");

            }
            current = current.parent;
            System.out.println("");
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(current.n[i][j] + " ");

            }
            System.out.println("");

        }
    }

}
