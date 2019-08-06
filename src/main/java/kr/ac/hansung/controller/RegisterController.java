package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.model.Cart;
import kr.ac.hansung.model.ShippingAddress;
import kr.ac.hansung.model.User;
import kr.ac.hansung.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	public String registerUser(Model model) {

		User user = new User();

		ShippingAddress shippingAddress = new ShippingAddress();

		user.setShippingAddress(shippingAddress);

		model.addAttribute("user", user);

		return "registerUser";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserPost(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) { // valid로 notEmpty 등을 검사해서 에러가 있다면 다시 페이지를 띄운다.
			return "registerUser";
		}

		List<User> userList = userService.getAllUsers();

		for (int i = 0; i < userList.size(); i++) {
			
			if (user.getUsername().equals(userList.get(i).getUsername())) { // 원래 있는 사용자를 입력했다면

				model.addAttribute("usernameMsg", "username already exist");

				return "registerUser";
			}
		}
		
		user.setEnabled(true);
		
		// admin 이라는 이름으로 등록했다면 권한을 ROLE_ADMIN을 주고 아니라면 일반 권한인 ROLE_USER를 준다
		if(user.getUsername().equals("admin"))
			user.setAuthority("ROLE_ADMIN");
		else
			user.setAuthority("ROLE_USER");
		
		Cart cart = new Cart();
		user.setCart(cart);
		
		userService.addUser(user);
		
		return "registerUserSuccess";
	}
}
