package com.zzy.service;


import java.util.List;
import java.util.Map;

public interface ClazzDataService {

    List<Map<String, Object>> clazzCount();

    List<Map<String, Object>> degreeCount();
}
