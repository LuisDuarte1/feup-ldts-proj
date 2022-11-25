package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<ItemStack> inventory = new ArrayList<>();  //verificar o tamanho da lista  criar classe chamada itemstqack pega num item e quantidade e guardas os dois e guardas a lista de items stacks
    private int size;   //tamanho da lista                       // ter um add item stack e remove item stack

    private int selecteditemindex = 0;
    final public static int MAX_SIZE = 100;

    //constructor
    public Inventory(int size) throws InvalidSizeException {
        if(size <= MAX_SIZE){
            this.size = size;
        }else{
            throw new InvalidSizeException("Invalid size");
        }
    }

    //getters
    public List<ItemStack> getInventory() { // criar um stub de inventario
        return inventory;
    }

    public int getSize() {
        return size;
    }


    // operations
    public void add(Item item, int quantity) throws InventoryFullException, InvalidQuantityException { // a quantity vamos ter de passar por ref para controlar o que n entra

        int quantityleft = quantity;

        if (contains(item) != null) {

            ItemStack itemstack = contains(item);

            if (itemstack.getQuantity() + quantityleft <= ItemStack.MAXQUANTITY) {
                itemstack.add(quantityleft);
                return;

            } else {
                quantityleft -= ItemStack.MAXQUANTITY - itemstack.getQuantity();
                itemstack.add( (ItemStack.MAXQUANTITY - itemstack.getQuantity())); // ???
            }
        }
        while (quantityleft > ItemStack.MAXQUANTITY && inventory.size() < MAX_SIZE) {
            ItemStack itemstack = new ItemStack(item, ItemStack.MAXQUANTITY);
            inventory.add(itemstack);
            quantityleft -= ItemStack.MAXQUANTITY;
            size++;

            

        }
        if (inventory.size() == MAX_SIZE) {
            throw new InventoryFullException("Inventory is full");
        } else {
            ItemStack itemstack = new ItemStack(item, quantityleft);
            inventory.add(itemstack);
            size++;
        }
    }



        // caso em que queremos dar add a um item que já existe na lista vai ter de adicionar a quantidade que pode ou criar outra stack?



    public void remove(Item item, int quantity ) throws InvalidQuantityException,ItemNotFoundException {
        if(contains(item) != null){
            ItemStack itemstack = contains(item);
            if(itemstack.getQuantity() - quantity > 0){
                itemstack.remove(quantity);}
            else if(itemstack.getQuantity() - quantity == 0){
                this.inventory.remove(itemstack);
            }
            else {
                throw new InvalidQuantityException("Invalid quantity");
            }
        }else{
            throw new ItemNotFoundException("Item not found");
        }
    }

    //check if the inventory contains the item
    public ItemStack contains(Item item){
        for (ItemStack itemStack : inventory) {
            if(itemStack.getItem().equals(item)){
                return itemStack;
            }
        }
        return null;
    }




    public int getSelecteditemindex() {
        return selecteditemindex;
    }

    public Item getSelectedItem(){
        return inventory.get(selecteditemindex).getItem();
    }

    public ItemStack getSelectedItemStack(){
        return inventory.get(selecteditemindex);
    }

    public void setSelecteditem(int selecteditemindex) throws InvalidIndexException {
        if(selecteditemindex < this.size && selecteditemindex >= 0)
        this.selecteditemindex = selecteditemindex;

        else{
            throw new InvalidIndexException("Index not valid");
        }
    }
}
