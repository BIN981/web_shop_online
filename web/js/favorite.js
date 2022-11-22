function RemoveFavoriteItem(productId) {
    $.ajax({
        url: 'removeFavorite',
        data: {
            productId: productId
        },
        success: function (responseText) {
            switch (responseText) {
                case "Remove success":
                    showNotification("success", "Remove product successful");
                    setTimeout(() => {
                        window.location.reload();
                    }, 3000)
                    break;
                case "Remove faild":
                    showNotification("error", "Error when remove product from your favorite");
                    break;
                default:
                    alert("Please sign in to use this function....");
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
