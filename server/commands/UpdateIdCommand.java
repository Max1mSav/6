package server.commands;

import client.utility.TicketChecker;
import data.Coordinates;
import data.Person;
import data.Ticket;
import data.TicketType;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Update by id.
 */

public class UpdateIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public UpdateIdCommand(CollectionManager collectionManager) {
        super("update", "<ID> {element}","обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (argument.isEmpty() || objArgument == null) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();

            Long id = Long.parseLong(argument);
            if (id <= 0) throw new NumberFormatException();
            Ticket ticket = collectionManager.getById(id);
            if (ticket == null) throw new EmptyException();

            TicketObj ticketObj = (TicketObj) objArgument;

            String name = ticketObj.getName() == null ? ticket.getName() : ticketObj.getName();
            Coordinates coordinates = ticketObj.getCoordinates() == null ? ticket.getCoordinates() : ticketObj.getCoordinates();
            LocalDateTime creationDate = ticket.getCreationDate();
            double price = ticketObj.getPrice() == -1 ? ticket.getPrice() : ticketObj.getPrice();
            TicketType ticketType = ticketObj.getTicketType() == null ? ticket.getType() : ticketObj.getTicketType();
            int discount = ticketObj.getDiscount() == -1 ? ticket.getDiscount() : ticketObj.getDiscount();
            Person person = ticketObj.getPerson() == null ? ticket.getPerson() : ticketObj.getPerson();

            collectionManager.deleteFromCollection(ticket);
            collectionManager.addToCollection(new Ticket(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    price,
                    discount,
                    ticketType,
                    person
            ));
            ResponseOutputer.appendln("Билет успешно изменен!");
            return true;

            System.out.println("Изменения загружены");
            return true;
            } catch(IncorrectInputException exception){
                ResponseOutputer.appendln("Использование: " + getName());
            } catch(CollectionEmptyException exception){
                ResponseOutputer.appendln("Коллекция пуста!");
            } catch(WrongArgumentInputException exception){
                ResponseOutputer.appenderror("ID должен быть целым числом!");
            } catch(EmptyException exception){
                ResponseOutputer.appenderror("Такого билета с таким id нет");
        }
        return false;
    }
}