package kr.co.code.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * <pre>
 * 이중 암호화 관련 암호화 모듈(SHA-256)
 * </pre>
 *
 * <pre>
 * <작업 History>
 *
 * 2012. 7. 04. : hosado
 * </pre>
 *
 * @author 성일경
 *
 */
public class Sha256
{
    /**
     *
     * <pre>
     * 암호화
     * </pre>
     *
     * @param word      문자열.
     * @return
     * @throws Exception
     */
//    public static String encode( String word ) throws Exception
//    {
//
//        MessageDigest md = MessageDigest.getInstance( "SHA-256" );
//        md.update( word.getBytes() );
//        byte[] raw = md.digest();
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode( raw );
//    }


    /**
     *
     * <pre>
     * 바이트코드를 Hex 코드로 전환
     * </pre>
     *
     * @param hash  해쉬코드
     * @return
     */
    private static String asHex( byte hash[] )
    {
        StringBuffer buf = new StringBuffer( hash.length * 2 );
        int i;

        for ( i = 0; i < hash.length; i++ ) {
            if ( (hash[i] & 0xff) < 0x10 )   buf.append( "0" );

            buf.append( Long.toString( hash[i] & 0xff, 16 ) );
        }
        return buf.toString();
    }


    /**
     *
     * <pre>
     * 문자열에 해쉬 함수 적용 처리 메소드.
     *
     * 사용방법
     * md.Sha256hash(password);
     * </pre>
     *
     * @param arg   해쉬함수 적용하기 위한 문자열(password 등)
     * @return
     */
    public static String SHA256hash( String arg )
    {
        return SHA256hash( arg.getBytes() );
    }

    /**
     *
     * <pre>
     * 실제 해쉬함수 적용 처리 로직
     * </pre>
     *
     * @param barray
     * @return
     */
    private static String SHA256hash( byte[] array )
    {
        byte[] result;

        try {
            MessageDigest m = MessageDigest.getInstance( "SHA-256" );
            m.reset();
            result = m.digest( array );
            return asHex( result );
        }
        catch ( NoSuchAlgorithmException ex ) {
        }
        return null;
    }

    /**
    *
    * <pre>
    * 문자열에 해쉬 함수 적용 처리 메소드.
    *
    * 사용방법
    * md.Sha256hash(password);
    * </pre>
    *
    * @param arg   해쉬함수 적용하기 위한 문자열(password 등)
    * @return
    */
   public static String SHA256hash(String arg, String encodedSalt ) throws Exception
   {
	   String salt = RsaCrypter.rsaDecrypt(encodedSalt);
       return SHA256hash( arg.getBytes("UTF-8"), salt.getBytes("UTF-8") );
   }


    /**
     *
     * <pre>
     * 실제 해쉬함수 적용 처리 로직
     * </pre>
     *
     * @param barray
     * @return
     */
    private static String SHA256hash( byte[] array, byte[] salt ) throws NoSuchAlgorithmException
    {
        byte[] result;

        MessageDigest m = MessageDigest.getInstance( "SHA-256" );
        m.reset();
        m.update(salt);
        result = m.digest( array );

        return asHex( result );
    }

    /**
     * salt를 생성하여 Base64로 변환하여 RSA 인코딩하여  리턴
     *
     * @return
     */
    public static String getSalt() throws Exception
    {
    	//salt 생성
    	String salt = new String(Base64.encodeBase64(Sha256.createSalt()));
    	//rsa 인코딩
    	return RsaCrypter.rsaEncrypt(salt);
    }

    /**
     * 32 bit salt 생성
     */
    private static byte[] createSalt() throws NoSuchAlgorithmException
    {
        byte[] salt = new byte[32];
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.nextBytes(salt);
        return salt;
    }


	public static void main(String[] args){

		try {
			String pwd = "1234";

			//암호화된 salt 생성 (for DB 저장)
			String encodedSalt = Sha256.getSalt();

			//암호화된 비밀번호 생성 (for DB 저장 및 로그인 시 비교)
			String encodedPwd = Sha256.SHA256hash(pwd, encodedSalt);

/*
[암호화된 salt]
bYx7LrJMKDO7f59Sba1JuFj7K/kH9SnzoRnqWO8amVFflPcf3GTXjR1G0dSH1YStYY4VVk2qMibrZyuFMT6FFSdzvR3m6nnSSeG4UtVt6bZ+5r0Kte2B6YBALL/22TDPj6AY5FDlYERFSRAem3nZDFMgLlRTqJ8B1GQiikuBP1w=
----------------------------------------------------
[암호화된 비밀번호]
01602d19b9c5751d2c539de7418f5afb04e123930208af75fa199a20a6a66130
*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
