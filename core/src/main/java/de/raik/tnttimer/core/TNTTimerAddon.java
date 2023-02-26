package de.raik.tnttimer.core;

import de.raik.tnttimer.core.generated.DefaultReferenceStorage;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class TNTTimerAddon extends LabyAddon<TNTTimerConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
    //TODO: Version specific code for 1.8 and 1.12

    this.registerListener(new TNTTimeTag(this,
        ((DefaultReferenceStorage) this.getReferenceStorageAccessor()).getCameraAccessor()));
  }

  @Override
  protected Class<TNTTimerConfig> configurationClass() {
    return TNTTimerConfig.class;
  }
}
