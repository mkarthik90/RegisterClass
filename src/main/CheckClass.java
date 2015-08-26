package main;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckClass {

	/**
	 * Example program to list links from a URL.
	 */
	public static void main(String[] args) throws IOException {

		// Code to get the List of Cookie values

		URLConnection connection = new URL("https://webapp4.asu.edu/catalog")
				.openConnection();
		List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

		System.out.println(cookies);
		
		
		String url = "https://webapp4.asu.edu/catalog/classlist?s=CSE&l=grad&t=2157&e=all&days=MTWThF&hon=F";
		// String baseURI = "https://webapp4.asu.edu/catalog/Home.ext";
		print("Fetching %s...", url);

		Document doc = Jsoup
				.connect(url)
				.followRedirects(false)
				.cookie("JSESSIONID",
						"javaprod1~18553851980E20ED450C5B03E6C03838.catalog1")
				/*.cookie("asu.edu",
						"d5b5e33caa2e2d6bce4d5fadbb9f70c621438989326")
				
				.cookie("_op_aixPageId",
						"a2_5154908d-5e3f-47fc-880f-6294e60207ea")
				.cookie("__utma",
						"137925942.275354288.1438989329.1438989329.1438989329.1")
				.cookie("__utmt", "1")

				.cookie("__utmb", "137925942.2.10.1438989329")
				.cookie("__utmc", "137925942")
				.cookie("__utmz",
						"137925942.1438989329.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")*/
				.cookie("onlineCampusSelection", "C").timeout(100000).

				get();
		// javaprod24~1D61889943154E4DF5936C95E8F9EB64.catalog24
		Elements table = doc.getElementsByTag("table");

		Element catalogTable = table.get(8);
/*		System.out.println(catalogTable.toString());
		System.out.println("***********");
*/
		Iterator evenRows = catalogTable.getElementsByAttributeValueMatching(
				"class", "grpOdd").iterator();

		Iterator oddRows = catalogTable.getElementsByAttributeValueContaining(
				"class", "grpEven").iterator();

		int count = 0;

		while (evenRows.hasNext()) {
			Element evenRow = (Element) evenRows.next();
			Elements evenRowToTrim = evenRow.getElementsByAttributeValue(
					"class", "availableSeatsColumnValue");
			System.out
					.println(evenRow.getElementsByAttributeValue("class",
							"subjectNumberColumnValue").text()
							+ "   "
							+ evenRow.getElementsByAttributeValue("class",
									"titleColumnValue").text()
							+ "    "
							+ "    "
							+ evenRow.getElementsByAttributeValue("class",
									"locationBuildingColumnValue").text()
							+ "    "
							+ evenRowToTrim
									.first()
									.getElementsByAttributeValue("style",
											"text-align:right;padding:0px;width:22px; border:none")
									.text());
			count++;
		}

		while (oddRows.hasNext()) {
			Element oddRow = (Element) oddRows.next();
			Elements evenRowToTrim = oddRow.getElementsByAttributeValue(
					"class", "availableSeatsColumnValue");
			System.out
					.println(oddRow.getElementsByAttributeValue("class",
							"subjectNumberColumnValue").text()
							+ "  "
							+ oddRow.getElementsByAttributeValue("class",
									"titleColumnValue").text()

							+ "  "
							+ oddRow.getElementsByAttributeValue("class",
									"locationBuildingColumnValue").text()
							+ "    "
							+ evenRowToTrim
									.first()
									.getElementsByAttributeValue("style",
											"text-align:right;padding:0px;width:22px; border:none")
									.text());
			count++;
		}

		System.out.println("Number of Classes" + count);
		print("\nTable Catalog List: (%d)", table.size());
		
		

	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}
}
