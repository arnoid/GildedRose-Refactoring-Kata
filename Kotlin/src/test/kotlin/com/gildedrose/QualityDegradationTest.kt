package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QualityDegradationTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: Item

    @BeforeEach
    fun before() {
        testItem = Item(
                name = "",
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun updateQualityCheckSellInDegrades() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.sellIn)
        gildedRose.updateQuality()//should drop to negative
        assertEquals(-1, testItem.sellIn)
    }

    @Test
    fun updateQualityCheckQualityIsNonNegative() {
        testItem.quality = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.quality)
        gildedRose.updateQuality()//should not drop to negative
        assertEquals(0, testItem.quality)
    }

    @Test
    fun updateQualityBeforeSellInCheckQualityDegradationSpeedIsDefault() {
        gildedRose.updateQuality()

        assertEquals(
                QUALITY - GildedRose.QUALITY_DEGRADATION_DEFAULT,
                testItem.quality
        )
    }

    @Test
    fun updateQualityAfterSellInCheckQualityDegradationSpeedIsIncreased() {
        testItem.sellIn = 0
        gildedRose.updateQuality()

        assertEquals(
                QUALITY - GildedRose.QUALITY_DEGRADATION_DEFAULT_AFTER_SELL_IN,
                testItem.quality
        )
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
    }
}