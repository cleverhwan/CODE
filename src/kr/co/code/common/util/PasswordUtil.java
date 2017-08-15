package kr.co.code.common.util;

import java.util.HashMap;
import java.util.Map;

public class PasswordUtil {
	
	/**
	 * 비밀번호 valication 검사
	 * @param pwd : 비밀번호
	 * @param id : 로그인 아이디
	 * @return
	 * code = "00", "11", "12", "13", "14", "15", "16"
	 */
	
	public static Map<String, String> validation( String pwd, String id ) {

		Map<String, String> result = new HashMap<String, String>();
		
		String code = "00";
		String msg = "성공";
		
		// 1. 길이 체크
		if( !isPwdRule1(pwd) ) {
			code = "11";
			msg = "비밀번호는 영문, 숫자 조합으로 8~20자리로 입력해 주세요.";
		}
		
		// 2. 사용자 ID 와 일치여부
		else if( !isPwdRule2(pwd, id) ) {
			code = "12";
			msg = "사용자 ID와 비밀번호는 같을 수 없습니다. 비밀번호를 다시 입력해 주세요.";
		}
		
		// 6. 사용자 ID가 비밀번호에 포함되는지
		else if( !isPwdRule6(pwd, id) ) {
			code = "16";
			msg = "비밀번호에는 아이디의 일부 문자를 쓸수 없습니다.";
		}
		
		// 3. 사용자 ID 중 연속 4자 중복 금지
		else if( !isPwdRule3(pwd, id) ) {
			code = "13";
			msg = "비밀번호에 사용자 ID의 문자열중에서 연속된 4자 이상 중복 사용 할 수 없습니다.";
		}
		
		// 4. 영문, 숫자, 특수문자, 공백 체크 - 허용되지 않는 문자 체크		
		else if( !isPwdRule4(pwd) ) {
			code = "14";
			msg = "비밀번호는 영문, 숫자, 특수문자 7개 (!,@,$,%,^,&,*)로 등록가능합니다.";
		}
		
		// 5. 사용순서대로 3자 이상 연속 금지 (abc, 123, abc789, Etc)
//		동일문자를 3번 이상 사용할 수 없습니다.
		else if( !isPwdRule5_1(pwd) ) {
			code = "15";
			msg = "동일문자를 3번 이상 사용할 수 없습니다.";
		}
//		연속된 문자열(123 또는 321, abc, cba 등)을 3자 이상 사용 할 수 없습니다.
		else if( !isPwdRule5_2(pwd) ) {
			code = "15";
			msg = "연속된 문자열(123 또는 321, abc, cba 등)을 3자 이상 사용 할 수 없습니다.";
		}
		// 
		else if( !isPwdRule6(pwd) ) {
			code = "16";
			msg = "비밀번호는 8자 이상 영문숫자 혼용, 특수문자 7개(!,@,$,%,^,&,*)을 사용하실 수 있습니다.";
		}
		
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	
	
	// 1. 비밀번호 길이 체크 8~20자리
	private static boolean isPwdRule1( String pwd ) {
		return (StringUtil.getByteLength(pwd) >= 8 && StringUtil.getByteLength(pwd) <= 20);
	}
	
	// 2. 사용자 ID와 비밀번호가 같은지 체크
	private static boolean isPwdRule2( String pwd, String id ) {
		return !(StringUtil.equals(pwd, id));
	}

	// 6. 사용자 ID에 비밀번호가 속해 있는지 여부 체크
	private static boolean isPwdRule6( String pwd, String id ) {
		return !(StringUtil.indexOf(id, pwd) > -1);
	}
	
	// 3. 사용자 ID 중 연속 4자 중복 금지
	private static boolean isPwdRule3( String pwd, String id ) {
		String str = null;
		for( int i=0; i<pwd.length(); i++ ) {
			if( i >StringUtil.getLength(pwd)-4 ) break;
			str = StringUtil.substring(pwd, i, i+4);
			if( StringUtil.indexOf(id, str) > -1 ) return false;
		}
		return true;
	}
	
	// 4. 영문, 숫자, 특수문자, 공백 체크 - 허용되지 않는 문자 체크
	private static boolean isPwdRule4( String pwd ) {
		char [] s = pwd.toCharArray();
		char [] symbol = { '!', '@', '$', '%', '^', '&', '*' };
		
		for( int i=0; i<s.length; i++ ) {
			if((s[i]>='0' && s[i]<='9') || (s[i]>='a' && s[i]<='z') || (s[i]>='A' && s[i]<='Z')) {
				continue;
			} else if( s[i] == ' ' ) {
				return false;
			} else {
				for( int j=0; j<symbol.length; j++ ) {
					if( s[i] == symbol[j] ) return true; 
				}
				return false;
			}
		}
		return true;
	}

	// 5. 사용순서대로 3자 이상 연속 금지 (abc, 123, abc789, Etc)
	private static boolean isPwdRule5_1( String pwd ) {
		char [] s = pwd.toCharArray();
		char chr_pass_0;
		char chr_pass_1;
		char chr_pass_2;

		for( int i=0; i<s.length-2; i++ ) {
			chr_pass_0 = s[i];
			chr_pass_1 = s[i+1];
			chr_pass_2 = s[i+2];
			if( chr_pass_0 == chr_pass_1 && chr_pass_0 == chr_pass_2 ) return false;
		}
		
		return true;
	}
	private static boolean isPwdRule5_2( String pwd ) {
		char [] s = pwd.toCharArray();
		char chr_pass_0;
		char chr_pass_1;
		char chr_pass_2;
		
		for( int i=0; i<s.length-2; i++ ) {
			chr_pass_0 = s[i];
			chr_pass_1 = s[i+1];
			chr_pass_2 = s[i+2];
			if( chr_pass_0 == chr_pass_1-1 && chr_pass_0 == chr_pass_2-2 ) return false;
			if( chr_pass_0 == chr_pass_1+1 && chr_pass_0 == chr_pass_2+2 ) return false;
		}
		
		return true;
	}
	private static boolean isPwdRule6( String pwd ) {
		
		String regex1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String regex2 = "1234567890";
		String regex3 = "\\!\\@\\$\\%\\^\\&\\*";
		
		int cnt = 0;
		
		for( int i=0; i<pwd.length(); i++) {
			if( regex1.indexOf( pwd.charAt(i) ) > -1 ) {
				cnt++;
				break;
			}
		}
		
		for( int i=0; i<pwd.length(); i++) {
			if( regex2.indexOf( pwd.charAt(i) ) > -1 ) {
				cnt++;
				break;
			}
		}
		
		for( int i=0; i<pwd.length(); i++) {
			if( regex3.indexOf( pwd.charAt(i) ) > -1 ) {
				cnt++;
				break;
			}
		}
		 
		if( cnt >= 2 ) 	return true; 
		else  				return false;
		
	}
}