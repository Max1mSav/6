package client.utility;


import exceptions.*;
import interaction.*;
import data.*;
import server.commands.TicketObj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Receives user requests.
 */
public class UserHelper {
    private final int maxRewriteAttempts = 1;

    private Scanner userScanner;
    private Stack<File> scriptStack = new Stack<>();
    private Stack<Scanner> scannerStack = new Stack<>();

    public UserHelper (Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Receives user input.
     *
     * @param serverResponseCode Last server's response code.
     * @return New request to server.
     */
    public Request handle(ResponseCode serverResponseCode) {
        String userInput;
        String[] userCommand;
        ProcessingCode processingCode;
        int rewriteAttempts = 0;
        try {
            do {
                try {
                    if (fileMode() && (serverResponseCode == ResponseCode.ERROR ||
                            serverResponseCode == ResponseCode.SERVER_EXIT))
                        throw new IncorrectInputScriptException();
                    while (fileMode() && !userScanner.hasNextLine()) {
                        userScanner.close();
                        userScanner = scannerStack.pop();
                        Outputer.println("Возвращаюсь к скрипту '" + scriptStack.pop().getName() + "'...");
                    }
                    if (fileMode()) {
                        userInput = userScanner.nextLine();
                        if (!userInput.isEmpty()) {
                            Outputer.print("$");
                            Outputer.println(userInput);
                        }
                    } else {
                        Outputer.print("$");
                        userInput = userScanner.nextLine();
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    Outputer.println();
                    Outputer.printerror("Произошла ошибка при вводе команды!");
                    userCommand = new String[]{"", ""};
                    rewriteAttempts++;
                    if (rewriteAttempts >= maxRewriteAttempts) {
                        Outputer.printerror("Превышено количество попыток ввода!");
                        System.exit(0);
                    }
                } catch (IncorrectInputScriptException e) {
                    Outputer.println("Неправильно введены данные для скрипта");
                }
                processingCode = processCommand(userCommand[0], userCommand[1]);
            } while (processingCode == ProcessingCode.ERROR && !fileMode() || userCommand[0].isEmpty());
            try {
                if (fileMode() && (serverResponseCode == ResponseCode.ERROR || processingCode == ProcessingCode.ERROR))
                    throw new IncorrectInputScriptException();
                switch (processingCode) {
                    case OBJECT:
                        TicketObj ticketAdd = generateTicketAdd();
                        return new Request(userCommand[0], userCommand[1], ticketAdd);
                    case UPDATE_OBJECT:
                        TicketObj ticketUpdate = generateTicketUpdate();
                        return new Request(userCommand[0], userCommand[1], ticketUpdate);
                    case SCRIPT:
                        File scriptFile = new File(userCommand[1]);
                        if (!scriptFile.exists()) throw new FileNotFoundException();
                        if (!scriptStack.isEmpty() && scriptStack.search(scriptFile) != -1)
                            throw new RecursionException();
                        scannerStack.push(userScanner);
                        scriptStack.push(scriptFile);
                        userScanner = new Scanner(scriptFile);
                        Outputer.println("Выполняю скрипт '" + scriptFile.getName() + "'...");
                        break;
                }
            } catch (FileNotFoundException exception) {
                Outputer.printerror("Файл со скриптом не найден!");
            } catch (RecursionException exception) {
                Outputer.printerror("Скрипты не могут вызываться рекурсивно!");
                throw new IncorrectInputScriptException();
            } catch (IncorrectInputScriptException e) {
                Outputer.println("Неправильно введены данные для скрипта");
            } catch (IncorrectInputException e) {
                Outputer.println("Неправильно введены данные");
            }
        } catch (IncorrectInputScriptException exception) {
            Outputer.printerror("Выполнение скрипта прервано!");
            while (!scannerStack.isEmpty()) {
                userScanner.close();
                userScanner = scannerStack.pop();
            }
            scriptStack.clear();
            return new Request();
        }
        return new Request(userCommand[0], userCommand[1]);
    }

    /**
     * Processes the entered command.
     *
     * @return Status of code.
     */
    private ProcessingCode processCommand(String command, String commandArgument) {
        try {
            switch (command) {
                case "":
                    return ProcessingCode.ERROR;
                case "help":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "info":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "show":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "add":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException("{element}");
                    return ProcessingCode.OBJECT;
                case "update":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<ID> {element}");
                    return ProcessingCode.UPDATE_OBJECT;
                case "remove_by_id":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<ID>");
                    break;
                case "clear":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "save":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "execute_script":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<file_name>");
                    return ProcessingCode.SCRIPT;
                case "exit":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "add_if_min":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException("{element}");
                    return ProcessingCode.OBJECT;
                case "remove_greater":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException("{element}");
                    return ProcessingCode.OBJECT;
                case "history":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "sum_of_health":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "max_by_melee_weapon":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "filter_by_weapon_type":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<weapon_type>");
                    break;
                case "server_exit":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                default:
                    Outputer.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
                    return ProcessingCode.ERROR;
            }
        } catch (CommandUsageException exception) {
            if (exception.getMessage() != null) command += " " + exception.getMessage();
            Outputer.println("Использование: '" + command + "'");
            return ProcessingCode.ERROR;
        }
        return ProcessingCode.OK;
    }

    /**
     * Generates marine to add.
     *
     * @return Marine to add.
     * @throws IncorrectInputException When something went wrong in script.
     */
    private TicketObj generateTicketAdd() throws IncorrectInputException {
        TicketChecker ticketChecker = new TicketChecker(userScanner);
        if (fileMode()) ticketChecker.setFileMode();
        return new TicketObj(
                ticketChecker.checkName(),
                ticketChecker.returnCoordinates(),
                ticketChecker.checkPrice(),
                ticketChecker.checkTicketType(),
                ticketChecker.returnPerson(),
                ticketChecker.checkDiscount()
        );
    }

    /**
     * Generates marine to update.
     *
     * @return Marine to update.
     * @throws IncorrectInputScriptException When something went wrong in script.
     */
    private TicketObj generateTicketUpdate() throws IncorrectInputException {
        TicketChecker ticketChecker = new TicketChecker(userScanner);
        if (fileMode()) ticketChecker.setFileMode();
        String name = ticketChecker.wantToChange("Хотите изменить название билета?") ?
                ticketChecker.checkName() : null;
        Coordinates coordinates = ticketChecker.wantToChange("Хотите изменить координаты билета?") ?
                ticketChecker.returnCoordinates() : null;
        double price = ticketChecker.wantToChange("Хотите изменить цену билета?") ?
                ticketChecker.checkPrice() : -1;
        int discount = ticketChecker.wantToChange("Хотите изменить скидку на билет?") ?
                ticketChecker.checkDiscount() : null;
        TicketType ticketType = ticketChecker.wantToChange("Хотите изменить тип билета?") ?
                ticketChecker.checkTicketType() : null;
        Person person = ticketChecker.wantToChange("Хотите изменить владельца билета?") ?
                ticketChecker.returnPerson() : null;
        return new TicketObj(
                name,
                coordinates,
                price,
                ticketType,
                person,
                discount
        );
    }

    /**
     * Checks if UserHandler is in file mode now.
     *
     * @return Is UserHandler in file mode now boolean.
     */
    private boolean fileMode() {
        return !scannerStack.isEmpty();
    }
}
