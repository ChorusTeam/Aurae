package net.yeoxuhang.ambiance.util;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.Property;
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

    public static String getBlockStateProperty(World world, BlockPos pos, String propertyName) {
        // Get the BlockState at the specified position
        BlockState blockState = world.getBlockState(pos);

        // Check if the BlockState is valid
        if (blockState != null) {
            // Retrieve the property by name from the BlockState's block
            Property<?> property = blockState.getBlock().getStateDefinition().getProperty(propertyName);

            if (property != null) {
                // Get the value of the property and convert it to a string
                Comparable<?> value = blockState.getValue(property);
                String valueStr = value.toString();
                return valueStr;
            } else {
                System.out.println("Property '" + propertyName + "' does not exist for this block.");
                return "unknown";
            }
        } else {
            System.out.println("Invalid BlockState at position: " + pos);
            return "invalid";
        }
    }
}
