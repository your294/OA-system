package com.chen.example.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.example.base.BaseResponse;
import com.chen.example.base.ResultUtils;
import com.chen.example.entity.Book;
import com.chen.example.entity.User;
import com.chen.example.mapper.BookMapper;
import com.chen.example.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 86199
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;
    @Resource
    private BookMapper bookMapper;

    @PostMapping
    public BaseResponse<Boolean> save(@RequestBody Book book){
        boolean save = bookService.save(book);
        return ResultUtils.success(save);
    }

    @GetMapping("{id}")
    public BaseResponse<Book> getById(@PathVariable Integer id){
        Book book = bookService.getById(id);
        return ResultUtils.success(book);
    }

    @PutMapping
    public BaseResponse<Boolean> update(@RequestBody Book book){
        boolean res = bookService.updateById(book);
        return ResultUtils.success(res);
    }

    @DeleteMapping("{id}")
    public BaseResponse<Boolean> deleteById(@PathVariable Integer id){
        boolean res = bookService.removeById(id);
        return ResultUtils.success(res);
    }

    @GetMapping
    public BaseResponse<Page<Book>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Book::getName,search);
        }

//        return ResultUtils.success(userMapper.selectPage(new Page<>(pageNum,pageSize),
//                wrapper));
        return ResultUtils.success(bookService.page(new Page<>(pageNum,pageSize),
                wrapper));
    }
}
