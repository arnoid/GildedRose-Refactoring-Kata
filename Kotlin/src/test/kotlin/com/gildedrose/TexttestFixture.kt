package com.gildedrose

fun main(args: Array<String>) {

    println("OMGHAI!")

    val items = arrayOf(
            ItemsLibrary.PLUS_5_DEXTERITY_VEST.toItem(10, 20), //
            ItemsLibrary.AGED_BRIE.toItem(2, 0), //
            ItemsLibrary.ELIXIR_OF_THE_MONGOOSE.toItem(5, 7), //
            ItemsLibrary.SULFURAS_HAND_OF_RAGNAROS.toItem(0, 80), //
            ItemsLibrary.SULFURAS_HAND_OF_RAGNAROS.toItem(-1, 80),
            ItemsLibrary.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.toItem(15, 20),
            ItemsLibrary.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.toItem(10, 49),
            ItemsLibrary.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.toItem(5, 49),
            // this conjured item does not work properly yet
            ItemsLibrary.CONJURED_MANA_CAKE.toItem(3, 6)
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

