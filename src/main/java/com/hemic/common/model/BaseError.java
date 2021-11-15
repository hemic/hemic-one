package com.hemic.common.model;

import com.hemic.common.error.ThrowableProblemWithCode;

/**
 * @Author jor
 * @create 2021/10/25 17:22
 */
public interface BaseError {

    int code();

    String message();

    ThrowableProblemWithCode getException();
}
