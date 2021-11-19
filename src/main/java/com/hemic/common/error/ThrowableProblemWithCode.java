package com.hemic.common.error;

import com.hemic.common.model.BaseError;
import com.hemic.one.utils.ContextUtil;
import com.hemic.one.utils.MessageUtil;
import java.io.Serial;
import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

/**
 * @Author jor
 * @create 2021/10/26 13:35
 */
public class ThrowableProblemWithCode extends AbstractThrowableProblem {


    @Serial
    private static final long serialVersionUID = 5490937660735608979L;

    public ThrowableProblemWithCode(BaseError error, StatusType status) {
        super(URI.create(ContextUtil.getContext().url()), String.valueOf(error.code()), status, MessageUtil.get(error.message()));
        printStackTrace();
    }


}
