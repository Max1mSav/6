package main;
import client.utility.TicketChecker;
import server.commands.*;
import server.FileHelper;
import server.utility.CollectionManager;
import server.utility.CommandManager;

import java.io.IOException;
import java.util.*;

/**
 * The main programme.
 */

public class Main {
    public static final String S = "> ";

    public static void main(String[] args) throws IOException {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "LABA";
            TicketChecker ticketChecker = new TicketChecker(userScanner);
            FileHelper fileHelper = new FileHelper(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileHelper);
            CommandManager commandManager = new CommandManager(new HelpCommand(), new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager), new AddElementCommand(collectionManager, ticketChecker),
                    new UpdateIdCommand(collectionManager, ticketChecker), new RemoveById(collectionManager),
                    new Clear(collectionManager), new Exit(), new AddIfMax(collectionManager, ticketChecker),
                    new RemoveGreater(collectionManager, ticketChecker), new History(), new FilterContainsName(collectionManager),
                    new FilterLessPrice(collectionManager, ticketChecker), new DiscountInAscendingOrder(collectionManager, ticketChecker),
                    new SaveCommand(collectionManager), new ExecuteScriptCommand());
            commandManager.help("");
            System.out.println("");
            ConsoleOutput console = new ConsoleOutput(commandManager, userScanner, ticketChecker);
            console.interactiveMode();
        }
    }
}

