package kr.co.code.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Random;


public class NumberUtil {

	private NumberUtil() {

	}

	public static String format(int i) {
		return format( new Integer(i) );
	}

	public static String format(int i, String pattern) {
		return format( new Integer(i), pattern );
	}

	public static String format(long l) {
		return format( new Long(l) );
	}

	public static String format(long l, String pattern) {
		return format( new Long(l), pattern );
	}

	public static String format(float f) {
		return format( new Float(f) );
	}

	public static String format(float f, String pattern) {
		return format( new Float(f), pattern );
	}

	public static String format(double d) {
		return format( new Double(d) );
	}

	public static String format(double d, String pattern) {
		return format( new Double(d), pattern );
	}

	public static String format(Number number) {
		return format(number, null);
	}

	public static String format(Number number, String pattern) {
		NumberFormat nf = getNumberFormat(pattern);
		return nf.format( number );
	}

	public static Number createNumber(String src) {
		return createNumber(src, null);
	}

	public static Number createNumber(String src, String pattern) {
//		if ( StringUtil.isBlank(src) ) return new Integer(0);
		try {
			NumberFormat nf = getNumberFormat(pattern);

			return nf.parse(src);
		} catch (ParseException e) {
			throw new RuntimeException("can not parse string number : " + src);
		}
	}

	public static Integer createInteger(String src) {
		Number number = createNumber(src);
		if ( Integer.class.isInstance( number ) ) {
			return (Integer) number;
		}

		return new Integer( number.intValue() );
	}

	public static Long createLong(String src) {
		Number number = createNumber(src);
		if ( Long.class.isInstance( number ) ) {
			return (Long) number;
		}

		return new Long( number.longValue() );
	}

	public static Double createDouble(String src) {
		Number number = createNumber(src);
		if ( Double.class.isInstance( number ) ) {
			return (Double) number;
		}

		return new Double( number.doubleValue() );
	}

	public static Float createFloat(String src) {
		Number number = createNumber(src);
		if ( Float.class.isInstance( number ) ) {
			return (Float) number;
		}

		return new Float( number.floatValue() );
	}

	public static int parseInt(String src) {
		return createNumber(src).intValue();
	}

	public static int parseInt(String src, int defaultValue) {
		if ( StringUtil.isBlank(src) ) return defaultValue;
		return createNumber(src).intValue();
	}

	public static long parseLong(String src) {
		return createNumber(src).longValue();
	}

	public static long parseLong(String src, long defaultValue) {
		if ( StringUtil.isBlank(src) ) return defaultValue;
		return createNumber(src).longValue();
	}

	public static double parseDouble(String src) {
		return createNumber(src).doubleValue();
	}

	public static double parseDouble(String src, double defaultValue) {
		if ( StringUtil.isBlank(src) ) return defaultValue;
		return createNumber(src).doubleValue();
	}

	public static float parseFloat(String src) {
		return createNumber(src).floatValue();
	}

	public static float parseFloat(String src, float defaultValue) {
		if ( StringUtil.isBlank(src) ) return defaultValue;
		return createNumber(src).floatValue();
	}

	public static Integer toInteger(Number number) {
		return toInteger(number, null);
	}

	public static Integer toInteger(Number number, Integer defaultValue) {
		if ( number == null ) return defaultValue;

		if ( Integer.class.isInstance(number) ) {
			return (Integer) number;
		}

		return new Integer(number.intValue());
	}

	public static Long toLong(Number number) {
		return toLong(number, null);
	}

	public static Long toLong(Number number, Long defaultValue) {
		if ( number == null ) return defaultValue;

		if ( Long.class.isInstance(number) ) {
			return (Long) number;
		}

		return new Long(number.longValue());
	}

	public static Float toFloat(Number number) {
		return toFloat(number, null);
	}

	public static Float toFloat(Number number, Float defaultValue) {
		if ( number == null ) return defaultValue;

		if ( Float.class.isInstance(number) ) {
			return (Float) number;
		}

		return new Float(number.floatValue());
	}

	public static Double toDouble(Number number) {
		return toDouble(number, null);
	}

	public static Double toDouble(Number number, Double defaultValue) {
		if ( number == null ) return defaultValue;

		if ( Double.class.isInstance(number) ) {
			return (Double) number;
		}

		return new Double(number.doubleValue());
	}


	/**
	 * 
	 * int 형 데이타를 가격 포맷으로 변환하여 이를 반환
	 * 
	 * @param m_sPrice
	 *            가격(ex,10000)
	 * @return 가격(10,000)
	 */
	public static String getFormatPrice(int m_sPrice) {
		DecimalFormat df = new DecimalFormat("###,###,###,##0");
		return df.format(m_sPrice);
	}

	/**
	 * 
	 * 문자열을 PRICE포맷으로 변환하는 METHOD
	 * 
	 * @param sSource
	 * @return
	 */
	public static String getFormatPrice(String sSource) {
		DecimalFormat decimalformat = null;
		String sFormat = null;

		try {
			decimalformat = new DecimalFormat("###,###,###,##0");
			sFormat = decimalformat.format(Long.parseLong(sSource));
		} catch (Exception e) {
			sFormat = "-1";
		}

		return sFormat;
	}


	/**
	 * 
	 * 전달받은 long 타입 데이터를 가격표시 형태대로 3자리마다 ',' 삽입하여 반환함
	 * 
	 * @param price
	 * @return
	 */
	public static String priceStr(long price) {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(price);
	}

	/**
	 * 
	 * 전달받은 long 타입 데이터를 가격표시 형태대로 3자리마다 ',' 삽입,
	 * 99999초과시는 99,999+로 반환함
	 * 
	 * @param price
	 * @return
	 */
	public static String priceStrPlus(long price) {
		long stdPrice = 99999;

		DecimalFormat df = new DecimalFormat("###,###");
		String resultValue = df.format(price);
		
		// 99999 초과시 "99,999+" 로 반환
		if(price > stdPrice) {
			resultValue = df.format(stdPrice)+"+";
		}
		
		return resultValue;
	}
	
	/**
	 * 
	 * 전달받은 문자열을 가격표시 형태대로 3자리마다 ',' 삽입하여 반환함
	 * 
	 * @param price
	 * @return
	 */
	public static String priceStr(String price) {
		if(StringUtil.isBlank(price)) {
			return "";
		}
		
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(Long.parseLong(price));
	}

	/**
	 *
	 * @param pattern
	 * @return
	 */
	private static NumberFormat getNumberFormat(String pattern) {
		if ( StringUtil.isBlank( pattern ) ) {
			return NumberFormat.getNumberInstance(Locale.KOREA);
		}

		DecimalFormat df = new DecimalFormat();
		df.applyPattern(pattern);
		return df;
	}

	/**
	 * 파일 사이즈 단위 변환
	 *
	 * @param size
	 *            long 형 타입의 파일 사이즈
	 * @return KB, MB, GB 로 변환한 사이즈
	 */
	public static String byteTranslater(long size) {

		NumberFormat nf = NumberFormat.getIntegerInstance();
		java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
		int intSize = 0;
		int kbyteSize = 1024;
		double doubleSize = 0;
		String returnSize = null;

		// 파일 사이즈가 1000, 2000 형식이므로 GB는 1024 가 아닌 1000을 기준으로.
		if (size >= (1000 * 1024 * 1024)) {
			intSize = new Long(size / (1000 * 1024 * 1024)).intValue();
			return nf.format(intSize) + "GB";

		// MB
		}else if (size > (kbyteSize * 1024)) {
			intSize = (int) (((double) size) / ((double) (kbyteSize * 1024)) * 100);
			doubleSize = (double) (((double) intSize) / 100);
			returnSize = df.format(doubleSize);

			if (returnSize.lastIndexOf(".") != -1) {

				if ("00".equals(returnSize.substring(returnSize.length() - 2, returnSize.length()))) {
					returnSize = returnSize.substring(0, returnSize.lastIndexOf("."));
				}
			}
			return returnSize + "MB";

		// KB
		}else if (size > kbyteSize) {
			intSize = new Long(size / kbyteSize).intValue();
			return nf.format(intSize) + "KB";

		}else {
			// return nf.format(size) + "Byte";
			return "1KB";
		}
	}


	/**
	 * 랜덤 숫자 생성 하기
	 *
	 * @param length
	 * 				생성할 자릿수
	 * @return	생성된 숫자
	 */
	public static String getRandNumber(int length) {

		char[] characters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		StringBuilder builder = new StringBuilder("");
		Random random = new Random();

		for(int i = 0; i < length; i++) {
			builder.append(characters[random.nextInt(characters.length)]);
		}

		return builder.toString();
	}

	/**
	 * 문자열을 Integer로 변경
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static int stringToInt(String str, int defaultValue)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return defaultValue;
        }
    }

	/**
	 * 문자열을 Double형으로 변경
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static double stringToDouble(String str, double defaultValue)
    {
        try
        {
            return Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return defaultValue;
        }
    }
}
