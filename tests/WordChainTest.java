import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
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
    public void testFindEnd() throws Exception {
        assertEquals(WordChain.findEnd("cat","dog",wordFile).word,"dog");
    }

    @Test
    public void testFindShortestChain() throws Exception {
        List<String> shortestChain = WordChain.findShortestChain("cat", "dog", wordFile);
        assertEquals(4,shortestChain.size());
        assertEquals("cat", shortestChain.get(0));
        long s =System.currentTimeMillis();
        shortestChain = WordChain.findShortestChain("mediocre", "meditate", wordFile);
        System.out.println("completed in: "+(System.currentTimeMillis()-s));
        assertEquals(0, shortestChain.size());
        s = System.currentTimeMillis();
        shortestChain = WordChain.findShortestChain("computer", "commonty", wordFile);
        System.out.println("completed in: "+(System.currentTimeMillis()-s));
        assertEquals(6, shortestChain.size());
        s = System.currentTimeMillis();
        shortestChain = WordChain.findShortestChain("monster", "smirkly", wordFile);
        System.out.println("completed in: "+(System.currentTimeMillis()-s));
        assertEquals(30,shortestChain.size());
    }
}