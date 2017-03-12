/**
 * @author David Reed
 */
public class Dictionary {
    private Set<String> words;
    private AbstractTree<String, LinkedList<String>> definitions;

    public Dictionary(String type) throws InvalidTreeException {
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

    public LinkedList<Entry<String, LinkedList<String>>> getWordsBetween(String beginning, String end) {
        return definitions.getItemsBetween(beginning, end);
    }

    public void dumpTree() {
        definitions.printDebugView();
    }

    private AbstractTree<String, LinkedList<String>> buildTree(String type) throws InvalidTreeException {
        switch (type) {
            case "BST":
                return new BinarySearchTree<>();
            case "RB":
                return new RedBlackTree<>();
        }

        throw new InvalidTreeException();
    }
}
