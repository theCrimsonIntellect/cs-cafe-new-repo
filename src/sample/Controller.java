package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Controller {
    public static final int NUMBER_OF_CHAIRS = 8;

    private String text = "";
    private String url = "";
    private String name = "";
    private int customerCounter = 0;

    public class Chair
    {
        private ImageView chairPos;
        boolean occupied;
        int customerIndex;

        Chair(ImageView i)
        {
            chairPos = i;
            occupied = false;
            customerIndex = 0;
        }

        public void setImageOnChairPos(Image i)
        {
            chairPos.setImage(i);
        }

        public void setOccupied(boolean occupiedStatus)
        {
            occupied = occupiedStatus;
        }

        public void setCustomerIndex(int cIndex)
        {
            customerIndex = cIndex;
        }

        public boolean isOccupied()
        {
            return occupied;
        }

        public ImageView getChairPos()
        {
            return chairPos;
        }
    } // end of Chair class `````````````````

    private class Customer
    {
        //Instance Variables
        //add an instane variable called icon
        private String customer_name;
        private int customer_state;
        private int point_total;
        private long time_at_creation;
        private boolean has_menu;
        private boolean has_water;
        private boolean has_food;

//        private Image customerImage;
//        private String customerName;
//        private String imageURL;


        private ImageView customerPosition;


        //Constructor Declaration of Class
        //change customer class to that the customer class itself generates its own random state.
        public Customer()
        {

            //add a field called icon
            Random rand = new Random();
            this.customer_name = "cheese";
            this.customer_state = rand.nextInt(3 + 1);
            this.time_at_creation = System.currentTimeMillis();
            this.has_menu = false;
            this.has_water = false;
            this.has_food = false;
            this.point_total = 3;
            customerPosition = new ImageView();
            switch (this.customer_state)
            {
                case 1:
                    System.out.println("line 97");
                    this.customerPosition.setImage(new Image("upsetCustomer.png"));
                    break;
                case 2:
                    System.out.println("line 100");
                    this.customerPosition.setImage(new Image("normalCustomer.jpg"));
                    break;
                case 3:
                    System.out.println("line 104");
                    this.customerPosition.setImage(new Image("relaxedCustomer.png"));
                    break;
            }
        }

        //GETTERS-----------------------------------------------------------------------------------------------

        //returns customer name
        public String getName ()
        {
            return this.customer_name;
        }

        //returns the customer state as a string, depending on what number is passed to the constructor
        public String getCustomerState ()
        {
            if (this.customer_state == 1)
            {
                return "Hurried";
            } else if (this.customer_state == 2)
            {
                return "Normal";
            } else if (this.customer_state == 3)
            {
                return "Relaxed";
            }
            else
            {
                return "Invalid Customer Type -- Needs to be int between 1 and 3";
            }
        }

        //gets the image url
//        public String getImageURL()
//        {
//            return this.imageURL;
//        }

        public ImageView getCustomerPosition()
        {
            return customerPosition;
        }
        //returns the time of creation of customer in milliseconds
        public long getTimeAtCreation ()
        {
            return this.time_at_creation;
        }

        //returns state of has_menu
        public boolean isMenu ()
        {
            return this.has_menu;
        }

        //returns state of has_water
        public boolean isWater ()
        {
            return this.has_water;
        }

        //returns state of has_food
        public boolean isFood ()
        {
            return this.has_food;
        }

        //returns customer Image.jpg
//        public Image getCustomerImage ()
//        {
//            return this.customerImage;
//        }


        //SETTERS-----------------------------------------------------------------------------------------------

        //sets the state of has_menu
        public void setMenu ( boolean menu)
        {
            //the try-catch statement below checks to make sure a bool was entered
            try
            {
                this.has_menu = menu;
            } catch (IllegalArgumentException e)
            {
                System.out.println("Something went wrong - Non bool value input");
            }
        }

        //sets the state of has_water
        public void setWater ( boolean water)
        {
            //the try-catch statement below checks to make sure a bool was entered
            try
            {
                this.has_water = water;
            } catch (IllegalArgumentException e)
            {
                System.out.println("Something went wrong - Non bool value input");
            }
        }

        //set the state of has_food
        public void setFood ( boolean food)
        {
            //the try-catch statement below checks to make sure a bool was entered
            try
            {
                this.has_food = food;
            } catch (IllegalArgumentException e)
            {
                System.out.println("Something went wrong - Non bool value input");
            }
        }

//        //set the customer image (must be jpq file)
//        public void setCustomerImage (Image url)
//        {
//            this.customerImage = url;
//        }

        //HELPER METHODS----------------------------------------------------------------------------------------

        //take away points for not having menu on time
        public void menuPointReduction ()
        {
            if (!this.isMenu() && this.timeSinceCreation() > 10)
            {
                this.point_total = this.point_total - 1;
            }
        }

        //take away points for not having water on time
        public void waterPointReduction ()
        {
            if (!this.isWater() && this.timeSinceCreation() > 20)
            {
                this.point_total = this.point_total - 1;
            }
        }

        public void foodPointReduction ()
        {
            if (!this.isFood() && this.timeSinceCreation() > 30)
            {
                this.point_total = this.point_total - 1;
            }
        }

        //returns the time since creation in seconds
        public double timeSinceCreation ()
        {
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
        //@Override
        public void run ()
        {
            //set up a way to exit the while loop
            boolean IsCustomerThread = true;

            //three gates set up so that the customer class doesn't deduct points more than once.
            boolean firstGate = false;
            boolean secondGate = false;
            boolean thirdGate = false;

            String myName = this.customer_name;
            //System.out.println("Hello my name is " + myName);
            //System.out.println();

            //constantly check time since creation of customer in order to know what the customer needs next.
            while (IsCustomerThread)
            {
                double creation = this.timeSinceCreation();

                if (creation == 10.0 && !firstGate)
                {
                    //System.out.print(this.getName());
                    //System.out.println(": Squidward, I need a menu");
                    //System.out.println();
                    this.menuPointReduction();
                    firstGate = true;
                }
                if (creation == 20.0 && !secondGate)
                {
                    //System.out.print(this.getName());
                    //System.out.println(": Squidward, I need some water");
                    //System.out.println();
                    this.waterPointReduction();
                    secondGate = true;
                }
                if (creation == 30.0 && !thirdGate)
                {
                    //System.out.println(this.getName());
                    //System.out.println(": Squidward, I need some food");
                    //System.out.println();
                    this.foodPointReduction();
                    thirdGate = true;
                }
                if (creation == 50.0)
                {
                    //System.out.println(this.getName());
                    //System.out.println(": Alright, i'm gonna head out, later sponge bob.");
                    //System.out.println();
                    IsCustomerThread = false;
                }

            }
        }
    } // end of Customer class (inner class)

    private Customer customer;

    private Image image;

    private ImageView[] images = new ImageView[8];


    @FXML
    ImageView seat1;

    @FXML
    ImageView seat2;

    @FXML
    ImageView seat3;
    @FXML
    ImageView seat4;
    @FXML
    ImageView seat5;
    @FXML
    ImageView seat6;
    @FXML
    ImageView seat7;
    @FXML
    ImageView seat8;

    private Chair[] seatDeclaration()
    {
        Chair[] chairs = new Chair[NUMBER_OF_CHAIRS];

        images[0] = seat1;
        images[1] = seat2;
        images[2] = seat3;
        images[3] = seat4;
        images[4] = seat5;
        images[5] = seat6;
        images[6] = seat7;
        images[7] = seat8;
        for (int i = 0; i < chairs.length; i++)
        {
            Chair chair = new Chair(images[i]);
            chairs[i] = chair;

        }
//        chairs[2].setImageOnChairPos(new Image("relaxedCustomer.png"));
        return chairs;

    }

    // takes array of chairs and determine the first open chair
    private int firstAvailable(Chair[] chairs)
    {
        for (int i = 0; i < chairs.length; i++)
        {
            // checks boolean for occupied
            if (!chairs[i].isOccupied())
            {
                return i;
            }
        }
        return -99;
    }

    private void seatAssignment(Image danielImage, Chair[] chairs, int openSeatIndex)
    {
        chairs[openSeatIndex].setOccupied(true);
        chairs[openSeatIndex].getChairPos().setImage(danielImage);
    }


    @FXML
    private ImageView testing;

    @FXML
    private Label testingLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView foodServedImageView;

    @FXML
    private ImageView customerImageView;

    @FXML
    private ImageView secondCustomerImageView;

    private String custUrl;

    public void waterServed(ActionEvent e)
    {
//        String water = "Water Bottle";
        Image image = new Image("waterGlass.jpg");
//        imageView = new ImageView(image);
        imageView.setImage(image);

//        waterBottle.setText(water);
    }

    public void menuServed(ActionEvent e)
    {
        Image image = new Image("menuBook.jpg");
        imageView2.setImage(image);
    }

    public void foodServed(ActionEvent e)
    {

        foodServedImageView.setImage(new Image("foodServed.jpg"));
    }

//    @FXML
//    public void startTheGame( ActionEvent e)
//    {
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    while (true)
//                    {
//                        if (testingLabel.getText().trim().length() == 0)
//                            text = "Hell Yeahhhh";
//                        else
//                            text = "";
//
//                        Platform.runLater(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                testingLabel.setText(text);
//                            }
//                        });
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException ex) {}
//            }
//        }).start();
//    }

//    @FXML
//    public void start(ActionEvent e)
//    {
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    while (true)
//                    {
//                        if (customerImageView.getImage() == null)
//                            image = new Image("upsetFace.jpg");
//                        else
//                            image = null;
//
//                        Platform.runLater(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                customerImageView.setImage(image);
//                            }
//                        });
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException ex) { ex.printStackTrace(); }
//            }
//        }).start();
//
//    }

//    @FXML
//    public void createCustomerWithImage(ActionEvent e)
//    {
//
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    while (true)
//                    {
//                        if (/*customerCounter == 0 || */customerImageView.getImage() == null)
//                        {
//                            customer = new Customer();
////                            image = customer.getCustomerImage();
//                        } else
//                        {
//                            image = null;
//                        }
//                        Platform.runLater(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                secondCustomerImageView.setImage(image);
//                            }
//                        });
//
//                        Thread.sleep(1000);
//                    }
//                } catch (InterruptedException ex)
//                {
//                    ex.printStackTrace();
//                }
//            }
//        }).start();
//    }
//


    // this method is invoked when the start button is clicked

    /**
     *
     * @param event
     */
    @FXML
    public void theRealGameStarter(ActionEvent event)
    {
        // array of chair objects: including image and customer assignments, booleans for index
        Chair[] chairs = seatDeclaration();



        new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                // start time: game start time
                long startingTime = System.currentTimeMillis();
                System.out.println("Game start time: " + startingTime);

                try
                {
                    while (true)
                    {
                        System.out.println("Game loop");

                        Image customerUrl;
                        Customer daniel;
                        int openSeatIndex;

                        long currentTime = System.currentTimeMillis();
                        long elapsedTime = (currentTime - startingTime) / 1000;

//                        System.out.println("line 537");

                        if (elapsedTime % 2 == 0 && elapsedTime != 0)
                        {
//                            System.out.println("line 540");
                            // create new customer
                            // random generator in customer constructor generating random customer states
                            // 1: is "hurried"; 2: is "normal"; 3: is "relaxed"
                            // customer class is on line 81
                            daniel = new Customer();

                            // first available seat is a static controller class method
                            // in line 377
                            openSeatIndex = firstAvailable(chairs);
                            System.out.println(openSeatIndex);

                            // takes the customer image
                            customerUrl = daniel.getCustomerPosition().getImage();
//                            chairs[openSeatIndex].getChairPos().setImage(customerUrl);

                            // assigns the seat, places the customer image on the imageView container of the seat
                            seatAssignment(customerUrl, chairs,openSeatIndex);

//                             new thread updates the gui
                            Platform.runLater(new Runnable()
                            {
                                @Override
                                public void run()
                                {
//                                    seatAssignment(customerUrl, chairs,openSeatIndex);

                                    System.out.println("line 557");
//                                chairs[1].getChairPos().setImage(customerUrl);
                                    System.out.println("line 559");

                                }
                            });
                            System.out.println("line 544");


//                        chairs[1].getChairPos().setImage(new Image(daniel.getImageURL()));
                        }
//                    String url = daniel.getImageURL();
//                        Platform.runLater(new Runnable()
//                        {
//
//                            @Override
//                            public void run()
//                            {
//                                System.out.println("line 557");
////                                chairs[1].getChairPos().setImage(customerUrl);
//                                System.out.println("line 559");
//
//                            }
//                        });
                        Thread.sleep(500);
                    }

                }catch (InterruptedException e) {e.printStackTrace();}
            }
        }).start();
    }

}