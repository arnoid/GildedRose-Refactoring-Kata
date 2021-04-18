package com.gildedrose

import com.gildedrose.item.strategy.ItemQualityStrategy

/**
 * This class allows application of special quality update rules.
 *
 * @param item item which is receiving updates
 * @param itemQualityStrategy quality update strategy
 */
//TODO: Extract [Item] interface so [ItemAdapter] can implement it as delegate
open class ItemAdapter constructor(
        private val item: Item,
        private val itemQualityStrategy: ItemQualityStrategy,
) {

    var sellIn: Int
        get() = item.sellIn
        set(value) {
            item.sellIn = value
        }

    var quality: Int
        get() = item.quality
        set(value) {
            item.quality = value
        }

    var name: String
        get() = item.name
        set(value) {
            item.name = value
        }

    fun updateQuality() = itemQualityStrategy.updateQuality(item)

    override fun toString(): String {
        return "item=${item} strategy=${itemQualityStrategy}"
    }

}