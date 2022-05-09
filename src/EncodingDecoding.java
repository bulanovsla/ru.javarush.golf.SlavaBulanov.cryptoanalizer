import java.util.List;

public class EncodingDecoding {

    public static String encrypt(String text, int key) {
        text = text.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < text.length(); i++) {
            int charPosition = Alphabet.ALPHABET.indexOf(text.charAt(i));
            if (charPosition < 0) {
                continue;
            }
            int keyVal = (key + charPosition) % Alphabet.ALPHABET.size();
            char replaceVal = Alphabet.ALPHABET.get(keyVal);
            cipherText += replaceVal;
        }
        return cipherText;
    }

    public static String decoding(String cipherText, int key) {
        cipherText = cipherText.toLowerCase();
        String message = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int charPosition = Alphabet.ALPHABET.indexOf(cipherText.charAt(i));
            if (charPosition < 0) {
                continue;
            }
            int keyVal = (charPosition - key) % Alphabet.ALPHABET.size();
            if (keyVal < 0) {
                keyVal = Alphabet.ALPHABET.size() + keyVal;
            }
            char replaceVal = Alphabet.ALPHABET.get(keyVal);
            message += replaceVal;
        }
        return message;
    }
}

