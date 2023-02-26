package de.raik.tnttimer.v1_16_5;

import de.raik.tnttimer.core.CameraAccessor;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.labymod.api.util.math.Quaternion;
import net.labymod.api.util.math.vector.FloatVector3;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

@Singleton
@Implements(CameraAccessor.class)
public class VersionCameraAccessor implements CameraAccessor {

  @Override
  public Quaternion orientation() {
    com.mojang.math.Quaternion rotation = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
    return new Quaternion(rotation.i(), rotation.j(), rotation.k(), rotation.r());
  }

  @Override
  public FloatVector3 position() {
    Vec3 position = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
    return new FloatVector3((float) position.x, (float) position.y, (float) position.z);
  }
}
