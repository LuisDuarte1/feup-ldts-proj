package ldts.terrarialike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MouseInputMethodTest {

    @Test

    public void testGetPosx(){
        MouseInputMethod mouseInputMethodtest = new MouseInputMethod(3,4);
        Assertions.assertEquals(3,mouseInputMethodtest.getPosx());
    }

    @Test

    public void testGetPosy(){
        MouseInputMethod mouseInputMethod = new MouseInputMethod(3,4);
        Assertions.assertEquals(4,mouseInputMethod.getPosy());

    }

    @Test

    public void testsetPosx(){
        MouseInputMethod mouseInputMethod = new MouseInputMethod(3,4);
        mouseInputMethod.setPosx(6);
        Assertions.assertEquals(6,mouseInputMethod.getPosx());
    }

    @Test

    public void testsetPosy(){
        MouseInputMethod mouseInputMethod = new MouseInputMethod(3,4);
        mouseInputMethod.setPosy(6);
        Assertions.assertEquals(6,mouseInputMethod.getPosy());
    }

    @Test

    public void testgetRight_click(){
        MouseInputMethod mouseInputMethod = new MouseInputMethod(true,false);
        Assertions.assertEquals(true,mouseInputMethod.getRight_click());
    }

    @Test

    public void testgetLeft_click(){
        MouseInputMethod mouseInputMethod = new MouseInputMethod(true,false);
        Assertions.assertEquals(false,mouseInputMethod.getLeft_click());
    }







}
