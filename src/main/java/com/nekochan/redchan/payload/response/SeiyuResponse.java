package com.nekochan.redchan.payload.response;

import com.nekochan.redchan.payload.request.CharacterRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeiyuResponse {

    private String id;

    private String name;

    private List<CharacterResponse> characters;

}
