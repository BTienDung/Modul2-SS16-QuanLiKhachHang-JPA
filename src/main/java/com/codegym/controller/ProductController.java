package com.codegym.controller;
import org.springframework.core.env.Environment;
import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;
    @GetMapping("/create-product")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());

        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/product/create");

        productService.save(product);
        modelAndView.addObject("message", "New customer create succesfully");

        return modelAndView;
    }
    @GetMapping("/listProduct")
    public ModelAndView showListProduct(){
        ModelAndView modelAndView =new ModelAndView("/product/list");
        List<Product> productList = productService.findAll();
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit-product")
    public ModelAndView modelAndView(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("message", "Edit success");
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/delete/delete-product")
    public ModelAndView deleteProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        productService.delete(product);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewProduct(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/product/view");
        Product product = productService.findById(id);
        modelAndView.addObject("products", product);
        return modelAndView;
    }
}
