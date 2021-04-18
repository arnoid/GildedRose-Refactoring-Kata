package com.gildedrose

import com.gildedrose.item.repository.ItemRepository
import com.gildedrose.item.repository.ItemType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BackstagePassTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: ItemAdapter

    @BeforeEach
    fun before() {
        testItem = ItemRepository().produceItemAdapter(
                itemType = ItemType.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT,
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
                QUALITY + QUALITY_CHANGE,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_SellIn_YellowZone_Check_Quality_Improvement_Speed_Is_Doubled() {
        testItem.sellIn = SELL_IN_STAGE_YELLOW_ZONE
        gildedRose.updateQuality()
        assertEquals(
                QUALITY + QUALITY_CHANGE * 2,
                testItem.quality
        )
    }

    @Test
    fun updateQuality_SellIn_RedZone_Check_Quality_Improvement_Speed_Is_Tripled() {
        testItem.sellIn = SELL_IN_STAGE_RED_ZONE
        gildedRose.updateQuality()
        assertEquals(
                QUALITY + QUALITY_CHANGE * 3,
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
        private const val QUALITY = 10
        private const val QUALITY_CHANGE = 1
        private const val SELL_IN_STAGE_RED_ZONE = 5
        private const val SELL_IN_STAGE_YELLOW_ZONE = 10
    }
}