package com.gildedrose

enum class ItemsLibrary(private val title: String) {
    PLUS_5_DEXTERITY_VEST("+5 Dexterity Vest"),
    AGED_BRIE("Aged Brie"),
    ELIXIR_OF_THE_MONGOOSE("Elixir of the Mongoose"),
    SULFURAS_HAND_OF_RAGNAROS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT("Backstage passes to a TAFKAL80ETC concert"),
    CONJURED_MANA_CAKE("Conjured Mana Cake");

    fun toItem(sellIn: Int, quality: Int) = Item(
            name = title,
            sellIn = sellIn,
            quality = quality
    )
}