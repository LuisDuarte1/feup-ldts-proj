package ldts.terrarialike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyboardInputMethodtest {

    @Test
    public void testGetPosx(){
        KeyboardInputMethod keyboardInputMethodtest = new KeyboardInputMethod(3,4);
        Assertions.assertEquals(3,keyboardInputMethodtest.getPosx());
    }

    @Test
    public void testGetPosy(){
        KeyboardInputMethod keyboardInputMethod = new KeyboardInputMethod(3,4);
        Assertions.assertEquals(4,keyboardInputMethod.getPosy());

    }

    @Test
    public void testsetPosx(){
        KeyboardInputMethod keyboardInputMethod = new KeyboardInputMethod(3,4);
        keyboardInputMethod.setPosx(6);
        Assertions.assertEquals(6,keyboardInputMethod.getPosx());
    }

    @Test
    public void testsetPosy(){
        KeyboardInputMethod keyboardInputMethod = new KeyboardInputMethod(3,4);
        keyboardInputMethod.setPosy(6);
        Assertions.assertEquals(6,keyboardInputMethod.getPosy());
    }

    @Test
    public void testgetkey(){
        KeyboardInputMethod keyboardInputMethod = new KeyboardInputMethod('a');
        Assertions.assertEquals('a',keyboardInputMethod.getkey());
    }

    @Test
    public void testsetkey(){
        KeyboardInputMethod keyboardInputMethod = new KeyboardInputMethod('a');
        keyboardInputMethod.setKey('b');
        Assertions.assertEquals('b',keyboardInputMethod.getkey());
    }

}
