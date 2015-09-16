import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by samue_000 on 16/09/2015.
 */
public class WordChainTest {

    private final static String wordFile = "c:/workarea/wordchain/words.txt";

    @Test
    public void testGenerateMasks() throws Exception {
        assertEquals(WordChain.generateMasks("cat").length,3);
        assertEquals(WordChain.generateMasks("cat")[0],"*at");
        assertEquals(WordChain.generateMasks("cat")[2],"ca*");

    }

    @Test
    public void testLoadDictionary() throws Exception{
        WordChain.loadDictionary(wordFile);
        assertEquals(WordChain.wordToMasks.get("cat")[0], "*at");
        assertEquals(WordChain.wordToMasks.get("cat")[2], "ca*");
        assertTrue(WordChain.maskToWords.get("ca*").contains("cat"));
        assertTrue(WordChain.maskToWords.get("*at").contains("cat"));
    }
}