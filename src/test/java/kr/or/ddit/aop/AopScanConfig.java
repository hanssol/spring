package kr.or.ddit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"},includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value =Aspect.class ))
@EnableAspectJAutoProxy

public class AopScanConfig {

}
