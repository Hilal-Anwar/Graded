

package org.graded;

import java.io.InputStream;
import java.net.URL;

public class MFXDemoResourcesLoader {

	private MFXDemoResourcesLoader() {
	}

	public static URL loadURL(String path) {
		return MFXDemoResourcesLoader.class.getResource(path);
	}

	public static String load(String path) {
		return loadURL(path).toString();
	}

	public static InputStream loadStream(String name) {
		return MFXDemoResourcesLoader.class.getResourceAsStream(name);
	}

}
