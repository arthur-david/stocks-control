package br.com.mercsys.stocks.exceptions;

import br.com.mercsys.stocks.exceptions.errors.ErrorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceRuleException extends Exception {

    private static final long serialVersionUID = 1L;

    private final ErrorDTO errorDTO;

    @Setter
    private HttpStatus httpStatus;

    public ServiceRuleException(ServiceRulesEnum serviceRules, Class<?> originClass) {
        this(serviceRules, originClass, null, null, null);
    }

    public ServiceRuleException(ServiceRulesEnum serviceRules, Class<?> originClass, HttpStatus httpStatus) {
        this(serviceRules, originClass, httpStatus, null, null);
    }

    public ServiceRuleException(ServiceRulesEnum serviceRules, Class<?> originClass, String details) {
        this(serviceRules, originClass, null, details, null);
    }

    public ServiceRuleException(ServiceRulesEnum serviceRules, Class<?> originClass, HttpStatus httpStatus, String details) {
        this(serviceRules, originClass, httpStatus, details, null);
    }

    public ServiceRuleException(ServiceRulesEnum serviceRules, Class<?> originClass, HttpStatus httpStatus, String details, Object errorDescription) {
        setHttpStatus(httpStatus);
        this.errorDTO = new ErrorDTO(serviceRules.ordinal(), originClass.getCanonicalName(), serviceRules.name(), details, errorDescription);
    }
}
