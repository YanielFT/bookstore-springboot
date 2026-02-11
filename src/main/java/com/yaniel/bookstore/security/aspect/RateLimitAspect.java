package com.yaniel.bookstore.security.aspect;

import com.yaniel.bookstore.errors.exception.ApiException;
import com.yaniel.bookstore.security.annotation.RateLimited;
import com.yaniel.bookstore.security.service.RateLimiterService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RateLimitAspect {
    private final RateLimiterService rateLimiterService;

    public RateLimitAspect(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Around("@annotation(rateLimited)")
    public Object rateLimit(ProceedingJoinPoint pjp, RateLimited rateLimited) throws Throwable {

        // Obtener IP del cliente
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String clientIp = request.getRemoteAddr();

        // Crear bucket por IP
        Bucket bucket = rateLimiterService.resolveBucket(
                clientIp,
                rateLimited.limit(),
                rateLimited.duration()
        );

        if (bucket.tryConsume(1)) {
            return pjp.proceed();
        }

        throw new ApiException(HttpStatus.TOO_MANY_REQUESTS, "Demasiadas solicitudes. Intente más tarde.");
    }

}
