package server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.commands.*;
import utility.CollectionFileManager;
import server.utility.CollectionManager;
import server.utility.CommandManager;
import server.utility.RequestHandler;

/**
 * Main server class. Creates all server instances.
 *
 * @author Sviridov Dmitry and Orlov Egor.
 */
public class Starter {
    public static final int PORT = 1821;
    public static final int CONNECTION_TIMEOUT = 60 * 1000;
    public static final String ENV_VARIABLE = "LABA";
    public static Logger logger = LogManager.getLogger("ServerLogger");

    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper(ENV_VARIABLE);
        CollectionManager collectionManager = new CollectionManager(fileHelper);
        CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager),
                new UpdateIdCommand(collectionManager),
                new RemoveById(collectionManager),
                new Clear(collectionManager),
                new SaveCommand(collectionManager),
                new Exit(),
                new ExecuteScriptCommand(),
                new AddIfMax(collectionManager),
                new RemoveGreater(collectionManager),
                new History(),
                new FilterContainsName(collectionManager),
                new FilterLessPrice(collectionManager),
                new DiscountInAscendingOrder(collectionManager),
                new ServerExitCommand()
        );
        RequestHandler requestHandler = new RequestHandler(commandManager);
        Server server = new Server(PORT, CONNECTION_TIMEOUT, requestHandler);
        server.run();
    }
}
