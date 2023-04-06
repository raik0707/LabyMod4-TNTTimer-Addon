package de.raik.tnttimer.v1_16_5.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * This mixin of primed tnt is meant to
 * overwrite getFuse()
 * The reason is that in 1.16.5 tnt has a life and fuse attribute which are meant to do the same
 * Fuse however is only set once and not updated . life is doing the job here.
 * To get the addon working getFuse is changed to return life instead as it represents what fuse is
 * in the other versions
 *
 * @author Raik
 * @version 1.0
 */
@Mixin(PrimedTnt.class)
public abstract class MixinPrimedTnt extends Entity {

  public MixinPrimedTnt(EntityType<?> type, Level level) {
    super(type, level);
  }

  @Shadow
  public int life;

  /**
   * Overwriting getFuse method
   * changing it from returning the constant fuse
   * to the life which actually represents the fuse
   */
  public int getFuse() {
    return this.life;
  }

}
