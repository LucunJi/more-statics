package github.io.lucunji.morestatics.utils;

import github.io.lucunji.morestatics.MoreStatics;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;


public class StatHelper {
    public static void addBreakBedrockStat(BlockPos pistonBasePos, BlockState state, World world) {
        if (!world.isClient()) {
            Direction direction = state.get(FacingBlock.FACING);
            BlockPos bedrockPos = pistonBasePos.offset(direction);
            Vec3d adjustedBlockPos = new Vec3d(bedrockPos.getX(), bedrockPos.getY(), bedrockPos.getZ()).add(0.5, 0.5, 0.5);
            if (world.getBlockState(bedrockPos).getBlock() == Blocks.BEDROCK) {
                world.getServer().getPlayerManager().getPlayerList().stream()
                        .min(Comparator.<PlayerEntity>comparingDouble(player -> adjustedBlockPos.squaredDistanceTo(player.getPosVector())))
                        .ifPresent(player -> {
                            if (player.getPosVector().squaredDistanceTo(adjustedBlockPos) <= 25) {
                                player.increaseStat(MoreStatics.BREAK_BEDROCK, 1);
                            }
                        });
            }
        }
    }
}
