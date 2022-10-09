/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: dsa-lab-3
 * Package: Graph
 * Class: Graph
 */

package graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {

    private final HashMap<String, ArrayList<String>> vertex = new HashMap<>();
    private final ArrayList<String> res = new ArrayList<>();

    public void readDocuments(String path) throws IOException {
        File file = new File(path);
        Scanner dataScanner = new Scanner(file);

        while (dataScanner.hasNextLine()) {
            String[] data = dataScanner.nextLine().split(" ");
            addVertex(data[0], data[1]);
        }
    }

    private void addVertex(String key, String value) {
        if (vertex.containsKey(key)) {
            vertex.get(key).add(value);
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(value);
            vertex.put(key, temp);
        }
        if (!vertex.containsKey(value)) {
            ArrayList<String> temp = new ArrayList<>();
            vertex.put(value, temp);
        }
    }

    private void recursiveFun(String v, HashMap<String, Boolean> visited) {
        visited.put(v, Boolean.TRUE);
        for (String x : vertex.get(v)) {
            if (!visited.get(x)) {
                recursiveFun(x, visited);
                res.add(x);
            }
        }
    }

    private void safeRes() throws IOException {
        StringBuffer result = new StringBuffer();
        for (String str : res) {
            result.append(str).append("\n");
        }
        Files.write(Paths.get("C:\\Users\\Administrator\\dsa-lab-3\\src\\result\\res.txt"),
                Collections.singleton(result));
        res.clear();
    }

    public void getProperWay() throws IOException {
        HashMap<String, Boolean> visited = new HashMap<>();
        for (String v : vertex.keySet()) {
            visited.put(v, Boolean.FALSE);
        }
        String root = getRootedElement();
        if (root == null) {
            throw new RuntimeException();
        }
        recursiveFun(root, visited);
        res.add(root);
        safeRes();
    }

    private String getRootedElement() {

        HashMap<String, Boolean> visited = new HashMap<>();
        for (String v : vertex.keySet()) {
            visited.put(v, Boolean.FALSE);
        }

        String v = "";

        for (String vert : vertex.keySet()) {
            if (!visited.get(vert)) {
                recursiveFun(vert, visited);
                v = vert;
            }
        }

        for (String vert : vertex.keySet()) {
            visited.put(vert, Boolean.FALSE);
        }

        recursiveFun(v, visited);
        for (String str : visited.keySet()) {
            if (!visited.get(str)) {
                return null;
            }
        }
        res.clear();
        return v;
    }
}