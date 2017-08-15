package kr.co.code.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;


/**
 *
 */
public class StringUtil {
	/**
	 * Data 구분자
	 */
	public static final String SEPA_COL = "\\|\\*\\|";
	/**
	 * Data 행 구분자
	 */
	public static final String SEPA_ROW = "\\|\\^\\|";

    /**
     * 휴대전화 국번
     */
    private static final List<String> EXCHANGE_NO_LIST =
    		Arrays.asList(new String[] {
            "010", "011", "016", "017", "018", "019" });

    /*
     * HTML escape character 대상.
     */
    private static final String[][] HTML_ARRAY = {
        {"&" , "&amp;" },  // & - ampersand  <-- 맨위에 있어야 함.
        {"\"", "&quot;"},  // " - double-quote
        //{")" , "&#41;"  },
        //{"(" , "&#40;"  },
        //{"#" , "&#35;"  },
        //{"/" , "&#x2F;"  },
        //{"'" , "&#x27;"  },
        {"<" , "&lt;"  },  // < - less-than
        {">" , "&gt;"  }   // > - greater-than
    };

	/*
	 * 요청 파라메터 취약점 대상 문자. (하나아이엔에스 제공 정보 적용 - 2017.03.23)
	 */
	private static final String[][] aasPARAMETER_WEAKNESS_TOKEN = {
//		"'", "\"", "--", ")", ">", "<", "*/", "/", "+", ".", "\\", "%", "&", ",", "[", "]", "=", "@"
		{ "#", "&", "<", "\\(", "'", "\\\"", "eval\\((.*)\\)", "[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "script", "--" },
/* 수정 시작 (2017.05.25 15:01)
 * 파라메터 취약점에서 'or', 'OR'은 제외 (하나아이엔에스 요청사항)
		{ "or", "and", "OR", "AND", "'", "--", "%", "!", "eval\\((.*)\\)", "[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "script", ".." }
*/
		{ "and", "AND", "'", "--", "%", "!", "eval\\((.*)\\)", "[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "script", ".." }
/* 수정 종료 (2017.05.25 15:01) */
	};

    private StringUtil() {

    }

    /**
     * 주어진 문자열에 검색문자가 들어있는지 검사한다.
     *
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains("", *)      = false
     * StringUtil.contains("abc", 'a') = true
     * StringUtil.contains("abc", 'z') = false
     * </pre>
     *
     * @param str
     * @param searchChar
     * @return
     */
    public static boolean contains(String str, char searchChar) {
    	Random random = new Random();
        return contains(str, searchChar);
    }

    /**
     * 주어진 문자열에 검색문자열이 들어있는지 검사한다.
     *
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains(*, null)    = false
     * StringUtil.contains("", "")     = false
     * StringUtil.contains("abc", "")  = true
     * StringUtil.contains("abc", "a") = true
     * StringUtil.contains("abc", "z") = false
     * </pre>
     *
     * @param str
     * @param searchChar
     * @return
     */
    public static boolean contains(String str, String searchStr) {
        return StringUtils.contains(str, searchStr);
    }

    /**
     * 주어진 문자열에 대소문자를 구분하지 않고 검색문자열이 들어있는지 검사한다.
     *
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains(*, null)    = false
     * StringUtil.contains("", "")     = false
     * StringUtil.contains("abc", "")  = true
     * StringUtil.contains("abc", "a") = true
     * StringUtil.contains("abc", "z") = false
     * StringUtil.contains("abc", "A") = true
     * StringUtil.contains("abc", "Z") = false
     * </pre>
     *
     * @param str
     * @param searchChar
     * @return
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        return StringUtils.containsIgnoreCase(str, searchStr);
    }

    /**
     * 주어진 문자열이 널(null)인경우 빈("") 문자열을 리턴하고
     * 널 이외의 문자열은 그냥 리턴한다.
     *
     * <pre>
     * StringUtil.defaultString(null)  = ""
     * StringUtil.defaultString("")    = ""
     * StringUtil.defaultString("bat") = "bat"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String defaultString(String str) {
        return StringUtils.defaultString(str);
    }

    /**
     * 주어진 문자열이 널(null)인경우, 주어진 기본문자열을 리턴하고
     * 널 이외의 문자열은 그냥 리턴한다.
     *
     * <pre>
     * StringUtil.defaultString(null, "NULL")  = "NULL"
     * StringUtil.defaultString("", "NULL")    = ""
     * StringUtil.defaultString("bat", "NULL") = "bat"
     * </pre>
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultString(String str, String defaultStr) {
        return StringUtils.defaultString(str, defaultStr);
    }

    /**
     * 두 문자열을 비교하여 동일하면 true를 리턴한다.
     * 두 문자열에 trim을 적용한 후, 비교한다.
     *
     * <pre>
     * StringUtil.equals(null, null)    = true
     * StringUtil.equals(null, "abc")   = false
     * StringUtil.equals("abc", null)   = false
     * StringUtil.equals("abc", "abc")  = true
     * StringUtil.equals("abc", "abc ") = true
     * StringUtil.equals("abc", "ABC")  = false
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(trim(str1), trim(str2));
    }

    /**
     * 대소문자를 구분하지 않고 두 문자열을 비교하여 동일하면 true를 리턴한다.
     *
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return StringUtils.equalsIgnoreCase(trim(str1), trim(str2));
    }

    /**
     * 주어진 문자열에서 검색하고자 하는 문자열이 포함된 첫번째 인덱스를 리턴한다.
     * 주어진 문자열이 널(null)인경우, -1을 리턴한다.
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static int indexOf(String str, String searchStr) {
        return StringUtils.indexOf(str, searchStr);
    }

    /**
     * 주어진 문자열에서 startPos이후부터 검색하고자 하는 문자열이 포함된 첫번째 인덱스를 리턴한다.
     * 주어진 문자열이 널(null)인경우, -1을 리턴한다.
     *
     * <pre>
     * StringUtil.indexOf(null, *, *)          = -1
     * StringUtil.indexOf(*, null, *)          = -1
     * StringUtil.indexOf("", "", 0)           = 0
     * StringUtil.indexOf("aabaabaa", "a", 0)  = 0
     * StringUtil.indexOf("aabaabaa", "b", 0)  = 2
     * StringUtil.indexOf("aabaabaa", "ab", 0) = 1
     * StringUtil.indexOf("aabaabaa", "b", 3)  = 5
     * StringUtil.indexOf("aabaabaa", "b", 9)  = -1
     * StringUtil.indexOf("aabaabaa", "b", -1) = 2
     * StringUtil.indexOf("aabaabaa", "", 2)   = 2
     * StringUtil.indexOf("abc", "", 9)        = 3
     * </pre>
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static int indexOf(String str, String searchStr, int startPos) {
        return StringUtils.indexOf(str, searchStr, startPos);
    }

    /**
     * 공백(" "), 빈(""), 널 문자열을 검사한다.
     *
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param src
     * @return
     */
    public static boolean isBlank(String src) {
        return StringUtils.isBlank(src);
    }

    /**
     * 빈(""), 널 문자열을 검사한다.
     *
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {
        return StringUtils.isEmpty(src);
    }

    /**
     * 전달받은 문자열이 숫자이면 true를 리턴한다.
     *
     * <pre>
     * StringUtil.isNumeric(null)   = false
     * StringUtil.isNumeric("")     = false
     * StringUtil.isNumeric("  ")   = false
     * StringUtil.isNumeric("12 ")  = false
     * StringUtil.isNumeric("123")  = true
     * StringUtil.isNumeric("12 3") = false
     * StringUtil.isNumeric("ab2c") = false
     * StringUtil.isNumeric("12-3") = false
     * StringUtil.isNumeric("12.3") = false
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if ( StringUtil.isBlank(str) ) return false;
        return StringUtils.isNumeric(str);
    }

    /**
     * 주어진 문자열이 공백(' ')만 포함하고 있는지  검사한다.
     *
     * <pre>
     * StringUtil.isWhitespace(null)   = false
     * StringUtil.isWhitespace("")     = true
     * StringUtil.isWhitespace("  ")   = true
     * StringUtil.isWhitespace("abc")  = false
     * StringUtil.isWhitespace("ab2c") = false
     * StringUtil.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isWhitespace(String str) {
        return StringUtils.isWhitespace(str);
    }

    /**
     * 문자열의 왼쪽부터 길이만큼의 문자열을 리턴한다.
     * len은 0 이상의 정수이어야 한다. 음수일 경우 예외가 발생한다.
     *
     * <pre>
     * StringUtil.left(null, *)    = null
     * StringUtil.left("", *)      = ""
     * StringUtil.left("abc", 0)   = ""
     * StringUtil.left("abc", 2)   = "ab"
     * StringUtil.left("abc", 5)   = "abc"
     * </pre>
     *
     * @param str
     * @param len
     * @return
     */
    public static String left(String str, int len) {
        return StringUtils.left(str, len);
    }

    /**
     * 문자열의 왼쪽에 공백(' ')를 붙인다.
     * 전체 문자열의 크기가 size만큼 붙인다.
     *
     * <pre>
     * StringUtil.leftPad(null, *)   = null
     * StringUtil.leftPad("", 3)     = "   "
     * StringUtil.leftPad("bat", 3)  = "bat"
     * StringUtil.leftPad("bat", 5)  = "  bat"
     * StringUtil.leftPad("bat", 1)  = "bat"
     * StringUtil.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str
     * @param size
     * @return
     */
    public static String leftPad(String str, int size) {
        return StringUtils.leftPad(str, size);
    }

    /**
     * 문자열의 왼쪽에 주어진 문자를 붙인다.
     * 전체 문자열의 크기가 size만큼 붙인다.
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)     = null
     * StringUtil.leftPad("", 3, 'z')     = "zzz"
     * StringUtil.leftPad("bat", 3, 'z')  = "bat"
     * StringUtil.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtil.leftPad("bat", 1, 'z')  = "bat"
     * StringUtil.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String leftPad(String str, int size, char padChar) {
        return StringUtils.leftPad(str, size, padChar);
    }

    /**
     * 문자열의 왼쪽에 주어진 문자열을 붙인다.
     * 전체 문자열의 크기가 size만큼 붙인다.
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)      = null
     * StringUtil.leftPad("", 3, "z")      = "zzz"
     * StringUtil.leftPad("bat", 3, "yz")  = "bat"
     * StringUtil.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtil.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.leftPad("bat", 1, "yz")  = "bat"
     * StringUtil.leftPad("bat", -1, "yz") = "bat"
     * StringUtil.leftPad("bat", 5, null)  = "  bat"
     * StringUtil.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str
     * @param size
     * @param padStr
     * @return
     */
    public static String leftPad(String str, int size, String padStr) {
        return StringUtils.leftPad(str, size, padStr);
    }

    /**
     * 문자열을 소문자로 변환한다.
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        return StringUtils.lowerCase(str);
    }

    /**
     * 주어진 문자열에서 특정 포지션부터 주어진 길이만큼의 부분 문자열을 얻는다.
     * 만약 주어진 포지션이 음수인 경우, 오른쪽에서부터 인덱싱한다.
     *
     * <pre>
     * StringUtil.mid(null, *, *)    = null
     * StringUtil.mid("", 0, *)      = ""
     * StringUtil.mid("abc", 0, 2)   = "ab"
     * StringUtil.mid("abc", 0, 4)   = "abc"
     * StringUtil.mid("abc", 2, 4)   = "c"
     * StringUtil.mid("abc", 4, 2)   = ""
     * StringUtil.mid("abc", -2, 2)  = "ab"
     * </pre>
     *
     * @param str
     * @param pos
     * @param len
     * @return
     */
    public static String mid(String str, int pos, int len) {
        return StringUtils.mid(str, pos, len);
    }

    /**
     * 주어진 문자열에서 모든 제거문자를 제거한 후,
     * 문자가 제거된 문자열을 리턴한다.
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str
     * @param remove
     * @return
     */
    public static String remove(String str, char remove) {
        return StringUtils.remove(str, remove);
    }

    /**
     * 주어진 문자열에서 모든 제거문자를 제거한 후,
     * 문자가 제거된 문자열을 리턴한다.
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queuedz", new char[]{'u','z'}) = "qeed"
     * </pre>
     *
     * @param str
     * @param remove
     * @return
     */
    public static String remove(String str, char[] remove) {
        String result = str;
        for ( int i = 0; i<remove.length; i++) {
            result = remove(result, remove[i]);
        }
        return result;
    }

    /**
     * 주어진 문자열에서 모든 제거문자열을 제거한 후,
     * 문자열이 제거된 문자열을 리턴한다.
     *
     * <pre>
     * StringUtil.remove(null, *)        = null
     * StringUtil.remove("", *)          = ""
     * StringUtil.remove(*, null)        = *
     * StringUtil.remove(*, "")          = *
     * StringUtil.remove("queued", "ue") = "qd"
     * StringUtil.remove("queued", "zz") = "queued"
     * </pre>
     *
     * @param str
     * @param remove
     * @return
     */
    public static String remove(String str, String remove) {
       return StringUtils.remove(str, remove);
    }

    /**
     * 주어진 문자열에서 바꾸하고자하는 문자열을 바꿀 문자열로 대체한다.
     *
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("any", null, *)    = "any"
     * StringUtil.replace("any", *, null)    = "any"
     * StringUtil.replace("any", "", *)      = "any"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @param text
     * @param repl
     * @param with
     * @return
     */
    public static String replace(String text, String repl, String with) {
        return StringUtils.replace(text, repl, with);
    }

    /**
     * 문자열의 오른쪽부터 길이만큼의 문자열을 리턴한다.
     * len은 0 이상의 정수이어야 한다. 음수일 경우 예외가 발생한다.
     *
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right("", *)      = ""
     * StringUtil.right("abc", 0)   = ""
     * StringUtil.right("abc", 2)   = "bc"
     * StringUtil.right("abc", 5)   = "abc"
     * </pre>
     *
     * @param str
     * @param len
     * @return
     */
    public static String right(String str, int len) {
        return StringUtils.right(str, len);
    }

    /**
     * 문자열의 오른쪽에 공백(' ')를 붙인다.
     * 전체 문자열의 크기가 size만큼 붙인다.
     *
     * <pre>
     * StringUtil.rightPad(null, *)   = null
     * StringUtil.rightPad("", 3)     = "   "
     * StringUtil.rightPad("bat", 3)  = "bat"
     * StringUtil.rightPad("bat", 5)  = "bat  "
     * StringUtil.rightPad("bat", 1)  = "bat"
     * StringUtil.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str
     * @param size
     * @return
     */
    public static String rightPad(String str, int size) {
        return StringUtils.rightPad( str, size );
    }

    /**
     * 문자열의 오른쪽에 주어진 문자를 붙인다.
     * 전체 문자열의 크기가 size만큼 붙인다.
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)     = null
     * StringUtil.rightPad("", 3, 'z')     = "zzz"
     * StringUtil.rightPad("bat", 3, 'z')  = "bat"
     * StringUtil.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtil.rightPad("bat", 1, 'z')  = "bat"
     * StringUtil.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String rightPad(String str, int size, char padChar) {
        return StringUtils.rightPad(str, size, padChar);
    }

    /**
     * 문자열의 왼쪽에 주어진 문자열을 붙인다.
     * 전체 문자열의 크기가 size만큼 붙인다.
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)      = null
     * StringUtil.rightPad("", 3, "z")      = "zzz"
     * StringUtil.rightPad("bat", 3, "yz")  = "bat"
     * StringUtil.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.rightPad("bat", 1, "yz")  = "bat"
     * StringUtil.rightPad("bat", -1, "yz") = "bat"
     * StringUtil.rightPad("bat", 5, null)  = "bat  "
     * StringUtil.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str
     * @param size
     * @param padStr
     * @return
     */
    public static String rightPad(String str, int size, String padStr) {
        return StringUtils.rightPad(str, size, padStr);
    }

    /**
     * String 타입의 날짜를 yyyy mm dd 로 분리한다.
     * @param str
     * @return
     */
    public static String[] dateSplit(String str){
    	String[] dateSplit = new String[3];
    	dateSplit[0] = str.substring(0, 4);
    	dateSplit[1] = str.substring(4, 6);
    	dateSplit[2] = str.substring(6, 8);

		return dateSplit;

    }


    /**
     * 주어진 문자열을 공백(' ')문자로 분리한다.
     *
     * <pre>
     * StringUtil.split(null)       = null
     * StringUtil.split("")         = []
     * StringUtil.split("abc def")  = ["abc", "def"]
     * StringUtil.split("abc  def") = ["abc", "def"]
     * StringUtil.split(" abc ")    = ["abc"]
     * </pre>
     *
     * @param str
     * @return
     */
    public static String[] split(String str) {
        return StringUtils.split(str);
    }

    /**
     * 주어진 문자열을 구분문자로 분리한다.
     *
     * <pre>
     * StringUtil.split(null, *)         = null
     * StringUtil.split("", *)           = []
     * StringUtil.split("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtil.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringUtil.split("a:b:c", '.')    = ["a:b:c"]
     * StringUtil.split("a\tb\nc", null) = ["a", "b", "c"]
     * StringUtil.split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     *
     * @param str
     * @param separatorChar
     * @return
     */
    public static String[] split(String str, char separatorChar) {
        return StringUtils.split(str, separatorChar);
    }

    /**
     * 주어진 문자열을 구분문자열에 포함된 문자들로 분리한다.
     *
     * <pre>
     * StringUtil.split(null, *)         = null
     * StringUtil.split("", *)           = []
     * StringUtil.split("abc def", null) = ["abc", "def"]
     * StringUtil.split("abc def", " ")  = ["abc", "def"]
     * StringUtil.split("abc  def", " ") = ["abc", "def"]
     * StringUtil.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * StringUtil.split("ab:cd,ef", ":,") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] split(String str, String separatorChars) {
        return StringUtils.split(str, separatorChars);
    }

    /**
     * 주어진 문자열로부터 시작 인덱스부터 서브 문자열을 리턴한다.
     * 만약 시작 인덱스가 음수인 경우, 오른쪽에서부터 인덱싱한다.
     *
     * <pre>
     * StringUtil.substring(null, *)   = null
     * StringUtil.substring("", *)     = ""
     * StringUtil.substring("abc", 0)  = "abc"
     * StringUtil.substring("abc", 2)  = "c"
     * StringUtil.substring("abc", 4)  = ""
     * StringUtil.substring("abc", -2) = "bc"
     * StringUtil.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str
     * @param start
     * @return
     */
    public static String substring(String str, int start) {
        return StringUtils.substring(str, start);
    }

    /**
     * 주어진 문자열로부터 시작 인덱스부터 종료인덱스까지 서브 문자열을 리턴한다.
     * 만약 인덱스가 음수인 경우, 오른쪽에서부터 인덱싱한다.
     *
     * <pre>
     * StringUtil.substring(null, *, *)    = null
     * StringUtil.substring("", * ,  *)    = ""
     * StringUtil.substring("abc", 0, 2)   = "ab"
     * StringUtil.substring("abc", 2, 0)   = ""
     * StringUtil.substring("abc", 2, 4)   = "c"
     * StringUtil.substring("abc", 4, 6)   = ""
     * StringUtil.substring("abc", 2, 2)   = ""
     * StringUtil.substring("abc", -2, -1) = "b"
     * StringUtil.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String substring(String str, int start, int end) {
        return StringUtils.substring(str, start, end);
    }

    /**
     * 주어진 문자열로부터 컨트롤 문자들 (char <= 32)을 제거한다.
     *
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim("")            = ""
     * StringUtil.trim("     ")       = ""
     * StringUtil.trim("abc")         = "abc"
     * StringUtil.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return StringUtils.trim(str);
    }

    /**
     * 주어진 문자열로부터 컨트롤 문자들 (char <= 32)을 제거한다.
     * 만약 컨트롤문자들이 제거된 문자열이 null인 경우 빈문자열("")을 리턴한다.
     *
     * <pre>
     * StringUtil.trimToEmpty(null)          = ""
     * StringUtil.trimToEmpty("")            = ""
     * StringUtil.trimToEmpty("     ")       = ""
     * StringUtil.trimToEmpty("abc")         = "abc"
     * StringUtil.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        return StringUtils.trimToEmpty(str);
    }

    /**
     * 주어진 문자열로부터 컨트롤 문자들 (char <= 32)을 제거한다.
     * 만약 컨트롤문자들이 제거된 문자열이 빈문자열("")일 경우 null을 리턴한다.
     *
     * <pre>
     * StringUtil.trimToNull(null)          = null
     * StringUtil.trimToNull("")            = null
     * StringUtil.trimToNull("     ")       = null
     * StringUtil.trimToNull("abc")         = "abc"
     * StringUtil.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        return StringUtils.trimToNull(str);
    }

    /**
     * 주어진 문자열을 대문자로 변환한다.
     *
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        return StringUtils.upperCase(str);
    }

    /**
     * SQL문의 문자들을 escape처리한다.
     *
     * <xmp>
     * ex)
     * McHale's Navy => McHale''s Navy
     * </xmp>
     *
     * @param str
     * @return
     */
    public static String escapeSql(String str) {
        return StringEscapeUtils.escapeSql(str);
    }

    /**
     * HTML의 문자들을 escape처리한다.
     *
     * <xmp>
     * 대상 문자
     * & => &amp;  - ampersand
     * \ => &quot; - double-quote
     * < => &lt;   - less-than
     * > => &gt;   - greater-than
     * </xmp>
     *
     * @param str
     * @return
     */
    public static String escapeHtml(String str) {
        if ( isBlank(str) ) return str;

        String content = str;
        for( int i = 0; i<HTML_ARRAY.length; i++ ) {
            content = replace(content, HTML_ARRAY[i][0], HTML_ARRAY[i][1]);
        }
        return content;
    }

	/**
	 * XSS에 걸리는 문자를 없앤다. (하나아이엔에스 제공 정보 적용 - 2017.03.23)
	 *
	 * @param str	취약점 검사할 문자열
	 * @return 취약점 문자를 없앤 문자열
	 */
	public static Map<String, Object> cleanXSS( Map<String, Object> pMap ) {
		if ( pMap == null || pMap.isEmpty() ) {
			return new HashMap<String, Object>();
		}

		Set<String> Set_Name = pMap.keySet();
		for( String sName : Set_Name ) {
			String content = StringUtil.defaultString( (String) pMap.get( sName ) );
			for( int i = 0; i<aasPARAMETER_WEAKNESS_TOKEN[ 0 ].length; i++ ) {
				content = replace( content, aasPARAMETER_WEAKNESS_TOKEN[ 0 ][ i ], "" );
			}
			pMap.put( sName, content );
		}

		return pMap;
	}

	/**
	 * XSS에 걸리는 문자를 없앤다. (하나아이엔에스 제공 정보 적용 - 2017.03.23)
	 *
	 * @param str	취약점 검사할 문자열
	 * @return 취약점 문자를 없앤 문자열
	 */
	public static String cleanXSS( String str ) {
		if ( isBlank(str) ) return str;

		String content = str;
		for( int i = 0; i<aasPARAMETER_WEAKNESS_TOKEN[ 0 ].length; i++ ) {
			content = replace( content, aasPARAMETER_WEAKNESS_TOKEN[ 0 ][ i ], "" );
		}
		return content;
	}

	/**
	 * 취약점 문자를 없앤다.
	 *
	 * @param str	취약점 검사할 문자열
	 * @return 취약점 문자를 없앤 문자열
	 */
	public static Map<String, Object> cleanWeekness( Map<String, Object> pMap ) {
		if ( pMap == null || pMap.isEmpty() ) {
			return new HashMap<String, Object>();
		}

		Set<String> Set_Name = pMap.keySet();
		for( String sName : Set_Name ) {
			String content = StringUtil.defaultString( (String) pMap.get( sName ) );
			for( int i = 0; i<aasPARAMETER_WEAKNESS_TOKEN[ 1 ].length; i++ ) {
				content = replace( content, aasPARAMETER_WEAKNESS_TOKEN[ 1 ][ i ], "" );
			}
			pMap.put( sName, content );
		}

		return pMap;
	}

	/**
	 * 취약점 문자를 없앤 파라메터 맵을 반환한다. (값이 배열이면 첫 번째 파라메터만 반환한다)
	 *
	 * @param pRequest	HttpServletRequest 객체
	 * @return 취약점 문자를 없앤 파라메터 맵
	 */
	public static Map<String, String> cleanWeeknessParameter( HttpServletRequest pRequest ) {
		Map<String, String> Map_Result = new HashMap<String, String>();

		Map<String, String[]> Map_Param = pRequest.getParameterMap();
		if ( Map_Param != null && !Map_Param.isEmpty() ) {
			Set<String> Set_Key = Map_Param.keySet();

			for( String sKey : Set_Key ) {
				String[] asValue = Map_Param.get( sKey );

				if( asValue != null && asValue.length != 0 ) {
					asValue[ 0 ] = StringUtil.defaultString( asValue[ 0 ] );

					if( asValue[ 0 ] != null && !asValue[ 0 ].isEmpty() ) {
						for( int j = 0; j<aasPARAMETER_WEAKNESS_TOKEN[ 1 ].length; j++ ) {
							asValue[ 0 ] = replace( asValue[ 0 ], aasPARAMETER_WEAKNESS_TOKEN[ 1 ][ j ], "" );
						}
					}
				}

				Map_Result.put( sKey, asValue[ 0 ] );
			}
		}
		return Map_Result;
	}

	/**
	 * 취약점 문자를 없앤 파라메터 맵을 반환한다.
	 *
	 * @param pRequest	HttpServletRequest 객체
	 * @return 취약점 문자를 없앤 파라메터 맵
	 */
	public static Map<String, String[]> cleanWeeknessParameter2( HttpServletRequest pRequest ) {
		Map<String, String[]> Map_Result = new HashMap<String, String[]>();

		Map<String, String[]> Map_Param = pRequest.getParameterMap();
		if ( Map_Param != null && !Map_Param.isEmpty() ) {
			Set<String> Set_Key = Map_Param.keySet();

			for( String sKey : Set_Key ) {
				String[] asValue = Map_Param.get( sKey );

				if( asValue != null && asValue.length != 0 ) {
					for( int i = 0; i<asValue.length; i++ ) {
						asValue[ i ] = StringUtil.defaultString( asValue[ i ] );

						if( asValue[ i ] != null && !asValue[ i ].isEmpty() ) {
							for( int j = 0; j<aasPARAMETER_WEAKNESS_TOKEN[ 1 ].length; j++ ) {
								asValue[ i ] = replace( asValue[ i ], aasPARAMETER_WEAKNESS_TOKEN[ 1 ][ j ], "" );
							}
						}
					}
				}

				Map_Result.put( sKey, asValue );
			}
		}

		return Map_Result;
	}

	/**
	 * 취약점 문자를 없앤다.
	 *
	 * @param str	취약점 검사할 문자열
	 * @return 취약점 문자를 없앤 문자열
	 */
	public static String cleanWeekness( String str ) {
		if ( isBlank(str) ) return str;

		String content = str;
		for( int i = 0; i<aasPARAMETER_WEAKNESS_TOKEN[ 1 ].length; i++ ) {
			content = replace( content, aasPARAMETER_WEAKNESS_TOKEN[ 1 ][ i ], "" );
		}
		return content;
	}

    public static String unescapeHtml(String str) {
        if ( isBlank(str) ) return str;

        String content = str;
        for( int i = HTML_ARRAY.length - 1; i >= 0; i-- ) {
            content = replace(content, HTML_ARRAY[i][1], HTML_ARRAY[i][0]);
        }
        return content;
    }


    /**
     * 문자열의 길이를 리턴한다.
     *
     * <pre>
     * StringUtil.getLength(null)    = 0
     * StringUtil.getLength("")      = 0
     * StringUtil.getLength("     ") = 5
     * StringUtil.getLength("abc")   = 3
     * StringUtil.getLength(" abc ") = 5
     * </pre>
     *
     * @param src
     * @return
     */
    public static int getLength(String src) {
        if ( isEmpty(src) ) return 0;
        return src.length();
    }


    /**
     *
     * 전달받은 문자열의 인코딩을 ksc5601에서 8859_1로 변환하여 반환함
     *
     * @param passStr
     * @return
     */
    public static String han2eng(String passStr) {
        try {
            return new String(passStr.getBytes("KSC5601"), "8859_1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     *
     * 전달받은 문자열의 인코딩을 8859_1에서 ksc5601로 변환하여 반환함
     *
     * @param passStr
     * @return
     */
    public static String eng2han(String passStr) {
        try {
            return new String(passStr.getBytes("8859_1"), "KSC5601");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     *
     * 전달받은 문자열에서 앞부분의 연속된 '0'들을 제거하여 반환함
     *
     * @param value
     * @return
     */
    public static String eraseZero(String value) {
        if (value == null) return "";

        int index = 0;
        value = value.trim();

        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != '0')
                break;
            index++;
        }
        return value.substring(index);
    }

    /**
     *
     * 특정 문자열의 왼편에 붙어있는 '0'들을 제거하여 반환함<br>
     * 로직중복으로 인해서 내용 제거하고 eraseZero() 메소드 호출로 대체
     *
     * @param src
     * @return
     */
    public static String ltrimZero(String src) {
        return eraseZero(src);
    }

    /**
     *
     * 전달받은 문자열에 지정된 길이에 맞게 0을 채운다
     *
     * @param value
     * @param size
     * @return
     */
    public static String fillZero(String value, int size) {
        if (value == null || value.equals(""))
            return "";

        int count = size - value.getBytes().length;

        for (int i = 0; i < count; i++) {
            value = "0" + value;
        }

        return value;
    }

    /**
     *
     * 전달받은 문자열에서 지정한 문자열을 제거한 문자열을 리턴한다.
     *
     * <pre>
     *
     * </pre>
     *
     * @param des
     *            변환시킬 문자열
     * @param del
     *            삭제할 문자열
     * @return
     */
    public static String getRemoveChar( String des, String del )
    {
        StringTokenizer st = new StringTokenizer( des, del );
        StringBuffer str = new StringBuffer();

        while ( st.hasMoreTokens() ) {
            str.append( st.nextToken() );
        }

        return str.toString();
    }

    /**
     *
     * 전달받은 문자열에 space를 넣어서 지정한 길이를 만든다<br>
     * static 메소드인 fillSpace()를 호출함.
     *
     * @param des
     * @param size
     * @return
     */
    public static String getFillSpace(String des, int size) {
        return fillSpace(des, size);
    }

    /**
     *
     * 전달받은 문자열에 space를 넣어서 지정한 길이를 만든다
     *
     * @param des
     * @param size
     * @return
     */
    public static String fillSpace(String des, int size) {
        StringBuffer str = new StringBuffer();
        // des = replace(des, " ", " ");

        if (des == null) {
            for (int i = 0; i < size; i++)
                str.append(" ");
            return str.toString();
        }

        if (des.trim().length() > size)
            return des.substring(0, size);
        else
            des = des.trim();

        byte[] bDes = des.getBytes();
        int diffsize = size - bDes.length;
        str.append(des);

        for (int i = 0; i < diffsize; i++) {
            str = str.append(" ");
        }

        return str.toString();
    }

    /**
     *
     * 전달받은 문자열에 지정된 길이에 맞게 0을 채운다<br>
     *
     * @param des
     * @param size
     * @return
     */
    public static String getFillZero(String des, int size) {
        StringBuffer str = new StringBuffer();

        if (des == null) {
            for (int i = 0; i < size; i++)
                str.append("0");
            return str.toString();
        }

        if (des.trim().length() > size)
            return des.substring(0, size);
        else
            des = des.trim();

        int diffsize = size - des.length();

        for (int i = 0; i < diffsize; i++) {
            str = str.append("0");
        }

        str.append(des);

        return str.toString();
    }

    /**
     *
     * 전달받은 문자열의 내용이 0인지 검사한다
     *
     * @param des
     * @return
     */
    public static boolean isZero(String des) {
        if (!isNumeric(des)) return false;

        if (Integer.parseInt(des) == 0) return true;

        return false;
    }


    /**
     *
     * 전달받은 문자열이 null값이거나 빈문자열("")일 경우 참을 반환함
     *
     * <pre>
     * StringUtil.isNull(null)      = true
     * StringUtil.isNull("")        = true
     * StringUtil.isNull(" ")       = false
     * StringUtil.isNull("bob")     = false
     * StringUtil.isNull("  bob  ") = false
     * </pre>
     *
     * @param value
     * @return
     */
    public final static boolean isNull(String value) {
        return StringUtils.isEmpty(value);
    }

    /**
     *
     * 전달받은 문자열이 null이거나 "null"문자열이거나 빈문자열("")일 경우 ""를 반환함<br>
     * StringUtil.defaultString(String)를 권고.
     *
     * @deprecated isNulls() -> defaultString()
     * @param str
     * @return
     */
    public static String isNulls(String str) {
        String return_str = str;
        if (str == null || str.equals("null") || str.equals("")) {
            return_str = "";
        }
        return return_str;
    }

    /**
     *
     * 전달받은 문자열을 숫자로 변환하여 반환하되, 숫자가 아니라면 빈문자열("")을 반환함.
     *
     * @param value
     * @return
     */
    public final static String getNumber(String value) {
        if ( isBlank(value) ) return "";

        try {
            return Integer.parseInt(value.trim()) + "";
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    /**
     *
     * 전달받은 문자열이 한칸의 빈칸일 경우 (" ") 참을 반환함
     *
     * @param value
     * @return
     */
    public final static boolean isSpace(String value) {
        if (value != null && value.equals(" "))
            return true;
        return false;
    }

    /**
     *
     * 인자로 전달받은 데이터가 올바른 날짜 데이터인지 검사한다
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static boolean isValidDate(String year, String month, String date) {
        if (year == null || year.length() != 4 || !isNumeric(year))
            return false;

        if (month == null || month.length() > 2 || month.length() <= 0
                || !isNumeric(month))
            return false;

        if (date == null || date.length() > 2 || date.length() <= 0
                || !isNumeric(date))
            return false;

        return true;
    }

    /**
     *
     * null값이나 문자열 "null"일 경우 빈 문자열("") 로 변환하여 반환함
     *
     * @deprecated replaceNull() -> defaultString()
     * @param str
     * @return
     */
    public static String replaceNull(String str) {
        String result = str;

        if (str == null || "null".equals(str)) {
            result = "";
        }

        return result;
    }

    /**
     *
     * 스트링 치환 함수 주어진 문자열(buffer)에서 특정문자열('src')를 찾아 특정문자열('dst')로 치환
     *
     * @param buffer
     * @param src
     * @param dst
     * @return
     */
    public static String replaceAll(String buffer, String src, String dst) {
        if (buffer == null)
            return null;
        if (buffer.indexOf(src) < 0)
            return buffer;

        int bufLen = buffer.length();
        int srcLen = src.length();
        StringBuffer result = new StringBuffer();

        int i = 0;
        int j = 0;
        for (; i < bufLen;) {
            j = buffer.indexOf(src, j);
            if (j >= 0) {
                result.append(buffer.substring(i, j));
                result.append(dst);

                j += srcLen;
                i = j;
            } else
                break;
        }
        result.append(buffer.substring(i));
        return result.toString();
    }


    /**
     *
     * 전달받은 문자열이 유효한가를 판단하여 반환함<br>
     * null값이거나, 빈문자열("")이거나, 정수로 변환하여 0이면 false를 반환함
     *
     * @param value
     * @return
     */
    public final static boolean isValid(String value) {
        if ( isBlank(value) ) return false;

        try {
                return (Integer.parseInt(value.trim()) == 0) ? false : true ;
        } catch (NumberFormatException e) {
            throw e;
        }
    }


    /**
     *
     * 전달받은 문자열을 가격표시 형태대로 3자리마다 ',' 삽입하여 반환함
     *
     * @param dStr
     * @return
     */
    public static String getCurrDisplay(String dStr) {
        if (dStr == null)
            return "";

        String sep = ",";
        int sLoc;

        sLoc = dStr.length();

        while (sLoc > 3) {
            dStr = dStr.substring(0, sLoc - 3) + sep + dStr.substring(sLoc - 3);
            sLoc -= 3;
        }

        return dStr;
    }



/**
 *
 * 휴대폰 유효성 검사
 *
 * @param sSvcNum
 * @return 0(성공), 2009(실패)
 */
    public static String checkSvcNum(String sSvcNum){

            String sResult="2009";
            boolean bCk=false;
            String sTemp="";

            if(!sSvcNum.startsWith("0100")){
              //휴대전화 자리수 체크

                if((sSvcNum.length() >9) && (sSvcNum.length() <12) ){

                    //국번 체크
                    for(int i=0 ; i< EXCHANGE_NO_LIST.size();i++){
                        sTemp= (String) EXCHANGE_NO_LIST.get( i );
                        if(sTemp.equals(sSvcNum.substring(0,3))){
                            bCk= true ;
                            break;
                        }
                    }

                    if(bCk){
                      //숫자 체크
                        for(int i=0; i<sSvcNum.length(); i++) {
                            char c = sSvcNum.charAt(i);
                            if(Character.isDigit(c) == true) sResult="0";
                            else  break;

                        }
                    }

                }

            }  // end of first if
            return sResult;

        }
    /**
     * Method cropByte. 문자열 바이트수만큼 끊어주고, 생략표시하기
     * @param str 문자열
     * @param i 바이트수
     * @param trail 생략 문자열. 예) "..."
     * @return String
     */
      public static String cropByte(String str, int i, String trail) {
          if (str==null) return "";
          String tmp = str;
          int slen = 0, blen = 0;
          char c;
          try {
              //if(tmp.getBytes("MS949").length>i) {//2-byte character..
              if(tmp.getBytes("UTF-8").length>i) {//3-byte character..
                  while (blen+1 < i) {
                      c = tmp.charAt(slen);
                      blen++;
                      slen++;
                      if ( c  > 127 ) blen++;
                  }
                   tmp=tmp.substring(0,slen)+trail;
              }
          } catch(Exception e) {}
          return tmp;
      }

      /**
       * XSS 보안
       * @param src
       * @return
       */
      public static String escapeXss(String src) {
          int length = src.length();
          String comp = "";
          StringBuffer buffer = new StringBuffer("");
          for (int i=0; i<length; i++) {
              comp = src.substring(i, i+1);
              if ("\"".compareTo(comp) == 0) {
                  comp = src.substring(i, i);
                  buffer.append("&quot;");
              } else if ("\\".compareTo(comp) == 0) {
                  comp = src.substring(i, i);
                  buffer.append("￦");
              } else if ("<".compareTo(comp) == 0) {
                  comp = src.substring(i, i);
                  buffer.append("&lt;");
              } else if (">".compareTo(comp) == 0) {
                  comp = src.substring(i, i);
                  buffer.append("&gt;");
              }
              buffer.append(comp);
          }
          return buffer.toString();
      }

    /**
    *  받은 문자열의 숫자여부 체크와 받은 길이가 일치한지 확인하여 리턴해준다.
    *    param src 문자열
    *    param length 길이
    * @return
    */
    public static boolean checkNumber(String src, int length)
    {
        boolean rv = false;

        // 받은 문자열이 null이거나 공백이면  false를 리턴한다. 단 길이(length)가 0이면 길이 체크를 하지 않는다.
        if( src == null || "".equals(src) ) return false;
        else
        {
            // 그리고 받은 길이와 일치하지 않으면 false를 리턴한다. 단 길이(length)가 0이면 길이 체크를 하지 않는다.
            if(length !=0 && src.length() != length) return false;
        }

        // 받은 문자열의 숫자 검증을 한다.
        for(int i=0; i<src.length(); i++) {
            if(Character.isDigit(src.charAt(i)) )
            {
                rv = true;
            }
            else
            {
                rv = false;
                break;
            }
        }
        return rv;
    }

    public static String[] splitBizRgstNum(String bizRgstNum) {
    	String[] rtnVal = new String[3];

    	if(bizRgstNum.length() == 10) {
    		rtnVal[0] = bizRgstNum.substring(0, 3);
    		rtnVal[1] = bizRgstNum.substring(3, 5);
    		rtnVal[2] = bizRgstNum.substring(5, 10);
    	}

    	return rtnVal;
    }

    public static String replaceStr (String str, int start, int position){
    	if(str ==null){
    		return "";
    	}
    	int length = str.length();
    	String ast = "";
    	for(int i=0;i<position;i++){
    		ast +="*";
    	}
    	String reStr = str.replace(str.substring(start,(start+position)),ast);
    	return reStr;
    }

	public static String getFormatNum(String svc_num) {
		String svc_num1 = "000";
		String svc_num2 = "0000";
		String svc_num3 = "0000";

		if (svc_num.length() == 11) {
			return svc_num;
		} else if 	(svc_num.length() == 10) {
			svc_num1 = svc_num.substring(0, 3);
			svc_num2 = "0"+svc_num.substring(3, 6);
			svc_num3 = svc_num.substring(6);

			svc_num = svc_num1+svc_num2+svc_num3;
		}
		return svc_num;
	}
	
	/**
     * 주어진 문자열에서 바꾸하고자하는 문자열을 바꿀 문자열로 대체한다.
     *
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("any", null, *)    = "any"
     * StringUtil.replace("any", *, null)    = "any"
     * StringUtil.replace("any", "", *)      = "any"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @param text
     * @param repl
     * @param with
     * @return
     */
    public static String replaceArr(String text,String[] with){
    	int withLen = 0;
    	withLen = with.length;
        for ( int i = 0; i<withLen; i++) {
        	text = StringUtils.replace(text, "{"+i+"}", with[i]);
        }
    	
		return text;
    }
    
    /**
     * 숫자와 영문이 혼합된 랜덤 문자열 생성
     *
     * @param count
     * @return
     */
    public static String getRandomStr(int count) {
      	return RandomStringUtils.randomAlphanumeric(count);
    }

    public static String getRandomStr(int count, int special) {
      	return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
	 * 이메일 형식 체크
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		if ( isBlank(email) ) return false;
		Pattern p = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.(co.kr|com|net|kr)$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 수신제한 도메인 체크
	 * @param email
	 * @return
	 */
	public static boolean isAvailableDomain(String email) {
		boolean isAvailable = true;
		String emails[] = email.split("@");
		for ( String domains : UNUSED_EMAILS ) {
			if ( equals(domains, emails[emails.length -1]) ) {
				isAvailable = false;
				break;
			}
		}
		return isAvailable;
	}
	
    /**
	 * 이메일 형식 체크, 수신제한 도메인 체크
	 * @param email
	 * @return
	 */
	public static boolean isAvailableEmail(String email) {
		boolean isAvailable = isValidEmail(email);
		if ( isAvailable ) {
			isAvailable = isAvailableDomain(email);
			return isAvailable;
		} else {
			return isAvailable;
		}
	}
	
	public static String replaceAmp(String url){
    	if(url.equals("")){
    		return url;
	    }else{
    		try{	
	    		url = StringUtil.replaceAll(url, "&amp;", "&");
    		}catch(Exception e){
    		}	
    		return url;
	    }
	}		
	
	/**
	 * 셀렉트박스 이메일 도메인
	 */
	public static final List<String> EMAILS = Arrays.asList(new String[] {
			"naver.com", "nate.com", "korea.com", "hotmail.com",
			"hanmail.net", "gmail.com", "dreamwiz.com", "chol.com" });

	/**
	 * 수신제한 이메일 도메인
	 */
	public static final List<String> UNUSED_EMAILS = Arrays.asList(new String[] {
			"yahoo.co.kr", "empal.com", "paran.com",
			"lycos.co.kr", "hanmir.com", "hanafos.com" });
	
	 /**
     * 문자열의 Byte길이를 리턴한다.
     *
     * <pre>
     * StringUtil.getLength(null)    = 0
     * StringUtil.getLength("")      = 0
     * StringUtil.getLength("     ") = 5
     * StringUtil.getLength("abc")   = 3
     * StringUtil.getLength(" abc ") = 5
     * </pre>
     *
     * @param src
     * @return
     */
    public static int getByteLength(String src) {
        if ( isEmpty(src) ) return 0;
        return src.getBytes().length;
    }
    
	 /**
     * 개인정보가 포함된 문자열의 일부를 별표로 처리해서 리턴한다.
     *
     * <pre>
     * StringUtil.asterisk("홍길동", "name") = 홍*동
     * StringUtil.asterisk("(주)SKC&C", "name") = (주)****C
     * StringUtil.asterisk("tester1", "id") = tes****
     * StringUtil.asterisk("tester@sk.com", "email") = te****@sk.com
     * </pre>
     *
     * @param str
     * @param rule
     * @return
     */
    public static String asterisk(String str, String rule){
		String result = "";

		if("name".equals(rule)){
			int sIndex = str.indexOf("(") != -1 && str.indexOf(")") != -1 ? str.indexOf(")") + 1 : 0;
			int eIndex = str.length() - 1;

			if(str.length() == 2){
				result = str.charAt(0) + "*";
			}else{
				result = sIndex == 0 ? "" + str.charAt(0) : str.substring(0, sIndex);
				for(int i = sIndex; i < (sIndex == 0 ? eIndex - 1 : eIndex); i++){
					result += "*";
				}
				result += "" + str.charAt(eIndex);
			}
		}

		if("id".equals(rule)){
			int round = Math.round(str.length() / 2);
			result = str.substring(0, round);
			for(int i = round; i < str.length(); i++){
				result += "*";
			}
		}

		if("email".equals(rule)){
			String[] emails = str.split("@");

			result = emails[0].substring(0, 2);
			for(int i = 2; i < emails[0].length(); i++){
				result += "*";
			}
			result += "@" + emails[1];
		}

    	return result;
    }

	 /**
     * 개인정보가 포함된 전화번호의 일부를 별표로 처리해서 리턴한다.
     *
     * <pre>
     * StringUtil.asterisk("010-1234-5678", "phone", "-") = 010-****-5678
     * StringUtil.asterisk("021234567", "phone", "") = 02***4567
     * StringUtil.asterisk("03112345678", "phone", "") = 031****5678
     * </pre>
     *
     * @param str
     * @param rule
     * @return
     */
    public static String asterisk(String str, String rule, String separator){
		String result = "";

		if(str.indexOf(separator) != -1 && !"".equals(separator)){
			String[] nums = str.split(separator);

			for(int i = 0; i < nums.length; i++){
				if(i == 1){
					result += separator;
					for(int j = 0; j < nums[i].length(); j++){
						result += "*";
					}
					result += separator;
				}else{
					result += nums[i];
				}
			}
		}else{
			for(int i = 0; i < str.length(); i++){
				if("010".equals(str.substring(0, 3))){
					if(i < 3 || i > (str.length() == 11 ? 6 : 5)){
						result += str.charAt(i);
					}else{
						result += "*";
					}
				}else{
					int startIndex = "02".equals(str.substring(0, 2)) ? 2 : 3;
					int endIndex = (str.length() - 4) - 1;
					if(i < startIndex || i > endIndex){
						result += str.charAt(i);
					}else{
						result += "*";
					}
				}
			}
		}

    	return result;
    }
    
    /*
    * 만 나이 14세를 체크함.
    * 년월이 같아도 날짜가 지나지 않으면 만14세가 안되는 것으로 처리됨.
    */
	public static boolean isCriminalMinor(String age){
    	boolean result = false;
    	
    	DateFormat df = new SimpleDateFormat("yyyyMMdd");
    	String now = df.format(new Date());

		int yyyy = Integer.parseInt(now.substring(0, 4));
		int mm = Integer.parseInt(now.substring(4, 6));
		int dd = Integer.parseInt(now.substring(6));

		int year = Integer.parseInt(age.substring(0, 4));
		int month = Integer.parseInt(age.substring(4, 6));
		int date = Integer.parseInt(age.substring(6));

		if(yyyy - year > 14){
			result = true;
		}else{
			if(yyyy - year == 14){
				if(mm < month){
					result = true;
				}else{
					if(mm == month){
						if(dd > date){
							result = true;
						}else{
							result = false;
						}
					}else{
						result = false;
					}
				}
			}else{
				result = false;
			}
		}
		return result;
    }
	
	public static Map<String, Object> querystringToMap(String querystring) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();

		String[] params = querystring.split("&");  
	    for(String param : params){
	    	String[] pair = param.split("=");
	    	String key = URLDecoder.decode(pair[0], "UTF-8");
	    	String value = URLDecoder.decode(pair.length == 1 ? "" : pair[1], "UTF-8");
	        map.put(key, value);  
	    }  

		return map;
	}
	
  	public static int randomInt(int n1, int n2){
		return (int)(Math.random() * (n2 - n1 + 1)) + n1;
	}

  	public static String getNumber(int num){
		String result = "";
		for(int i = 0; i < num; i++){
			result += randomInt(0, 9);
		}
		return result;
	}

	public static String randomLower(){
      char ch = (char)((Math.random() * 26) + 97);
	  return "" + ch;
	}

	public static String getLower(int num){
		String result = "";
		for(int i = 0; i < num; i++){
			result += randomLower();
		}
		return result;
	}

	public static String randomUpper(){
      char ch = (char)((Math.random() * 26) + 65);
	  return "" + ch;
	}

	public static String getUpper(int num){
		String result = "";
		for(int i = 0; i < num; i++){
			result += randomUpper();
		}
		return result;
	}

	public static String randomSpecial(){
		String[] specials = {"!", "@", "#", "$", "%", "&", "*", "(", ")", "_", "-", "+", "=", "[", "]", "{", "}", "\\", "|", ":", "/", "?", ".", ",", ">", "<"};
		Random random = new Random();
		return specials[random.nextInt(specials.length)];
	}

	public static String getSpecial(int num){
		String result = "";
		for(int i = 0; i < num; i++){
			result += randomSpecial();
		}
		return result;
	}

	/*
	* String _str = shuffle("ZXCV0987");
	* System.out.println(_str);
	*/
	public static String shuffle(String a){
		return shuffle(a.split(""));
	}

	/*
	* String str = shuffle("abcd1234".split(""));
	* System.out.println(str);
	*/
    public static String shuffle(String[] a){
        int n = a.length;
        for(int i = 0; i < n; i++){
            int r = i + (int)(Math.random() * (n - i));
			String temp = a[i];
			a[i] = a[r];
			a[r] = temp;
        }

		String result = "";
		for(int i = 0; i < a.length; i++){
			result += a[i];
		}
		return result;
    }

	public static String password(int specials, int nums, int chars){
		int half1 = (int)Math.floor(chars / 2);
		int half2 = (int)Math.ceil(chars / 2);
		String result = getSpecial(specials) + getNumber(nums) + getLower(half1) + getUpper(half2);
		return shuffle(result);
	}
}