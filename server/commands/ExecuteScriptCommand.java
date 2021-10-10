package server.commands;

import exceptions.WrongAmountOfElementsException;
import exceptions.WrongArgumentInputException;
import server.utility.ResponseOutputer;

/**
  * Execution of script
 */

public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "","исполнить скрипт из указанного файла");
    }

    /**
     * Executes the command, but partially.
     */
    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (argument.isEmpty() || objArgument != null) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
