import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Main {

	public static int getTotalBerries() throws MalformedURLException, IOException {
		String rootURL = "https://pokeapi.co/api/v2/berry";
		URL request = new URL(rootURL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);
		JSONObject root = new JSONObject(response);
		int count = (int) root.get("count");
		return count;
	}
	
	public static JSONObject getData(int idNumber) throws MalformedURLException, IOException {
		if (idNumber < 1 || idNumber > getTotalBerries())
			throw new IllegalArgumentException("ID number must be in the range 1 to " + getTotalBerries());
		String rootURL = "https://pokeapi.co/api/v2/berry";
		rootURL += "/" + idNumber + "/";
		URL request = new URL(rootURL);
		InputStream openStream = request.openStream();
		String response = IOUtils.toString(openStream);
		JSONObject root = new JSONObject(response);
		return root;
	}

	public static String getBerryName(int idNumber) throws MalformedURLException, IOException {
		JSONObject root = getData(idNumber);
		String name = (String) root.get("name");
		return name;
	}

	public static int getBerrySize(int idNumber) throws MalformedURLException, IOException {
		JSONObject root = getData(idNumber);
		int size = (int) root.get("size");
		return size;
	}

	public static int getBerryGrowthTime(int idNumber) throws MalformedURLException, IOException {
		JSONObject root = getData(idNumber);
		int growthTime = (int) root.get("growth_time");
		return growthTime;
	}

	public static List<Berry> getMaxSizedBerries(List<Berry> berriesList) {
		Integer maxSize = berriesList
				.stream()
				.map(Berry::getSize)
				.max(Integer::compare)
				.get();
		System.out.println("The size of the largest Berry in the list is: " + maxSize + " millimeters.");
		List<Berry> maxSizedBerries = berriesList
				.stream()
				.filter(x -> x.getSize() == maxSize)
				.collect(Collectors.toList());
		return maxSizedBerries;
	}

	public static List<Berry> getMinGrowthTimeBerries(List<Berry> berriesList) {
		Integer minGrowthTime = berriesList
				.stream()
				.map(Berry::getGrowthTime)
				.min(Integer::compare)
				.get();
		System.out.println("The shortest growth time of the Berries in the list is: " + minGrowthTime + " hours.");
		List<Berry> minGrowthTimeBerries = berriesList
				.stream()
				.filter(x -> x.getGrowthTime() == minGrowthTime)
				.collect(Collectors.toList());
		return minGrowthTimeBerries;
	}

	public static void main(String[] args) throws IOException {

		List<Berry> berries = new ArrayList<Berry>();
		
		for (int i = 1; i <= getTotalBerries(); i++) {
			berries.add(new Berry(getBerryName(i), getBerrySize(i), getBerryGrowthTime(i)));
		}
		
		List<Berry> maxSizedBerries = getMaxSizedBerries(berries);
		List<Berry> maxSizedBerriesWithMinGrowthTime = getMinGrowthTimeBerries(maxSizedBerries);

		String oneOrMore = maxSizedBerriesWithMinGrowthTime.size() == 1 
				? "is one largest Berry"
				: "are " + maxSizedBerriesWithMinGrowthTime.size() + " largest Berries";

		System.out.println();
		System.out.println("There " + oneOrMore + " you can grow in the shortest time: ");
		maxSizedBerriesWithMinGrowthTime.forEach(x -> System.out.println(x.toString()));

	}

}
