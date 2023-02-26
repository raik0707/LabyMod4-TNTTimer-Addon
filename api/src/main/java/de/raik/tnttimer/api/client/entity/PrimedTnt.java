package de.raik.tnttimer.api.client.entity;

import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.LivingEntity;

/**
 * Represent the ignited tnt entity of Minecraft
 */
public interface PrimedTnt extends Entity {

  int getFuse();

  void setFuse(int fuse);

  LivingEntity getOwner();

}
