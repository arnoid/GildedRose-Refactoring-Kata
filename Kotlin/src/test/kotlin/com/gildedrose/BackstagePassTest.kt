package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BackstagePassTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: Item

    @BeforeEach
    fun before() {
        testItem = ItemsLibrary.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.toItem(
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
    fun updateQuality_Check_Quality_Is_Not_Greater_Than_50() {
        testItem.quality = 49
        gildedRose.updateQuality()//should increment
        assertEquals(50, testItem.quality)
        gildedRose.updateQuality()//should not increment
        assertEquals(50, testItem.quality)
    }

    @Test
    fun updateQuality_Before_SellIn_Check_Quality_Improvement_Speed_Is_Default() {
        gildedRose.updateQuality()
        assertEquals(
                QUALITY + GildedRose.QUALITY_DEGRADATION_DEFAULT,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_SellIn_YellowZone_Check_Quality_Improvement_Speed_Is_Doubled() {
        testItem.sellIn = SELL_IN_YELLOW_ZONE
        gildedRose.updateQuality()
        assertEquals(
                QUALITY + GildedRose.QUALITY_DEGRADATION_DEFAULT * 2,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_SellIn_RedZone_Check_Quality_Improvement_Speed_Is_Tripled() {
        testItem.sellIn = SELL_IN_RED_ZONE
        gildedRose.updateQuality()
        assertEquals(
                QUALITY + GildedRose.QUALITY_DEGRADATION_DEFAULT * 3,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_After_SellIn_Check_Quality_Is_Zero() {
        testItem.sellIn = 0

        gildedRose.updateQuality()

        assertEquals(0, testItem.quality)
    }

    companion object {
        private const val SELL_IN = 15
        private const val SELL_IN_YELLOW_ZONE = 10
        private const val SELL_IN_RED_ZONE = 5
        private const val QUALITY = 10
    }
}