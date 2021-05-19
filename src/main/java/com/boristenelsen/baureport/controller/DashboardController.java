package com.boristenelsen.baureport.controller;

import com.boristenelsen.baureport.jwt.service.GehwegmodulService;
import com.boristenelsen.baureport.messages.ErrorMessage;
import com.boristenelsen.baureport.messages.SuccessMessage;
import com.boristenelsen.baureport.model.Baumodul;
import com.boristenelsen.baureport.model.GehwegModulDao;
import com.boristenelsen.baureport.model.GehwegModulDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class DashboardController {

    @Autowired
    GehwegmodulService gehwegmodulService;
    @GetMapping("/dashboard/topbaumodule")
    public ResponseEntity<?> populateTopBaumodule(){
        ArrayList<Baumodul> topbaumdodullist = new ArrayList<>();
        Baumodul gehwegabsenkungModul = new Baumodul(1,"Gehwegabsenkung", "Sie benötigen ein Angbeot für eine Gehwegabsenkung schnellmöglich?\n Hier bekommen Sie innerhalb von 24 Stunden ein Angebot!");
        Baumodul kanal = new Baumodul(2,"Kanal", "Kanal Lorem Ipsum");

        topbaumdodullist.add(gehwegabsenkungModul);
        topbaumdodullist.add(kanal);
        return ResponseEntity.ok(topbaumdodullist);
    }

    /**
     * Postmapping für Baumodul mit Pfad /dashboard um Baumodulobjekt zu erstellen
     * @param gehwegModulDto
     * @param principal
     * @return
     */
    @PostMapping("/dashboard/gehwegabsenkungmodul")
    public ResponseEntity<?> createGehwegModul(@ModelAttribute GehwegModulDto gehwegModulDto, Principal principal){
        try {
            gehwegmodulService.saveGehwegmodul(gehwegModulDto,principal);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Gehwegmodul konnte nicht erstellt werden.");

        }
        return ResponseEntity.ok("Gehwegmodul erfolgreich erstellt");
    }

    @GetMapping("/dashboard/gehwegabsenkungmodul")
    public ResponseEntity<?> getGehwegModul(Principal principal){
        GehwegModulDto gehwegModulDto = gehwegmodulService.getGehwegModul(principal);
       if(gehwegModulDto ==null)return ResponseEntity.badRequest().body(new ErrorMessage(true,"Es konnte kein Modul gefunden werden"));
        return ResponseEntity.ok(gehwegModulDto);
    }
}
