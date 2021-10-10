package server.commands;

import client.utility.TicketChecker;
import exceptions.CollectionEmptyException;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Discount sorting.
 */

public class DiscountInAscendingOrder extends AbstractCommand {
    private CollectionManager collectionManager;

    public DiscountInAscendingOrder(CollectionManager collectionManager) {
        super("print_field_ascending_discount", "", "вывести значения поля discount всех элементов в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            collectionManager.sortDiscount();
            return true;
        } catch (WrongArgumentInputException e) {
            ResponseOutputer.appendln("Неправильно введены данные");
        } catch (CollectionEmptyException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}
