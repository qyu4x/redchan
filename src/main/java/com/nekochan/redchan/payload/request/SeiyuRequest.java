package com.nekochan.redchan.payload.request;

import com.nekochan.redchan.entity.Character;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeiyuRequest {

    private String id;

    private String name;

    private List<CharacterRequest> characters;
}
