package com.itheima.reggine.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggine.common.R;
import com.itheima.reggine.entity.Employee;
import com.itheima.reggine.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    // 请求的参数如果是以json的格式传递过来的时候，需要添加一个注解@RequestBody
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        /**
         *  1、将页面提交的密码password进行md5加密处理
         *  2、根据页面提交的用户名username查询数据库
         *  3、如果没有查询到则返回登录失败结果
         *  4、密码比对，如果不一致则返回登录失败结果
         *  5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
         *   6、登录成功，将员工id存入Session并返回登录成功结果
         */
        String password = employee.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        //2
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        // 3
        if(emp==null){
            return R.error("登录失败");
        }
        //4
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        //5
        if(emp.getStatus()==0){
            return R.error("账号已经禁用");
        }
        // 6
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 请理Session中保存的当前员工的Id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");

    }
    // 新增员工
    @PostMapping
    public R<String> Save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工,员工信息：{}",employee);
        // 设置初始密码 需要进行MD5的加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
       // employee.setCreateTime(LocalDateTime.now());
       // employee.setUpdateTime(LocalDateTime.now());
        //获得当前登录用户的id
        Long empId=(Long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息的分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    //http://localhost:8080/employee/page?page=1&pageSize=10&name=123
    // 如果不是？这种形式的传参 如果是 1/10/123 这种形式的传参 需要加@Pathvariable
    @GetMapping("/page")
    public R<Page>  page(int page,int pageSize,String name){
        log.info("page={},pagesize={},name={}",page,pageSize,name);
        // 构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        // 构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        // 添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        // 添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        // 执行查询
        employeeService.page(pageInfo,queryWrapper);
        return  R.success(pageInfo);
    }

    /**
     * 根据ID修改员工的信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工的信息
     * @param id
     * @return
     */
    @GetMapping("/{id}") // restful风格的路径变量
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if(employee!=null)
        {
            return  R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
}
