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

        System.out.println("Done processing command files.  You can now enter custom commands (type help for a list of commands, or quit to exit the program)");
        Scanner consoleInput = new Scanner(System.in);
        while (true) {
            System.out.print("> ");

            try {
                commandParser.processCommand(consoleInput.nextLine());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (AppQuitException | NoSuchElementException e) {
                return;
            }
        }
    }

    private void printValidTrees() {
        System.out.println("Valid trees: ");
        System.out.println("\tBST - Binary Search Tree");
        System.out.println("\tRB - Red Black Tree");
        System.out.println("\tAVL - AVL Tree");
        System.out.println("\tSplay - Splay Tree");
    }

    public static void main(String[] args) {
        new Main().run(args);
    }
}
