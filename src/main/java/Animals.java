import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Animals {

    private String endangered;
    private String name;
    private String health;
    private String age;
    private int id;

    public Animals(String name, String health, String age,String endangered){
        this.name = name;
        this.health = health;
        this.age = age;
        this.endangered=endangered;
    }

    public String getEndangered() {
        return endangered;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO Animals(name, health,age,endangered) VALUES (:name, :health, :age, :endangered)";
           this.id=(int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();

        }

    }
    public static List<Animals> all() {
        String sql = "SELECT * FROM Animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animals.class);
        }
    }
}
