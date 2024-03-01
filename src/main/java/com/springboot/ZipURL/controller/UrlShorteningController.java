package com.springboot.ZipURL.controller;

import com.springboot.ZipURL.model.Url;
import com.springboot.ZipURL.model.UrlDTO;
import com.springboot.ZipURL.model.UrlErrorResponseDTO;
import com.springboot.ZipURL.model.UrlResponseDTO;
import com.springboot.ZipURL.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShorteningController {

    private URLService urlService;

    @Autowired
    public UrlShorteningController(URLService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlDTO urlDTO){

        Url urlToReturn = urlService.generateShortLink(urlDTO);

        if(urlToReturn != null){
            UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
            urlResponseDTO.setOriginalUrl(urlToReturn.getOriginalUrl());
            urlResponseDTO.setShortLink(urlToReturn.getShortLink());
            urlResponseDTO.setExpirationDate(urlToReturn.getExpirationDate());

            return new ResponseEntity<UrlResponseDTO>(urlResponseDTO, HttpStatus.OK);
        }

        UrlErrorResponseDTO urlErrorResponseDTO = new UrlErrorResponseDTO();
        urlErrorResponseDTO.setError("404");
        urlErrorResponseDTO.setStatus("There is an error processing your request. Please try again after some time!");

        return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.NOT_FOUND);

    }
}
