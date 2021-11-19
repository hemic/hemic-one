package com.hemic.one.security;

import com.hemic.common.error.ThrowableProblemWithCode;

import com.hemic.common.model.BaseError;
import com.hemic.one.constants.ErrorConstants;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class AuthenticationException extends ThrowableProblemWithCode {

    private static final long serialVersionUID = 1L;


    public AuthenticationException(BaseError error) {
        super(error, Status.UNAUTHORIZED);
    }
}
