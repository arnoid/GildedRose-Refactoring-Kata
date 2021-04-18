package com.gildedrose.item.strategy

import com.gildedrose.Item
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class MultiplierItemQualityStrategyTest {

    lateinit var item: Item

    @BeforeEach
    fun before() {
        item = Item("", SELL_IN, QUALITY)
    }

    private fun produceItemQualityStrategy(
            hasQualityAfterSellIn: Boolean,
            qualityUpdate: Int = QUALITY_CHANGE,
    ) = MultiplierItemQualityStrategy(MULTIPLIER, qualityUpdate, hasQualityAfterSellIn)

    @Test
    fun when_sellIn_Is_Positive_Then_SellIn_Decrement() {
        item.sellIn = 1
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(0, item.sellIn)
    }

    @Test
    fun when_sellIn_Is_Zero_Then_SellIn_Decrement() {
        item.sellIn = 0
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun when_sellIn_Is_Negative_Then_SellIn_Decrement() {
        item.sellIn = -1
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(-2, item.sellIn)
    }

    @Test
    fun when_sellIn_Is_Positive_And_HasQualityAfterSellIn_Then_Quality_Decrement() {
        item.sellIn = 1
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(QUALITY + QUALITY_CHANGE * MULTIPLIER, item.quality)
    }

    @Test
    fun when_sellIn_Is_Zero_And_HasQualityAfterSellIn_Then_Double_Quality_Decrement() {
        item.sellIn = 0
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(QUALITY + QUALITY_CHANGE * MULTIPLIER * 2, item.quality)
    }

    @Test
    fun when_sellIn_Is_Negative_And_HasQualityAfterSellIn_Then_Double_Quality_Decrement() {
        item.sellIn = -1
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(QUALITY + QUALITY_CHANGE * MULTIPLIER * 2, item.quality)
    }

    @Test
    fun when_sellIn_Is_Positive_And_HasQualityAfterSellIn_Is_False_Then_Quality_Decrement() {
        item.sellIn = 1
        val strategy = produceItemQualityStrategy(false)
        strategy.updateQuality(item)
        assertEquals(QUALITY + QUALITY_CHANGE * MULTIPLIER, item.quality)
    }

    @Test
    fun when_sellIn_Is_Zero_And_HasQualityAfterSellIn_Is_False_Then_Quality_Is_Zero() {
        item.sellIn = 0
        val strategy = produceItemQualityStrategy(false)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Negative_And_HasQualityAfterSellIn_Is_False_Then_Quality_Is_Zero() {
        item.sellIn = -1
        val strategy = produceItemQualityStrategy(false)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Positive_And_HasQualityAfterSellIn_Is_True_And_Quality_Is_Zero_Then_Quality_Is_Zero() {
        item.sellIn = 1
        item.quality = 0
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Zero_And_HasQualityAfterSellIn_Is_True_And_Quality_Is_Zero_Then_Quality_Is_Zero() {
        item.sellIn = 0
        item.quality = 0
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Negative_And_HasQualityAfterSellIn_Is_True_And_Quality_Is_Zero_Then_Quality_Is_Zero() {
        item.sellIn = -1
        item.quality = 0
        val strategy = produceItemQualityStrategy(true)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }


    @Test
    fun when_sellIn_Is_Positive_And_HasQualityAfterSellIn_Is_False_And_Quality_Is_Zero_Then_Quality_Is_Zero() {
        item.sellIn = 1
        item.quality = 0
        val strategy = produceItemQualityStrategy(false)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Zero_And_HasQualityAfterSellIn_Is_False_And_Quality_Is_Zero_Then_Quality_Is_Zero() {
        item.sellIn = 0
        item.quality = 0
        val strategy = produceItemQualityStrategy(false)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Negative_And_HasQualityAfterSellIn_Is_False_And_Quality_Is_Zero_Then_Quality_Is_Zero() {
        item.sellIn = -1
        item.quality = 0
        val strategy = produceItemQualityStrategy(false)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Positive_And_HasQualityAfterSellIn_Is_True_And_Quality_Is_50_Then_Quality_Is_50() {
        item.sellIn = 1
        item.quality = 50
        val strategy = produceItemQualityStrategy(true, 1)
        strategy.updateQuality(item)
        assertEquals(50, item.quality)
    }

    @Test
    fun when_sellIn_Is_Zero_And_HasQualityAfterSellIn_Is_True_And_Quality_Is_50_Then_Quality_Is_50() {
        item.sellIn = 0
        item.quality = 50
        val strategy = produceItemQualityStrategy(true, 1)
        strategy.updateQuality(item)
        assertEquals(50, item.quality)
    }

    @Test
    fun when_sellIn_Is_Negative_And_HasQualityAfterSellIn_Is_True_And_Quality_Is_50_Then_Quality_Is_50() {
        item.sellIn = -1
        item.quality = 50
        val strategy = produceItemQualityStrategy(true, 1)
        strategy.updateQuality(item)
        assertEquals(50, item.quality)
    }


    @Test
    fun when_sellIn_Is_Positive_And_HasQualityAfterSellIn_Is_False_And_Quality_Is_50_Then_Quality_Is_50() {
        item.sellIn = 1
        item.quality = 50
        val strategy = produceItemQualityStrategy(false, 1)
        strategy.updateQuality(item)
        assertEquals(50, item.quality)
    }

    @Test
    fun when_sellIn_Is_Zero_And_HasQualityAfterSellIn_Is_False_And_Quality_Is_50_Then_Quality_Is_Яукщ() {
        item.sellIn = 0
        item.quality = 0
        val strategy = produceItemQualityStrategy(false, 1)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    fun when_sellIn_Is_Negative_And_HasQualityAfterSellIn_Is_False_And_Quality_Is_50_Then_Quality_Is_Zero() {
        item.sellIn = -1
        item.quality = 0
        val strategy = produceItemQualityStrategy(false, 1)
        strategy.updateQuality(item)
        assertEquals(0, item.quality)
    }

    companion object {
        const val QUALITY = 10
        const val SELL_IN = 10
        const val MULTIPLIER = 2
        const val QUALITY_CHANGE = -1
    }

}