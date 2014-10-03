/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cisco.hackit5.demo.stec3;

/**
 *
 * @author ttsatury
 */
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
 
/**
 * Root resource (exposed at "helloworld" path)
 */
@Path("/threats")
public class Threats {
    @Context
    private UriInfo context;
    /** Creates a new instance of HelloWorld */
    public Threats() {
    }
 
    /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/demo")
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, World!! kkfsdkjfskd3333fhk 1212 </h1></body></html>";
    }
    @GET
    @Path("/getthreats")
    public Response getThreats(@Context UriInfo info) 
    {
        String from = info.getQueryParameters().getFirst("from");
	String to = info.getQueryParameters().getFirst("to");
        return Response
		   .status(200)
		   .entity("getUsers is called, from : " + from + ", to : " + to).build();
    }
}