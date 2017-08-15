package kr.co.code.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import org.apache.commons.codec.digest.HmacUtils;		// common-codec-1.10.jar 추가

public class TestSearchcodeAuth {
	public static final int KIND_REPO_LIST = 0;
	public static final int KIND_SEARCH = 1;

	private final String PUB_KEY = "APIK-Nxj9WvtGkwjcPyobLeDy2oU81zW";
	private final String PRI_KEY = "juYOQxown09Zp1kSVbjoHDM5tieAC8SH";

	private final String[] BASE_URLs = {
		"http://192.168.1.43:8182/api/repo/list/?sig=",
		"http://192.168.1.43:8182/api/codesearch/?sig="
	};

	private final String[][][] PARAMs = {
		{ { "pub", PUB_KEY } },
		{ { "pub", PUB_KEY }, { "q", "func" }, { "p", "1" }, { "per_page", "2" } }
	};

	private String mContent = new String();

	public TestSearchcodeAuth() {
	}

	public int connect( int pKind ) {
		String sBaseUrl = BASE_URLs[ pKind ];
		String[][] aasParam = PARAMs[ pKind ];


		StringBuffer oUrl = new StringBuffer();
		oUrl.append( sBaseUrl );

		String sParam = makeParam( aasParam );

		String sSigKey = HmacUtils.hmacSha512Hex( PRI_KEY, sParam );

		oUrl.append( sSigKey );
		oUrl.append( "&" );
		oUrl.append( sParam );
		oUrl.append( "&hmac=sha512" );

		try {
			String sUrl = oUrl.toString();
System.out.println( sUrl );

			URL url = new URL( sUrl );
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.connect();
			InputStream inputStream = httpUrlConnection.getInputStream();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] bTemp = new byte[ 1024 ];
			while( true ) {
				int nSize = inputStream.read( bTemp );
				if( nSize == -1 ) {
					break;
				} else {
					byteArrayOutputStream.write( bTemp, 0, nSize );
				}
			}

			mContent = new String( byteArrayOutputStream.toByteArray() );

			return 0;
		} catch (MalformedURLException e) {
			e.printStackTrace();

			return -1;
		} catch (IOException e) {
			e.printStackTrace();

			return -2;
		}
	}

	private String makeParam( String[][] pData ) {
		if( pData == null || pData.length == 0 ) {
			return null;
		} else {
			StringBuffer oParam = new StringBuffer();
			for( int i = 0; i < pData.length; i++ ) {
				if( i > 0 ) {
					oParam.append( "&" );
				}
				oParam.append( pData[ i ][ 0 ] );
				oParam.append( "=" );
				oParam.append( pData[ i ][ 1 ] );
			}

			return oParam.toString();
		}
	}

	public String getResult() {
		return mContent;
	}

	public static void main(String[] args) {
		TestSearchcodeAuth oTest = new TestSearchcodeAuth();

		String sLog = "";
		int nRet = oTest.connect( TestSearchcodeAuth.KIND_SEARCH );
		if( nRet < 0 ) {
			sLog = "접속 실패";
		} else {
			sLog = oTest.getResult();
		}

		System.out.println( sLog );
	}

}
