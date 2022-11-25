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

        Assertions.assertEquals(new Position(6,0), camera.getPosition());
        camera.setNewPositionRelativeToPosition(new Position(-51,0));

        newCameraPosition = camera.getPosition();
        Assertions.assertEquals(new Position(-6,0), camera.getPosition());


    }

    @Test
    public void moveByOneCameraTest() throws InvalidPositionException{
        camera.setNewPositionRelativeToPosition(new Position((int) (50-(50*Camera.SPACE_LEFT)),0));

        Position newCameraPosition = camera.getPosition();

        Assertions.assertEquals(new Position(0,0), newCameraPosition);

        camera.setNewPositionRelativeToPosition(new Position((int) (50-(50*Camera.SPACE_LEFT)) + 1,0));

        newCameraPosition = camera.getPosition();

        Assertions.assertEquals(new Position(1,0), newCameraPosition);

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
}
