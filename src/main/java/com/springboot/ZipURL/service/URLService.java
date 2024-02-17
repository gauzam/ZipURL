package com.springboot.ZipURL.service;

import org.springframework.stereotype.Service;

@Service
public interface URLService {

    public Url generateShortLink(UrlDTO urlDTO);
    public Url persistShortLink(Url url);


}
