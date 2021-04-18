package com.gildedrose

import com.gildedrose.item.repository.ItemRepository
import com.gildedrose.item.repository.ItemType

fun main(args: Array<String>) {

    println("OMGHAI!")

    val itemRepository = ItemRepository()


    val items = arrayOf(
            itemRepository.produceItemAdapter(ItemType.PLUS_5_DEXTERITY_VEST, 10, 20), //
            itemRepository.produceItemAdapter(ItemType.AGED_BRIE, 2, 0), //
            itemRepository.produceItemAdapter(ItemType.ELIXIR_OF_THE_MONGOOSE, 5, 7), //
            itemRepository.produceItemAdapter(ItemType.SULFURAS_HAND_OF_RAGNAROS, 0, 80), //
            itemRepository.produceItemAdapter(ItemType.SULFURAS_HAND_OF_RAGNAROS, -1, 80),
            itemRepository.produceItemAdapter(ItemType.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20),
            itemRepository.produceItemAdapter(ItemType.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 10, 49),
            itemRepository.produceItemAdapter(ItemType.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 49),
            // this conjured item does not work properly yet
            itemRepository.produceItemAdapter(ItemType.CONJURED_MANA_CAKE, 3, 6)
    )

    val app = GildedRose(items)

    var days = 2
    if (args.size > 0) {
        days = Integer.parseInt(args[0]) + 1
    }

    for (i in 0..days - 1) {
        println("-------- day $i --------")
        println("name, sellIn, quality")
        for (item in items) {
            println(item)
        }
        println()
        app.updateQuality()
    }


}

