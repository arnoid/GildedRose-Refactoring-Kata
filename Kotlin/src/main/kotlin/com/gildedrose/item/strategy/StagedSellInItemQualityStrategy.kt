package com.gildedrose.item.strategy

import com.gildedrose.Item

/**
 * Staged sellIn item quality strategy applies [ItemQualityStrategy.applyQualityChange] for each item in the [StagedSellInItemQualityStrategy.sellInStages] that is equal or less than current [Item.sellIn].
 *
 * For Example:
 * ```
 * sellInStages = arrayOf(5, 10)
 * ```
 *
 * If `item.sellIn = 15`,then `qualityChange` will be applied once, as none of the stages is equal or below the [Item.sellIn]
 * If `item.sellIn = 10`,then `qualityChange` will be applied once and one more time for stage 10 as it follows the rule "stage is equal or below the [Item.sellIn]"
 * If `item.sellIn = 5`,then `qualityChange` will be applied once and one more time for each stage 10 and 5 as all of then follow the rule "stage is equal or below the [Item.sellIn]"
 */
open class StagedSellInItemQualityStrategy(
        override val qualityChange: Int,
        override val hasQualityAfterSellIn: Boolean,
        private val sellInStages: List<Int>,
) : ItemQualityStrategy, DefaultItemQualityStrategy(qualityChange, hasQualityAfterSellIn) {

    override fun applyQualityChange(item: Item) {
        super.applyQualityChange(item)

        sellInStages.forEach { sellInStage ->
            if (item.sellIn <= sellInStage) {
                super.applyQualityChange(item)
            }
        }
    }

    override fun toString(): String {
        return "${this::class.simpleName}{qualityChange=$qualityChange, stages=$sellInStages, hasQualityAfterSellIn=$hasQualityAfterSellIn}"
    }

}