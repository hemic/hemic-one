package com.hemic.one.web.rest.errors;

import com.hemic.one.constants.BaseError;
import com.hemic.one.utils.ContextUtil;
import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * @Author jor
 * @create 2021/10/14 9:19
 */
public class AuthException  extends ThrowableProblemWithCode {


   public AuthException(BaseError error){
        super(error,Status.UNAUTHORIZED);
    }

}
