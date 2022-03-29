package com.edu.iga.genetics;

import lombok.Getter;

@Getter
public class ChromosomePair {
    private final Chromosome first;
    private final Chromosome second;

    public ChromosomePair(Chromosome c1, Chromosome c2) {
        this.first = c1;
        this.second = c2;
    }

}
