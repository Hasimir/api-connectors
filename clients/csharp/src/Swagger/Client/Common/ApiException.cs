using System;

namespace Swagger.Client.Common {
  public class ApiException : Exception {
    
  	private int errorCode = 0;

    public ApiException() {}

    public int ErrorCode { 
    	get
    	{
    		return errorCode;
    	}
    }

    public ApiException(int errorCode, string message) : base(message) {
    	this.errorCode = errorCode;
    }
  }
}
