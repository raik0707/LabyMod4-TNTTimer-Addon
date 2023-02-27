package de.raik.tnttimer.v1_18_2.mixins.world.entity.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PrimedTnt.class)
@Implements(@Interface(
    iface = de.raik.tnttimer.api.client.entity.PrimedTnt.class,
    prefix = "primedTnt$",
    remap = Remap.NONE
))
public abstract class MixinPrimedTnt extends Entity {

  public MixinPrimedTnt(EntityType<?> $$0,
      Level $$1) {
    super($$0, $$1);
  }

  @Shadow
  public abstract int shadow$getFuse();

  @Intrinsic
  public int primedTnt$getFuse() {
    return this.shadow$getFuse();
  }

}
