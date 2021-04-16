package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConjuredItemTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: Item

    @BeforeEach
    fun before() {
        testItem = ItemsLibrary.CONJURED_MANA_CAKE.toItem(
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun updateQuality_Check_SellIn_Degrades() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.sellIn)
        gildedRose.updateQuality()//should drop to negative
        assertEquals(-1, testItem.sellIn)
    }

    @Test
    fun updateQuality_Check_Quality_Is_Non_Negative() {
        testItem.quality = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.quality)
        gildedRose.updateQuality()//should not drop to negative
        assertEquals(0, testItem.quality)
    }

    @Test
    fun updateQuality_Before_SellIn_Check_Quality_Degradation_Speed_Is_Default() {
        gildedRose.updateQuality()

        assertEquals(
                QUALITY - GildedRose.QUALITY_DEGRADATION_DEFAULT * 2,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_After_SellIn_Check_Quality_Degradation_Speed_Is_Increased() {
        testItem.sellIn = 0
        gildedRose.updateQuality()

        assertEquals(
                QUALITY - GildedRose.QUALITY_DEGRADATION_DEFAULT_AFTER_SELL_IN * 2,
                testItem.quality
        )
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
    }
}