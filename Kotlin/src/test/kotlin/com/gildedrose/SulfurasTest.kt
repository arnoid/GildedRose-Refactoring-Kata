package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SulfurasTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: Item

    @BeforeEach
    fun before() {
        testItem = ItemsLibrary.SULFURAS_HAND_OF_RAGNAROS.toItem(
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun degradeQualityCheckSellIsNotChanging() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should not change
        assertEquals(1, testItem.sellIn)
    }

    @Test
    fun degradeQualityCheckQualityIsNotChanging() {
        testItem.quality = 1
        gildedRose.updateQuality()//should not change
        assertEquals(1, testItem.quality)
    }

    @Test
    fun degradeQualityAfterSellInCheckQualityIsNotChanging() {
        testItem.sellIn = 0

        gildedRose.updateQuality()

        assertEquals(QUALITY, testItem.quality)
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
    }
}