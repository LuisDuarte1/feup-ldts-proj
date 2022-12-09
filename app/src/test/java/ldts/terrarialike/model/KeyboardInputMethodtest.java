package ldts.terrarialike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyboardInputMethodtest {





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
