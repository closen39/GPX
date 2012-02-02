package edu.upenn.cis350.gpx;


import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class GPXcalculatorTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	/* Equivalence Class 1: correct inputs
	 * Tests a correct input
	 */
	@Test
	public void testCorrectInput(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, 0.0, new Date());
		GPXtrkpt pt2 = new GPXtrkpt(50.0, 50.0, new Date());

		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(pt2);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		double eucDist = Math.sqrt(Math.pow((pt2.lat() - pt1.lat()), 2) + 
							Math.pow((pt2.lon() - pt1.lon()), 2));						
		assertEquals(eucDist, GPXcalculator.calculateDistanceTraveled(trk1), 0.001);
	}
	/* Equivalence Class 2: null inputs
	 * Tests a null input. Return value should be -1
	 */
	@Test
	public void testNullInput(){
		assertEquals(-1.0, GPXcalculator.calculateDistanceTraveled(null), 0.0);
	}
	
	/* Equivalence Class 3: GPXtrk has no GPXtrkseg's
	 * Return value should be -1
	 */
	@Test
	public void testNoGPXtrkseg(){
		GPXtrk trk1 = new GPXtrk("trk1", new ArrayList<GPXtrkseg>());
		assertEquals(-1.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 4: GPXtrk contains a null GPXtrkseg
	 * Return value should be 0 (or distance of other segment, in this case, 0)
	 */
	@Test
	public void testNullGPXtrkseg(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, 0.0, new Date());
		GPXtrkpt pt2 = new GPXtrkpt(0.0, 0.0, new Date());

		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(pt2); //distance b/w these two points should be zero (for this segment)
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(null);
		list2.add(new GPXtrkseg(list1));
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 5: GPXtrkseg contains no GPXtrkpt's
	 * Return value should be 0
	 */
	@Test
	public void testNoGPXtrkpt(){
		GPXtrkseg seg1 = new GPXtrkseg(new ArrayList<GPXtrkpt>());
		ArrayList<GPXtrkseg> list1 = new ArrayList<GPXtrkseg>();
		list1.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list1);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);

	}
	
	/* Equivalence Class 6: GPXtrkseg contains only one GPXtrkpt
	 * Return value should be 0
	 */
	@Test
	public void testOneGPXtrkpt(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, 0.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 7: GPXtrkseg contains a null GPXtrkpt
	 * Return value should be 0
	 */
	@Test
	public void testNullGPXtrkpt(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, 0.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(null);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 8: GPXtrkpt has lat > 90
	 * Return value should be 0
	 */
	@Test
	public void testLatGreater90(){
		GPXtrkpt pt1 = new GPXtrkpt(91.0, 0.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 9: GPXtrkpt has lat < -90
	 * Return value should be 0
	 */
	@Test
	public void testLatLess90(){
		GPXtrkpt pt1 = new GPXtrkpt(-91.0, 0.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 10: GPXtrkpt has long > 180
	 * Return value should be 0
	 */
	@Test
	public void testLongGreater180(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, 181.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 11: GPXtrkpt has long < 180
	 * Return value should be 0
	 */
	@Test
	public void testLongLess180(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, -180.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 12: Boundary Condition1 - Two points with 0 distance
	 * Return value should be 0
	 */
	@Test
	public void testBoundaryCondition1(){
		GPXtrkpt pt1 = new GPXtrkpt(0.0, 0.0, new Date());
		GPXtrkpt pt2 = new GPXtrkpt(0.0, 0.0, new Date());
		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(pt2);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);				
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
		
		// test with points that don't start at 0
		pt1 = new GPXtrkpt(60.0, 60.0, new Date());
		pt2 = new GPXtrkpt(60.0, 60.0, new Date());
		list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(pt2);
		seg1 = new GPXtrkseg(list1);
		list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		trk1 = new GPXtrk("trk1", list2);				
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(trk1), 0.0);
	}
	
	/* Equivalence Class 13: Boundary Condition2 - Lat at exactly +-90, long at exactly +-180
	 * Return value should not be zero 
	 */
	@Test
	public void testBoundaryCondition2(){ 
		GPXtrkpt pt1 = new GPXtrkpt(90.0, 180.0, new Date());
		GPXtrkpt pt2 = new GPXtrkpt(45.0, 45.0, new Date());

		ArrayList<GPXtrkpt> list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(pt2);
		GPXtrkseg seg1 = new GPXtrkseg(list1);
		ArrayList<GPXtrkseg> list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		GPXtrk trk1 = new GPXtrk("trk1", list2);				
		assertTrue("Boundary Condition failed", GPXcalculator.calculateDistanceTraveled(trk1) > 0.0);
		
		pt1 = new GPXtrkpt(-90, -180.0, new Date());
		pt2 = new GPXtrkpt(45.0, 45.0, new Date());
		list1 = new ArrayList<GPXtrkpt>();
		list1.add(pt1);
		list1.add(pt2);
		seg1 = new GPXtrkseg(list1);
		list2 = new ArrayList<GPXtrkseg>();
		list2.add(seg1);
		trk1 = new GPXtrk("trk1", list2);
		assertTrue("Boundary Condition failed", GPXcalculator.calculateDistanceTraveled(trk1) > 0.0);

	}
	
}
