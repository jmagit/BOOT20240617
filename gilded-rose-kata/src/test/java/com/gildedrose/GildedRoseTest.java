package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GildedRoseTest {

	@Test
	void foo() {
		Item[] items = new Item[] { new Item("foo", 7, 5) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
		assertEquals(6, app.items[0].sellIn);
		assertEquals(4, app.items[0].quality);
	}
//    @Test
//    void foo(int sellin, int quality, int sellinOut, int qualityOut) {
//        Item[] items = new Item[] { new Item("foo", sellin, quality) };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertEquals("foo", app.items[0].name);
//        assertEquals(sellinOut, app.items[0].sellIn);
//        assertEquals(qualityOut, app.items[0].quality);
//    }

	Item[] samples = new Item[] { new Item("+5 Dexterity Vest", 10, 20), //
			new Item("Aged Brie", 2, 0), //
			new Item("Elixir of the Mongoose", 5, 7), //
			new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
			new Item("Sulfuras, Hand of Ragnaros", -1, 80),
			new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
			new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
			new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
			// this conjured item does not work properly yet
			new Item("Conjured Mana Cake", 3, 6) };

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1} –> sellIn: {2} quality: {3}")
	@CsvSource({ 
		"11, 10, 10, 9", 
		"7, 1, 6, 0",
//		"5, -5, 4, 0",
		"0, 3, -1, 1", 
		})
	void normal_Product_Test(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Normal Product";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1} –> sellIn: {2} quality: {3}")
	@CsvSource({ "5, -5, 4, 0", })
	@Disabled
	void normal_Product_KO_Test(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Normal Product";
		Item product = new Item(name, sellIn, 1);
		product.quality = quality;
		GildedRose app = new GildedRose(new Item[] { product });

		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1}")
	@CsvSource({ "5, -5",  "0, 55" })
	@Disabled
	void Item_CTOR_KO_Test(int sellIn, int quality) {
		String name = "Normal Product";
		assertThrows(IllegalArgumentException.class, () -> new Item(name, sellIn, quality));
	}

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1} –> sellIn: {2} quality: {3}")
	@CsvSource({ 
		"2, 0, 1, 1", 
		"-1, 48, -2, 50", 
		"2, 50, 1, 50", 
		"-2, 49, -3, 50", 
		"1, 1, 0, 2", 
		})
	void product_Aged_Brie_Test(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Aged Brie";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1} –> sellIn: {2} quality: {3}")
	@CsvSource({ 
		"1, 0, 1, 0", 
		"0, 1, 0, 1", 
		"-1, 1, -1, 1", 
		})
	void product_Sulfuras_Test(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Sulfuras, Hand of Ragnaros";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1} –> sellIn: {2} quality: {3}")
	@CsvSource({ 
		"11, 0, 10, 1", 
		"7, 1, 6, 3", 
		"7, 49, 6, 50", 
		"5, 3, 4, 6", 
		"0, 3, -1, 0", 
		"-1, 3, -2, 0"
		})
	void product_Passes_Test(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Backstage passes to a TAFKAL80ETC concert";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest(name = "{index} => sellIn: {0} quality: {1} –> sellIn: {2} quality: {3}")
	@CsvSource({ 
		"11, 10, 10, 8", 
		"7, 1, 6, 0", 
		"-5, 10, -6, 6", 
		"0, 3, -1, 0", 
		})
//	@Disabled
	void product_Conjured_Test(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Conjured Mana Cake";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest(name = "{0} => sellIn: {1} quality: {2} –> sellIn: {3} quality: {4}")
	@CsvFileSource(resources = "casos-de-prueba.csv", numLinesToSkip = 1)
//    	@Disabled
	void datasourceTest(String producto, int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = producto.replace("\'", "");
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

}