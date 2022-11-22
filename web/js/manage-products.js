var tableSize;
const modal = document.querySelector(".modal");

const sizes = new Map();

var size;
var quantity;

window.onbeforeunload = function () {
    localStorage.setItem("product_name", $('#product_name').val());
    localStorage.setItem("price_from", $('#price_from').val());
    localStorage.setItem("price_to", $('#price_to').val());
    localStorage.setItem("product_status", $('#product_status').val());
    localStorage.setItem("product_category", $('#product_category').val());
}

window.onload = function () {
    if (localStorage.getItem("product_name") !== null)
        $('#product_name').val(localStorage.getItem("product_name"));
    if (localStorage.getItem("price_from") !== null)
        $('#price_from').val(localStorage.getItem("price_from"));
    if (localStorage.getItem("price_to") !== null)
        $('#price_to').val(localStorage.getItem("price_to"));
    if (localStorage.getItem("product_status") !== null)
        $('#product_status').val(localStorage.getItem("product_status"));
    if (localStorage.getItem("product_category") !== null)
        $('#product_category').val(localStorage.getItem("product_category"));
}

function AddSize() {
    if (size.value && quantity.value) {
        let objSize = {
            size: size.value,
            quantity: quantity.value
        };
        addSizeToList(objSize);
        console.log(sizes);
        RenderTableSize();
        size.value = "";
        quantity.value = "";
        size.focus();
    } else {
        alert("Input all fields please!");
    }
}

function addSizeToList(objSize) {
    if (objSize.size < 35 || objSize.size > 50) {
        alert("Size must be in range 35 - 50!");
        return;
    }
    if (objSize.quantity < 0) {
        alert("Quantity must be greater than 0!");
        return;
    }
    if (sizes.has(objSize.size)) {
        if (confirm(`Size ${objSize.size} had already, replace it?`)) {
            sizes.set(objSize.size, objSize.quantity);
        }
    } else {
        sizes.set(objSize.size, objSize.quantity);
    }
}

function initSizes() {
    sizes.clear();
    const alreadySizes = document.querySelectorAll(".size");
    alreadySizes.forEach((item) => {
        let objSize = {
            size: item.children[0].innerText,
            quantity: item.children[1].innerText
        };
        sizes.set(objSize.size, objSize.quantity);
    });
    console.log(sizes);
}

function RenderTableSize() {
    tableSize.innerHTML = `<tr>
    <th>Size</th>
    <th>Stock Quantity</th>
  </tr>`;
    sizes.forEach((value, key, map) => {
        let tr = document.createElement("tr");
        tr.innerHTML = `<td>${key}</td>
        <td class="stock-quantity">${value}
          <i class="fa-solid fa-xmark"></i>
        </td>`;
        tableSize.appendChild(tr);
        let removeIcon = tr.querySelector(".stock-quantity > i");
        removeIcon.addEventListener("click", (e) => {
            if (confirm("Are you sure to remove this size of product?")) {
                tr.remove();
                sizes.delete(tr.children[0].innerText);
                console.log(sizes);
            }
        });
    });
}

function initRemoveSize() {
    const removeSizeIcons = document.querySelectorAll(
            ".size .stock-quantity > i"
            );
    console.log(removeSizeIcons);
    removeSizeIcons.forEach((item) => {
        item.removeEventListener("click", (e) => {
            e.preventDefault;
        });
        item.addEventListener("click", (e) => {
            if (confirm("Are you sure to remove this size of product?")) {
                e.target.parentElement.parentElement.remove();
                let targetSize = e.target.parentElement.previousElementSibling;
                sizes.delete(targetSize.innerText);
                console.log(sizes);
            }
        });
    });
}

function ClickSize(productId) {

    $.ajax({
        url: "manageProductSize",
        data: {
            productId: productId
        },
        success: function (responseText) {
            modal.innerHTML = responseText;
            modal.classList.remove("hide");

            const closeIcon = document.querySelector(".edit-modal > i");

            closeIcon.addEventListener("click", (e) => {
                modal.classList.add("hide");
                modal.innerHTML = "";
            });

            initSizes();
            initRemoveSize();

            size = document.querySelector(".product-sizes-input input[name=size]");
            quantity = document.querySelector(
                    ".product-sizes-input input[name=quantity]"
                    );
            tableSize = document.querySelector(".product-size-list");
        }
    });
}

function ClickSale(productId) {

    $.ajax({
        url: "manageProductSale",
        data: {
            productId: productId
        },
        success: function (responseText) {
            modal.innerHTML = responseText;
            modal.classList.remove("hide");

            const closeIcon = document.querySelector(".edit-modal > i");

            closeIcon.addEventListener("click", (e) => {
                modal.classList.add("hide");
                modal.innerHTML = "";
            });

        }
    });
}

function SaveSize(productId) {
    let sizesJSON = [];
    sizes.forEach((value, key, map) => {
        let product_size = {
            productId: productId,
            size: key,
            stockQuantity: value
        };
        sizesJSON.push(product_size);
    });

    $.ajax({
        type: 'POST',
        url: "manageProductSize",
        data: {
            productSizes: JSON.stringify(sizesJSON),
        },
        success: function (responseText) {
            if (responseText === "done") {
                modal.classList.add("hide");
                modal.innerHTML = "";
                showNotification("success", "Save product sizes successful");
                sizes.clear();
            }
        }
    });
}

function UpdateProductStatus(productId, status) {
    $.ajax({
        type: 'POST',
        url: "updateProductStatus",
        data: {
            productId: productId,
            status: status
        },
        success: function (responseText) {
            if (responseText === "success") {
                window.location.reload();
            } else {
                alert("Update failed! Occurs when update product status...");
            }
        }
    });
}

function ClickEdit(productId) {
    document.querySelector('.modal').classList.remove("hide");
    $.ajax({
        url: 'editProduct',
        data: {
            productId: productId
        },
        success: function (responseText) {
            $('.modal').html(responseText);
            const closeIcon = document.querySelector(".modal__close");
            const modal = document.querySelector(".modal");


            closeIcon.addEventListener("click", e => {
                modal.classList.add("hide");
                modal.innerHTML = "";
            });

            const thumbnailInput = document.querySelector(".modal__blog-image");
            const thumbnail = document.querySelector("#previewThumbnail");

            thumbnailInput.addEventListener('change', (e) => {
                var reader = new FileReader();
                reader.onload = function () {
                    thumbnail.src = reader.result;
                    document.querySelector("#image-base64").value = reader.result;
                };
                reader.readAsDataURL(e.target.files[0]);
            });
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
    noti_box.appendChild(noti);
    setTimeout(() => {
        noti.style.animation = "disappear 1s linear forwards";
    }, 2000);
    setTimeout(() => {
        noti.remove();
    }, 4000);
}

