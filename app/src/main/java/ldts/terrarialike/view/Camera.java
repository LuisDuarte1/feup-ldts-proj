package ldts.terrarialike.view;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.utils.Pair;

import static java.lang.Math.abs;

public class Camera {

    private Position position;

    public final static double SPACE_LEFT=0.10;

    private BoundlessPosition gameScreenSize;

    public Camera(Position relativePlayer, BoundlessPosition gameScreenSize){
        this.position = relativePlayer;
        this.gameScreenSize = gameScreenSize;
    }

    public Position getPosition() {
        return position;
    }

    private BoundlessPosition getGameScreenMiddle(){
        return new BoundlessPosition(gameScreenSize.getX()/2, gameScreenSize.getY()/2);
    }

    private Pair<Integer, Integer> getEdgesX(){
        return new Pair<Integer,Integer>(position.getX()+getGameScreenMiddle().getX(),-(position.getX()+getGameScreenMiddle().getX()));
    }

    private int getBoundariesX() {
        return (int) (getGameScreenMiddle().getX() - (getGameScreenMiddle().getX() * SPACE_LEFT));
    }

    private int getBoundariesY(){
        return (int) (getGameScreenMiddle().getY() - ( getGameScreenMiddle().getY() * SPACE_LEFT));
    }

    // we consider that the position will be centered in the gameScreen
    public void setNewPositionRelativeToPosition(Position relativePlayer) throws InvalidPositionException {
        BoundlessPosition p_sub = this.position.subtract(relativePlayer);
        //if negative = player to the right; positive = player to the left
        int x_diff = 0;
        int y_diff = 0;

        if(abs(p_sub.getX()) >= getBoundariesX()){
            x_diff = abs(p_sub.getX()) - getBoundariesX();
        }
        if(abs(p_sub.getY()) >= getBoundariesY()){
            y_diff = abs(p_sub.getY()) - getBoundariesY();
        }

        x_diff = p_sub.getX() > 0 ? -x_diff : x_diff;
        y_diff = p_sub.getY() > 0 ? -y_diff : y_diff;

        this.position = new Position(this.position.getX() + x_diff, this.position.getY() + y_diff);

    }

    public boolean isVisibleInCamera(Position position){
        BoundlessPosition p_sub = this.position.subtract(position);
        p_sub.setX(abs(p_sub.getX()));
        p_sub.setY(abs(p_sub.getY()));

        if(p_sub.getX() > getGameScreenMiddle().getX()) return false;
        if(p_sub.getY() > getGameScreenMiddle().getY()) return false;

        return true;
    }

    public BoundlessPosition invertYPosition(BoundlessPosition oldPos){
        return new BoundlessPosition(oldPos.getX(), gameScreenSize.getY()-oldPos.getY());
    }

    public BoundlessPosition getRelativePositionToCamera(Position position){
        BoundlessPosition p_sub = position.subtract(this.position);

        BoundlessPosition r = getGameScreenMiddle();
        r.add(p_sub);
        return r;
    }

    public void resizeScreen(BoundlessPosition gameScreenSize) {
        this.gameScreenSize = gameScreenSize;
    }

    public Boolean isChunkVisible(int chunk_id){
        Pair<Integer,Integer> edges = getEdgesX();
        if(chunk_id >= 0 && (chunk_id*Chunk.CHUNK_SIZE)-15 <= edges.first){
            return true;
        } else if(chunk_id < 0 && (chunk_id*Chunk.CHUNK_SIZE)+15 >= edges.second) {
            return true;
        }
        return false;

    } 
}
