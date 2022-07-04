package test;

import org.junit.jupiter.api.Test;

import static com.company.BinaryTransform.BinaryNoneDecoder;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTransformTest {

    @Test
    void binaryToS()  {
        assertEquals("1", "1");
    }

    @Test
    void binaryDecoder() {


    }

    @Test
    void binaryNoneDecoder() {
        assertEquals(0, BinaryNoneDecoder(0));
        assertEquals(77, BinaryNoneDecoder(0x4D));

        assertEquals(0, BinaryNoneDecoder(0,0));
        assertEquals(19832, BinaryNoneDecoder(0x4D,0x78));

        assertEquals(13_622_254, BinaryNoneDecoder(0xCF,0xDB,0xEE));
        assertEquals(908765, BinaryNoneDecoder(0xDDDDD));



    }

    @Test
    void binaryCoder() {
    }
}