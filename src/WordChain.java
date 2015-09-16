import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by samue_000 on 16/09/2015.
 */
public class WordChain {

    public static HashMap<String, String[]> wordToMasks = new HashMap<>();
    public static HashMap<String, ArrayList<String>> maskToWords = new HashMap<>();

    public static String[] generateMasks(String word) {
        String[] masks = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            masks[i] = word.substring(0, i) + "*" + word.substring(i + 1);
        }
        return masks;
    }

    public static void loadDictionary(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            String word = sc.nextLine();
            String[] masks = WordChain.generateMasks(word);
            wordToMasks.put(word, masks);
            for(String mask : masks){
                if(!maskToWords.containsKey(mask)){
                    maskToWords.put(mask,new ArrayList<>());
                }
                maskToWords.get(mask).add(word);
            }
        }
        sc.close();
    }
}
