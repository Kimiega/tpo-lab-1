package com.example.tpolab1.task3;

import java.util.HashMap;
import java.util.Map;

public class GalaxyDict {
    private final Map<String, String> words;

    public GalaxyDict() {
        words = new HashMap<>();
    }

    public String getMeaning(String word) {
        return words.getOrDefault(word, null);
    }
    public void addWord(String word, String meaning) {
        words.put(word, meaning);
    }
}
