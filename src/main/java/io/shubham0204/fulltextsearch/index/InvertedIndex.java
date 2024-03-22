package io.shubham0204.fulltextsearch.index;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class InvertedIndex {

    private Map<String, HashSet<Integer>> index = new HashMap<>();

    public static InvertedIndex load(String dirpath) throws IOException, ClassNotFoundException {
        InvertedIndex invertedIndex = new InvertedIndex();
        File file = new File(Paths.get(dirpath, ".FST_INDEX").toString());
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        invertedIndex.index = (Map<String, HashSet<Integer>>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return invertedIndex;
    }

    public void build(String[] documents) {
        for (int i = 0; i < documents.length; i++) {
            List<String> tokens = Utils.preprocessDocument(documents[i]);
            for (String token : tokens) {
                index.computeIfAbsent(token, k -> new HashSet<>());
                index.get(token).add(i);
            }
        }
        System.out.println("Num Tokens: " + index.keySet().size());
    }

    public Integer[] query(String token) {
        Set<Integer> docIndices = index.get(token.toLowerCase().strip());
        return Arrays.stream(docIndices.toArray(new Integer[0]))
                .sorted()
                .toArray(Integer[]::new);
    }

    public void save(String filepath) throws IOException {
        File file = new File(filepath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(index);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    static class IndexInformation {

        private int numTokens;
        private Date creationDate;
        private int numDocuments;


    }

}
