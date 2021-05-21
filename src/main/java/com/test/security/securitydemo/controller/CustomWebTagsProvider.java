package com.test.security.securitydemo.controller;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CustomWebTagsProvider implements WebMvcTagsProvider {
    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 Throwable exception) {
        HttpSession session = request.getSession(false);
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        String username = securityContext.getAuthentication() == null ? "UNKNOWN" : securityContext.getAuthentication().getName();

        return Tags.of(WebMvcTags.method(request), WebMvcTags.uri(request, response, true),
                WebMvcTags.exception(exception), WebMvcTags.status(response), WebMvcTags.outcome(response),
                Tag.of("principleName", username));
    }

    @Override
    public Iterable<Tag> getLongRequestTags(HttpServletRequest request, Object handler) {
        return Tags.of(WebMvcTags.method(request), WebMvcTags.uri(request, null, true));
    }
}
