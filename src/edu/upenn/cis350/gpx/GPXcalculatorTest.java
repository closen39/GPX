package edu.upenn.cis350.gpx;


import java.util.ArrayList;

import org.junit.Before;
import static org.junit.Assert.*;

public class GPXcalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	/* Equivalence Class 1: correct inputs
	 * Tests a correct input
	 */
	public void testCorrectInput(){
		
	}
	/* Equivalence Class 2: null inputs
	 * Tests a null input. Return value should be -1
	 */
	public void testNullInput(){
		assertEquals(GPXcalculator.calculateDistanceTraveled(null), -1.0, 0.0);
	}
	
	/* Equivalence Class 3: GPXtrk has no GPXtrkseg's
	 * Return value should be -1
	 * TODO: should second arg be null??
	 */
	public void testNoGPXtrkseg(){
		GPXtrk trk1 = new GPXtrk("trk1", new ArrayList<GPXtrkseg>());
		assertEquals(GPXcalculator.calculateDistanceTraveled(trk1), -1.0, 0.0);
	}
	
	/* Equivalence Class 4: GPXtrk contains a null GPXtrkseg
	 * Return value should be 0
	 */
	public void testNullGPXtrkseg(){
		ArrayList<GPXtrkseg> list1 = new ArrayList<GPXtrkseg>();
		list1.add(null);
		list1.add(new GPXtrkseg(null));
		GPXtrk trk1 = new GPXtrk("trk1", list1);
		assertEquals(GPXcalculator.calculateDistanceTraveled(trk1), 0.0, 0.0);
	}
	
}
