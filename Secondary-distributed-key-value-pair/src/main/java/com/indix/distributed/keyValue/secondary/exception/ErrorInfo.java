package com.indix.distributed.keyValue.secondary.exception;

public class ErrorInfo {


  private String status;

  private String code;

  private String message;

  private String moreinfo;

  public ErrorInfo() {}

  public ErrorInfo(String message, String code) {
    super();
    this.message = message;
    this.code = code;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the moreinfo
   */
  public String getMoreinfo() {
    return moreinfo;
  }

  /**
   * @param moreinfo the moreinfo to set
   */
  public void setMoreinfo(String moreinfo) {
    this.moreinfo = moreinfo;
  }

}
