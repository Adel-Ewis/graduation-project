/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.enums;

import javax.faces.application.FacesMessage;

/**
 *
 * @author Mohamed-Ali
 */
public enum Severity
{
    Info(FacesMessage.SEVERITY_INFO), Warn(FacesMessage.SEVERITY_WARN), Error(FacesMessage.SEVERITY_ERROR), Fatal(FacesMessage.SEVERITY_FATAL);
    private final FacesMessage.Severity value;

    private Severity(FacesMessage.Severity v)
    {
        this.value = v;
    }

    public FacesMessage.Severity getValue()
    {
        return this.value;
    }
}
