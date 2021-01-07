package de.heiko.algos;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {

    /**
     * A secure random instance.
     */
    private SecureRandom random;


    /**
     * Constructor.
     */
    public ElGamal() {
        this.random = new SecureRandom();
    }

    /**
     * Generates a new key pair.
     *
     * @param bitLength The bit length.
     * @return The key pair.
     */
    public Keys generateKeys(int bitLength) {

        // Generate cyclic group
        BigInteger G = BigInteger.probablePrime(bitLength, random);

        // Generate generator g, where g \in G
        BigInteger g = new BigInteger(bitLength, random);
        while(g.compareTo(G) >= 0) {
            g = new BigInteger(bitLength, random);
        }

        // Generate exponent a, where a in {2,...,ord(G)-1}
        BigInteger a = new BigInteger(bitLength, random);
        while(a.compareTo(BigInteger.valueOf(2)) < 0 ||  a.compareTo(G) >= 1) {
            a = new BigInteger(bitLength, random);
        }

        // Calculate A = g^a mod G
        BigInteger A = g.modPow(a, G);

        // Return the key pair
        PublicKey publicKey = new PublicKey(G, g, A);
        PrivateKey privateKey = new PrivateKey(G, g, a);

        return new Keys(publicKey, privateKey);
    }

    /**
     * Encrypts a message m using a public key.
     * A bit length for r is required, recommended use same as for keys.
     *
     * @param m The message.
     * @param bitLength The bit length of r.
     * @param publicKey The public key.
     * @return The chiffre.
     */
    public Chiffre encrypt(BigInteger m, int bitLength, PublicKey publicKey) {

        // Generate random r in {2,...,ord(G)-1}
        BigInteger r = new BigInteger(bitLength, random);
        while(r.compareTo(BigInteger.valueOf(2)) < 0 ||  r.compareTo(publicKey.G) >= 1) {
            r = new BigInteger(bitLength, random);
        }

        // Calculate R
        BigInteger R = publicKey.g.modPow(r, publicKey.G);

        // Calculate K
        BigInteger K = publicKey.A.modPow(r, publicKey.G);

        // Calculate C
        BigInteger C = m.multiply(K).mod(publicKey.G);

        return new Chiffre(R, C);
    }

    /**
     * Decrypts a cipher c using a private key.
     *
     * @param chiffre The chiffre.
     * @param privateKey The private key.
     * @return The message.
     */
    public BigInteger decrypt(Chiffre chiffre, PrivateKey privateKey) {

        // Calculate K
        BigInteger K = chiffre.R.modPow(privateKey.a, privateKey.G);

        // Find K^-1 in G
        BigInteger inverseK = K.modInverse(privateKey.G);

        // Decrypt C * K^-1 and return
        return chiffre.C.multiply(inverseK).mod(privateKey.G);
    }


    /**
     * The public and private key pair.
     */
    public static class Keys {

        public PublicKey publicKey;
        public PrivateKey privateKey;

        public Keys(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

    }

    /**
     * The public key containing (G, g, A)
     */
    public static class PublicKey {

        public BigInteger G;
        public BigInteger g;
        public BigInteger A;

        public PublicKey(BigInteger G, BigInteger g, BigInteger A) {
            this.G = G;
            this.g = g;
            this.A = A;
        }

    }

    /**
     * The private key containing (G, g, a)
     */
    public static class PrivateKey {

        public BigInteger G;
        public BigInteger g;
        public BigInteger a;

        public PrivateKey(BigInteger G, BigInteger g, BigInteger a) {
            this.G = G;
            this.g = g;
            this.a = a;
        }

    }

    /**
     * The chiffre after encrypting containing (R, C)
     */
    public static class Chiffre {

        public BigInteger R;
        public BigInteger C;

        public Chiffre(BigInteger R, BigInteger C) {
            this.R = R;
            this.C = C;
        }

    }

}
