/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: dsa-lab-3
 * Package: main
 * Class: Main
 */

package main;


import graph.Graph;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Graph graph = new Graph();
        graph.readDocument("C:\\Users\\Administrator\\dsa-lab-3\\src\\examples\\1.txt");

        graph.getProperWay();
    }
}