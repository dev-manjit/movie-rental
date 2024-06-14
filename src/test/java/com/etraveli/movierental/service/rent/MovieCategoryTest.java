package com.etraveli.movierental.service.rent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class MovieCategoryTest {

    @Test
    void testEnumValues() {
        // Test enum has the expected number of constants
        MovieCategory[] categories = MovieCategory.values();
        assertEquals(4,categories.length);

        // Test that the enum contains specific constants
        assertTrue(EnumSet.of(MovieCategory.REGULAR, MovieCategory.NEW, MovieCategory.CHILDREN, MovieCategory.DEFAULT)
                .containsAll(Arrays.asList(categories)));
    }

    @Test
     void testEnumValueOf() {
        // Test the valueOf method for valid enum constants
        assertEquals(MovieCategory.REGULAR, MovieCategory.valueOf("REGULAR"));
        assertEquals(MovieCategory.NEW, MovieCategory.valueOf("NEW"));
        assertEquals(MovieCategory.CHILDREN, MovieCategory.valueOf("CHILDREN"));
        assertEquals(MovieCategory.DEFAULT, MovieCategory.valueOf("DEFAULT"));
    }
    @Test
    void testInvalidEnumValue_ThrowException() {
        // Test that an exception is thrown for invalid enum constant
        assertThrows(IllegalArgumentException.class, () -> MovieCategory.valueOf("INVALID"));
    }

    @Test
    void testEnumCodes() {
        // Test the getCode method for each enum constant
        assertEquals("Regular", MovieCategory.REGULAR.getCode());
        assertEquals("New", MovieCategory.NEW.getCode());
        assertEquals("Children", MovieCategory.CHILDREN.getCode());
        assertEquals("Default", MovieCategory.DEFAULT.getCode());
    }

}