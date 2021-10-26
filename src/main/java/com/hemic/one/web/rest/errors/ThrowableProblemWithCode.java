package com.hemic.one.web.rest.errors;

import com.hemic.one.constants.BaseError;
import com.hemic.one.utils.ContextUtil;
import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * @Author jor
 * @create 2021/10/26 13:35
 */
public class ThrowableProblemWithCode extends AbstractThrowableProblem {

    private int code;

    public ThrowableProblemWithCode(){

    }
    public ThrowableProblemWithCode(BaseError error, StatusType status){
        super(URI.create(ContextUtil.getContext().url()),  ContextUtil.getContext().messageSource().getMessage(error.message(),null,ContextUtil.getContext().locale()), status, null);
        this.code = error.code();
    }


    public int getCode(){
        return code;
    }


}
