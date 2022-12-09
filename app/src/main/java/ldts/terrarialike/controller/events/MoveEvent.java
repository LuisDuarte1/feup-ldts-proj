package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.MovementType;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;

import java.util.ArrayList;
import java.util.List;

public class MoveEvent implements GameEvent{

    private Entity entity;

    private MovementType movementType;





    public MoveEvent(MovementType movementType, Entity entity) {

        this.movementType = movementType;

        this.entity = entity;

    }







    //FIXME: retirar o caso switch case e abstrair para uma classe

    @Override
    public List<GameEvent> execute(World world) {

        List<GameEvent> list = new ArrayList<>();



        try {
            switch (movementType) {

                case UP:
                     // como é que é suposto sabermos se o movimento é valido ou não?
                    Position newPosition = new Position(entity.getPosition().getX(), entity.getPosition().getY() +1 );

                    if(world.getBlock(newPosition) == null){
                        entity.setPosition(newPosition);
                    }


                    break;

                case DOWN:

                    newPosition = new Position(entity.getPosition().getX(), entity.getPosition().getY() -1 );

                    if(world.getBlock(newPosition) == null){
                        entity.setPosition(newPosition);
                    }

                    break;

                case LEFT:
                    newPosition = new Position(entity.getPosition().getX() -1, entity.getPosition().getY() );

                    if(world.getBlock(newPosition) == null){
                        entity.setPosition(newPosition);
                    }

                    break;

                case RIGHT:

                    newPosition = new Position(entity.getPosition().getX() +1 , entity.getPosition().getY() );

                    if(world.getBlock(newPosition) == null){
                        entity.setPosition(newPosition);
                    }

                    break;

            }


        }
         catch (InvalidPositionException e) {

        }


        return list;
    }





    }

