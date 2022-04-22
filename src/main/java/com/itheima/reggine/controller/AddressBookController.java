package com.itheima.reggine.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itheima.reggine.common.BaseContext;
import com.itheima.reggine.common.R;
import com.itheima.reggine.entity.AddressBook;
import com.itheima.reggine.service.AddressBookService;
import com.sun.corba.se.impl.protocol.AddressingDispositionException;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Runqiang Jiang
 * @create 2022/4/22 13:25
 */
@RequestMapping("/addressBook")
@RestController
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     * @param addressBook
     * @return
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        // 根据用户登录，获取用户的id
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook：{}",addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        log.info("addreddBook:{}",addressBook);
        LambdaUpdateWrapper<AddressBook> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        wrapper.set(AddressBook::getIsDefault,0);
        // SQl:update address_book set is_default=0 where user_id=?
        addressBookService.update(wrapper);
        addressBook.setIsDefault(1);
        //Sql: update address_book set is_defaault =1 where id=?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * 根据id 查询地址
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public R get(@PathVariable Long id){
        AddressBook addressBook=addressBookService.getById(id);
        if(addressBook!=null){
            return R.success(addressBook);
        }else {
            return R.error("没有找到该对象");
        }

    }

    /**
     * 查询默认地址
     * @return
     */
    @GetMapping("default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);
        // SQL:select * from address_book where user_id=? and is_default=1
        AddressBook addressBook=addressBookService.getOne(queryWrapper);
        if(addressBook==null){
            return R.error("没有找到该对象");
        }else{
            return R.success(addressBook);
        }
    }
    @GetMapping("/list")
    public R<List<AddressBook>> getAll(){
        List<AddressBook> list = addressBookService.list();
        return R.success(list);
    }


}
