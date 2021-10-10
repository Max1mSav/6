package server.commands;

import exceptions.CollectionEmptyException;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Filter by names
 */

public class FilterContainsName extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterContainsName(CollectionManager collectionManager) {
        super("filter_contains_name name", "","вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (argument.isEmpty() || objArgument == null) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            String filteredElements = collectionManager.nameFilter(argument);
            if (!filteredElements.isEmpty()) {
                ResponseOutputer.appendln(filteredElements);
                return true;
            } else ResponseOutputer.appendln("В коллекции нет элементов с таким именем");
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Вы ничего не ввели");
        } catch (CollectionEmptyException exception) {
            ResponseOutputer.appendln("Коллекция пуста!");
        }
        return false;
    }
}
