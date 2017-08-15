package kr.co.code.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import kr.co.code.common.message.CodePropertyHandler;

import org.apache.commons.codec.binary.Base64;


/**
 * <pre>
 *
 * PRE 시스템에서 전달된 RSA 암호화 유틸리티
 *
 * salt 암호화시 사용됨
 *
 * </pre>
 *
 * @author  : 정유석
 * @version : 1.0(2013. 3. 6.) 최초생성
 */
public class RsaCrypter {

	/**
	 * RSA 공개키 파일로 암호화
	 *
	 * @param data : 복구화값
	 * @return cipherData_enc : 암호화값
	 * @throws Exception
	 */
	public static String rsaEncrypt(String data) throws Exception {
		//PublicKey publicKey = getPublicKey("C:/SLTT_/workspace_SLTT_/LTT_Common/lte_keystore/public.key");
		PublicKey publicKey = getPublicKey(CodePropertyHandler.getMessage("rsa.public.key.file"));

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherData_enc = cipher.doFinal(data.getBytes());

		return new String(Base64.encodeBase64(cipherData_enc));
	}

	/**
	 * RSA 비공개키 파일로 복구화
	 *
	 * @param data : 암호화값
	 * @param configurationService
	 * @return cipherData_dec : 복구화값
	 * @throws Exception
	 */
	public static String rsaDecrypt(String data) throws Exception {
		//PrivateKey privateKeyKey = getPrivateKey("C:/SLTT_/workspace_SLTT_/LTT_Common/lte_keystore/private.key");
		PrivateKey privateKeyKey = getPrivateKey(CodePropertyHandler.getMessage("rsa.private.key.file"));

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKeyKey);
		byte[] cipherData_dec = cipher.doFinal(Base64.decodeBase64(data.getBytes()));

		return new String(cipherData_dec);
	}

	/**
	 * RSA 공개키 파일을 가져온다.
	 *
	 * @param keyFileName : publicKey 파일명
	 * @return PublicKey : publicKey 파일
	 * @throws IOException
	 */
	private static PublicKey getPublicKey(String keyFile) throws Exception {
		InputStream inputStream = new FileInputStream(keyFile);
		ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));

		try {
			BigInteger modulus = (BigInteger) objectInputStream.readObject();
			BigInteger exponent = (BigInteger) objectInputStream.readObject();

			RSAPublicKeySpec rsaKeySpec = new RSAPublicKeySpec(modulus, exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(rsaKeySpec);

			return publicKey;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("From getPublicKey: ", e);
		} finally {
			objectInputStream.close();
		}
	}

	/**
	 * RSA 비공개키 파일을 가져온다.
	 *
	 * @param keyFileName : privateKey 파일명
	 * @return PrivateKey : privateKey 파일
	 * @throws IOException
	 */
	private static PrivateKey getPrivateKey(String keyFile) throws IOException {
		InputStream inputStream = new FileInputStream(keyFile);
		ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));

		try {
			BigInteger modulus = (BigInteger) objectInputStream.readObject();
			BigInteger exponent = (BigInteger) objectInputStream.readObject();

			RSAPrivateKeySpec rsaKeySpec = new RSAPrivateKeySpec(modulus, exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(rsaKeySpec);

			return privateKey;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("From getPrivateKey: ", e);
		} finally {
			objectInputStream.close();
		}
	}
}
