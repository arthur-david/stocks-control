package br.com.mercsys.stocks.exceptions.errors;

import br.com.mercsys.stocks.helpers.CollectionsHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter @Setter
@NoArgsConstructor
public class ErrorDTO {

    private Integer errorCode;
    private String errorSource;
    private String errorReason;
    private String errorDetail;
    private Object errorDescription;

    public ErrorDTO(Integer errorCode, String errorSource, String errorReason) {
        this(errorCode, errorSource, errorReason, null);
    }

    public ErrorDTO(Integer errorCode, String errorSource, String errorReason, String errorDetail) {
        this(errorCode, errorSource, errorReason, errorDetail, null);
    }

    public ErrorDTO(Integer errorCode, String errorSource, String errorReason, String errorDetail, Object errorDescription) {
        setErrorCode(errorCode);
        setErrorSource(errorSource);
        setErrorReason(errorReason);
        setErrorDetail(errorDetail);
        if (errorDescription instanceof StackTraceElement[]) {
            setErrorDescription(formatErrorDescription((StackTraceElement[]) errorDescription));
        } else {
            setErrorDescription(errorDescription);
        }
    }

    public ErrorDTO(Exception exception) {
        setErrorCode(99);
        setErrorSource(exception.getClass().getCanonicalName());
        if(isNull(exception.getMessage()) && !exception.getMessage().trim().isEmpty())
            setErrorReason(exception.getMessage());

        StackTraceElement[] stackTrace = exception.getStackTrace();
        if(CollectionsHelper.isValid(stackTrace)) {
            StringBuilder bld = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (stackTraceElement.getClassName().contains("br.com.mercsys.stocks"))
                    bld.append(String.format("%s (%d) ", stackTraceElement.getClassName(), stackTraceElement.getLineNumber()));
            }
            String reason = bld.toString();
            setErrorDescription(reason.trim());
        }
        setErrorDetail("Desculpe, ocorreu uma falha pontual, por favor, tente novamente. Se erro persistir entre em contato conosco.");
    }

    private String formatErrorDescription(StackTraceElement[] stackTraceElements) {
        String description = null;
        if(CollectionsHelper.isValid(stackTraceElements)) {
            StringBuilder bld = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                if (stackTraceElement.getClassName().contains("br.com.mercsys.stocks"))
                    bld.append(String.format("%s (%d) ", stackTraceElement.getClassName(), stackTraceElement.getLineNumber()));
            }
            description = bld.toString();
        }
        return description;
    }
}
