import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
	This requires an update to our system:

	- "Conjured" items degrade in Quality twice as fast as normal items
 */
public class GildedRoseTest {

    @Test
    public void at_the_end_of_each_day_our_system_lowers_both_values_for_every_item() {
        Item[] items = new Item[]{new Item("foo", 10, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void once_the_sell_by_date_has_passed_quality_degrades_twice_as_fast() {
        Item[] items = new Item[]{new Item("foo", 0, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(8, app.items[0].quality);
    }

    @Test
    public void the_quality_of_an_item_is_never_negative() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void the_quality_of_an_item_is_never_more_than_50() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void sulfuras_being_a_legendary_item_never_has_to_be_sold() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    public void sulfuras_being_a_legendary_item_never_decreases_in_quality() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(10, app.items[0].quality);
    }

    @Test
    public void backstage_passes_increases_in_quality_by_three_when_there_are_5_days_or_less() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 3, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(13, app.items[0].quality);
        assertEquals(13, app.items[1].quality);
    }

    @Test
    public void backstage_passes_increases_in_quality_by_two_when_there_are_10_days_or_less() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(12, app.items[0].quality);
        assertEquals(12, app.items[1].quality);
    }

    @Test
    public void backstage_passes_quality_drops_to_0_after_the_concert() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void aged_brie_actually_increases_by_two_in_quality_when_sell_in_is_less_than_0() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void conjured_item_quality_decreases_twice_as_fast_as_regular_items() {

        Item[] items = new Item[]{new Item("Conjured sword", 5, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }
}
