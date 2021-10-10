package server.commands;

import exceptions.WrongArgumentInputException;
import server.utility.ResponseOutputer;

/**
 * Last 8 commands
 */

public class History extends AbstractCommand {
    public History() {
        super("history", "","вывести историю использованных команд");
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument != null) throw new WrongArgumentInputException();
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: " + getName());
        }
        return false;
    }
}
