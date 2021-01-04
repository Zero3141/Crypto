package tests;

import de.heiko.maths.Euklid;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class EuklidTests {

    @Test
    void euklid() {

        BigInteger a = BigInteger.valueOf(13);
        BigInteger b = BigInteger.valueOf(31);
        BigInteger c = BigInteger.valueOf(39);

        assertEquals(BigInteger.ONE, Euklid.euklid(a, b));
        assertEquals(BigInteger.valueOf(13), Euklid.euklid(a, c));

    }

    @Test
    void euklidExtended() {

        BigInteger a = BigInteger.valueOf(141);
        BigInteger b = BigInteger.valueOf(9);

        Euklid.EuklidResult result = Euklid.euklidExtended(a, b);

        assertEquals(result.d, BigInteger.valueOf(3));
        assertEquals(result.k, BigInteger.valueOf(16));
        assertEquals(result.l, BigInteger.valueOf(-1));

    }

}
