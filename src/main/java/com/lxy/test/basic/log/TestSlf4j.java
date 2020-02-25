package com.lxy.test.basic.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lxy on 2018/12/29.
 */
public class TestSlf4j {

 @Test
 public void testSlf4j() {
     Logger logger = LoggerFactory.getLogger(Object.class);
     logger.error("123");
  }
}
