package com.platz.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Anderson
 */
@Provider
public class CrossOriginHeaderFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        //Adicionar Header Access-Control-Allow-Origin a todas as Responses
        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");

    }

}
