package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AgedBrieTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: Item

    @BeforeEach
    fun before() {
        testItem = ItemsLibrary.AGED_BRIE.toItem(
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun updateQuality_Check_SellIn_Degrades() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should drop to zero
        Assertions.assertEquals(0, testItem.sellIn)
        gildedRose.updateQuality()//should drop to negative
        Assertions.assertEquals(-1, testItem.sellIn)
    }

    @Test
    fun updateQuality_Check_Quality_Is_Not_Greater_Than_50() {
        testItem.quality = 49
        gildedRose.updateQuality()//should increment
        Assertions.assertEquals(50, testItem.quality)
        gildedRose.updateQuality()//should not increment
        Assertions.assertEquals(50, testItem.quality)
    }

    @Test
    fun updateQuality_Before_SellIn_Check_Quality_Improvement_Speed_Is_Default() {
        gildedRose.updateQuality()

        Assertions.assertEquals(
                QUALITY + GildedRose.QUALITY_DEGRADATION_DEFAULT,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_After_SellIn_Check_Quality_Improvement_Speed_Is_Increased() {
        testItem.sellIn = 0

        gildedRose.updateQuality()

        Assertions.assertEquals(
                QUALITY + GildedRose.QUALITY_DEGRADATION_DEFAULT_AFTER_SELL_IN,
                testItem.quality
        )
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
    }
}