package net.yeoxuhang.ambiance.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class NbtGetter {
    public static String endrem$getEyeType(Level world, BlockPos pos) {
        // Get the block entity at the specified position
        BlockEntity blockEntity = world.getBlockEntity(pos);
        CompoundTag nbtData = new CompoundTag();
        // Check if the block entity is valid
        if (blockEntity != null) {
            // Create a new CompoundTag to store the NBT data

            // Save the block entity's data to the NBT tag
            blockEntity.saveAdditional(nbtData, null);

            // Return the NBT data
            return nbtData.getString("eye_inside");
        } else return "evil_eye";
    }
}
