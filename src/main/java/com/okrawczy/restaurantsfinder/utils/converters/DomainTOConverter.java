package com.okrawczy.restaurantsfinder.utils.converters;

/**
 * Created by Olaf on 2017-10-28.
 */
public interface DomainTOConverter<E, S> {
    S convertToTO(E entity);
}
