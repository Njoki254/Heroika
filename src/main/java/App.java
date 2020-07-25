
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static spark.Spark.get;

public class App {

    public static void main(String[] args) {


     //type “psvm + tab” to autocreate this
        staticFileLocation("/public");

        //Display homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Homepage.hbs");
        }, new HandlebarsTemplateEngine());
        //display hero profiles
        get("/profiles", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Heroes-Profile.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hero-form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());
        //display squad-form
        get("/squad-form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //display hero-form
        get("/squad-Details", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());
        //form ya kucreate
        get("/squads/new",(req,res)->{
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //To get all posts in one place
        get("/squad-store", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> squadInstance = Squad.getInstances();//code that is collecting all the instances of squad available
            model.put("squadInstance", squadInstance);
            return new ModelAndView(model, "squad-store.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hero-store", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Hero> heroInstance = Hero.getInstances();//code that is collecting all the instances of squad available
            model.put("heroInstance", heroInstance);
            return new ModelAndView(model, "hero-store.hbs");
        }, new HandlebarsTemplateEngine());


        //Code to retrieve user's input
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String squadName = request.queryParams("squadName");
            String squadCause = request.queryParams("squadCause");
            //String[] members = request.queryParamsValues("members"); important line for working with checkboxes
            String members= request.queryParams("members");
           // String heroName = request.queryParams("heroName");
           // String heroStrength= request.queryParams("heroStrength");
           // String heroWeakness = request.queryParams("heroWeakness");
            //int heroAge = parseInt(request.params("heroAge"));
           // Hero newHeroes = new Hero(heroName,heroAge,heroStrength,heroWeakness);

            Squad newSquad = new Squad(squadName, squadCause, members);//constructor
            //String members = request.queryParams("members");

           // model.put("newHeroes", newHeroes);
            model.put( "newSquad",newSquad);//stores the newSquad
            model.put("members", members);
            // model.put("members", members);
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String heroName = request.queryParams("heroName");
            String heroWeakness = request.queryParams("heroWeakness");
            //String[] members = request.queryParamsValues("members"); important line for working with checkboxes
            String heroStrength= request.queryParams("heroStrength");
            int heroAge= parseInt(request.queryParams("heroAge"));
            // String heroName = request.queryParams("heroName");
            // String heroStrength= request.queryParams("heroStrength");
            // String heroWeakness = request.queryParams("heroWeakness");
            //int heroAge = parseInt(request.params("heroAge"));
            // Hero newHeroes = new Hero(heroName,heroAge,heroStrength,heroWeakness);

           Hero newHero = new Hero(heroName, heroAge,heroStrength, heroWeakness);//constructor
            //String members = request.queryParams("members");

            // model.put("newHeroes", newHeroes);
            model.put( "newHero",newHero);//stores the newSquad

            // model.put("members", members);
            return new ModelAndView(model, "Successheroes.hbs");
        }, new HandlebarsTemplateEngine());

       /* post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String heroName = request.queryParams("heroName");
            String heroStrength= request.queryParams("heroStrength");
            String heroWeakness = request.queryParams("heroWeakness");
            int heroAge = parseInt(request.params("heroAge"));
            Hero newHeroes = new Hero(heroName,heroAge,heroStrength,heroWeakness);

           ;//constructor
            //String members = request.queryParams("members");

            model.put("newHeroes", newHeroes);//stores the newSquad
            // model.put("members", members);
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());*/

        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-store.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundHero = Squad.findSquadById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "Heroes-Profile.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        //post: process a form to update a squad
        get("/squads/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = parseInt(req.params("id"));
            Squad editSquad = Squad.findSquadById(idOfSquadToEdit);
            model.put("editSquad", editSquad)
            return new ModelAndView(model, "Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        get("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Squad editPost = Squad.findSquadById(idOfPostToEdit);
            model.put("editPost", editPost);
            return new ModelAndView(model, "Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("squadName");
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findSquadById(idOfPostToEdit);
            editSquad.update(newName); //don’t forget me
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clear();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad deleteSquad = Squad.findSquadById(idOfSquadToDelete); //use it to find post
            deleteSquad.deleteSquad();
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());

        //For Heroes
        post("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String heroName = req.queryParams("heroName");
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findSquadById(idOfPostToEdit);
            editSquad.update(heroName); //don’t forget me
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hero/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clear();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hero/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero deleteHero = Hero.findHeroById(idOfHeroToDelete); //use it to find post
            deleteHero.deleteHero();
            return new ModelAndView(model, "heroDeleteSuccess.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "heroDeleteSuccess.hbs");
        }, new HandlebarsTemplateEngine());

    }

}