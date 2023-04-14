package mock.project.frontend.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL, value = Include.NON_NULL)
public class ValidationError {

    private String field;
    private String code;
    private String defaultMessage;
	
	
	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDefaultMessage() {
		return defaultMessage;
	}


	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}


	@Override
	public String toString() {
		return "ValidationError [field=" + field + ", code=" + code + ", defaultMessage=" + defaultMessage + "]";
	}


	
    
}
