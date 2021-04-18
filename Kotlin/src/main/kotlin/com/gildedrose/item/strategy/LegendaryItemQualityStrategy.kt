package com.gildedrose.item.strategy

import com.gildedrose.Item

/**
 * Legendary Item quality strategy:
 *   - item quality is not changing,
 *   - item quality is not changing after sellIn
 */
class LegendaryItemQualityStrategy : ItemQualityStrategy, DefaultItemQualityStrategy(0, true) {

    override val qualityChange: Int
        get() = 0
    override val hasQualityAfterSellIn: Boolean
        get() = true

    override fun updateQuality(item: Item) = Unit

    override fun decrementSellIn(item: Item) = Unit

    override fun toString(): String {
        return "${this::class.simpleName}{}"
    }
}