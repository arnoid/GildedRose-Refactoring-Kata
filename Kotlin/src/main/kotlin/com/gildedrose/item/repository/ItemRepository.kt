package com.gildedrose.item.repository

import com.gildedrose.Item
import com.gildedrose.ItemAdapter
import com.gildedrose.item.strategy.DefaultItemQualityStrategy
import com.gildedrose.item.strategy.LegendaryItemQualityStrategy
import com.gildedrose.item.strategy.StagedSellInItemQualityStrategy

/**
 * Mimic for persistent item storage and hide item creation logic.
 */
class ItemRepository {

    fun produceItemAdapter(itemType: ItemType, sellIn: Int, quality: Int) = ItemAdapter(
            produceItem(itemType, sellIn, quality),
            produceItemQualityStrategy(itemType)
    )

    private fun produceItemQualityStrategy(itemType: ItemType) = when (itemType) {
        ItemType.AGED_BRIE -> DefaultItemQualityStrategy(
                qualityChange = 1,
                hasQualityAfterSellIn = true
        )
        ItemType.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT -> StagedSellInItemQualityStrategy(
                qualityChange = 1,
                hasQualityAfterSellIn = false,
                sellInStages = listOf(10, 5)
        )
        ItemType.CONJURED_MANA_CAKE -> DefaultItemQualityStrategy(
                qualityChange = -2,
                hasQualityAfterSellIn = true
        )
        ItemType.ELIXIR_OF_THE_MONGOOSE -> DefaultItemQualityStrategy(
                qualityChange = -1,
                hasQualityAfterSellIn = true
        )
        ItemType.PLUS_5_DEXTERITY_VEST -> DefaultItemQualityStrategy(
                qualityChange = -1,
                hasQualityAfterSellIn = true
        )
        ItemType.SULFURAS_HAND_OF_RAGNAROS -> LegendaryItemQualityStrategy()
    }

    private fun produceItem(itemType: ItemType, sellIn: Int, quality: Int) =
            when (itemType) {
                ItemType.AGED_BRIE -> Item(
                        name = "Aged Brie",
                        sellIn = sellIn,
                        quality = quality,
                )
                ItemType.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT -> Item(
                        name = "Backstage passes to a TAFKAL80ETC concert",
                        sellIn = sellIn,
                        quality = quality
                )
                ItemType.CONJURED_MANA_CAKE -> Item(
                        name = "Conjured Mana Cake",
                        sellIn = sellIn,
                        quality = quality
                )
                ItemType.ELIXIR_OF_THE_MONGOOSE -> Item(
                        name = "Elixir of the Mongoose",
                        sellIn = sellIn,
                        quality = quality
                )
                ItemType.PLUS_5_DEXTERITY_VEST -> Item(
                        name = "+5 Dexterity Vest",
                        sellIn = sellIn,
                        quality = quality
                )
                ItemType.SULFURAS_HAND_OF_RAGNAROS -> Item(
                        name = "Sulfuras, Hand of Ragnaros",
                        sellIn = sellIn,
                        quality = quality
                )
            }

}

enum class ItemType {
    AGED_BRIE,
    BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT,
    CONJURED_MANA_CAKE,
    ELIXIR_OF_THE_MONGOOSE,
    PLUS_5_DEXTERITY_VEST,
    SULFURAS_HAND_OF_RAGNAROS
}