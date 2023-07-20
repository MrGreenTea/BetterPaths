package stephen.betterpaths.mixin;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import stephen.betterpaths.block.FarmlandSlab;

import static stephen.betterpaths.block.FarmlandSlab.TYPE;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class CropBlockStateMixin extends State<Block, BlockState> {

    protected CropBlockStateMixin(Block owner, ImmutableMap<Property<?>, Comparable<?>> entries, MapCodec<BlockState> codec) {
        super(owner, entries, codec);
    }

    @Shadow
    public abstract Block getBlock();




    @Inject(at = @At(value = "HEAD", target = "Lnet/minecraft/block/AbstractBlock$AbstractBlockState;hasModelOffset()Z"), method = "hasModelOffset", cancellable = true)
    private void hasModelOffsetIfOnBottomFarmlandSlab(CallbackInfoReturnable<Boolean> cir){
        if (this.getBlock() instanceof CropBlock){
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    @ModifyReturnValue(method = "getModelOffset", at= @At(value = "RETURN", target = "Lnet/minecraft/block/AbstractBlock$AbstractBlockState;getModelOffset(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d getModelOffsetIfOnBottomFarmlandSlab(Vec3d original, BlockView world, BlockPos pos){
        if (!(this.getBlock() instanceof CropBlock)){
            return original;
        }

        BlockState floor = world.getBlockState(pos.down());
        if (!(floor.getBlock() instanceof FarmlandSlab)){
            return original;
        }
        if (!(floor.get(TYPE) == SlabType.BOTTOM)){
            return original;
        }
        return original.add(0.0, -0.5, 0.0);
    }


}
