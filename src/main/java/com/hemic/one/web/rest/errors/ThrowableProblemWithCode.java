package com.hemic.one.web.rest.errors;

import com.hemic.one.constants.BaseError;
import com.hemic.one.utils.ContextUtil;
import com.hemic.one.utils.MessageUtil;
import java.net.URI;
import org.apache.commons.lang3.StringUtils;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

/**
 * @Author jor
 * @create 2021/10/26 13:35
 */
public class ThrowableProblemWithCode extends AbstractThrowableProblem {



    public ThrowableProblemWithCode(BaseError error, StatusType status){
        super(URI.create(ContextUtil.getContext().url()), String.valueOf(error.code()), status, MessageUtil.get(error.message()));
    }





}
