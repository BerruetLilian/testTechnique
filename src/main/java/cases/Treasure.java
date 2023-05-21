package cases;

import utils.Position;

public class Treasure extends AbstractCase {

    private int quantity;

    public Treasure(int x, int y,int quantity) {
        super(new Position(x, y));
        this.quantity = quantity;
    }

    public void decreaseQuantity(){
        if(quantity != 0){
            quantity -= 1;
        }
    }

    public int getQuantity(){
        return this.quantity;
    }

    @Override
    public String toString() {
        return "T("+this.quantity+")";
    }

}
