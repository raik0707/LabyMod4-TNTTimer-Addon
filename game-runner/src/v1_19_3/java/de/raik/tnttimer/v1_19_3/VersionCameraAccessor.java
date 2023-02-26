package de.raik.tnttimer.v1_19_3;

import de.raik.tnttimer.core.CameraAccessor;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.labymod.api.util.math.Quaternion;
import net.labymod.api.util.math.vector.FloatVector3;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

@Singleton
@Implements(CameraAccessor.class)
public class VersionCameraAccessor implements CameraAccessor {

  @Override
  public Quaternion orientation() {
    Quaternionf rotation = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
    return new Quaternion(rotation.x, rotation.y, rotation.z, rotation.w);
  }

  @Override
  public FloatVector3 position() {
    Vec3 position = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
    return new FloatVector3((float) position.x, (float) position.y, (float) position.z);
  }
}
