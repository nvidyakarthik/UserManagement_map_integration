package com.epicproportionstour.user.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epicproportionstour.json.CustomGenericResponse;
import com.epicproportionstour.json.CustomUserResponse;
import com.epicproportionstour.json.JsonJGridOptionsBean;
import com.epicproportionstour.json.JsonJGridOptionsResponse;
import com.epicproportionstour.user.controller.RoleEditor;
import com.epicproportionstour.user.dao.UsersDao;
import com.epicproportionstour.user.model.Role;
import com.epicproportionstour.user.model.Users;
import com.epicproportionstour.user.service.LoginService;
import com.epicproportionstour.user.service.UserResponse;
import com.epicproportionstour.user.service.impl.EmailServiceImpl;
import com.epicproportionstour.user.util.*;




@Controller
public class LoginController {
	
	CommonUtils cUtils = new CommonUtils();
	private String lastLogin;
	private Users user;
	private String fullName;
	private String userName=null;
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping("/secured/mypage")
	public String mypage(Model model, Principal principal,
			HttpServletRequest req) {
		userName = principal.getName();
		lastLogin = cUtils.loginDateTime();
		loginService.updateLastLogin(lastLogin, userName);
		user = loginService.findUserByName(userName);
		fullName = user.getFirstname() + " " + user.getLastname();
		model.addAttribute("fullName", fullName);
		req.getSession().setAttribute("userName", userName);
		List<Role> userRoleList = user.getRoles();
		for (Role temp : userRoleList) {
			if (temp.getRoleName().equals("ADMIN")) {
				return "secured/userHome";
			}
		}
		return "secured/appUserHome";
	}
	
	@RequestMapping(value="/secured/users",method=RequestMethod.GET)
	public String AppUsers(){
		System.out.println("inside maingrid calling method");
		//List<Users> users = new ArrayList<Users>();
		//users = loginService.getAllUsers();
		//model.addAttribute("ApplicationUsers", users);
       	return "secured/mainGrid";
	}
/*//	 @Transactional(readOnly = true)
//	    public List<JsonJTableExpenseOptionsBean> listExpenseCategories() throws ExpenseException {      
//	        List<JsonJTableExpenseOptionsBean> lblList = new ArrayList<JsonJTableExpenseOptionsBean>();
//	        try {
//	            List<ExpenseCategory> expCategories = expenseDao.listExpenseCategories();
//	            for (ExpenseCategory expenseCategory : expCategories) {
//	                JsonJTableExpenseOptionsBean expenseOptionsBean = new JsonJTableExpenseOptionsBean(expenseCategory.getExpenseCategoryName(), expenseCategory.getId().toString());
//	                lblList.add(expenseOptionsBean);
//	            }
//	        } catch (Exception e) {
//	            LOG.error("Exception thrown while listing expense categories" + e.getMessage());
//	            throw new ExpenseException("Exception while listing expense categories "+e.getMessage());
//	        }      
//	        return lblList;
//	    }
*/	@RequestMapping(value="/secured/allroles",method=RequestMethod.GET,produces="application/json")
	public  @ResponseBody JsonJGridOptionsResponse  getAllRoles(){
		System.out.println("inside maingrid calling method");
		List<Role> roles ;
		JsonJGridOptionsResponse response;
        List<JsonJGridOptionsBean> rolebeanList=new ArrayList<JsonJGridOptionsBean>();
        try {
        	roles = loginService.getUserRoles();
        	for(Role role:roles){
        		JsonJGridOptionsBean roleOptionsBean=new JsonJGridOptionsBean(role.getRoleName(),String.valueOf(role.getId()));
        		rolebeanList.add(roleOptionsBean);
        	}
            response = new JsonJGridOptionsResponse("OK",rolebeanList);
        } catch (Exception e) {
        	response = new JsonJGridOptionsResponse("ERROR",e.getMessage());
            
        }
        return response;
		
		
		
	}


	 @RequestMapping(value="/secured/users1",method=RequestMethod.GET,produces="application/json")         
	    public @ResponseBody  CustomUserResponse getAll(HttpServletRequest request){
		 //System.out.println(request.getParameter("rows"));
		 //System.out.println(request.getParameter("page"));
		 //System.out.println(request.getParameter("sidx"));
		 //System.out.println(request.getParameter("sord"));
		 
	        int rows = Integer.parseInt(request.getParameter("rows"));
	        int page = Integer.parseInt(request.getParameter("page"));
	        String sidx = request.getParameter("sidx");
	        String sord = request.getParameter("sord");
	        List<Users> list = null;
	        list = loginService.getAllUsers(rows, page, sidx, sord);
	        CustomUserResponse response = new CustomUserResponse();
	        response.setRows(list);
	        int count = list.size();
	        int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
	        response.setTotal(String.valueOf(total));
	        response.setRecords(String.valueOf(count));
	        response.setPage(String.valueOf(page));
	        return response;
	  }
	
	
	@RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
	public String logoutPage() {
		return "logoutPage";
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage() {
		return "loginPage";
	}
	@RequestMapping(value = "/secured/userHome", method = RequestMethod.GET)
	public String userHome() {
		return "secured/appUserHome";
	}
	
	@RequestMapping("/")
	public String homePage() {
		return "loginPage";
	}
	
	
	
	@RequestMapping("/secured/edit")
	public String editUser(){
		
		//List<Users> users = new ArrayList<Users>();
		//users = loginService.getAllUsers();
		//model.addAttribute("ApplicationUsers", users);
       	return "secured/editUser";
	}
	
	
	/*@RequestMapping("/secured/users")
	public String AppUsers(Model model, Principal principal){
		
		List<Users> users = new ArrayList<Users>();
		users = loginService.getAllUsers();
		model.addAttribute("ApplicationUsers", users);
       	return "secured/users";
	}*/
	
	@RequestMapping(value = "/secured/addUser", method = RequestMethod.GET)
	public String addNewUser(Model model, Principal principal) {
		model.addAttribute("newUser", new Users());
		model.addAttribute("roleList",loginService.getUserRoles());
		return "secured/addUser";
	}
	
	/*@initBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	//do whatever
	 "foos" is the name of the property that we want to register a custom editor to
	* you can set property name null and it means you want to register this editor for occurrences of Set class in * command object
	
	binder.registerCustomEditor(Set.class, "roles", new CustomCollectionEditor(Set.class) {
	@Override
	protected Object convertElement(Object element) {
	String roleId = (String) element;
	System.out.println("1111111111111111111111111111111111@@@@@@@@@@@@@@22");
	Role role1 = new Role();
	role1.setId(Integer.parseInt(roleId));
	role1.setRoleName(loginService.findRole(Integer.parseInt(roleId)).getRoleName());
	return role1;
	}
	});
	}
	@InitBinder
    protected void initBinder(HttpServletRequest request, WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "roles", new RoleEditor(this.loginService, List.class));
    }*/
	
	/*@RequestMapping(value = "/secured/addUser", method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("newUser") Users newUser,
			BindingResult result, Model model) {
		String[] items = result.getFieldValue("roles").toString().split(",");
		Role role1 = null;
		int id = 0;
		List<String> container = Arrays.asList(items);
		for (String roleid : container) {
			id = Integer.parseInt(roleid);
			role1 = new Role(id, (loginService.findRole(id)).getRoleName());
			newUser.getRoles().add(role1);
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+newUser.getRoles());
		String securePassword = Long.toHexString(Double.doubleToLongBits(Math
				.random()));
		newUser.setLastlogin(cUtils.loginDateTime());
		newUser.setPassword(MD5Hash.md5(securePassword));
		newUser.setIsactive(true);
		// We set the Sender Email Id and password, but we would put them in
		// loginCredentials.property file.
		// Update your emailId and Password before you use.
		// Comment on 21 Dec, 2013--- NO mail functionality used
		String sSenderEmailId ="enter valid gmailid"; // Valid Gmail account ID
		String sSenderPassword ="enter pwd";// Corresponding Password.
		
		String userName = result.getFieldValue("username").toString();

		EmailServiceImpl objSendPWD = new EmailServiceImpl(result.getFieldValue("emailid").toString(), sSenderEmailId,
				sSenderPassword, securePassword, userName);
		objSendPWD.SendPwdByMail();
		loginService.addUser(newUser);
		model.addAttribute("ModelMessage",
				"User added successfully.Please check you mail for the new Password");
		return "secured/successPage";

	}
	*/
	 /**
     * Add a new user
     */
    @RequestMapping(value = "/secured/addUser", method = RequestMethod.POST)
    public @ResponseBody CustomGenericResponse addUser(
    		@RequestParam("username") String userName,
    		@RequestParam("firstname") String firstName,
    		@RequestParam("lastname") String lastName,
    		@RequestParam("emailid") String emailid,
    		@RequestParam("isactive") boolean status,
    		@RequestParam("roles") String roles,
    		@RequestParam("lastlogin") String lastLogin
    ) {
    	
    	
    	// Construct our new user object. Take note the id is not required.
    	// Assign the values from the parameters
    	Users user = new Users();
    	user.setUsername(userName);
    	user.setFirstname(firstName);
    	user.setLastname(lastName);
    	user.setEmailid(emailid);
    	user.setIsactive(status);
    	Role role1=null;
    	int id = Integer.parseInt(roles);
		role1 = new Role(id, (loginService.findRole(id)).getRoleName());
		user.getRoles().add(role1);
		String securePassword = Long.toHexString(Double.doubleToLongBits(Math
				.random()));
		user.setLastlogin(cUtils.loginDateTime());
		user.setPassword(MD5Hash.md5(securePassword));
		//user.setIsactive(true);
    	// Do custom validation here or in your service
    	
    	// Call service to add
    	Boolean success = loginService.addUser(user);;
    	
    	// Check if successful
    	if ( success == true ) {
    		// Success. Return a custom response
    		CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(true);
			response.setMessage("Action successful!");
	   		return response;
    		
    	} else {
    		// A failure. Return a custom response as well
    		CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess(false);
			response.setMessage("Action failure!");
    		return response;
    	}
    	
	}
    
	
	@RequestMapping(value = "/secured/changePassword", method = RequestMethod.GET)
	public String changePasswordPage(HttpServletRequest req) {
		
		return "secured/userChangePassword";
	}
	
	@RequestMapping(value="/secured/changePassword", method = RequestMethod.POST)
	public String ChangeUserPassword(HttpServletRequest req,Model model) throws Exception {
	
	    String sCurrentPassword=req.getParameter("currentPassword");
        String sNewPassword = req.getParameter("newPassword");
        sNewPassword=MD5Hash.md5(sNewPassword);
        String userName=req.getSession().getAttribute("userName").toString();
        String sNewConfirmPassword = req.getParameter("confirmPassword");
        System.out.println("username =============="+userName);
        System.out.println("currentPassword =============="+sCurrentPassword);
        System.out.println("newPassword =============="+sNewPassword);
        System.out.println("confirmpassword =============="+sNewConfirmPassword);
        loginService.changePassword(userName, sNewPassword);
        model.addAttribute("ModelMessage","Password changed successfully");
        return "secured/successPage";
       
 	}
	
	 /**
     * Delete an existing user
     */
    @RequestMapping(value = "/secured/deleteUser", method = RequestMethod.POST)
    public @ResponseBody CustomGenericResponse delete(@RequestParam("id") String id) {
      
          
     // Construct our user object. We just need the id for deletion.
     // Assign the values from the parameters
     //Users user = new Users();
     //user.setId( Integer.valueOf(id) );
      
     // Do custom validation here or in your service
      
     // Call service to add
     Boolean success = loginService.deleteUser(Integer.valueOf(id));
      
     // Check if successful
     if ( success == true ) {
      // Success. Return a custom response
      CustomGenericResponse response = new CustomGenericResponse();
   response.setSuccess(true);
   response.setMessage("Action successful!");
      return response;
       
     } else {
      // A failure. Return a custom response as well
      CustomGenericResponse response = new CustomGenericResponse();
   response.setSuccess(false);
   response.setMessage("Action failure!");
      return response;
     }
 }
	

}


