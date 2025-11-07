package com.briup.smartlabs.common.utils;

import com.briup.smartlabs.bean.SysUser;
import io.jsonwebtoken.*;

import java.util.Date;

//jwt工具类
public class JwtUtils {
	private static final SignatureAlgorithm ALG = SignatureAlgorithm.HS256;
	private static final String TOKEN_TYPE = "jwt";
	private static final long EXPIRATION = 60*60*1000;
	//密钥
	private static final String  SECRET_KEY = "smart_lab";

	//生成token
	public static String generateJwtStr(SysUser user) {
		JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("alg", ALG.getValue());
		builder.setHeaderParam("typ", TOKEN_TYPE);
		builder.claim("username", user.getUsername()); //用户相关的数据
		Date now = new Date();
		builder.setIssuedAt(now);
		builder.setExpiration(new Date(now.getTime()+EXPIRATION));
		builder.signWith(ALG, SECRET_KEY.getBytes());
		return builder.compact();
	}

	//token解析
	public static Claims parseToken(String token) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(
				SECRET_KEY.getBytes());
		Claims claims = parser.parseClaimsJws(token).getBody();
		return claims;
	}

	//获取登录用户名
	public static String getUsername(String token) {
		Claims s = parseToken(token);
		return s.get("username", String.class);
	}
	
	public static void main(String[] args) {
		SysUser user = new SysUser();
		user.setUsername("zhangsan");
		String token = JwtUtils.generateJwtStr(user);
		
		Claims c = parseToken(token);
		System.out.println(c);
	}
}



