package com.lxy.test.basic.pattern.Adapter.impl;

import com.lxy.test.basic.pattern.Adapter.IOuterUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class OuterUser implements IOuterUser {

    @Override
    /**
     * 用户的基本信息
     */
    public Map getUserBaseInfo() {
        Map<String, String> baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王....");
        baseInfoMap.put("mobileNumber", "这个员工电话是....");

        return baseInfoMap;
    }

    /**
     * 员工的家庭信息
     *
     * @return
     */
    @Override
    public Map getUserOfficeInfo() {
        HashMap homeInfo = new HashMap();
        homeInfo.put("homeTelNumbner", "员工的家庭电话是....");
        homeInfo.put("homeAddress", "员工的家庭地址是....");
        return homeInfo;
    }

    /**
     * 员工的工作信息，比如职位了等
     * @return
     */
    @Override
    public Map getUserHomeInfo() {
        HashMap officeInfo = new HashMap();
        officeInfo.put("jobPosition", "这个人的职位是BOSS...");
        officeInfo.put("officeTelNumber", "员工的办公电话是....");
        return officeInfo;
    }
}
