package org.warehouse.managementservice.stockincrossModule;

import net.sf.json.JSONArray;
import org.warehouse.managementservice.stockinhttpRequest.*;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class CrossModuleClient {

    public String getSpuCodeBySpuName(String url, String spuName){ // spuName 和 spuCode 一一对应
        // 根据spuName获取spuCode
        String params = "{\n" + "\"spuName\":" + "\"" + spuName + "\"" + "\n" + "}";
        String str = HttpRequestTest.sendPostWithJson(url, params);
        JSONArray result = JSONArray.fromObject(str);
        JSONArray resultJsonArray = result.getJSONArray(0);
        String spuCode = resultJsonArray.getJSONObject(0).get("spuCode").toString();
        return spuCode;
    }

    public List <Object> getMaterialCodeBySpuCode(String url, String spuCode){ // 一个SpuCode可能对应多种MaterialCode 返回一个List
        // 根据spuCode获取materialCode
        List <Object> materialCode = new ArrayList<>();
        String params = "{\n" + "\t\"spuCode\":" + "\"" + spuCode + "\""+ ",\n" + "\t\"typeArr\":[2]\n" + "}";
        String str = HttpRequestTest.sendPostWithJson(url, params);
        JSONArray result = JSONArray.fromObject(str);
        JSONArray resultJsonArray = result.getJSONArray(0);
        for(int i = 0; i < resultJsonArray.size(); i++){
            JSONObject jsonObject = resultJsonArray.getJSONObject(i);
            materialCode.add(jsonObject.get("materialCode"));
        }
        return materialCode;
    }

    public String getSpuCodeByMaterialCode(String url, String materialCode){ // 一个materialCode 只有对应的一种spuCode
        // 根据materialCode获取spuCode
        String params = "{\n" + "\"materialCode\":" + "\"" + materialCode + "\"" + "\n" + "}";
        String str = HttpRequestTest.sendPostWithJson(url, params);
        JSONArray result = JSONArray.fromObject(str);
        JSONArray resultJsonArray = result.getJSONArray(0);
        String spuCode = resultJsonArray.getJSONObject(0).get("spuCode").toString();
        return spuCode;
    }

    public String getMaterialNameByMaterialCode(String url, String materialCode){ /////////还有问题
        // 根据materialCode获取materialName
        String params = "{\n" + "\"materialCode\":" + "\"" + materialCode + "\"" + "\n" + "}";
        String str = HttpRequestTest.sendPostWithJson(url, params);
        JSONArray result = JSONArray.fromObject(str);
        JSONArray resultJsonArray = result.getJSONArray(0);
        System.out.println(resultJsonArray);
        String materialName = resultJsonArray.getJSONObject(0).get("materialName").toString();
        return materialName;
    }

    public int getMaterialCatIdBySpucode(String url, String spuCode){
        // 根据spuCode获取materialCatId
        String params = "{\n" + "\"spuCode\":" + "\"" + spuCode + "\"" + "\n" + "}";
        String str = HttpRequestTest.sendPostWithJson(url, params);
        JSONArray result = JSONArray.fromObject(str);
        JSONArray resultJsonArray = result.getJSONArray(0);
        int materialCatId = resultJsonArray.getJSONObject(0).getInt("materialCatId");
        return materialCatId;
    }

    public String getSpecificationByMaterialCatId(String url, int materialCatId){
        // 根据materialCatId获取规格属性
        String specificationStr = "";
        String params = "{\n" + "\t\"catId\":" + materialCatId + ",\n" + "\t\"propertyType\":4\n" + "}";
        String str = HttpRequestTest.sendPostWithJson(url, params);
        JSONArray resultArray = JSONArray.fromObject(str);
        for (int i =0; i < resultArray.size(); i++){
            String name = resultArray.getJSONObject(i).getString("name");
            specificationStr = specificationStr + name;
            if (i != resultArray.size()-1){
                specificationStr = specificationStr + " ";
            }
        }

        return specificationStr;
    }
}
