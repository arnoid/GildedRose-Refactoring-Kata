package com.gildedrose

import com.gildedrose.item.repository.ItemRepository
import com.gildedrose.item.repository.ItemType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AgedBrieTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: ItemAdapter

    @BeforeEach
    fun before() {
        testItem = ItemRepository().produceItemAdapter(
                itemType = ItemType.AGED_BRIE,
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun when_updateQuality_Then_SellIn_Degrades() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should drop to zero
        Assertions.assertEquals(0, testItem.sellIn)
        gildedRose.updateQuality()//should drop to negative
        Assertions.assertEquals(-1, testItem.sellIn)
    }

    @Test
    fun when_updateQuality_Then_Quality_Is_Not_Greater_Than_50() {
        testItem.quality = 49
        gildedRose.updateQuality()//should increment
        Assertions.assertEquals(50, testItem.quality)
        gildedRose.updateQuality()//should not increment
        Assertions.assertEquals(50, testItem.quality)
    }

    @Test
    fun when_updateQuality_Before_SellIn_Then_Quality_Improvement_Speed_Is_Default() {
        gildedRose.updateQuality()

        Assertions.assertEquals(
                QUALITY + QUALITY_CHANGE,
                testItem.quality
        )
    }

    @Test
    fun when_updateQuality_After_SellIn_Then_Quality_Improvement_Speed_Is_Increased() {
        testItem.sellIn = 0

        gildedRose.updateQuality()

        Assertions.assertEquals(
                QUALITY + QUALITY_CHANGE * 2,
                testItem.quality
        )
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
        private const val QUALITY_CHANGE = 1
    }
}