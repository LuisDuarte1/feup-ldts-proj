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

    private Pair<Boolean, Boolean> boundaries;

    public Camera(Position relativePlayer, BoundlessPosition gameScreenSize){
        this.position = relativePlayer;
        this.gameScreenSize = gameScreenSize;
        this.boundaries = new Pair<>(null, null);
    }

    public Position getPosition() {
        return position;
    }

    private BoundlessPosition getGameScreenMiddle(){
        return new BoundlessPosition(gameScreenSize.getX()/2, gameScreenSize.getY()/2);
    }

    private Pair<Integer, Integer> getEdgesX(){
        return new Pair<Integer,Integer>(abs(position.getX())+getGameScreenMiddle().getX(),-(abs(position.getX())+getGameScreenMiddle().getX()));
    }
    // we consider that the position will be centered in the gameScreen
    public void setNewPositionRelativeToPosition(Position relativePlayer) throws InvalidPositionException {
        this.position = relativePlayer;

    }

    public boolean isVisibleInCamera(Position position){
        BoundlessPosition p_sub = this.position.subtract(position);
        p_sub.setX(abs(p_sub.getX()));
        p_sub.setY(abs(p_sub.getY()));

        if(p_sub.getX() > getGameScreenMiddle().getX()) return false;
        return p_sub.getY() <= getGameScreenMiddle().getY();
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
        if(chunk_id >= 0 && (chunk_id*Chunk.CHUNK_SIZE)-(Chunk.CHUNK_SIZE-1) <= edges.first){
            return true;
        } else return chunk_id < 0 && (chunk_id * Chunk.CHUNK_SIZE) + (Chunk.CHUNK_SIZE - 1) >= edges.second;

    } 
}
