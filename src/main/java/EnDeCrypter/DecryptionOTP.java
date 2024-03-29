package EnDeCrypter;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class DecryptionOTP {

    /***
     * startDecryption method
     * @param fileInput the file to be decrypted
     * @param fileOutput the file where the decrypted text will go
     * @param key the decryption key
     * @throws Exception any exception
     */
    public static void startDecryption(String fileInput, String fileOutput, String key) throws Exception {
        // Opening streams
        FileReader fileReader = new FileReader(fileInput); // Input file
        FileWriter fileWriter = new FileWriter(fileOutput); // Output file
        FileInputStream keyStream = new FileInputStream(key); // EnDeCrypter.Decryption key file

        int c = fileReader.read(); // Getting the first character
        int shift; // Instancing the shift to be applied to each character

        // Looping on the input file
        while (c != -1) {
            if (c != 32) {
                shift = keyStream.read(); // Read the encryption shift
                fileWriter.write(decryptChar(c, shift)); // Write the decrypted character to the output file
                c = fileReader.read(); // Get the next character
            } else {
                c = fileReader.read();
            }
        }

        // Closing streams
        fileReader.close();
        fileWriter.close();
        keyStream.close();
    }

    public static String startFileDecryption(String inputFile, String outputFile, String keyPath) throws Exception {
        // Opening streams
        FileReader fileReader = new FileReader(inputFile); // Input file
        FileWriter fileWriter = new FileWriter(outputFile); // Output file
        FileInputStream key = new FileInputStream(keyPath); // EnDeCrypter.Encryption key file
        StringBuilder string = new StringBuilder();

        int c = fileReader.read(); // Getting the first character
        int shift; // Instancing the shift that will be made on the letters

        // Looping on the FileReader
        while (c != -1) {
            if (c != 32) {
                shift = key.read(); // Writing the number on the encryption key
                fileWriter.write(decryptChar(c, shift)); // Writing the encrypted character on the output file
                string.append((char)decryptChar(c, shift));
                c = fileReader.read(); // Getting the next character
            } else {
                c = fileReader.read();
            }
        }

        // Closing streams
        fileReader.close();
        fileWriter.close();
        key.close();
        return string.toString();
    }

    public static String startStringDecryption(String inputString, String keyPath) throws Exception{
        FileInputStream key = new FileInputStream(keyPath);
        char[] encryptedString = inputString.toCharArray();
        StringBuilder decryptedString = new StringBuilder();

        int shift; // Instancing the shift that will be made on the letters

        for (char c : encryptedString) {
            if(c != 32) {
                shift = key.read();
                decryptedString.append((char)decryptChar(c, shift));
            }
        }

        // Closing streams
        key.close();

        return decryptedString.toString();
    }

    /***
     * decryptChar method
     * @param c the character to be decrypted
     * @param shift the shift to be applied to the character
     * @return the decrypted character
     */
    public static int decryptChar(int c, int shift) {
        int index = -1; // The index of the character in the alphabet
        // If the character is uppercase
        if (Character.isUpperCase(c)) {
            c = Character.toLowerCase(c);
        }
        // Getting the alphabet with ASCII characters
        int[] alphabet = new int[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = 97 + i;
            if (alphabet[i] == c) {
                index = i;
            }
        }
        // If the character is not in the alphabet (e.g a number, a comma...)
        if (index == -1) {
            return c; // Return the character without decrypting it
        }

        while (index - shift < 0) {
            shift -= alphabet.length;
        }

        // Return the shifted/decrypted character
        //return alphabet[Math.abs(index - shift)%alphabet.length];
        return alphabet[index - shift];
    }
}