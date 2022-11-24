package ldts.terrarialike.model;

public class ItemStack { // não esquecer para verificar o tipo de item que está a ser adicionado pode influenciar aspetos
    final private Item item;
    final private static short maxquantity = 64;
    private short quantity;

    // checks if he can add the quantity to the stack


    // constructor
    public ItemStack(Item item, short quantity) throws Exception {
        this.item = item;

        if(quantity <= maxquantity){
            this.quantity = quantity;
        }else{
            throw new Exception("Invalid quantity");
        }
    }

      public ItemStack(Item item) throws Exception {
            this(item, (short)1);
        }

        //add quantity to the stack
    public void add(short quantity) throws Exception { // adds any quantity to the stack
            if(quantity <= maxquantity){
                this.quantity += quantity;
            }else{
                throw new Exception("Invalid quantity");
            }
        }
        public void add() throws Exception {  // add 1
            add((short)1);
        }
        //remove quantity from the stack
    public void remove(short quantity) throws Exception { // removes any quantity from the stack
            if(this.quantity - quantity >= 0){
                this.quantity -= quantity;
            }else{
                throw new Exception("Invalid quantity");
            }
        }

        public void remove() throws Exception {  // remove 1
            remove((short)1);
        }


        //getters
    public Item getItem() {
        return item;
    }

    public short getQuantity() {
        return quantity;
    }

    public short getMaxquantity() {
        return maxquantity;
    }


}
