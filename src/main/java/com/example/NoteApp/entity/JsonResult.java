package com.example.NoteApp.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Json返回数据格式
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult implements Serializable{

	private static final long serialVersionUID = -3251721328990152019L;

	//返回状态码  -1:错误状态,0:没有数据, 1:成功状态
	private int code;

	//返回提示信息
	private String msg;

	//返回数据
	private Object data;

	/**
	 * 操作成功
	 */
	public static JsonResult success(){
		return JsonResult.success("操作成功！");
	}
	
	/**
	 * 自定义成功提示
	 * @param msg
	 * @return
	 */
	public static JsonResult success(String msg){
		return JsonResult.success(msg, null);
	}
	
	/**
	 * 自定义成功提示
	 * @param msg
	 * @param data
	 * @return
	 */
	public static JsonResult success(String msg, Object data){
		return new JsonResult(1, msg, data);
	}
	
	/**
	 * 操作失败
	 */
	public static JsonResult error(){
		return JsonResult.error("操作失败");
	}
	
	/**
	 * 自定义操作失败提示
	 * @param msg
	 * @return
	 */
	public static JsonResult error(String msg){
		return JsonResult.error(msg, null);
	}
	
	/**
	 * 自定义操作失败提示
	 * @param msg
	 * @param data
	 * @return
	 */
	public static JsonResult error(String msg, Object data){
		return new JsonResult(-1, msg, data);
	}
	public static JsonResult error(int errorno,String msg){
		return new JsonResult(errorno, msg, null);
	}
	
	/**
	 * 暂无数据
	 */
	public static JsonResult empty(){
		return JsonResult.empty("暂无数据！");
	}
	
	/**
	 * 自定义暂无数据提示
	 * @param msg
	 * @return
	 */
	public static JsonResult empty(String msg){
		return JsonResult.empty(msg, null);
	}
	
	/**
	 * 自定义暂无数据提示
	 * @param msg
	 * @param data
	 * @return
	 */
	public static JsonResult empty(String msg, Object data){
		return new JsonResult(0, msg, data);
	}
	
	/**
	 * 自定义
	 */
	public static JsonResult bulid(int code, String msg, Object data){
		return new JsonResult(code, msg, data);
	}
}
