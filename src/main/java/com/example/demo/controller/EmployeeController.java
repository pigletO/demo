package com.example.demo.controller;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 从dashboard页面跳转list页面
     * @return
     */
    //@RequestMapping(value = "/list",method =RequestMethod.GET)
    @GetMapping(value = "/list")
    public String turnToList(Map<String,Object> map){

        //Collection<Employee> emps = employeeDao.getAll();
        ArrayList<Employee> emps = new ArrayList<>(employeeDao.getAll());
        map.put("emps", emps);
        return "emp/list";
    }

    /**
     * 员工列表页面跳转到员工添加页面（查询）
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(HttpServletRequest request, HttpServletResponse response, Map map){
        /*UUID token = UUID.randomUUID();
        // 将token存储到session中
        request.getSession().setAttribute("token",token.toString());
        request.setAttribute("token",token.toString());*/
        List<Department> departments = new ArrayList<Department>(departmentDao.getDepartments());
        map.put("departments", departments);
        //map.put("token", token.toString());
        /*System.err.println("session : "+ request.getSession().getAttribute("token"));
        System.err.println("request : "+ request.getAttribute("token"));*/
        return "/emp/add";
    }

    /**
     * 从员工添加页面到员工列表页面，并添加刚输入的数据（增加）
     * @param employee
     * @return
     * @throws ParseException
     * SpringMVC支持前台数据与实体类进行映射，前台数据的name属性对应实体类的字段名即可完成映射
     */
    @PostMapping("/emp")
    public String addEmployee(Employee employee) throws ParseException {
        // 将Collection对象用new ArrayList的方式强转成List，用括号强转失败！
        List<Employee> employeeList = new ArrayList<Employee>(employeeDao.getAll());
        Employee emps = new Employee(null,employee.getLastName(),employee.getEmail(),employee.getGender(),employee.getDepartment(),employee.getBirth());
        // 将Employee对象持久化
        employeeDao.save(emps);
        // redirect: 页面重定向  forward: 页面转发
        return "redirect:/list";
    }

    /**
     * 从员工列表页面跳转到员工修改页面，并回显被选中行的数据
     * @param employeeId
     * @param model
     * @return
     */
    @GetMapping("/emp/modify/{id}")
    public String toModifiedEmployee(@PathVariable("id") int employeeId, Model model){

        // 查询全部
        Employee emp = employeeDao.get(employeeId);
        List<Department> departments = new ArrayList<>(departmentDao.getDepartments());
        // 将emp/add.html中的添加按钮更名为修改
        model.addAttribute("emp", emp);
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    /****
     * 修改员工信息，并刷新员工列表页面
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String modifiedEmployee(Employee employee){
        employeeDao.save(employee);

        return "redirect:/list";
    }


    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id")int id){

        employeeDao.delete(id);
        return "redirect:/list";
    }
}
