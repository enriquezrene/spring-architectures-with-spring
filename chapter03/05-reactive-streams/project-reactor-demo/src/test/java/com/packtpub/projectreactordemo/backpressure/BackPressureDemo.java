package com.packtpub.projectreactordemo.backpressure;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BackPressureDemo {

    @Test
    public void givenAListOfElements_WhenTheBackPressureIsPresent_ThenOnlyTheNElementsRequestedArePresent() throws Exception {
        List<Integer> digitsArray = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Iterator<Integer> digitsIterator = digitsArray.iterator();
        Flux<Integer> fluxWithDigits = Flux.fromIterable(digitsArray);

        fluxWithDigits.take(5)
                .log()
                .subscribe(element ->
                        Assert.assertEquals(digitsIterator.next(), element)
                );

        Assert.assertTrue(digitsIterator.next().equals(6));
    }
}
