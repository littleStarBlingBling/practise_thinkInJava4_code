package com.practise.enumerated;

public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
