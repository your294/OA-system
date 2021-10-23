package com.chen.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.example.entity.News;
import com.chen.example.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
 * @author 86199
 */
@Service("NewsService")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
}
