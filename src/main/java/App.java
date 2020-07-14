
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static spark.Spark.get;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
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


        //Code to retrieve user's input
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String squadName = request.queryParams("squadName");
            String squadCause = request.queryParams("squadCause");
            //String[] members = request.queryParamsValues("members"); important line for working with checkboxes
            String members= request.queryParams("members");
            String heroName = request.queryParams("heroName");
            String heroStrength= request.queryParams("heroStrength");
            String heroWeakness = request.queryParams("heroWeakness");
            int heroAge = parseInt(request.params("heroAge"));
            Hero newHeroes = new Hero(heroName,heroAge,heroStrength,heroWeakness);

            Squad newSquad = new Squad(squadName, squadCause, members);//constructor
            //String members = request.queryParams("members");

            model.put("newHeroes", newHeroes);
            model.put( "newSquad",newSquad);//stores the newSquad
            model.put("members", members);
            // model.put("members", members);
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/squads/new", (request, response) -> {
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
        }, new HandlebarsTemplateEngine());
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clear();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-store.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        //post: process a form to update a squad
        post("/posts/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newName= req.queryParams("name");
            String newCause= req.queryParams("cause");
            String newMember= req.queryParams("member");
            int idOfSquadToEdit = parseInt(req.params("id"));
            Squad editSquad = Squad.findSquadById(idOfSquadToEdit);
            editSquad.update(newName,newCause, newMember );
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findSquadById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Hero foundHero = Hero.findHeroById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
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
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad deleteSquad = Squad.findSquadById(idOfSquadToDelete); //use it to find post
            deleteSquad.deleteSquad();
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            Squad editPost = Squad.findSquadById(idOfPostToEdit);
            model.put("editPost", editPost);
            return new ModelAndView(model, "hero-Form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String heroName = req.queryParams("heroName");
            String heroStrength= req.queryParams("heroStrength");
            String heroWeakness = req.queryParams("heroWeakness");
            int heroAge = parseInt(req.params("heroAge"));
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findHeroById(idOfHeroToEdit);
            editHero.update(heroName,heroAge,heroStrength,heroWeakness); //don’t forget me
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero deleteHero = Hero.findHeroById(idOfHeroToDelete); //use it to find post
            deleteHero.deleteHero();
            return new ModelAndView(model, "Success.hbs");
        }, new HandlebarsTemplateEngine());

        /*//To display all objects created
        get("/", (req, res) -> {
            //just for testing - make two new objects so we have something to retrieve
            Rectangle rectangle = new Rectangle(3,2);
            Rectangle otherRectangle = new Rectangle(12, 12);

            Map<String, ArrayList<Rectangle>> model = new HashMap<>();
            ArrayList myRectangleArrayList = Rectangle.getAll();
            model.put("myRectangles", myRectangleArrayList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form
        post("/success", (req, res) -> { //URL to make new post on POST route
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