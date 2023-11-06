package stephen.betterpaths.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import stephen.betterpaths.BetterPaths;


public class ModBlocks {
    public static final Block DIRT_SLAB = registerBlock("dirt_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.DIRT)));
    public static final Block COARSE_DIRT_SLAB = registerBlock("coarse_dirt_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.COARSE_DIRT)));
    public static final Block ROOTED_DIRT_SLAB = registerBlock("rooted_dirt_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.ROOTED_DIRT)));
    public static final Block GRASS_SLAB = registerBlock("grass_slab", new GrassSlab());
    public static final Block PODZOL_SLAB = registerBlock("podzol_slab", new PodzolSlab());
    public static final Block DIRT_PATH_SLAB = registerBlock("dirt_path_slab", new DirtPathSlab());
    public static final Block GRAVEL_SLAB = registerBlock("gravel_slab", new GravelSlab());
    public static final Block SAND_SLAB = registerBlock("sand_slab", new SandSlab());

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(BetterPaths.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(BetterPaths.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        BetterPaths.LOGGER.info("Registering ModBlocks for " + BetterPaths.MOD_ID);
    }
}
