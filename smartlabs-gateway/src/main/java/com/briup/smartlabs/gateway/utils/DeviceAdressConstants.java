package com.briup.smartlabs.gateway.utils;

public class DeviceAdressConstants {

	public static final String[] devices = {
			//温度       湿度		   CO2     	  光照 		紫外线		PM2.5
			"2430A41A","2530341B","23309418","2630C41B","2730541A","43309430"
	};

	//具体设备，含编号和类型
	public enum DeviceEnum{
		Device23("23",EnvType.CO2),
		Device24("24",EnvType.TEMP),
		Device25("25",EnvType.Humidity),
		Device26("26",EnvType.Illumination),
		Device27("27",EnvType.UV),
		Device43("43",EnvType.PM25);

		private String adres;
		private EnvType type;

		private DeviceEnum(String adres,EnvType type) {
			this.adres = adres;
			this.type = type;
		}

		//可以根据地址，获取设备对应的数据类型(co2,pm2.5,uv)
		public static EnvType getByAdres(String adres) {
			for(DeviceEnum d: values()) {
				if(adres.equals(d.adres)) {
					return d.type;
				}
			}
			return null;
		}
	}

	//采集设备的类型
	public enum EnvType{
		TEMP("温度",0),
		Humidity("湿度",1),
		CO2("二氧化碳浓度",2),
		Illumination("光照",3),
		UV("紫外线",4),
		PM25("pm2.5",5);

		private String name;
		private int type;

		private EnvType(String name,int type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public int getType() {
			return type;
		}
	}
}
