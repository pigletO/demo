package com.example.demo.controller;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeDao employeeDao;
    /**
     * 从dashboard页面跳转list页面
     * @return
     */
    //@RequestMapping(value = "/list",method = RequestMethod.GET)
    @GetMapping(value = "/list")
    public String turnToList(Map<String,Object> map){

        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps", emps);
        return "emp/list";
    }

    /**
     * 员工列表页面跳转到员工添加页面
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(){

        return "/emp/add";
    }

    /**
     * 从员工添加页面到员工列表页面，并添加刚输入的数据
     * @param employee
     * @param email
     * @param gender
     * @param departmentName
     * @param birthDay
     * @param map
     * @return
     * @throws ParseException
     */
    @PostMapping("/emp")
    public String addEmployee(@RequestParam("employeeName") String employee, String email,String gender,String departmentName,String birthDay,Map map) throws ParseException {
        // 将Collection对象用new ArrayList的方式强转成List，用括号强转失败！
        List<Employee> employeeList = new ArrayList<Employee>(employeeDao.getAll());
        Department department = new Department(999999,departmentName);
        Employee emps = new Employee(null,employee,email,gender=="女"?0:1,department,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(birthDay));
        // 将Employee对象持久化
        employeeDao.save(emps);
        // 将新录入的对象放到list中，做页面显示
        employeeList.add(emps);
        map.put("emps", employeeList);
        // 跳转classpath:/templates/emp/list.html
        return "/emp/list";
    }

}
