package com.tangbaobao.spike.controller;

import com.tangbaobao.spike.controller.resp.ResultResp;
import com.tangbaobao.spike.service.ProductService;
import com.tangbaobao.spike.vo.ProductVO;
import com.tangbaobao.spike.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2018/10/10 10:22 PM
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final RedisTemplate<String, String> redisTemplate;

    private final ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    public ProductController(ProductService productService, RedisTemplate<String, String> redisTemplate, ThymeleafViewResolver thymeleafViewResolver) {
        this.productService = productService;
        this.redisTemplate = redisTemplate;
        this.thymeleafViewResolver = thymeleafViewResolver;
    }


    /**
     * 通过页面缓存的方式
     *
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/listCache", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getList(Model model, HttpServletRequest request, HttpServletResponse response) {

        //取缓存
        String productList = redisTemplate.opsForValue().get("ProductList");
        if (productList != null) {
            return productList;
        }
        List<ProductVO> productVOList = productService.findList();
        model.addAttribute("productVOList", productVOList);
        //手动渲染
        WebContext springWebContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        productList = thymeleafViewResolver.getTemplateEngine()
                .process("product_list", springWebContext);

        //放入缓存
        redisTemplate.opsForValue().set("ProductList", productList);
        return productList;
    }

    /**
     * 页面静态化
     */
    @GetMapping("/list")
    public ResultVO getProductVOList() {
        List<ProductVO> productVOList = productService.findList();
        return ResultResp.SUCCESS(productVOList);
    }

    @GetMapping(value = "/detail/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody()
    public String getProductDetail(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        //取缓存
        String html = redisTemplate.opsForValue().get("product_detail" + id);
        if (html != null) {
            return html;
        }

        ProductVO productVO = productService.getDetailById(id);
        model.addAttribute("productDetail", productVO);
        long currentTime = System.currentTimeMillis();
        long startTime = productVO.getStartTime().atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        long endTime = productVO.getEndTime().atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        int spikeStatus;
        int remainTime = -1;
        if (startTime > currentTime) {
            spikeStatus = 0;
            remainTime = (int) ((startTime - currentTime) / 1000);
        } else if (endTime < currentTime) {
            spikeStatus = 1;
        } else {
            remainTime = 0;
            spikeStatus = 2;
        }
        model.addAttribute("spikeStatus", spikeStatus);
        model.addAttribute("remainTime", remainTime);
        WebContext springWebContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine()
                .process("product_detail", springWebContext);

        redisTemplate.opsForValue().set("product_detail" + id, html);

        return html;
    }
}
