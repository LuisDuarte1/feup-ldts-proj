package ldts.terrarialike.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;

public class World {
    private List<Chunk> chunksList = new ArrayList<>();
    private int seed = 0;


    private List<Enemy> enemiesList = new ArrayList<>();
    

    private Player player = null;



    public World() throws InvalidPositionException, InvalidSizeException {
        Random random = new Random();
        this.seed = random.nextInt();
        this.player = new Player(new Position(0,0), 100);
    }

    public World(Integer seed) throws InvalidPositionException, InvalidSizeException{
        this.seed = seed;
        this.player = new Player(new Position(0,0), 100);
        
    }
    public Player getPlayer() {
        return player;
    }

    public int getSeed() {
        return seed;
    }

    public boolean tryAddChunk(Chunk chunk){
        for (Chunk c : this.chunksList) {
            //equals only compares position of chunk 
            if(c.equals(chunk)) return false;
        }
        this.chunksList.add(chunk);
        sortChunks();
        return true;
    }


    public List<Chunk> getChunks(){
        return chunksList;
    }        


    public void addEnemy(Enemy enemy){
        enemiesList.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        //maybe check hp?
        if(enemiesList.contains(enemy)){
            enemiesList.remove(enemy);
        }
 
    }
    public List<Enemy> getEnemiesList() {
        return enemiesList;
    }

    private void sortChunks(){
        chunksList.sort(new Comparator<Chunk>() {
            @Override
            public int compare(Chunk arg0, Chunk arg1) {
                Integer chunkID0 = arg0.getPosition();
                Integer chunkID1 = arg1.getPosition();

                return chunkID0.compareTo(chunkID1);
            }
            
        });
    }

    private Integer getChunkID(Integer xPos){
        Integer desiredChunkID = Integer.MIN_VALUE;
        if(xPos >= 0 && xPos <= Chunk.CHUNK_SIZE-1){
            desiredChunkID = 0;
        } else if(xPos >= Chunk.CHUNK_SIZE){
            desiredChunkID = xPos / Chunk.CHUNK_SIZE;
        } else if (xPos < 0 && xPos % Chunk.CHUNK_SIZE == 0) {
            //on the negative size the edges of chunks are a bit different -1 to -16 for example is contained in -1
            desiredChunkID = xPos / Chunk.CHUNK_SIZE;
        } else{
            desiredChunkID = (xPos / Chunk.CHUNK_SIZE)-1;
        }
        return desiredChunkID;
    }

    public Block getBlock(Position blockPosition){
        Integer desiredChunkID = getChunkID(blockPosition.getX());
        Chunk desiredChunk = null;
        //we have to do it this way because we can't guarentee that chunks will always be 
        //sequential
        for (Chunk chunk : chunksList) {
            if(chunk.getChunkID() == desiredChunkID){
                desiredChunk = chunk;
            }
        }
        //block doesnt exist because chunk doesnt exist either lmao
        if(desiredChunk == null) return null;
        for (Block block : desiredChunk.getBlocks()) {
            if(block.getPosition().equals(blockPosition)){
                return block;
            }
        }
        //if we get here block doesnt exist
        return null;
    }

    public Integer findMaxHeightOfXPos(Integer xPos) {
        int maxZeroPosition = -1;
        Integer chunkID = getChunkID(xPos);
        Chunk desiredChunk = null;
        for(Chunk c: chunksList){
            if(c.getChunkID() == chunkID){
                desiredChunk = c;
            }
        }
        for (Block a :
                desiredChunk.getBlocks()) {
            if(a.getPosition().getX() == xPos && a.getPosition().getY() > maxZeroPosition) {
                maxZeroPosition = a.getPosition().getY();
            }
        }
        return maxZeroPosition;
    }

}
