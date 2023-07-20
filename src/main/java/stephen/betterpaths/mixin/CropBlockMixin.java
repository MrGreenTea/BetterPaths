package stephen.betterpaths.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import stephen.betterpaths.BetterPaths;
import stephen.betterpaths.block.FarmlandSlab;

import static stephen.betterpaths.block.FarmlandSlab.TYPE;
import stephen.betterpaths.FarmlandTag;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(CropBlock.class)
abstract
class CropBlockMixin{


    @Shadow
    public abstract int getAge(BlockState state);

    private static VoxelShape[] AGE_TO_SHAPE_ON_BOTTOM_SLAB = new VoxelShape[]{Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 10.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 12.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 14.0, 16.0), Block.createCuboidShape(0.0, -3.0, 0.0, 16.0, 16.0, 16.0)};

    @ModifyExpressionValue(method = "canPlantOnTop", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean farmlandlib$isFarmland(boolean original, BlockState floor, BlockView world, BlockPos pos) {
        return original || floor.isIn(FarmlandTag.FARMLAND);
    }

    @ModifyExpressionValue(method = "getAvailableMoisture", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
    private static boolean farmlandlib$isFarmland(boolean original, @Local BlockState state) {
        return original || state.isIn(FarmlandTag.FARMLAND);
    }
}


