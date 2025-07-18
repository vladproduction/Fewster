package com.vladproduction.fewster.controller;

import com.vladproduction.fewster.dto.UrlDTO;
import com.vladproduction.fewster.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/url")
public class UrlRestController {

    private static final Logger log = LoggerFactory.getLogger(UrlRestController.class);

    private final UrlService urlService;

    public UrlRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**[POST] Create List of URLs API and save to database
    ResponseEntity with status code 201 CREATED*/
    @PostMapping("/batch")
    public ResponseEntity<List<UrlDTO>> createAll(@RequestBody List<String> listUrlsText){

        log.info("Received request to create List of short URLs for: {}", listUrlsText);
        List<UrlDTO> createdUrls = urlService.createAll(listUrlsText);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUrls);

    }

    /**[POST] Create url API and save to database
    ResponseEntity with status code 201 CREATED*/
    @PostMapping
    public ResponseEntity<UrlDTO> create(@RequestParam String urlText){

        log.info("Received request to create short URL for: {}", urlText);
        UrlDTO createdUrl = urlService.create(urlText);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUrl);

    }

    /**[GET] Get by ID API and return from database if exists, otherwise friendly exception
    ResponseEntity with status code 200 SUCCESS*/
    @GetMapping("/{id}")
    public ResponseEntity<UrlDTO> getByID(@PathVariable Long id){

        log.info("Received request to get URL by ID: {}", id);
        UrlDTO urlById = urlService.getById(id);
        return ResponseEntity.ok(urlById);

    }

    /**[GET] Get All API and return from database if exists, otherwise friendly exception
    ResponseEntity with status code 200 SUCCESS*/
    @GetMapping("/all")
    public ResponseEntity<List<UrlDTO>> getAll(){

        log.info("Received request to get ALL URLs");
        List<UrlDTO> allUrls = urlService.getAll();
        return ResponseEntity.ok(allUrls);

    }

    /**[GET] Get URL by short URL passing in request param
    ResponseEntity with status code 200 SUCCESS*/
    @GetMapping("/short")
    public ResponseEntity<UrlDTO> getByShortUrl(@RequestParam String shortUrl){

        log.info("Received request to get URL by short URL: {}", shortUrl);
        UrlDTO urlDto = urlService.getByShortUrl(shortUrl);
        return ResponseEntity.ok(urlDto);

    }

}














