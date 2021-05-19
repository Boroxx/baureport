package com.boristenelsen.baureport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GehwegModulDao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    public UUID userid;
    public String modulname;
    public String strasse_hausnummer;
    public String stadt_plz;
    @Lob
    public byte[] genehmigung;
    public String vorgarten;
    public String hinderniss;
    public String anmerkung;

    @Lob
    public byte[] frontansicht;
    @Lob
    public byte[] laengsansicht;
    public int gehwegbreite;
    public String art_bodenlag;
    public int platten_masse;


}
