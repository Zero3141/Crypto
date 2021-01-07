package de.byheiko.algos;

import de.byheiko.exceptions.CaesarException;

public class Caesar {

    /**
     * The alphabet.
     */
    private String alphabet;


    /**
     * Constructor with default alphabet.
     */
    public Caesar() {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
    }

    /**
     * Constructor with custom alphabet.
     *
     * @param alphabet The alphabet.
     */
    public Caesar(String alphabet) {
        this.alphabet = alphabet;
    }


    /**
     * Encrypts a message using a char as key.
     *
     * @param message The message.
     * @param key The key.
     * @return Encrypted string.
     * @throws CaesarException If a char of message or the key is not in alphabet.
     */
    public String encrypt(String message, char key) throws CaesarException {

        if (!hasInvalidCharacters(message.toCharArray())) {
            hasInvalidCharacters(new char[]{key});
        }

        StringBuilder builder = new StringBuilder();

        for(char c : message.toCharArray()) {

            int cIndex = alphabet.indexOf(c);
            int kIndex = alphabet.indexOf(key);

            int e = (cIndex + kIndex) % alphabet.length();

            builder.append(alphabet.charAt(e));
        }


        return builder.toString();
    }


    /**
     * Decrypts a message using a char as key.
     *
     * @param message The message.
     * @param key The key.
     * @return Decrypted string.
     * @throws CaesarException If a char of message or the key is not in alphabet.
     */
    public String decrypt(String message, char key) throws CaesarException {

        int kIndex = alphabet.indexOf(key);
        key = alphabet.charAt(alphabet.length() - kIndex % alphabet.length());

        return encrypt(message, key);
    }



    /**
     * Checks if characters are contained in alphabet.
     *
     * @param cs The character array.
     * @return Boolean.
     * @throws CaesarException If character is not in alphabet.
     */
    private boolean hasInvalidCharacters(char[] cs) throws CaesarException {

        for(char c : cs) {
            if(!alphabet.contains(Character.toString(c))) {
                throw new CaesarException(String.format("Character %s is not in alphabet", c));
            }
        }

        return false;
    }



}
