package kr.co.code.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * 간단한 Excel 생성 및 Excel Parsing을 해준다.
 */
public class SimpleExcelUtil {
	/**
	 * Sheet 이름
	 */
	private final String sSHEET_NAME = "Sheet1";

	/**
	 * Excel 셀 너비
	 */
	private final int nCOLUMN_WIDTH = 5133;
	/**
	 * Excel 셀 높이
	 */
	private final short nCELL_HEIGHT = 338;

	/**
	 * HSSFWorkbook 객체
	 */
	private HSSFWorkbook mWorkbook;
	/**
	 * HSSFSheet 객체
	 */
	private HSSFSheet mSheet;

	/**
	 * 헤드 셀 스타일
	 */
	private final HSSFCellStyle mHeadCellStyle;
	/**
	 * 바디 셀 스타일
	 */
	private final HSSFCellStyle mBodyCellStyleLeft;

	/**
	 * 현재 생성한 
	 */
	private int mnRowIdx = 0;

	/**
	 * 생성자
	 */
	public SimpleExcelUtil() {
		mWorkbook = new HSSFWorkbook();
		mSheet = mWorkbook.createSheet( sSHEET_NAME );

//		byte[] bData = new byte[1];
//		int nPictureIdx = mWorkbook.addPicture( bData, HSSFWorkbook.PICTURE_TYPE_JPEG );
//		HSSFPatriarch test = mSheet.createDrawingPatriarch();
//		HSSFClientAnchor Anchor = new HSSFClientAnchor();
//		HSSFPicture Picture = test.createPicture( Anchor, 0 );
//		Picture.setPictureIndex( nPictureIdx );

		mHeadCellStyle = mWorkbook.createCellStyle();
		mHeadCellStyle.setBorderBottom( HSSFCellStyle.BORDER_THIN );
		mHeadCellStyle.setBorderLeft( HSSFCellStyle.BORDER_THIN );
		mHeadCellStyle.setBorderRight( HSSFCellStyle.BORDER_THIN );
		mHeadCellStyle.setBorderTop( HSSFCellStyle.BORDER_THIN );
		mHeadCellStyle.setFillPattern( HSSFPatternFormatting.SOLID_FOREGROUND );
		mHeadCellStyle.setFillForegroundColor( HSSFColor.GREY_25_PERCENT.index );
		mHeadCellStyle.setWrapText(true);

		mBodyCellStyleLeft = mWorkbook.createCellStyle();
		mBodyCellStyleLeft.setBorderBottom( HSSFCellStyle.BORDER_THIN );
		mBodyCellStyleLeft.setBorderLeft( HSSFCellStyle.BORDER_THIN );
		mBodyCellStyleLeft.setBorderRight( HSSFCellStyle.BORDER_THIN );
		mBodyCellStyleLeft.setBorderTop( HSSFCellStyle.BORDER_THIN );
		mBodyCellStyleLeft.setWrapText(true);

		mnRowIdx = 0;
	}

	/**
	 * 다음 Row 생성
	 * @return HSSFRow 객체
	 */
	private HSSFRow createNextRow() {
		HSSFRow oRow = mSheet.createRow( mnRowIdx++ );
		oRow.setHeight( nCELL_HEIGHT );
		return oRow;
	}

	/**
	 * Head 셀 생성
	 * @param pRow HSSFRow 객체
	 * @param pnCellIdx Cell 순번
	 * @param psValue 입력 값
	 * @return HSSFCell 객체
	 */
	private HSSFCell createHeadCell( HSSFRow pRow, int pnCellIdx, String psValue ) {
		if( pnCellIdx < 0 ) {
			return null;
		}

		HSSFCell oCell = pRow.createCell( pnCellIdx );
		oCell.setCellStyle( mHeadCellStyle );
		if( psValue != null && !psValue.isEmpty() ) {
			oCell.setCellValue( psValue );
		}

		return oCell;
	}

	/**
	 * Body 셀 생성
	 * @param pRow HSSFRow 객체
	 * @param pnCellIdx Cell 순번
	 * @param psValue 입력 값
	 * @return HSSFCell 객체
	 */
	private HSSFCell createBodyCell( HSSFRow pRow, int pnCellIdx, String psValue ) {
		if( pnCellIdx < 0 ) {
			return null;
		}

		HSSFCell oCell = pRow.createCell( pnCellIdx );
		oCell.setCellStyle( mBodyCellStyleLeft );
		if( psValue != null && !psValue.isEmpty() ) {
			oCell.setCellValue( psValue );
		}

		return oCell;
	}

	/**
	 * 시나리오 일괄등록 샘플 파일 생성
	 * @param pnRowCount	Row 개수
	 * @param pList_Type	시나리오 유형 정보 목록
	 * @param pList_Quality 품질특성 정보 목록
	 * @return HSSFWorkbook 객체
	 */
	@SuppressWarnings("rawtypes")
//	public HSSFWorkbook makeScenarioBatchSampleExcel( final int pnRowCount, List<Map> pList_CompanyGroup, List<Map> pList_Type, List<Map> pList_Quality ) {
	public HSSFWorkbook makeScenarioBatchSampleExcel( final int pnRowCount, List<Map> pList_Type, List<Map> pList_Quality ) {
		mWorkbook.removeSheetAt( mWorkbook.getSheetIndex( sSHEET_NAME ) );
		mSheet = mWorkbook.createSheet( sSHEET_NAME );

		final String[][] aasHead = new String[][] {
			{ "순번",				"자동생성" },
			{ "소속사코드",		"소속사코드 입력" },
			{ "앱코드",			"앱코드 입력" },
			{ "시나리오ID",		"시나리오ID 입력" },
			{ "시나리오명",		"시나리오명 입력" },
			{ "대분류",			"대분류 입력" },
			{ "중분류",			"중분류 입력" },
			{ "소분류",			"소분류 입력" },
			{ "시나리오유형",	"시나리오유형 선택" },
			{ "시나리오설명",	"시나리오명설명 입력" },
			{ "케이스ID",		"케이스ID 입력" },
			{ "케이스명",		"케이스명 입력" },
			{ "품질특성",		"품질특성 선택" },
			{ "케이스설명",		"케이스설명 입력" },
			{ "시퀀스내용",		"시퀀스내용 입력" },
			{ "사전조건",		"사전조건 입력" },
			{ "입력값",			"입력값 입력" },
			{ "예상결과",		"예상결과 입력" }
		};

		mnRowIdx = 0;

		for( int i=0; i<aasHead[0].length; i++ ) {
			HSSFRow oRow = createNextRow();
			for( int j=0; j<aasHead.length; j++ ) {
				createHeadCell( oRow, j, aasHead[ j ][ i ] );
			}
		}

		for( int i=0; i<pnRowCount; i++ ) {
			HSSFRow oRow = createNextRow();
			for( int j=0; j<aasHead.length; j++ ) {
				if( j == 0 ) {
					createBodyCell( oRow, j, "" + ( i + 1 ) );
				} else {
					createBodyCell( oRow, j, null );
				}
			}
		}

/* 소속사 정보가 너무 많아 Excel Combo로 생성 시 Error 발생하여 소속사 정보는 Excel Combo로 제공 안 함
		HSSFDataValidation DataValidation_CompayGroup = getDataValidation( pList_CompanyGroup, pnRowCount, 1 );
		if( DataValidation_CompayGroup != null ) {
			mSheet.addValidationData( DataValidation_CompayGroup );
		}
*/
		HSSFDataValidation DataValidation_Type = getDataValidation( pList_Type, pnRowCount, 8 );
		if( DataValidation_Type != null ) {
			mSheet.addValidationData( DataValidation_Type );
		}

		HSSFDataValidation DataValidation_Quality = getDataValidation( pList_Quality, pnRowCount, 12 );
		if( DataValidation_Quality != null ) {
			mSheet.addValidationData( DataValidation_Quality );
		}

		for( int j=0; j<aasHead.length; j++ ) {
			mSheet.setColumnWidth( j, nCOLUMN_WIDTH );
		}

		mnRowIdx = 0;

		return mWorkbook;
	}

	/**
	 * Excel Combo 생성
	 * @param pList_Code Combo 값 목록
	 * @param pnRowCount 생성 Row 개수
	 * @param pnColumnIdx 생성될 Cell 순번
	 * @return HSSFDataValidation 객체
	 */
	@SuppressWarnings("rawtypes")
	private HSSFDataValidation getDataValidation( final List<Map> pList_Code,  final int pnRowCount, final int pnColumnIdx ) {
		if ( pList_Code == null ) {
			return null;
		} else {
			String[] asCode = new String[ pList_Code.size() ];
			for( int i = 0; i < asCode.length; i++ ) {
				Map Map_Temp = pList_Code.get( i );
				String sCode = ( String ) Map_Temp.get( "code" );
				String sValue = ( String ) Map_Temp.get( "value" );
	
				asCode[ i ] = sCode + "_" + sValue;
			}

			HSSFDataValidation oDataValidation_Code = new HSSFDataValidation(
					new CellRangeAddressList( 2, 1 + pnRowCount, pnColumnIdx, pnColumnIdx ),
					DVConstraint.createExplicitListConstraint( asCode )
			);
			oDataValidation_Code.setSuppressDropDownArrow( false );

			return oDataValidation_Code;
		}
	}

	/**
	 * 인자로 받은 Head / Body 목록 정보로 Excel 생성
	 * @param paasHead	Head 목록
	 * @param pList_Body Body 목록
	 * @return HSSFWorkbook 객체
	 */
	@SuppressWarnings("rawtypes")
	public HSSFWorkbook makeExcel( String[][] paasHead, List<Map> pList_Body ) {
		return makeExcel( paasHead, pList_Body, false, true );
	}

	/**
	 * 인자로 받은 Head / Body 목록 정보로 Excel 생성. Row에 수순으로 자동번호 매김
	 * @param paasHead	Head 목록
	 * @param pList_Body Body 목록
	 * @param pfIsNumbering Row에 자동번호 매김
	 * @return HSSFWorkbook 객체
	 */
	@SuppressWarnings("rawtypes")
	public HSSFWorkbook makeExcel( String[][] paasHead, List<Map> pList_Body, boolean pfIsNumbering ) {
		return makeExcel( paasHead, pList_Body, pfIsNumbering, true );
	}

	/**
	 * 인자로 받은 Head / Body 목록 정보로 Excel 생성. Row에 자동번호 매김
	 * @param paasHead	Head 목록
	 * @param pList_Body Body 목록
	 * @param pfIsNumbering Row에 자동번호 매김
	 * @param pfIsAsc 자동번호 수순/역순
	 * @return HSSFWorkbook 객체
	 */
	@SuppressWarnings("rawtypes")
	public HSSFWorkbook makeExcel( String[][] paasHead, List<Map> pList_Body, boolean pfIsNumbering, boolean pfIsAsc ) {
		int i = 0;

		makeHeadRow( mSheet.createRow( i++ ), paasHead, pfIsNumbering );

		int nNumbering = 0;
		if( pfIsAsc ) {
			nNumbering = 1;
		} else {
			nNumbering = pList_Body.size();
		}

		for( Map oMap : pList_Body ) {
			if( pfIsNumbering ) {
				makeBodyRow( mSheet.createRow( i++ ), paasHead, oMap, nNumbering );

				if( pfIsAsc ) {
					nNumbering++;
				} else {
					nNumbering--;
				}
			} else {
				makeBodyRow( mSheet.createRow( i++ ), paasHead, oMap, -1 );
			}
		}

//		for( int j=0; j<paasHead.length; j++ ) {
//			mSheet.setColumnWidth( j, nCOLUMN_WIDTH );
//		}

		return mWorkbook;
	}

	/**
	 * Head Row 생성
	 * @param pRow HSSFRow 객체
	 * @param paasHead Head 정보 목록. [][] 2중배열이며, Head 값은 두번째 배열의 0번째에 들어 있음.
	 * @param pfIsNumbering Row에 자동번호 Column 생성
	 */
	private void makeHeadRow( HSSFRow pRow, String[][] paasHead, boolean pfIsNumbering ) {
		pRow.setHeight( nCELL_HEIGHT );

		int i = 0;
		if( pfIsNumbering ) {
			HSSFCell oCell = pRow.createCell( i++ );
			oCell.setCellStyle( mHeadCellStyle );
			oCell.setCellValue( "No" );
		}

		for( String[] asData : paasHead ) {
			HSSFCell oCell = pRow.createCell( i++ );
			oCell.setCellStyle( mHeadCellStyle );
			oCell.setCellValue( asData[ 0 ] );
		}
	}

	/**
	 * Body Row 생성
	 * @param pRow HSSFRow 객체
	 * @param paasKey pMap_Body Map의 Key 값 목록
	 * @param pMap_Body Body 값이 들어있는 Map 객체
	 * @param pnNumbering 자동순번으로 들어갈 숫자. -1 이면 숫자를 표시하지 않는다.
	 */
	@SuppressWarnings("rawtypes")
	private void makeBodyRow( HSSFRow pRow, String[][] paasKey, Map pMap_Body, int pnNumbering ) {
		pRow.setHeight( nCELL_HEIGHT );

		int i = 0;
		if( pnNumbering > -1 ) {
			HSSFCell oCell = pRow.createCell( i++ );
			oCell.setCellStyle( mBodyCellStyleLeft );
			oCell.setCellValue( "" + pnNumbering );
		}

		for( String[] asData : paasKey ) {
			HSSFCell oCell = pRow.createCell( i++ );
			oCell.setCellStyle( mBodyCellStyleLeft );
			oCell.setCellValue( "" + pMap_Body.get( asData[1] ) );
		}
	}

	/**
	 * Excel을 Parsing하여 List 객체로 반환한다.
	 * @param pWorkbook pWorkbook 객체
	 * @param pnDataRowStartNo Data 시작 Row 순번
	 * @return Excel을 Parsing한 List 객체
	 */
	public List<Map<String, Object>> parseExcel( Workbook pWorkbook, int pnDataRowStartNo ) {
		List<Map<String, Object>> List_Result = new ArrayList<Map<String, Object>>();

		Sheet oSheet = pWorkbook.getSheetAt( 0 );

		int nRowCount = oSheet.getLastRowNum() + 1;	// 행의 총 개수(행은 0부터 시작)
		for( int i = pnDataRowStartNo; i < nRowCount; i++ ) {
			Map<String, Object> Map_Cell = new HashMap<String, Object>();

			Row oRow = oSheet.getRow( i );

			int nCellCount = oRow.getLastCellNum() + 1;	// 열의 총 개수
			for( int j = 0; j < nCellCount; j++ ) {
				Map_Cell.put( "" + j, getCellValue( oRow.getCell( j ) ) );
			}

			List_Result.add( Map_Cell );
		}

		return List_Result;
	}

	/**
	 * Cell에 있는 값을 얻는다.
	 * @param pCell Cell 객체
	 * @return 
	 */
	private String getCellValue( Cell pCell ) {
		String sValue = "";

		if( pCell != null ) {
			switch ( pCell.getCellType() ) {
			case Cell.CELL_TYPE_STRING:		// 셀의 형식이 문자일때
				sValue = pCell.getStringCellValue();
				break;

			case Cell.CELL_TYPE_NUMERIC:	
				if( DateUtil.isCellDateFormatted( pCell ) ) {	// 셀의 형식이 날짜일때
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
					sValue = sdf.format( pCell.getDateCellValue() );
				} else {	// 셀의 형식이 숫자일때
					Double dTemp = pCell.getNumericCellValue();

					if( Math.floor( dTemp ) == dTemp ) {
						sValue = String.valueOf( dTemp.intValue() );
					} else {
						sValue = String.valueOf( dTemp );
					}
				}
				break;

			case Cell.CELL_TYPE_BOOLEAN:
				sValue = String.valueOf( pCell.getBooleanCellValue() );
			}
		}

		return sValue;
	}
}
