package com.gildedrose.item.strategy

import com.gildedrose.Item

interface ItemQualityStrategy {
    /**
     * Amount of item quality change per quality update
     */
    val qualityChange: Int

    /**
     * Specifies if item has quality after SellIn hits 0
     * if [true] - item quality will become equal to 0
     * if [false] - item quality will continue to change
     */
    val hasQualityAfterSellIn: Boolean

    fun updateQuality(item: Item)
}