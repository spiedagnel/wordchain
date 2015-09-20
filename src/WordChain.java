import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by samue_000 on 16/09/2015.
 */
public class  WordChain {

    public static String[] generateMasks(String word) {
        String[] masks = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            masks[i] = word.substring(0, i) + "*" + word.substring(i + 1);
        }
        return masks;
    }

    public static HashMap<String, ArrayList<String>>  loadDictionary(String path,int length) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> maskToWords = new HashMap<>();
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            String word = sc.nextLine();
            if(word.length()==length) {
                String[] masks = generateMasks(word);
                for (String mask : masks) {
                    ArrayList<String> words = maskToWords.get(mask);
                    if (words==null) {
                        words = new ArrayList<>();
                        maskToWords.put(mask,words);
                    }

                    words.add(word);
                }
            }
        }
        sc.close();
        System.out.println("loaded in:"+(System.currentTimeMillis()-time));
        return maskToWords;
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
        HashMap<String, ArrayList<String>> maskToWords = loadDictionary(file,start.length());

         Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node e = new Node(start, null);
        visited.add(start);
        queue.add(e);
        while(!queue.isEmpty()){
            Node r = queue.remove();
            for (String mask : generateMasks(r.word)){
                ArrayList<String> words = maskToWords.get(mask);
                if(words!=null) {
                    for (String neighbour : words) {
                        if (!visited.contains(neighbour)) {
                            Node n = new Node(neighbour, r);
                            if (neighbour.equalsIgnoreCase(end)) {
                                return n;
                            }
                            visited.add(neighbour);
                            queue.add(n);
                        }
                    }
                }
            }
        }
        return null;
    }
    public static List<String> findShortestChain(String start, String end, String file) throws FileNotFoundException {
        Node n = findEnd(start, end, file);
        LinkedList<String> l = new LinkedList<>();
        while (n != null){
            l.push(n.word);
            n = n.parent;
        }
        return  l;
    }
}
