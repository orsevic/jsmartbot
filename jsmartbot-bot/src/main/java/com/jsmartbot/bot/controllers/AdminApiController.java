package com.jsmartbot.bot.controllers;

import com.jsmartbot.bot.api.dto.PhraseDto;
import com.jsmartbot.bot.services.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin-api")
public class AdminApiController {

    @Autowired
    private AdminApiService adminApiService;

    @PutMapping(value = "add")
    public List<PhraseDto> add (@RequestBody PhraseDto element) {
        return adminApiService.add(element);
    }

    @DeleteMapping(value = "delete")
    public List<PhraseDto> delete (@RequestBody PhraseDto element) {
        return adminApiService.delete(element);
    }

    @GetMapping(value = "list")
    public List<PhraseDto> list () {
        return adminApiService.list();
    }
}
