package test.pres.tool.web;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.testng.annotations.Test;

import pres.tool.web.Condition;
import pres.tool.web.ConstraintType;

public class TestCondition {
	/**
	 * 测试构造方法
	 */
	@Test
	public void test_01_Condition() {
		Condition c = new Condition("测试", "html", ConstraintType.NUMBER, "[1, 2]");
		c = new Condition("测试", "html", ConstraintType.NUMBER, "[0.31, 2.55]");
		c = new Condition("测试", "html", ConstraintType.DATE, "[2019.3.5, 2019-6.4]");
		c = new Condition("测试", "html", ConstraintType.DATE, "[2019.3.5 11:13:25, 2019-6.4 5:5]");
		c = new Condition("测试", "html", ConstraintType.DATE, "[2019年3月5日 11:13:25, 2019-6.4 5:5]");
		c = new Condition("测试", "html", ConstraintType.DATE, "[2019年3月5日 11时13分25秒, 2019年6月4 15时15分15]");
		
		try {
			c = new Condition("测试", "html", ConstraintType.DATE, "[2sdfgbsdhbs秒, 2019u6d4 15g15h15]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			c = new Condition("测试", "html", ConstraintType.NUMBER, "[w, d]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试约束条件数组中存储的数据
	 */
	@Test
	public void test_02_constraintsNumber() {
		try {
			Class<?> clazz = Class.forName("pres.tool.web.Condition");
			//参数类型数组
			Class[] parameterTypes={String.class, String.class, ConstraintType.class, String[].class};
			//根据参数类型获取相应的构造函数
			Constructor constructor=clazz.getConstructor(parameterTypes);
			//参数数组
			Object[] parameters={"测试", "html", ConstraintType.NUMBER, new String[] {"[1, 2]", "(0.3, 5)", "[0.1, 0.6)", "(6, 0.8]"}};
			//根据获取的构造函数和参数，创建实例
			Condition pc =(Condition) constructor.newInstance(parameters);
 
			Field[] fs = clazz.getDeclaredFields();// 获取PrivateClass所有属性
			for (int i = 0; i < fs.length; i++) {
				fs[i].setAccessible(true);// 将目标属性设置为可以访问
				if ("constraints".equals(fs[i].getName()))
					System.out.println(fs[i].get(pc));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 测试约束条件数组中存储的数据
	 */
	@Test
	public void test_03_constraintsDate() {
		try {
			Class<?> clazz = Class.forName("pres.tool.web.Condition");
			//参数类型数组
			Class[] parameterTypes={String.class, String.class, ConstraintType.class, String[].class};
			//根据参数类型获取相应的构造函数
			Constructor constructor=clazz.getConstructor(parameterTypes);
			//参数数组
			Object[] parameters={"测试", "html", ConstraintType.DATE, new String[] {
					"[2019年3月5日 11时13分25秒, 2019年6月4 15时15分15]", 
					"(2019.3.5 11:13:25, 2019-6.4 5:5)", 
					"[2019.3.5, 2019-6.4)", 
					"(2019年3月5日 11:13:25, 2009-6.4 5:5]"}};
			//根据获取的构造函数和参数，创建实例
			Condition pc =(Condition) constructor.newInstance(parameters);
 
			Field[] fs = clazz.getDeclaredFields();// 获取PrivateClass所有属性
			for (int i = 0; i < fs.length; i++) {
				fs[i].setAccessible(true);// 将目标属性设置为可以访问
				if ("constraints".equals(fs[i].getName()))
					System.out.println(fs[i].get(pc));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 测试判断数字数据
	 */
	@Test
	public void test_04_isConformToConstraint_number() {
		Condition c = new Condition("测试", "html", ConstraintType.NUMBER, "(0.1, 0.5)", "[2, 4]", "[4.2, 6)", "(10, 7.5]");
		System.out.println(c.isConformToConstraint("0"));
		System.out.println(c.isConformToConstraint("0.1"));
		System.out.println(c.isConformToConstraint("0.2"));
		System.out.println(c.isConformToConstraint("0.5"));
		System.out.println(c.isConformToConstraint("0.6"));
		System.out.println(c.isConformToConstraint("1"));
		System.out.println(c.isConformToConstraint("2"));
		System.out.println(c.isConformToConstraint("3"));
		System.out.println(c.isConformToConstraint("4"));
		System.out.println(c.isConformToConstraint("5"));
		System.out.println(c.isConformToConstraint("4.1"));
		System.out.println(c.isConformToConstraint("4.2"));
		System.out.println(c.isConformToConstraint("5"));
		System.out.println(c.isConformToConstraint("6"));
		System.out.println(c.isConformToConstraint("7"));
		System.out.println(c.isConformToConstraint("7.5"));
		System.out.println(c.isConformToConstraint("8"));
		System.out.println(c.isConformToConstraint("10"));
		System.out.println(c.isConformToConstraint("10.1"));
	}
	
	/**
	 * 测试判断日期数据
	 */
	@Test
	public void test_05_isConformToConstraint_date() {
		Condition c = new Condition("测试", "html", ConstraintType.DATE, "(2019-1-1, 2019-1-5)", "[2019-2-1, 2019-2-5]", "[2019-3-1, 2019-3-5)", "(2019-4-5, 2019-4-1]");
		System.out.println(c.isConformToConstraint("2019-1-1"));
		System.out.println(c.isConformToConstraint("2019-1-2"));
		System.out.println(c.isConformToConstraint("2019-1-5"));
		System.out.println(c.isConformToConstraint("2019-2-1"));
		System.out.println(c.isConformToConstraint("2019-2-2"));
		System.out.println(c.isConformToConstraint("2019-2-5"));
		System.out.println(c.isConformToConstraint("2019-3-1"));
		System.out.println(c.isConformToConstraint("2019-3-2"));
		System.out.println(c.isConformToConstraint("2019-3-5"));
		System.out.println(c.isConformToConstraint("2019-4-1"));
		System.out.println(c.isConformToConstraint("2019-4-2"));
		System.out.println(c.isConformToConstraint("2019-4-5"));
	}
	
	/**
	 * 测试判断字符串
	 */
	@Test
	public void test_06_isConformToConstraint_string() {
		Condition c = new Condition("测试", "html", ConstraintType.STRING, "a", "b", "ab", "abc");
		System.out.println(c.isConformToConstraint("a"));
		System.out.println(c.isConformToConstraint("b"));
		System.out.println(c.isConformToConstraint("ab"));
		System.out.println(c.isConformToConstraint("abc"));
		System.out.println(c.isConformToConstraint("abcd"));
		System.out.println(c.isConformToConstraint("c"));
	}
	
	/**
	 * 测试无限制
	 */
	@Test
	public void test_07_isConformToConstraint_noStraint() {
		Condition c = new Condition("测试", "html", ConstraintType.STRING);
		System.out.println(c.isConformToConstraint("a"));
		System.out.println(c.isConformToConstraint("b"));
		System.out.println(c.isConformToConstraint("ab"));
		System.out.println(c.isConformToConstraint("abc"));
		System.out.println(c.isConformToConstraint("abcd"));
		System.out.println(c.isConformToConstraint("c"));
	}
	
	/**
	 * 测试无限制
	 */
	@Test
	public void test_08_isConformToConstraint_noStraint() {
		Condition c = new Condition("测试", "html");
		System.out.println(c.isConformToConstraint("a"));
		System.out.println(c.isConformToConstraint("b"));
		System.out.println(c.isConformToConstraint("ab"));
		System.out.println(c.isConformToConstraint("abc"));
		System.out.println(c.isConformToConstraint("abcd"));
		System.out.println(c.isConformToConstraint("c"));
	}
}
