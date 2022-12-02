package com.csv.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csv.dao.CsvRepository;
import com.csv.model.Items;
import com.csv.service.CsvService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class CsvServiceImpl implements CsvService {

	@Autowired
	private CsvRepository csvRepository;

	@Override
	public ResponseEntity<String> upload(MultipartFile multipartFile) throws IOException {
		List<Items> list = csvRepository.findAll();
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		csvParserSettings.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(csvParserSettings);
		List<Record> files = parser.parseAllRecords(multipartFile.getInputStream());
		files.forEach(file -> {
			Items items = new Items();
			items.setItemId(file.getLong("ItemID"));
			items.setItemName(file.getString("ItemName"));
			items.setSalesDescription(file.getString("SalesDescription"));
			items.setSellingPrice(file.getString("SellingPrice"));
			items.setSalesAccount(file.getString("SalesAccount"));
			items.setIsReturnableItem(file.getBoolean("IsReturnableItem"));
			items.setBrand(file.getString("Brand"));
			items.setManufacturer(file.getString("Manufacturer"));
			items.setPackageWeight(file.getLong("PackageWeight"));
			items.setPackageLength(file.getLong("PackageLength"));
			items.setPackageWidth(file.getLong("PackageWidth"));
			items.setPackageHeight(file.getLong("PackageHeight"));
			items.setDimensionUnit(file.getString("DimensionUnit"));
			items.setWeightUnit(file.getString("WeightUnit"));
			items.setTaxName(file.getString("TaxName"));
			items.setTaxPercentage(file.getLong("TaxPercentage"));
			items.setTaxType(file.getString("TaxType"));
			items.setProductTypes(file.getString("ProductType"));
			items.setSource(file.getLong("Source"));
			items.setReferanceId(file.getLong("ReferenceID"));
			items.setLastSyncTime(file.getString("LastSyncTime"));
			items.setStatus(file.getString("Status"));
			items.setUnit(file.getString("Unit"));
			items.setSku(file.getString("SKU"));
			items.setUpc(file.getString("UPC"));
			items.setEan(file.getString("EAN"));
			items.setIsbn(file.getString("ISBN"));
			items.setPartNumber(file.getLong("PartNumber"));
			items.setPurchasePrice(file.getString("PurchasePrice"));
			items.setPurchaseAccount(file.getString("PurchaseAccount"));
			items.setPurchaseDescription(file.getString("PurchaseDescription"));
			items.setInventoryAccount(file.getString("InventoryAccount"));
			items.setRecoderLevel(file.getString("ReorderLevel"));
			items.setPreferredVendor(file.getString("PreferredVendor"));
			items.setOpeningStock(file.getString("OpeningStock"));
			items.setOpeningStockValue(file.getString("OpeningStockValue"));
			items.setStockOnHand(file.getLong("StockOnHand"));
			items.setIsComboProduct(file.getBoolean("IsComboProduct"));
			items.setItemType(file.getString("ItemType"));

			list.add(items);
			csvRepository.saveAll(list);
		});
		return new ResponseEntity<>("Saved...", HttpStatus.OK);

	}

}
