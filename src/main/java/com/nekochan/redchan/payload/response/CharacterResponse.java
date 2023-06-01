package com.nekochan.redchan.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterResponse {

    private String id;

    private String name;

    private String anime;

    private String gender;

    private Integer age;

}
