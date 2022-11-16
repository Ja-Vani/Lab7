import java.sql.*;
import java.sql.Date;

public class Collection {
    private Connection con;
    private Date creationDate = new Date(new java.util.Date().getTime());

    public Collection() {
    }

    public Collection(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
            String query =
                    "CREATE SEQUENCE IF NOT EXISTS GenerateNumberSequence " +
                            "START WITH 1 " +
                            "INCREMENT BY 1;";
            try {
                PreparedStatement pst = con.prepareStatement(query);
                pst.execute();
            } catch (SQLException ex) {
                System.out.println("Ошибка при попытке создания sequence");
                System.exit(1);
            }
            query = "CREATE TABLE IF NOT EXISTS Users(" +
                    "Login VARCHAR(50) NOT NULL" +
                    ", Password VARCHAR(50) NOT NULL);";
            try {
                PreparedStatement pst = con.prepareStatement(query);
                pst.execute();
            } catch (SQLException ex) {
                System.out.println("Ошибка при попытке создания user таблицы");
                System.exit(1);
            }
            query = "CREATE TABLE IF NOT EXISTS humanBeing( " +
                    "Id INT NOT NULL DEFAULT nextval('GenerateNumberSequence'), " +
                    " n VARCHAR(50) NOT NULL," +
                    " CoordinatX INT NOT NULL," +
                    " CoordinatY INT NOT NULL," +
                    " Date DATE," +
                    " RealHero BOOLEAN NOT NULL, " +
                    " hasToothpick BOOLEAN NOT NULL, " +
                    " impactSpeed INT NOT NULL, " +
                    " WeaponType VARCHAR(50) NOT NULL," +
                    " Mood VARCHAR(50) NOT NULL," +
                    " Car VARCHAR (50)," +
                    " Key INT NOT NULL," +
                    " login VARCHAR (50) NOT NULL );";
            try {
                PreparedStatement pst = con.prepareStatement(query);
                pst.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Ошибка при попытке создания таблицы");
                System.exit(1);
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка при подключении к БД");
            System.exit(1);
        }
    }

    public void save() {
        System.out.println("Сохранения автоматические");
    }

    public String insert(Integer k, HumanBeing humanBeing, User user) {
        String query = "INSERT INTO humanBeing( n, CoordinatX, " +
                "CoordinatY, Date, RealHero, hasToothpick, impactSpeed," +
                " WeaponType, Mood, Car, Key, login)" +
                " VALUES (  " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, humanBeing.getName());
            pst.setInt(2, (int) humanBeing.getCoordinates().getX());
            pst.setInt(3, humanBeing.getCoordinates().getY());
            pst.setDate(4, new Date(new java.util.Date().getTime()));
            pst.setBoolean(5, humanBeing.isRealHero());
            pst.setBoolean(6, humanBeing.getHasToothpick());
            pst.setInt(7, (int) humanBeing.getImpactSpeed().longValue());
            pst.setString(8, humanBeing.getWeaponType().toString());
            pst.setString(9, humanBeing.getMood().toString());
            pst.setString(10, humanBeing.getCar().getName());
            pst.setInt(11, k);
            pst.setString(12, user.getLogin());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка SQL";
        }
        return "";
    }

    public String pushId(Long id, HumanBeing humanBeing, User user) {
        String query = "UPDATE humanBeing SET " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) WHERE Id = ?, login = ?;";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, (int) id.longValue());
            pst.setString(2, humanBeing.getName());
            pst.setInt(3, (int) humanBeing.getCoordinates().getX());
            pst.setInt(4, humanBeing.getCoordinates().getY());
            pst.setDate(5, new Date(new java.util.Date().getTime()));
            pst.setBoolean(6, humanBeing.isRealHero());
            pst.setBoolean(7, humanBeing.getHasToothpick());
            pst.setInt(8, (int) humanBeing.getImpactSpeed().longValue());
            pst.setString(9, humanBeing.getWeaponType().toString());
            pst.setString(10, humanBeing.getMood().toString());
            pst.setString(11, humanBeing.getCar().getName());
            pst.setInt(12, (int) (Math.random() * 1000000));
            pst.setString(13, user.getLogin());
            pst.setInt(14, (int) id.longValue());
            pst.setString(15, user.getLogin());
            pst.execute();
        } catch (SQLException e) {
            return ("Ошибка SQL");
        }
        return "Объект успешно заменён";
    }

    public String ril(Integer k, HumanBeing humanBeing, User user) {
        String query = "UPDATE humanBeing( n, CoordinatX " +
                " CoordinatY, Date, RealHero, hasToothpick, impactSpeed, " +
                " WeaponType, Mood, Car, Key, login) SET VALUES ( " +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE login = ?, Key = ?;";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, humanBeing.getName());
            pst.setInt(2, (int) humanBeing.getCoordinates().getX());
            pst.setInt(3, humanBeing.getCoordinates().getY());
            pst.setDate(4, new Date(new java.util.Date().getTime()));
            pst.setBoolean(5, humanBeing.isRealHero());
            pst.setBoolean(6, humanBeing.getHasToothpick());
            pst.setInt(7, (int) humanBeing.getImpactSpeed().longValue());
            pst.setString(8, humanBeing.getWeaponType().toString());
            pst.setString(9, humanBeing.getMood().toString());
            pst.setString(10, humanBeing.getCar().getName());
            pst.setInt(11, k);
            pst.setString(12, user.getLogin());
            pst.setString(13, user.getLogin());
            pst.setInt(14, k);
            pst.execute();
        } catch (SQLException e) {
            return ("Ошибка SQL");
        }
        return "Объект успешно заменён";
    }

    public String remove(Integer k, User user) {
        String query = "DELETE FROM humanBeing WHERE Key = ?, login = ?;";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, k);
            pst.setString(2, user.getLogin());
            pst.execute();
            return "Эллемент убран удачно";
        } catch (UnsupportedOperationException | SQLException e) {
            return ("Не существовало элемента с данным ключом");
        }
    }

    public String clear(User user) {
        String query = "DELETE FROM humanBeing WHERE login = ?;";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getLogin());
            pst.execute();
        } catch (SQLException e) {
            return "Ошибка SQL";
        }
        return "Коллекция очищена";
    }


    public String getInfo() {
        String info;
        int count = 0;
        String query = "SELECT COUNT (*) FROM humanBeing;";
        ResultSet result;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            result = pst.executeQuery();
            while (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            return "Ошибка SQL";
        }

        info = "HumanBeing" + "\n" +
                creationDate.toString() + "\n" + count;
        return info;
    }

    public String getElements() {
        String query = "SELECT * FROM humanBeing;";
        ResultSet result;
        String res = "";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            result = pst.executeQuery();
        } catch (SQLException e) {
            return "Ошибка SQL";
        }
        res += "| " + "Id" + " | " +
                "Name" + " | " +
                "X" + " | " +
                "Y" + " | " +
                "Date" + " | " +
                "RealHero" + " | " +
                "hasToothpick" + " | " +
                "impactSpeed" + " | " +
                "WeaponType" + " | " +
                "Mood" + " | " +
                "Car" + " | " +
                "Key" + " | " +
                "User" + " |\n";
        try {
            while (result.next()) {
                res += "| " + result.getString(1) + " | " +
                        result.getString(2) + " | " +
                        result.getString(3) + " | " +
                        result.getString(4) + " | " +
                        result.getString(5) + " | " +
                        result.getString(6) + " | " +
                        result.getString(7) + " | " +
                        result.getString(8) + " | " +
                        result.getString(9) + " | " +
                        result.getString(10) + " | " +
                        result.getString(11) + " | " +
                        result.getString(12) + " | " +
                        result.getString(13) + " |\n";
            }
        } catch (SQLException e) {
            return "Ошибка SQL";
        }
        return res;
    }

    public double average() {
        String query = "SELECT impactSpeed FROM humanBeing;";
        ResultSet result;
        int count = 0;
        double res = 0;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            result = pst.executeQuery();
        } catch (SQLException e) {
            return 0;
        }
        try {
            while (result.next()) {
                count++;
                res += result.getDouble(1);
            }
        } catch (SQLException e) {
            return 0;
        }
        if (count > 0) return res / count;
        else return 0;
    }

    public String filterName(String name) {
        String query = "SELECT Name FROM humanBeing WHERE n > ?;";
        ResultSet result;
        String res = "";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            result = pst.executeQuery();
        } catch (SQLException e) {
            return "";
        }
        try {
            while (result.next()) {
                res += result.getString(1) + " ";
            }
        } catch (SQLException e) {
            return "";
        }
        return res;
    }

    public String filterImpact(long impact) {
        String query = "SELECT impactSpeed FROM humanBeing WHERE impactSpeed > ?;";
        ResultSet result;
        String res = "";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, (int) impact);
            result = pst.executeQuery();
        } catch (SQLException e) {
            return "";
        }
        try {
            while (result.next()) {
                res += result.getDouble(1) + " ";
            }
        } catch (SQLException e) {
            return "";
        }
        return res;
    }

    public String register(User user) {
        String query = "SELECT  COUNT(*) FROM Users WHERE Login = ?;";
        ResultSet result;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getLogin());
            result = pst.executeQuery();
        } catch (SQLException e) {
            return "SQL Ошибка1";
        }
        String query2 = "INSERT INTO Users VALUES (?, ?);";
        try {
            int count = 0;
            while (result.next()) {
                count = result.getInt(1);
            }
            if (count == 0) {
                PreparedStatement pst = con.prepareStatement(query2);
                pst.setString(1, user.getLogin());
                pst.setString(2, user.getPassword());
                pst.execute();
                return "Новый пользователь создан удачно";
            } else {
                return "Имя пользователя занято";
            }
        } catch (SQLException e) {
            return "SQL Ошибка2";
        }
    }

    public String login(User user) {
        String query = "SELECT Password FROM Users WHERE Login = ?;";
        ResultSet result;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getLogin());
            result = pst.executeQuery();
        } catch (SQLException e) {
            return "SQL Ошибка1";
        }
        try {
            String buff = "";
            while (result.next()) {
                buff = result.getString("Password");
            }
            if (buff.equals(user.getPassword())) {
                return "Авторизация успешна";
            } else {
                return "Неверный пароль или логин";
            }
        } catch (SQLException e) {
            return "SQL Ошибка2";
        }
    }
}