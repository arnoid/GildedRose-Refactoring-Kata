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
    fun degradeQualityCheckSellInDegrades() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.sellIn)
        gildedRose.updateQuality()//should drop to negative
        assertEquals(-1, testItem.sellIn)
    }

    @Test
    fun degradeQualityCheckQualityIsNonNegative() {
        testItem.quality = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.quality)
        gildedRose.updateQuality()//should not drop to negative
        assertEquals(0, testItem.quality)
    }

    @Test
    fun degradeQualityBeforeSellInCheckQualityDegradationSpeedIsDefault() {
        val previousQualityValue = testItem.quality
        gildedRose.updateQuality()

        assertEquals(
                previousQualityValue - GildedRose.QUALITY_DEGRADATION_DEFAULT,
                testItem.quality
        )
    }

    @Test
    fun degradeQualityAfterSellInCheckQualityDegradationSpeedIsIncreased() {
        testItem.sellIn = 0
        val previousQualityValue = testItem.quality
        gildedRose.updateQuality()

        assertEquals(
                previousQualityValue - GildedRose.QUALITY_DEGRADATION_DEFAULT_AFTER_SELL_IN,
                testItem.quality
        )
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
    }
}