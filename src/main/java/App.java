
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");

        //Display homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Homepage.hbs");
        }, new HandlebarsTemplateEngine());
        get("/profiles", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Heroes-Profile.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squad-form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/Hero-Details", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Hero-Details.hbs");
        }, new HandlebarsTemplateEngine());
        //form ya kucreate
        get("/squads/new",(req,res)->{
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squad-store/:id", (request, response) -> {//colon is collecting id.
            Map<String, Object> model = new HashMap<String, Object>();
            //code that is collecting all the instances of squad available
            int deleteSquadId = Integer.parseInt(request.queryParams("id"));
            Squad.deleteSquad(deleteSquadId); //how to call a method from class
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        get("/squad-store", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> squadInstance = Squad.getInstances();//code that is collecting all the instances of squad available
            model.put("squadInstance", squadInstance);
            return new ModelAndView(model, "squad-store.hbs");
        }, new HandlebarsTemplateEngine());

        //Code to retrieve user's input
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String squadName = request.queryParams("squadName");
            String squadCause = request.queryParams("squadCause");
            Squad newSquad = new Squad(squadName, squadCause);//constructor
            //String members = request.queryParams("members");


            model.put( "newSquad",newSquad);//stores the newSquad

           // model.put("members", members);
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form
       /* post("/success", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();


            String content = req.queryParams("content");
            Squad newSquad= new Squad(content);
            model.put("squad", newSquad);
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());*/
        //code to alter stored info

        //post: process new post form
        //code for grabbing all posts
       /* get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads= Squad.getAll();
            model.put("squads", squads);

            return new ModelAndView(model, "homepage.hbs");
        }, new HandlebarsTemplateEngine());*/
    }

}