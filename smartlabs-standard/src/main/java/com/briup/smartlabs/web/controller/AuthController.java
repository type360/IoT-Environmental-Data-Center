package com.briup.smartlabs.web.controller;

import com.briup.smartlabs.bean.SysUser;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.service.AuthService;
import com.briup.smartlabs.web.vm.UserToken;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api(tags = "认证服务")
@RestController
public class AuthController {
	@Autowired
	AuthService authService;

	//验证码示例对象
	@Autowired
	Producer producer;
	
	@ApiOperation(value = "生成验证码")
	@ApiImplicitParam(name = "formId",value = "表单唯一标识符，用来进行验证码校验",required = true)
	@GetMapping("/kaptcha")
	public void createKaptcha(HttpServletResponse respose,String formId) {
		String code = producer.createText();
		System.out.println("生成的验证码为： "+code);
		authService.saveOrUpdateKaptcha(formId, code);
		BufferedImage image = producer.createImage(code);
		respose.setContentType("image/jpeg");
		try {
			ImageIO.write(image, "jpg", respose.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value = "登录认证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username",value = "用户名",required = true),
		@ApiImplicitParam(name = "password",value = "密码",required = true),
		@ApiImplicitParam(name = "formId",value = "表单id",required = true),
		@ApiImplicitParam(name = "code",value = "验证码",required = true)
	})
	@PostMapping("/login")
	public Response<UserToken> login(
			String username,String password,
			String formId,String code){

		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("formId:"+formId);
		System.out.println("code:"+code);

		authService.verfiyKaptcha(formId, code);
		UserToken token = authService.login(username,password);
		return Response.ok(token);
	}
	
	@ApiOperation(value = "获取当前用户信息")
	@GetMapping("/auth/me")
	public Response<SysUser> showMe(){
		SysUser user = AuthenticationHolder.getCurrentUser();
		if(user!=null) {
			user.setPassword("");
		}
		return Response.ok(user);
	}
	
	@ApiOperation(value = "获取当前用户的权限信息")
	@GetMapping("/auth/perms")
	public Response<String> getPermissonsOfCurrentUser(){
		return Response.ok(authService.getPermissions());
	}
}





