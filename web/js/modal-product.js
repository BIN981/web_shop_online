function SelectSize(productId) {
    $.ajax({
        url: 'sizeSelect',
        data: {
            productId: productId
        },
        success: function (responseText) {
            $('#modal').html(responseText);
            const closeIcon = document.querySelector(".modal__close");
            const modal = document.querySelector(".modal__detail");


            closeIcon.addEventListener("click", e => {
                modal.classList.add("hide");
            });
        }
    });
}

function AddToCart(productCode) {
    const productSizes = document.querySelectorAll(".size");
    console.log(productSizes);
    var productSize = 0;
    for (var size of productSizes) {
        console.log(size);
        if (size.checked) {
            productSize = size.value;
            break;
        }
    }
    console.log(productSize);
    $.ajax({
        url: 'addToCart',
        data: {
            productCode: productCode,
            productSize: productSize
        },
        success: function (responseText) {
            switch (responseText) {
                case "Add successful":
                    $("#modal").html("");
                    showNotification("success", "Add product to cart successful");
                    setTimeout(() => {
                        window.location.reload();
                    }, 3000)
                    break;
                case "Add faild":
                    $("#modal").html("");
                    showNotification("error", "Error when add product to cart");
                    break;
                default:
                    alert("Please sign in by CUSTOMER to use this function....");
                    break;
            }

        }
    });

}

function AddToFavorite(productId) {
    $.ajax({
        url: 'addToFavorite',
        data: {
            productId: productId
        },
        success: function (responseText) {
            switch (responseText) {
                case "Add successful":
                    showNotification("success", "Add product to favorite successful");
                    break;
                case "Add faild":
                    showNotification("error", "Error when add product to favorite");
                    break;
                case "Sign in first":
                    alert("Please sign in to use this function....");
                    break;

                default:

                    break;
            }
        }
    });
}



const noti_box = document.querySelector("#notification-box");
function showNotification(type, message) {
    let noti = document.createElement("div");
    noti.className = `notification ${type}`;
    noti.innerHTML = `
    <i class="fa-solid fa-xmark close"></i>
    <div class="notification__title">
      <i class="fa-solid fa-circle-info"></i>
      <span>${type.charAt(0).toUpperCase() + type.slice(1)}</span>
    </div>
    <div class="notification__detail">
      ${message}
    </div>
    `;

    const notiClose = document.querySelectorAll(".close");
    notiClose.forEach(item => {
        item.addEventListener("click", e => {
            item.parentElement.remove();
        });
    });
    noti_box.appendChild(noti)
    setTimeout(() => {
        noti.style.animation = "disappear 1s linear forwards";
    }, 2000);
    setTimeout(() => {
        noti.remove()
    }, 4000);
}

function RemoveFeedback(feedbackId, productId){
    if(confirm("Are you sure to remove your feedback?")){
        window.location.assign(`removeFeedback?feedbackId=${feedbackId}&productId=${productId}`);
    }
}
