package server.commands;

import data.Ticket;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Remove by id.
 */

public class RemoveById extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id id", "","удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (argument.isEmpty() || objArgument != null) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            Long id = Long.parseLong(argument);
            Ticket ticketRemove = collectionManager.getById(id);
            if (ticketRemove == null) throw new EmptyException();
            collectionManager.removeFromCollection(ticketRemove);
            ResponseOutputer.appendln("Билет с таким id удалён");
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: " + getName());
        } catch (CollectionEmptyException exception) {
            ResponseOutputer.appendln("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appenderror("ID должен быть целым числом");
        } catch (EmptyException exception) {
            ResponseOutputer.appenderror("Билета с таким id нет");
        }
        return false;
    }
}
