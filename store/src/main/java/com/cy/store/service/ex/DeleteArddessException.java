package com.cy.store.service.ex;
/**删除数据时产生的异常*/
public class DeleteArddessException extends ServiceException {
    /**重写ServiceException的所有构造方法*/
    public DeleteArddessException() {
        super();
    }

    public DeleteArddessException(String message) {
        super(message);
    }

    public DeleteArddessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteArddessException(Throwable cause) {
        super(cause);
    }

    protected DeleteArddessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

