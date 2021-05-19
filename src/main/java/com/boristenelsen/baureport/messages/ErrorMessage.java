package com.boristenelsen.baureport.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {


    private boolean error;
    private String errormessage;


}
