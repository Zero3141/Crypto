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
    public Keys generateKeys(int bitLength) {

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

        // Use fixed value 2^(16)+1 = 65537 as e
        BigInteger e = BigInteger.valueOf(65537);

        // Calculate d
        BigInteger d = Euklid.euklidExtended(e, phiN).l;

        // Return the key pair
        PublicKey publicKey = new PublicKey(e, n);
        PrivateKey privateKey = new PrivateKey(d, n);

        return new Keys(publicKey, privateKey);
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
     * The public and private key pair.
     */
    public static class Keys {

        public PublicKey publicKey;
        public PrivateKey privateKey;

        public Keys(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override
        public String toString() {
            return "Keys{" +
                    "publicKey=" + publicKey +
                    ", privateKey=" + privateKey +
                    '}';
        }

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
     * The private key containing (d, n)
     */
    public static class PrivateKey {

        public BigInteger d;
        public BigInteger n;

        public PrivateKey(BigInteger d, BigInteger n) {
            this.d = d;
            this.n = n;
        }

        @Override
        public String toString() {
            return "PrivateKey{" +
                    "d=" + d +
                    ", n=" + n +
                    '}';
        }

    }

}
