import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by samue_000 on 18/09/2015.
 */
public class WordChainTest {
    private final static String wordFile = "c:/workarea/ideaworkspace/wordchain/words.txt";

    @Test
    public void testGenerateMasks() throws Exception {
        assertEquals(WordChain.generateMasks("cat").length,3);
        assertEquals(WordChain.generateMasks("cat")[0],"*at");
        assertEquals(WordChain.generateMasks("cat")[2],"ca*");

    }

    @Test
    public void testLoadDictionary() throws Exception{
        WordChain.loadDictionary(wordFile,3);
        assertEquals(WordChain.wordToMasks.get("cat")[0], "*at");
        assertEquals(WordChain.wordToMasks.get("cat")[2], "ca*");
        assertTrue(WordChain.maskToWords.get("ca*").contains("cat"));
        assertTrue(WordChain.maskToWords.get("*at").contains("cat"));
    }

    @Test
    public void testFindEnd() throws Exception {
        assertEquals(WordChain.findEnd("cat","dog",wordFile).word,"dog");
    }

    @Test
    public void testFindShortestChain() throws Exception {
        List<String> shortestChain = WordChain.findShortestChain("cat", "dog", wordFile);
        assertEquals(shortestChain.size(),4);
        assertEquals("cat",shortestChain.get(3));
        shortestChain = WordChain.findShortestChain("mediocre", "meditate", wordFile);
        assertEquals(shortestChain.size(),0);
        shortestChain = WordChain.findShortestChain("cued", "gold", wordFile);
        assertEquals(0,shortestChain.size());
    }
}