package com.tlb.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
			2, 1 };
	static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
	static private int[] ai = new int[18];
	private static final Random randoms = new Random();

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return "0000-00-00 00:00:00";

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String randNum(int n) {
		String rand = "";
		for (int i = 0; i < n; i++) {
			String rand_temp = String.valueOf(randoms.nextInt(10));
			rand += rand_temp;
		}
		return rand;
	}

	/**
	 * 获取时间随机数序号
	 * 
	 * @return
	 */
	public static String getNowDateRandomNumber() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + randoms.nextInt(10000);
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String shortFormatDate(Date date) {
		if (date == null)
			return "00000000";

		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @param
	 * @return
	 */
	public static Date shortParseDate(String str) {
		if (str == null) {
			return null;
		}
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String str) {
		if (str == null)
			return null;

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 判断字符是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumberString(String str) {
		if (str == null || str.length() == 0)
			return false;

		int i = 0;

		while (true) {
			if (i >= str.length())
				break;

			char ch = str.charAt(i);

			if (ch > '9' || ch < '0')
				break;

			i++;
		}

		return i == str.length();
	}

	/**
	 * 用户名为字母、数字或下划线、并不能以数字打头和纯数字
	 * 
	 * @param userName
	 * @return
	 */
	public static boolean isUserName(String userName) {
		boolean is = true;
		String regEx = "^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){3,15}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(userName);
		is = m.find();
		return is;
	}

	/**
	 * 判断是否是邮箱地址
	 * 
	 * @param eMail
	 * @return
	 */
	public static boolean isEmail(String eMail) {
		boolean is = true;
		String regEx = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(eMail);
		is = m.find();
		return is;
	}



	public static String wipescript(String html) {
		Pattern pattern1 = Pattern.compile("<script[^>]+>.*<\\/script>");
		Pattern pattern2 = Pattern.compile(" href *= *[\\s\\s]*script *:");
		Pattern pattern3 = Pattern.compile(" on[\\s\\s]*=");
		Pattern pattern4 = Pattern.compile("<iframe[\\s\\s]+</iframe *>");
		Pattern pattern5 = Pattern.compile("<frameset[\\s\\s]+</frameset *>");
		Matcher matcher1 = pattern1.matcher(html);
		html = matcher1.replaceAll(" ");
		Matcher matcher2 = pattern2.matcher(html);
		html = matcher2.replaceAll(" ");
		Matcher matcher3 = pattern3.matcher(html);
		html = matcher3.replaceAll(" ");
		Matcher matcher4 = pattern4.matcher(html);
		html = matcher4.replaceAll(" ");
		Matcher matcher5 = pattern5.matcher(html);
		html = matcher5.replaceAll(" ");
		return html;
	}

	/** 生成序号 */
	public static String getRedPagerNo(String str) {
		String no = Utility.formatDate(new Date());
		no = no.replaceAll("-", "");
		no = no.replaceAll(":", "");
		no = no.replaceAll(" ", "");
		String sRand = "";
		for (int i = 0; i < 5; i++) {
			String rand = String.valueOf(randoms.nextInt(10));
			sRand += rand;
		}
		return str + no + sRand;
	}

	/**
	 * 生成uuid
	 * 
	 * @return
	 */
	public static String randomUUID() {
		String uuid = java.util.UUID.randomUUID().toString();
		uuid = uuid.toUpperCase();
		uuid = uuid.replaceAll("-", "_");
		return uuid;
	}

	public static Long dateToLong(Date date) {
		String strDate = formatDate(date);
		strDate = strDate.replaceAll("-", "");
		strDate = strDate.replaceAll(":", "");
		strDate = strDate.replaceAll(" ", "");
		return Long.valueOf(strDate);
	}

	/**
	 * 判断是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}


	public static String getPropKey(String uri) {
		String str = "";
		InputStream is = (new Utility()).getClass().getResourceAsStream(uri);
		Properties props = new Properties();
		try {
			props.load(is);
			Set<Object> keys = new TreeSet<Object>();
			keys.addAll(props.keySet());
			for (Object object : keys) {
				str += (String) object + "|";
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getProps(String uri, String propName) {
		String propValue = "";
		InputStream is = (new Utility()).getClass().getResourceAsStream(uri);
		Properties props = new Properties();
		try {
			props.load(is);
			is.close();
			propValue = props.getProperty(propName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propValue;
	}

	// 根据身份证取性别
	public static int getSexByIdCard(String idCard) {
		try {
			int length = idCard.length();
			int i = Integer.valueOf(idCard.substring(length - 2, length - 1));
			if (15 == length) {
				i = Integer.valueOf(idCard.substring(length - 1, length));
			}
			if (i % 2 == 0) {
				return 2;
			}
			return 1;
		} catch (Exception e) {
			return 1;
		}
	}

	// 根据身份证取出生日期
	public static String getBirthByIdCard(String idCard) {
		try {
			int length = idCard.length();
			String year = "";
			String month = "";
			String day = "";
			if (15 == length) {
				year = idCard.substring(6, 8);
				year = (Integer.valueOf(year) > 10 ? ("19" + year)
						: ("20" + year)) + "-";
				month = idCard.substring(8, 10) + "-";
				day = idCard.substring(10, 12);
			}
			if (18 == length) {
				year = idCard.substring(6, 10) + "-";
				month = idCard.substring(10, 12) + "-";
				day = idCard.substring(12, 14);
			}
			return year + month + day;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 校验身份证的合法性
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean Verify(String idcard) {
		boolean flag = false;
		try {
			if (idcard.length() == 15) {
				idcard = uptoeighteen(idcard);
			}
			if (idcard.length() != 18) {
				flag = false;
			}

			String verify = idcard.substring(17, 18);
			String birth = idcard.substring(6, 10) + "-"
					+ idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
			if (Utility.isNum(idcard)) {
				if (verify.equals(getVerify(idcard))) {
					flag = true;
				}
			} else {
				flag = false;
			}
		} catch (EmptyStackException e) {
			flag = false;
		}
		return flag;
	}

	private static String getVerify(String eightcardid) {
		int remaining = 0;
		if (eightcardid.length() == 18) {
			eightcardid = eightcardid.substring(0, 17);
		}
		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}

	private static String uptoeighteen(String fifteencardid) {
		String eightcardid = fifteencardid.substring(0, 6);
		eightcardid = eightcardid + "19";
		eightcardid = eightcardid + fifteencardid.substring(6, 15);
		eightcardid = eightcardid + getVerify(eightcardid);
		return eightcardid;
	}
	
	/**
	 * 13位时间码+n随机码
	 */
	public static String getTimeStr() {
		Calendar c = Calendar.getInstance();
		Long l = c.getTimeInMillis();
		return l.toString();
	}
	
	public static String getTimeRandom(int n) {
		String random = getTimeStr() + randNum(n);
		return random;
	}

	/*
	判断当前时间是否在时间范围内HH:mm
	 */
	public static boolean inTime(String startTime, String endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			Date nowDate = sdf.parse(sdf.format(new Date()));
			if (nowDate.compareTo(startDate) >= 0){
				if(nowDate.compareTo(endDate) <= 0){
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(inTime("16:06" ,"16:07"));
	}
}