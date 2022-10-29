package com.rtaylor02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobStandardizerTest {
    JobStandardizer js = new JobStandardizer();

    @Test
    void standardizeTest_SoftwareEngineer() {
        assertEquals("Software engineer",js.standardize("Java engineer"));
    }

    @Test
    void standardizeTest_nullInputTitle() {
        assertThrows(NullPointerException.class,() -> js.standardize(null));
    }

    @Test
    void calculateQualityScoreTest_NoMatch() {
        assertEquals(0,js.calculateQualityScore("Sales"));
    }

    @Test
    void calculateQualityScoreTest_1Match() {
        assertEquals(0.5,js.calculateQualityScore("Java engineer"));
    }

    @Test
    void calculateQualityScoreTest_FullMatch() {
        assertEquals(1,js.calculateQualityScore("Accountant"));
    }

    @Test
    void matchStandardTitleTest_NoMatch() {
        assertEquals(-1,js.matchStandardTitle("Sales"));
    }

    @Test
    void matchStandardTitleTest_1Match() {
        assertEquals(0,js.matchStandardTitle("Java engineer"));
    }

    @Test
    void matchStandardTitleTest_FullMatch() {
        assertEquals(3,js.matchStandardTitle("Quantity surveyor"));
    }
}