package de.raik.tnttimer.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class TNTTimerAddon extends LabyAddon<TNTTimerConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
    this.labyAPI().permissionRegistry().register("tnttimer", true);
    this.labyAPI().tagRegistry().register("tnttag", PositionType.ABOVE_NAME, new TNTTimeTag(this));
  }

  @Override
  protected Class<TNTTimerConfig> configurationClass() {
    return TNTTimerConfig.class;
  }
}
