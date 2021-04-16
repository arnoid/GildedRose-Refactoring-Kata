package com.gildedrose

class GildedRose(var items: Array<Item>) {

    /**
     * - Once the sell by date has passed, Quality degrades twice as fast
     * - The Quality of an item is never negative
     * - "Aged Brie" actually increases in Quality the older it gets
     * - The Quality of an item is never more than 50
     * - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
     * - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     *     - Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
     *     - Quality drops to 0 after the concert
     * - "Conjured" items degrade in Quality twice as fast as normal items
     */
    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

    companion object {
        const val QUALITY_DEGRADATION_DEFAULT = 1
        const val QUALITY_DEGRADATION_DEFAULT_AFTER_SELL_IN = QUALITY_DEGRADATION_DEFAULT * 2
    }

}

