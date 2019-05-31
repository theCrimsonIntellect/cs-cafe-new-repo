import java.util.*;
import java.lang.*;


//Class Declaration
public class Customer implements Runnable {


    //Instance Variables
    //add an instane variable called icon
    private String customer_name;
    private int customer_state;
    private int point_total;
    private long time_at_creation;
    private boolean has_menu;
    private boolean has_water;
    private boolean has_food;

    //Constructor Declaration of Class
    //change customer class to that the customer class itself generates its own random state.
    public Customer(String customer_name){

        //add a field called icon
        Random rand = new Random();
        this.customer_name = customer_name;
        this.customer_state = rand.nextInt(3 + 1);
        this.time_at_creation = System.currentTimeMillis();
        this.has_menu = false;
        this.has_water = false;
        this.has_food = false;
        this.point_total = 3;

    }

    //GETTERS-----------------------------------------------------------------------------------------------

    //returns customer name
    public String getName(){
        return this.customer_name;
    }

    //returns the customer state as a string, depending on what number is passed to the constructor
    public String getCustomerState(){
        if (this.customer_state == 1){
            return "Hurried";
        }
        else if (this.customer_state == 2){
            return "Normal";
        }
        else if (this.customer_state == 3){
            return "Relaxed";
        }
        else {
            return "Invalid Customer Type -- Needs to be int between 1 and 3";
        }
    }

    //returns the time of creation of customer in milliseconds
    public long getTimeAtCreation(){
        return this.time_at_creation;
    }

    //returns state of has_menu
    public boolean isMenu(){
        return this.has_menu;
    }

    //returns state of has_water
    public boolean isWater(){
        return this.has_water;
    }

    //returns state of has_food
    public boolean isFood(){
        return this.has_food;
    }

    //returns the number of points customer instance has
    public int getPointTotal() { return this.point_total;}


    //SETTERS-----------------------------------------------------------------------------------------------

    //sets the state of has_menu
    public void setMenu(boolean menu){
        //the try-catch statement below checks to make sure a bool was entered
        try {
            this.has_menu = menu;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Something went wrong - Non bool value input");
        }
    }

    //sets the state of has_water
    public void setWater(boolean water) {
        //the try-catch statement below checks to make sure a bool was entered
        try {
            this.has_water = water;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Something went wrong - Non bool value input");
        }
    }

    //set the state of has_food
    public void setFood(boolean food) {
        //the try-catch statement below checks to make sure a bool was entered
        try {
            this.has_food = food;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Something went wrong - Non bool value input");
        }
    }

    //HELPER METHODS----------------------------------------------------------------------------------------

    //take away points for not having menu on time
    public void menuPointReduction(){
        if (!this.isMenu() && this.timeSinceCreation() > 10){
            this.point_total = this.point_total - 1;
        }
    }

    //take away points for not having water on time
    public void waterPointReduction(){
        if (!this.isWater() && this.timeSinceCreation() > 20){
            this.point_total = this.point_total - 1;
        }
    }

    public void foodPointReduction(){
        if (!this.isFood() && this.timeSinceCreation() > 30){
            this.point_total = this.point_total - 1;
        }
    }



    //returns the time since creation in seconds
    public double timeSinceCreation() {
        float elapsed_time;
        long current_time = System.currentTimeMillis();

        /*
        The following equation takes the time when the method is called, and subtracts from it the time
        recorded when the customer was created. Since all of this is in milliseconds we then divide
        by 1000 to get our result in seconds.
         */
        elapsed_time = (current_time - this.time_at_creation) / 1000f;

        return elapsed_time;

    }

    //Our customer's thread execution;
    @Override
    public void run() {
        //set up a way to exit the while loop
        boolean IsCustomerThread = true;

        //three gates set up so that the customer class doesn't deduct points more than once.
        boolean firstGate = false;
        boolean secondGate = false;
        boolean thirdGate = false;

        /*
        String myName = this.customer_name;
        System.out.println("Hello my name is " + myName);
        System.out.println();
        */

        //constantly check time since creation of customer in order to know what the customer needs next.
        while (IsCustomerThread) {
            double creation = this.timeSinceCreation();

            //customer should have a menu before 10 seconds
            if (creation == 10.0 && !firstGate) {
                /*
                System.out.print(this.getName());
                System.out.println(": Squidward, I need a menu");
                System.out.println();
                */

                //takes away a point if the customer doesn't have a menu after 10 seconds
                if (!this.isMenu()){
                    this.menuPointReduction();
                }
                firstGate = true;
            }
            if (creation == 20.0 && !secondGate) {
                /*
                System.out.print(this.getName());
                System.out.println(": Squidward, I need some water");
                System.out.println();
                */

                //takes away a point if the customer doesn't hae water after 20 seconds
                if (!this.isWater()) {
                    this.waterPointReduction();
                }
                secondGate = true;
            }
            if (creation == 30.0 && !thirdGate) {
                /*
                //System.out.println(this.getName());
                //System.out.println(": Squidward, I need some food");
                //System.out.println();
                */

                //takes away a point if the customer doesn't have food after 30 seconds
                if (!this.isFood()) {
                    this.foodPointReduction();
                }
                thirdGate = true;
            }
            if (creation == 50.0) {
                /*
                //System.out.println(this.getName());
                //System.out.println(": Alright, i'm gonna head out, later sponge bob.");
                //System.out.println();
                */

                //Exits the while loop
                IsCustomerThread = false;
            }

        }
    }
}
