package com.kismet.cloud.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author kismet
 * @since 2020/3/29
 */
@Component
public class LoginPreFilter extends ZuulFilter {

    /**
     * 什么类型的过滤
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器执行顺序越小越优先
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 什么条件下执行过滤方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        return request.getRequestURI().startsWith("/order/");
    }

    /**
     * 过滤方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        if (StringUtils.isEmpty(token)) {
            // 阻止请求往下走
            context.setSendZuulResponse(false);
            // 设置错误码
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        // 有token就继续往下走
        return null;
    }
}
