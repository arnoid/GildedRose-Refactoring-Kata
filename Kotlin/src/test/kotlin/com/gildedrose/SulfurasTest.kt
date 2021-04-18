package com.gildedrose

import com.gildedrose.item.repository.ItemRepository
import com.gildedrose.item.repository.ItemType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SulfurasTest {

    lateinit var gildedRose: GildedRose
    lateinit var testItem: ItemAdapter

    @BeforeEach
    fun before() {
        testItem = ItemRepository().produceItemAdapter(
                itemType = ItemType.SULFURAS_HAND_OF_RAGNAROS,
                sellIn = SELL_IN,
                quality = QUALITY
        )

        gildedRose = GildedRose(arrayOf(testItem))
    }

    @Test
    fun when_updateQuality_Then_SellIn_Is_Not_Changing() {
        testItem.sellIn = 1
        gildedRose.updateQuality()//should not change
        assertEquals(1, testItem.sellIn)
    }

    @Test
    fun when_updateQuality_Then_Quality_Is_Not_Changing() {
        testItem.quality = 1
        gildedRose.updateQuality()//should not change
        assertEquals(1, testItem.quality)
    }

    @Test
    fun when_updateQuality_Above_Allowed_Level_Then_Quality_Is_Not_Changing() {
        testItem.quality = 80
        gildedRose.updateQuality()//should not change
        assertEquals(80, testItem.quality)
    }

    @Test
    fun when_updateQuality_After_SellIn_Then_Quality_Is_Not_Changing() {
        testItem.sellIn = 0
        gildedRose.updateQuality()
        assertEquals(QUALITY, testItem.quality)
    }

    @Test
    fun when_updateQuality_After_SellIn_And_Quality_Above_Allowed_Level_Then_Quality_Is_Not_Changing() {
        testItem.quality = 80
        testItem.sellIn = -1
        gildedRose.updateQuality()
        assertEquals(80, testItem.quality)
    }


    companion object {
        private const val SELL_IN = 5
        private const val QUALITY = 10
    }
}