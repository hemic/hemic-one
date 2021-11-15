package com.hemic.common.error;

import com.hemic.common.model.BaseError;
import java.io.Serial;
import org.zalando.problem.Status;

/**
 * @Author jor
 * @create 2021/10/14 9:19
 */
public class ServiceException extends ThrowableProblemWithCode {

    @Serial
    private static final long serialVersionUID = -4872114589737312888L;

    public ServiceException(BaseError error) {
        super(error, Status.INTERNAL_SERVER_ERROR);
    }

}
