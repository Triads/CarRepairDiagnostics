package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String year;
	private String make;
	private String model;

	private List<Part> parts;

	public Map<PartType, Integer> getMissingPartsMap() {
		/*
		 * Return map of the part types missing.
		 *
		 * Each car requires one of each of the following types:
		 *      ENGINE, ELECTRICAL, FUEL_FILTER, OIL_FILTER
		 * and four of the type: TIRE
		 *
		 * Example: a car only missing three of the four tires should return a map like this:
		 *
		 *      {
		 *          "TIRE": 3
		 *      }
		 */
		int numTires = Collections.frequency(parts, PartType.TIRE);
		int numEngine = Collections.frequency(parts, PartType.ENGINE);
		int numElectrical = Collections.frequency(parts, PartType.ELECTRICAL);
		int numFuelFilter = Collections.frequency(parts, PartType.FUEL_FILTER);
		int numOilFilter = Collections.frequency(parts, PartType.OIL_FILTER);

		Map<PartType, Integer> missingPartsMap = new HashMap<>();

		if(numTires > 4 || numElectrical > 1 || numEngine > 1 || numFuelFilter > 1 || numOilFilter > 1){
			//thought I'd mention this case should be handled
		}
		int neededTires = 4 - numTires;
		if(neededTires != 0) missingPartsMap.put(PartType.TIRE, neededTires);

		putInMissingPartsMap(PartType.ELECTRICAL, numElectrical, missingPartsMap);
		putInMissingPartsMap(PartType.ENGINE, numEngine, missingPartsMap);
		putInMissingPartsMap(PartType.FUEL_FILTER, numFuelFilter, missingPartsMap);
		putInMissingPartsMap(PartType.OIL_FILTER, numOilFilter, missingPartsMap);

		return missingPartsMap;
	}

	private void putInMissingPartsMap(PartType p, int currentNum, Map<PartType, Integer> mpm){
		if(currentNum == 0) mpm.put(p, 1);
	}

	@Override
	public String toString() {
		return "Car{" +
				       "year='" + year + '\'' +
				       ", make='" + make + '\'' +
				       ", model='" + model + '\'' +
				       ", parts=" + parts +
				       '}';
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters *///region
	/* --------------------------------------------------------------------------------------------------------------- */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters End *///endregion
	/* --------------------------------------------------------------------------------------------------------------- */

}
