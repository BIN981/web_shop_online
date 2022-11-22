/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import config.Constants;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modal.Account;
import modal.Blog;
import modal.BlogCategory;
import modal.CartProduct;
import modal.Feedback;
import modal.Order;
import modal.OrderInfo;
import modal.OrderProduct;
import modal.OrderSale;
import modal.OrderStatus;
import modal.Product;
import modal.ProductCategory;
import modal.ProductSale;
import modal.ProductSize;
import modal.Slider;
import modal.User;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ADMIN
 */
public class DAO extends DBContext {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public DAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
        }
    }

    //    login
    public Account login(String username, String password) { //, String isAdmin
        String query = "SELECT * FROM dbo.account WHERE username= ? AND password = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    // check acc exit
    public Boolean checkAccountExit(String username, String email) { //, String isAdmin
        String query = "SELECT* \n"
                + "  FROM dbo.account a\n"
                + "  left join [user] u on u.id = a.[user_id]\n"
                + "  WHERE username = ? or u.email = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    // check acc exit
    public Account getAccountByUsername(String username) { //, String isAdmin
        String query = "SELECT * from [account] where username = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString("username"), rs.getString("password"), rs.getInt("user_id"), rs.getInt("role_id"));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // insert account
    public int insertAccount(String username, String password, int userId, int roleId) {
        String query = "INSERT INTO dbo.[account] (username,\n"
                + "                        password,\n"
                + "                        user_id,\n"
                + "                        role_id)\n"
                + " VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, userId);
            ps.setInt(4, roleId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // insert user
    public int insertUser(String firstName, String lastName, Boolean gender, String phone, String email, String dob, String address) {
        String query = "INSERT INTO dbo.[user] (gender,\n"
                + "                        image,\n"
                + "                        phone_number,\n"
                + "                        email,\n"
                + "                        dob,\n"
                + "                        address,\n"
                + "                        first_name,\n"
                + "                        last_name)\n"
                + "VALUES (?, NULL, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, gender);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, dob);
            ps.setString(5, address);
            ps.setString(6, firstName);
            ps.setString(7, lastName);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // update user profile
    public int updatetUser(int userId, String firstName, String lastName, String base64Image, Boolean gender, String phone, String dob, String address) {
        StringBuilder query = new StringBuilder("UPDATE dbo.[user] SET first_name = ?, last_name = ?, gender = ?, phone_number = ?, dob = ?, address = ? ");
        if (base64Image != null && !base64Image.isEmpty()) {
            query.append(", image = ? ");
        }
        query.append("WHERE id = ?");

        try {
            ps = con.prepareStatement(query.toString());
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setBoolean(3, gender);
            ps.setString(4, phone);
            ps.setString(5, dob);
            ps.setString(6, address);
            if (base64Image != null && !base64Image.isEmpty()) {
                ps.setString(7, base64Image);
                ps.setInt(8, userId);
            } else {
                ps.setInt(7, userId);
            }
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // get signed up user
    public User getUserSignedUp() {
        String query = "SELECT TOP 1 * FROM dbo.[user] ORDER BY id desc ";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("image"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("dob"),
                        rs.getString("address"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<User> getAllUserByRole(int roleId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM dbo.[user] u "
                + "left join [account] a on a.user_id = u.id "
                + "where a.role_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("image"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("dob"),
                        rs.getString("address")));
            }
        } catch (Exception e) {
        }
        return users;
    }

    public void signup(String username, String password, String firstName, String lastName, Boolean gender, String email, String phone, String dob, String address) {
        int insertUserResult = insertUser(firstName, lastName, gender, phone, email, dob, address);

        if (insertUserResult == 1) {
            int userId = getUserSignedUp().getId();
            //insert vaof aacount
            insertAccount(username, password, userId, Constants.ROLE_CUSTOMER);
        }
    }

    public List<Product> getAllProducts(String name, int productCategoryId, int page) {
        List<Product> products = new ArrayList<>();
        StringBuilder query = new StringBuilder("select distinct\n"
                + "	p.*,\n"
                + "	case \n"
                + "	when GETDATE() between sp.create_at and sp.expired_at then (100 - sp.[percentage]) * p.price / 100 else 0 \n"
                + "	end as sale_price,\n"
                + "     case\n"
                + "     when GETDATE() between sp.create_at and sp.expired_at then isnull(sp.[percentage], 0) else 0\n"
                + "     end as [sale_percentage]\n"
                + "     from product p\n"
                + "     left join (select * \n"
                + "					 from (\n"
                + "						select *, ROW_NUMBER() OVER (\n"
                + "                		PARTITION BY product_id\n"
                + "                		ORDER BY expired_at desc\n"
                + "                	   ) row_num \n"
                + "                	   from sale_price\n"
                + "					 ) t where t.row_num = 1 \n"
                + "	  ) sp on sp.product_id = p.id\n"
                + "     left join product_size ps on ps.product_id = p.id\n"
                + "     where is_active = 1 and ps.stock_quantity > 0 and [name] like ?\n");
        if (productCategoryId != 0) {
            query.append("and category_id = ? ");
        }

        query.append("order by [publish_at] desc offset ").append((page - 1) * Constants.PAGE_COUNT_PRODUCT).append(" rows fetch next ").append(Constants.PAGE_COUNT_PRODUCT).append(" rows only");
        try {
            ps = con.prepareStatement(query.toString());
            ps.setString(1, "%" + name + "%");
            if (productCategoryId != 0) {
                ps.setString(2, productCategoryId + "");
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("code"),
                        rs.getDate("publish_at"),
                        rs.getBoolean("is_active"),
                        rs.getInt("sold_quantity"),
                        rs.getInt("sale_percentage"),
                        rs.getDouble("sale_price")
                ));
            }
        } catch (Exception e) {
        }
        return products;
    }

    public int countAllProduct(String name, int productCategoryId, Boolean status) {
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("SELECT COUNT(id) FROM product where [name] like ?\n ");
        if (productCategoryId != 0) {
            query.append("and category_id = ?\n ");
        }
        if (status != null) {
            query.append("and is_active = ?\n");
        }
        try {
            ps = con.prepareStatement(query.toString());
            ps.setString(filterIndex++, "%" + name + "%");
            if (productCategoryId != 0) {
                ps.setInt(filterIndex++, productCategoryId);
            }
            if (status != null) {
                ps.setBoolean(filterIndex++, status);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Blog> getTop4Blog() {
        List<Blog> blog = new ArrayList<>();
        String query = "Select top 4 * from blog where is_active = 1 order by published_at desc";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                blog.add(new Blog(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getDate("published_at"),
                        rs.getString("content"),
                        rs.getInt("publish_by"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
        }
        return blog;

    }
// get all product and amount

    public List<ProductCategory> getAllProductCategory(String startDate, String endDate) {
        List<ProductCategory> productCategories = new ArrayList<>();
        String query = "select p.category_id, c.[name], count(p.category_id) as amount\n"
                + "  from product p\n"
                + "  left join category c on c.id = p.category_id\n"
                + "  where p.[publish_at] between ? and ?\n"
                + "  group by p.category_id, c.[name]";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                productCategories.add(new ProductCategory(rs.getInt("category_id"),
                        rs.getString("name"), rs.getInt("amount")));
            }
        } catch (Exception e) {
        }
        return productCategories;
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM [user] where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, id + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("image"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("dob"),
                        rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Slider> getAllSlider(Boolean isActive, String title) {
        List<Slider> sliders = new ArrayList<>();
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("SELECT * FROM dbo.[slider] where 1=1 ");
        if (title.trim().length() != 0) {
            query.append("and [title] like ? ");
        }

        if (isActive != null) {
            query.append("and is_active = ?\n");
        }

        try {
            ps = con.prepareStatement(query.toString());
            if (title.trim().length() != 0) {
                ps.setString(filterIndex++, "%" + title + "%");
            }

            if (isActive != null) {
                ps.setBoolean(filterIndex++, isActive);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                sliders.add(new Slider(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getString("content"),
                        rs.getBoolean("is_active"),
                        rs.getInt("publish_by"),
                        rs.getDate("publish_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sliders;
    }

    public Slider getSliderById(int sliderId, Boolean isActive) {
        StringBuilder query = new StringBuilder("select * from [slider] where id = ?\n");
        if (isActive != null) {
            query.append("and is_active = ?");
        }
        try {
            ps = con.prepareStatement(query.toString());
            ps.setInt(1, sliderId);
            if (isActive != null) {
                ps.setBoolean(2, isActive);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Slider(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getString("content"),
                        rs.getBoolean("is_active"),
                        rs.getInt("publish_by"),
                        rs.getDate("publish_at"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //Count all sliders
    public int countAllSliders(Boolean isActive, String title) {
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("SELECT COUNT(id) FROM slider where [title] like ?");

        if (isActive != null) {
            query.append("and is_active = ?");
        }
        try {

            ps = con.prepareStatement(query.toString());
            ps.setString(filterIndex++, "%" + title + "%");
            if (isActive != null) {
                ps.setBoolean(filterIndex++, isActive);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    //Blog DAO
    public List<Blog> getAllBlog(Boolean isActive, String title, int blogCategory, int page) {
        List<Blog> blog = new ArrayList<>();
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("SELECT * FROM dbo.[blog] where 1=1 ");
        if (title.trim().length() != 0) {
            query.append("and FREETEXT([title], ?) ");
        }

        if (blogCategory != 0) {
            query.append("and category_id = ? ");
        }

        if (isActive != null) {
            query.append("and is_active = ? ");
        }

        query.append("order by [published_at] desc offset ").append((page - 1)
                * Constants.PAGE_COUNT_BLOG).append(" rows fetch next ")
                .append(Constants.PAGE_COUNT_BLOG).append(" rows only");
        try {
            ps = con.prepareStatement(query.toString());
            if (title.trim().length() != 0) {
                ps.setString(filterIndex++, title);
            }
            if (blogCategory != 0) {
                ps.setInt(filterIndex++, blogCategory);
            }
            if (isActive != null) {
                ps.setBoolean(filterIndex++, isActive);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                blog.add(new Blog(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getDate("published_at"),
                        rs.getString("content"),
                        rs.getInt("publish_by"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
        }
        return blog;
    }
// lay list category blog

    public List<BlogCategory> getAllBlogCategory() {
        List<BlogCategory> blogCategory = new ArrayList<>();
        String query = "select* from blog_category";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                blogCategory.add(new BlogCategory(rs.getInt("id"),
                        rs.getString("name"),
                        null));
            }
        } catch (Exception e) {
        }
        return blogCategory;
    }

    public List<ProductCategory> getAllProductCategory() {
        List<ProductCategory> productCategory = new ArrayList<>();
        String query = "select * from category";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                productCategory.add(new ProductCategory(rs.getInt("id"),
                        rs.getString("name"), null));
            }
        } catch (Exception e) {
        }
        return productCategory;
    }

    public List<BlogCategory> getAllBlogCategory(String startDate, String endDate) {
        List<BlogCategory> blogCategory = new ArrayList<>();
        String query = "select b.category_id, bc.[name], count(b.category_id) as amount\n"
                + "  from blog b\n"
                + "  left join blog_category bc on bc.id = b.category_id\n"
                + "  where b.published_at between ? and ?\n"
                + "  group by b.category_id, bc.[name]";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogCategory.add(new BlogCategory(rs.getInt("id"),
                        rs.getString("name"), null));
            }
        } catch (Exception e) {
        }
        return blogCategory;
    }

    public int countAllBlog(Boolean isActive, String title, int blogCategoryId) {
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("SELECT COUNT(id) FROM blog where [title] like ? ");
        if (blogCategoryId != 0) {
            query.append("and category_id = ?");
        }
        if (isActive != null) {
            query.append("and is_active = ?");
        }
        try {

            ps = con.prepareStatement(query.toString());

            ps.setString(filterIndex++, "%" + title + "%");

            if (blogCategoryId != 0) {
                ps.setInt(filterIndex++, blogCategoryId);
            }

            if (isActive != null) {
                ps.setBoolean(filterIndex++, isActive);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countAllBlogBycategory(int categoryId) {
        String query = "SELECT COUNT(id) FROM blog_category where id = ? and is_active = 1";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, categoryId + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    //get all product in cart by user_id
    public List<CartProduct> getAllCartProductByUserId(int userId) {
        List<CartProduct> cartProducts = new ArrayList<>();
        String query = "select cp.[user_id], cp.product_size_id, cp.quantity, cp.id as cart_product_id, ps.size, ps.stock_quantity, p.*,\n"
                + "case when GETDATE() between sp.create_at and sp.expired_at then (100 - sp.[percentage]) * p.price / 100 else 0 end as sale_price,\n"
                + "           case\n"
                + "            when GETDATE() between sp.create_at and sp.expired_at then isnull(sp.[percentage], 0) else 0\n"
                + "              end as [sale_percentage]\n"
                + "from cart_product cp\n"
                + "inner join product_size ps on ps.id = cp.product_size_id\n"
                + "left join product p on p.id = ps.product_id\n"
                + "     left join (select * \n"
                + "					 from (\n"
                + "						select *, ROW_NUMBER() OVER (\n"
                + "                		PARTITION BY product_id\n"
                + "                		ORDER BY expired_at desc\n"
                + "                	   ) row_num \n"
                + "                	   from sale_price\n"
                + "					 ) t where t.row_num = 1 \n"
                + "	  ) sp on sp.product_id = p.id\n"
                + "where cp.[user_id] = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, userId + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                cartProducts.add(new CartProduct(rs.getInt("cart_product_id"),
                        rs.getInt("user_id"),
                        new Product(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("image"),
                                rs.getDouble("price"),
                                rs.getString("description"),
                                rs.getInt("category_id"),
                                rs.getString("code"),
                                rs.getDate("publish_at"),
                                rs.getBoolean("is_active"),
                                rs.getInt("sold_quantity"),
                                rs.getInt("sale_percentage"),
                                rs.getDouble("sale_price")),
                        rs.getInt("size"),
                        rs.getInt("product_size_id"),
                        rs.getInt("quantity")));
            }
        } catch (Exception e) {
        }
        return cartProducts;
    }

    public List<Integer> getListSizeByProductId(int productId) {
        List<Integer> sizes = new ArrayList<>();
        String query = "select size from product_size where stock_quantity > 0 and product_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                sizes.add(rs.getInt("size"));
            }
        } catch (Exception e) {
        }
        return sizes;
    }

    public Product getProductById(int productId) {
        String query = "select p.*,"
                + "case when GETDATE() between sp.create_at and sp.expired_at then sp.[percentage] * p.price / 100 else 0 end as sale_price,\n"
                + "           case\n"
                + "            when GETDATE() between sp.create_at and sp.expired_at then isnull(sp.[percentage], 0) else 0\n"
                + "              end as [sale_percentage]\n"
                + "     from product p "
                + "left join sale_price sp on sp.product_id = p.id "
                + "where p.id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("code"),
                        rs.getDate("publish_at"),
                        rs.getBoolean("is_active"),
                        rs.getInt("sold_quantity"),
                        rs.getInt("sale_percentage"),
                        rs.getDouble("sale_price"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int insertToCart(int userId, int productSizeId) {
        String query = "insert into cart_product values(?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productSizeId);
            ps.setInt(3, Constants.INITAL_QUANTITY);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateCart(int userId, int productSizeId, int quantity) {
        String query = "update cart_product set quantity = ("
                + "select quantity from cart_product "
                + "where product_size_id = ? and [user_id] = ?) + ? "
                + "where product_size_id = ? and [user_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productSizeId);
            ps.setInt(2, userId);
            ps.setInt(3, quantity);
            ps.setInt(4, productSizeId);
            ps.setInt(5, userId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateCart(int cartProductId, int quantity) {
        String query = "update cart_product set quantity = ("
                + "select quantity from cart_product "
                + "where id = ?) + ? "
                + "where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, cartProductId);
            ps.setInt(2, quantity);
            ps.setInt(3, cartProductId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int removeCartItem(int cartProductId) {
        String query = "delete from cart_product where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, cartProductId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int removeFavoriteItem(int user_id, int productId) {
        String query = "delete from product_favorite where product_id = ? and [user_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, user_id);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean checkExistsInCart(int userId, int productSizeId) {
        String query = "select * from cart_product where [user_id] = ? and product_size_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productSizeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public int addToCart(int userId, int productSizeId) {
        if (checkExistsInCart(userId, productSizeId)) {
            updateCart(userId, productSizeId, Constants.INCREASE_QUANTITY);
            return 2;
        } else {
            insertToCart(userId, productSizeId);
            return 1;
        }
    }

    public int getProductIdByCode(String productCode) {
        String query = "select id from product where code = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int countItemInCart(int userId) {
        String query = "select count(*) as [counter] from cart_product where [user_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("counter");
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int getProductSizeId(int productId, int productSize) {
        String query = "select id from product_size where product_id = ? and size = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, productSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    // change password
    public int changePassword(String username, String newPass) {
        String query = "update dbo.account set password = ? where username = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, newPass);
            ps.setString(2, username);
            ps.executeUpdate();
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    public User getEmailIfExist(String email) {
        String query = "select * from [user] where email = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("image"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("dob"),
                        rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByUserId(int user_id) {
        String query = "select * from [account] where [user_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean isExistInFavoriteProduct(int userId, int productId) {
        String query = "select * from product_favorite where [user_id] = ? and [product_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public int insertFavoriteProduct(int userId, int productId) {
        String query = "insert into product_favorite values (?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Product> getAllFavoriteProduct(int userId) {
        List<Product> products = new ArrayList<>();
        String query = "select p.*\n"
                + "from product_favorite pf\n"
                + "left join product p on p.id = pf.product_id\n"
                + "where pf.[user_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("code"),
                        rs.getDate("publish_at"),
                        rs.getBoolean("is_active"),
                        rs.getInt("sold_quantity"),
                        0,
                        0));
            }
        } catch (Exception e) {
        }
        return products;
    }

    public Product getProductByProductSizeId(int productSizeId) {
        String query = "select ps.id as productSizeId, p.*\n"
                + "from product_size ps\n"
                + "inner join product p on p.id = ps.product_id\n"
                + "where ps.id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productSizeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("code"),
                        rs.getDate("publish_at"),
                        rs.getBoolean("is_active"),
                        rs.getInt("sold_quantity"),
                        0,
                        0);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public int createOrder(int userId, float totalPrice, String orderAt, float discount, String phone, String address, String fullName, String note) {
        String query = "insert into [order] values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setFloat(2, totalPrice);
            ps.setString(3, orderAt);
            ps.setFloat(4, discount);
            ps.setString(5, Constants.ORDER_STATUS_SUBMITTED);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setString(8, fullName);
            ps.setString(9, note);
            ps.setBoolean(10, Constants.ORDER_UNFEEDBACKED);
            ps.executeUpdate();
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    public int createOrderDetail(int orderId, int productSizeId, int quantity, float price) {
        String query = "insert into [order_detail] values( ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setInt(2, productSizeId);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            ps.executeUpdate();
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    public int removeCartItem(int userId, int productSizeId) {
        String query = "delete from [cart_product] where [user_id] = ? and [product_size_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productSizeId);
            ps.executeUpdate();
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getLastestOrderIdByUserId(int userId) {
        String query = "select top 1 id from [order] where [user_id] = ? order by id desc";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public boolean isInStockItem(int productSizeId, int quantity) {
        String query = "select * \n"
                + "from product_size \n"
                + "where id = ? and stock_quantity >= ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productSizeId);
            ps.setInt(2, quantity);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public int updateStockQuantity(int productSizeId, int quantity) {
        String query = "update product_size\n"
                + "set stock_quantity = (select stock_quantity\n"
                + "from product_size \n"
                + "where id = ?) - ?\n"
                + "where id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productSizeId);
            ps.setInt(2, quantity);
            ps.setInt(3, productSizeId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<OrderProduct> getOrderProducts(int orderId) {
        List<OrderProduct> products = new ArrayList<>();
        String query = "SELECT \n"
                + "	p.id as productId,\n"
                + "	p.image,\n"
                + "     p.[name],\n"
                + "     ps.size,\n"
                + "     p.price,\n"
                + "     od.quantity\n"
                + "  FROM order_detail od \n"
                + "  left join product_size ps on ps.id = od.product_size_id\n"
                + "  left join product p on p.id = ps.product_id "
                + "  where od.[order_id] = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new OrderProduct(
                        rs.getInt("productId"),
                        rs.getString("image"),
                        rs.getString("name"),
                        rs.getInt("size"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
        } catch (Exception e) {
        }
        return products;
    }

    public int updateOrderInfo(String address, String status, int orderId, String note) {
        StringBuilder query = new StringBuilder("update [order] \n");
        if (StringUtils.isNotBlank(address)) {
            query.append("set [address] = ?,\n");
            query.append("note = ?\n");
        }
        if (status != null) {
            query.append("set [status] = ?\n");
        }
        query.append("where id = ?");
        int filterIndex = 1;
        try {
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(address)) {
                ps.setString(filterIndex++, address);
                ps.setString(filterIndex++, note);
            }
            if (status != null) {
                ps.setString(filterIndex++, status);
            }
            ps.setInt(filterIndex, orderId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Order> getAllOrders(String userId, String orderAtFrom, String orderAtTo, String customerName, Float totalCostFrom, Float totalCostTo, String status, int page) {
        List<Order> orders = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT distinct o.id as order_id,\n"
                + "	o.order_at,\n"
                + "	o.fullname,\n"
                + "	o.total_price,\n"
                + "	o.[status]\n"
                + "  FROM [order] o\n"
                + "  left join order_detail od on od.order_id = o.id\n"
                + "  left join product_size ps on ps.id = od.product_size_id\n"
                + "  left join product p on p.id = ps.product_id\n"
                + "  where 1=1\n");
        if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
            query.append("and o.[order_at] between ? and ?\n");
        } else if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isBlank(orderAtTo)) {
            query.append("and o.[order_at] >= ?\n");
        } else if (StringUtils.isBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
            query.append("and o.[order_at] <= ?\n");
        }

        if (StringUtils.isNotBlank(userId)) {
            query.append("and o.user_id = ?\n");
        }

        if (StringUtils.isNotBlank(customerName)) {
            query.append("and o.fullname like ?\n");
        }

        if (totalCostFrom != null && totalCostTo != null) {
            query.append("and o.[total_price] between ? and ?\n");
        } else if (totalCostFrom != null && totalCostTo == null) {
            query.append("and o.[total_price] >= ?\n");
        } else if (totalCostFrom == null && totalCostTo != null) {
            query.append("and o.[total_price] <= ?\n");
        }

        if (StringUtils.isNotBlank(status)) {
            query.append("and o.[status] = ?\n");
        }
        query.append("order by o.[order_at] desc offset ").append((page - 1) * Constants.PAGE_COUNT_MANAGE_ORDER).append(" rows fetch next ").append(Constants.PAGE_COUNT_MANAGE_ORDER).append(" rows only");

        int filterIndex = 1;

        try {
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
                ps.setString(filterIndex++, orderAtFrom);
                ps.setString(filterIndex++, orderAtTo);
            } else if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isBlank(orderAtTo)) {
                ps.setString(filterIndex++, orderAtFrom);
            } else if (StringUtils.isBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
                ps.setString(filterIndex++, orderAtTo);
            }

            if (StringUtils.isNotBlank(userId)) {
                ps.setString(filterIndex++, userId);
            }

            if (StringUtils.isNotBlank(customerName)) {
                ps.setString(filterIndex++, "%" + customerName + "%");
            }

            if (totalCostFrom != null && totalCostTo != null) {
                ps.setFloat(filterIndex++, totalCostFrom);
                ps.setFloat(filterIndex++, totalCostTo);
            } else if (totalCostFrom != null && totalCostTo == null) {
                ps.setFloat(filterIndex++, totalCostFrom);
            } else if (totalCostFrom == null && totalCostTo != null) {
                ps.setFloat(filterIndex++, totalCostTo);
            }

            if (StringUtils.isNotBlank(status)) {
                ps.setString(filterIndex, status);
            }

            rs = ps.executeQuery();
//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setFullName(rs.getString("fullName"));
                order.setOrderAt(rs.getString("order_at"));
                order.setTotalPrice(rs.getInt("total_price"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
        } catch (Exception e) {
        }

        for (Order order : orders) {
            List<OrderProduct> products = getOrderProducts(order.getOrderId());
            if (products != null && !products.isEmpty()) {
                order.setProducts(products);
            }
        }
        return orders;
    }

    public List<ProductSize> getAllProductSizeByOrderId(int orderId) {
        List<ProductSize> productSizes = new ArrayList<>();
        String query = "select * from order_detail where order_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                productSizes.add(new ProductSize(rs.getInt("product_size_id"), /// lưu tạm product_size_id vào product_id
                        0,
                        rs.getInt("quantity")));
            }
        } catch (Exception e) {
        }
        return productSizes;
    }

    public int countAllOrders(String userId, String orderAtFrom, String orderAtTo, String customerName, Float totalCostFrom, Float totalCostTo, String status) {
        StringBuilder query = new StringBuilder("SELECT count(distinct o.id)"
                + "  FROM [order] o\n"
                + "  left join order_detail od on od.order_id = o.id\n"
                + "  left join product_size ps on ps.id = od.product_size_id\n"
                + "  left join product p on p.id = ps.product_id\n"
                + "  where 1=1\n");
        if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
            query.append("and o.[order_at] between ? and ?\n");
        } else if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isBlank(orderAtTo)) {
            query.append("and o.[order_at] >= ?\n");
        } else if (StringUtils.isBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
            query.append("and o.[order_at] <= ?\n");
        }

        if (StringUtils.isNotBlank(userId)) {
            query.append("and o.user_id = ?\n");
        }

        if (StringUtils.isNotBlank(customerName)) {
            query.append("and o.fullname like ?\n");
        }

        if (totalCostFrom != null && totalCostTo != null) {
            query.append("and o.[total_price] between ? and ?\n");
        } else if (totalCostFrom != null && totalCostTo == null) {
            query.append("and o.[total_price] >= ?\n");
        } else if (totalCostFrom == null && totalCostTo != null) {
            query.append("and o.[total_price] <= ?\n");
        }

        if (StringUtils.isNotBlank(status)) {
            query.append("and o.[status] = ?\n");
        }

        int filterIndex = 1;

        try {
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
                ps.setString(filterIndex++, orderAtFrom);
                ps.setString(filterIndex++, orderAtTo);
            } else if (StringUtils.isNotBlank(orderAtFrom) && StringUtils.isBlank(orderAtTo)) {
                ps.setString(filterIndex++, orderAtFrom);
            } else if (StringUtils.isBlank(orderAtFrom) && StringUtils.isNotBlank(orderAtTo)) {
                ps.setString(filterIndex++, orderAtTo);
            }

            if (StringUtils.isNotBlank(userId)) {
                ps.setString(filterIndex++, userId);
            }

            if (StringUtils.isNotBlank(customerName)) {
                ps.setString(filterIndex++, "%" + customerName + "%");
            }

            if (totalCostFrom != null && totalCostTo != null) {
                ps.setFloat(filterIndex++, totalCostFrom);
                ps.setFloat(filterIndex++, totalCostTo);
            } else if (totalCostFrom != null && totalCostTo == null) {
                ps.setFloat(filterIndex++, totalCostFrom);
            } else if (totalCostFrom == null && totalCostTo != null) {
                ps.setFloat(filterIndex++, totalCostTo);
            }

            if (StringUtils.isNotBlank(status)) {
                ps.setString(filterIndex, status);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int countBlog(String startDate, String endDate) {
        String query = "select count(*) as[active] from blog where  [published_at] between\n"
                + "+  ? and ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;

    }

    public int countAllActive() {
        String query = "select count(*) as[active] from product where is_active = 1 ";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

// count total order 
    public int countTotalOrder(String startDate, String endDate) {
        String query = "  select count(*)  from [order] where order_at between\n"
                + "+  ? and ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;

    }

    public double totalRevenues(String startDate, String endDate) {
        String query = "  select sum(total_price)from [order] where order_at between\n"
                + "+  ? and ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // get all order by category
    public List<OrderSale> getAllOrderCategory(String startDate, String endDate) {
        List<OrderSale> product = new ArrayList<>();
        String query = "select [status],COUNT([status]) as amount from [order]\n"
                + "where order_at between\n"
                + "+  ? and ?\n"
                + "group by [status]";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.add(new OrderSale(null,
                        rs.getString("status"), rs.getInt("amount")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public Blog getBlogById(int blogId) {
        String query = "select * from [blog] where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, blogId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Blog(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getDate("published_at"),
                        rs.getString("content"),
                        rs.getInt("publish_by"),
                        rs.getBoolean("is_active"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int updateBlog(int blogId, String image, String title, int categoryId, String content) {
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("update blog set title = ?, category_id = ?, content = ?");
        if (StringUtils.isNotBlank(image)) {
            query.append(", image = ? ");
        }

        query.append(" where id = ?");

        try {
            ps = con.prepareStatement(query.toString());
            ps.setString(filterIndex++, title);
            ps.setInt(filterIndex++, categoryId);
            ps.setString(filterIndex++, content);
            if (StringUtils.isNotBlank(image)) {
                ps.setString(filterIndex++, image);
            }
            ps.setInt(filterIndex, blogId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateSlider(int sliderId, String image, String title, String content) {
        int filterIndex = 1;
        StringBuilder query = new StringBuilder("update slider set title = ?, content = ?");
        if (StringUtils.isNotBlank(image)) {
            query.append(", image = ? ");
        }

        query.append(" where id = ?");

        try {
            ps = con.prepareStatement(query.toString());
            ps.setString(filterIndex++, title);
            ps.setString(filterIndex++, content);
            if (StringUtils.isNotBlank(image)) {
                ps.setString(filterIndex++, image);
            }
            ps.setInt(filterIndex, sliderId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int insertBlog(String image, String title, int categoryId, String content, int publishBy) {
        Date now = new Date();
        int filterIndex = 1;
        String query = "insert into blog(image, title, category_id, content, publish_by, published_at, is_active) "
                + "values(?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(query);
            ps.setString(filterIndex++, image);
            ps.setString(filterIndex++, title);
            ps.setInt(filterIndex++, categoryId);
            ps.setString(filterIndex++, content);
            ps.setInt(filterIndex++, publishBy);
            ps.setString(filterIndex++, now.toLocaleString());
            ps.setBoolean(filterIndex++, Constants.STATUS_ACTIVE);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int insertSlider(String image, String title, String content, int publishBy) {
        Date now = new Date();
        int filterIndex = 1;
        String query = "insert into slider(image, title, content, publish_by, publish_at, is_active) "
                + "values(?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(query);
            ps.setString(filterIndex++, image);
            ps.setString(filterIndex++, title);
            ps.setString(filterIndex++, content);
            ps.setInt(filterIndex++, publishBy);
            ps.setString(filterIndex++, now.toLocaleString());
            ps.setBoolean(filterIndex++, Constants.STATUS_ACTIVE);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateStatusPost(int postId, Boolean status) {
        String query = "update blog set is_active = ? where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, status);
            ps.setInt(2, postId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateStatusSlider(int sldierId, Boolean status) {
        String query = "update slider set is_active = ? where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, status);
            ps.setInt(2, sldierId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<User> getAllUser(String name, int roleId) {
        List<User> users = new ArrayList<>();
        StringBuilder query = new StringBuilder("select * from [user] u\n"
                + "  inner join account a on a.[user_id] = u.id where a.role_id <> 1\n");
        if (StringUtils.isNotBlank(name)) {
            query.append("and u.first_name + ' ' + u.last_name like ?\n");
        }

        if (roleId > 0 && roleId < 6) {
            query.append("and a.role_id = ?");
        }

        int filterIndex = 1;
        try {
            ps = con.prepareStatement(query.toString());
            if (StringUtils.isNotBlank(name)) {
                ps.setString(filterIndex++, "%" + name + "%");
            }

            if (roleId > 0 && roleId < 6) {
                ps.setInt(filterIndex++, roleId);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("gender"),
                        rs.getString("image"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getDate("dob"),
                        rs.getString("address")));
            }
        } catch (Exception e) {
        }
        return users;

    }

// sum of category
    public List<ProductCategory> sumOfCategory(String startDate, String endDate) {
        List<ProductCategory> product = new ArrayList<>();
        String query = "select c.id, c.[name], sum(p.price) as [sum]\n"
                + "  from product as p\n"
                + "  left join category c on p.category_id = c.id\n"
                + "  where  [publish_at] between\n"
                + "+  ? and ?\n"
                + "  group by c.id, c.[name]";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.add(new ProductCategory(rs.getInt("id"),
                        rs.getString("name"), rs.getInt("sum")));
            }
        } catch (Exception e) {
        }
        return product;
    }

    public int successOrder(String startDate, String endDate) {
        String query = "  select  COUNT(*) as amount from [order] where [status] ='XN' and order_at between\n"
                + "+  ? and ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("amount");
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<OrderStatus> listStatusOrder(String startDate, String endDate) {
        List<OrderStatus> list = new ArrayList<>();
        String query = "select id,total_price,order_at,discount,fullname from [order] where order_at between\n"
                + "+  ? and ? and [status] ='cxn'";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderStatus(rs.getInt("id"),
                        rs.getInt("total_price"),
                        rs.getDate("order_at"),
                        rs.getInt("discount"),
                        rs.getString("fullname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int updateRole(int role, int userId) {
        int filterIndex = 1;
        String query = "update account set role_id =? where [user_id]=?";
        try {
            ps = con.prepareStatement(query.toString());
            ps.setInt(filterIndex++, role);
            ps.setInt(filterIndex++, userId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public OrderInfo getOrderInfo(int orderId) {
        String query = "select o.fullname, o.phone, o.[address], o.total_price , o.status, o.note, o.is_feedbacked\n"
                + "From [order] o \n"
                + "where o.id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new OrderInfo(
                        rs.getString("fullname"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getDouble("total_price"),
                        rs.getString("note"),
                        rs.getString("status"),
                        rs.getBoolean("is_feedbacked"),
                        null);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List getBlogCategory(String startDate, String endDate) {
        List<OrderSale> blogcategory = new ArrayList<>();
        String query = "select bc.[name], COUNT(b.id) as amount from blog b left join blog_category bc\n"
                + "on bc.id = b.category_id \n"
                + "where  b.published_at between\n"
                + "? and ?\n"
                + "group by bc.[name] ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogcategory.add(new OrderSale(null,
                        rs.getString("name"), rs.getInt("amount")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogcategory;
    }

    public int insertFeedback(int orderId, int userId, int rating, String content) {
        String query = "insert into feedbacks values(?, ?, ?, ? , ?)";
        Date now = new Date();
        int filterIndex = 1;
        try {
            ps = con.prepareStatement(query);
            ps.setString(filterIndex++, content);
            ps.setString(filterIndex++, now.toLocaleString());
            ps.setInt(filterIndex++, userId);
            ps.setInt(filterIndex++, rating);
            ps.setInt(filterIndex++, orderId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int insertFeedbackImage(int feedbackId, String image) {
        String query = "insert into feedback_image values(?, ?)";
        int filterIndex = 1;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(filterIndex++, feedbackId);
            ps.setString(filterIndex++, image);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getLastestFeedbackByUserIdAndOrderId(int userId, int orderId) {
        String query = "select top 1 id from [feedbacks] where [publish_by] = ? and order_id = ? order by id desc";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int updateFeedbackOrder(int orderId, Boolean isFeedbacked) {
        String query = "update [order] set is_feedbacked = ? where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, isFeedbacked);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Feedback> getAllFeedbackByProductId(int productId, int page) {
        List<Feedback> feedbacks = new ArrayList<>();
        StringBuilder query = new StringBuilder("  select distinct p.id as productId,\n"
                + "		p.name as productName,\n"
                + "		f.*,\n"
                + "		u.first_name + ' ' + u.last_name as fullName,\n"
                + "		u.image as avatar\n"
                + "  from feedbacks f\n"
                + "  left join [order] o on o.id = f.order_id\n"
                + "  left join order_detail od on od.order_id = o.id\n"
                + "  left join product_size ps on ps.id = od.product_size_id\n"
                + "  left join product p on p.id = ps.product_id\n"
                + "  left join [user] u on u.id = f.publish_by\n"
                + "  where p.id = ?\n");

        query.append("order by f.date desc offset ").append((page - 1) * Constants.PAGE_COUNT_FEEDBACK).append(" rows fetch next ").append(Constants.PAGE_COUNT_FEEDBACK).append(" rows only");
        try {
            ps = con.prepareStatement(query.toString());
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                feedbacks.add(new Feedback(rs.getInt("id"),
                        rs.getString("content"),
                        rs.getDate("date"),
                        rs.getInt("publish_by"),
                        rs.getInt("rate_star"),
                        rs.getInt("order_id"),
                        rs.getString("fullName"),
                        rs.getString("avatar"),
                        rs.getInt("productId"),
                        rs.getString("productName"),
                        null));
            }
        } catch (Exception e) {
        }
        return feedbacks;
    }

    public List<String> getFeedbackImageByFeedbackId(int feedbackId) {
        List<String> images = new ArrayList<>();
        String query = "select * from feedback_image where feedback_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, feedbackId);
            rs = ps.executeQuery();
            while (rs.next()) {
                images.add(rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return images;
    }

    public int countFeedbackImageByProductId(int productId) {
        String query = "  select count(distinct p.id) as counter\n"
                + "  from feedbacks f\n"
                + "  left join [order] o on o.id = f.order_id\n"
                + "  left join order_detail od on od.order_id = o.id\n"
                + "  left join product_size ps on ps.id = od.product_size_id\n"
                + "  left join product p on p.id = ps.product_id\n"
                + "  left join [user] u on u.id = f.publish_by\n"
                + "  where p.id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("counter");
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int removeFeedbackById(int feedbackId, int publishBy) {
        String query = "delete from feedbacks where id = ? and publish_by = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, feedbackId);
            ps.setInt(2, publishBy);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int removeFeedbackImagesByFeebackId(int feedbackId) {
        String query = "delete from feedback_image where feedback_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, feedbackId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Product> getManageProducts(String name, int productCategoryId, Float priceFrom, Float priceTo, Boolean status) {
        List<Product> products = new ArrayList<>();
        StringBuilder query = new StringBuilder("select \n"
                + "	p.*"
                + "     from product p\n"
                + "     where [name] like ?\n");
        if (productCategoryId != 0) {
            query.append("and category_id = ?\n");
        }
        if (status != null) {
            query.append("and is_active = ?\n");
        }

        if (priceFrom != null && priceTo != null) {
            query.append("and p.price between ? and ?\n");
        } else {
            if (priceFrom != null) {
                query.append("and p.price >= ?\n");
            }
            if (priceTo != null) {
                query.append("and p.price <= ?\n");
            }
        }

        query.append("order by [publish_at] desc");
        try {
            int filterIndex = 1;
            ps = con.prepareStatement(query.toString());
            ps.setString(filterIndex++, "%" + name + "%");
            if (productCategoryId != 0) {
                ps.setInt(filterIndex++, productCategoryId);
            }

            if (status != null) {
                ps.setBoolean(filterIndex++, status);
            }

            if (priceFrom != null && priceTo != null) {
                ps.setFloat(filterIndex++, priceFrom);
                ps.setFloat(filterIndex++, priceTo);
            } else {
                if (priceFrom != null) {
                    ps.setFloat(filterIndex++, priceFrom);
                }
                if (priceTo != null) {
                    ps.setFloat(filterIndex++, priceTo);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("code"),
                        rs.getDate("publish_at"),
                        rs.getBoolean("is_active"),
                        rs.getInt("sold_quantity"),
                        0,
                        0
                ));
            }
        } catch (Exception e) {
        }
        return products;
    }

    public String getCategoryNameByProductId(int productId) {
        String query = "  select c.name\n"
                + "  from product p\n"
                + "  inner join category c on c.id = p.category_id\n"
                + "  where p.id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<ProductSize> getAllSizeByProductId(int productId) {
        List<ProductSize> sizes = new ArrayList<>();
        String query = "select * from product_size where product_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                sizes.add(new ProductSize(productId,
                        rs.getInt("size"),
                        rs.getInt("stock_quantity")));
            }
        } catch (Exception e) {
        }
        return sizes;
    }

    //update lại list size
    public int updateProductSize(int productId, int size, int stockQuantity) {
        String query = "update product_size set stock_quantity = ? where product_id = ? and size = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, stockQuantity);
            ps.setInt(2, productId);
            ps.setFloat(3, size);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isExistSize(int productId, int size) {
        String query = "select * from product_size where product_id = ? and size = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, size);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int insertProductSize(int productId, int size, int stockQuantity) {
        String query = "insert into product_size values(?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setFloat(2, size);
            ps.setInt(3, stockQuantity);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<ProductSale> getAllSaleByProductId(int productId) {
        List<ProductSale> sales = new ArrayList<>();
        String query = "select * from sale_price where product_id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                sales.add(new ProductSale(productId,
                        rs.getDate("create_at"),
                        rs.getDate("expired_at"),
                        rs.getInt("percentage")));
            }
        } catch (Exception e) {
        }
        return sales;
    }

    public int insertProductSale(int productId, String fromDate, String toDate, int percentage) {
        String query = "insert into sale_price values(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, fromDate);
            ps.setString(3, toDate);
            ps.setInt(4, percentage);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateProductStatus(int productId, int status) {
        String query = "update [dbo].[product] set is_active = ? where id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, productId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int editProduct(int productId, String productName, double price, String image, String productCode, int categoryId, String detail) {
        StringBuilder query = new StringBuilder("update product set name = ?, price = ?, description = ?, category_id = ?, code = ?");
        if (StringUtils.isNotBlank(image)) {
            query.append(", image = ? ");
        }
        query.append(" where id = ?");
        int filterIndex = 1;

        try {
            ps = con.prepareStatement(query.toString());
            ps.setString(filterIndex++, productName);
            ps.setDouble(filterIndex++, price);
            ps.setString(filterIndex++, detail);
            ps.setInt(filterIndex++, categoryId);
            ps.setString(filterIndex++, productCode);
            if (StringUtils.isNotBlank(image)) {
                ps.setString(filterIndex++, image);
            }
            ps.setInt(filterIndex, productId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int insertProduct(String productName, double price, String image, String productCode, int categoryId, String detail, String publishAt) {
        String query = "insert into product(name, price, image, description, category_id, code, publish_at, is_active) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, productName);
            ps.setDouble(2, price);
            ps.setString(3, image);
            ps.setString(4, detail);
            ps.setInt(5, categoryId);
            ps.setString(6, productCode);
            ps.setString(7, publishAt);
            ps.setInt(8, Constants.PRODUCT_ACTIVE ? 1 : 0);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println(e);
            return 0;
        }
    }

    public List<Slider> getTop5Slider(int sliderId) {
        List<Slider> sliders = new ArrayList<>();
        String query = "SELECT * FROM dbo.[slider] where id <> ? and is_active = 1"
                + "order by publish_at desc";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, sliderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                sliders.add(new Slider(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image"),
                        rs.getString("content"),
                        rs.getBoolean("is_active"),
                        rs.getInt("publish_by"),
                        rs.getDate("publish_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sliders;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
//        System.out.println(dao.getSliderById());
    }
}
