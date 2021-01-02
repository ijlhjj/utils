package com.sweetmanor.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * 可序列化Person类示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class Person implements Serializable {
	private static final long serialVersionUID = -7932723685014215557L;

	private String name;
	private int age;
	// transient关键字指定在序列化时忽略此字段
	private transient String sex;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

	/**
	 * <pre>
	 * 	Java语言规范对equals方法提出的要求：
	 * 		1、自反性：对于非空引用，x.equals(x)返回true；
	 * 		2、对称性：x.equals(y)==y.equals(x)；
	 * 		3、传递性：x.equals(y)返回true，y.equals(z)返回true，则x.equals(z)返回true；
	 * 		4、一致性：如果x和y引用的对象没有发生变化，反复调用x.equals(y)应该返回相同结果；
	 * 		5、对于非空引用x,x.equals(null)应该返回false。
	 * 	如果子类中重写equals方法，应该优先调用父类的equals方法：super.equals(other)。
	 * </pre>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // 检测是否引用同一个对象，这是经常采用的优化形式
			return true;
		if (obj == null) // null值返回false，必须的检测
			return false;
		// 比较是否是同一个类
		if (getClass() != obj.getClass())
			return false;

		/*
		 * 如果子类拥有统一语义，则使用instinceof检测，例如Person类的子类都只判断ID来确定是否相等，
		 * 这时父类应该使用final修饰符确保equals方法不会被子类覆盖。
		 * 使用instinceof进行检测，子类的设计将极大的受到“对称性”的限制，因为子类的域没有合适的比对方式。
		 */
		// if (!(obj instanceof Person)) return false;

		// 类型转换后逐个比较关注的属性值，基本类型使用==，引用类型调用equals方法
		Person other = (Person) obj;

		return Objects.equals(name, other.name) && age == other.age;
	}

	/**
	 * <pre>
	 * 	hashCode方法定义应该与equals方法对应，即x.equals(y)返回true，则x.hashCode()==y.hashCode()
	 * 		Java7的Objects工具类为hashCode方法提供了2项改进： 
	 * 			1、Objects.hashCode()：替代了null值的判断；
	 * 			2、Objects.hash()：联合多个域的hashCode值。
	 * 		以下方法体可简化为：return Objects.hash(name, age);
	 * </pre>
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}