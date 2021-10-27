package com.hemic.one.utils;


import com.hemic.one.constants.BaseError;
import com.hemic.one.web.rest.errors.ServiceException;
import java.util.Collection;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @Author jor
 * @create 2021/10/26 15:15
 */
public class Assert {

    public static void isTrue(boolean expression, BaseError error) {
        if (!expression) {
            throw new ServiceException(error);
        }
    }
    public static  void isFalse(boolean expression, BaseError error) {
        if (expression) {
            throw new ServiceException(error);
        }
    }

    public static void notEmpty(Collection array, BaseError error){
        if(CollectionUtils.isEmpty(array)){
            throw new ServiceException(error);
        }
    }

    public static void notEmpty(String str, BaseError error){
        if(StringUtils.isEmpty(str)){
            throw new ServiceException(error);
        }
    }

    public static void notNull(Object obj,BaseError error){
        if(obj==null){
            throw new ServiceException(error);
        }
    }

    public static void isPresent(Optional optional,BaseError error){
        if(!optional.isPresent()){
            throw new ServiceException(error);
        }
    }

    public static  void isNotPresent(Optional optional, BaseError error){
        if(optional.isPresent()){
            throw new ServiceException(error);
        }
    }
}
