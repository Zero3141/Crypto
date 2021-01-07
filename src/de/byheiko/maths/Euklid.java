package de.byheiko.maths;

import java.math.BigInteger;

public class Euklid {

    /**
     * Calculates the gcd of two numbers
     *
     * @param a The first number.
     * @param b The second number.
     * @return The gcd.
     */
    public static BigInteger euklid(BigInteger a, BigInteger b) {

        if(b.equals(BigInteger.ZERO)) {
            return a;
        }

        return euklid(b, a.mod(b));
    }


    /**
     * Calculates the extended euklid.
     *
     * @param a The first number.
     * @param b The second number.
     * @return EuklidResult (gcd d, k, l)
     */
    public static EuklidResult euklidExtended(BigInteger a, BigInteger b) {
        EuklidResult euklidResult = euklidExtendedHelper(b, a.mod(b));
        euklidResult.k = euklidResult.k.subtract(a.divide(b).multiply(euklidResult.l));
        return euklidResult;
    }


    /**
     * Helper function for the extended euklid.
     *
     * @param a The first number.
     * @param b The second number.
     * @return EuklidResult (gcd d, k, l)
     */
    private static EuklidResult euklidExtendedHelper(BigInteger a, BigInteger b) {

        if(b.equals(BigInteger.ZERO)) {
            return new EuklidResult(a, BigInteger.ONE, BigInteger.ZERO);
        }

        EuklidResult euklidResult = euklidExtendedHelper(b, a.mod(b));
        return new EuklidResult(euklidResult.d, euklidResult.l, euklidResult.k.subtract(a.divide(b).multiply(euklidResult.l)));
    }



    /**
     * The result for the extended euklid helper.
     */
    public static class EuklidResult {

        public BigInteger d;
        public BigInteger k;
        public BigInteger l;

        EuklidResult(BigInteger d, BigInteger k, BigInteger l) {
            this.d = d;
            this.k = k;
            this.l = l;
        }

    }

}
