package EnDeCrypter;

public class Caesar {

    public static String Encrypt(String input, int shift) {
        char[] plaintext = input.toCharArray();
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext) {
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
            }
            if (c > 96 && c < 123) {
                c -= 97;
                c = (char)((c + shift) % 26);
                c += 97;
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public static String Decrypt(String input, int shift) {
        char[] ciphertext = input.toCharArray();
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext) {
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
            }
            if (c > 96 && c < 123) {
                c -= 97;
                if (c - shift < 0) {
                    c = (char)((c - shift) + 26);
                } else {
                    c = (char)(c - shift);
                }
                c += 97;
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        System.out.println(Encrypt("abcdefghijklmnopqrstuvwxyz", 10));
        System.out.println(Decrypt(Encrypt("abcdefghijklmnopqrstuvwxyz", 10), 10));
    }

}
