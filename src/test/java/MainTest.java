import static org.junit.Assert.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MainTest {

	@Test
	public void testGetTotalBerries() throws MalformedURLException, IOException {
		assertEquals(64, Main.getTotalBerries());
	}

	@Test
	public void testGetBerryName() throws Exception {
		assertEquals("cheri", Main.getBerryName(1));
		assertEquals("nomel", Main.getBerryName(30));
		assertEquals("rowap", Main.getBerryName(64));
	}

	@Test
	public void testGetBerrySize() throws Exception {
		assertEquals(95, Main.getBerrySize(10));
		assertEquals(252, Main.getBerrySize(45));
		assertEquals(155, Main.getBerrySize(60));
	}

	@Test
	public void testGetBerryGrowthTime() throws Exception {
		assertEquals(5, Main.getBerryGrowthTime(15));
		assertEquals(15, Main.getBerryGrowthTime(32));
		assertEquals(18, Main.getBerryGrowthTime(50));
	}

	@Test
	public void testGetMaxSizedBerries() throws Exception {
		List<Berry> testList = new ArrayList<Berry>();
		testList.add(new Berry("aaa", 8, 2));
		testList.add(new Berry("bbb", 5, 3));
		assertEquals("aaa", Main.getMaxSizedBerries(testList).get(0).getName());
		testList.add(new Berry("ccc", 8, 3));
		testList.add(new Berry("ddd", 8, 2));
		assertEquals(3, Main.getMaxSizedBerries(testList).size());
		assertEquals("aaa" + "ccc" + "ddd",
				Main.getMaxSizedBerries(testList).get(0).getName() 
				+ Main.getMaxSizedBerries(testList).get(1).getName()
				+ Main.getMaxSizedBerries(testList).get(2).getName());
	}

	@Test
	public void testGetMinGrowthTimeBerries() throws Exception {
		List<Berry> testList = new ArrayList<Berry>();
		testList.add(new Berry("aaa", 8, 2));
		testList.add(new Berry("bbb", 5, 3));
		assertEquals("aaa", Main.getMinGrowthTimeBerries(testList).get(0).getName());
		testList.add(new Berry("ccc", 8, 3));
		testList.add(new Berry("ddd", 8, 2));
		assertEquals(2, Main.getMinGrowthTimeBerries(testList).size());
		assertEquals("aaa" + "ddd", Main.getMinGrowthTimeBerries(testList).get(0).getName()
				+ Main.getMinGrowthTimeBerries(testList).get(1).getName());
	}

}
