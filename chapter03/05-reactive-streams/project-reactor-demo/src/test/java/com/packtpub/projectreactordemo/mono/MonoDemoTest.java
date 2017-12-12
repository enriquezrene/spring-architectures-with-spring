package com.packtpub.projectreactordemo.mono;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Mono;

public class MonoDemoTest {

    @Test
    public void givenATextWhenTheSubscriptionHappensThenTheTextIsReceived() throws Exception {
        String text = "Hello world";
        Mono<String> prefixUsa = Mono.just(text);

        prefixUsa.subscribe(element -> Assert.assertEquals(text, element));
    }

    @Test
    public void givenAnEmptyMonoWhenTheSubscriptionHappensThenTheResultIsEmpty() throws Exception {
        Mono<String> prefixUsa = Mono.empty();

        prefixUsa.subscribe(element -> Assert.assertEquals(Mono.empty(), element));
    }
}
