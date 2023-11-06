package stephen.betterpaths;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stephen.betterpaths.block.ModBlocks;
import stephen.betterpaths.item.ModItemGroups;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.minecraft.block.SlabBlock.TYPE;

public class BetterPaths implements ModInitializer {
    public static final String MOD_ID = "betterpaths";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Map<BlockState, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> TILLING_ACTIONS=  new HashMap<>();
    public static Map<BlockState, Pair<Boolean, Consumer<ItemUsageContext>>> ROOTED_DIRT_TILLING = new HashMap<>();
    public static Map<BlockState, BlockState> PATH_STATES = new HashMap<>();
    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        //ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        TILLING_ACTIONS.put(ModBlocks.COARSE_DIRT_SLAB.getDefaultState(), Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(ModBlocks.DIRT_SLAB.getDefaultState())));
        TILLING_ACTIONS.put(ModBlocks.COARSE_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP), Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(ModBlocks.DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP))));
        TILLING_ACTIONS.put(ModBlocks.COARSE_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(ModBlocks.DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE))));

        ROOTED_DIRT_TILLING.put(ModBlocks.ROOTED_DIRT_SLAB.getDefaultState(), Pair.of(true, HoeItem.createTillAndDropAction(ModBlocks.DIRT_SLAB.getDefaultState(), Items.HANGING_ROOTS)));
        ROOTED_DIRT_TILLING.put(ModBlocks.ROOTED_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP), Pair.of(true, HoeItem.createTillAndDropAction(ModBlocks.DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP), Items.HANGING_ROOTS)));
        ROOTED_DIRT_TILLING.put(ModBlocks.ROOTED_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), Pair.of( true, HoeItem.createTillAndDropAction(ModBlocks.DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), Items.HANGING_ROOTS)));

        PATH_STATES.put(ModBlocks.GRASS_SLAB.getDefaultState(), ModBlocks.DIRT_PATH_SLAB.getDefaultState());
        PATH_STATES.put(ModBlocks.GRASS_SLAB.getDefaultState().with(TYPE, SlabType.TOP), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.TOP));
        PATH_STATES.put(ModBlocks.GRASS_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE));
        PATH_STATES.put(ModBlocks.DIRT_SLAB.getDefaultState(), ModBlocks.DIRT_PATH_SLAB.getDefaultState());
        PATH_STATES.put(ModBlocks.DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.TOP));
        PATH_STATES.put(ModBlocks.DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE));
        PATH_STATES.put(ModBlocks.COARSE_DIRT_SLAB.getDefaultState(), ModBlocks.DIRT_PATH_SLAB.getDefaultState());
        PATH_STATES.put(ModBlocks.COARSE_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.TOP));
        PATH_STATES.put(ModBlocks.COARSE_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE));
        PATH_STATES.put(ModBlocks.ROOTED_DIRT_SLAB.getDefaultState(), ModBlocks.DIRT_PATH_SLAB.getDefaultState());
        PATH_STATES.put(ModBlocks.ROOTED_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.TOP), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.TOP));
        PATH_STATES.put(ModBlocks.ROOTED_DIRT_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE));
        PATH_STATES.put(ModBlocks.PODZOL_SLAB.getDefaultState(), ModBlocks.DIRT_PATH_SLAB.getDefaultState());
        PATH_STATES.put(ModBlocks.PODZOL_SLAB.getDefaultState().with(TYPE, SlabType.TOP), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.TOP));
        PATH_STATES.put(ModBlocks.PODZOL_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE), ModBlocks.DIRT_PATH_SLAB.getDefaultState().with(TYPE, SlabType.DOUBLE));
    }
}

