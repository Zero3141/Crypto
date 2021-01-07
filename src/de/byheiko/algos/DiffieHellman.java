package de.byheiko.algos;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {

    /**
     * A secure random instance.
     */
    private final SecureRandom random;

    /**
     * The public containing p and g.
     */
    private final Public pub;

    /**
     * The clients secret key.
     */
    private BigInteger secretKey;


    /**
     * Constructor.
     */
    public DiffieHellman(Public pub) {
        this.pub = pub;
        this.random = new SecureRandom();
    }

    /**
     * Generates the public part.
     *
     * @param bitLength The bit length.
     * @return Public containing p and g
     */
    public static Public generatePublic(int bitLength) {

        // Create new secure random instance
        SecureRandom random = new SecureRandom();

        // Generate random prime
        BigInteger p = BigInteger.probablePrime(bitLength, random);

        // Generate g smaller than p
        BigInteger g = new BigInteger(bitLength, random);
        while(g.compareTo(p) >= 0) {
            g = new BigInteger(bitLength, random);
        }

        return new Public(p, g);
    }

    /**
     * Generates the secret key and calculates g^secretKey mod p.
     *
     * @param bitLength The bit length.
     * @return The g^secretKey mod p of this client.
     */
    public BigInteger generateSecretAndCalculate(int bitLength) {

        // Generate random number with 0 < secretKey < p
        secretKey = new BigInteger(bitLength, random);
        while(secretKey.compareTo(pub.p) >= 0 || secretKey.compareTo(BigInteger.ZERO) <= 0) {
            secretKey = new BigInteger(bitLength, random);
        }

        // Calculate g^secretKey mod p
        return pub.g.modPow(secretKey, pub.p);
    }

    /**
     * Calculates the shared key.
     *
     * @param gx The g^secretKey of other client.
     * @return The shared key.
     */
    public BigInteger calculateKey(BigInteger gx) {

        // Calculate gx^secretKey mod p
        return gx.modPow(secretKey, pub.p);
    }

    /**
     * The public part
     */
    public static class Public {

        public final BigInteger p;
        public final BigInteger g;

        public Public(BigInteger p, BigInteger g) {
            this.p = p;
            this.g = g;
        }

    }

}
