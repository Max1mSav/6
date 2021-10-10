package server.commands;

import client.utility.TicketChecker;
import exceptions.CollectionEmptyException;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Filter by price
 */

public class FilterLessPrice extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterLessPrice(CollectionManager collectionManager) {
        super("filter_less_than_price price", "","вывести элементы, значение поля price которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        Double priceLess;
        try {
            if (argument.isEmpty() || objArgument == null) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            priceLess = Double.parseDouble(argument);
            ResponseOutputer.appendln(priceLess);
            String filteredElements = collectionManager.priceLess(priceLess);
            if (!filteredElements.isEmpty()) {
                ResponseOutputer.appendln(filteredElements);
                return true;
            } else ResponseOutputer.appendln("В коллекции нет билетов с ценой ниже " + priceLess);
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: " + getName());
        } catch (CollectionEmptyException exception) {
            ResponseOutputer.appendln("Коллекция пуста!");
        }
        return false;
    }
}
