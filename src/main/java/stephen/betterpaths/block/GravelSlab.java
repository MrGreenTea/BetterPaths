package stephen.betterpaths.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;

public class GravelSlab extends FallingSlab{
    public GravelSlab() {
        super(FabricBlockSettings.copy(Blocks.GRAVEL));
    }
}
