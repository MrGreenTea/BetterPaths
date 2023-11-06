package stephen.betterpaths.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GravelSlab extends FallingSlab{
    public GravelSlab() {
        super(FabricBlockSettings.copy(Blocks.GRAVEL));
    }
}
