package ldts.terrarialike.model;

import java.util.List;

public class Inventory {

    private List<ItemStack> inventory;  //verificar o tamanho da lista  criar classe chamada itemstqack pega num item e quantidade e guardas os dois e guardas a lista de items stacks
    private short size;   //tamanho da lista                       // ter um add item stack e remove item stack

    private Item selecteditem;

    final private short maxsize = 10;

    //constructor
    public Inventory(short size) {
        this.size = size;
    }

    //getters
    public List<ItemStack> getInventory() {
        return inventory;
    }

    public short getSize() {
        return size;
    }

    public short getMaxsize() {
        return maxsize;
    }

    public Item getselectedItem() {
        return selecteditem;
    }

    public void add(Item item, short quantity) throws Exception { // a quantity vamos ter de passar por ref para controlar o que n entra

        short quantityleft = quantity;

        if (contains(item) != null) {

            ItemStack itemstack = contains(item);

            if (itemstack.getQuantity() + quantityleft <= itemstack.getMaxquantity()) {
                itemstack.add(quantityleft);
                return;

            } else {
                quantityleft -= itemstack.getMaxquantity() - itemstack.getQuantity();
                itemstack.add((short) (itemstack.getMaxquantity() - itemstack.getQuantity())); // ???
            }
        }
        while (quantityleft > 64 && inventory.size() < maxsize) {
            ItemStack itemstack = new ItemStack(item, (short) 64);
            inventory.add(itemstack);
            quantityleft -= 64;
            size++;

        }
        if (inventory.size() == maxsize) {
            throw new Exception("Inventory is full");
        } else {
            ItemStack itemstack = new ItemStack(item, quantityleft);
            inventory.add(itemstack);
            size++;
        }
    }



        // caso em que queremos dar add a um item que já existe na lista vai ter de adicionar a quantidade que pode ou criar outra stack?



    public void remove(Item item, short quantity ) throws Exception {
        if(contains(item) != null){
            ItemStack itemstack = contains(item);
            if(itemstack.getQuantity() - quantity > 0){
                itemstack.remove(quantity);}
            else if(itemstack.getQuantity() - quantity == 0){
                this.inventory.remove(itemstack);
            }
            else {
                throw new Exception("Invalid quantity");
            }
        }else{
            throw new Exception("Item not found");
        }
    }

    //check if the inventory contains the item
    public ItemStack contains(Item item){
        for (ItemStack itemStack : inventory) {
            if(itemStack.getItem() == item){
                return itemStack;
            }
        }
        return null;
    }





    
}
