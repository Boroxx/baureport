package com.boristenelsen.baureport.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class TopBauModule {

    private ArrayList<Baumodul> topBauModuleList;

    public TopBauModule(ArrayList<Baumodul> topBauModuleList){
        this.topBauModuleList = topBauModuleList;

    }
}
