package com.sweetmanor.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 身份证号码工具类
 * 
 * @Description:
 *               <p>
 *               身份证号码：由17位数字本体码和1位数字校验码组成。<br>
 *               从左至右依次为：6位数字地址码，8位数字出生日期码， 3位数字顺序码和1位数字校验码。 <br/>
 *               地址码：行政区划代码。<br />
 *               出生日期码：出生年、月、日。<br />
 *               顺序码：顺序码的奇数分配给男性，偶数分配给女性。<br />
 *               校验码：根据前面17位数字码，按照ISO7064:1983.MOD11-2校验码计算出来的检验码。<br />
 * 
 *               <pre>
 *               	校验码计算方法:
 *               	<ul>
 *               		<li>将前面的身份证号码17位数分别乘以不同的系数。系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。</li>
 *               		<li>将这17位数字和系数相乘的结果相加。 </li>
 *               		<li>用加出来和除以11。</li>
 *               		<li>余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。其分别对应的最后一位身份证的号码为1－0－X－9－8－7－6－5－4－3－2。</li>
 *               	</ul>
 *               </pre>
 * 
 * @version 1.0 2015-12-31
 * @author ijlhjj
 */
public class IDCardUtil {
	private static final String[] PARITYBIT = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };// 校验位对应值
	private static final int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };// 计算校验位的系数
	private static final String CARD_PATTERN = "^\\d{17}(\\d|x|X)$";// 18位身份证号正则表达式
	private static final String CARD15_PATTERN = "^\\d{15}$";// 15位身份证号正则表达式

	private static Map<String, String> provinceMap;

	static {
		provinceMap = new HashMap<>();

		provinceMap.put("11", "北京");
		provinceMap.put("12", "天津");
		provinceMap.put("13", "河北");
		provinceMap.put("14", "山西");
		provinceMap.put("15", "内蒙古");
		provinceMap.put("22", "吉林");
		provinceMap.put("23", "黑龙江");
		provinceMap.put("31", "上海");
		provinceMap.put("32", "江苏");
		provinceMap.put("33", "浙江");
		provinceMap.put("34", "安徽");
		provinceMap.put("35", "福建");
		provinceMap.put("36", "江西");
		provinceMap.put("37", "山东");
		provinceMap.put("41", "河南");
		provinceMap.put("42", "湖北");
		provinceMap.put("43", "湖南");
		provinceMap.put("44", "广东");
		provinceMap.put("45", "广西");
		provinceMap.put("46", "海南");
		provinceMap.put("50", "重庆");
		provinceMap.put("51", "四川");
		provinceMap.put("52", "贵州");
		provinceMap.put("53", "云南");
		provinceMap.put("54", "西藏");
		provinceMap.put("61", "陕西");
		provinceMap.put("62", "甘肃");
		provinceMap.put("63", "青海");
		provinceMap.put("64", "宁夏");
		provinceMap.put("65", "新疆");
		provinceMap.put("71", "台湾");
		provinceMap.put("81", "香港");
		provinceMap.put("82", "澳门");
		provinceMap.put("91", "国外");
	};

	/**
	 * 对身份证号码做简单验证，其他内部方法都会调用此方法进行验证， 验证流程如下：
	 * 
	 * <pre>
	 * 		<ul>
	 * 			<li>标准化预处理；
	 * 			<li>判断长度是否15或18位；
	 * 			<li>判断是否符合身份证号模式匹配；
	 * 			<li>判断省份是否为正确值。
	 * 		</ul>
	 * </pre>
	 * 
	 * @param idCard 身份证号码，判断前会去除两边空格，所以两边包含空格的正确身份证号码也会返回true
	 * @return 是否正确的身份证号码
	 */
	public static boolean simpleCheck(String idCard) {
		idCard = standardizing(idCard);// 标准化处理
		if (idCard == null)
			return false;
		if (idCard.length() != 18 && idCard.length() != 15) // 位数判断
			return false;
		if (!idCard.matches(CARD_PATTERN) && !idCard.matches(CARD15_PATTERN)) // 模式匹配
			return false;
		String province = idCard.substring(0, 2);// 获取省份代码
		if (!provinceMap.containsKey(province)) // 判断区域代码
			return false;
		return true;
	}

	/**
	 * 获取身份证号码所在省，非法值返回null
	 * 
	 * @return 例： 北京 / 河北
	 */
	public static String getProvince(String idCard) {
		if (!simpleCheck(idCard)) // 先做简单验证，防止非法入参
			return null;
		idCard = standardizing(idCard);// 标准化处理，在simpleCheck检查中去除了两边空格，此处也必须进行同样的处理

		String province = idCard.substring(0, 2);
		return provinceMap.get(province);
	}

	/**
	 * 获取身份证号码性别，非法值返回null
	 * 
	 * @return 男 / 女
	 */
	public static String getGender(String idCard) {
		if (!simpleCheck(idCard)) // 先做简单验证，防止非法入参
			return null;
		idCard = standardizing(idCard);// 标准化处理，在simpleCheck检查中去除了两边空格，此处也必须进行同样的处理

		String gender = "";
		if (idCard.length() == 18) // 简单验证保证了位数必须是15或18位
			gender = idCard.substring(16, 17);
		else if (idCard.length() == 15) {
			gender = idCard.substring(14, 15);
		}
		int num = Integer.parseInt(gender);// 简单验证中要保证这里不会出现转换错误
		if (num % 2 == 0)
			return "女";
		else
			return "男";
	}

	/**
	 * 获取身份证号码出生日期，非法值返回null
	 */
	public static Date getBirthday(String idCard) {
		if (!simpleCheck(idCard)) // 先做简单验证，防止非法入参
			return null;
		idCard = standardizing(idCard);// 标准化处理，在simpleCheck检查中去除了两边空格，此处也必须进行同样的处理

		String birthday = "";
		if (idCard.length() == 18) // 简单验证保证了位数必须是15或18位
			birthday = idCard.substring(6, 14);
		else if (idCard.length() == 15) {
			birthday = idCard.substring(6, 12);
		}

		Date date = null;
		try {
			if (birthday.length() == 8)
				date = DateUtils.parseDateStrictly(birthday, "yyyyMMdd");
			else if (birthday.length() == 6)
				date = DateUtils.parseDateStrictly(birthday, "yyMMdd");
		} catch (ParseException e) {
			System.err.println("身份证出生日期格式转换发生错误！错误信息：" + e.getMessage());
		}

		return date;
	}

	/**
	 * 身份证号码校验，验证流程如下：
	 * 
	 * <pre>
	 * 		<ul>
	 * 			<li>标准化预处理；
	 * 			<li>判断长度是否15或18位；
	 * 			<li>判断是否符合身份证号模式匹配；
	 * 			<li>判断省份是否为正确值；
	 *          <li>判断出身日期是否为正确值；
	 *          <li>判断校验位是否正确。
	 * 		</ul>
	 * </pre>
	 * 
	 * @param idCard 身份证号码，判断前会去除两边空格，所以两边包含空格的正确身份证号码也会返回true
	 * @return 是否正确的身份证号码
	 */
	public static boolean check(String idCard) {
		if (!simpleCheck(idCard)) // 先做简单验证
			return false;
		idCard = standardizing(idCard);// 标准化处理，在simpleCheck检查中去除了两边空格，此处也必须进行同样的处理

		if (getBirthday(idCard) == null) // 校验出身日期合法性
			return false;

		if (idCard.length() == 15) // 经过以上校验，15位身份证号被认为校验完成
			return true;

		if (idCard.length() == 18) {// 18位身份证号校验
			String info = idCard.substring(0, 17);
			String check = idCard.substring(17);
			if (check.equalsIgnoreCase(checkBit(info)))
				return true;
		}

		return false;// 默认返回false
	}

	/**
	 * 根据身份证前17位数字获取校验位的值，非法入参将返回空字符串“”
	 * 
	 * @param str 身份证号前17位数字
	 * @return 校验位
	 */
	private static String checkBit(String str) {
		if (str == null || str.length() != 17) // 位数判断
			return "";
		if (!NumberUtils.isDigits(str)) // 确保入参是数字
			return "";

		int sum = 0;
		for (int i = 0; i < str.length(); i++) {// 分割每位数字乘以指定系数并求和
			String bit = str.substring(i, i + 1);
			int b = Integer.parseInt(bit);
			sum += b * POWER_LIST[i];// 对当前位乘以指定系数
		}

		int mod = sum % 11;// 求余
		return PARITYBIT[mod];// 返回对应的校验位
	}

	/**
	 * 将15位的身份证转换成18位身份证
	 * 
	 * @param idCard15 15位身份证号码
	 * @return 转换后的18位身份证号码，非法入参将返回null
	 */
	public static String convertIdcarBy15bit(String idCard15) {
		idCard15 = standardizing(idCard15);// 标准化处理

		if (idCard15.length() != 15) // 非15位身份证
			return null;

		String idCard = null;
		if (NumberUtils.isDigits(idCard15)) {// 必须是纯数字
			Date birthday = getBirthday(idCard15);// 获取出生日期
			if (birthday == null) // 出生日期转换错误时返回null
				return null;
			// 拼接前17位信息，2位年份转成了4位年份
			idCard = idCard15.substring(0, 6) + DateUtil.format(birthday, "yyyyMMdd") + idCard15.substring(12);
			String checkBit = checkBit(idCard);// 计算校验位
			idCard += checkBit; // 拼接校验位
		} else {
			return null;
		}

		return idCard;
	}

	/**
	 * 身份证号的标准化处理，处理流程如下：
	 * 
	 * <pre>
	 * 		<ul>
	 * 			<li>NULL值不进行处理；
	 * 			<li>两边去除空格；
	 * 			<li>如果有x转换为大写X；
	 * 		</ul>
	 * </pre>
	 */
	public static String standardizing(String idCard) {
		if (idCard == null)
			return null;
		String result = idCard.trim();
		result = result.toUpperCase();
		return result;
	}

}
