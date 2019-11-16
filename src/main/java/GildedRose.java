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

            if (isAgedBrie(itemName)) {
                updateAgedBrie(currentItem, itemName);
                continue;
            }

            if (isBackstagePasses(itemName)) {
                if (isQualityLessThan50(currentItem)) {
                    currentItem.quality = currentItem.quality + 1;

                    if (isBackstagePasses(itemName)) {
                        if (isSellInLessThan11(currentItem)) {
                            if (isQualityLessThan50(currentItem)) {
                                currentItem.quality = currentItem.quality + 1;
                            }
                        }

                        if (isSellInLessThan6(currentItem)) {
                            if (isQualityLessThan50(currentItem)) {
                                currentItem.quality = currentItem.quality + 1;
                            }
                        }
                    }
                }
                decreaseSellIn(currentItem, itemName);

                if (isSellInLessThanZero(currentItem)) {
                    currentItem.quality = 0;
                }
                continue;
            }

            updateRegularItem(currentItem, itemName);
        }
    }

    private void updateAgedBrie(Item currentItem, String itemName) {
        if (isQualityLessThan50(currentItem)) {
            currentItem.quality = currentItem.quality + 1;
        }
        decreaseSellIn(currentItem, itemName);

        if (isSellInLessThanZero(currentItem)) {
            if (isQualityLessThan50(currentItem)) {
                currentItem.quality = currentItem.quality + 1;
            }
        }
    }

    private boolean isSellInLessThan6(Item currentItem) {
        return currentItem.sellIn < 6;
    }

    private boolean isSellInLessThan11(Item currentItem) {
        return currentItem.sellIn < 11;
    }

    private boolean isSellInLessThanZero(Item currentItem) {
        return currentItem.sellIn < 0;
    }

    private boolean isQualityLessThan50(Item currentItem) {
        return currentItem.quality < 50;
    }

    private void updateRegularItem(Item currentItem, String itemName) {
        if (currentItem.quality > 0) {
            currentItem.quality = currentItem.quality - 1;
        }

        decreaseSellIn(currentItem, itemName);

        if (isSellInLessThanZero(currentItem)) {
            if (currentItem.quality > 0) {
                currentItem.quality = currentItem.quality - 1;
            }
        }
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