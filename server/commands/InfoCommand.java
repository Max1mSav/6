package server.commands;

import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Information about command.
 */

public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "","Доступная информация о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument != null) throw new WrongArgumentInputException();
            LocalDateTime lastInitTime = collectionManager.getLastInitializationTime();
            String lastInitTimeString = (lastInitTime == null) ? "инициализации не было" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "сохранения не было" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            ResponseOutputer.appendln("Информация о данной коллекции:");
            ResponseOutputer.appendln(" Тип: " + collectionManager.collectionType());
            ResponseOutputer.appendln(" Дата последней инициализации: " + lastInitTimeString);
            ResponseOutputer.appendln(" Количество элементов: " + collectionManager.collectionSize());
            ResponseOutputer.appendln(" Дата последнего сохранения: " + lastSaveTimeString);
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Execution: '" + getName());
        }
        return false;
    }
}
