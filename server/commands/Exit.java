package server.commands;

import exceptions.WrongAmountOfElementsException;
import exceptions.WrongArgumentInputException;
import server.utility.ResponseOutputer;


/**
 * Exit
 */
public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "","завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument != null) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
