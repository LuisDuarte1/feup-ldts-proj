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

    public int getMaxSize() {
        return size;
    }

    public int getSize() {
        return inventory.size();
    }


    // operations
    public void add(Item item, int quantity) throws InventoryFullException, InvalidQuantityException { // a quantity vamos ter de passar por ref para controlar o que n entra

        int quantityleft = quantity;

        if (contains(item) != null) {

            List<ItemStack> itemStacks = inventory.stream().filter(
                    (iStack) -> {return iStack.getItem().equals(item);}).toList();
            for(ItemStack itemstack : itemStacks){
                if (itemstack.getQuantity() + quantityleft <= ItemStack.MAXQUANTITY) {
                    itemstack.add(quantityleft);
                    return;

                } else {
                    quantityleft -= ItemStack.MAXQUANTITY - itemstack.getQuantity();
                    itemstack.add( (ItemStack.MAXQUANTITY - itemstack.getQuantity())); // ???
                }
            }

        }
        while (quantityleft > ItemStack.MAXQUANTITY && inventory.size() > this.size) {
            ItemStack itemstack = new ItemStack(item, ItemStack.MAXQUANTITY);
            inventory.add(itemstack);
            quantityleft -= ItemStack.MAXQUANTITY;


            

        }
        if (inventory.size() == this.size) {
            throw new InventoryFullException("Inventory is full");
        } else if(quantityleft > 0) {
            ItemStack itemstack = new ItemStack(item, quantityleft);
            inventory.add(itemstack);
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

    public void setInventory(List<ItemStack> inventory) {
        this.inventory = inventory;
    }




    public int getSelecteditemindex() {
        return selecteditemindex;
    }

    public Item getItem(int index) throws InvalidIndexException {

        if( index < inventory.size()){
            return inventory.get(index).getItem();
        }
        else {
            throw new InvalidIndexException("Invalid Index");
        }
    }

    public ItemStack getItemStack(int index) throws InvalidIndexException {

        if( index < inventory.size()){
            return inventory.get(index);
        }
        else {
            throw new InvalidIndexException("Invalid Index");
        }
    } // fazer testes!!!!!!!!!!!!!!!!!!!!1

    public Item getSelectedItem(){
        try{
            return inventory.get(selecteditemindex).getItem();
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public void selectEmpty(){
        selecteditemindex = size + 1;
    }

    public ItemStack getSelectedItemStack(){
        try{
            return inventory.get(selecteditemindex);
        } catch (IndexOutOfBoundsException e){
            return  null;
        }
    }

    public void setSelecteditem(int selecteditemindex) throws InvalidIndexException {
        if(selecteditemindex < this.size && selecteditemindex >= 0)
        this.selecteditemindex = selecteditemindex;

        else{
            throw new InvalidIndexException("Index not valid");
        }
    }
}
