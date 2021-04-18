package com.gildedrose.item.strategy

import com.gildedrose.Item

/**
 * Default item quality strategy:
 *      - item [DefaultItemQualityStrategy.qualityChange] will be applied once.
 *      - item [DefaultItemQualityStrategy.qualityChange] will be applied second time after sellIn to represent increased item quality change.
 *      - item quality cannot go above 50.
 *      - item quality cannot drop below 0.
 *      - [DefaultItemQualityStrategy.hasQualityAfterSellIn] is set to [false] then after item sellIn is below 0 item quality will become 0. Otherwise it will continue to drop till hit 0.
 */
open class DefaultItemQualityStrategy(
        override val qualityChange: Int,
        override val hasQualityAfterSellIn: Boolean,
) : ItemQualityStrategy {

    override fun updateQuality(item: Item) {
        applyQualityChange(item)

        decrementSellIn(item)

        if (item.sellIn < 0) {
            applyQualityChange(item)
        }

        if (!hasQualityAfterSellIn && item.sellIn < 0) {
            item.quality = 0
        }

        if (item.quality > 50) {
            item.quality = 50
        }

        if (item.quality < 0) {
            item.quality = 0
        }
    }

    protected open fun decrementSellIn(item: Item) {
        item.sellIn -= 1
    }

    protected open fun applyQualityChange(item: Item) {
        item.quality += qualityChange
    }

    override fun toString(): String {
        return "${this::class.simpleName}{qualityChange=$qualityChange, hasQualityAfterSellIn=$hasQualityAfterSellIn}"
    }

}