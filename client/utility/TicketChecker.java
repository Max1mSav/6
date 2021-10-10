package client.utility;
import data.Coordinates;
import data.Location;
import data.Person;
import data.TicketType;
import exceptions.EmptyException;
import exceptions.IncorrectInputException;
import exceptions.NullInputException;
import exceptions.WrongArgumentInputException;
import main.Main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Help to control that values are correct.
 */

public class TicketChecker {
    private final int MIN_DISCOUNT = 1;
    private final int MAX_DISCOUNT = 100;
    private final double MIN_PRICE = 0;
    private final double MIN_HEIGHT = 0;
    private final float MIN_WEIGHT = 0;
    private final float MIN_Y = -588;

    private Scanner userScanner;
    private boolean fileMode;

    public TicketChecker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    public String checkName() throws IncorrectInputException {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя: ");
                System.out.print(Main.S);
                name = userScanner.nextLine().trim();
                if (fileMode) System.out.println(name);
                if (name.equals("")) throw new EmptyException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Имя не распознано!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (EmptyException exception) {
                System.err.println("Имя не может быть пустым");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            }
        }
        return name;
    }

    public float checkXCoordinates() throws IncorrectInputException {
        String str;
        Float x;

        while (true) {
            try {
                System.out.println("Введите координату X:");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                x = Float.parseFloat(str);
                if (x.equals(null)) throw new NullInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Координата X не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Координата X должна быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NullInputException e) {
                System.err.println("Координата X не может быть null");
                System.out.print(Main.S);
            }
        }
        return x;
    }

    public float checkYCoordinates() throws IncorrectInputException {
        String str;
        Float y;

        while (true) {
            try {
                System.out.println("Введите координату Y > " + MIN_Y + ": ");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                y = Float.parseFloat(str);
                if (y < MIN_Y) throw new WrongArgumentInputException();
                if (y.equals(null)) throw new NullInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Координата Y не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Координата Y должна быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (WrongArgumentInputException exception) {
                System.err.println("Y должен превышать значение равное = " + MIN_Y);
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NullInputException e) {
                System.err.println("Координата Y не может быть null");
                System.out.print(Main.S);
            }
        }
        return y;
    }

    public Coordinates returnCoordinates() throws IncorrectInputException {
        Float x;
        Float y;
        x = checkXCoordinates();
        y = checkYCoordinates();
        return new Coordinates(x, y);
    }

    public Double checkPrice() throws IncorrectInputException {
        String str;
        Double price;

        while (true) {
            try {
                System.out.println("Введите цену > " + MIN_PRICE + ": ");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                price = Double.parseDouble(str);
                if (price < MIN_PRICE) throw new WrongArgumentInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Цена не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Цена должна быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (WrongArgumentInputException exception) {
                System.err.println("Цена должна превышать значение равное = " + MIN_PRICE);
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return price;
    }

    public int checkDiscount() throws IncorrectInputException {
        String str;
        int discount;

        while (true) {
            try {
                System.out.println("Введите скидку в диапозоне от " + MIN_DISCOUNT + " до " + MAX_DISCOUNT + ":");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                discount = Integer.parseInt(str);
                if (discount > MAX_DISCOUNT || discount < MIN_DISCOUNT) throw new WrongArgumentInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Скидка не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Скидка должна быть целым числом!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (WrongArgumentInputException exception) {
                System.err.println("Скидка должна быть в диапозоне от " + MIN_DISCOUNT + " до " + MAX_DISCOUNT + ":");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return discount;
    }

    public TicketType checkTicketType() throws IncorrectInputException {
        String str;
        TicketType ticketType;

        while (true) {
            try {
                System.out.println("Список возможных типов: " + TicketType.nameList());
                System.out.println("Введите тип: ");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                if (str.equals(null) || str.equals("")) ticketType = null;
                else{
                    ticketType = TicketType.valueOf(str.toUpperCase());
                }
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Тип не распознан!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (IllegalArgumentException exception) {
                System.err.println("Такого типа билетов нет!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return ticketType;
    }

    public Date  checkBirthday() throws IncorrectInputException {
        String str;
        String pattern = "dd/MM/yyyy";
        Date birthday;
        //LocalDate birthday;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy h:mm:ss");

        while (true) {
            try {
                System.out.println("Введите дату дня рождения в фомате dd/MM/YYYY HH:mm:ss: ");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                //LocalDate localDate = LocalDate.parse(str, formatter);
                birthday = dateFormat.parse(str);
                if (birthday.equals(null)) throw new WrongArgumentInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("дата не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (ParseException e) {
                System.err.println("Ошибка, не правильно введены даты");
                System.out.print(Main.S);
            } catch (WrongArgumentInputException e) {
                System.err.println("Дата не может быть NULL");
                System.out.print(Main.S);
            }
        }
        return birthday;
    }

    public Double checkHeight() throws IncorrectInputException {
        String str;
        Double height;

        while (true) {
            try {
                System.out.println("Введите рост человека > " + MIN_HEIGHT + ": ");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                height = Double.parseDouble(str);
                if (height < MIN_HEIGHT) throw new WrongArgumentInputException();
                if (height.equals(null)) throw new NullInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Рост не распознан!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Рост должен быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (WrongArgumentInputException exception) {
                System.err.println("Рост не должен быть ниже значение равное = " + MIN_HEIGHT);
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NullInputException e) {
                System.err.println("Рост не может быть null");
                System.out.print(Main.S);
            }
        }
        return height;
    }

    public float checkWeight() throws IncorrectInputException {
        String str;
        Float weight;

        while (true) {
            try {
                System.out.println("Введите ширину человека > " + MIN_WEIGHT + ": ");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                weight = Float.parseFloat(str);
                if (weight < MIN_HEIGHT) throw new WrongArgumentInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Цена не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Цена должна быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (WrongArgumentInputException exception) {
                System.err.println("Цена должна превышать значение равное = " + MIN_Y);
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weight;
    }

    public double checkXLocation() throws IncorrectInputException {
        String str;
        Double x;

        while (true) {
            try {
                System.out.println("Введите локацию X:");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                x = Double.parseDouble(str);
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Координата X не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Координата X должна быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    public long checkYLocation() throws IncorrectInputException {
        String str;
        Long y;

        while (true) {
            try {
                System.out.println("Введите локацию Y:");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                y = Long.parseLong(str);
                if (y.equals(null)) throw new NullInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Координата Y не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Координата Y должна быть целым числом!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NullInputException e) {
                System.err.println("Координата Y не может быть null");
                System.out.print(Main.S);
            }
        }
        return y;
    }

    public double checkZLocation() throws IncorrectInputException {
        String str;
        Double z;

        while (true) {
            try {
                System.out.println("Введите локацию Z:");
                System.out.print(Main.S);
                str = userScanner.nextLine().trim();
                if (fileMode) System.out.println(str);
                z = Double.parseDouble(str);
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Координата Z не распознана!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NumberFormatException exception) {
                System.err.println("Координата Z должна быть числом c плавающей запятой!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (NullPointerException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return z;
    }

    public Location returnLocation() throws IncorrectInputException {
        double x;
        Long y;
        double z;
        x = checkXLocation();
        y = checkYLocation();
        z = checkZLocation();
        return  new Location(x, y, z);
    }

    public Person returnPerson() throws IncorrectInputException {
        Date birthday;
        Double height;
        float weight;
        Location location;
        birthday = checkBirthday();
        height = checkHeight();
        weight = checkWeight();
        location = returnLocation();
        return new Person(birthday, height, weight, location);
    }

    public boolean wantToChange (String str) throws IncorrectInputException {
        String question = str + " : 'yes' or 'no':";
        String answer;
        while (true) {
            try {
                System.out.println(question);
                System.out.print(Main.S);
                answer = userScanner.nextLine().trim();
                if (fileMode) System.out.println(answer);
                if (!answer.equals("yes") && !answer.equals("no")) throw new WrongArgumentInputException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Ответ не распознан!");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (WrongArgumentInputException exception) {
                System.err.println("Нужно ответить: 'yes' или 'no'");
                System.out.print(Main.S);
                if (fileMode) throw new IncorrectInputException();
            } catch (IllegalStateException exception) {
                System.err.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("yes")) ? true : false;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public Scanner getUserScanner() {
        return userScanner;
    }

    public void setFileMode() {
        fileMode = true;
    }

    public void setUserMode() {
        fileMode = false;
    }
}
