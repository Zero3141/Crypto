package tests;

import de.heiko.algos.RSA;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RSATests {

    @Test
    void generateKeys() {

        RSA rsa = new RSA();
        RSA.Keys keys = rsa.generateKeys(2048);

        BigInteger m = BigInteger.valueOf(10);
        BigInteger c = rsa.encrypt(m, keys.publicKey);
        BigInteger m_d = rsa.decrypt(c, keys.privateKey);

        assertEquals(m, m_d);
    }

    @Test
    void encrypt() {

        BigInteger m = BigInteger.valueOf(8);

        RSA rsa = new RSA();
        RSA.PublicKey publicKey = new RSA.PublicKey(BigInteger.valueOf(11), BigInteger.valueOf(899));

        assertEquals(BigInteger.valueOf(380), rsa.encrypt(m, publicKey));
    }

    @Test
    void decrypt() {

        BigInteger c = BigInteger.valueOf(722);

        RSA rsa = new RSA();
        RSA.PrivateKey privateKey = new RSA.PrivateKey(BigInteger.valueOf(-323), BigInteger.valueOf(899));

        assertEquals(BigInteger.valueOf(10), rsa.decrypt(c, privateKey));

    }

}
