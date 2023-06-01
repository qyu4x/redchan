package com.nekochan.redchan.service;


import com.nekochan.redchan.payload.request.SeiyuRequest;
import com.nekochan.redchan.payload.response.SeiyuResponse;

import java.util.List;

public interface SeiyuService {

    SeiyuResponse save(SeiyuRequest seiyuRequest);

    List<SeiyuResponse> findAll();

    SeiyuResponse findById(String id);

}
