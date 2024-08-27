package com.se_backend.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_object.URLPowers;
import com.se_backend.DB.DB_object.UserRole;
import com.se_backend.DB.DB_object.UserToken;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorRolePowerMapper;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorURLPowerNeedMapper;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorUerRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PowerInterceptor implements HandlerInterceptor {
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private InterceptorUerRoleMapper interceptorUerRoleMapper;
    @Autowired
    private InterceptorRolePowerMapper interceptorRolePowerMapper;
    @Autowired
    private InterceptorURLPowerNeedMapper interceptorURLPowerNeedMapper;
    private static ObjectMapper objectMapper = new ObjectMapper();
    String patternUserPowerCheck = "\"UserPowerCheck\":\\{.*}";
    Pattern p_UserPowerCheck = Pattern.compile(patternUserPowerCheck);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestReaderHttpServletRequestWrapper wrapper = new RequestReaderHttpServletRequestWrapper(request);
        String bodyParams = wrapper.inputStream2String(wrapper.getInputStream());
        Matcher PowerCheck = p_UserPowerCheck.matcher(bodyParams);
        //获取用户、密钥对象
        if(PowerCheck.find()) {
            String UserPowerCheckString = PowerCheck.group(0);
            UserPowerCheckString = UserPowerCheckString.substring(UserPowerCheckString.indexOf('{'),UserPowerCheckString.indexOf('}')+1);
            UserPowerCheck UserPowerCheck = objectMapper.readValue(UserPowerCheckString,UserPowerCheck.class);
            //验证密钥
            if(userTokenMapper.CheckToken(new UserToken(UserPowerCheck.getId(),UserPowerCheck.getToken()))!=null){
                    ArrayList<UserRole> UerRoles = interceptorUerRoleMapper.getUserAllRole(UserPowerCheck.getId());
                    ArrayList<Integer> PowerID;
                    Map<Integer,Boolean> powerMap = new HashMap<>();
                    //生成用户权限哈希表
                    for (UserRole UerRole:UerRoles
                         ) {
                        PowerID = interceptorRolePowerMapper.GetPowerID(UerRole.getRoleID());
                        for (Integer powerID:PowerID
                             ) {
                            if(!powerMap.containsKey(powerID)){
                                powerMap.put(powerID,true);
                            }
                        }
                    }
                    //获取路由+方法所需权限清单
                    ArrayList<URLPowers> Powers= interceptorURLPowerNeedMapper.GetURLPowers(request.getRequestURI(),request.getMethod());
                    //验证路由+方法权限与用户权限是否匹配
                for (URLPowers power:Powers
                         ) {
                    if(!powerMap.containsKey(power.getPowerNeed())) {
                            System.out.println("权限验证失败 "+power.getPowerNeed());
                            return false;
                    }
                }
                System.out.println("权限验证通过 ");
                return true;
            }
            System.out.println("用户密钥验证失败 ");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
