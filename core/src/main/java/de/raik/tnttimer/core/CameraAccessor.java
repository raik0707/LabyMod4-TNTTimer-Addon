package de.raik.tnttimer.core;

import net.labymod.api.reference.annotation.Referenceable;
import net.labymod.api.util.math.Quaternion;
import net.labymod.api.util.math.vector.FloatVector3;
import org.jetbrains.annotations.Nullable;

@Nullable
@Referenceable
public interface CameraAccessor {

  Quaternion orientation();

  FloatVector3 position();

}
