import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by samue_000 on 16/09/2015.
 */
public class  WordChain {

    public static HashMap<String, String[]> wordToMasks = new HashMap<>();
    public static HashMap<String, ArrayList<String>> maskToWords = new HashMap<>();

    public static String[] generateMasks(String word) {
        String[] masks = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            masks[i] = word.substring(0, i) + "*" + word.substring(i + 1);
        }
        return masks;
    }

    public static void loadDictionary(String path,int length) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            String word = sc.nextLine();
            if(word.length()==length) {
                String[] masks = WordChain.generateMasks(word);
                wordToMasks.put(word, masks);
                for (String mask : masks) {
                    if (!maskToWords.containsKey(mask)) {
                        maskToWords.put(mask, new ArrayList<>());
                    }
                    maskToWords.get(mask).add(word);
                }
            }
        }
        sc.close();
    }

    public static class Node{

        String word;
        Node parent;

        public Node(String word, Node parent) {
            this.word = word;
            this.parent = parent;
        }
    }



    public static Node findEnd(String start, String end, String file) throws FileNotFoundException {
        loadDictionary(file,start.length());
        if(!wordToMasks.containsKey(start) || !wordToMasks.containsKey(end))
            return null;
         Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node e = new Node(start, null);
        visited.add(start);
        queue.add(e);
        while(!queue.isEmpty()){
            Node r = queue.remove();
            for (String mask : wordToMasks.get(r.word)){
                for(String neighbour: maskToWords.get(mask)){
                    if(!visited.contains(neighbour)){
                        Node n = new Node(neighbour,r);
                        if(neighbour.equalsIgnoreCase(end)){
                            return n;
                        }
                        visited.add(neighbour);
                        queue.add(n);
                    }
                }
            }
        }
        return null;
    }
    public static List<String> findShortestChain(String start, String end, String file) throws FileNotFoundException {
        Node n = findEnd(start, end, file);
        List<String> l = new ArrayList<>();
        while (n != null){
            l.add(n.word);
            n = n.parent;
        }
        return l;
    }
}
