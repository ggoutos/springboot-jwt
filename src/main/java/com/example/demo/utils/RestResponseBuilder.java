package com.example.demo.utils;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.example.demo.utils.Messages.HAS_NEXT_PAGE_HEADER;
import static com.example.demo.utils.Messages.NO_DATA_MESSAGE;
import static org.springframework.http.ResponseEntity.ok;

public class RestResponseBuilder {

    public static ResponseEntity build(Slice<?> data) {
        data.getContent().stream().findAny()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, NO_DATA_MESSAGE));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HAS_NEXT_PAGE_HEADER, "" + data.hasNext());

        return new ResponseEntity<>(data.getContent(), headers, HttpStatus.OK);
    }

    public static ResponseEntity build(List data) {
        return ok(Optional.of(data)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, NO_DATA_MESSAGE)));
    }
}
