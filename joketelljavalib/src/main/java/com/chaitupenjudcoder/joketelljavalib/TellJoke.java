package com.chaitupenjudcoder.joketelljavalib;

import java.util.Random;

public class TellJoke {

    private String jokes[] = {"Employee of the month is a good example of how somebody can be both a winner and a loser at the same time.",
    "It\'s not that I\'m afraid to die, I just don\'t want to be there when it happens.",
    "I don\'t have a girlfriend, but I know a girl that would get really mad if she heard me say that.",
    "Why couldn\'t the leopard play hide and seek? \n" +
            "Because he was always spotted."};

    public String getJoke() {
        int no;
        Random r = new Random();
        no = r.nextInt(4);
        return jokes[no];
    }
}
