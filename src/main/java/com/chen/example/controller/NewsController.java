package com.chen.example.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.example.base.BaseResponse;
import com.chen.example.base.ResultUtils;
import com.chen.example.entity.Book;
import com.chen.example.entity.News;
import com.chen.example.mapper.NewsMapper;
import com.chen.example.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 86199
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private NewsMapper newsMapper;

    @GetMapping("/{id}")
    public BaseResponse<News> getById(@PathVariable Integer id){
        News news = newsService.getById(id);
        return ResultUtils.success(news);
    }

    @PostMapping
    public BaseResponse<Boolean> save(@RequestBody News news){
        news.setTime(new Date());
        boolean save = newsService.save(news);
        return ResultUtils.success(save);
    }

    @PutMapping
    public BaseResponse<Boolean> update(@RequestBody News news){
        boolean update = newsService.updateById(news);
        return ResultUtils.success(update);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Boolean> delete(@PathVariable Integer id){
        boolean b = newsService.removeById(id);
        return ResultUtils.success(b);
    }

    @GetMapping
    public BaseResponse<Page<News>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(News::getTitle,search);
        }

//        return ResultUtils.success(userMapper.selectPage(new Page<>(pageNum,pageSize),
//                wrapper));
        return ResultUtils.success(newsService.page(new Page<>(pageNum,pageSize),
                wrapper));
    }
}
