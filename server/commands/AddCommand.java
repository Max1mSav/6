package server.commands;

import data.Ticket;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Add element to collection
 */

public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public AddCommand (CollectionManager collectionManager) {
        super("add", "{element}", "Добавление элемента в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty() || objArgument == null) throw new WrongArgumentInputException();
            TicketObj ticketObj = (TicketObj) objArgument;
            collectionManager.addToCollection(new Ticket(
                    collectionManager.createNextId(),
                    ticketObj.getName(),
                    ticketObj.getCoordinates(),
                    LocalDateTime.now(),
                    ticketObj.getPrice(),
                    ticketObj.getDiscount(),
                    ticketObj.getTicketType(),
                    ticketObj.getPerson()
            ));
            ResponseOutputer.appendln("Солдат успешно добавлен!");
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ResponseOutputer.appenderror("Переданный клиентом объект неверен!");
        }
        return false;
    }
}
