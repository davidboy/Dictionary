/**
 * @author David Reed
 */
public class Dictionary {
    private AbstractBinaryTree<String, LinkedList<String>> definitions;

    public Dictionary(String type) throws InvalidTreeException {
        definitions = buildTree(type);
    }

    public void addDefinition(String word, String newDefinition) {
        LinkedList<String> existingDefinitions = definitions.get(word);

        if (existingDefinitions == null) {
            definitions.put(word, LinkedList.containing(newDefinition));
            return;
        }

        for (String existingDefinition : existingDefinitions) {
            if (existingDefinition.equals(newDefinition)) {
                return;
            }
        }

        existingDefinitions.add(newDefinition);
    }

    public LinkedList<String> getDefinitions(String word) {
        return definitions.get(word);
    }

    public LinkedList<Entry<String, LinkedList<String>>> getWords() {
        return definitions.getEntries();
    }

    public LinkedList<Entry<String, LinkedList<String>>> getWordsBetween(String beginning, String end) {
        return definitions.getEntriesBetween(beginning, end);
    }

    public void dumpTree() {
        definitions.printDebugView();
    }

    private AbstractBinaryTree<String, LinkedList<String>> buildTree(String type) throws InvalidTreeException {
        switch (type) {
            case "BST":
                return new BinarySearchTree<>();
            case "RB":
                return new RedBlackTree<>();
            case "AVL":
                return new AVLTree<>();
            case "Splay":
                return new SplayTree<>();
        }

        throw new InvalidTreeException();
    }
}
