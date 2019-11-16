class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item currentItem : items) {
            String itemName = currentItem.name;

            if (isSulfurasHandOfRagnaros(itemName)) {
                continue;
            }

            if (isAgedBrie(itemName)) {
                updateAgedBrieItem(currentItem, itemName);
                continue;
            }

            if (isBackstagePasses(itemName)) {
                updateBackstageItem(currentItem, itemName);
                continue;
            }

            updateRegularItem(currentItem, itemName);
        }
    }

    private void updateBackstageItem(Item currentItem, String itemName) {
        if (isQualityLessThan50(currentItem)) {
            currentItem.quality = currentItem.quality + 1;

            if (isBackstagePasses(itemName)) {
                if (isSellinLessThan11(currentItem)) {
                    if (isQualityLessThan50(currentItem)) {
                        currentItem.quality = currentItem.quality + 1;
                    }
                }

                if (isSellinLessThan6(currentItem)) {
                    if (isQualityLessThan50(currentItem)) {
                        currentItem.quality = currentItem.quality + 1;
                    }
                }
            }
        }
        decreaseSellin(currentItem, itemName);

        if (isSellinLessThanZero(currentItem)) {
            currentItem.quality = 0;
        }
    }

    private void updateAgedBrieItem(Item currentItem, String itemName) {
        if (isQualityLessThan50(currentItem)) {
            currentItem.quality = currentItem.quality + 1;
        }
        decreaseSellin(currentItem, itemName);

        if (isSellinLessThanZero(currentItem)) {
            if (isQualityLessThan50(currentItem)) {
                currentItem.quality = currentItem.quality + 1;
            }
        }
    }

    private void updateRegularItem(Item currentItem, String itemName) {
        if (isQualityGreaterThanZero(currentItem)) {
            currentItem.quality = currentItem.quality - 1;
        }

        decreaseSellin(currentItem, itemName);

        if (isSellinLessThanZero(currentItem)) {
            if (isQualityGreaterThanZero(currentItem)) {
                currentItem.quality = currentItem.quality - 1;
            }
        }
    }

    private boolean isQualityGreaterThanZero(Item currentItem) {
        return currentItem.quality > 0;
    }

    private boolean isSellinLessThan6(Item currentItem) {
        return currentItem.sellIn < 6;
    }

    private boolean isSellinLessThan11(Item currentItem) {
        return currentItem.sellIn < 11;
    }

    private boolean isSellinLessThanZero(Item currentItem) {
        return currentItem.sellIn < 0;
    }

    private boolean isQualityLessThan50(Item currentItem) {
        return currentItem.quality < 50;
    }

    private void decreaseSellin(Item currentItem, String itemName) {
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