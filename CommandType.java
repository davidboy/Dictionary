/**
 * @author David Reed
 */

public enum CommandType {
    ADD, DELETE, FIND, LIST, LOAD, TREE;

    public String getCorrectSyntax() {
        switch (this) {
            case ADD: return "add <word> <definition>";
            case DELETE: return "delete <word>";
            case FIND: return "find <word>";
            case LIST: return "list <begin word (optional)> <end word (optional)>";
            case LOAD: return "load <file name>";
            case TREE: return "tree";
            default: return null;
        }
    }

    public String toString() {
        switch (this) {
            case ADD: return "add";
            case DELETE: return "delete";
            case FIND: return "find";
            case LIST: return "list";
            case LOAD: return "load";
            case TREE: return "tree";
            default: return null;
        }
    }
}