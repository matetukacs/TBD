/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cisco.tbd.stec.client;

/**
 *
 * @author matetukacs
 */
public interface ServerConnection {
    
    void pushDetectedThreat(String clientIpAddress, String attackerIpAddress, String userId, String passwordHash);
    
    boolean pushDetectedThreatWithResponse(String clientIpAddress, String attackerIpAddress, String userId, String passwordHash);
}
