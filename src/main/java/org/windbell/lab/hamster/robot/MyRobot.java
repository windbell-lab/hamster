package org.windbell.lab.hamster.robot;


import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyRobot {
	private Dimension dim; // 存储屏幕尺寸
	private static Robot robot;// 自动化对象

	public MyRobot() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("屏幕大小  width+" + dim.getWidth() + "  height:"
				+ dim.getHeight());
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void move(int width, int heigh) {
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		width += mousepoint.x;
		heigh += mousepoint.y;
		try {
			robot.delay(100);
			robot.mouseMove(width, heigh);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveTo(int width, int heigh) {
		robot.delay(100);
		robot.mouseMove(width, heigh);
	}

	public static void main(String[] args) {
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		int width = mousepoint.x;
		int heigh = mousepoint.y;
		MyRobot myMouseController = new MyRobot();
		myMouseController.moveTo(20, 20);
		System.out.println("--------------------------------");
		myMouseController.moveTo(512, 384);
		// 移动鼠标
		robot.mouseMove(width, heigh);
		robot.delay(1000);
		// 模拟单击
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		// 鼠标滚轮滚动
		robot.mouseWheel(-5);
		// 双击
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		// 模拟按键
		robot.keyPress(KeyEvent.VK_0);

	}
}
