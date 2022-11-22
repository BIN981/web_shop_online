/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dal.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Product;
import modal.ProductSize;
import modal.SelectedProduct;

/**
 *
 * @author ChauDM
 */
public class ModalMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static List<SelectedProduct> mapToSelectedProducts(String jsonData) throws JsonProcessingException {
        return objectMapper.readValue(jsonData, new TypeReference<List<SelectedProduct>>() {
        });
    }
    
    public static List<String> mapToListImages(String jsonData) throws JsonProcessingException{
        return objectMapper.readValue(jsonData, new TypeReference<List<String>>() {
        });
    }
    
    public static List<ProductSize> mapToListProductSizes(String jsonData) throws JsonProcessingException{
        return objectMapper.readValue(jsonData, new TypeReference<List<ProductSize>>() {
        });
    }

    public static void main(String[] args) throws JsonProcessingException {
        //        String jsonData = "[{\"productSizeId\":7,\"quantity\":1,\"price\":160},{\"productSizeId\":12,\"quantity\":1,\"price\":300},{\"productSizeId\":14,\"quantity\":1,\"price\":200}]";
//            List<SelectedProduct> selectedProducts = SelectedProductMapper.mapperToSelectedProducts(jsonData);
//            DAO dao = new DAO();
//            selectedProducts.forEach(item -> {
//                Product product = dao.getProductByProductSizeId(item.getProductSizeId());
//                item.setProduct(product);
//                System.out.println(item);
//            });
//            
//            float totalPrice = selectedProducts.stream().map(SelectedProduct::getPrice).reduce(0f, (total, element) -> total + element);
//            System.out.println(totalPrice);

        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }
}
