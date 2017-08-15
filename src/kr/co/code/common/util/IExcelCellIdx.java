package kr.co.code.common.util;

/**
 * 엑셀의 셀 Index의 정보
 */
public interface IExcelCellIdx {
	/**
	 * 시나리오 일괄등록에서 사용되는 엑셀의 셀 Index의 정보
	 */
	public static interface IScenarioBatch {
		/**
		 * 소속사 코드
		 */
		public final int COMPANY_CODE = 1;
		/**
		 * 앱 코드
		 */
		public final int APP_CODE = 2;
		/**
		 * 사용자가 입력한 시나리오 아이디
		 */
		public final int USER_SCENARIO_ID = 3;
		/**
		 * 시나리오 이름
		 */
		public final int SCENARIO_NAME = 4;
		/**
		 * 업무 대분류
		 */
		public final int JOB_GROUP_TOP = 5;
		/**
		 * 업무 중분류
		 */
		public final int JOB_GROUP_MIDDLE = 6;
		/**
		 * 업무 소분류
		 */
		public final int JOB_GROUP_BOTTOM = 7;
		/**
		 * 시나리오 유형
		 */
		public final int SCENARIO_TYPE = 8;
		/**
		 * 시나리오 설명
		 */
		public final int SCENARIO_DESCRIPTION = 9;

		/**
		 * 사용자가 입력한 케이스 아이디
		 */
		public final int USER_CASE_ID = 10;
		/**
		 * 케이스 이름
		 */
		public final int CASE_NAME = 11;
		/**
		 * 케이스 품질
		 */
		public final int CASE_QUALITY = 12;
		/**
		 * 케이스 설명
		 */
		public final int CASE_DESCRIPTION = 13;

		/**
		 * 시퀀스 설명
		 */
		public final int SEQUENCE_DESCRIPTION = 14;
		/**
		 * 시퀀스 사전 조건
		 */
		public final int SEQUENCE_PRECONDITION = 15;
		/**
		 * 시퀀스 입력 값
		 */
		public final int SEQUENCE_INPUT_VALUE = 16;
		/**
		 * 시퀀스 예상 결과
		 */
		public final int SEQUENCE_EXPECT_RESULT = 17;
	}
}
