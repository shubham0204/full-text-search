package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.index.InvertedIndex;
import io.shubham0204.fulltextsearch.index.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestIndex {

    @Test()
    @DisplayName("test-preprocess-text")
    void testPreprocessText() {
        String src = "Hello World. The world has 7 continents. The price of the burger is $7. Today's date is 3/4/2024";
        List<String> tokens = Utils.preprocessDocument(src);
        System.out.println(tokens.toString());
        assertEquals(
                Arrays.asList("hello", "world", "the", "world", "has", "continents", "the", "price", "of", "the", "burger", "is", "todays", "date", "is"),
                tokens);
    }

    @Test
    @DisplayName("test-inverted-index")
    void testInvertedIndex() {
        String[] docs = {
                "Hello World",
                "Hello World from Java",
                "Java is a JIT compiled language",
                "The world could be a better place!"
        };
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.build(docs);

        Integer[] indices = invertedIndex.query("hello");
        assertArrayEquals(
                new Integer[]{0, 1},
                indices
        );
    }

}
