package com.boristenelsen.baureport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GehwegModulDto {


    public UUID id;
    public UUID user_id;
    public String modulname;
    public String strasse_hausnummer;
    public String stadt_plz;
    public MultipartFile genehmigung;
    public String vorgarten;
    public String [] hinderniss;
    public String anmerkung;
    public MultipartFile frontansicht;
    public MultipartFile laengsansicht;
    public int gehwegbreite;
    public String art_bodenlag;
    public int platten_masse;

}
