
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/newAnimal", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/newAnimal",(req,res)-> {
            Map<String, Object> model = new HashMap<>();
            String name= req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            String endangered = req.queryParams("endangered");
           Animals newAnimal = new Animals(name,health,age,endangered);
           newAnimal.save();
            req.session().attribute("item",newAnimal);
            model.put("item",req.session().attribute("item"));
            System.out.println(newAnimal);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/list", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animals> animalsList= Animals.all();
            model.put("animalsList", animalsList);
            return new ModelAndView(model, "successm.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new",(req,res)-> {
            Map<String, Object> model = new HashMap<>();
            int animal_id=Integer.parseInt(req.queryParams("animal_id"));
            String rangerName = req.queryParams("rangerName");
            String location = req.queryParams("location");
            Sightings newSightings = new Sightings(rangerName,location,animal_id);
            newSightings .save();
            req.session().attribute("item",newSightings );
            model.put("item",req.session().attribute("item"));
            System.out.println(newSightings );
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
    }


}
