package eu.crushedpixel.replaymod.gui.elements;

import net.minecraft.client.Minecraft;

public class ComposedElement implements GuiElement {
    private final GuiElement[] parts;

    public ComposedElement(GuiElement...parts) {
        this.parts = parts;
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY) {
        for (GuiElement part : parts) {
            part.draw(mc, mouseX, mouseY);
        }
    }

    @Override
    public void drawOverlay(Minecraft mc, int mouseX, int mouseY) {
        for (GuiElement part : parts) {
            part.drawOverlay(mc, mouseX, mouseY);
        }
    }

    @Override
    public boolean isHovering(int mouseX, int mouseY) {
        for (GuiElement part : parts) {
            if (part.isHovering(mouseX, mouseY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClick(Minecraft mc, int mouseX, int mouseY, int button) {
        for (GuiElement part : parts) {
            part.mouseClick(mc, mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseDrag(Minecraft mc, int mouseX, int mouseY, int button) {
        for (GuiElement part : parts) {
            part.mouseDrag(mc, mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseRelease(Minecraft mc, int mouseX, int mouseY, int button) {
        for (GuiElement part : parts) {
            part.mouseRelease(mc, mouseX, mouseY, button);
        }
    }
}