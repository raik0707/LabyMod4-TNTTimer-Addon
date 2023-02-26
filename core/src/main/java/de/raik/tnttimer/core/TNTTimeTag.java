package de.raik.tnttimer.core;

import de.raik.tnttimer.api.client.entity.PrimedTnt;
import java.text.DecimalFormat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.entity.player.tag.event.NameTagBackgroundRenderEvent;
import net.labymod.api.client.entity.player.tag.tags.NameTag;
import net.labymod.api.client.gfx.GFXBridge;
import net.labymod.api.client.options.MinecraftOptions;
import net.labymod.api.client.render.font.RenderableComponent;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.client.world.WorldRenderer;
import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.camera.CameraSetupEvent;
import net.labymod.api.event.client.render.entity.EntityRenderEvent;
import net.labymod.api.loader.platform.PlatformEnvironment;
import net.labymod.api.util.ColorUtil;
import net.labymod.api.util.math.MathHelper;
import net.labymod.api.util.math.vector.FloatMatrix4;
import net.labymod.api.util.math.vector.FloatVector3;

public class TNTTimeTag extends NameTag {

  private final TNTTimerAddon addon;

  private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

  private Stack currentStack = null;

  private final MinecraftOptions options;

  public TNTTimeTag(TNTTimerAddon addon) {
    this.addon = addon;
    this.options = addon.labyAPI().minecraft().options();
  }

  private TextColor getColor() {
    if (!this.addon.configuration().colored().get()) {
      return NamedTextColor.WHITE;
    }

    int green = MathHelper.clamp(255 * ((PrimedTnt) this.entity).getFuse() / 80, 0, 255);
    return TextColor.color(255 - green, green, 0);
  }

  private String getTag() {
    float number = ((PrimedTnt) this.entity).getFuse()  / 20F;
    if (number < 0) {
      return null;
    }
    return this.decimalFormat.format(number);
  }

  /*
   * Receiving this event because there's no closer event to access
   * the render stack if order to use it with the Render event
   */
  @Subscribe
  public void onBeforeRenderWorld(CameraSetupEvent event) {
    if (event.phase() == Phase.POST) {
      this.currentStack = event.stack();
    }
  }

  @Subscribe
  public void onRenderEntity(EntityRenderEvent event) {
    if (event.phase() == Phase.PRE || this.currentStack == null) {
      return;
    }
    if (!this.addon.configuration().enabled().get()) {
      return;
    }
    if (event.entity() instanceof PrimedTnt) {
      PrimedTnt tnt = (PrimedTnt) event.entity();
      this.begin(tnt);
      this.renderTag(tnt);
    }
  }

  private void renderTag(PrimedTnt tnt) {
    this.currentStack.push();
    // Setup position
    WorldRenderer worldRenderer = this.addon.labyAPI().minecraft().worldRenderer();
    FloatVector3 cameraPosition = worldRenderer.cameraPosition();
    this.currentStack.translate(
        tnt.position().getX() - cameraPosition.getX(),
        tnt.position().getY() - cameraPosition.getY(),
        tnt.position().getZ() - cameraPosition.getZ());
    this.currentStack.translate(0, tnt.axisAlignedBoundingBox().getYSize() + 0.5F, 0);

    this.currentStack.multiply(new FloatMatrix4(worldRenderer.cameraRotation()));
    this.currentStack.scale(-0.025F);
    // Centering tag
    this.currentStack.translate(-this.getWidth() / 2.0F, -this.getHeight(), 0);

    GFXBridge gfx = this.addon.labyAPI().gfxRenderPipeline().gfx();
    gfx.storeBlaze3DStates();
    gfx.depthMask(false);

    if (!PlatformEnvironment.isAncientOpenGL()) {
      gfx.enableDepth();
    }

    gfx.enableBlend();
    gfx.defaultBlend();

    this.renderBackground();
    gfx.depthMask(true);

    if (PlatformEnvironment.isAncientOpenGL()) {
      gfx.enableDepth();
    }
    this.renderText(this.currentStack, this.getRenderableComponent(), false, 0x20FFFFFF, 1, 0.5F);
    this.renderText(this.currentStack, this.getRenderableComponent(), false, -1, 1, 0.5F);

    this.currentStack.pop();
  }

  private void renderBackground() {
    NameTagBackgroundRenderEvent event = NameTagBackgroundRenderEvent.singleton();
    if (event.isCancelled()) {
      return;
    }

    int alpha = (int) (this.options.getBackgroundColorWithOpacity(192) * 255.0F);
    int backgroundColor = ColorUtil.toValue(event.getColor(), alpha);

    this.renderBackground(
        this.currentStack,
        -1.0F,
        0,
        this.getWidth(),
        this.getHeight(),
        backgroundColor
    );
  }

  @Override
  protected RenderableComponent getRenderableComponent() {
    String tag = this.getTag();
    if (tag == null) {
      return null;
    }

    return RenderableComponent.of(
        Component
            .text(tag)
            .color(this.getColor()));
  }
}
