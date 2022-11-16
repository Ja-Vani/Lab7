import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;

    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private long x;
    private int y; //Максимальное значение поля: 361

    public Coordinates(long x, int y) {
        this.x = x;
        if (y <= 361) this.y = y;
        else this.y = 0;
    }

    public Coordinates() {
        this.x = 0;
        this.y = 0;
    }
}