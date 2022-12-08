package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidPositionException;


public class Position {
    public static final int Y_MIN = 0;
    public static final int Y_MAX = 200;

    private int x;

    private int y;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Position(int x, int y) throws InvalidPositionException{
        if(y < Y_MIN ){
            throw new InvalidPositionException("y is below Y_MIN");
        }
        if(y > Y_MAX){
            throw new InvalidPositionException("y if above Y_MAX");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public BoundlessPosition subtract(Position other){
        int x_sub = this.x - other.x;
        int y_sub = this.y - other.y;
        return new BoundlessPosition(x_sub, y_sub);
    }

}
