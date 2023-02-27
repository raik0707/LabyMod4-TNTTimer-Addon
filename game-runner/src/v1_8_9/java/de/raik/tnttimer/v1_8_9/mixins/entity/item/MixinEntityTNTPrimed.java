package de.raik.tnttimer.v1_8_9.mixins.entity.item;

import de.raik.tnttimer.api.client.entity.PrimedTnt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
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
  public int fuse;

  public int primedTnt$getFuse() {
    return this.fuse;
  }

}
