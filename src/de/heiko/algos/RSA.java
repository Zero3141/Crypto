package de.heiko.algos;

import de.heiko.exceptions.RSAException;
import de.heiko.maths.Euklid;
import de.heiko.maths.PrimeNumber;
import javafx.util.Pair;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    /**
     * A secure random instance.
     */
    private SecureRandom random;


    /**
     * Constructor.
     */
    public RSA() {
        this.random = new SecureRandom();
    }


    /**
     * Generates a new key pair.
     *
     * @param bitLength The bit length.
     * @return The key pair.
     */
    public Pair<PublicKey, PrivateKey> generateKeys(int bitLength) {

        // Generate two random prime numbers
        PrimeNumber primeNumber = new PrimeNumber(random);
        BigInteger p = primeNumber.generateRandom(bitLength).toBigInteger();

        BigInteger q = primeNumber.generateRandom(bitLength).toBigInteger();
        while(p.equals(q)) {
            q = primeNumber.generateRandom(bitLength).toBigInteger();
        }

        // Calculate n
        BigInteger n = p.multiply(q);

        // Calculate phi(n)
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Calculate e
        BigInteger e = new BigInteger(bitLength, random);
        while (!Euklid.euklid(e, phiN).equals(BigInteger.ONE)) {
            e = new BigInteger(bitLength, random);
        }

        // Calculate d
        BigInteger d = Euklid.euklidExtended(e, phiN).getKey();

        // Return the key pair
        return new Pair<>(new PublicKey(e, n), new PrivateKey(e, n, d));
    }


    /**
     * Encrypts a message m using a public key.
     *
     * @param m The message.
     * @param publicKey The public key.
     * @return The cipher.
     * @throws RSAException Thrown when message size is bigger than n.
     */
    public BigInteger encrypt(BigInteger m, PublicKey publicKey) throws RSAException {

        if(m.compareTo(publicKey.n) >= 0) {
            throw new RSAException("The message size can not be greater or equal n.");
        }

        return m.modPow(publicKey.e, publicKey.n);
    }


    /**
     * Decrypts a cipher c using a private key.
     *
     * @param c The cipher c.
     * @param privateKey The private key.
     * @return The message.
     */
    public BigInteger decrypt(BigInteger c, PrivateKey privateKey) {
        return c.modPow(privateKey.d, privateKey.n);
    }


    /**
     * The public key containing (n, e)
     */
    public static class PublicKey {

        public BigInteger e;
        public BigInteger n;

        public PublicKey(BigInteger e, BigInteger n) {
            this.e = e;
            this.n = n;
        }

        @Override
        public String toString() {
            return "PublicKey{" +
                    "e=" + e +
                    ", n=" + n +
                    '}';
        }
    }

    /**
     * The private key containing (n, e, d)
     */
    public static class PrivateKey extends PublicKey {

        public BigInteger d;

        public PrivateKey(BigInteger e, BigInteger n, BigInteger d) {
            super(e, n);
            this.d = d;
        }

        @Override
        public String toString() {
            return "PrivateKey{" +
                    "e=" + e +
                    ", n=" + n +
                    ", d=" + d +
                    '}';
        }

    }

}
