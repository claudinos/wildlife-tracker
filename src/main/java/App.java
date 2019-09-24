
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
//get("/newAnimal",(req, res) ->{
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "animal-form.hbs");
//        }, new HandlebarsTemplateEngine());
        get("/", (req, res) -> {
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
            return new ModelAndView(model,"successm.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }


}
