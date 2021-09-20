/**
 * 
 */
package com.jwt.dto;

import java.util.List;

/**
 * @author shans
 *
 */
public class ResponseDto<T> {
	
	private String status;
	private String errMsg;
	private String msg;
	private List<?> responseDto;
	private T responseObj;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<?> getResponseDto() {
		return responseDto;
	}
	public void setResponseDto(List<?> responseDto) {
		this.responseDto = responseDto;
	}
	public T getResponseObj() {
		return responseObj;
	}
	public void setResponseObj(T responseObj) {
		this.responseObj = responseObj;
	}
	

}
