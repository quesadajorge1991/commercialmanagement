/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wason
 */
public class AllAuthorities {

    private List<Authorities> listinmemory = new ArrayList<Authorities>();

    public List<Authorities> getListinmemory() {
        listinmemory.add(new Authorities("ROLE_READ"));
        listinmemory.add(new Authorities("ROLE_WRITE"));
        listinmemory.add(new Authorities("ROLE_DELETE"));
        listinmemory.add(new Authorities("ROLE_UPDATE"));
        listinmemory.add(new Authorities("ROLE_ADMIN"));
        listinmemory.add(new Authorities("ROLE_DB"));
        listinmemory.add(new Authorities("ROLE_USERS"));
        listinmemory.add(new Authorities("ROLE_INVITED"));
        listinmemory.add(new Authorities("ROLE_FULLADMIN"));
        return listinmemory;
    }

    public void setListinmemory(List<Authorities> listinmemory) {
        this.listinmemory = listinmemory;
    }

}
