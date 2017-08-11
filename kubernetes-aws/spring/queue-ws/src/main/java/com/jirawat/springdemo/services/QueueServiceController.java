package com.jirawat.springdemo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author juttayaya
 */
@RestController
public class QueueServiceController {
    @RequestMapping("/queue")
    public HttpEntity<QueueService> queue() {
        final String hostname = findHostname();
        final QueueService queueService = new QueueService(hostname);
        queueService.add(linkTo(methodOn(QueueServiceController.class).queue()).withSelfRel());

        return new ResponseEntity<>(queueService, HttpStatus.OK);
    }

    private String findHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "UNKNOWN: " + e.getMessage();
        }
    }
}
