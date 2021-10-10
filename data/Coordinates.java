package data;

public class Coordinates {
    private Float x; //Поле не может быть null
    private Float y; //Значение поля должно быть больше -588, Поле не может быть null

    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */
    public Float getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return x == coordinatesObj.getX() && y.equals(coordinatesObj.getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + x.hashCode();
    }
}
