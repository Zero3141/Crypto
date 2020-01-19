package tests;

import de.heiko.maths.PrimeNumber;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberTests {

    @Test
    void generateRandom() {

        PrimeNumber primeNumber = new PrimeNumber(new SecureRandom());
        BigInteger bigInteger = primeNumber.generateRandom(5).toBigInteger();

        assertNotNull(bigInteger);

        boolean isPrime = true;
        for(int divisor = 2; divisor <= bigInteger.intValue() / 2; divisor++) {
            if (bigInteger.intValue() % divisor == 0) {
                isPrime = false;
                break;
            }
        }
        assertTrue(isPrime);

    }

}
