/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.utils.recommendation.refresherhelpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author adel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class results {
    String status;
    Result[] result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result[] getResult() {
        return result;
    }

    public void setResult(Result[] result) {
        this.result = result;
    }
}
