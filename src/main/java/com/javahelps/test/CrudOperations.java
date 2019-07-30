package com.javahelps.test;

import static com.jayway.restassured.RestAssured.get;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class CrudOperations {

	@Test
	public void getProductDetails() {
		Response resp = get("http://localhost:9000/products");
		int stausCode = resp.getStatusCode();
		JSONArray jsonResponse = new JSONArray(resp.asString());
		System.out.println("Responce Body ----->" + jsonResponse);
		String Fname = jsonResponse.getJSONObject(0).getString("name");
		String Lname = jsonResponse.getJSONObject(1).getString("name");
		Assert.assertEquals(stausCode, 200);
		Assert.assertEquals(Fname, "Honey");
		Assert.assertEquals(Lname, "Lemnon");

	}

	@Test
	public void createProductDetails() {

		/**
		 * Creating Product Operation performs
		 *
		 */
		RestAssured.baseURI = "http://localhost:9000/products";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "7"); // Cast
		requestParams.put("name", "Mangao");
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response = request.post("");
		System.out.println("POST Responce Body ----->" + response.asString());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

	}

	@Test
	public void updateProductDetails() {

		/**
		 * Updating Product Operation performs
		 *
		 */

		RestAssured.baseURI = "http://localhost:9000/products";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Banana");
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response = request.put("/5");
		System.out.println("Update Responce Body ----->" + response.asString());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void deleteProductDetails() {

		/**
		 * Delete Product Operation performs
		 *
		 */
		RestAssured.baseURI = "http://localhost:9000/products";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Almond");
		request.header("Content-Type", "application/json");
		request.body(requestParams.toString());
		Response response = request.delete("/7");
		System.out.println("DELETE Responce Body ----->" + response.asString());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

}
