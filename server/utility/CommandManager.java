package server.utility;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import server.commands.Command;
import exceptions.EmptyException;

/**
 * Manager of all commands
 */

public class CommandManager {

    private final int COMMAND_HISTORY_SIZE = 8;
    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addElementCommand;
    private Command updateIdCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exit;
    private Command executeScriptCommand;
    private Command addIfMaxCommand;
    private Command removeGreaterCommand;
    private Command historyCommand;
    private Command filterContainsName;
    private Command filterLessPrice;
    private Command discountInAscendingOrder;
    private Command serverExitCommand;

    private ReadWriteLock historyLocker = new ReentrantReadWriteLock();
    private ReadWriteLock collectionLocker = new ReentrantReadWriteLock();

    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command addElementCommand,
        Command updateIdCommand, Command removeByIdCommand, Command clearCommand, Command exit, Command addIfMaxCommand,
                          Command removeGreaterCommand, Command historyCommand, Command filterContainsName,
                          Command filterLessPrice, Command discountInAscendingOrder, Command saveCommand,
                          Command executeScriptCommand, Command serverExitCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addElementCommand = addElementCommand;
        this.updateIdCommand = updateIdCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.exit = exit;
        this.addIfMaxCommand = addIfMaxCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.historyCommand = historyCommand;
        this.filterContainsName = filterContainsName;
        this.filterLessPrice = filterLessPrice;
        this.discountInAscendingOrder = discountInAscendingOrder;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.serverExitCommand = serverExitCommand;
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addElementCommand);
        commands.add(updateIdCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeScriptCommand);
        commands.add(exit);
        commands.add(addIfMaxCommand);
        commands.add(removeGreaterCommand);
        commands.add(historyCommand);
        commands.add(filterContainsName);
        commands.add(filterLessPrice);
        commands.add(discountInAscendingOrder);
        commands.add(serverExitCommand);
    }


    /**
     * @return The command history.
     */
    public String[] getCommandHistory() {
        return commandHistory;
    }

    /**
     * @return List of commands.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Adds command to command history.
     */
    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    /* public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    } */

    public boolean help(String argument, Object objArgument) {
        if (helpCommand.execute(argument, objArgument)) {
            for (Command command : commands) {
                System.out.printf("%-37s%-1s%n", command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    public boolean info(String argument, Object objArgument) {
        return infoCommand.execute(argument, objArgument);
    }

    public boolean show(String argument, Object objArgument) {
        return showCommand.execute(argument, objArgument);
    }

    public boolean add(String argument, Object objArgument) {
        return addElementCommand.execute(argument, objArgument);
    }

    public boolean update(String argument, Object objArgument) {
        return updateIdCommand.execute(argument, objArgument);
    }

    public boolean removeById(String argument, Object objArgument) {
        return removeByIdCommand.execute(argument, objArgument);
    }

    public boolean clear(String argument, Object objArgument) {
        return clearCommand.execute(argument, objArgument);
    }

    public boolean save(String argument, Object objArgument) {
        return saveCommand.execute(argument, objArgument);
    }

    public boolean exit(String argument, Object objArgument) {
        return exit.execute(argument, objArgument);
    }

    public boolean executeScript(String argument, Object objArgument) {
        return executeScriptCommand.execute(argument, objArgument);
    }

    public boolean addIfMax(String argument, Object objArgument) {
        return addIfMaxCommand.execute(argument, objArgument);
    }

    public boolean removeGreater(String argument, Object objArgument) {
        return removeGreaterCommand.execute(argument, objArgument);
    }

    public boolean filterContainsName(String argument, Object objArgument) {
        return filterContainsName.execute(argument, objArgument);
    }
    public boolean filterLessPrice(String argument, Object objArgument) {
        return filterLessPrice.execute(argument, objArgument);
    }
    public boolean discountInAscendingOrder(String argument, Object objArgument) {
        return discountInAscendingOrder.execute(argument, objArgument);
    }

    public boolean history(String argument, Object objArgument) {
        if (historyCommand.execute(argument, objArgument)) {
            try {
                if (commandHistory.length == 0) throw new EmptyException();

                System.out.println("Последние использованные команды:");
                for (int i = 0; i < commandHistory.length; i++) {
                    if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
                }
                return true;
            } catch (EmptyException exception) {
                System.out.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }

    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }
    public boolean serverExit(String stringArgument, Object objectArgument) {
        return serverExitCommand.execute(stringArgument, objectArgument);
    }
}