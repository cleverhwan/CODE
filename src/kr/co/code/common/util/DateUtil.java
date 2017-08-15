package kr.co.code.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

public class DateUtil {

    private static String WHITE_SPACE = " ";
    private static String DOT = ".";
    private static String HYPHEN = "-";
    private static String SLASH = "/";
    private static String COLON	= ":";

	public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
	public static final String SLASH_DATE_FORMAT = "yyyy/MM/dd";
	public static final String HYPHEN_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DOT_DATE_FORMAT = "yyyy.MM.dd";
	public static final String HANGEUL_DATE_FORMAT = "yyyy년MM월dd일";
	public static final String HANGEUL_DATE_FORMAT2 = "yyyy년 MM월 dd일";
	public static final String HANGEUL_DATE_FORMAT3 = "yyyy년 MM월 dd";
	public static final String HANGEUL_DATE_FORMAT4 = "MM월 dd일";
	public static final String HANGEUL_DATE_FORMAT5 = "M월 d일";
	public static final String HANGEUL_DATE_FORMAT6 = "yyyy년M월d일";

	public static final String DEFAULT_TIME_FORMAT = "HHmmss";
	public static final String COLON_TIME_FORMAT = "HH:mm:ss";
	public static final String HANGEUL_TIME_FORMAT = "HH시mm분ss초";

	public static final String DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT + DEFAULT_TIME_FORMAT;
	public static final String SLASH_DATETIME_FORMAT = SLASH_DATE_FORMAT + WHITE_SPACE + COLON_TIME_FORMAT;
	public static final String HYPHEN_DATETIME_FORMAT = HYPHEN_DATE_FORMAT + WHITE_SPACE + COLON_TIME_FORMAT;
	public static final String HANGEUL_DATETIME_FORMAT = HANGEUL_DATE_FORMAT + WHITE_SPACE + HANGEUL_TIME_FORMAT;

	private DateUtil() {

	}

	/**
	 * 주어진 날자를 일자(yyyyMMdd)형 문자열로 변환한다.<br>
	 *
	 * ex) 20070912
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format( date, DEFAULT_DATE_FORMAT );
	}

	/**
	 * 주어진 날자를 일자형 문자열(yyyy/MM/dd)로 변환한다.<br>
	 *
	 * ex) 2007/09/12
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateBySlash(Date date) {
		return format( date, SLASH_DATE_FORMAT );
	}

	/**
	 * 주어진 날자를 일자형 문자열(yyyy-MM-dd)로 변환한다.<br>
	 *
	 * ex) 2007-09-12
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateByHyphen(Date date) {
		return format( date, HYPHEN_DATE_FORMAT );
	}

	/**
	 * 주어진 날자를 일자형 문자열(yyyy년MM월dd일)로 변환한다.<br>
	 *
	 * ex) 2007년09월12일
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateByHangeul(Date date) {
		return format( date, DEFAULT_DATE_FORMAT );
	}

	/**
	 * 주어진 날자를 일자형 문자열(yyyy.MM.dd)로 변환한다.<br>
	 *
	 * ex) 2007.09.12
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateByDot(Date date) {
		return format( date, DOT_DATE_FORMAT );
	}
	/**
	 * 주어진 날자를 시간형 문자열(HHmmss)로 변환한다.(00-23시 표기형)<br>
	 *
	 * ex) 202510
	 *
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return format( date, DEFAULT_TIME_FORMAT );
	}

	/**
	 * 주어진 날자를 시간형 문자열(HH:mm:ss)로 변환한다.<br>
	 *
	 * ex) 20:25:10
	 *
	 * @param date
	 * @return
	 */
	public static String formatTimeByColon(Date date) {
		return format( date, COLON_TIME_FORMAT );
	}

	/**
	 * 주어진 날자를 시간형 문자열(HH시mm분ss초)로 변환한다.<br>
	 *
	 * ex) 20시25분10초
	 *
	 * @param date
	 * @return
	 */
	public static String formatTimeByHangeul(Date date) {
		return format( date, HANGEUL_TIME_FORMAT );
	}

	/**
	 * 주어진 날자를 일시형 문자열(yyyyMMddHHmmss)로 변환한다.<br>
	 *
	 * ex) 20070912202510
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format( date, DEFAULT_DATETIME_FORMAT );
	}

	/**
	 * 주어진 날자를 일시형 문자열(yyyy/MM/dd HH:mm:ss)로 변환한다.<br>
	 *
	 * ex) 2007/09/12 20:25:10
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTimeBySlash(Date date) {
		return format( date, SLASH_DATETIME_FORMAT );
	}

	/**
	 * 주어진 날자를 일시형 문자열(yyyy-MM-dd HH:mm:ss)로 변환한다.<br>
	 *
	 * ex) 2007-09-12 20:25:10
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTimeByHyphen(Date date) {
		return format( date, HYPHEN_DATETIME_FORMAT );
	}

	/**
	 * 주어진 날자를 일시형 문자열(yyyy년MM월dd일 HH시mm분ss초)로 변환한다.<br>
	 *
	 * ex) 2007년09월12일 20시25분10초
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTimeByHangeul(Date date) {
		return format( date, HANGEUL_DATETIME_FORMAT );
	}

	/**
	 * 주어진 날자를 특정 패턴형식으로 변환한다.<br>
	 *
	 * <pre>
	 * DateUtil.format(new Date(), "yyyy/MM/dd")          = 2007/09/12
	 * DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss") = 2007/09/12 20:25:10
	 * </pre>
	 *
	 * @param date
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern(pattern);

		return df.format(date);
	}

	/**
	 * 주어진 일자형 문자열(yyyyMMdd)을 날자타입으로 변환한다.
	 *
	 * ex) 20070912
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDate(String src) {
		return parseDate(src, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 주어진 일자형 문자열(yyyy/MM/dd)을 날자타입으로 변환한다.
	 *
	 * ex) 2007/09/12
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateBySlash(String src) {
		return parseDate(src, SLASH_DATE_FORMAT);
	}

	/**
	 * 주어진 일자형 문자열(yyyy-MM-dd)을 날자타입으로 변환한다.
	 *
	 * ex) 2007-09-12
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateByHyphen(String src) {
		return parseDate(src, HYPHEN_DATE_FORMAT);
	}

	/**
	 * 주어진 일자형 문자열(yyyy년MM월dd일)을 날자타입으로 변환한다.
	 *
	 * ex) 2007년09월12일
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateByHangeul(String src) {
		return parseDate(src, HANGEUL_DATE_FORMAT);
	}

	/**
	 * 주어진 일시형 문자열(yyyyMMddHHmmss)을 날자타입으로 변환한다.
	 *
	 * ex) 20070912092025
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateTime(String src) {
		return parseDate(src, DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 주어진 일시형 문자열(yyyy/MM/dd HH:mm:ss)을 날자타입으로 변환한다.
	 *
	 * ex) 2007/09/12 09:20:25
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateTimeBySlash(String src) {
		return parseDate(src, SLASH_DATETIME_FORMAT);
	}

	/**
	 * 주어진 일시형 문자열(yyyy-MM-dd HH:mm:ss)을 날자타입으로 변환한다.
	 *
	 * ex) 2007-09-12 09:20:25
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateTimeByHyphen(String src) {
		return parseDate(src, HYPHEN_DATETIME_FORMAT);
	}

	/**
	 * 주어진 일시형 문자열(yyyy년MM월dd일 HH시mm분ss초)을 날자타입으로 변환한다.
	 *
	 * ex) 2007년09월12일 09시20분25초
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDateTimeByHangeul(String src) {
		return parseDate(src, HANGEUL_DATETIME_FORMAT);
	}

	/**
	 * 주어진 문자열을 특정 패턴으로 날자타입으로 변환한다.
	 *
	 * <pre>
	 * DateUtil.parseDate("2007/09/12", "yyyy/MM/dd")
	 * DateUtil.parseDate("2007/09/12 20:25:10", "yyyy/MM/dd HH:mm:ss")
	 * </pre>
	 *
	 * @param src
	 * @return
	 */
	public static Date parseDate(String src, String pattern) {
		try {
			SimpleDateFormat df = new SimpleDateFormat();
			df.applyPattern(pattern);

			return df.parse( src );
		} catch (ParseException e) {
			throw new RuntimeException("can not parse string date : " + src + ":" + pattern);
		}
	}

	/**
	 * 현재 일시를 리턴한다.
	 *
	 * @return
	 */
	public static Date getCurrentDateTime() {
		return Calendar.getInstance().getTime();
	}

	public static String getCurrentDateString() {
		return formatDate(getCurrentDateTime());
	}

	public static String getCurrentDateStringBySlash() {
		return formatDateBySlash(getCurrentDateTime());
	}

	public static String getCurrentDateStringByHyphen() {
		return formatDateByHyphen(getCurrentDateTime());
	}

	public static String getCurrentDateStringByHangeul() {
		return formatDateByHangeul(getCurrentDateTime());
	}

	public static String getCurrentDateString(String pattern) {
		return format(getCurrentDateTime(), pattern);
	}

	public static String getCurrentDateTimeString() {
		return formatDateTime(getCurrentDateTime());
	}

	public static String getCurrentDateTimeStringBySlash() {
		return formatDateTimeBySlash(getCurrentDateTime());
	}

	public static String getCurrentDateTimeStringByHyphen() {
		return formatDateTimeByHyphen(getCurrentDateTime());
	}

	public static String getCurrentDateTimeStringByHangeul() {
		return formatDateTimeByHangeul(getCurrentDateTime());
	}

    /**
     * 두 날짜의 크기를 비교하여 date1이 크면 1, 같으면 0, 작으면 -1을 반환
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate( String date1, String date2 )
    {

        for ( int i = 0; i < date1.length(); i++ ) {
            if ( date1.charAt( i ) > date2.charAt( i ) ) {
                return 1;
            }
            else if ( date1.charAt( i ) < date2.charAt( i ) ) {
                return -1;
            }
            else {
                continue;
            }
        }

        return 0;

    }


    /**
     * 특정문자형 날짜형식의 특별한 타입의 일정량을 증가하거나 감소한 문자열을 반환( yyyymmdd )
     *
     * @param date
     * @param type
     * @param amt
     * @return
     */
    public static String getAddDate( String date, int type, int amt )
    {

        // 캘린더 객체 생성
        Calendar cal = Calendar.getInstance();

        // 문자열 날짜로 변환하여 시간 세팅
        cal.setTime( getDate( date ) );
        // 더함
        cal.add( type, amt );

        return getDate( cal.getTime() );

    }


    /**
     * 첫번째 인자의 날짜를 기준으로 두 번째 인자의 일 수를 더한 yyyymmdd 형태의 날짜를 반환
     *
     * @param ymd
     * @param day
     * @return
     */
    public static String getAddDateByDay( String ymd, int day )
    {
        return getAddDate( ymd, Calendar.DATE, day );
    }


    /**
     * 첫번째 인자의 날짜를 기준으로 두 번째 인자의 개월 수를 더한 yyyymmdd 형태의 날짜를 반환
     *
     * @param ymd
     * @param month
     * @return
     */
    public static String getAddDateByMonth( String ymd, int month )
    {
        return getAddDate( ymd, Calendar.MONTH, month );
    }


    /**
     * 첫번째 인자의 날짜를 기준으로 두 번째 인자의 연 수를 더한 yyyymmdd 형태의 날짜를 반환
     *
     * @param ymd
     * @param year
     * @return
     */
    public static String getAddDateByYear( String ymd, int year )
    {
        return getAddDate( ymd, Calendar.YEAR, year );
    }


	/**
	 *
	 * 현재 시간을 문자열로 변환하여 반환함<br>
	 * hh24miss0 형태로 반환.
	 *
	 * @return
	 */
	public static String getCurrentTime() {
		Calendar	date = Calendar.getInstance(TimeZone.getTimeZone("JST"));
		String 		hour = Integer.toString(date.get(Calendar.HOUR_OF_DAY));

		if (hour.length() == 1)
			hour = "0" + hour;

		String minute = Integer.toString(date.get(Calendar.MINUTE));

		if (minute.length() == 1)
			minute = "0" + minute;

		String second = Integer.toString(date.get(Calendar.SECOND));

		if (second.length() == 1)
			second = "0" + second;

		return new StringBuffer().append(hour).append(minute).append(
				second).append("0").toString();
	}

    /**
     * 현재 날짜를 스트링 형태로 반환
     * 예) 20031010
     *
     * @return 현재날짜
     */
    public static String getCurrDate()
    {
        return getCurrDate( "" );
    }

	/**
	 *
	 * 현재 날짜를 문자열로 변환하여 반환함<br>
	 * yyyy/mm/dd 형태로 반환
	 *
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar date = Calendar.getInstance(TimeZone.getTimeZone("JST"));
		String year = Integer.toString(date.get(Calendar.YEAR));
		String month = Integer.toString(date.get(Calendar.MONTH) + 1);

		if (month.length() == 1)
			month = "0" + month;

		String day = Integer.toString(date.get(Calendar.DAY_OF_MONTH));

		if (day.length() == 1)
			day = "0" + day;

		return new StringBuffer().append(year).append("/").append(month)
				.append("/").append(day).toString();
	}

    /**
     * 지금 현 시점의 Date형 객체를 반환
     *
     * @return
     */
    public static java.sql.Date getCurrSqlDate()
    {
        return new java.sql.Date( System.currentTimeMillis() );
    }


    /**
     * 전달받은 토큰으로 형식화 한 현재 날짜를 스트링 형태로 반환
     * 예) 2003-10-10
     *
     * @param tok
     * @return 형식화된 현재날짜
     */
    public static String getCurrDate( String tok )
    {
        return new SimpleDateFormat( "yyyy" + tok + "MM" + tok + "dd" ).format( new Date() );
    }


    /**
     * 현재 시간 및 날짜를 스트링 형태로 반환
     * 예) 20031010100333
     *
     * @return 현재날짜 및 시간
     */
    public static String getCurrDateTime()
    {
        return new SimpleDateFormat( "yyyyMMddHHmmss" ).format( new Date() );
    }

    /**
     * 현재 날짜/시간 얻기
     *
     * @param   f  날짜 형식
     *
     * @return  날짜 형식으로 포매팅된 현재 날짜 문자열
     */
    public static String getDateFormat(String f) {
        Date fdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(f);
        return sdf.format(fdate);
    }


    /**
     * 현재 날짜 및 시간, 밀리초를 스트링 형태로 반환<br>
     * 예)  20031010100333862
     *
     * @return
     */
    public static String getCurrDateTimeMillis()
    {
        return new SimpleDateFormat( "yyyyMMddHHmmssS" ).format( new Date() );
    }


    /**
     * 전달받은 토큰으로 형식화 한 현재 시간 및 날짜를 스트링 형태로 반환
     * 예) 2003-10-10 10:03:33
     *
     * @return 현재날짜 및 시간
     */
    public static String getCurrDateTime( String tokDate, String tokTime )
    {
        return new SimpleDateFormat( "yyyy" + tokDate + "MM" + tokDate + "dd" + " HH" + tokTime + "mm" + tokTime + "ss" )
                                                                                                                         .format( new Date() );
    }


    /**
     * 현재의 연도를 int형태로 반환
     * @return
     */
    public static int getCurrYear()
    {
        return Integer.parseInt( getCurrDate().substring( 0, 4 ) );
    }


    /**
     * 현재의 월을 int 형태로 반환
     *
     * @return
     */
    public static int getCurrMonth()
    {
        return Integer.parseInt( getCurrDate().substring( 4, 6 ) );
    }


    /**
     * 설정된 날자정보의 yyyymmdd형의데이타를 반환
     *
     * @return
     */
    public static String getDate( Date date )
    {
        return new SimpleDateFormat( "yyyyMMdd" ).format( date );
    }


    /**
     * 설정된 날짜정보의 yyyymmddhh24miss 형의 데이타를 반환
     *
     * @return
     */
    public static String getDateTime( Date date )
    {
        return new SimpleDateFormat( "yyyyMMddHHmmss" ).format( date );
    }


    /**
     * 설정된 TimeStamp정보의 yyyymmddhh24miss 형의 데이타를 반환
     *
     * @param Timestamp
     * @return
     */
    public static String getDateTime( Timestamp ts )
    {
        return new SimpleDateFormat( "yyyyMMddHHmmss" ).format( ts );
    }


    /**
     * 설정된 날짜정보의 hh24miss형태의 데이타를 반환
     *
     * @return
     */
    public static String getTime( Date date )
    {
        return new SimpleDateFormat( "HHmmss" ).format( date );
    }


    /**
     *
     * <pre>
     * 넘겨받은 hh24miss 시간 포맷 스트링으로 Date 형태로 변환하여 반환
     * </pre>
     *
     * @param time
     * @return
     */
    public static Date getTime( String time )
    {

        // 연월일 분리
        int hour = Integer.parseInt( time.substring( 0, 2 ) );
        int minuite = Integer.parseInt( time.substring( 2, 4 ) );
        int second = Integer.parseInt( time.substring( 4, 6 ) );

        // 캘린더 객체 생성 및 세팅
        Calendar cal = Calendar.getInstance();
        cal.set( 0, 0, 0, hour, minuite, second );

        return cal.getTime();

    }


    /**
     * 현재 설정된 날짜를 포맷팅하여 반환
     * 예) 2003/10/10
     *
     * @return
     */
    public static String getFormatDate( Date date )
    {
        return format( date, SLASH_DATE_FORMAT );
    }


    /**
     * 전달받은 날자와 토큰으로 형식화한 문자열 형태의 날짜정보를 반환
     *
     * @param date
     * @param tok
     * @return
     */
    public static String getFormatDate( Date date, String tok )
    {
        return new SimpleDateFormat( "yyyy" + tok + "MM" + tok + "dd" ).format( date );
    }


    /**
     * 현재 설정된 일시를 포맷팅하여 반환
     * 예) 20031010100333
     *
     * @return
     */
    public static String getFormatDateTime( Date date )
    {
        return getFormatDateTime( date, "/", ":" );
    }


    /**
     * 전달받은 날짜와 토큰들로써 포맷팅한 날짜정보를 반환
     *
     * @param date
     * @param tokDate
     * @param tokTime
     * @return
     */
    public static String getFormatDateTime( Date date, String tokDate, String tokTime )
    {
        return new SimpleDateFormat( "yyyy" + tokDate + "MM" + tokDate + "dd" + " HH" + tokTime + "mm" + tokTime + "ss" ).format( date );
    }


    /**
     * 현재 설정된 일시를 포맷팅하여 반환
     * 예) 20031010100333
     *
     * @return
     */
    public static String getFormatDateTime( Timestamp date )
    {
        return getFormatDateTime( date, "/", ":" );
    }


    /**
     * 전달받은 날짜와 토큰들로써 포맷팅한 날짜정보를 반환
     *
     * @param date
     * @param tokDate
     * @param tokTime
     * @return
     */
    public static String getFormatDateTime( Timestamp date, String tokDate, String tokTime )
    {
        return new SimpleDateFormat( "yyyy" + tokDate + "MM" + tokDate + "dd" + " HH" + tokTime + "mm" + tokTime + "ss" ).format( date );
    }


    /**
     * 문자열형태의 날짜정보를 형식화하여 반환
     *
     * @param date yyyymmdd형의 날짜정보
     * @return
     */
    public static String getFormatDate( String date )
    {
        return getFormatDate( date, "/" );
    }


    /**
     * 전달받은 문자열 날짜를 토큰으로 형식화해서 반환
     * 예) 2003-10-10
     *
     * @param date 형식화된 날짜
     */
    public static String getFormatDate( String date, String tok )
    {
    	if  (date ==null || date.equals("")) return "";
    	if (date.length()<7) {
    		return date.substring( 0, 4 ) + tok + date.substring( 4, 6 );
    	} else {
    		return date.substring( 0, 4 ) + tok + date.substring( 4, 6 ) + tok + date.substring( 6, 8 );
    	}
    }


    /**
     * 전달받은 문자열을 기본값으로 형식화한 후 반환
     *
     * @param date
     * @return
     */
    public static String getFormatDateTime( String date )
    {
        return getFormatDateTime( date, "/", ":" );
    }


    /**
     * 전달받은 문자열 날짜를 토큰으로 형식화해서 반환
     * 예) 2003-10-10 10:03:33
     *
     * @param date 형식화된 날짜
     */
    public static String getFormatDateTime( String date, String tokDate, String tokTime )
    {
    	if(date==null)
    		return "";
    	else if ( date.length()<14)
    		return  date;
    	
        StringBuffer sb = new StringBuffer();
        if(StringUtil.isNull(date)) {
        	return "";
        }
        sb.append( date.substring( 0, 4 ) ).append( tokDate ).append( date.substring( 4, 6 ) ).append( tokDate )
          .append( date.substring( 6, 8 ) );
        sb.append( " " ).append( date.substring( 8, 10 ) ).append( tokTime ).append( date.substring( 10, 12 ) )
          .append( tokTime ).append( date.substring( 12, 14 ) );

        return sb.toString();
    }

    /**
     * 전달받은 문자열 날짜를 토큰으로 형식화해서 반환 BR포함
     * 예) 2003-10-10 10:03:33
     *
     * @param date 형식화된 날짜
     */
    public static String getFormatDateTimeBr( String date, String tokDate, String tokTime )
    {
    	if(date==null)
    		return "";
    	else if ( date.length()<14)
    		return  date;

    	StringBuffer sb = new StringBuffer();

        sb.append( date.substring( 0, 4 ) ).append( tokDate ).append( date.substring( 4, 6 ) ).append( tokDate )
          .append( date.substring( 6, 8 ) );
        sb.append("<br />");
        sb.append( " " ).append( date.substring( 8, 10 ) ).append( tokTime ).append( date.substring( 10, 12 ) )
          .append( tokTime ).append( date.substring( 12, 14 ) );

        return sb.toString();
    }

    /**
     * 해당 년월의 1일의 요일정수를 반환
     *
     * @param year 연
     * @param month 월
     * @return
     */
    public static int getFirstDayOfWeek( int year, int month )
    {
        Calendar cal = Calendar.getInstance();
        cal.set( year, month - 1, 1 );

        return cal.get( Calendar.DAY_OF_WEEK );
    }


    /**
     * 해당 년월의 마지막 날짜를 반환
     *
     * @param year 연
     * @param month 월
     * @return
     */
    public static int getLastDay( int year, int month )
    {
        Calendar cal = Calendar.getInstance();
        cal.set( year, month - 1, 1 );

        return cal.getActualMaximum( Calendar.DATE );
    }


    /**
     * 해당 년월의 마지막 날짜를 반환함 - 인자로 yyyymmdd 형의 스트링을 받음
     *
     * @param ymd
     * @return
     */
    public static int getLastDay( String ymd )
    {
        int year = Integer.parseInt( ymd.substring( 1, 4 ) );
        int month = Integer.parseInt( ymd.substring( 4, 6 ) );

        return getLastDay( year, month );
    }

	/**
	 *
	 * 현재 날짜의 첫달 1일을 <br>
	 * yyyy.mm.dd 형태로 반환
	 *
	 * @return
	 */
	public static String getCurrentFirstDay() {
		Calendar date = Calendar.getInstance(TimeZone.getTimeZone("JST"));
		String year = Integer.toString(date.get(Calendar.YEAR));
		String month = Integer.toString(date.get(Calendar.MONTH) + 1);

		if (month.length() == 1)
			month = "0" + month;

		String day = "01";

		return new StringBuffer().append(year).append(".").append(month)
				.append(".").append(day).toString();
	}
    /**
     * 해당 년월의 주의 수를 반환함
     *
     * @param year 년
     * @param month 월
     * @return
     */
    public static int getWeekCnt( int year, int month )
    {
        int lastDay = getLastDay( year, month );

        Calendar cal = Calendar.getInstance();
        cal.set( year, month - 1, lastDay );

        return cal.get( Calendar.WEEK_OF_MONTH );
    }


    /**
     * 주말이면 참을 반환
     *
     * @param date yyyymmdd형태의 날짜문자열
     * @return
     */
    public static boolean isWeekend( String date )
    {

        int dayOfWeek = getDayOfWeek( date );

        return (dayOfWeek == 7 || dayOfWeek == 1) ? true : false;

    }


    /**
     * YYYYMMDD 스트링형 날짜의 요일코드를 반환
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek( String date )
    {

        // 캘린더 객체생성하여 날짜세팅
        Calendar cal = Calendar.getInstance();
        cal.setTime( getDate( date ) );

        // 요일코드를 받고 토요일/일요일이면 참
        return cal.get( Calendar.DAY_OF_WEEK );

    }


    /**
     * yyyymmdd형식에 맞는 스트링 형식의 날짜를
     * Date 객체로 생성하여 반환함
     *
     * @param   date        yyyymmdd형식의 날자스트링
     * @return
     */
    public static Date getDate( String date )
    {

        // 연월일 분리
        int year = Integer.parseInt( date.substring( 0, 4 ) );
        int month = Integer.parseInt( date.substring( 4, 6 ) ) - 1;
        int day = Integer.parseInt( date.substring( 6, 8 ) );

        // 캘린더 객체 생성 및 세팅
        Calendar cal = Calendar.getInstance();
        cal.set( year, month, day, 0, 0, 0 );

        return cal.getTime();

    }


    /**
     * 두 날짜타입의 차이나는 일수를 구하여 반환함
     *
     * @param   date1   대상날짜 1
     * @param   date2   대상날짜 2
     * @return
     */
    public static long getDateGap( Date date1, Date date2 )
    {

        // 첫번째 날짜의 타임스탬프 구함
        long time1 = date1.getTime();
        long time2 = date2.getTime();

        return new BigDecimal( Math.ceil( (double) (time1 - time2) / 1000 / 60 / 60 / 24 ) ).longValue();

    }


    /**
     * 두 스트링형 날짜 사이에 차이나는 일수를 구하여 반환함
     *
     * @param   date1   날짜스트링1
     * @param   date2   날짜스트링2
     * @return
     */
    public static long getDateGap( String date1, String date2 )
    {
        return getDateGap( getDate( date1 ), getDate( date2 ) );
    }


    /**
     * 현재 날짜와 스트링형 날짜 사이에 차이나는 일수를 구하여 반환함
     *
     * @param date1 yyyymmddgu 형의 날짜
     * @return
     */

    public static long getCurrDateGap(String date1){
    	String date2 = getCurrDate();
    	return getDateGap(getDate( date1 ), getDate( date2 ));
    }


    /**
     * 두 스트링형 날짜가 현재일시를 포함하는지 여부를 반환
     *
     * @param   date1       yyyymmdd형의 날짜
     * @param   date2       yyyymmdd형의 날짜
     * @return
     */
    public static boolean isInPeriod( String startDate, String endDate )
    {

        boolean flag = false;
        // 현재날짜
        String currDate = getCurrDate();

        // 날짜범위 내에 있으면 참
        if ( compareDate( currDate, startDate ) >= 0 && compareDate( currDate, endDate ) <= 0 ) {
            flag = true;
        }

        return flag;

    }


    /**
     * 요일코드에서 요일의 이름을 반환
     *
     * @param dayOfWeek
     * @return
     */
    public static String getDayOfWeekName( int dayOfWeek )
    {

        String[] dayNm = { " ", "일", "월", "화", "수", "목", "금", "토" };

        return dayNm[dayOfWeek];

    }


    /**
     * YYYYMMDD형 날짜에서 요일 이름을 추출하여 반환
     *
     * @param date
     * @return
     */
    public static String getDayOfWeekName( String date )
    {

        String[] dayNm = { " ", "일", "월", "화", "수", "목", "금", "토" };
        int dayOfWeek = getDayOfWeek( date );

        return dayNm[dayOfWeek];

    }


    /**
     *
     * <pre>
     * 6자리, 혹은 4자리의 시간을 입력 받아서 이를 다음과 같이 포매팅하여 반환.
     *
     * getFmtTimeString( "1022" ) => 10:22 AM
     *
     * </pre>
     *
     * @param dateTime
     * @return
     */
    public static String getFmtTimeString( String time )
    {
        DateFormat df = new SimpleDateFormat( "h:mm a", Locale.US );
        return df.format( getTime( (time.length() == 4) ? time + "00" : time ) );
    }

	/**
	 *
	 * 현재의 날짜와 시간을 (yyyymmddhh24miss) 형태로 변환하여 반환함
	 *
	 * @return
	 */
	public static String getCurrentTimeString() {

		Calendar cal = Calendar.getInstance();

		StringBuffer currentTime = new StringBuffer();
		String year = null;
		String month = null;
		String day = null;

		String hour = null;
		String minute = null;
		String second = null;

		year = String.valueOf(new Integer(cal.get(Calendar.YEAR)));
		currentTime.append(year);

		month = "00" + Integer.toString(cal.get(Calendar.MONTH) + 1);
		month = month.substring(month.length() - 2, month.length());
		currentTime.append(month);

		day = "00" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		day = day.substring(day.length() - 2, day.length());
		currentTime.append(day);

		hour = "00" + Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		hour = hour.substring(hour.length() - 2, hour.length());
		currentTime.append(hour);

		minute = "00" + Integer.toString(cal.get(Calendar.MINUTE));
		minute = minute.substring(minute.length() - 2, minute.length());
		currentTime.append(minute);

		second = "00" + Integer.toString(cal.get(Calendar.SECOND));
		second = second.substring(second.length() - 2, second.length());
		currentTime.append(second);

		return currentTime.toString();

	}

	/**
	 *
	 * 특정 문자열을 날짜포맷(yyyy.mm.dd)으로 변환하여 반환하되, 문자열 길이가 8자리 미만이면 그냥 반환
	 *
	 * @param m_sDate
	 * @return
	 */
	public static String getShortToDate(String date) {
		if (date == null) return "";

		if (date.length() < 8)
			return date;
		else
			return date.substring(0, 4) + "." + date.substring(4, 6)
					+ "." + date.substring(6, 8);
	}

	/**
	 *
	 * 날짜 포맷, 지정한 구분자로 날짜 포멧을 만들어준다<br>
	 * Ex) getDateFormat( "20020202", "/" ); => 2002/02/02
	 *
	 * @param m_sDate
	 * @param gubun
	 * @return
	 */
	public static String getDateFormat(String date, String gubun) {
		if (date == null) return "";

		if (date.length() < 8)
			return date;
		else
			return date.substring(0, 4) + gubun + date.substring(4, 6)
					+ gubun + date.substring(6, 8);
	}

	/**
	 *
	 * COIS형태의 데이터를 날짜데이터로 변환한다 XXXX년 X월X일
	 *
	 * @param date
	 * @return
	 */
	public static String getDateHan(String date) {
		String datestr = null;

		if (date == null || date.trim().equals(""))
			return "";
		date = date.replace(' ', '0');

		if (date.charAt(0) == '1') {
			datestr = "19" + date.substring(1, 3) + "년 " + date.substring(3, 5)
					+ "월 " + date.substring(5) + "일";

		} else {
			datestr = "20" + date.substring(1, 3) + "년 " + date.substring(3, 5)
					+ "월 " + date.substring(5) + "일";
		}

		return datestr;
	}

	/**
	 *
	 * COIS의 월 형태를 YYYY/MM형태로 바꿔 줌.
	 *
	 * @param s
	 * @return
	 */
	public static String getMonthString(String s) {
		if (s == null) return "";

		s = s.trim();
		if (s.length() < 5)
			return s.trim();

		String tmp = "";
		if (s.charAt(0) == '1')
			tmp = "19";
		else
			tmp = "20";

		return tmp + s.substring(1, 3) + "/" + s.substring(3);
	}

	/**
	 *
	 * 두개의 날짜를 받아들여 두 날짜를 포함하며, 두 날짜 사이의 달들을 반환
	 *
	 * @param sStartDate
	 * @param sEndDate
	 * @return
	 */
	public static String[] getPeriodMonth(String sStartDate, String sEndDate) {

		Calendar cal = Calendar.getInstance(Locale.KOREAN);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.KOREAN);
		Vector<String> vec = new Vector<String>();
		int iStringArrSize = 0;
		Date dStartDate = null;
		Date dEndDate = null;
		// int iPeriodCount = 0;

		// 시작 년월 SET
		cal.set(Integer.parseInt(sStartDate.substring(0, 4)),
				Integer.parseInt(sStartDate.substring(4)) - 1, 1);
		dStartDate = cal.getTime();
		// 시작 년월 SET
		cal.set(Integer.parseInt(sEndDate.substring(0, 4)),
				Integer.parseInt(sEndDate.substring(4)) - 1, 1);
		dEndDate = cal.getTime();

		while (!dEndDate.equals(dStartDate)) {
			vec.addElement(formatter.format(dStartDate));
			cal.add(Calendar.MONTH, 1);
			dStartDate = cal.getTime();
		}
		vec.addElement(formatter.format(dStartDate));
		iStringArrSize = vec.size();

		String[] months = new String[iStringArrSize];
		for (int i = 0; i < vec.size(); i++) {
			months[i] = (String) vec.elementAt(i);
		}

		return months;
	}


	/**
	 *
	 * YYYY/MM/DD형태의 날짜를 COIS에서 사용하는 날짜형태로 바꿔 줌.
	 *
	 * @param date
	 * @return
	 */
	public static String getDbDateString(String date) {
		StringBuffer buf = new StringBuffer("");

		if (date != null && !date.equals("") && !date.equals("0")) {
			int i = 0;
			StringTokenizer t = new StringTokenizer(date, "/");

			String tmp = null;
			while (t.hasMoreTokens()) {
				tmp = t.nextToken().trim();

				if (i == 0)
					tmp = tmp.substring(0, 1) + tmp.substring(2);
				else if (tmp.length() == 1)
					tmp = "0" + tmp;

				buf.append(tmp);
				i++;
			}
		} else
			buf.append("0");

		return buf.toString();
	}

	public static String getStrDateHan(String strDate) {
		if (strDate == null) return null;
		if (strDate.equals("")) return "";

		Date date = parseDate(strDate, DEFAULT_DATE_FORMAT);
		return format(date, HANGEUL_DATE_FORMAT2);
	}

	/**
	 * yyyy년 MM월 dd 형식으로 변환
	 * @param strDate
	 * @return
	 */
	public static String getStrDateHan2(String strDate) {
		if (strDate == null) return null;
		if (strDate.equals("")) return "";

		Date date = parseDate(strDate, DEFAULT_DATE_FORMAT);
		return format(date, HANGEUL_DATE_FORMAT3);
	}

	/**
	 * MM월 dd일 형식으로 변환
	 * @param strDate
	 * @return
	 */
	public static String getStrDateHan3(String strDate) {
		if (strDate == null) return null;
		if (strDate.equals("")) return "";

		Date date = parseDate(strDate, DEFAULT_DATE_FORMAT);
		return format(date, HANGEUL_DATE_FORMAT4);
	}

	/**
	 * M월 d일 형식으로 변환
	 * @param strDate
	 * @return
	 */
	public static String getStrDateHan4(String strDate) {
		if (strDate == null) return null;
		if (strDate.equals("")) return "";

		Date date = parseDate(strDate, DEFAULT_DATE_FORMAT);
		return format(date, HANGEUL_DATE_FORMAT5);
	}

	/**
	 * yyyy년M월d일 형식으로 변환
	 * @param strDate
	 * @return
	 */
	public static String getStrDateHan5(String strDate) {
		if (strDate == null) return null;
		if (strDate.equals("")) return "";

		Date date = parseDate(strDate, DEFAULT_DATE_FORMAT);
		return format(date, HANGEUL_DATE_FORMAT6);
	}

	/**
	 * yyyyMMdd형식으로 변환
	 * @param date
	 * @return
	 */
	public static String formatDate (String date){
		if (date == null) return null;

		String resultDate = null;
		if(date.indexOf(HYPHEN) > 0 ){
			resultDate = formatDate(parseDate(date,HYPHEN_DATE_FORMAT));
		}else if(date.indexOf(DOT) > 0){
			resultDate = formatDate(parseDate(date,DOT_DATE_FORMAT));
		}else if(date.indexOf(SLASH) > 0){
			resultDate = formatDate(parseDate(date,SLASH_DATE_FORMAT));
		}else{
			resultDate = date;
		}
		return resultDate;
	}

	public static String formatTime (String time){
		if (time == null) return null;

		String resultTime = null;
		if(time.indexOf(COLON) > 0){
			resultTime = formatTime(parseDate(time,COLON_TIME_FORMAT));
		}else{
			resultTime = time;
		}
		return resultTime;
	}
	
	/**
	 *
	 * Ex) getFormetDateTime( "153000", ":" ); => 15:30:00
	 *
	 * @param m_sDate
	 * @param gubun
	 * @return
	 */
	public static String getFormetDateTime(String date, String tokTime){
		 StringBuffer sb = new StringBuffer();
	        if(StringUtil.isNull(date)) {
	        	return "";
	        }
	      sb.append( " " ).append( date.substring( 0, 2 ) ).append( tokTime ).append( date.substring( 2, 4 ) )
	          .append( tokTime ).append( date.substring( 4, 6 ) );

	        return sb.toString();
	}  
	
	/**
	 * 지정된 날짜만큼 더하거나 뺀 날짜를 구한다.
	 *
	 * @param date (yyyyMMdd)
	 * @return
	 */
	public static String dateAdd(String date, int days) {
	    Calendar cal = Calendar.getInstance();
	    int year=0, month=0, day=0;
	    try {
			year   = Integer.parseInt(date.substring(0,4));
		    month  = Integer.parseInt(date.substring(4,6));
		    day    = Integer.parseInt(date.substring(6,8));
	    } catch (Exception e) {}
		cal.set(year, month-1, day+days);
		return getDate(cal.getTime(), "yyyyMMdd");
	}
	
	/**
     * 지정한 날짜를 지정한 날짜 형식으로 포매팅한다.
     *
     * @param   d   날짜
     * @param   f   날짜 형식
     *
     * @return  지정된 날짜/시간 문자열
     */
    public static String getDate(Date d, String f) {
        SimpleDateFormat sdf = new SimpleDateFormat(f);
        return sdf.format(d);
    }
}