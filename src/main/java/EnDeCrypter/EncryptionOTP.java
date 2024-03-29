package EnDeCrypter;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class EncryptionOTP {

    /***
     * startEncryption method
     * @param inputFile the text file that will be encrypted
     * @param outputFile the text file where the encrypted text will go
     * @param keyPath the path to the decryption key that will be generated
     * @throws Exception any exception
     */
    public static String startFileEncryption(String inputFile, String outputFile, String keyPath) throws Exception {

        // Opening streams
        FileReader fileReader = new FileReader(inputFile); // Input file
        FileWriter fileWriter = new FileWriter(outputFile); // Output file
        FileOutputStream key = new FileOutputStream(keyPath); // EnDeCrypter.Encryption key file
        StringBuilder string = new StringBuilder();

        int c = fileReader.read(); // Getting the first character
        int shift; // Instancing the shift that will be made on the letters

        // Looping on the FileReader
        while (c != -1) {
            if (c != 32) {
                shift = (int)(26 * Math.random()); // Getting a random number between 0-26
                key.write(shift); // Writing the number on the encryption key
                fileWriter.write(encryptChar(c, shift)); // Writing the encrypted character on the output file
                string.append((char)encryptChar(c, shift));
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

    public static String startStringEncrption(String inputString, String keyPath) throws Exception{
        FileOutputStream key = new FileOutputStream(keyPath);
        char[] plaintext = inputString.toCharArray();
        StringBuilder encryptedString = new StringBuilder();

        int shift; // Instancing the shift that will be made on the letters

        for (char c : plaintext) {
            if (c != 32) {
                shift = (int)(26*Math.random());
                key.write(shift);
                encryptedString.append((char)encryptChar(c, shift));
            }
        }

        // Looping on the FileReader


        // Closing streams
        key.close();

        return encryptedString.toString();
    }

    /***
     * encryptChar method
     * @param c the character to be encrypted
     * @param shift the shift to be made on the character
     * @return the encrypted character
     */
    public static int encryptChar(int c, int shift) {
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
            return c; // Return the character without encrypting it
        }

        // Return the shifted/encrypted character
        return alphabet[(index + shift) % alphabet.length];
    }

}