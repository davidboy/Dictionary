/**
 * @author David Reed
 */
public class InvalidCommandException extends Exception {
    private CommandType commandCausingException;

    public InvalidCommandException(CommandType commandCausingException) {
        this.commandCausingException = commandCausingException;
    }

    public String getMessage() {
        return "Invalid parameters encountered for " + commandCausingException + " command.  Correct syntax: " + commandCausingException.getCorrectSyntax();
    }
}
