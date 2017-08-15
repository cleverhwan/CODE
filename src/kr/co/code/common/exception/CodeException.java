package kr.co.code.common.exception;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

import kr.co.code.common.logging.CodeLogger;
import kr.co.code.common.message.CodeMessageHandler;



/**
 * Mtc에서 사용하는 Exception을 정의해 두는 부분
 *
 * @author
 *
 */
public class CodeException extends Exception
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5775564396637630822L;

	// AUTH
	public static final String	AUTH_NOT_AUTHORIZED		= "SB0001";
	// Parameter Validation
	public static final String	PARAM_TYPE_MISMATCH		= "SB2001";
	public static final String	PARAM_LENGTH_MISMATCH	= "SB2010";
	public static final String	PARAM_LENGTH_SHORTER	= "SB2011";
	public static final String	PARAM_LENGTH_LONGER		= "SB2012";
	public static final String	PARAM_VALUE_MISMATCH	= "SB2020";
	public static final String	PARAN_PASSWD_MISMATCH	= "SB2021";
	public static final String	PARAN_VALUE_NOT_ALLOWED	= "SB2022";
	public static final String	PARAM_VALUE_NOT_ALOWED		= "ERR_1006";	//허용되지 않은 값이 입력되었습니다.
	public static final String	NOT_RESP_YN	= "SB2023";

	public static final String	LINK_SYSTEM_NOT_USED	= "SB3001";

	public static final String	NOT_ALLOW_FILE			= "SB4001";

	//
	public static final String	METHOD_NOT_SUPPORTED	= "SB8001";

	// Etc
	public static final String	ERROR_UNKNOWN			= "SB9999";

    private CodeLogger logger = CodeLogger.getLogger(this.getClass());

    private String errorCode;
    private String systemMessage;
    private String userMessage;

    public CodeException(Exception cause) {
        super(cause);
        Throwable th = cause.getCause();
        if (th instanceof ClassNotFoundException) {
            this.errorCode = "CS0001";
        } else if(th instanceof CloneNotSupportedException) {
            this.errorCode = "CS0002";
        } else if(th instanceof DataFormatException) {
            this.errorCode = "CS0003";
        } else if(th instanceof IllegalAccessException) {
            this.errorCode = "CS0004";
        } else if(th instanceof InstantiationException) {
            this.errorCode = "CS0005";
        } else if(th instanceof IOException) {
            this.errorCode = "CS0006";
        } else if(th instanceof NoSuchFieldException) {
            this.errorCode = "CS0007";
        } else if(th instanceof NoSuchMethodException) {
            this.errorCode = "CS0008";
        } else if(th instanceof SQLException) {
            this.errorCode = "CS0009";
        } else if(th instanceof BufferOverflowException) {
            this.errorCode = "CS0010";
        } else if(th instanceof ClassCastException) {
            this.errorCode = "CS0011";
        } else if(th instanceof ConcurrentModificationException) {
            this.errorCode = "CS0012";
        } else if(th instanceof IllegalArgumentException) {
            this.errorCode = "CS0013";
        } else if(th instanceof IllegalStateException) {
            this.errorCode = "CS0014";
        } else if(th instanceof IndexOutOfBoundsException) {
            this.errorCode = "CS0015";
        } else if(th instanceof NoSuchElementException) {
            this.errorCode = "CS0016";
        } else if(th instanceof NullPointerException) {
            this.errorCode = "CS0017";
        } else if(th instanceof UnsupportedOperationException) {
            this.errorCode = "CS0018";
        } else if(th instanceof RuntimeException) {
            this.errorCode = "CS0019";
        } else {
            this.errorCode = "CS0020";
        }

        this.userMessage = "시스템 오류입니다.<br>사이트관리자에게 문의하세요";

        StringBuffer temp = new StringBuffer();
        temp.append(cause.toString() + " (StackTrace : "+cause.getStackTrace().length + ")");
        for (int i = 0 ;i<cause.getStackTrace().length;i++) {
              temp.append("\n"+cause.getStackTrace()[i].toString());
        }
        this.systemMessage = temp.toString();
        
        logger.error("==>> CodeException(Exception cause) Occured! <<==\n[ userMessage : "
                        + getUserMessage() + " ]\n[ systemMessage : " + getSystemMessage() + " ]");
    }

    public CodeException(String errorCode) {
        this.errorCode = errorCode;
        try{
            this.systemMessage = CodeMessageHandler.getSystemMessage( errorCode );
            this.userMessage = CodeMessageHandler.getUserMessage( errorCode );
        }catch(Exception e){
            this.systemMessage = "CodeException 내부오류 : " + e.toString();
            this.userMessage = "시스템 오류입니다.<br>사이트관리자에게 문의하세요";
        }
        //this(errorCode, "", cause);
        logger.error("==>> CodeException(String errorCode) Occured! <<==\n[ errorCode : " + getErrorCode()
                + " ]\n[ userMessage : " + getUserMessage() + " ]\n[ systemMessage : " + getSystemMessage() + " ]");
    }

    public CodeException(String errorCode, String userMessage) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        logger.error("==>> CodeException(String errorCode, String userMessage) Occured! <<==\n[ errorCode : "
            + getErrorCode() + " ]\n[ userMessage : " + getUserMessage() + " ]");
    }


    public CodeException(String errorCode, Exception cause) {
        super(cause);

        this.errorCode = errorCode;
        try{
            this.systemMessage = CodeMessageHandler.getSystemMessage( errorCode );
            this.userMessage = CodeMessageHandler.getUserMessage( errorCode );
        }catch(Exception e){
            this.systemMessage = "CodeException 내부오류 : " + e.toString();
            this.userMessage = "시스템 오류입니다. <br> 사이트관리자에게 문의하세요";
        }
        //this(errorCode, "", cause);
        logger.error("==>> CodeException(String errorCode, Exception cause) Occured! <<==\n[ errorCode : " + getErrorCode()
            + " ]\n[ userMessage : " + getUserMessage() + " ]\n[ systemMessage : " + getSystemMessage() + " ]");
    }

    public CodeException(String errorCode, Exception cause, String[] param) {
        super(cause);

        this.errorCode = errorCode;
        try{
            this.systemMessage = CodeMessageHandler.getSystemMessage( errorCode );
            this.userMessage = CodeMessageHandler.getUserMessage( errorCode, param );
        }catch(Exception e){
            this.systemMessage = "CodeException 내부오류 : " + e.toString();
            this.userMessage = "시스템 오류입니다. <br> 사이트관리자에게 문의하세요";
        }
        logger.error("==>> CodeException(String errorCode, Exception cause, String[] param) Occured! <<==\n[ errorCode : " + getErrorCode()
            + " ]\n[ userMessage : " + getUserMessage() + " ]\n[ systemMessage : " + getSystemMessage() + " ]");
    }

    public CodeException(String errorCode, Exception cause, String param){
        String[] arryParam = new String[1];

        arryParam[0] = param;
        new CodeException(errorCode, cause, arryParam);
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }
    /**
     * @return the System Message
     */
    public String getSystemMessage() {
        return systemMessage;
    }
    /**
     * @return the errorMessage
     */
    public String getUserMessage() {
        return userMessage;
    }
}
