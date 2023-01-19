package com.new_ton.service;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class GerUserRoleServiceImpl implements GerUserRoleService {
    @Override
    public String getUserRole(HttpServletRequest request) {
        SecurityContextImpl context = (SecurityContextImpl) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        return context.getAuthentication().getAuthorities().stream().findFirst().get().toString();
    }
}
