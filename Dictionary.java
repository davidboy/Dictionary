/**
 * @author David Reed
 */
public class Dictionary {
    private Set<String> words;
    private AbstractTree<String, LinkedList<String>> definitions;

    public Dictionary(String type) {
        words = new Set<>();
        definitions = buildTree(type);
    }

    public void addDefinition(String word, String newDefinition) {
        if (words.contains(word)) {
            for (String existingDefinition : getDefinitions(word)) {
                if (existingDefinition.equals(newDefinition)) {
                    return;
                }
            }

            getDefinitions(word).add(newDefinition);
        } else {
            words.add(word);
            definitions.put(word, LinkedList.containing(newDefinition));
        }
    }

    public LinkedList<String> getDefinitions(String word) {
        return definitions.get(word);
    }

    public LinkedList<String> getWords() {
        return words.toList();
    }

    public LinkedList<String> getWordsBetween(String beginning, String end) {
        // TODO: write
        return null;
    }

    private AbstractTree<String, LinkedList<String>> buildTree(String type) {
        switch (type) {
            case "BST":
                return new BinarySearchTree<>();
            case "RB":
                return new RedBlackTree<>();
        }

        // TODO: handle error better
        throw new RuntimeException("NOPE");
    }

    public static void main(String[] args) {

    }
}
