package server.commands;
import data.*;
import java.io.Serializable;

/**
 * Class for get Ticket value.
 */
public class TicketObj implements Serializable {
    private String name;
    private Coordinates coordinates;
    private Double price;
    private TicketType type;
    private Person person;
    private int discount;

    public TicketObj(String name, Coordinates coordinates, Double price,
                     TicketType type, Person person, int discount) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
        this.discount = discount;
    }

    /**
     * @return Name of the Ticket.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the Ticket.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return price of the Ticket.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Type of the Ticket.
     */
    public TicketType getTicketType() {
        return type;
    }

    /**
     * @return Person of the Ticket.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return Discount of the Ticket.
     */
    public int getDiscount() {
        return discount;
    }


    @Override
    public String toString() {
        String info = "";
        info += "Билет";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Цена: " + price;
        info += "\n Тип билета: " + type;
        info += "\n Владелец билета: " + person;
        info += "\n Скидка на билет: " + discount;
        return info;
    }

    @Override
    public int hashCode() {
        return (int) (name.hashCode() + coordinates.hashCode() + (double) price + type.hashCode() + person.hashCode() +
                discount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ticket) {
            Ticket ticketObj = (Ticket) obj;
            return name.equals(ticketObj.getName()) && coordinates.equals(ticketObj.getCoordinates()) &&
                    (price == ticketObj.getPrice()) && (type == ticketObj.getType()) &&
                    (person == ticketObj.getPerson()) && (discount == ticketObj.getDiscount());
        }
        return false;
    }
}

