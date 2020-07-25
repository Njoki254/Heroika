
import DAO.Sql2oHero;
import DAO.Sql2oSquad;
import DAO.SquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import org.sql2o.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static spark.Spark.get;

public class App {

    public static void main(String[] args) {
        //instantiating DAOs
        String connectionString = "jdbc:postgresql://localhost:5432/heroika"; //connect to todolist, not todolist_test!
        Sql2oHero heroDbStorage; //heroDao is just a variable storing, can call it anything
        Sql2oSquad squadDbStorage;
        Connection conn;
        Sql2o sql2o = new Sql2o(connectionString, "njoki254", "cookie254" );// store data into this account, asking app to connect postgre to heroika datab, pass username and password
        heroDbStorage = new Sql2oHero(sql2o);//sql20hero stored into heroDao
        squadDbStorage = new Sql2oSquad(sql2o);
        conn = sql2o.open(); //now opening database, connecting
        // sqlsquad is a string, suadDao is the variable
        //trying to connect database to
       // Sql2o is inbuilt into system
        //

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
            return new ModelAndView(model,"models.Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());

        //To get all posts in one place
        get("/squad-store", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> squadInstance = squadDbStorage.getAllSquads();//code that is collecting all the instances of squad available, retrieving all squads to display
            model.put("squadInstance", squadInstance);
            return new ModelAndView(model, "squad-store.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hero-store", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Hero> heroInstance = heroDbStorage.getAllHeroes();//code that is collecting all the instances of squad available/ heroDao is just a variable
            model.put("heroInstance", heroInstance);
            return new ModelAndView(model, "hero-store.hbs");
        }, new HandlebarsTemplateEngine());


        //Code to retrieve user's input
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String squadName = request.queryParams("squadName");
            String squadCause = request.queryParams("squadCause");
            int squadSize =parseInt(request.queryParams("squadSize"));
            Squad newSquad = new Squad(squadName, squadCause, squadSize);
            squadDbStorage.add(newSquad);//adding new method to the database
            //String members = request.queryParams("members");

           // model.put("newHeroes", newHeroes);
            model.put( "newSquad",newSquad);//stores the newSquad
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
            Hero newHero = new Hero(heroName, heroAge,heroStrength, heroWeakness);
            heroDbStorage.add(newHero);

           //constructor
            //String members = request.queryParams("members");

            // model.put("newHeroes", newHeroes);
            model.put( "newHero",newHero);//stores the newSquad

            // model.put("members", members);
            return new ModelAndView(model, "Successheroes.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = squadDbStorage.findById(idOfSquadToFind);
            List<Hero> squadHeroes = squadDbStorage.getAllHeroesBySquad(idOfSquadToFind);
            model.put("squad", foundSquad);
            model.put("squadHeroes", squadHeroes);//add it to model for template to display
            return new ModelAndView(model, "squad-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = parseInt(req.params(":id")); //pull id - must match route segment
            Hero foundHero = heroDbStorage.findById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "Heroes-Profile.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        //post: process a form to update a squad
        get("/squads/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = parseInt(req.params("id"));
            Squad editSquad = squadDbStorage.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            return new ModelAndView(model, "models.Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/squads/:id", (req,res)-> {
            Map<String, Object> model = new HashMap<>();
            String squadName = req.queryParams("squadName");
            String squadCause = req.queryParams("squadCause");
            int squadSize =parseInt(req.queryParams("squadSize"));
            int id = Integer.parseInt(req.params("id"));
            squadDbStorage.updateSquad(squadName, squadCause, squadSize, id);//adding new method to the database
            //String members = request.queryParams("members");

            // model.put("newHeroes", newHeroes);
            res.redirect("/squad-store");//redirecting to root that will display changes
            // model.put("members", members);
            return null;
        }, new HandlebarsTemplateEngine());
        post("/heroes/:id", (req,res)-> {
            Map<String, Object> model = new HashMap<>();
            String heroName = req.queryParams("heroName");
            String heroWeakness = req.queryParams("heroWeakness");
            //String[] members = request.queryParamsValues("members"); important line for working with checkboxes
            String heroStrength= req.queryParams("heroStrength");
            int heroAge= parseInt(req.queryParams("heroAge"));
            int id = Integer.parseInt(req.params("id"));
            heroDbStorage.updateHero(id,heroName, heroAge,heroStrength, heroWeakness);

            //constructor
            //String members = request.queryParams("members");

            // model.put("newHeroes", newHeroes);
           //stores the newSquad
            res.redirect("/squad-store");
            // model.put("members", members);
            return new ModelAndView(model, "Successheroes.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = parseInt(req.params("id"));
            Hero editHero = heroDbStorage.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "models.Squad-Form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());



        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            heroDbStorage.deleteAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squad/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad deleteSquad = squadDbStorage.findById(idOfSquadToDelete); //use it to find post
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());

        //For Heroes

    }

}