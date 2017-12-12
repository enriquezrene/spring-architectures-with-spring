package com.packtpub.projectreactordemo.backpressure;

import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BackPressureDemo {

    @Test
    public void givenAListOfElementsWhenTheBackPressureIsPresentThenOnlyTheNElementsRequestedArePresent() throws Exception {
        List<Integer> digits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Iterator<Integer> digitsIterator = digits.iterator();

        Flux<Integer> digitsFlux = Flux.fromIterable(digits);

        digitsFlux.take(5)
                .subscribe(element ->
                Assert.assertEquals(digitsIterator.next(), element)
        );
        Assert.assertEquals(Integer.valueOf(6), digitsIterator.next());
    }
}
