package com.sam.pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class Parser {
	public static void main(String[] args) throws IOException {

		/*
		 * Tx : Given Name Text Box : þÿ : Given Name Text Box Tx : Family Name Text Box
		 * : þÿ : Family Name Text Box Tx : House nr Text Box : þÿ : House nr Text Box
		 * Tx : Address 2 Text Box : þÿ : Address 2 Text Box Tx : Postcode Text Box : þÿ
		 * : Postcode Text Box Ch : Country Combo Box : [þÿ] : Country Combo Box Tx :
		 * Height Formatted Field : 150 : Height Formatted Field Tx : City Text Box : þÿ
		 * : City Text Box Btn : Driving License Check Box : Off : Driving License Check
		 * Box Ch : Favourite Colour List Box : [Red] : Favourite Colour List Box Btn :
		 * Language 1 Check Box : Off : Language 1 Check Box Btn : Language 2 Check Box
		 * : Yes : Language 2 Check Box Btn : Language 3 Check Box : Off : Language 3
		 * Check Box Btn : Language 4 Check Box : Off : Language 4 Check Box Btn :
		 * Language 5 Check Box : Off : Language 5 Check Box Ch : Gender List Box :
		 * [Man] : Gender List Box Tx : Address 1 Text Box : þÿ : Address 1 Text Box
		 */
		LinkedHashMap<String, String> inputFieldNames = new LinkedHashMap<String, String>();

		inputFieldNames.put("Given Name Text Box", "Sambasiva Rao Akula");
		inputFieldNames.put("Family Name Text Box", "Akulas");
		inputFieldNames.put("House nr Text Box", "Do No 1-1A Budampadu");
		inputFieldNames.put("Address 2 Text Box", "Guntur Rural");
		inputFieldNames.put("Postcode Text Box", "522017");
		inputFieldNames.put("Country Combo Box", "India");
		inputFieldNames.put("City Text Box", "Guntur");
		inputFieldNames.put("Driving License Check Box", "Yes");
		inputFieldNames.put("Language 5 Check Box", "Yes");
		inputFieldNames.put("Gender List Box", "Men");

		// inputFieldNames.put("Given Name Text Box");
		PDDocument pdfDoc = PDDocument.load(new File("C:\\Users\\acer\\Desktop\\Pdftest\\OoPdfFormExample.pdf")); // paste
																													// your
																													// pdf-file
																													// location
																													// here

		PDDocumentCatalog docCatalog = pdfDoc.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();

		for (Object fieldObj : acroForm.getFields()) {
			PDField field = (PDField) fieldObj;
			// System.out.println(field.getAlternateFieldName() + " : " +
			// field.getValueAsString()); // print field's name
			System.out.println(field.getFieldType() + " : " + field.getFullyQualifiedName() + " :  "
					+ field.getValueAsString() + " : " + field.getPartialName()); // print
			// field's
			// name

			// field.setValue("1"); // set value of field to 1
			for (String fieldName : inputFieldNames.keySet()) {
				if (field.getFullyQualifiedName().equalsIgnoreCase(fieldName)) {
					field.setValue(inputFieldNames.get(fieldName));
				}
			}

			/*
			 * if (field.getFullyQualifiedName().equalsIgnoreCase("Gender List Box")) {
			 * field.setValue("M"); // field.se }
			 */

		}

		pdfDoc.save("C:\\Users\\acer\\Desktop\\Pdftest\\OoPdfFormExample_out.pdf"); // save changes to another file
		pdfDoc.close();
		System.out.println("Conversion done.");

	}
}