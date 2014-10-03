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
public class AttackData {
    
    private String attackIp;
    private int priorityLevel;
    
    public AttackData(String attckIp, int priorityLevel) {
        this.attackIp = attackIp;
        this.priorityLevel = priorityLevel;
    }

    public String getAttackIp() {
        return attackIp;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
    
    
}
