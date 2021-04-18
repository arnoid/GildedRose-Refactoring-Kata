package com.gildedrose.item.strategy

import com.gildedrose.Item

/**
 * Applies [ItemQualityStrategy.updateQuality] [MultiplierItemQualityStrategy.multiplier] number of times
 */
class MultiplierItemQualityStrategy(
        private val multiplier: Int,
        override val qualityChange: Int,
        override val hasQualityAfterSellIn: Boolean,
) : ItemQualityStrategy, DefaultItemQualityStrategy(qualityChange, hasQualityAfterSellIn) {

    override fun applyQualityChange(item: Item) = repeat(multiplier) { count ->
        super.applyQualityChange(item)
    }

    override fun toString(): String {
        return "${this::class.simpleName}{qualityChange=$qualityChange, multiplier=$multiplier, hasQualityAfterSellIn=$hasQualityAfterSellIn}"
    }
}