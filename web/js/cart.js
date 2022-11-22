
var products = [];
function SelectItem(productSizeId, size, quantity, price, checked) {
    let item = {
        "productSizeId": productSizeId,
        "size": size,
        "quantity": quantity,
        "price": price
    };
    if (!checked) {
        products = products.filter((value, index, arr) => {
            return value.productSizeId !== item.productSizeId;
        });
    } else {
        products.push(item);
    }

    console.log(products);
}

function BuyNow() {
    $.ajax({
        url: 'previewOrder',
        data: {
            selectedProducts: JSON.stringify(products)
        },
        success: function (responseText) {
            switch (responseText) {
                case "success":
                    window.location.assign("order.jsp");
                    products = [];
                    break;
   
            }
        }
    });
    
}

function Decrease(cartProductId) {
    $.ajax({
        url: 'decreaseCartItem',
        data: {
            cartProductId: cartProductId
        },
        success: function (responseText) {
            if (responseText === 'Decrease successful') {
                location.reload();
            }
        }
    });
}

function Increase(cartProductId) {
    $.ajax({
        url: 'increaseCartItem',
        data: {
            cartProductId: cartProductId
        },
        success: function (responseText) {
            if (responseText === 'Increase successful') {
                location.reload();
            }
        }
    });
}

function Remove(cartProductId) {
    if (confirm("Do you want to remove this item?")) {
        $.ajax({
            url: 'removeCartItem',
            data: {
                cartProductId: cartProductId
            },
            success: function (responseText) {
                if (responseText === 'Remove successful') {
                    location.reload();
                }
            }
        });
    }

}

