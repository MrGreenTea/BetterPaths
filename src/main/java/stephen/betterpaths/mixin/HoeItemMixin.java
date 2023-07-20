package stephen.betterpaths.mixin;

import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import stephen.betterpaths.BetterPaths;

import java.util.function.Consumer;
import java.util.function.Predicate;


@Mixin(HoeItem.class)
public abstract class HoeItemMixin {


    @Inject(at = @At(value = "HEAD", target = "Lnet/minecraft/item/Item;useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;"), method = "useOnBlock", cancellable = true)
    private void useOnBetterPaths(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos blockPos;
        World world = context.getWorld();
        Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = BetterPaths.TILLING_ACTIONS.get(world.getBlockState(blockPos = context.getBlockPos()));
        if (pair != null) {
            Predicate<ItemUsageContext> predicate = pair.getFirst();
            Consumer<ItemUsageContext> consumer = pair.getSecond();
            if (predicate.test(context)) {
                PlayerEntity playerEntity = context.getPlayer();
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (!world.isClient) {
                    consumer.accept(context);
                    if (playerEntity != null) {
                        context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                    }
                }
                cir.setReturnValue(ActionResult.success(world.isClient));
                cir.cancel();
            }
            cir.setReturnValue(ActionResult.PASS);
            cir.cancel();
        } else {
            Pair<Boolean, Consumer<ItemUsageContext>> newPair = BetterPaths.ROOTED_DIRT_TILLING.get(world.getBlockState(blockPos = context.getBlockPos()));
            if (newPair != null) {
                Consumer<ItemUsageContext> consumer = newPair.getSecond();
                if (newPair.getFirst()) {
                    PlayerEntity playerEntity = context.getPlayer();
                    world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    if (!world.isClient) {
                        consumer.accept(context);
                        if (playerEntity != null) {
                            context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                        }
                    }
                    cir.setReturnValue(ActionResult.success(world.isClient));
                    cir.cancel();
                }
                cir.setReturnValue(ActionResult.PASS);
                cir.cancel();
            }
        }
    }
}