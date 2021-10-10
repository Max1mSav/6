package server.commands;

import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Show information about every element of collection.
 */

public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ShowCommand (CollectionManager collectionManager) {
        super("show", "","Вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument != null) throw new WrongArgumentInputException();
            ResponseOutputer.appendln(collectionManager.showCollection());
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
