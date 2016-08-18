package com.mrcrayfish.device.api.app.component;

import java.awt.Color;
import java.util.List;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.core.Laptop;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Text extends Component 
{
	protected List<String> lines;
	protected int width;
	protected boolean shadow = false;
	
	protected int textColour = Color.WHITE.getRGB();
	
	/**
	 * Default text constructor
	 * 
	 * @param text the text to display
	 * @param x the application x position (from {@link Application#init(int x, int y)})
	 * @param y the application y position (from {@link Application#init(int x, int y)})
	 * @param left how many pixels from the left
	 * @param top how many pixels from the top
	 * @param width the max width
	 */
	public Text(String text, int x, int y, int left, int top, int width) 
	{
		super(x, y, left, top);
		this.setText(text);
		this.width = width;
	}

	@Override
	public void render(Laptop laptop, Minecraft mc, int mouseX, int mouseY, boolean windowActive, float partialTicks) 
	{
		if (this.visible)
        {
			for(int i = 0; i < lines.size(); i++)
			{
				mc.fontRendererObj.drawString(lines.get(i), xPosition, yPosition + (i * 10), textColour, shadow);
			}
        }
	}
	
	/**
	 * Sets the text for this component 
	 * 
	 * @param text the text
	 */
	public void setText(String text)
	{
		this.lines = Minecraft.getMinecraft().fontRendererObj.listFormattedStringToWidth(text, width);
	}
	
	/**
	 * Sets the text colour for this component
	 * 
	 * @param color the text colour
	 */
	public void setTextColour(Color color) 
	{
		this.textColour = color.getRGB();
	}
	
	/**
	 * Sets the whether shadow should show under the text
	 * 
	 * @param color the text colour
	 */
	public void setShadow(boolean shadow)
	{
		this.shadow = shadow;
	}
}
