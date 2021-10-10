package data;

import java.util.Date;

public class Person {
    private Date birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private float weight; //Значение поля должно быть больше 0
    private Location location; //Поле не может быть null

    public Person(Date birthday, Double height, float weight, Location location) {
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.location = location;
    }

    /**
     * @return Date of birthday.
     */

    public Date getBirthday() {
        return birthday;
    }

    /**
     * @return Height.
     */

    public Double getHeight() {
        return height;
    }

    /**
     * @return Weight.
     */

    public float getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public double getXLocation() {
        return location.getX();
    }

    public Long getYLocation() {
        return location.getY();
    }
    public double getZLocation() {
        return location.getZ();
    }

    @Override
    public int hashCode() {
        return birthday.hashCode() + height.hashCode() + (int) weight + location.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return birthday.equals(person.getBirthday()) && height.equals(person.getHeight())
                    && weight == person.getWeight() && location.equals(person.getLocation());
        }
        return false;
    }
}

