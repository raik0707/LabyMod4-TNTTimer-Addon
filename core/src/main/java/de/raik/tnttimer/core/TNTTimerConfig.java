package de.raik.tnttimer.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.PermissionRequired;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
@SpriteTexture("settings.png")
public class TNTTimerConfig extends AddonConfig {

  @SwitchSetting
  @PermissionRequired("tnttimer")
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SpriteSlot
  @SwitchSetting
  private final ConfigProperty<Boolean> colored = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> colored() {
    return this.colored;
  }
}
