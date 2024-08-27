package com.se_backend.Interceptor.InterceptorMapper;

import com.se_backend.DB.DB_object.URLPowers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
@Mapper
public interface InterceptorURLPowerNeedMapper {
    @Select("Select * from URLPowers where URL=#{URL} and Func=#{Func}")
    ArrayList<URLPowers> GetURLPowers(@Param("URL")String URL,@Param("Func")String Func);
}
