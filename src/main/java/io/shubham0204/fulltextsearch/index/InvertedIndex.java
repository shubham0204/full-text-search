package io.shubham0204.fulltextsearch.index;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class InvertedIndex implements Serializable {

    private final Map<String, HashSet<Integer>> index = new HashMap<>();
    private IndexInformation indexInformation;

    public static InvertedIndex load(String dirpath) throws IOException, ClassNotFoundException {
        File file = new File(Paths.get(dirpath, ".FST_INDEX").toString());
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        InvertedIndex invertedIndex = (InvertedIndex) objectInputStream.readObject();
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
        indexInformation = new IndexInformation(index.size(), documents.length, new Date());
    }

    public Integer[] query(String query) {
        List<String> tokens = Utils.tokenize(query) ;
        Set<Integer> docIndices = new HashSet<>() ;
        for (String token : tokens) {
            if( index.containsKey( token.toLowerCase( ) ) ) {
                HashSet<Integer> indices = index.get(token.toLowerCase().strip());
                if( docIndices.isEmpty() ) {
                    docIndices = indices ;
                }
                docIndices.retainAll(indices);
            }
        }
        return Arrays
                .stream(docIndices.toArray(new Integer[0]))
                .sorted()
                .toArray(Integer[]::new);
    }

    public void save(String filepath) throws IOException {
        File file = new File(filepath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public IndexInformation getInformation() {
        return indexInformation;
    }

    public record IndexInformation(
            int numTokens,
            int numDocuments,
            Date creationDate
    ) implements Serializable {
        @Override
        public String toString() {
            return "Number of tokens: " + numTokens + "\n" +
                    "Number of documents: " + numDocuments + "\n" +
                    "Creation Date: " + creationDate.toString();
        }
    }

}
