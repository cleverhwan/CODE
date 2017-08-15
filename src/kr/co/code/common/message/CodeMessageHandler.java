package kr.co.code.common.message;

import java.text.MessageFormat;

import kr.co.code.common.exception.CodeException;
import kr.co.code.common.util.StringUtil;

import org.springframework.context.support.MessageSourceAccessor;

/**
 *
 *
 */
public class CodeMessageHandler
{
    //static WcpMessageCDTO msgDTO = new WcpMessageCDTO();
    //static WcpMessageCDAO msgDAO = new WcpMessageCDAO();
	
	private static MessageSourceAccessor messageSourceAccessor;
	
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}

    public static String getUserMessage(String code) {

        String userMessage = messageSourceAccessor.getMessage(code);
        if(userMessage == null || StringUtil.isEmpty(userMessage)) 
        	userMessage = "[NN0000]오류가 발생했습니다.<br>관리자에게 문의하십시오.";
        
        return userMessage;
    }
    
    /**
     * 이용자 메세지 조회
     *
     * @param code
     * @param param
     * @return
     */
    public static String getUserMessage(String code, String param) {
    	return getMessage(code, (new String[] {param} ));
    }
    
    /**
     * properties에서 메세지 조회
     * 파라미터 셋팅
     * @param code
     * @param param
     * @return
     */
    private static String getMessage(String code, String[] param) {
    	Object[] obj = (Object[])param;
    	String message = MessageFormat.format(getMessage(code), obj);
        return message;
    }
    
    /**
     * properties에서 메세지 조회
     * @param code
     * @return
     */
    private static String getMessage(String code) {
        String message = "";
        try{
        	message = messageSourceAccessor.getMessage(code);
        	if(message == null || StringUtil.isEmpty(message))   	message = "";
        }catch(Exception e){ }
        return message;
    }
    
    public static String getUserMessage(String code, String[] str) throws CodeException {

        String userMessage = getUserMessage(code,(Object[])str );

        return userMessage;
    }

    public static String getUserMessage(String code, Object[] obj) {

        String userMessage = null;
        String returnMessage=null;
        returnMessage = "[NN0000]오류가 발생했습니다.<br>관리자에게 문의하십시오.";
        /*
        try{
            msgDTO = msgDAO.selectMsg( code );
        }catch(SQLException sqle){
            throw new WcpException(sqle);
        }

        if(msgDTO == null)
        {
            returnMessage = "[NN0000]오류가 발생했습니다.<br>관리자에게 문의하십시오.";
        }
        else
        {
            if(msgDTO.getWebMsgCtt() == null || "".equals(msgDTO.getWebMsgCtt()) )
            {
                returnMessage = "[NS0000]오류가 발생했습니다.<br>관리자에게 문의하십시오.";
            }
            else
            {
                userMessage = msgDTO.getWebMsgCtt();
                returnMessage = MessageFormat.format(userMessage, obj);
            }

        }
        */
        return returnMessage;
    }


    public static String getSystemMessage(String code) {
        String systemMessage = null;
        systemMessage = "[NN0000]주어진 코드에 해당하는 메시지 객체가 없습니다.";
        /*
        msgDTO = msgDAO.selectMsg( code );

        if(msgDTO == null)
        {
            systemMessage = "[NN0000]주어진 코드에 해당하는 메시지 객체가 없습니다.";
        }
        else
        {
            if(msgDTO.getMsgCtt() == null || "".equals(msgDTO.getMsgCtt()) )
                systemMessage = "[NS0000] 주어진 코드에 해당하는 시스템 메시지가 존재하지 않습니다.";
            else
                systemMessage = msgDTO.getMsgCtt();
        }
        */
        return systemMessage;
    }
}

