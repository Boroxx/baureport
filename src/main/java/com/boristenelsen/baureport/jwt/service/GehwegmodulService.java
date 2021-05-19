package com.boristenelsen.baureport.jwt.service;

import com.boristenelsen.baureport.model.GehwegModulDao;
import com.boristenelsen.baureport.model.GehwegModulDto;
import com.boristenelsen.baureport.model.User;
import com.boristenelsen.baureport.repository.GehwegModulRepository;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

@Service
public class GehwegmodulService {

    @Autowired
    GehwegModulRepository gehwegModulRepository;
    @Autowired
    GehwegmodulService gehwegmodulService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    public GehwegModulDao saveGehwegmodul(GehwegModulDto gehwegModulDto, Principal principal) throws IOException {
        GehwegModulDao gehwegModulDao= null;

        User user = customUserDetailsService.getUser(principal.getName());
        try{
                     gehwegModulDao = GehwegModulDao.builder().userid(user.getId())
                    .strasse_hausnummer(gehwegModulDto.getStrasse_hausnummer())
                    .stadt_plz(gehwegModulDto.getStadt_plz()).modulname(gehwegModulDto.getModulname())
                    .vorgarten(gehwegModulDto.getVorgarten())
                    .hinderniss(String.join(" ",Arrays.asList(gehwegModulDto.getHinderniss())))
                    .anmerkung(gehwegModulDto.getAnmerkung()).genehmigung(gehwegModulDto.getGenehmigung().getBytes())
                             .frontansicht(gehwegModulDto.getFrontansicht().getBytes())
                             .laengsansicht(gehwegModulDto.getLaengsansicht().getBytes()).build();

        }catch(Exception e){
            e.printStackTrace();
        }

        if(gehwegModulDao !=null && !hasAlreadyGehwegModul(user)){
            gehwegModulRepository.save(gehwegModulDao);
        }

        return gehwegModulDao;
    }

    public GehwegModulDto getGehwegModul(Principal principal){
        User user = customUserDetailsService.getUser(principal.getName());

        GehwegModulDao gda = gehwegModulRepository.findGehwegModulDaoByUserid(user.getId());
        GehwegModulDto gehwegModulDto = null;
        if(gda!=null){
                     gehwegModulDto = GehwegModulDto.builder().anmerkung(gda.getAnmerkung())
                    .stadt_plz(gda.getStadt_plz())
                    .strasse_hausnummer(gda.getStrasse_hausnummer())
                    .anmerkung(gda.getAnmerkung())
                    .modulname(gda.getModulname())
                    .hinderniss(gda.getHinderniss().split(" "))
                    .user_id(gda.getUserid())
                    .id(gda.getId())
                    .vorgarten(gda.vorgarten)
                    .build();
        }

        return  gehwegModulDto;


    }
    private boolean hasAlreadyGehwegModul(User user){
        return gehwegModulRepository.findGehwegModulDaoByUserid(user.getId()) != null;
    }
}
