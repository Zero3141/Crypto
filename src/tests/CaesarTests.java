package tests;

import de.byheiko.algos.Caesar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarTests {

    @Test
    public void encrypt() {
        Caesar caesar = new Caesar();
        assertEquals("zab", caesar.encrypt("xyz", 'c'));
    }

    @Test
    public void decrypt() {
        Caesar caesar = new Caesar();
        assertEquals("abc", caesar.decrypt("cde", 'c'));
    }

    @Test
    public void customAlphabet() {
        Caesar caesar = new Caesar("abc");
        assertEquals("a", caesar.encrypt("b", 'c'));
        assertEquals("c", caesar.decrypt("a", 'b'));
    }

}
