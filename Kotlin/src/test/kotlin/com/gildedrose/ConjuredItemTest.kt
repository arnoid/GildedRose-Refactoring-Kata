package com.gildedrose

import com.gildedrose.item.repository.ItemRepository
import com.gildedrose.item.repository.ItemType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConjuredItemTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: ItemAdapter

    @BeforeEach
    fun before() {
        testItem = ItemRepository().produceItemAdapter(
                itemType = ItemType.CONJURED_MANA_CAKE,
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun when_updateQuality_Then_SellIn_Degrades() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.sellIn)
        gildedRose.updateQuality()//should drop to negative
        assertEquals(-1, testItem.sellIn)
    }

    @Test
    fun when_updateQuality_Then_Quality_Is_Non_Negative() {
        testItem.quality = 1
        gildedRose.updateQuality()//should drop to zero
        assertEquals(0, testItem.quality)
        gildedRose.updateQuality()//should not drop to negative
        assertEquals(0, testItem.quality)
    }

    @Test
    fun when_updateQuality_Before_SellIn_Then_Quality_Degradation_Speed_Is_Default() {
        gildedRose.updateQuality()

        assertEquals(
                QUALITY + QUALITY_CHANGE * 2,
                testItem.quality
        )
    }

    @Test
    fun when_updateQuality_After_SellIn_Then_Quality_Degradation_Speed_Is_Increased() {
        testItem.sellIn = 0
        gildedRose.updateQuality()

        assertEquals(
                QUALITY + QUALITY_CHANGE * 4,
                testItem.quality
        )
    }

    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
        private const val QUALITY_CHANGE = -1
    }
}