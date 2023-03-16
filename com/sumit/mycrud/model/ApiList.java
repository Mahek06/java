package com.sumit.mycrud.model;

import java.util.HashMap;

public class ApiList {
    private static HashMap<String,String> apiList = new HashMap<>();

    public static void init(){
        apiList.put("all","http://192.168.1.25/phpdemo5/api2.php?action=selectall&table=tbl_employee");
        apiList.put("delete","http://192.168.1.4/phpdemo5/api2.php?action=delete&table=tbl_employee&id=");
        apiList.put("activate","http://192.168.1.4/phpdemo5/api2.php?action=activate&table=tbl_employee&id=");
        apiList.put("edit","http://192.168.1.4/phpdemo5/api2.php?action=edit&table=tbl_employee&id=");
        apiList.put("update","http://192.168.1.4/phpdemo5/api2.php?action=update&table=tbl_employee");
    }

    public static String getApis(String key){
        return apiList.get(key);
    }
}
