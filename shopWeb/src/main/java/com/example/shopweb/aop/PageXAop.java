package com.example.shopweb.aop;

import cn.hutool.core.util.ObjectUtil;
import com.example.shopmodel.info.PageInfo;
import com.example.shopmodel.utils.LocalpageInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class PageXAop {
    //定义切入点
    @Pointcut("@annotation(com.example.shopweb.annotaion.PageX)")
    public void pointCut() {

    }

    //定义环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        String pageNum = ((ServletRequestAttributes) requestAttributes).getRequest().getParameter("pageNum");
        String pageSize = ((ServletRequestAttributes) requestAttributes).getRequest().getParameter("pageSize");

        //判断pageNum和pageSize是否都不为空
        if (!ObjectUtil.isEmpty(pageNum) && !ObjectUtil.isEmpty(pageSize)) {
            PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        }

        Object proceed = null;

        //判断结果是否为Page类型
        try {
            //从切入点继续执行
            proceed = joinPoint.proceed();
            if (proceed instanceof Page) {
                Page<?> page = (Page<?>) proceed;
                long total = page.getTotal();
                LocalpageInfoUtil.set(PageInfo.builder().total(total).build());
            } else {
                // 记录或处理proceed不是Page实例的情况
                System.out.println("Proceed is not an instance of Page");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return proceed;
    }
}
