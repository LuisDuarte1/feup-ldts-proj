package ldts.terrarialike.model;


import java.util.Objects;

public class Block {
    private Position position;
    private boolean collision;

    private BlockInfo blockInfo;




    public Block(Position position, BlockInfo blockInfo) {
        this.position = position;
        this.collision = true;
        this.blockInfo = blockInfo;
    }

    public Block(Position position, boolean collision, BlockInfo blockInfo) {
        this.position = position;
        this.collision = collision;
        this.blockInfo = blockInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return position.equals(block.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public Position getPosition() {
        return position;
    }

    public boolean isCollision() {
        return collision;
    }

    public BlockInfo getBlockInfo() {
        return blockInfo;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
