package com.okrawczy.restaurantsfinder.utils.converters;

/**
 * Created by Olaf on 2017-10-25.
 */

public interface DomainStubConverter<E, S> {
    S convertToStub(E entity);
}
