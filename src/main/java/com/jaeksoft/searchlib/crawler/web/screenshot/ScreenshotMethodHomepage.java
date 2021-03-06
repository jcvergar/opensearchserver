/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2011 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of OpenSearchServer.
 *
 * OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.jaeksoft.searchlib.crawler.web.screenshot;

import java.net.URL;

public class ScreenshotMethodHomepage extends ScreenshotMethod {

	public ScreenshotMethodHomepage() {
		super("Homepage");
	}

	@Override
	public boolean doScreenshot(URL url) {
		if (url == null)
			return false;
		String path = url.getPath();
		if (path == null)
			return true;
		if (path.length() == 0)
			return true;
		if ("/".equals(path))
			return true;
		return false;
	}
}
