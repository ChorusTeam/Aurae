package net.yeoxuhang.ambiance.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NbtGetter {

    public static String endrem$getEyeType(World world, BlockPos pos) {
        // Get the block entity at the specified position
        TileEntity blockEntity = world.getBlockEntity(pos);

        // Check if the block entity is valid
        if (blockEntity != null) {
            // Create a new CompoundTag to store the NBT data
            CompoundNBT nbtData = new CompoundNBT();
            // Save the block entity's data to the NBT tag
            blockEntity.save(nbtData);

            System.out.println(nbtData);
            // Return the NBT data
            return nbtData.getString("eye_inside");
        } else return "evil_eye";
    }
}
