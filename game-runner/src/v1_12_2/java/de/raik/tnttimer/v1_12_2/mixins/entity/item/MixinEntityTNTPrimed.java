package de.raik.tnttimer.v1_12_2.mixins.entity.item;

import de.raik.tnttimer.api.client.entity.PrimedTnt;
import net.labymod.api.client.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityTNTPrimed.class)
@Implements(@Interface(
    iface = PrimedTnt.class,
    prefix = "primedTnt$",
    remap = Remap.NONE
))
public abstract class MixinEntityTNTPrimed extends Entity {

  public MixinEntityTNTPrimed(World world) {
    super(world);
  }

  @Shadow
  public abstract int shadow$getFuse();

  @Shadow
  public abstract void shadow$setFuse(int fuse);

  @Shadow
  public abstract EntityLivingBase getTntPlacedBy();

  @Intrinsic
  public int primedTnt$getFuse() {
    return this.shadow$getFuse();
  }

  @Intrinsic
  public void primedTnt$setFuse(int fuse) {
    this.shadow$setFuse(fuse);
  }

  public LivingEntity primedTnt$getOwner() {
    return (LivingEntity) this.getTntPlacedBy();
  }
}
