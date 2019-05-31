import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;


public class StartButton extends GraphicsProgram {

    public static final int NUMBER_OF_PLAYERS = 8;
    public static final double TABLE_LENGTH = 360;
    public static final double TABLE_WIDTH = 120;
    public static final double CHAIR_LENGTH = 60;
    public static final double CHAIR_WIDTH = 20;

    public static final int PLACEMAT_RADIUS = 10;

    public static final double TABLE_X = 200;
    public static final double TABLE_Y = 200;

    public static final int NO_SEAT_AVAILABLE = -99;

    public static final double QUEUE_X = 10;
    public static final double QUEUE_Y = 10;

    CustomerLinkedList<Customer> customer_list = new CustomerLinkedList<>();
    Chair[] chair_array = new Chair[NUMBER_OF_PLAYERS];

    //public GLabel label;

    public void run(){

        //setLayout(new GridLayout(1,2));
        //GLabel label = new GLabel("Ready to Begin", 10, 10);
       // add(label);
        //add(new JButton("Start"), NORTH);
        //addActionListeners();
        setupGameBoard();
        runGame();

       //
    }
/*
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();



    }*/

    public void setupGameBoard(){

        createTable();
        createChairs();
        createPlaceSettings();
    }



    public void runGame (){

        long startTime;
        startTime = System.currentTimeMillis();
        long elapsedTime = getElapsedTime(startTime);
        int chair_index;
        int customer_index;



        while (elapsedTime <= 85000) {
            System.out.println("time" + elapsedTime);
            chair_index = firstAvailableSeat();
            customer_index = generateCustomer();
            if (chair_index != NO_SEAT_AVAILABLE){
                assignSeat(customer_index, chair_index);
            }
            else {
                moveOut (customer_index);
            }
            pause(1000);
           //long time = System.currentTimeMillis();



           // System.out.println ("first available chair is" + chair_index);

            elapsedTime = getElapsedTime(startTime);


        }
        System.exit(0);
            //Customer retrieved_customer = customer_list.getCustomer(5);
        //    System.out.println("index: 5"+ retrieved_customer.getCustomerType());

    }

    public void createTable(){
        add(new GRect(TABLE_X, TABLE_Y, TABLE_LENGTH, TABLE_WIDTH));
    }

    public void createChairs(){

        double startingPosition_X = TABLE_X + TABLE_LENGTH/NUMBER_OF_PLAYERS - CHAIR_LENGTH/2;
        double startingPosition_Y = TABLE_Y - 2* CHAIR_WIDTH;
        for (int i = 0; i < (NUMBER_OF_PLAYERS/2); i++){
            chair_array[i] = new Chair(startingPosition_X, startingPosition_Y);
            add(chair_array[i].shape);
            chair_array[i].shape.setFilled(true);
            chair_array[i].shape.setFillColor(chair_array[i].getColor());
            startingPosition_X += TABLE_LENGTH/(NUMBER_OF_PLAYERS/2);
        };

        startingPosition_X = TABLE_X + TABLE_LENGTH/NUMBER_OF_PLAYERS - CHAIR_LENGTH/2;
        startingPosition_Y = TABLE_Y + TABLE_WIDTH + CHAIR_WIDTH;
        for (int i = NUMBER_OF_PLAYERS/2; i < NUMBER_OF_PLAYERS; i++){
            chair_array[i] = new Chair(startingPosition_X, startingPosition_Y);
            add(chair_array[i].shape);
            chair_array[i].shape.setFilled(true);
            chair_array[i].shape.setFillColor(chair_array[i].getColor());
            startingPosition_X += TABLE_LENGTH/(NUMBER_OF_PLAYERS/2);
        };
    }

    public void createPlaceSettings(){

        double startingPosition_X = TABLE_X + TABLE_LENGTH/NUMBER_OF_PLAYERS - PLACEMAT_RADIUS;
        double startingPosition_Y = TABLE_Y + PLACEMAT_RADIUS;
        double placemat_diameter = 2 * PLACEMAT_RADIUS;
        for (int i = 0; i < (NUMBER_OF_PLAYERS/2); i++){
            GOval placemat = new GOval(startingPosition_X, startingPosition_Y, placemat_diameter, placemat_diameter);
            add(placemat);
            placemat.setFilled(true);
            placemat.setFillColor(Color.GREEN);
            startingPosition_X += TABLE_LENGTH/(NUMBER_OF_PLAYERS/2);
        };

        startingPosition_X = TABLE_X + TABLE_LENGTH/NUMBER_OF_PLAYERS - PLACEMAT_RADIUS;
        startingPosition_Y = TABLE_Y + TABLE_WIDTH - 3*PLACEMAT_RADIUS;
        for (int i = 0; i < (NUMBER_OF_PLAYERS/2); i++){
            GOval placemat = new GOval(startingPosition_X, startingPosition_Y, placemat_diameter, placemat_diameter);
            add(placemat);
            placemat.setFilled(true);
            placemat.setFillColor(Color.GREEN);
            startingPosition_X += TABLE_LENGTH/(NUMBER_OF_PLAYERS/2);
        };
    }

    public int generateCustomer (){

        int customer_index;
        Customer next_customer = new Customer ();
        System.out.println(customer_list.size);
        System.out.println(next_customer.customer_type);
        customer_index = customer_list.add(customer_list.getSize()+1, next_customer);
        add(customer_list.getCustomer(customer_index).getIcon(), QUEUE_X, QUEUE_Y);
        pause(3000);
        return customer_index;

    }

    public long getElapsedTime(long startTime){
        long elapsedTime;
        elapsedTime = System.currentTimeMillis() - startTime;
        return elapsedTime;
    }

    public int firstAvailableSeat(){
        int index = 0;
        while (index < NUMBER_OF_PLAYERS && chair_array[index].getOccupancy() != false){
            index++;
        }
        if (index == NUMBER_OF_PLAYERS) return NO_SEAT_AVAILABLE;
        else return index;

    }

    public void assignSeat(int customer_index, int chair_index){

        System.out.println("customer_index: " + customer_index + "chair_index" + chair_index);
        chair_array[chair_index].setChairOccupancy(customer_index);
        String customer_type = customer_list.getCustomer(customer_index).getCustomerType();
        System.out.println(customer_type);

        double image_width = customer_list.getCustomer(customer_index).icon.getWidth();
        double image_height = customer_list.getCustomer(customer_index).icon.getHeight();

        if (chair_index < NUMBER_OF_PLAYERS/2){

            double dx = ((chair_array[chair_index].X_coordinate + CHAIR_WIDTH/2) - (QUEUE_X + image_width/2 ))/100;
            double dy = ((chair_array[chair_index].Y_coordinate) - (QUEUE_Y  + image_height))/100;
            for (int step = 0; step < 100; step++){
                customer_list.getCustomer(customer_index).icon.move(dx, dy);
                pause(25);
            }
            pause(1000);
            remove(customer_list.getCustomer(customer_index).icon);
        }

        else {
            double new_position_x =  TABLE_X/3 + (2/3) *QUEUE_X;
            double dx = (new_position_x - QUEUE_X)/100;
            double new_position_y = getHeight() - image_height - 20;
            double dy = (new_position_y - QUEUE_Y)/100;
            for (int step = 0; step < 100; step++){
                customer_list.getCustomer(customer_index).icon.move(dx, dy);
                pause(25);
            }
            dx = ((chair_array[chair_index].X_coordinate + CHAIR_WIDTH/2) - (new_position_x + image_width/2))/100;
            dy = ((chair_array[chair_index].Y_coordinate) + CHAIR_WIDTH - new_position_y)/100;
            for (int step = 0; step < 100; step++){
                customer_list.getCustomer(customer_index).icon.move(dx, dy);
                pause(25);
            }
            pause(1000);
            remove(customer_list.getCustomer(customer_index).icon);
        }
        chair_array[chair_index].setChairColor(customer_type);
    }

    public void moveOut (int customer_index){
        System.out.println("customer_index in moveOut: " + customer_index);

        double image_width = customer_list.getCustomer(customer_index).icon.getWidth();
        double image_height = customer_list.getCustomer(customer_index).icon.getHeight();

        double dx = (getWidth() - (QUEUE_X + image_width))/100;
        double dy = 0;
        for (int step = 0; step < 100; step++){
            customer_list.getCustomer(customer_index).icon.move(dx, dy);
            pause(25);
        }
        pause(100);
        remove(customer_list.getCustomer(customer_index).icon);

    }
}
