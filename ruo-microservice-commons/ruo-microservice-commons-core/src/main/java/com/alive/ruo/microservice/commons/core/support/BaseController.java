package com.alive.ruo.microservice.commons.core.support;

import com.alive.ruo.microservice.commons.base.dto.LoginAuthDto;
import com.alive.ruo.microservice.commons.core.generator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.core.support
 * @title: BaseController
 * @description: TODO
 * @date 2019/4/16 18:11
 */
public class BaseController {

    @Autowired
    private IdGenerator idGenerator;

    /**
     * Gets login auth dto.
     *
     * @return the login auth dto
     */
    protected LoginAuthDto getLoginAuthDto() {
        /*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginAuthDto loginAuthDto = new LoginAuthDto(0L, userDetails.getUsername(), userDetails.getUsername());*/
        LoginAuthDto loginAuthDto = new LoginAuthDto(0L, "admin", "admin");
        return loginAuthDto;
    }

    protected long generateId() {
        return Long.valueOf(idGenerator.nextUniqueId(2, 3));
    }
}
