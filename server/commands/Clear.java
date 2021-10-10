package server.commands;

import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Delete whole collection.
 */

public class Clear extends AbstractCommand {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            collectionManager.deleteCollection();
            ResponseOutputer.appendln("Коллекция очищена!");
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
