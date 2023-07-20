package stephen.betterpaths.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class SandSlab extends FallingSlab{
    public SandSlab() {
        super(FabricBlockSettings.copy(Blocks.SAND));
    }
    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return 14406560;
    }
}
