package com.nekochan.redchan.controller;

import com.nekochan.redchan.payload.request.SeiyuRequest;
import com.nekochan.redchan.payload.response.SeiyuResponse;
import com.nekochan.redchan.payload.response.WebResponse;
import com.nekochan.redchan.service.SeiyuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/redchan")
public class SeiyuController {

    private final SeiyuService seiyuService;

    public SeiyuController(SeiyuService seiyuService) {
        this.seiyuService = seiyuService;
    }


    @PostMapping(path = "/seiyu")
     public ResponseEntity<WebResponse<SeiyuResponse>> save(@RequestBody SeiyuRequest seiyuRequest) {
        SeiyuResponse seiyuResponse = seiyuService.save(seiyuRequest);

        WebResponse<SeiyuResponse> webResponse = new WebResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                seiyuResponse
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);

    }

    @GetMapping(path = "/seiyu/{id}")
    public ResponseEntity<WebResponse<SeiyuResponse>> findById(@PathVariable("id") String id) {
        SeiyuResponse seiyuResponse = seiyuService.findById(id);

        WebResponse<SeiyuResponse> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                seiyuResponse
        );

        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }

    @GetMapping(path = "/seiyus")
    public ResponseEntity<WebResponse<List<SeiyuResponse>>> findAll() {
        List<SeiyuResponse> seiyuResponse = seiyuService.findAll();

        WebResponse<List<SeiyuResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                seiyuResponse
        );

        return ResponseEntity.status(HttpStatus.OK).body(webResponse);
    }
}
