import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author David Reed
 */
public class Main {
    private Dictionary dictionary;

    private void run(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: please specify the type of tree to use.");
            printValidTrees();
            return;
        }

        try {
            dictionary = new Dictionary(args[0]);
        } catch (InvalidTreeException e) {
            System.out.println("Error: invalid tree type specified.");
            printValidTrees();
        }

        CommandRunner commandParser = new CommandRunner(dictionary);

        for (int i = 1; i < args.length; i++) {
            try {
                commandParser.processCommandFile(args[i]);
            } catch (AppQuitException e) {
                return;
            }
        }

        System.out.println("Done processing command files.  Enter custom commands:");
        Scanner consoleInput = new Scanner(System.in);
        while (true) {
            System.out.print("> ");

            try {
                commandParser.processCommand(consoleInput.nextLine());
            } catch (InvalidCommandException e) {
                System.out.println("Error: invalid command encountered.");
            } catch (AppQuitException | NoSuchElementException e) {
                return;
            }
        }
    }

    private void printValidTrees() {
        System.out.println("Valid trees: ");
        System.out.println("\tBST - Binary Search Tree");
        System.out.println("\t RB - Red Black Tree");
        System.out.println("\t AVL - AVL Tree");
    }

    public static void main(String[] args) {
        new Main().run(args);
    }
}
