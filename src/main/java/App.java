
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");

        //get: show new post form
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "models.Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form
        post("/squads/new", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();


            String content = req.queryParams("content");
            Squad newSquad= new Squad(content);
            model.put("squad", newSquad);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all posts
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads",squads);

            return new ModelAndView(model, "homepage.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "models.Squad-Details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            return new ModelAndView(model, "models.Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post
        post("/suads/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("content");
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            editSquad.update(newContent);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual post
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad deleteSquad = Squad.findById(idOfSquadToDelete); //use it to find post
            deleteSquad.deleteSquad();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all posts
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clearAllSquads();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/models.Squad-Form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "models.Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/profiles", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Heroes-Profile.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
