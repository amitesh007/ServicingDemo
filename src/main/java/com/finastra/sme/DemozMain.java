package com.finastra.sme;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finastra.sme.common.data.Group;
import com.finastra.sme.common.data.Item;
import com.finastra.sme.common.data.LiqBusinessObject;
import com.finastra.sme.common.data.ValueList;
import com.finastra.sme.request.data.RequestData;
import com.finastra.sme.response.data.Response;
import com.finastra.sme.response.data.ResponseData;

public class DemozMain {

	final static Map<String, ResponseData> mapReqData = new ConcurrentHashMap<>();

	public static void main(String[] args) throws JsonProcessingException, IOException, URISyntaxException {
		RequestData uiRequestGet = getObjectFromJson(getContent("request_GET_from_ui_to_ms.json"), RequestData.class);
		Map<String, ResponseData> mapResponseData = doGetOrechestration(uiRequestGet,"SchedulePayment");
		ResponseData responseCashflowData = invokeCashflow("cashflow");
		ResponseData responseDealData = invokeCashflow("deal");
		mapResponseData.put("CashFlows", responseCashflowData);
		mapResponseData.put("Deal", responseDealData);
		String newUi=transformForUi(mapResponseData,"SchedulePayment",uiRequestGet);
		System.out.println(newUi);
	}
	
	public static String transformForUi(Map<String, ResponseData> mapResponseData,String templateJsonName,RequestData requestData) throws JsonProcessingException, IOException, URISyntaxException {
		ResponseData template = getTemplate(templateJsonName+"DataTemplate.json");
		Map<String, ResponseData> mapSourceData = mapResponseData;
		ResponseData trgResponseData = template;
		for (Map.Entry<String, ResponseData> entry : mapSourceData.entrySet()) {
			trgResponseData=transform(entry.getKey(),entry.getValue(),trgResponseData,template,requestData);
		}
		return getJsonFromObject(trgResponseData);
	}
	
	public static ResponseData transform(String type,ResponseData srcResponseData,ResponseData trgResponseData,ResponseData templateJson,RequestData requestData) throws JsonProcessingException, IOException, URISyntaxException {
		trgResponseData = updateTrgResponseData(requestData,trgResponseData);
		trgResponseData = updateTrgNonListAttributeData(type,srcResponseData,trgResponseData);
		trgResponseData = updateTrgListAttributeData(type,srcResponseData,trgResponseData);
		return trgResponseData;
		
	}
	
	private static ResponseData updateTrgListAttributeData(String type, ResponseData srcResponseData,
			ResponseData trgResponseData) {
		List<LiqBusinessObject> listLiqBusinessObject=srcResponseData.getResponse().getResult().getLiqBusinessObjects().getLiqBusinessObject();
		for(LiqBusinessObject bo:listLiqBusinessObject) {
			trgResponseData=updateTrgGrpListAttributeData(type,bo,trgResponseData);
		}
		return trgResponseData;
	}

	private static ResponseData updateTrgGrpListAttributeData(String type, LiqBusinessObject bo,
			ResponseData trgResponseData) {
		List<Group> groups = bo.getGroup();
		for (Group group : groups) {
			List<Item> items = group.getItem();
			for (Item item : items) {
				ValueList valueList = item.getValueList();
				if(null != valueList) {
					List<LiqBusinessObject> listLiqBusinessObjectValueList = valueList.getLiqBusinessObject();
					for (LiqBusinessObject boValueList : listLiqBusinessObjectValueList) {
						type = boValueList.getObjectType();
						trgResponseData = updateTrgGroupAttributeData(type, boValueList.getGroup(), trgResponseData);
					}
				}
			}
		}
		return trgResponseData;
	}

	private static ResponseData updateTrgNonListAttributeData(String type, ResponseData srcResponseData,
			ResponseData trgResponseData) {
		List<LiqBusinessObject> listLiqBusinessObject=srcResponseData.getResponse().getResult().getLiqBusinessObjects().getLiqBusinessObject();
		for(LiqBusinessObject bo:listLiqBusinessObject) {
			trgResponseData = updateTrgGroupAttributeData(type,bo.getGroup(),trgResponseData);
		}
		return trgResponseData;
	}
	
	private static ResponseData updateTrgGroupAttributeData(String type, List<Group> listGroup,
			ResponseData trgResponseData) {
		int count =0;
		for( Group group:listGroup) {
			List<Item> items=group.getItem();
			
			for(Item item:items) {
				trgResponseData=updateTrgGroupAttributeData(type+"_"+count,item,trgResponseData);
			}
			count++;
		}
		return trgResponseData;
	}

	private static ResponseData updateTrgGroupAttributeData(String type,Item srcItem, ResponseData trgResponseData) {
		boolean isUpdated=false;
		//int count=0;
		//String boType=type.substring(0, type.lastIndexOf("_"));
		List<LiqBusinessObject> listLiqBusinessObject=trgResponseData.getResponse().getResult().getLiqBusinessObjects().getLiqBusinessObject();
		for(LiqBusinessObject bo:listLiqBusinessObject) {
			isUpdated=updateTrgGroupAttributeData(type,bo.getGroup(),srcItem);
			if(isUpdated) {
				break;
			}
		}
		if(!isUpdated) {
			for(LiqBusinessObject bo:listLiqBusinessObject) {
				List<LiqBusinessObject> listBoChild=bo.getLiqBusinessObject();
				updateBoChildTrgGroupAttributeData(listBoChild,type,srcItem);
//				for(LiqBusinessObject boChild:listBoChild) {
//					isUpdated=updateTrgGroupNonListAttributeData(type,boChild.getGroup(),srcItem);
//					if(isUpdated) {
//						break;
//					}
//				}
			}
		}
		return trgResponseData;
	}
	
	public static boolean updateBoChildTrgGroupAttributeData(List<LiqBusinessObject> listBoChild,String type, Item srcItem) {
		boolean isUpdated=false;
		if(null != listBoChild) {
			for(LiqBusinessObject boChild:listBoChild) {
				isUpdated=updateTrgGroupAttributeData(type,boChild.getGroup(),srcItem);
				if(isUpdated) {
					isUpdated =true;
					break;
				}
				updateBoChildTrgGroupAttributeData(boChild.getLiqBusinessObject(),type,srcItem);
			}
		}
		return isUpdated;
	}

	private static boolean updateTrgGroupAttributeData(String type, List<Group> listGroup, Item srcItem) {
		boolean flag=false;
		for( Group group:listGroup) {
			List<Item> items=group.getItem();
			for(Item trgItem:items) {
				if(trgItem.getType().equals(type)
						&& !trgItem.getValueType().equals("List")
						&& srcItem.getAttribute().equals(trgItem.getSrcAttribute())) {
					trgItem.setValue(srcItem.getValue());
					flag=true;
					break;
				}
			}
		}
		return flag;
	}

	public static ResponseData updateTrgResponseData(RequestData requestData,ResponseData trgResponseData) {
		com.finastra.sme.request.data.Objects objetcs=requestData.getObjects();
		Response trgResponse=trgResponseData.getResponse();
		if(null != trgResponse) {
			trgResponse.setObjects(objetcs);
		}
		trgResponseData.getResponse().setFormId(requestData.getFormId());
		trgResponseData.getResponse().setSessionId(requestData.getSessionId());
		trgResponseData.getResponse().setTemplateId(requestData.getTemplateId());
		trgResponseData.getResponse().setUserId(requestData.getUserId());
		trgResponseData.getResponse().setSuccess("success");
		return trgResponseData;
	}
	
	private static ResponseData getTemplate(String templateName) throws JsonProcessingException, IOException, URISyntaxException {
		String templateJsonString = new DemozMain().getTemplateJson(templateName);
		return getObjectFromJson(templateJsonString,ResponseData.class);
	}

	public String getTemplateJson(String fileName) throws IOException, URISyntaxException {
		return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI())));
	}

	public static <T> T getObjectFromJson(String jsonString, Class<T> type)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		T responseObject = mapper.readValue(jsonString, type);
		return responseObject;
	}

	public static String getContent(String fileName) throws IOException {
		String path = "C:\\professional\\sme\\" + fileName;
		return Files.readString(Paths.get(path), StandardCharsets.UTF_8);
	}

	private static ResponseData invokeCashflow(String type) throws IOException {

		return getObjectFromJson(getContent(type+".json"), ResponseData.class);
	}

	public static Map<String, ResponseData> doGetOrechestration(RequestData requestData,String type) {
		com.finastra.sme.request.data.Objects requestObjects = requestData.getObjects();
		Map<String, ResponseData> mapRequest = null;
		try {
			mapReqData.put(type, invokeQueryAction(requestObjects));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mapRequest = mapReqData;
		return mapRequest;
	}

	private static ResponseData invokeQueryAction(com.finastra.sme.request.data.Objects requestObjects)
			throws IOException {

		return getObjectFromJson(getContent("loan_scheduleItems_2.json"), ResponseData.class);

	}
	
	public static <T> String getJsonFromObject(T type) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(type);
	    return jsonInString;
	}

}
