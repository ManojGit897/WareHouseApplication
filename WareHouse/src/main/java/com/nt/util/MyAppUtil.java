package com.nt.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//JDK 8 (static method in interface)
public interface MyAppUtil {

	public static Map<Integer,String> convertListToMap(List<Object[]> list) {
		return list.stream()
				.collect(
						Collectors.toMap(
								ob->(Integer)ob[0], ob->(String)ob[1])
						);
		/*Map<Integer, String> map = new LinkedHashMap<>();
		for(Object[] ob: list ) {
			map.put((Integer)ob[0], (String)ob[1]);
		}
		return map;*/
	}
}
