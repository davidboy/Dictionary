import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author David Reed
 */
public class CommandRunner {
    private Dictionary dictionary;

    public CommandRunner(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void processCommandFile(String fileLocation) throws AppQuitException {
        System.out.println("Processing " + fileLocation);

        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    processCommand(line);
                } catch (InvalidCommandException e) {
                    System.out.println("Skipping invalid command: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: input file " + fileLocation + " not found.");
        } catch (IOException e) {
            System.out.println("Error reading from input file " + fileLocation + ".");
        }
    }

    public void processCommand(String command) throws InvalidCommandException, AppQuitException {
        String[] parts = command.split("\\s+");

        switch (parts[0].toLowerCase()) {
            case "add":
                processAddCommand(parts);
                break;
            case "find":
                processFindCommand(parts);
                break;
            case "list":
                processListCommand(parts);
                break;
            case "load":
                processLoadCommand(parts);
                break;
            case "tree":
                dictionary.dumpTree();
                break;
            case "help":
                printHelp();
                break;
            case "delete":
                processDeleteCommand(parts);
                break;
            case "quit":
                throw new AppQuitException();
        }
    }

    private void processAddCommand(String[] commandParts) throws InvalidCommandException {
        if (commandParts.length < 3) {
            throw new InvalidCommandException(CommandType.ADD);
        }

        String word = commandParts[1];
        String definition = String.join(" ", Arrays.copyOfRange(commandParts, 2, commandParts.length));

        dictionary.addDefinition(word, definition);
    }

    private void displayDefinitions(String word) {
        displayDefinitions(word, dictionary.getDefinitions(word));
    }

    private void displayDefinitions(LinkedList<Entry<String, LinkedList<String>>> entries) {
        for (Entry<String, LinkedList<String>> entry : entries) {
            displayDefinitions(entry.getKey(), entry.getValue());
        }
    }

    private void displayDefinitions(String word, LinkedList<String> definitions) {
        if (definitions == null) {
            System.out.println("No definitions found for " + word);
            return;
        }

        System.out.println(word + ":");

        int i = 1;
        for (String definition : definitions) {
            System.out.println("\t" + i++ + ") " + definition);
        }
    }

    private void processFindCommand(String[] commandParts) throws InvalidCommandException {
        if (commandParts.length != 2) {
            throw new InvalidCommandException(CommandType.FIND);
        }

        displayDefinitions(commandParts[1]);
    }

    private void processListCommand(String[] commandParts) throws InvalidCommandException {
        if (commandParts.length == 1) {
            displayDefinitions(dictionary.getWords());
        } else if (commandParts.length == 3) {
            displayDefinitions(dictionary.getWordsBetween(commandParts[1], commandParts[2]));
        } else {
            throw new InvalidCommandException(CommandType.LIST);
        }
    }

    private void processLoadCommand(String[] commandParts) throws InvalidCommandException, AppQuitException {
        if (commandParts.length <= 1) {
            throw new InvalidCommandException(CommandType.LOAD);
        }

        for (int i = 1; i < commandParts.length; i++) {
            processCommandFile(commandParts[i]);
        }
    }

    private void processDeleteCommand(String[] commandParts) throws InvalidCommandException {
        if (commandParts.length != 2) {
            throw new InvalidCommandException(CommandType.DELETE);
        }

        try {
            dictionary.deleteWord(commandParts[1]);
        } catch (NodeNotFoundException e) {
            System.out.println("That word isn't in the dictionary, so it can't be deleted.");
        } catch (DeleteNotImplementedException e) {
            System.out.println("Delete operation not implemented for red black trees.");
        }
    }

    private void printHelp() {
        System.out.println("Commands available: ");

        System.out.println("\tadd <word> <definition>");
        System.out.println("\tdelete <word>");
        System.out.println("\tfind <word>");
        System.out.println("\tlist");
        System.out.println("\tlist <begin word> <end word>");
        System.out.println("\tload <file name>");
        System.out.println("\ttree");
        System.out.println("\tquit");
    }
}
