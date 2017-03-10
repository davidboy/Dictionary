/**
 * @author David Reed
 */
public interface Dictionary {
    void addDefinition(String word, String definition);
    String[] getDefinitions(String word);

    String[] getWords();
    String[] getWordsBetween(String beginning, String end);

    void printDebugTree();
}
