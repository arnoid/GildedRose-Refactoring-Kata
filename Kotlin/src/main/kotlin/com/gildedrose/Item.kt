package com.gildedrose

/**
 * @param name name
 * @param sellIn value which denotes the number of days we have to sell the item
 * @param quality value which denotes how valuable the item is
 */
open class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}