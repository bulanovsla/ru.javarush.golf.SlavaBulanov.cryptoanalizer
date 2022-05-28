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
            for (String s : dictSplit) {
                String replaced = s.replace("-","").toLowerCase();
                if (!replaced.isEmpty()) dictSplitLowerCase.add(replaced);
            }
            String[] split = tempResult.split("[\\p{Punct}\\s]+");
            for (String word : split) {
                for (String dictSuffix:dictSplitLowerCase) {
                    if (word.contains(dictSuffix)) {
                        if (matches.get(key) == null) {
                            matches.put(key, 1);
                        } else {
                            int newValue = matches.get(key) + 1;
                            matches.put(key, newValue);
                        }
                        break;
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