package net.yeoxuhang.ambiance.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class NbtGetter {
    public static String getBlockStateProperty(Level world, BlockPos pos, String propertyName) {
        // Get the BlockState at the specified position
        BlockState blockState = world.getBlockState(pos);

        // Check if the BlockState is valid
        if (blockState != null) {
            // Retrieve the property by name from the BlockState's block
            Property<?> property = blockState.getBlock().getStateDefinition().getProperty(propertyName);
            if (property != null) {
                // Get the value of the property and convert it to a string
                Comparable<?> value = blockState.getValue(property);
                String valueStr = value.toString().toLowerCase().replace("_eye", "");
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
