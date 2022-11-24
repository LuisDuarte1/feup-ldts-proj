package ldts.terrarialike.model;

public class KeyboardInputMethod { // posição do rato e as keys( usar  o position para isso)

   private int Posx;
   private int Posy;
   private char key;  // para o teclado

    public KeyboardInputMethod(int posx, int posy) {
         this.Posx = posx;
         this.Posy = posy;}


    public KeyboardInputMethod(char key) {
        this.key = key;
    }


    //setters and getters
    public int getPosx() {
        return Posx;
    }

    public void setPosx(int posx) {
        this.Posx = posx;
    }
    public int getPosy() {
        return Posy;
    }
    public void setPosy(int posy) {
        this.Posy = posy;
    }

    public char getkey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }











    
}
