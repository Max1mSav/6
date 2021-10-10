package server.commands;
import exceptions.WrongArgumentInputException;
import server.utility.ResponseOutputer;

/**
 * Help command
 */

public class HelpCommand extends AbstractCommand {
   public HelpCommand() {
       super("help", "", "Доступные команды");
   }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument != null) throw new WrongArgumentInputException();
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Execution: '" + getName());
        }
        return false;
    }
}
