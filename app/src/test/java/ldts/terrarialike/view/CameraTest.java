package ldts.terrarialike.view;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CameraTest {

    private static Camera camera = null;

    @BeforeEach
    public void resetCamera(){
        try{
            camera = new Camera(new Position(0,0), new BoundlessPosition(100,100));
        } catch (InvalidPositionException e) {
            Assertions.fail();
        }
    }

    @Test
    public void hugeJumpCameraTest() throws InvalidPositionException{

        camera.setNewPositionRelativeToPosition(new Position(51,0));

        Position newCameraPosition = camera.getPosition();

        Assertions.assertEquals(new Position(51,0), camera.getPosition());
        camera.setNewPositionRelativeToPosition(new Position(-51,0));

        newCameraPosition = camera.getPosition();
        Assertions.assertEquals(new Position(-51,0), camera.getPosition());


    }

    @Test
    public void checkVisiblePositionTest() throws InvalidPositionException {
        boolean visible = camera.isVisibleInCamera(new Position(0,1));
        Assertions.assertEquals(true, visible);
        visible = camera.isVisibleInCamera(new Position(1,0));
        Assertions.assertEquals(true, visible);
        visible = camera.isVisibleInCamera(new Position(50,0));
        Assertions.assertEquals(true, visible);
        visible = camera.isVisibleInCamera(new Position(0,50));
        Assertions.assertEquals(true, visible);
    }

    @Test
    public  void checkNonVisiblePosibleTest() throws  InvalidPositionException{
        boolean visible = camera.isVisibleInCamera(new Position(51,0));
        Assertions.assertEquals(false, visible);
        visible = camera.isVisibleInCamera(new Position(51,51));
        Assertions.assertEquals(false, visible);
        visible = camera.isVisibleInCamera(new Position(0,51));
        Assertions.assertEquals(false, visible);



    }

    @Test
    public void checkVisibleChunkTest(){
        Assertions.assertTrue(camera.isChunkVisible(-2));

        Assertions.assertTrue(camera.isChunkVisible(-1));

        Assertions.assertTrue(camera.isChunkVisible(0));

        Assertions.assertTrue(camera.isChunkVisible(1));

        Assertions.assertFalse(camera.isChunkVisible(-100));
        Assertions.assertFalse(camera.isChunkVisible(100));


    }

    @Test
    public void invertYPositionTest() {
        Assertions.assertEquals(new BoundlessPosition(1,1), camera.invertYPosition(new BoundlessPosition(1,99)));
        Assertions.assertEquals(new BoundlessPosition(1,99), camera.invertYPosition(new BoundlessPosition(1,1)));
        Assertions.assertEquals(new BoundlessPosition(1,50), camera.invertYPosition(new BoundlessPosition(1,50)));


    }

    @Test
    public void getPositionRelativeToCameraTest() throws InvalidPositionException {
        Assertions.assertEquals(new BoundlessPosition(50, 51), camera.getRelativePositionToCamera(new Position(0, 1)));
        Assertions.assertEquals(new BoundlessPosition(50, 50), camera.getRelativePositionToCamera(new Position(0, 0)));
        Assertions.assertEquals(new BoundlessPosition(49, 51), camera.getRelativePositionToCamera(new Position(-1, 1)));


    }
}
