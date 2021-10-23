package com.chen.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.example.entity.Book;
import com.chen.example.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
 * @author 86199
 */
@Service("BookService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
