package data;

import java.time.LocalDateTime;

public class Ticket {
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле может быть null, Значение поля должно быть больше 0
    private int discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    public Ticket(long id, String name, Coordinates coordinates, LocalDateTime creationDate,
                  Double price, int discount, TicketType type, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.type = type;
        this.person = person;
    }

    /**
     * @return All information about each field of ticket.
     */

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        String information = "";
        information += "Билет №" + id;
        information += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        information += "\n Имя: " + name;
        information += "\n Местоположение: " + coordinates;
        information += "\n Цена: " + price;
        information += "\n Скидка: " + discount;
        information += "\n Тип билета: " + type;
        information += "\n День рождения покупателя: " + person.getBirthday();
        information += "\n Рост покупателя: " + person.getHeight();
        information += "\n Высота покупателя: " + person.getWeight();
        information += "\n Локация покупателя X: " + person.getLocation().getX() + " Y:" + person.getLocation().getX() +
                " Z:"+ person.getLocation().getX();
        return information;
    }

    public int compareTo(Ticket Obj) {
        return id.compareTo(Obj.getId());
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + discount + person.hashCode() + price.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ticket) {
            Ticket ticket = (Ticket) obj;
            return name.equals(ticket.getName()) && coordinates.equals(ticket.getCoordinates()) &&
                    (discount == ticket.getDiscount()) && person.equals(ticket.getPerson()) &&
                    price.equals(ticket.getPrice()) && type.equals(ticket.getType());
        }
        return false;
    }

}
