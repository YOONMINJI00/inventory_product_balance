package com.ipb.controller;

import com.ipb.domain.Product;
import com.ipb.domain.StockInfo;
import com.ipb.domain.StoreProduct;
import com.ipb.service.ProductService;
import com.ipb.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storeproduct")
public class StoreProductController {

  @Autowired
  StoreProductService storeProductService;

//이건 그냥 스토어 id 와 관련없이 다 뿌려는거라서 필요가 없다.
//  @GetMapping("/list")
//  public List<StoreProduct> get() {
//    try {
//      List<StoreProduct> storeProducts = productService.get();
//      return storeProducts;
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//이것도 발주의 개념이라 필요 없음
//  @PostMapping("/add")
//  public StoreProduct register(StoreProduct product) {
//    try {
//      productService.register(product);
//      return product;
//    } catch (Exception e) {
//      e.printStackTrace();
//      return null;
//    }
//  }

  @GetMapping("/detail")
  public StoreProduct detail(Long id) {
    try {
      return storeProductService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
// 이부분은 삭제인데 삭제는 되는것이 아니고 재고를 0으로 만드는 것이다.
  @DeleteMapping("/delete")
  public void delete(Long id) {
    try {
      storeProductService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
//상품의 수량과 이용중을 업데이트 한다.
  @PutMapping("/update")
  public StoreProduct update(StoreProduct product) {
    try {
      storeProductService.modify(product);
      return product;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  //해당점포의 보유상품 전체조회
  @GetMapping("/list/{store_id}")
  public List<StockInfo>allProductByStoreId(Long store_id) {
    try {
      List<StockInfo> selectstoreproduct = storeProductService.selectstoreproduct(store_id);
      return selectstoreproduct;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  //해당 점포보유상품을 카테고리 이름별로 분류
  @GetMapping("/categoryname/{store_id}")
  public List<StockInfo> getByCategory(String categoryname,@PathVariable Long store_id) {
    try {
      List<StockInfo> selectcategoryname = storeProductService.selectcategoryname(categoryname,store_id);
      return selectcategoryname;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  //해당 점포 보유 상품을 검색해서 찾아오기 (리스트로 가능)
  @GetMapping("/searchname/{store_id}")
  public List<StockInfo> searchByName(String txt,@PathVariable Long store_id) {
    try {
      List<StockInfo> selectcategoryname = storeProductService.searchstoreproduct(txt,store_id);
      return selectcategoryname;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}





























