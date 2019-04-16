package com.kitri.common.service;

import java.util.Map;

import com.kitri.util.PageNavigation;

public interface CommonService {

	PageNavigation getPageNavigation(Map<String, String> map);
	
}
