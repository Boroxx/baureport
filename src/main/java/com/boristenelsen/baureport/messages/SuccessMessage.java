package com.boristenelsen.baureport.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessMessage {

    private boolean success;
    private String successMessage;
}
