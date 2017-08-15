/**
 * <ul> 
 * <li>�꾨줈�앺듃紐�: LTT_ADMIN</li>
 * <li>�쒕툕�낅Т紐�: skt.ltt.admin.utils</li>
 * <li>�꾨줈洹몃옩紐�: MsgUtil.java</li>
 * <li>��         紐�: message �좏떥</li>
 * <li>����誘���: Parameter - </li>
 * <li>��   ��  ��	: (P056790)</li>
 * <li>��   ��  ��: 2014. 8. 12.</li>
 * </ul>
 * <pre>
 * </pre>
 */
package kr.co.code.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.code.common.message.CodePropertyHandler;


public class MsgUtil
{
	public static Map<String, String> getMmsDefaultMap (
			String msgId
			, String hpNum
			, String smsMsg
	) {

		Map<String, String> sMap = new HashMap<String, String>();
		
		sMap.put("SEQNO", msgId);
		sMap.put("INTIME", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));	
		sMap.put("USERCODE", "skchannel");						
		sMap.put("REQPHONE", CodePropertyHandler.getMessage("surem.sendPhnId"));					
		sMap.put("CALLPHONE", hpNum);						
		sMap.put("KIND", "M");					
		sMap.put("REQNAME", "");							
		sMap.put("CALLNAME", "");					
		sMap.put("MSG", smsMsg);					
		sMap.put("COUNTRY", "");
		sMap.put("SUBJECT", "");	
		sMap.put("REQTIME", "");				
		sMap.put("SENTTIME", "");
		sMap.put("RECVTIME", "0");
		sMap.put("RESULT", "");
		sMap.put("ERRCODE", "");
		sMap.put("FKCONTENT", "");
		sMap.put("RESULT", "");
		sMap.put("BATCHFLAG", "");
		
		return sMap;
	}
	
/*	public static Map<String, String> getSmsDefaultMap (
			String hpNum
			, String smsMsg
			, String reqTime
			, String result
	) throws Exception {
		
		Map<String, String> sMap = new HashMap<String, String>();
		
		SuremSmsVO suremVO = new SuremSmsVO();
		suremVO.setInTime(DateUtil.getCurrDateTime());
		suremVO.setSmsData(
					"skchannel",
					CodePropertyHandler.getMessage("surem.sendPhnId"),
					hpNum,
					smsMsg,
					reqTime,
					result
					);
		
		
		Method[] methods = suremVO.getClass().getMethods();
//		sMap.put( "sndPhnId", CodePropertyHandler.getMessage("surem.sendPhnId"));		
		
		String pttn = "get(.){1}(.*)";
		Pattern p = Pattern.compile(pttn);
		Matcher m;
		String s1 = "";
		String s2 = "";		
		
		for (int j = 0; j < methods.length; j++)
		{
			if (methods[j].getName().startsWith("get") && methods[j].getParameterTypes().length == 0)
			{
				m = p.matcher(methods[j].getName());
				if (m.find()) {
					s1 = m.group(1);
					s2 = m.group(2);
				}	
				sMap.put( s1.toLowerCase()+s2, String.valueOf( methods[j].invoke( suremVO, new Object[0])));
			}
		}						
		
		return sMap;
	}
	*/
	public static String getBindMsg (
			String msg
			, String[] aryVal
	) {
		
		if (!isEmpty( msg) && aryVal != null)
		{	
			String pttn = "(\\{\\d\\})";
			Pattern p = Pattern.compile(pttn);
			Matcher m = p.matcher(msg);
			
			int i = 0;
			while (m.find()) {
				if (aryVal.length > i)
				{
					msg = msg.replace(m.group(), aryVal[i]);
				}
				
				i++;
			}
			
			return msg;
		}
		else
		{
			return "";
		}
	}
	
	public static String getExtMsg (
			Map<String, String> msgMap
			, String[] args
			, String rplMsg
	) {
		String smsMsg = "";
		
		if (msgMap != null && msgMap.get( "USER_MSG") != null && !isEmpty( msgMap.get( "USER_MSG")))
			smsMsg = msgMap.get( "USER_MSG");
		else
			smsMsg = rplMsg;
		
		return MsgUtil.getBindMsg( smsMsg, args);
	}
	
	public static boolean isEmpty(Object obj) {
		if (obj == null) return true;
		else
			if ("".equals(eraseSpace((String)obj)))  return true;
			else return false;
	}	
	
	public static String eraseSpace(String str) {
		str = str.replaceAll(" ", "");
		return str;
	}
}
