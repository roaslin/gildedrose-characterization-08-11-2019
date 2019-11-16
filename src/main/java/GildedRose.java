class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item currentItem = items[i];
            String itemName = currentItem.name;

            if (isSulfurasHandOfRagnaros(itemName)) {
                continue;
            }

            if (isAgedBrie(itemName)
                    || isBackstagePasses(itemName)) {
                if (currentItem.quality < 50) {
                    currentItem.quality = currentItem.quality + 1;

                    if (isBackstagePasses(itemName)) {
                        if (currentItem.sellIn < 11) {
                            if (currentItem.quality < 50) {
                                currentItem.quality = currentItem.quality + 1;
                            }
                        }

                        if (currentItem.sellIn < 6) {
                            if (currentItem.quality < 50) {
                                currentItem.quality = currentItem.quality + 1;
                            }
                        }
                    }
                }
            } else {
                updateRegularItem(currentItem, itemName);
                continue;
            }

            decreaseSellIn(currentItem, itemName);

            if (currentItem.sellIn < 0) {
                if (isAgedBrie(itemName)) {
                    if (currentItem.quality < 50) {
                        currentItem.quality = currentItem.quality + 1;
                    }
                } else {
                    currentItem.quality = currentItem.quality - currentItem.quality;
                }
            }
        }
    }

    private void updateRegularItem(Item currentItem, String itemName) {
        if (currentItem.quality > 0) {
            currentItem.quality = currentItem.quality - 1;
        }
        decreaseSellIn(currentItem, itemName);

        if (currentItem.sellIn < 0) {
            if (currentItem.quality > 0) {
                currentItem.quality = currentItem.quality - 1;
            }
        }
        return;
    }

    private void decreaseSellIn(Item currentItem, String itemName) {
        if (!isSulfurasHandOfRagnaros(itemName)) {
            currentItem.sellIn = currentItem.sellIn - 1;
        }
    }

    private boolean isSulfurasHandOfRagnaros(String itemName) {
        return "Sulfuras, Hand of Ragnaros".equals(itemName);
    }

    private boolean isBackstagePasses(String itemName) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(itemName);
    }

    private boolean isAgedBrie(String itemName) {
        return "Aged Brie".equals(itemName);
    }
}