package net.yeoxuhang.ambiance.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.yeoxuhang.ambiance.mixin.BlockEntityAccessor;

public class NbtGetter {

    public static String endrem$getEyeType(Level world, BlockPos pos) {
        // Get the block entity at the specified position
        BlockEntity blockEntity = world.getBlockEntity(pos);

        // Check if the block entity is valid
        if (blockEntity != null) {
            // Create a new CompoundTag to store the NBT data
            CompoundTag nbtData = new CompoundTag();
            // Save the block entity's data to the NBT tag
            ((BlockEntityAccessor)blockEntity).invokeSaveAdditional(nbtData, null);

            System.out.println(nbtData);
            // Return the NBT data
            return nbtData.getString("eye_inside");
        } else return "evil_eye";
    }
}
