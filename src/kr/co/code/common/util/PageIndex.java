/**
 *
 */
package kr.co.code.common.util;

/**
 * @author ?????
 *
 */
public class PageIndex {
	public static final int DEFAULT_PAGE_BLOCK_SIZE = 10;
	public static final int DEFAULT_PAGE_LIST_SIZE = 10;

	private static String DEFAULT_ADM_BUTTON_CLASS = "";
	private static String DEFAULT_ADM_NUMBER_CLASS = "pager";
	private static String DEFAULT_ADM_IMAGE_ROOT = "/common/images/board/";
	private static String DEFAULT_ADM_FIRST_IMAGE = "ico_first.gif";
	private static String DEFAULT_ADM_PREVIOUS_IMAGE = "ico_prev.gif";
	private static String DEFAULT_ADM_NEXT_IMAGE = "ico_next.gif";
	private static String DEFAULT_ADM_LAST_IMAGE = "ico_last.gif";

	private static String DEFAULT_BUTTON_CLASS = "";
	private static String DEFAULT_NUMBER_CLASS = "";
	private static String DEFAULT_INDEX_NUMBER_CLASS = "";
	private static String DEFAULT_IMAGE_ROOT = "/common/images/board/";
	private static String DEFAULT_FIRST_IMAGE = "btn_end.gif";
	private static String DEFAULT_PREVIOUS_IMAGE = "btn_next.gif";
	private static String DEFAULT_NEXT_IMAGE = "btn_prev.gif";
	private static String DEFAULT_LAST_IMAGE = "btn_start.gif";

	private int currentPageNo = 1;
	private int pageListSize = DEFAULT_PAGE_LIST_SIZE;
	private int totalCount = 1;
	private int pageBlockSize = DEFAULT_PAGE_BLOCK_SIZE;
	private String buttonClass = DEFAULT_BUTTON_CLASS;
	private String numberClass = DEFAULT_NUMBER_CLASS;
	private String imageRoot = DEFAULT_IMAGE_ROOT;
	private String firstButtonImage = DEFAULT_FIRST_IMAGE;
	private String previousButtonImage = DEFAULT_PREVIOUS_IMAGE;
	private String nextButtonImage = DEFAULT_NEXT_IMAGE;
	private String lastButtonImage = DEFAULT_LAST_IMAGE;
	private String indexNumberClass=DEFAULT_INDEX_NUMBER_CLASS;
	/**
	 * ???? ????????
	 *
	 * @param currentPageNo
	 * @param totalCount
	 * @return
	 */
	public static PageIndex createPageIndexForAdmin(int currentPageNo, int totalCount, int pageListSize) {
		PageIndex page = new PageIndex();

		page.setCurrentPageNo(currentPageNo);
		page.setTotalCount(totalCount);
		page.setPageListSize(pageListSize);

		page.setImageRoot(DEFAULT_ADM_IMAGE_ROOT);
		page.setFirstButtonImage(DEFAULT_ADM_FIRST_IMAGE);
		page.setPreviousButtonImage(DEFAULT_ADM_PREVIOUS_IMAGE);
		page.setNextButtonImage(DEFAULT_ADM_NEXT_IMAGE);
		page.setLastButtonImage(DEFAULT_ADM_LAST_IMAGE);
		page.setButtonClass(DEFAULT_ADM_BUTTON_CLASS);
		page.setNumberClass(DEFAULT_ADM_NUMBER_CLASS);

		return page;
	}


	/**
	 * ???? ????????
	 *
	 * @param currentPageNo
	 * @param totalCount
	 * @return
	 */
	public static PageIndex createPageIndexForWeb(int currentPageNo, int totalCount, int pageListSize) {
		PageIndex page = new PageIndex();

		page.setCurrentPageNo(currentPageNo);
		page.setTotalCount(totalCount);
		page.setPageListSize(pageListSize);

		return page;
	}


	/**
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = (currentPageNo<1) ? 1: currentPageNo;
	}

	/**
	 * @return the pageListSize
	 */
	public int getPageListSize() {
		return pageListSize;
	}

	/**
	 * @param pageListSize
	 *            the pageListSize to set
	 */
	public void setPageListSize(int pageListSize) {
		this.pageListSize = pageListSize;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = (totalCount<1) ? 1 : totalCount;
	}

	/**
	 * @return the pageBlockSize
	 */
	public int getPageBlockSize() {
		return pageBlockSize;
	}

	/**
	 * @param pageBlockSize
	 *            the pageBlockSize to set
	 */
	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}

	/**
	 * @return the buttonClass
	 */
	public String getButtonClass() {
		return buttonClass;
	}

	/**
	 * @param buttonClass
	 *            the buttonClass to set
	 */
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}

	/**
	 * @return the numberClass
	 */
	public String getNumberClass() {
		return numberClass;
	}

	/**
	 * @param numberClass
	 *            the numberClass to set
	 */
	public void setNumberClass(String numberClass) {
		this.numberClass = numberClass;
	}

	/**
	 * @return the firstButtonImage
	 */
	public String getFirstButtonImage() {
		return firstButtonImage;
	}

	/**
	 * @param firstButtonImage
	 *            the firstButtonImage to set
	 */
	public void setFirstButtonImage(String firstButtonImage) {
		this.firstButtonImage = firstButtonImage;
	}

	/**
	 * @return the previousButtonImage
	 */
	public String getPreviousButtonImage() {
		return previousButtonImage;
	}

	/**
	 * @param previousButtonImage
	 *            the previousButtonImage to set
	 */
	public void setPreviousButtonImage(String previousButtonImage) {
		this.previousButtonImage = previousButtonImage;
	}

	/**
	 * @return the nextButtonImage
	 */
	public String getNextButtonImage() {
		return nextButtonImage;
	}

	/**
	 * @param nextButtonImage
	 *            the nextButtonImage to set
	 */
	public void setNextButtonImage(String nextButtonImage) {
		this.nextButtonImage = nextButtonImage;
	}

	/**
	 * @return the lastButtonImage
	 */
	public String getLastButtonImage() {
		return lastButtonImage;
	}

	/**
	 * @param lastButtonImage
	 *            the lastButtonImage to set
	 */
	public void setLastButtonImage(String lastButtonImage) {
		this.lastButtonImage = lastButtonImage;
	}

	/**
	 * @return the imageRoot
	 */
	public String getImageRoot() {
		return imageRoot;
	}

	/**
	 * @param imageRoot
	 *            the imageRoot to set
	 */
	public void setImageRoot(String imageRoot) {
		this.imageRoot = imageRoot;
	}


	/**
	 *
	 * @return
	 */
    public String getIndexNumberClass()
    {
        return indexNumberClass;
    }

}
