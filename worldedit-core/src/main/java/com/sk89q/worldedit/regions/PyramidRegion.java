package com.sk89q.worldedit.regions;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;

/**
 * Represents a pyramid region with a square base.
 */
public class PyramidRegion extends AbstractRegion {
    private BlockVector3 base;
    private int size;
    private boolean hollow;

    /**
     * Create a new instance of a pyramid region with a center and size.
     *
     * @param world  the world
     * @param base   the base
     * @param size   the size
     * @param hollow whether the pyramid should be hollow
     */
    public PyramidRegion(World world, BlockVector3 base, int size, boolean hollow) {
        super(world);
        this.base = base;
        this.size = size;
        this.hollow = hollow;
    }

    public void setBase(BlockVector3 base) {
        this.base = base;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public BlockVector3 getMinimumPoint() {
        return this.base.subtract(this.size, 0, this.size);
    }

    @Override
    public BlockVector3 getMaximumPoint() {
        return this.base.add(this.size, this.size, this.size);
    }

    @Override
    public void expand(BlockVector3... changes) throws RegionOperationException {
        // TODO
    }

    @Override
    public void contract(BlockVector3... changes) throws RegionOperationException {
        // TODO
    }

    @Override
    public boolean contains(BlockVector3 position) {
        var local = position.subtract(base);
        int localY = local.getBlockY();
        if (localY < 0 || localY >= size) {
            return false;
        }
        int maxWidth = size - localY - 1;
        int localX = local.getBlockX();
        int localZ = local.getBlockZ();
        return localX >= -maxWidth && localX <= maxWidth && localZ >= -maxWidth && localZ <= maxWidth;
    }
}
