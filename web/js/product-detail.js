function getDetailProduct(productID){
    $.ajax({
        url:'productDetail',
        data:{
            productID : productID
        },
        success :function(responseText){
            if(responseText !== null){
                $('product_list').remove();
                $('products').append(responseText);
            }
        }
    })
}
