import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;



    private String name; //Поле не может быть null

    public Car() {
        this.name = "default";
    }

    public Car(String name) {
        if (name != null) this.name = name;
        else this.name = "default";
    }
    public String getName() {
        return name;
    }
}