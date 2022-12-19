package ldts.terrarialike.model;


import ldts.terrarialike.exceptions.BlockNotFoundException;
import ldts.terrarialike.exceptions.InvalidPositionException;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
        public static int CHUNK_SIZE = 16;    


        private final int position;
        private List<Block> blocks = new ArrayList<>(); // Lista de blocos no chunk

        public Chunk(int position) {
            this.position = position;
        }


        public int getChunkID(){return position;}

        public List<Block> getBlocks() {
            return this.blocks;
        }

        public List<Block> setBlocks(List<Block> blocks) {
            return this.blocks = blocks;
        }

        public int getPosition() {
            return this.position;
        }

        public void addBlock(Block block) throws InvalidPositionException {
            if (!validCoords(block)) {
                throw new InvalidPositionException("Block invalid position at: x:"
                        +Integer.toString(block.getPosition().getX())+
                        " y:"+Integer.toString(block.getPosition().getY())
                        +" chunk_id:"+Integer.toString(position));
            }
            if(!notIn(block)){
                System.err.println("Block already there at: x:"
                        +Integer.toString(block.getPosition().getX())+
                        " y:"+Integer.toString(block.getPosition().getY())
                        +" chunk_id:"+Integer.toString(position));
            }
            blocks.add(block);
        }

        public void removeBlock(Position pos) throws BlockNotFoundException {
            boolean found = false;
            for (Block b : blocks) {
                if (b.getPosition().equals(pos)) {
                    blocks.remove(b);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new BlockNotFoundException("Block not found");
            }
        }

        // Check if a block is already in chunk
        public boolean notIn(Block block) {
         return !blocks.contains(block);
        }

        // Check if block coordinates are contained in assigned chunk
        public boolean validCoords(Block block) {

            return block.getPosition().getX() >= CHUNK_SIZE * position
                    && block.getPosition().getX() < CHUNK_SIZE * position + CHUNK_SIZE
                    && block.getPosition().getY() >= Position.Y_MIN
                    && block.getPosition().getY() <= Position.Y_MAX;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + position;
            return result;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Chunk other = (Chunk) obj;
            return position == other.position;
        }
    }



