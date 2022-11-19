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
    private final ArrayList<String> result = new ArrayList<>();

    public void readDocument(String path) throws IOException {
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
            ArrayList<String> temporary = new ArrayList<>();
            temporary.add(value);
            vertex.put(key, temporary);
        }
        if (!vertex.containsKey(value)) {
            ArrayList<String> temporary = new ArrayList<>();
            vertex.put(value, temporary);
        }
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
        result.add(root);
        saveResult();
    }

    private String getRootedElement() {

        HashMap<String, Boolean> visited = new HashMap<>();
        for (String v : vertex.keySet()) {
            visited.put(v, Boolean.FALSE);
        }

        String root = "";

        for (String vert : vertex.keySet()) {
            if (!visited.get(vert)) {
                recursiveFun(vert, visited);
                root = vert;
            }
        }

        for (String vert : vertex.keySet()) {
            visited.put(vert, Boolean.FALSE);
        }

        recursiveFun(root, visited);
        for (String str : visited.keySet()) {
            if (!visited.get(str)) {
                return null;
            }
        }
        result.clear();

        return root;
    }


    private void recursiveFun(String v, HashMap<String, Boolean> visited) {
        visited.put(v, Boolean.TRUE);
        for (String x : vertex.get(v)) {
            if (!visited.get(x)) {
                recursiveFun(x, visited);
                result.add(x);
            }
        }
    }

    private void saveResult() throws IOException {
        StringBuffer result = new StringBuffer();
        for (String str : this.result) {
            result.append(str).append("\n");
        }
        Files.write(Paths.get("C:\\Users\\Administrator\\dsa-lab-3\\src\\result\\res.txt"),
                Collections.singleton(result));
        this.result.clear();
    }

}

