import java.util.*;

public class BrutForce {
    public static String hack(String text, String dictionary) {
        String result = "";
        Map<Integer, Integer> matches = new HashMap<>();
        for (int key = 0; key < Alphabet.ALPHABET.size(); key++) {
            String tempResult = EncodingDecoding.decoding(text, key);
            List<String> dictSplit = new ArrayList<>
                    (Arrays.asList(dictionary.split("[\\p{Punct}\\s]+")));
            List<String> dictSplitLowerCase = new ArrayList<>();
            for (String s:dictSplit
                 ) {
                dictSplitLowerCase.add(s.toLowerCase());
            }
            String[] split = tempResult.split("[\\p{Punct}\\s]+");
            for (String word : split) {
                if (dictSplitLowerCase.contains(word)) {
                    if (matches.get(key) == null) {
                        matches.put(key, 1);
                    } else {
                        int newValue = matches.get(key) + 1;
                        matches.put(key, newValue);
                    }
                }
            }
            Integer currentMatches = matches.get(key);
            int max = 0;
            for (Integer value : matches.values()) {
                if (value > max) {
                    max = value;
                }
            }
            if (currentMatches != null && currentMatches >= max) {
                result = tempResult;
            }
        }
        return result;
    }
}