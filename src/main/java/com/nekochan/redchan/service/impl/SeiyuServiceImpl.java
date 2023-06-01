package com.nekochan.redchan.service.impl;

import com.nekochan.redchan.entity.Character;
import com.nekochan.redchan.entity.Seiyu;
import com.nekochan.redchan.exception.DataNotFoundException;
import com.nekochan.redchan.payload.request.SeiyuRequest;
import com.nekochan.redchan.payload.response.CharacterResponse;
import com.nekochan.redchan.payload.response.SeiyuResponse;
import com.nekochan.redchan.repository.SeiyuRepository;
import com.nekochan.redchan.service.SeiyuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableCaching
public class SeiyuServiceImpl implements SeiyuService {

    private SeiyuRepository seiyuRepository;

    public SeiyuServiceImpl(SeiyuRepository seiyuRepository) {
        this.seiyuRepository = seiyuRepository;
    }

    @Override
    public SeiyuResponse save(SeiyuRequest seiyuRequest) {
        List<Character> characters = new ArrayList<>();
        Seiyu seiyu = Seiyu.builder()
                .id("ss-".concat(UUID.randomUUID().toString()))
                .name(seiyuRequest.getName())
                .build();

        seiyuRequest.getCharacters().forEach(characterRequest -> {
            Character character = Character.builder()
                    .id("cc-".concat(UUID.randomUUID().toString()))
                    .name(characterRequest.getName())
                    .anime(characterRequest.getAnime())
                    .gender(characterRequest.getGender())
                    .age(characterRequest.getAge())
                    .seiyu(seiyu)
                    .build();
            characters.add(character);
        });

        seiyu.setCharacters(characters);
        seiyuRepository.save(seiyu);

        List< CharacterResponse> characterResponses = new ArrayList<>();
        seiyuRequest.getCharacters().stream().forEach(characterRequest -> {
            CharacterResponse characterResponse = CharacterResponse.builder()
                    .id("cc-".concat(UUID.randomUUID().toString()))
                    .name(characterRequest.getName())
                    .anime(characterRequest.getAnime())
                    .gender(characterRequest.getGender())
                    .age(characterRequest.getAge())
                    .build();
            characterResponses.add(characterResponse);
        });

        return SeiyuResponse.builder()
                .id("ss-".concat(UUID.randomUUID().toString()))
                .name(seiyuRequest.getName())
                .characters(characterResponses)
                .build();
    }

    @Transactional
    @Override
    @Cacheable("seiyus")
    public List<SeiyuResponse> findAll() {
        doLongRunningTask();
        doLongRunningTask();
        List<SeiyuResponse> seiyuResponses = new ArrayList<>();
        List<Seiyu> seiyus = seiyuRepository.findAll();

        seiyus.stream().forEach(seiyu -> {
            List<CharacterResponse> characterResponses = new ArrayList<>();
            seiyu.getCharacters().stream().forEach(character -> {
                CharacterResponse characterResponse = CharacterResponse.builder()
                        .id(character.getId())
                        .name(character.getName())
                        .anime(character.getAnime())
                        .gender(character.getGender())
                        .age(character.getAge())
                        .build();
                characterResponses.add(characterResponse);
            });
            SeiyuResponse seiyuResponse = SeiyuResponse.builder()
                    .id(seiyu.getId())
                    .name(seiyu.getName())
                    .characters(characterResponses)
                    .build();
            seiyuResponses.add(seiyuResponse);
        });

        return seiyuResponses;
    }

    @Transactional
    @Override
    @Cacheable(key = "#id", value = "seiyu")
    public SeiyuResponse findById(String id) {
        doLongRunningTask();
        Seiyu seiyu = seiyuRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Opps seiyu not found"));
        List<CharacterResponse> characterResponses = new ArrayList<>();
        seiyu.getCharacters().stream().forEach(character -> {
            CharacterResponse characterResponse = CharacterResponse.builder()
                    .id(character.getId())
                    .name(character.getName())
                    .anime(character.getAnime())
                    .gender(character.getGender())
                    .age(character.getAge())
                    .build();
            characterResponses.add(characterResponse);
        });
        return SeiyuResponse.builder()
                .id(seiyu.getId())
                .name(seiyu.getName())
                .characters(characterResponses)
                .build();

    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.getStackTrace();
        }
    }
}
