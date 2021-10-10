package data;

public class Location {
    private double x;
    private Long y; //Поле не может быть null
    private double z;

    public Location(double x, Long y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return X-coordinate.
     */

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return Y-coordinate.
     */

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    /**
     * @return Z-coordinate.
     */

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Location) {
            Location location = (Location) obj;
            return x == location.getX() && y.equals(location.getY()) && z == location.getZ();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + (int) x + (int) z;
    }
}
