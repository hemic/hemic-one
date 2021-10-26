package com.hemic.one.web.rest.errors;

import com.hemic.one.constants.BaseError;
import com.hemic.one.utils.ContextUtil;
import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * @Author jor
 * @create 2021/10/14 9:19
 */
public class ServiceException extends ThrowableProblemWithCode {

    public ServiceException(BaseError error){
        super(error,Status.INTERNAL_SERVER_ERROR);
    }

}
