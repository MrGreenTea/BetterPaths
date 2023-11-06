package stephen.betterpaths.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import stephen.betterpaths.BetterPaths;
import stephen.betterpaths.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup BETTER_PATHS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BetterPaths.MOD_ID, ""),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.betterpaths"))
                    .icon(() -> new ItemStack(ModBlocks.DIRT_SLAB)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.DIRT_SLAB);
                        entries.add(ModBlocks.GRASS_SLAB);
                        entries.add(ModBlocks.ROOTED_DIRT_SLAB);
                        entries.add(ModBlocks.PODZOL_SLAB);
                        entries.add(ModBlocks.COARSE_DIRT_SLAB);
                        entries.add(ModBlocks.DIRT_PATH_SLAB);
                        entries.add(ModBlocks.GRAVEL_SLAB);
                        entries.add(ModBlocks.SAND_SLAB);
                    }).build());

    public static void registerItemGroups() {
        BetterPaths.LOGGER.info("Registering Item Groups for " + BetterPaths.MOD_ID);
    }
}
