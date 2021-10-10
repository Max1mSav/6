package server.commands;

import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

import java.io.IOException;

/**
 * Save collection to the file.
 */

public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "","сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument != null) throw new WrongArgumentInputException();
            collectionManager.saveCollection();
            return true;
        } catch (WrongArgumentInputException | IOException e) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        return false;
    }
}
