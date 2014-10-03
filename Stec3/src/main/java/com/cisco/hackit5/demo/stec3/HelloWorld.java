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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
 
/**
 * Root resource (exposed at "helloworld" path)
 */
@Path("/helloworld")
public class HelloWorld {
    @Context
    private UriInfo context;
    /** Creates a new instance of HelloWorld */
    public HelloWorld() {
    }
 
    /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, World!! kkfsdkjfskdfhk 1212 </h1></body></html>";
    }
}