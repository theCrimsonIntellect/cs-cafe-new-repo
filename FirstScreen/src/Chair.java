import acm.graphics.GRect;

import java.awt.*;

public class Chair {
    double X_coordinate;
    double Y_coordinate;
    GRect shape;
    Color color;
    boolean occupied;
    int customerIndex;



    Chair(double xCoordinate, double yCoordinate){
        X_coordinate = xCoordinate;
        Y_coordinate = yCoordinate;
        shape = new GRect(X_coordinate, Y_coordinate, StartButton.CHAIR_LENGTH, StartButton.CHAIR_WIDTH);
        this.shape.setFilled(true);
        color = Color.GREEN;
        occupied = false;
        int customerIndex = 0;
    }

    public Color getColor(){
        return this.color;
    }

    public boolean getOccupancy(){
        return occupied;
    }

    public void setChairOccupancy(int customer_index){
        customerIndex = customer_index;
        occupied = true;
    }

    public void setChairColor(String customer_type){
        switch (customer_type) {
            case "harried":
                this.shape.setFillColor(Color.RED);
                break;
            case "normal":
                this.shape.setFillColor(Color.YELLOW);
                break;
            case "relaxed":
                this.shape.setFillColor(Color.CYAN);
                break;
        }
    }

}
