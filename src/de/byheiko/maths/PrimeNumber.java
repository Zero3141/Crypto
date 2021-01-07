package de.byheiko.maths;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeNumber {

    /**
     * The prime number.
     */
    private BigInteger bigInteger;

    /**
     * A secure random instance.
     */
    private SecureRandom random;


    /**
     * Constructor
     *
     * @param random Secure random instance.
     */
    public PrimeNumber(SecureRandom random) {
        this.random = random;
    }

    /**
     * Generates a random prime number.
     *
     * @param bitLength The bit length (must be greater than 2).
     * @return PrimeNumber.
     */
    public PrimeNumber generateRandom(int bitLength) {
        bigInteger = BigInteger.probablePrime(bitLength, random);
        return this;
    }

    /**
     * Returns the prime number as a big integer.
     *
     * @return The big integer.
     */
    public BigInteger toBigInteger() {
        return bigInteger;
    }

}
