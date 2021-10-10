package server.commands;

import client.utility.TicketChecker;
import data.Ticket;
import exceptions.WrongAmountOfElementsException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Add element if it is maximum
 */

public class AddIfMax extends AbstractCommand {
    private CollectionManager collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max", "{element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            TicketObj ticketObj = (TicketObj) objArgument;
            Ticket ticketAdd = new Ticket(
                    collectionManager.createNextId(),
                    ticketObj.getName(),
                    ticketObj.getCoordinates(),
                    LocalDateTime.now(),
                    ticketObj.getPrice(),
                    ticketObj.getDiscount(),
                    ticketObj.getTicketType(),
                    ticketObj.getPerson()
            );
            if (collectionManager.collectionSize() == 0 || ticketAdd.compareTo(collectionManager.getLastElement()) < 0) {
                collectionManager.addToCollection(ticketAdd);
                ResponseOutputer.appendln("Билет добавлен");
                return true;
            } else ResponseOutputer.appenderror("Значение ,билета меньше, чем значение наибольшего из билетов!");
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ResponseOutputer.appenderror("Переданный клиентом объект неверен!");
        }
        return false;
    }
}
