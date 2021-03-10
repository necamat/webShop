package com.mycompany.assigmentweb.controller;

import com.mycompany.assigmentweb.model.OrderPr;
import com.mycompany.assigmentweb.model.State;
import com.mycompany.assigmentweb.model.User;
import com.mycompany.assigmentweb.model.UserProfile;
import com.mycompany.assigmentweb.model.UserProfileType;
import com.mycompany.assigmentweb.util.TargetUrl;
import com.mycompany.assigmentweb.service.UserProfileService;
import com.mycompany.assigmentweb.service.UserService;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("roles")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService profileService;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    TargetUrl targetUrl;

    @Autowired
    MessageSource messageSource;

    /*
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/listusers"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();  
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getCurrentUser());

        return "usersList";
    }

    /*
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getCurrentUser());
        return "registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveRegistration(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }
        //uniqueness check userName
        if (!userService.isUserNameUnique(user.getId(), user.getUserName())) {
            FieldError userNameError = new FieldError("user", "userName", messageSource.getMessage("non.unique.userName", new String[]{user.getUserName()}, Locale.getDefault()));
            result.addError(userNameError);
            return "registration";
        }
        userService.saveUser(user);

        model.addAttribute("success", "User <strong>" + user.getFirstName() + "</strong> has been registered successfully.");
        model.addAttribute("loggedinuser", getCurrentUser());

        return "userMessages";
    }

    /*
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{username}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {

        User user = userService.findByUserName(username);
        
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getCurrentUser());

        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{username}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model,@PathVariable String username) {

        if (result.hasErrors()) {
            return "registration";
        }
        //uniqueness check userName
        if (!userService.isUserNameUnique(user.getId(), user.getUserName())) {
            FieldError userNameError = new FieldError("user", "userName", messageSource.getMessage("non.unique.userName", new String[]{user.getUserName()}, Locale.getDefault()));
            result.addError(userNameError);
            return "registration";
        }
        
        userService.updateUser(user);

        model.addAttribute("success", "User <strong>" + user.getFirstName() + " " + user.getLastName() + "</strong> updated successfully.");
        model.addAttribute("loggedinuser", getCurrentUser());

       
        return "userMessages";
    }

    /**
     * This method will be change  state users. Activates the user or places a ban on him.
     * Does not allow changing administrator status.
     */
    @RequestMapping(value = {"/change-state-{username}"}, method = RequestMethod.GET)
    public String changeState (ModelMap model, @PathVariable String username){
        
        User user = userService.findByUserName(username);
        Set<UserProfile> profiles = user.getUserProfiles();
        UserProfile pr = new UserProfile();
        for (UserProfile profile : profiles) {
            pr = profile;
        }
        if (pr.getType().equals(UserProfileType.ADMIN.getUserProfileType())) {
            
            model.addAttribute("failure", "You cannot change the administrator status.");
            return "userMessages";
            
        }else{
        if (user.getState().equals(State.ACTIVE.getState())) {
            user.setState(State.INACTIVE.getState());
            userService.updateUser(user);
            return "redirect:/listusers";
        }else{
        
            user.setState(State.ACTIVE.getState());
            userService.updateUser(user);
            return "redirect:/listusers";
        }}
        
        
    }
    /*
     * This method will delete an user by it's User Name value.
     * If the user information is in the purchase order, returns the message in which the purchase orders are.
     */
    @RequestMapping(value = {"/delete-user-{username}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username, ModelMap model) {

        List<OrderPr> orderPrs = userService.findByUserName(username).getOrderPrs();

        if (orderPrs.isEmpty()) {

            userService.deleteByUserName(username);
            return "redirect:/listusers";
        } else {

            StringBuilder strb = new StringBuilder();
            strb.append("You cannot delete a user because user information is in the purchase order: ");
            for (OrderPr orderPr : orderPrs) {
                strb.append("<strong>");
                strb.append(orderPr.getId());
                strb.append("</strong> ");
            }
            strb.append(".");
            model.addAttribute("failure", strb);
            return "userMessages";
        }

    }

    /*
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfile() {
        return profileService.findAll();
    }

    /*
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = {"accessDenied"}, method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getCurrentUser());
        return "accessDenied";
    }

    /*
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        if (isAnonymous()) {
            return "login";
        } else {

            return "redirect:" + getRolePages();
        }

    }

    /*
     * This method handles logout requests. Toggle the handlers if you are
     * RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /*
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getCurrentUser() {
        String userName = null;
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser instanceof UserDetails) {
            userName = ((UserDetails) currentUser).getUsername();
        } else {
            userName = currentUser.toString();
        }
        return userName;
    }

    /*
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isAnonymous() {
        final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return resolver.isAnonymous(authentication);
    }

    /*
     * This method returns home page logged-in user, depending on his role..
     */
    private String getRolePages() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return targetUrl.determineTargetUrl(authentication);
    }

}
