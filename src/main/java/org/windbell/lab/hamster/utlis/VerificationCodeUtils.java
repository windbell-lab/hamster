package org.windbell.lab.hamster.utlis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

public final class VerificationCodeUtils {
	public static Random random = new Random();
	private VerificationCodeUtils() {
	}
	
	public Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	private static Font getRandFont(){
		String[] fontFamilyNames = getFontFamilyNames();
		String fontName=fontFamilyNames[random.nextInt(fontFamilyNames.length)];
		return new Font(fontName, Font.PLAIN, 20);
	}
	private static String[] getFontFamilyNames(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        return e.getAvailableFontFamilyNames();  
	}
	public Image createImage(int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		// 填充矩形
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, width - 1, height - 1);
		// 绘制矩形边框
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		return image;
	}
	
}
