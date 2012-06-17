/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2010-2012 Emmanuel Keller / Jaeksoft
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

package com.jaeksoft.searchlib.scheduler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.xml.sax.SAXException;

import com.jaeksoft.searchlib.config.Config;
import com.jaeksoft.searchlib.util.StringUtils;
import com.jaeksoft.searchlib.util.XmlWriter;

public class TaskProperties {

	private Map<TaskPropertyDef, TaskProperty> map;

	private TaskProperty[] cache;

	public TaskProperties(Config config, TaskAbstract task,
			TaskPropertyDef[] propertyDefs) {
		map = new LinkedHashMap<TaskPropertyDef, TaskProperty>();
		if (propertyDefs == null)
			return;
		cache = new TaskProperty[propertyDefs.length];
		int i = 0;
		for (TaskPropertyDef propertyDef : propertyDefs) {
			TaskProperty taskProperty = new TaskProperty(config, task,
					propertyDef);
			map.put(propertyDef, taskProperty);
			cache[i++] = taskProperty;
		}
	}

	public TaskProperty[] getArray() {
		return cache;
	}

	public String getValue(TaskPropertyDef propertyDef) {
		TaskProperty prop = map.get(propertyDef);
		if (prop == null)
			return null;
		return prop.getValue();
	}

	public void setValue(TaskPropertyDef propertyDef, String value) {
		TaskProperty prop = map.get(propertyDef);
		if (prop == null)
			return;
		if (prop.getType() == TaskPropertyType.password)
			if (Base64.isBase64(value))
				value = StringUtils.base64decode(value);
		prop.setValue(value);
	}

	public void set(TaskProperty taskProperty) {
		setValue(taskProperty.getDef(), taskProperty.getValue());
	}

	public void writeXml(XmlWriter xmlWriter) throws SAXException {
		if (cache != null)
			for (TaskProperty taskProperty : cache)
				taskProperty.writeXml(xmlWriter);
	}

}
