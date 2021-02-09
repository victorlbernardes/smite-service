package br.com.victor.smite.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language  {
    English(1),
    German(2),
    French(3),
    Chinese(5),
    Spanish(7),
    Latin_Spanish(9),
    Portuguese(10),
    Russian(11),
    Polish(12),
    Turkish(13);

    private final Integer id;

    public String getName() {
        return name().replace("_", " ");
    }

    public String toString() {
        return getName();
    }
}
