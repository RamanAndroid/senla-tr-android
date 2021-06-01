import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lesson04 {

    public static int sumRandomNumbers(int firstNumber, int secondNumber, int thirdNumber) {
        return splitNumber(firstNumber) + splitNumber(secondNumber) + splitNumber(thirdNumber);
    }

    public static int splitNumber(int number) {
        return number / 100;
    }

    public static String bindingString(ArrayList<String> words) {
        StringBuilder str = new StringBuilder();
        for (String word : words) {
            String firstPart;
            String secondPart;
            firstPart = word.substring(0, 1);
            secondPart = word.substring(1);
            String sentence = firstPart.toLowerCase() + secondPart.toUpperCase();
            str.append(sentence.replaceAll("\\s+", ""));
        }
        return str.toString();
    }

    public static void sortByWordRepeat(ArrayList<String> words) {
        HashMap<String, Integer> uniqueWords = new HashMap<>();

        for (String word : words) {
            if (uniqueWords.containsKey(word)) {
                Integer wordCount = uniqueWords.get(word);
                uniqueWords.put(word, ++wordCount);
            } else {
                uniqueWords.put(word, 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> wordEntries = new ArrayList<>(uniqueWords.entrySet());
        for (int i = 0; i < wordEntries.size() - 1; i++) {
            for (int j = i; j < wordEntries.size(); j++) {
                Map.Entry<String, Integer> firstEntry = wordEntries.get(i);
                Map.Entry<String, Integer> secondEntry = wordEntries.get(j);
                if (firstEntry.getValue() >= secondEntry.getValue()) {
                    wordEntries.set(i, secondEntry);
                    wordEntries.set(j, firstEntry);
                }
            }
        }

        for (Map.Entry<String, Integer> wordEntry : wordEntries) {
            System.out.println("word: " + wordEntry.getKey() + " count :" + wordEntry.getValue().toString());

        }
    }
}
