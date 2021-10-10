package server.commands;

import client.utility.TicketChecker;
import data.Ticket;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Remove all greater tickets.
 */

public class RemoveGreater extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater {element}", "","удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument, Object objArgument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            TicketObj ticketObj = (TicketObj) objArgument;
            Ticket ticketToFind = new Ticket(collectionManager.createNextId(), ticketObj.getName(),
                    ticketObj.getCoordinates(), LocalDateTime.now(), ticketObj.getPrice(),
                    ticketObj.getDiscount(), ticketObj.getTicketType(), ticketObj.getPerson());
            Ticket ticketToDelete = collectionManager.getByValue(ticketToFind);
            if (ticketToDelete == null) throw new EmptyException();
            collectionManager.removeGreater(ticketToFind);
            ResponseOutputer.appendln("Билет удалён");
            return true;
        } catch (WrongArgumentInputException exception) {
            ResponseOutputer.appendln("Использование: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionEmptyException exception) {
            ResponseOutputer.appenderror("Коллекция пуста");
        } catch (EmptyException exception) {
            ResponseOutputer.appenderror("Коллекция пуста");
        /*} catch (IncorrectInputException e) {
            ResponseOutputer.appenderror("Неверно введены данные");*/
        }
        return false;
    }
}
