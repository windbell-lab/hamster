package org.windbell.lab.hamster.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JunitTestDemo {
	private int param1;
	private int param2;
	private int param3;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
	}

	public JunitTestDemo(int param1, int param2, int param3) {
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
	}

	@Test(timeout = 1000)
	public void test1() {
		System.out.println(this.toString());
	}
	@Ignore("忽略测试")
	@Test(timeout = 1000)
	public void test2() {
		while (true)
			System.out.println(this.toString());
	}

	@Test(expected = ArithmeticException.class, timeout = 1000)
	public void test3() {
		System.out.println(this.toString());
		throw new ArithmeticException();
	}

	@Override
	public String toString() {
		return "JunitTestDemo [param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + "]";
	}

}
