package lessons.three;

import java.util.*;

/**
 * Task 1
 */
public class WordProcessor {

    public static void main(String[] args) {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "lemon", "avocado", "grape", "melon", "leak", "orange", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "olive", "apple", "pineapple", "apricot",
                "peanut", "lemon", "pear", "pepper", "lemon", "avocado", "leak", "pineapple", "pumpkin", "potato"};

        Map<String, Integer> wordCounter = new HashMap<>();
        // Count words
        for (String word : words) {
            if (wordCounter.containsKey(word)) {
                wordCounter.put(word, wordCounter.get(word) + 1);
            } else {
                wordCounter.put(word, 1);
            }
        }

        System.out.println("Unique words:");
        for (String key: wordCounter.keySet()){
            System.out.println(key);
        }

        System.out.println();
        System.out.println("============");
        System.out.println("Words count:");
        for (Map.Entry<String, Integer> entry : wordCounter.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(" : ");
            System.out.print(entry.getValue());
            System.out.println();
        }


    }
}
