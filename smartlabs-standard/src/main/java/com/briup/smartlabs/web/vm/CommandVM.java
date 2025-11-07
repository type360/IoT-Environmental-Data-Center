package com.briup.smartlabs.web.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("设备指令参数")
@Setter
@Getter
public class CommandVM {
	@ApiModelProperty(value = "设备地址",required = true)
	private String adres;
	@ApiModelProperty(value = "开关控制类/范围控制类，必填")
	private int sval;
	@ApiModelProperty(value = "双值控制类中的低值")
	private int lVal;
	@ApiModelProperty(value = "双值控制类中的高值")
	private int hVal;
	
	@ApiModelProperty(value = "rgb灯/炫彩led,r")
	private int red;
	@ApiModelProperty(value = "rgb灯/炫彩led,g")
	private int green;	
	@ApiModelProperty(value = "rgb灯/炫彩led,b")
	private int blue;
	
	public String toString() {
		if(adres.charAt(2)=='2') {
			int length = Integer.parseInt(adres.substring(4, 6),16);
			if(length==1) {
				return adres + toHexString2(sval);
			}else if(length==2) {
				return adres + toHexString2(hVal,lVal);
			}else if(length==3) {
				return adres + toHexString2(red,green,blue);
			}
		}
		return adres;
	}

	private String toHexString(int val) {
		String str = Integer.toHexString(val);
		return str.length()==1?"0"+str:str;
	}

	private String toHexString2(int... val) {
		StringBuilder sb = new StringBuilder("");
		for(int v : val) {
			sb.append(toHexString(v));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		CommandVM mv = new CommandVM();
		System.out.println(mv.toHexString2(10,67,55));;
	}
}







