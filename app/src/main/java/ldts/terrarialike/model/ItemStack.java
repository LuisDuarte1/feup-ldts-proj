package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidQuantityException;

public class ItemStack { // não esquecer para verificar o tipo de item que está a ser adicionado pode influenciar aspetos
    final private Item item;
    final public static int MAXQUANTITY = 64;
    private int quantity;

    // checks if he can add the quantity to the stack


    // constructor
    public ItemStack(Item item, int quantity) throws InvalidQuantityException {
        this.item = item;

        if(quantity <= MAXQUANTITY){
            this.quantity = quantity;
        }else{
            throw new InvalidQuantityException("Invalid quantity");
        }
    }

     /* public ItemStack(Item item) throws Exception {
            this(item, 1);
        }*/

        //add quantity to the stack
    public void add(int quantity) throws InvalidQuantityException { // adds any quantity to the stack
            if(quantity + this.quantity <= MAXQUANTITY){
                this.quantity += quantity;
            }else{
                throw new InvalidQuantityException("Invalid quantity");
            }
        }
        //remove quantity from the stack
    public void remove(int quantity) throws InvalidQuantityException { // removes any quantity from the stack
        if (this.quantity - quantity >= 0) {
            this.quantity -= quantity;
            } else {
                throw new InvalidQuantityException("Invalid quantity");
            }
        }


        //getters
        public Item getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }


    }
