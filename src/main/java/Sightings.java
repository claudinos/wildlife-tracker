import org.sql2o.Connection;

import java.util.List;

public class Sightings {
    private String ranger;
    private String location;
    private int animal_id;
    private String date_sighted;
    private int id;

    public Sightings (String ranger, String location, int animal_id){
        this.ranger = ranger;
        this.location = location;
        this.animal_id = animal_id;
    }

    public String getRanger(){
        return ranger;
    }

    public String getLocation(){
        return location;
    }

    public int getAnimalId(){
        return animal_id;
    }

    public int getId(){
        return id;
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO Sightings (ranger,location,animal_id) VALUES (:ranger, :location, :animal_id)";
            con.createQuery(sql)
                    .addParameter("ranger", this.ranger)
                    .addParameter("location", this.location)
                    .addParameter("animal_id", this.animal_id)
                    .executeUpdate()
                    .getKey();

        }

    }
    public static List< Sightings > all() {
        String sql = "SELECT * FROM  Sightings ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch( Sightings .class);
        }
    }
}
