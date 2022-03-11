package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import config.service.ProductService;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    ProductService productService = new ProductService();

    @GetMapping("/product")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("showProduct");
        modelAndView.addObject("productShow", ProductService.products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("createProduct");
        modelAndView.addObject("productCreate", new Product());
        return modelAndView;
    }

    @PostMapping("/createButton")
    public String createProduct(@ModelAttribute Product product, @RequestParam MultipartFile upImg) {
        String fileName = upImg.getOriginalFilename();
        try {
            // copy file vừa tải lên và lưu vào thư mục mình muốn
            FileCopyUtils.copy(upImg.getBytes(), new File("D:\\CodeGym\\Module4\\PracticeModule4\\demo-spring-mvc\\src\\main\\webapp\\image/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImg("/i/image/" + fileName);
        productService.add(product);
        return "redirect:/product";
    }

    @GetMapping("/edit")
    public ModelAndView editForm(@RequestParam int index) {
        ModelAndView modelAndView = new ModelAndView("editProduct");
        modelAndView.addObject("productEdit", ProductService.products.get(index));
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam int index, @ModelAttribute Product product, @RequestParam MultipartFile upImg2) {
        String fileName = upImg2.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg2.getBytes(), new File("D:\\CodeGym\\Module4\\PracticeModule4\\demo-spring-mvc\\src\\main\\webapp\\image/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImg("/i/image/" + fileName);
        productService.edit(index, product);
        return "redirect:/product";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int index) {
        Product product = ProductService.products.get(index);
        if (product.getImg().isEmpty()) {
            productService.delete(index);
            return "redirect:/product";
        }
        String fileDelete = product.getImg().replaceAll("/i/image/", "");
        String file1 = "D:\\CodeGym\\Module4\\PracticeModule4\\demo-spring-mvc\\src\\main\\webapp\\image/" + fileDelete;
        File file = new File(file1);
        if (file.exists()) {
            file.delete();
        }
        productService.delete(index);
        return "redirect:/product";
    }
}
