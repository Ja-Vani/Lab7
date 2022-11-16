import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private static final long serialVersionUID = 1L;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isRealHero() {
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public Long getImpactSpeed() {
        return impactSpeed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }

    public static long getStatId() {
        return statId;
    }

    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private Boolean hasToothpick; //Поле не может быть null
    private Long impactSpeed; //Поле не может быть null
    private WeaponType weaponType; //Поле не может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null
    private static long statId = 0;

    public HumanBeing() {
        this.id = statId;
        statId++;
        this.name = "Steav";
        this.coordinates = new Coordinates();
        this.realHero = false;
        this.hasToothpick = false;
        this.impactSpeed = Long.valueOf(0);
        this.weaponType = WeaponType.AXE;
        this.mood = Mood.CALM;
        this.car = null;
        this.creationDate = new Date();
    }

    public HumanBeing(String name, Coordinates coordinates, boolean realHero, Boolean hasToothpick, Long impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        if (name != null) this.name = name;
        else this.name = "Steav";
        if (coordinates != null) this.coordinates = coordinates;
        else this.coordinates = new Coordinates();
        this.realHero = realHero;
        if (hasToothpick != null) this.hasToothpick = hasToothpick;
        else hasToothpick = false;
        this.impactSpeed = impactSpeed;
        if (weaponType != null) this.weaponType = weaponType;
        else this.weaponType = WeaponType.AXE;
        if (mood != null) this.mood = mood;
        else this.mood = Mood.CALM;
        this.car = car;
        this.id = statId;
        statId++;
        this.creationDate = new Date();
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);
        String[] c;
        System.out.println("Введите имя");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c[0].equals("")) {
                System.out.println("Ошибка ввода имени. Попробуйте ещё раз");
            } else if (c.length == 1) {
                name = c[0];
                break;
            } else {
                System.out.println("Ошибка ввода имени. Попробуйте ещё раз");
            }
        }
        System.out.println("Введите координаты");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c.length == 2) {
                try {
                    Long d1 = Long.parseLong(c[0]);
                    Integer d2 = Integer.parseInt(c[1]);
                    coordinates = new Coordinates(d1, d2);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода, аргументы должны быть long и int. Попробуйте ещё раз");
                }
            } else
                System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
        System.out.println("Введите является ли он героем");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c.length == 1 && !c[0].equals("")) {
                try {
                    realHero = Boolean.parseBoolean(c[0]);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода, аргумент должны быть boolean. Попробуйте ещё раз");
                }
            } else System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
        System.out.println("Введите есть ли зубочистка");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c.length == 1 && !c[0].equals("")) {
                try {
                    hasToothpick = Boolean.parseBoolean(c[0]);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода, аргумент должны быть boolean. Попробуйте ещё раз");
                }
            } else System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
        System.out.println("Введите скорость");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c.length == 1) {
                try {
                    impactSpeed = Long.parseLong(c[0]);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода, аргумент должны быть long. Попробуйте ещё раз");
                }
            } else System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
        System.out.println("Введите оружие:\n HAMMER,\n AXE,\n SHOTGUN,\n KNIFE");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c.length == 1 && !c[0].equals("")) {
                try {
                    weaponType = WeaponType.valueOf(c[0]);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка ввода, аргумент должны быть Human.WeaponType. Попробуйте ещё раз");
                }
            } else System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
        System.out.println("Введите машину");
        String cars;
        while (true) {
            cars = scanner.nextLine();
            c = cars.split(" ");
            if (cars.equals("")) {
                car = null;
            } else if (c.length == 1) {
                car = new Car(c[0]);
                break;
            } else System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
        System.out.println("Введите настроение:\n LONGING,\n GLOOM,\n CALM,\n FRENZY");
        while (true) {
            c = scanner.nextLine().split(" ");
            if (c.length == 1 && !c[0].equals("")) {
                try {
                    mood = Mood.valueOf(c[0]);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка ввода, аргумент должны быть Human.Mood. Попробуйте ещё раз");
                }
            } else System.out.println("Ошибка ввода, неверное число аргументов. Попробуйте ещё раз");
        }
    }

    public int compareTo(HumanBeing h) {
        return h.name.compareTo(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return id == that.id && hasToothpick == that.hasToothpick && Double.compare(that.impactSpeed, impactSpeed) == 0 && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(realHero, that.realHero) && weaponType == that.weaponType && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }

    public long getId() {
        return id;
    }

    public long getImpact() {
        return impactSpeed;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getId() + getName() + getCoordinates().getX() + getCoordinates().getY() + getCreationDate() + isRealHero() +
                getHasToothpick() + getImpactSpeed() + getWeaponType() + getMood() + getCar().getName();
    }
}
