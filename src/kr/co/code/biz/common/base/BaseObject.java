/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * BaseObject.
 *
 *
 */
public class BaseObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	protected BaseObject() {
	}

    public String toString() {
        return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorCd;

    public String getErrorCd()
    {
        return errorCd;
    }

    public void setErrorCd( String errorCd )
    {
        this.errorCd = errorCd;
    }

}
