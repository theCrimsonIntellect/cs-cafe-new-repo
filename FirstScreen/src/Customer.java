import acm.graphics.GImage;
import acm.util.RandomGenerator;

public class Customer {

    String customer_type;
    GImage icon;

    Customer(){

        this.setCustomerType();
        this.setCustomerIcon(this.customer_type);

    }

    public void setCustomerType(){
        RandomGenerator rgen = RandomGenerator.getInstance();
        int type_int = rgen.nextInt(1,3);
        switch (type_int) {
            case 1:
                this.customer_type = "harried";
                break;
            case 2:
                this.customer_type = "normal";
                break;
            case 3:
                this.customer_type = "relaxed";
                break;
        }
    }

    public void setCustomerIcon(String customer_type){

        switch (customer_type) {
            case "harried":
                this.icon = new GImage("harried.gif");
                this.icon.scale(0.65);
                break;
            case "normal":
                this.icon = new GImage("normal.gif");
                this.icon.scale(1.5);
                break;
            case "relaxed":
                this.icon = new GImage("relaxed.gif");
                this.icon.scale(0.25);
                break;
        }
    }

    public String getCustomerType(){
        return customer_type;
    }

    public GImage getIcon() {
        return icon;
    }
}
